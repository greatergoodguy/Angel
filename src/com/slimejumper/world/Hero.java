package com.slimejumper.world;

import java.util.Random;

import com.slimejumper.Assets;
import com.slimejumper.levels.World;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.attacks.MusicNote;


public class Hero extends DynamicGameObject{
	
	public static final int HERO_START_X = 3;
	public static final int HERO_START_Y = 3;
	
	public static final int HERO_STATE_JUMP = 0;
	public static final int HERO_STATE_FALL = 1;
	public static final int HERO_STATE_LAND = 2;
	public static final int HERO_STATE_COLLIDED = 3;
	public static final int HERO_STATE_BASIC_ATTACK = 4;
	
	public int basic_attack_type;
	public static final int HERO_BASIC_HALO_ATTACK = 1;
	public static final int HERO_BASIC_ATTACK_2 = 2;
	public static final int HERO_BASIC_ATTACK_3 = 3;
	public static final int HERO_BASIC_ATTACK_SPECIAL_LYRE_ATTACK = 4;
	
	public static final int HERO_LEFT = 1;
	public static final int HERO_NEUTRAL = 0;
	public static final int HERO_RIGHT = -1;
	
	public static final float HERO_MAX_VELY = -15;
	public static final float HERO_JUMP_VELOCITY = 6.5f;  // 6.5f seems suitable
	public static final float HERO_MOVE_VELOCITY = 5;
	
	public static final float HERO_HIT_VERTICAL_VELOCITY = 1.5f;
	public static final float HERO_HIT_HORIZONTAL_VELOCITY = 1;
	
	public static final float HERO_STANDARD_WIDTH = 1.0f;
	public static final float HERO_STANDARD_HEIGHT = 1.0f;
	public static final float HERO_HALO_ATTACK_WIDTH = 1.0f;
	public static final float HERO_HALO_ATTACK_HEIGHT = 1.125f;
	public static final float HERO_LYRE_ATTACK_WIDTH = 0.9625f;
	public static final float HERO_LYRE_ATTACK_HEIGHT = 1.2125f;
	
	public float state_timer;
	public static final float HERO_LAND_TIMER_LIMIT = 0.16f;
	public static final float HERO_COLLIDED_TIMER_LIMIT = 0.8f;
	
	public float basic_attack_timer_limit;
	public static final float HERO_BASIC_HALO_ATTACK_TIMER = Assets.HERO_HALO_ATTACK_1_FRAME_DURATION * 14;
	public static final float HERO_BASIC_LYRE_SPECIAL_ATTACK_TIMER = Assets.HERO_LYRE_ATTACK_FRAME_DURATION * 12;
	
	public static final float HERO_ATTACK_LAUNCH_TIMER = Assets.HERO_HALO_ATTACK_1_FRAME_DURATION * 3;
	
	public int state;
	public int moveDirection;

	public boolean attack_launched;
	
	public static final float HERO_DEFAULT_INVINCIBILITY_TIMER_LIMIT = 1.0f;
	public static final float HERO_LYRE_INVINCIBILITY_TIMER_LIMIT = 4.0f;
	public float invincibility_timer;
	public float invincibility_timer_limit;
	
//	public Vector2 hero_center;

	public Hero() {
		super(HERO_START_X, HERO_START_Y, HERO_STANDARD_WIDTH, HERO_STANDARD_HEIGHT);
		
		state = HERO_STATE_FALL;
		facedirection = HERO_LEFT;
		moveDirection = HERO_NEUTRAL;
		accel.set(0, World.WORLD_GRAVITY);
		
		attack_launched = false;
		invincibility_timer = 0;
		invincibility_timer_limit = HERO_DEFAULT_INVINCIBILITY_TIMER_LIMIT;
		
		state_timer = 0;
		basic_attack_type = HERO_BASIC_HALO_ATTACK;
		basic_attack_timer_limit = HERO_BASIC_HALO_ATTACK_TIMER;
	}
	
	public void update(float deltaTime){				
		
		super.update(deltaTime);
//		hero_center.set(position.x+width/2, position.y+height/2); 
		deathLoop();
		checkSideBounds();

		adjustVectors();
		adjustFaceDirection();
		checkInvincibility(deltaTime);
		state_timer += deltaTime;
		
		switch(state){
		case HERO_STATE_JUMP:
			updateJumpState(deltaTime);
			break;
		case HERO_STATE_FALL:
			updateFallState(deltaTime);
			break;
		case HERO_STATE_LAND:
			updateLandState(deltaTime);
			break;
		case HERO_STATE_COLLIDED:
			updateCollidedState(deltaTime);
			break;
		case HERO_STATE_BASIC_ATTACK:
			updateBasicAttackState(deltaTime);
			break;
		}
	}

	private void deathLoop() {
		if(position.y + height < 0){
			position.y = 9;
			velocity.y = 0;
		}
		
	}

	public void checkSideBounds() {
		if(position.x < 0)
			resetPositionLowerLeft(0, position.y);
		if(position.x > 20.0f - width)
			resetPositionLowerLeft(20.0f - width, position.y);
	}

	private void changeToJumpOrFallState(){
		resetDimensions(HERO_STANDARD_WIDTH, HERO_STANDARD_HEIGHT);
		if(velocity.y < 0)
			changeToFallState();		
		else
			changeToJumpState();
	}
	
	private void changeToFallState() {
		state = HERO_STATE_FALL;
		state_timer = 0;
	}

	private void changeToJumpState() {
		state = HERO_STATE_JUMP;
		state_timer = 0;
	}
	
	public void changeToLandState(){
		state = HERO_STATE_LAND;
		state_timer = 0;
	}

	public void changeToCollidedState() {
		if(is_invincible)
			return;
		
		if(facedirection == HERO_RIGHT)
			velocity.set(-HERO_HIT_HORIZONTAL_VELOCITY, HERO_HIT_VERTICAL_VELOCITY);
		else
			velocity.set(HERO_HIT_HORIZONTAL_VELOCITY, HERO_HIT_VERTICAL_VELOCITY);
		
		state = HERO_STATE_COLLIDED;
		state_timer = 0;
		activateInvincibility();
	}

	private void updateFallState(float deltaTime){
		if(velocity.y >= 0)
			changeToLandState();
	}
	
	private void updateJumpState(float deltaTime){
		if(velocity.y < 0)
			changeToFallState();
	}
	
	private void updateLandState(float deltaTime){
		if(state_timer > HERO_LAND_TIMER_LIMIT)
			changeToJumpOrFallState();
	}
	
	private void updateCollidedState(float deltaTime){
		if(state_timer > HERO_COLLIDED_TIMER_LIMIT)
			changeToJumpOrFallState();
	}

	public void changeToBasicAttackState() {
		state = HERO_STATE_BASIC_ATTACK;
		
		setBasicAttackType();
		
		if(basic_attack_type == HERO_BASIC_ATTACK_SPECIAL_LYRE_ATTACK)
			activateInvincibility(HERO_LYRE_INVINCIBILITY_TIMER_LIMIT);
		
		state_timer = 0;
		attack_launched = false;
		if(velocity.y < 0)
			velocity.y = 2.0f;
	}

	private void setBasicAttackType() {
		Random random = new Random();
		float randomValue = random.nextFloat();
		// HERO_BASIC_ATTACK_1
		if(randomValue >= 0 && randomValue < 0.3f){
			resetDimensions(HERO_HALO_ATTACK_WIDTH, HERO_HALO_ATTACK_HEIGHT);
			basic_attack_type = HERO_BASIC_HALO_ATTACK;
			basic_attack_timer_limit = HERO_BASIC_HALO_ATTACK_TIMER;
		}
		// HERO_BASIC_ATTACK_2
		else if(randomValue >= 0.3f && randomValue < 0.6f){
			resetDimensions(HERO_HALO_ATTACK_WIDTH, HERO_HALO_ATTACK_HEIGHT);
			basic_attack_type = HERO_BASIC_HALO_ATTACK;
			basic_attack_timer_limit = HERO_BASIC_HALO_ATTACK_TIMER;
		}
		// HERO_BASIC_ATTACK_3
		else if(randomValue >= 0.6f && randomValue < 0.9f){
			resetDimensions(HERO_LYRE_ATTACK_WIDTH, HERO_LYRE_ATTACK_HEIGHT);
			basic_attack_type = HERO_BASIC_ATTACK_SPECIAL_LYRE_ATTACK;
			basic_attack_timer_limit = HERO_BASIC_LYRE_SPECIAL_ATTACK_TIMER;
		}
		// HERO_BASIC_ATTACK_SPECIAL
		else{
			resetDimensions(HERO_LYRE_ATTACK_WIDTH, HERO_LYRE_ATTACK_HEIGHT);
			basic_attack_type = HERO_BASIC_ATTACK_SPECIAL_LYRE_ATTACK;
			basic_attack_timer_limit = HERO_BASIC_LYRE_SPECIAL_ATTACK_TIMER;
		}
	}

	private void updateBasicAttackState(float deltaTime){		
		
		switch(basic_attack_type){
		case HERO_BASIC_HALO_ATTACK:
			if(state_timer > HERO_ATTACK_LAUNCH_TIMER && !attack_launched){
				attack_launched = true;
				HaloAttack.activate(this);
			}
			break;
		case HERO_BASIC_ATTACK_2:
			break;
		case HERO_BASIC_ATTACK_3:
			break;
		case HERO_BASIC_ATTACK_SPECIAL_LYRE_ATTACK:
			if(state_timer > HERO_ATTACK_LAUNCH_TIMER && !attack_launched){
				attack_launched = true;
				MusicNote.activateMusicalCircularBurst(this);
			}
			break;
		}
		
		if(state_timer > basic_attack_timer_limit)
			changeToJumpOrFallState();
	}

	public void adjustFaceDirection() {
		if(state == HERO_STATE_COLLIDED ||
			(state == HERO_STATE_BASIC_ATTACK && basic_attack_type == HERO_BASIC_HALO_ATTACK))
			return;
			
		if(velocity.x > 0)
			facedirection = HERO_RIGHT;
		else if(velocity.x < 0)
			facedirection = HERO_LEFT;
		
	}
	
	private void checkInvincibility(float deltaTime) {
		if(is_invincible){
			invincibility_timer += deltaTime;
			if(invincibility_timer > invincibility_timer_limit)
				deactivateInvincibility();
		}		
	}

	private void adjustVectors() {
		if(state == HERO_STATE_JUMP ||
			state == HERO_STATE_FALL ||
			state == HERO_STATE_LAND ||
			state == HERO_STATE_BASIC_ATTACK){
			
			
			switch(moveDirection){
			case HERO_LEFT:
				moveLeft();
				break;
			case HERO_RIGHT:
				moveRight();
				break;
			case HERO_NEUTRAL:
				moveCancel();
				break;
			}
			if(velocity.y < HERO_MAX_VELY)
				velocity.y = HERO_MAX_VELY;
		}
		
		else if(state == HERO_STATE_COLLIDED){
			
		}
	}

	public void reboundPlatform(GameObject platform){
		position.y = platform.position.y + platform.height;
		velocity.y = HERO_JUMP_VELOCITY;
	}
	
	public void activateInvincibility(){
		activateInvincibility(HERO_DEFAULT_INVINCIBILITY_TIMER_LIMIT);		
	}
	
	public void activateInvincibility(float timer_limit){
		invincibility_timer = 0;
		invincibility_timer_limit = timer_limit;
		is_invincible = true;
	}
	
	public void deactivateInvincibility(){
		is_invincible = false;
	}
	
	public void moveLeft(){
		velocity.x = -HERO_MOVE_VELOCITY;
	}
	
	public void moveRight(){
		velocity.x = HERO_MOVE_VELOCITY;
	}

	public void moveCancel(){
		velocity.x = 0;
	}
}

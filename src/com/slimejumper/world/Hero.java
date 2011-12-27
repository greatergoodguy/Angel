package com.slimejumper.world;

import java.util.LinkedList;
import java.util.Random;

import com.slimejumper.Assets;
import com.slimejumper.gameframework.math.UnitCircle;
import com.slimejumper.levels.Level;
import com.slimejumper.tools.PoolManager;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.attacks.MusicNote;
import com.slimejumper.world.attacks.SpiralAttack;
import com.slimejumper.world.environment.Platform;


public class Hero extends DynamicGameObject{

	public static final Hero hero_singleton = new Hero();
	
	/*
	 * Hero Attacks
	 */
	
	public LinkedList<MusicNote> music_notes;
	public LinkedList<SpiralAttack> spiral_attacks;
	public LinkedList<HaloAttack> halo_attacks;
	
	/*
	 * Constants
	 */
	
	public static final int HERO_START_X = 3;
	public static final int HERO_START_Y = 3;
	
	public static enum STATE{
		HERO_STATE_JUMP,
		HERO_STATE_FALL,
		HERO_STATE_LAND,
		HERO_STATE_COLLIDED,
		HERO_STATE_BASIC_ATTACK,
		HERO_STATE_DEATH_BY_FALLING
	}
	public static final int HERO_STATE_JUMP = 0;
	public static final int HERO_STATE_FALL = 1;
	public static final int HERO_STATE_LAND = 2;
	public static final int HERO_STATE_COLLIDED = 3;
	public static final int HERO_STATE_BASIC_ATTACK = 4;
	public static final int HERO_STATE_DEATH_BY_FALLING = 5;
	
	public int basic_attack_type;
	public static final int HERO_BASIC_HALO_ATTACK = 1;
	public static final int HERO_BASIC_SPIRAL_ATTACK = 2;
	public static final int HERO_BASIC_ATTACK_3 = 3;
	public static final int HERO_BASIC_ATTACK_SPECIAL_LYRE_ATTACK = 4;
	
	public static final float HERO_MAX_VELY = -15;
	public static final float HERO_JUMP_VELOCITY = 6.5f;  // 6.5f seems suitable
	public static final float HERO_MOVE_VELOCITY = 5;
	public static final float HERO_HOP_VELOCITY = 2.5f;
	
	public static final float HERO_HIT_VERTICAL_VELOCITY = 1.5f;
	public static final float HERO_HIT_HORIZONTAL_VELOCITY = 1;
	
	public static final float HERO_STANDARD_WIDTH = 1.0f;
	public static final float HERO_STANDARD_HEIGHT = 1.0f;
	public static final float HERO_HALO_ATTACK_WIDTH = 1.0f;
	public static final float HERO_HALO_ATTACK_HEIGHT = 1.125f;
	public static final float HERO_LYRE_ATTACK_WIDTH = 1.0375f;
	public static final float HERO_LYRE_ATTACK_HEIGHT = 1.0625f;
	public static final float HERO_SPIRAL_ATTACK_WIDTH = 1.1375f;
	public static final float HERO_SPIRAL_ATTACK_HEIGHT = 1.0f;
	
	public float state_timer;
	public static final float HERO_LAND_TIMER_LIMIT = 0.16f;
	public static final float HERO_COLLIDED_TIMER_LIMIT = 0.8f;
	
	public float basic_attack_timer_limit;
	public static final float HERO_BASIC_HALO_ATTACK_TIMER = Assets.HERO_HALO_ATTACK_1_FRAME_DURATION * 14;
	public static final float HERO_BASIC_LYRE_SPECIAL_ATTACK_TIMER = Assets.HERO_LYRE_ATTACK_FRAME_DURATION * 12;
	public static final float HERO_BASIC_SPIRAL_ATTACK_TIMER = Assets.HERO_SPIRAL_ATTACK_1_FRAME_DURATION * 5;
	
	public static final float HERO_ATTACK_LAUNCH_TIMER = Assets.HERO_HALO_ATTACK_1_FRAME_DURATION * 3;
	
	public int state;

	public boolean attack_launched;
	
	public static final float HERO_DEFAULT_INVINCIBILITY_TIMER_LIMIT = 1.0f;
	public static final float HERO_LYRE_INVINCIBILITY_TIMER_LIMIT = 4.0f;
	public float invincibility_timer;
	public float invincibility_timer_limit;	

	public Hero() {
		super(HERO_START_X, HERO_START_Y, HERO_STANDARD_WIDTH, HERO_STANDARD_HEIGHT);
		
		halo_attacks = new LinkedList<HaloAttack>();
		music_notes = new LinkedList<MusicNote>();
		spiral_attacks = new LinkedList<SpiralAttack>();
		
		state = HERO_STATE_FALL;
		facedirection = SPRITE_LEFT;
		accel.set(0, Level.WORLD_GRAVITY);
		
		attack_launched = false;
		invincibility_timer = 0;
		invincibility_timer_limit = HERO_DEFAULT_INVINCIBILITY_TIMER_LIMIT;
		
		state_timer = 0;
		basic_attack_type = HERO_BASIC_HALO_ATTACK;
		basic_attack_timer_limit = HERO_BASIC_HALO_ATTACK_TIMER;
	}
	
	public void update(float deltaTime){				
		
		super.update(deltaTime);
		if(velocity.y < HERO_MAX_VELY)
			velocity.y = HERO_MAX_VELY;
		
		updateAttacks(deltaTime);
		removeUnnecessaryAttacks();
				
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

	private void updateAttacks(float deltaTime) {
		for(HaloAttack halo_attack : halo_attacks)
			halo_attack.update(deltaTime);
		for(MusicNote music_note : music_notes)
			music_note.update(this, deltaTime);
		for(SpiralAttack spiral_attack : spiral_attacks)
			spiral_attack.update(deltaTime);		
	}
/*
	private void deathLoop() {
		if(position.y + height < 0){
			position.y = 9;
			velocity.y = 0;
		}
		
	}
*/
	public void checkSideBounds(Level level) {
		if(position.x < 0)
			resetPositionLowerLeft(0, position.y);
		if(position.x > level.world_width - width)
			resetPositionLowerLeft(level.world_width - width, position.y);
	}

	private void changeToJumpOrFallState(){
		float old_x = position.x;
		float old_y = position.y;
		resetDimensions(HERO_STANDARD_WIDTH, HERO_STANDARD_HEIGHT);
		resetPositionLowerLeft(old_x, old_y);
		
		if(velocity.y < 0)
			changeToFallState();		
		else
			changeToJumpState();
	}
	
	private void changeToFallState() {
		state = HERO_STATE_FALL;
		state_timer = 0;
	}

	private void updateFallState(float deltaTime){
		if(velocity.y >= 0)
			changeToLandState();
	}

	private void changeToJumpState() {
		state = HERO_STATE_JUMP;
		state_timer = 0;
	}
	
	private void updateJumpState(float deltaTime){
		if(velocity.y < 0)
			changeToFallState();
	}

	public void changeToLandState(){
		velocity.y = HERO_JUMP_VELOCITY;
		state = HERO_STATE_LAND;
		state_timer = 0;
	}

	private void updateLandState(float deltaTime){
		if(state_timer > HERO_LAND_TIMER_LIMIT)
			changeToJumpOrFallState();
	}

	public void changeToCollidedState() {
		if(is_invincible)
			return;
		
		if(facedirection == SPRITE_RIGHT)
			velocity.set(-HERO_HIT_HORIZONTAL_VELOCITY, HERO_HIT_VERTICAL_VELOCITY);
		else
			velocity.set(HERO_HIT_HORIZONTAL_VELOCITY, HERO_HIT_VERTICAL_VELOCITY);
		
		state = HERO_STATE_COLLIDED;
		state_timer = 0;
		activateInvincibility();
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

	private void updateBasicAttackState(float deltaTime){		
		
		switch(basic_attack_type){
		case HERO_BASIC_HALO_ATTACK:
			if(state_timer > HERO_ATTACK_LAUNCH_TIMER && !attack_launched){
				attack_launched = true;
				activateHaloAttack();
			}
			break;
		case HERO_BASIC_SPIRAL_ATTACK:
			if(state_timer > HERO_ATTACK_LAUNCH_TIMER && !attack_launched){
				attack_launched = true;
				activateSpiralAttack();
			}
			break;
		case HERO_BASIC_ATTACK_3:
			break;
		case HERO_BASIC_ATTACK_SPECIAL_LYRE_ATTACK:
			if(state_timer > HERO_ATTACK_LAUNCH_TIMER && !attack_launched){
				attack_launched = true;
				activateMusicalBurst();
			}
			break;
		}
		
		if(state_timer > basic_attack_timer_limit)
			changeToJumpOrFallState();
	}

	private void setBasicAttackType() {
		Random random = new Random();
		float randomValue = random.nextFloat();
		// HERO_BASIC_ATTACK_1
		if(randomValue >= 0 && randomValue < 0.3){
			resetDimensions(HERO_HALO_ATTACK_WIDTH, HERO_HALO_ATTACK_HEIGHT);
			basic_attack_type = HERO_BASIC_HALO_ATTACK;
			basic_attack_timer_limit = HERO_BASIC_HALO_ATTACK_TIMER;
		}
		// HERO_BASIC_ATTACK_2
		else if(randomValue >= 0.3f && randomValue < 0.6f){
			resetDimensions(HERO_SPIRAL_ATTACK_WIDTH, HERO_SPIRAL_ATTACK_HEIGHT);
			basic_attack_type = HERO_BASIC_SPIRAL_ATTACK;
			basic_attack_timer_limit = HERO_BASIC_SPIRAL_ATTACK_TIMER;
		}
		// HERO_BASIC_ATTACK_3
		else if(randomValue >= 0.6f && randomValue < 0.85f){
			resetDimensions(HERO_HALO_ATTACK_WIDTH, HERO_HALO_ATTACK_HEIGHT);
			basic_attack_type = HERO_BASIC_HALO_ATTACK;
			basic_attack_timer_limit = HERO_BASIC_HALO_ATTACK_TIMER;
		}
		// HERO_BASIC_ATTACK_SPECIAL
		else{
			resetDimensions(HERO_LYRE_ATTACK_WIDTH, HERO_LYRE_ATTACK_HEIGHT);
			basic_attack_type = HERO_BASIC_ATTACK_SPECIAL_LYRE_ATTACK;
			basic_attack_timer_limit = HERO_BASIC_LYRE_SPECIAL_ATTACK_TIMER;
		}
	}

	public void adjustFaceDirection() {
		if(state == HERO_STATE_COLLIDED ||
			(state == HERO_STATE_BASIC_ATTACK && basic_attack_type == HERO_BASIC_HALO_ATTACK))
			return;
			
		if(velocity.x > 0)
			facedirection = SPRITE_RIGHT;
		else if(velocity.x < 0)
			facedirection = SPRITE_LEFT;
		
	}
	
	private void checkInvincibility(float deltaTime) {
		if(is_invincible){
			invincibility_timer += deltaTime;
			if(invincibility_timer > invincibility_timer_limit)
				deactivateInvincibility();
		}		
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
	
	public void hop() {
		velocity.y = HERO_HOP_VELOCITY;
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
	
	public void reboundPlatform(Platform platform){
		position.y = platform.position.y + platform.height;
		if(state == Hero.HERO_STATE_BASIC_ATTACK ||
			state == Hero.HERO_STATE_COLLIDED)
			velocity.y = 3 * HERO_JUMP_VELOCITY / 4;
		else
			changeToLandState();
	}

	public void activateHaloAttack(){
		HaloAttack halo_attack = PoolManager.pool_manager_singleton.halo_attack_pool.newObject();
		halo_attack.reset(this);
		halo_attacks.add(halo_attack);
	}
	
	public void activateSpiralAttack(){
		SpiralAttack spiral_attack = PoolManager.pool_manager_singleton.spiral_attack_pool.newObject();
		spiral_attack.reset(this);
		hop();
		spiral_attacks.add(spiral_attack);
	}
	
	public void activateMusicalBurst(){
		for(int frame_counter_starter=0; frame_counter_starter<UnitCircle.UNIT_CIRCLE_SIZE; frame_counter_starter+=3){
			MusicNote music_note = PoolManager.pool_manager_singleton.music_note_pool.newObject();
			music_note.reset(frame_counter_starter);
			
			music_notes.add(music_note);
		}
	}

	public void removeUnnecessaryAttacks() {
		if(!halo_attacks.isEmpty()){
			HaloAttack halo_attack = halo_attacks.getFirst();
			if(halo_attack.life_timer > HaloAttack.HaloAttack_LIFESPAN){
				halo_attacks.removeFirst();
				PoolManager.pool_manager_singleton.halo_attack_pool.free(halo_attack);
			}
		}
		
		if(!music_notes.isEmpty()){
			MusicNote music_note = music_notes.getFirst();
			if(music_note.life_timer > MusicNote.MUSIC_NOTE_LIFESPAN){
				music_notes.removeFirst();
				PoolManager.pool_manager_singleton.music_note_pool.free(music_note);
			}
		}
		
		if(!spiral_attacks.isEmpty()){
			SpiralAttack spiral_attack = spiral_attacks.getFirst();
			if(spiral_attack.life_timer > SpiralAttack.SpiralAttack_LIFESPAN){
				spiral_attacks.removeFirst();
				PoolManager.pool_manager_singleton.spiral_attack_pool.free(spiral_attack);
			}
		}
	}
}

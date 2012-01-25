package com.slimejumper.world;

import java.util.LinkedList;
import java.util.Random;

import com.slimejumper.Assets;
import com.slimejumper.gameframework.math.UnitCircle;
import com.slimejumper.levels.Level;
import com.slimejumper.tools.PoolManager;
import com.slimejumper.world.attacks.AngelicFlame;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.attacks.MusicNote;
import com.slimejumper.world.attacks.SpiralAttack;
import com.slimejumper.world.environment.Platform;


public class Hero extends DynamicGameObject{

	public static final Hero hero_singleton = new Hero();
	final PoolManager pool_manager = PoolManager.pool_manager_singleton;
	
	/*
	 * Hero Attacks
	 */
	
	public LinkedList<MusicNote> music_notes;
	public LinkedList<SpiralAttack> spiral_attacks;
	public LinkedList<HaloAttack> halo_attacks;
	public LinkedList<AngelicFlame> angelic_flames;
	
	/*
	 * Constants
	 */
	
	public static final int HERO_START_X = 3;
	public static final int HERO_START_Y = 3;
	
	public static enum STATE{
		HERO_STATE_JUMP,
		HERO_STATE_FALL,
		HERO_STATE_COLLIDED,
		HERO_STATE_BASIC_ATTACK,
		HERO_STATE_DEATH_BY_FALLING
	}
	public static final int HERO_STATE_JUMP = 0;
	public static final int HERO_STATE_FALL = 1;
	public static final int HERO_STATE_COLLIDED = 3;
	public static final int HERO_STATE_BASIC_ATTACK = 4;
	public static final int HERO_STATE_DEATH_BY_FALLING = 5;
	
	public int basic_attack_type;
	public static final int HERO_BASIC_HALO_ATTACK = 1;
	public static final int HERO_BASIC_SPIRAL_ATTACK = 2;
	public static final int HERO_BASIC_ATTACK_ANGELIC_FLAME = 3;
	public static final int HERO_BASIC_ATTACK_SPECIAL_LYRE_ATTACK = 4;
	
	public static final float HERO_MAX_VELY = -15;
	public static final float HERO_JUMP_VELOCITY = 6.5f;  // 6.5f seems suitable, Trying 6.0
	public static final float HERO_MOVE_VELOCITY = 5;
	public static final float HERO_HOP_VELOCITY = 1.5f;
	
	public static final float HERO_HIT_VERTICAL_VELOCITY = 1.5f;
	public static final float HERO_HIT_HORIZONTAL_VELOCITY =1.8f;
	
	public static final float HERO_STANDARD_WIDTH = 1.0f;
	public static final float HERO_STANDARD_HEIGHT = 1.0f;
	public static final float HERO_HALO_ATTACK_WIDTH = 1.0f;
	public static final float HERO_HALO_ATTACK_HEIGHT = 1.125f;
	public static final float HERO_LYRE_ATTACK_WIDTH = 1.0375f;
	public static final float HERO_LYRE_ATTACK_HEIGHT = 1.0625f;
	public static final float HERO_SPIRAL_ATTACK_WIDTH = 1.1375f;
	public static final float HERO_SPIRAL_ATTACK_HEIGHT = 1.0f;
	public static final float HERO_COLLIDED_WIDTH = 0.8875f;
	public static final float HERO_COLLIDED_HEIGHT = 1.125f;
	
	
	public float state_timer;
	public static final float HERO_LAND_TIMER_LIMIT = 0.16f;
	public static final float HERO_COLLIDED_TIMER_LIMIT = 0.8f;
	
	public float basic_attack_timer_limit;
	public static final float HERO_BASIC_HALO_ATTACK_TIMER = Assets.HERO_HALO_ATTACK_1_FRAME_DURATION * 11;
	public static final float HERO_BASIC_LYRE_SPECIAL_ATTACK_TIMER = Assets.HERO_LYRE_ATTACK_FRAME_DURATION * 12;
	public static final float HERO_BASIC_SPIRAL_ATTACK_TIMER = Assets.HERO_SPIRAL_ATTACK_1_FRAME_DURATION * 5;
	
	public static final float HERO_ATTACK_LAUNCH_TIMER = Assets.HERO_HALO_ATTACK_1_FRAME_DURATION * 3;
	public static final float HERO_DEATH_BY_FALLING_TIMER = 3.3f;
	
	public int state;

	public boolean attack_launched;
	
	public static final float HERO_DEFAULT_INVINCIBILITY_TIMER_LIMIT = 1.8f;
	public float invincibility_timer;
	public float invincibility_timer_limit;	
	
	public int health;

	public Hero() {
		super(HERO_START_X, HERO_START_Y, HERO_STANDARD_WIDTH, HERO_STANDARD_HEIGHT);
		
		halo_attacks = new LinkedList<HaloAttack>();
		music_notes = new LinkedList<MusicNote>();
		spiral_attacks = new LinkedList<SpiralAttack>();
		angelic_flames = new LinkedList<AngelicFlame>();
		
		state = HERO_STATE_FALL;
		facedirection = SPRITE_RIGHT;
		accel.set(0, Level.WORLD_GRAVITY);
		
		attack_launched = false;
		invincibility_timer = 0;
		invincibility_timer_limit = HERO_DEFAULT_INVINCIBILITY_TIMER_LIMIT;
		
		state_timer = 0;
		basic_attack_type = HERO_BASIC_HALO_ATTACK;
		basic_attack_timer_limit = HERO_BASIC_HALO_ATTACK_TIMER;
		
		health = 5;
	}
	
	public void reset(float x_coord, float y_coord){
		position.set(x_coord, y_coord);
		facedirection = SPRITE_RIGHT;
		velocity.y = 0;
		accel.set(0, Level.WORLD_GRAVITY);
		changeToJumpOrFallState();
		health = 5;
		
	}
	
	public void update(float deltaTime){				
		
		super.update(deltaTime);
		if(velocity.y < HERO_MAX_VELY)
			velocity.y = HERO_MAX_VELY;
		
		updateAttacks(deltaTime);
				
		adjustFaceDirection();
		checkInvincibility(deltaTime);
		
		state_timer += deltaTime;		
		switch(state){
		case HERO_STATE_JUMP:
			updateJumpState(deltaTime);
			break;
		case HERO_STATE_FALL:
			//updateFallState(deltaTime);
			break;
		case HERO_STATE_COLLIDED:
			updateCollidedState(deltaTime);
			break;
		case HERO_STATE_BASIC_ATTACK:
			updateBasicAttackState(deltaTime);
			break;
		case HERO_STATE_DEATH_BY_FALLING:
			updateDeathByFallingState(deltaTime);
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
		for(AngelicFlame angelic_flame : angelic_flames)
			angelic_flame.update(deltaTime);
				
		if(!halo_attacks.isEmpty()){
			HaloAttack halo_attack = halo_attacks.getFirst();
			if(halo_attack.life_timer > HaloAttack.HaloAttack_LIFESPAN){
				halo_attacks.removeFirst();
				pool_manager.halo_attack_pool.free(halo_attack);
			}
		}

		if(!music_notes.isEmpty()){
			MusicNote music_note = music_notes.getFirst();
			if(music_note.life_timer > MusicNote.MUSIC_NOTE_LIFESPAN){
				music_notes.removeFirst();
				pool_manager.music_note_pool.free(music_note);
			}
		}

		if(!spiral_attacks.isEmpty()){
			SpiralAttack spiral_attack = spiral_attacks.getFirst();
			if(spiral_attack.life_timer > SpiralAttack.SpiralAttack_LIFESPAN){
				spiral_attacks.removeFirst();
				pool_manager.spiral_attack_pool.free(spiral_attack);
			}
		}
		
		if(!angelic_flames.isEmpty()){
			
		}
	}

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
		accel.set(0, Level.WORLD_GRAVITY);
		
		if(velocity.y < 0)
			changeToFallState();		
		else
			changeToJumpState(velocity.y);
	}

	private void changeToFallState() {
		float old_x = position.x;
		float old_y = position.y;
		resetDimensions(HERO_STANDARD_WIDTH, HERO_STANDARD_HEIGHT);
		resetPositionLowerLeft(old_x, old_y);
		accel.set(0, Level.WORLD_GRAVITY);
		
		state = HERO_STATE_FALL;
		state_timer = 0;
	}

	
	private void updateFallState(float deltaTime){
	}

	public void changeToJumpState() {
		velocity.y = HERO_JUMP_VELOCITY;
		state = HERO_STATE_JUMP;
		state_timer = 0;

		accel.set(0, Level.WORLD_GRAVITY);
	}
	
	private void changeToJumpState(float new_velocity_y) {
		velocity.y = new_velocity_y;
		state = HERO_STATE_JUMP;
		state_timer = 0;
		
	}
	
	private void updateJumpState(float deltaTime){
		if(velocity.y < 0)
			changeToFallState();
	}

	public void changeToCollidedStateFromRight() {
		if(is_invincible)
			return;
		
		facedirection = SPRITE_RIGHT;
		changeToCollidedState();
	}

	public void changeToCollidedStateFromLeft() {
		if(is_invincible)
			return;
		
		facedirection = SPRITE_LEFT;
		changeToCollidedState();
	}
	
	public void changeToCollidedState() {
		if(is_invincible)
			return;
		
		resetDimensions(HERO_COLLIDED_WIDTH, HERO_COLLIDED_HEIGHT);
		
		if(facedirection == SPRITE_RIGHT)
			velocity.set(-HERO_HIT_HORIZONTAL_VELOCITY, HERO_HIT_VERTICAL_VELOCITY);
		else
			velocity.set(HERO_HIT_HORIZONTAL_VELOCITY, HERO_HIT_VERTICAL_VELOCITY);
		
		state = HERO_STATE_COLLIDED;
		state_timer = 0;
		activateInvincibility();
	}

	private void updateCollidedState(float deltaTime){
		if(state_timer > HERO_COLLIDED_TIMER_LIMIT){
			health--;
			changeToFallState();
		}
	}
	
	public void changeToBasicAttackStateLeft() {
		changeToBasicAttackState();
		facedirection = SPRITE_LEFT;
	}
	
	public void changeToBasicAttackStateRight() {
		changeToBasicAttackState();
		facedirection = SPRITE_RIGHT;
	}

	public void changeToBasicAttackState() {
		state = HERO_STATE_BASIC_ATTACK;
		
		setBasicAttackType();
		
		if(basic_attack_type == HERO_BASIC_ATTACK_SPECIAL_LYRE_ATTACK)
			activateInvincibility(HERO_BASIC_LYRE_SPECIAL_ATTACK_TIMER);
		
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
		case HERO_BASIC_ATTACK_ANGELIC_FLAME:
			if(state_timer > HERO_ATTACK_LAUNCH_TIMER && !attack_launched){
				attack_launched = true;
				activateAngelicFlame();
			}
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
		if(randomValue >= 0 && randomValue < 0.33){
			resetDimensions(HERO_HALO_ATTACK_WIDTH, HERO_HALO_ATTACK_HEIGHT);
			basic_attack_type = HERO_BASIC_HALO_ATTACK;
			basic_attack_timer_limit = HERO_BASIC_HALO_ATTACK_TIMER;
		}
		
		// HERO_BASIC_ATTACK_2
		else if(randomValue >= 0.33f && randomValue < 0.66f){
			resetDimensions(HERO_SPIRAL_ATTACK_WIDTH, HERO_SPIRAL_ATTACK_HEIGHT);
			basic_attack_type = HERO_BASIC_SPIRAL_ATTACK;
			basic_attack_timer_limit = HERO_BASIC_SPIRAL_ATTACK_TIMER;
		}
		
		// HERO_BASIC_ATTACK_3
		else if(randomValue >= 0.66f && randomValue < 1.00f){
			resetDimensions(HERO_SPIRAL_ATTACK_WIDTH, HERO_SPIRAL_ATTACK_HEIGHT);
			basic_attack_type = HERO_BASIC_ATTACK_ANGELIC_FLAME;
			basic_attack_timer_limit = HERO_BASIC_SPIRAL_ATTACK_TIMER;
		}
		
		// HERO_BASIC_ATTACK_SPECIAL
		else{
			resetDimensions(HERO_LYRE_ATTACK_WIDTH, HERO_LYRE_ATTACK_HEIGHT);
			basic_attack_type = HERO_BASIC_ATTACK_SPECIAL_LYRE_ATTACK;
			basic_attack_timer_limit = HERO_BASIC_LYRE_SPECIAL_ATTACK_TIMER;
		}
	}
	
	public void changeToDeathByFallingState(){
		/*
		 * Remove Gravity 
		 */
		accel.set(0, 0);
		velocity.set(0, 0.8f);
		
		resetDimensions(HERO_STANDARD_WIDTH, HERO_STANDARD_HEIGHT);
		state = HERO_STATE_DEATH_BY_FALLING;
		state_timer = 0;
		health--;
		activateInvincibility(HERO_DEATH_BY_FALLING_TIMER + 0.1f);
	}
	
	public void updateDeathByFallingState(float deltaTime){
		if(state_timer > HERO_DEATH_BY_FALLING_TIMER){
			accel.set(0, Level.WORLD_GRAVITY);
			changeToJumpOrFallState();			
		}
	}

	public void adjustFaceDirection() {
		if(state == HERO_STATE_COLLIDED ||
			state == HERO_STATE_BASIC_ATTACK)
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
	
	public void moveByAccel(float controller_accel) {
		velocity.x = controller_accel / 10 * HERO_MOVE_VELOCITY * 3;
		
		if(velocity.x > 6.0f)
			velocity.x = 6.0f;
		if(velocity.x < -6.0f)
			velocity.x = -6.0f;
		
	}
	
	public void reboundPlatform(Platform platform){
		position.y = platform.position.y + platform.height;
		if(state == Hero.HERO_STATE_COLLIDED)
			velocity.y = 3 * HERO_JUMP_VELOCITY / 4;
		else if(state == Hero.HERO_STATE_BASIC_ATTACK)
			velocity.y = HERO_JUMP_VELOCITY;
		else
			changeToJumpState();
	}

	public void activateHaloAttack(){
		HaloAttack halo_attack = pool_manager.halo_attack_pool.newObject();
		halo_attack.reset(this);
		hop();
		halo_attacks.add(halo_attack);
	}
	
	public void activateAngelicFlame(){
		AngelicFlame angelic_flame = pool_manager.angelic_flame_pool.newObject();
		angelic_flame.reset(this);
		hop();
		angelic_flames.add(angelic_flame);
	}
	
	public void activateSpiralAttack(){
		SpiralAttack spiral_attack = pool_manager.spiral_attack_pool.newObject();
		spiral_attack.reset(this);
		hop();
		spiral_attacks.add(spiral_attack);
	}
	
	public void activateMusicalBurst(){
		for(int frame_counter_starter=0; frame_counter_starter<UnitCircle.UNIT_CIRCLE_SIZE; frame_counter_starter+=3){
			MusicNote music_note = pool_manager.music_note_pool.newObject();
			music_note.reset(frame_counter_starter);
			
			music_notes.add(music_note);
		}
	}

	public void dispose() {
		while(!halo_attacks.isEmpty()){
			HaloAttack halo_attack = halo_attacks.removeFirst();
			pool_manager.halo_attack_pool.free(halo_attack);
		}
		
		while(!music_notes.isEmpty()){
			MusicNote music_note = music_notes.removeFirst();
			pool_manager.music_note_pool.free(music_note);
		}
		
		while(!spiral_attacks.isEmpty()){
			SpiralAttack spiral_attack = spiral_attacks.getFirst();
			pool_manager.spiral_attack_pool.free(spiral_attack);
		}	
	}
}

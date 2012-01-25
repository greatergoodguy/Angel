package com.slimejumper.world.enemies;

import java.util.LinkedList;

import com.slimejumper.Assets;
import com.slimejumper.tools.PoolManager;
import com.slimejumper.world.attacks.PurpleFlame;


public class PurpleGhost extends Enemy{
	public static final float PurpleGhost_STANDARD_STATE_WIDTH = 1.1125f;
	public static final float PurpleGhost_STANDARD_STATE_HEIGHT = 1.125f;
	public static final float PurpleGhost_ATTACK_STATE_WIDTH = 1.45f;
	public static final float PurpleGhost_ATTACK_STATE_HEIGHT = 1.1875f;
	public static final float PurpleGhost_DANCE_STATE_WIDTH = 1.1f;
	public static final float PurpleGhost_DANCE_STATE_HEIGHT = 1.05f;
	public static final float PurpleGhost_COLLISION_STATE_WIDTH = 1.075f;
	public static final float PurpleGhost_COLLISION_STATE_HEIGHT = 1.25f;
	
	
	public static final int PurpleGhost_HEALTH = 2;
		
	public static final float PurpleGhost_LIFESPAN = 14.0f;
	
	public static final float PurpleGhost_VERTICAL_VEL = 1.2f;
	public static final float PurpleGhost_WALK_VEL = 1.2f;
	
	public static final float PURPLE_GHOST_ATTACK_TIMER_LIMIT = Assets.PURPLE_GHOST_ATTACK_FRAME_DURATION * 10;
	public static final float PURPLE_FLAME_ATTACK_LAUNCH_TIMER = Assets.PURPLE_GHOST_ATTACK_FRAME_DURATION * 5;
	
	public STATE state;
	public float state_timer;
	public float collided_timer;
	
	public boolean is_dancing;
	public float dance_timer;
	public boolean dance_type_toggler;
	
	public float future_vel;
	private boolean attack_launched;
	
	public STATE next_state;
	
	public static enum STATE{
		STATE_STANDARD,
		STATE_SHOOT_THEN_STANDARD,
		STATE_FLOAT_VERTICAL,
		STATE_SHOOT_THEN_FLOAT_VERTICAL,
		STATE_WALK,
		STATE_COLLIDED
	}
	public static LinkedList<PurpleFlame> purple_flames;
	
	public PurpleGhost(){
		super(0, 0, PurpleGhost_STANDARD_STATE_WIDTH, PurpleGhost_STANDARD_STATE_HEIGHT);
	}
	
	public PurpleGhost(float x, float y){
		super(x, y, PurpleGhost_STANDARD_STATE_WIDTH, PurpleGhost_STANDARD_STATE_HEIGHT);
	}
	
	public static void registerPurpleFlames(LinkedList<PurpleFlame> registered_purple_flames){
		purple_flames = registered_purple_flames;
	}
	
	public static void deregisterPurpleFlames(){
		purple_flames = null;
	}
	
	public void reset(float x_coord, float y_coord){
		super.reset();
		resetPositionLowerLeft(x_coord, y_coord);
		state_timer = 0;
		health_bar = PurpleGhost_HEALTH;
		facedirection = SPRITE_LEFT;
	}
	
	public void update(float deltaTime){
		super.update(deltaTime);
		
		state_timer += deltaTime;
		
		switch(state){
		case STATE_STANDARD:
			updateStandardState();
			break;
		case STATE_SHOOT_THEN_STANDARD:
			updateShootThenStandardState();
			break;
		case STATE_FLOAT_VERTICAL:
			updateFloatVerticalState();
			break;
		case STATE_SHOOT_THEN_FLOAT_VERTICAL:
			updateShootThenVerticalState();
			break;
		case STATE_WALK:
			updateWalkState(deltaTime);
			break;
		case STATE_COLLIDED:
			updateCollidedState(deltaTime);
			break;
		}
	}

	public void changeToStandardStateRight(){		
		facedirection = SPRITE_RIGHT;
		changeToStandardState();
	}
	
	public void changeToStandardStateRight(float state_timer_offset) {
		resetDimensions(PurpleGhost_STANDARD_STATE_WIDTH, PurpleGhost_STANDARD_STATE_HEIGHT);
		state_timer = state_timer_offset;
		
		facedirection = SPRITE_RIGHT;
		state = STATE.STATE_STANDARD;
		
		
	}
	
	public void changeToStandardStateLeft(){		
		facedirection = SPRITE_LEFT;
		changeToStandardState();
	}
	
	public void changeToStandardStateLeft(float state_timer_offset) {
		resetDimensions(PurpleGhost_STANDARD_STATE_WIDTH, PurpleGhost_STANDARD_STATE_HEIGHT);
		state_timer = state_timer_offset;
		
		facedirection = SPRITE_LEFT;
		state = STATE.STATE_STANDARD;
		
	}
	
	public void changeToStandardState(){
		resetDimensions(PurpleGhost_STANDARD_STATE_WIDTH, PurpleGhost_STANDARD_STATE_HEIGHT);
		state_timer = 0;
		state = STATE.STATE_STANDARD;
	}
	
	private void updateStandardState() {
		if(state_timer >= 2.3f){
			changeToShootThenStandardState();
		}
	}

	public void changeToShootThenStandardState(){
		resetDimensions(PurpleGhost_ATTACK_STATE_WIDTH, PurpleGhost_ATTACK_STATE_HEIGHT);
		state_timer = 0;
		state = STATE.STATE_SHOOT_THEN_STANDARD;
		attack_launched = false;
	}
	
	private void updateShootThenStandardState() {
		if(state_timer > PURPLE_FLAME_ATTACK_LAUNCH_TIMER && !attack_launched){
			activatePurpleFlame();
			attack_launched  = true;
		}
		
		if(state_timer > PURPLE_GHOST_ATTACK_TIMER_LIMIT)
			changeToStandardState();
	}
	
	public void changeToFloatVerticalState(){
		resetDimensions(PurpleGhost_STANDARD_STATE_WIDTH, PurpleGhost_STANDARD_STATE_HEIGHT);
		state_timer = 0;
		velocity.y = PurpleGhost_VERTICAL_VEL;
		state = STATE.STATE_FLOAT_VERTICAL;
	}
	
	public void changeToFloatVerticalState(float vertical_vel){
		resetDimensions(PurpleGhost_STANDARD_STATE_WIDTH, PurpleGhost_STANDARD_STATE_HEIGHT);
		state_timer = 0;
		velocity.y = vertical_vel;
		state = STATE.STATE_FLOAT_VERTICAL;
	}
	
	private void updateFloatVerticalState() {
		if(state_timer >= 2.5f){
			changeToShootThenVerticalState(-velocity.y);
		}	
	}

	public void changeToShootThenVerticalState(float future_vertical_vel){
		resetDimensions(PurpleGhost_ATTACK_STATE_WIDTH, PurpleGhost_ATTACK_STATE_HEIGHT);
		state_timer = 0;
		state = STATE.STATE_SHOOT_THEN_FLOAT_VERTICAL;
		
		future_vel = future_vertical_vel;
		velocity.x = 0;
		velocity.y = 0;
		
		attack_launched = false;
	}
	
	private void updateShootThenVerticalState(){
		if(state_timer > PURPLE_FLAME_ATTACK_LAUNCH_TIMER && !attack_launched){
			activatePurpleFlame();
			attack_launched  = true;
		}
		
		if(state_timer > PURPLE_GHOST_ATTACK_TIMER_LIMIT)
			changeToFloatVerticalState(future_vel);
	}
	
	public void changeToWalkStateLeft(){
		resetDimensions(PurpleGhost_STANDARD_STATE_WIDTH, PurpleGhost_STANDARD_STATE_HEIGHT);
		facedirection = SPRITE_LEFT;
		velocity.x = -PurpleGhost_WALK_VEL;
		
		state = STATE.STATE_WALK;
		state_timer = 0;
		
		is_dancing = false;
		dance_timer = 0;
	}
	
	public void changeToWalkStateRight(){
		resetDimensions(PurpleGhost_STANDARD_STATE_WIDTH, PurpleGhost_STANDARD_STATE_HEIGHT);
		facedirection = SPRITE_RIGHT;
		velocity.x = PurpleGhost_WALK_VEL;
		
		state = STATE.STATE_WALK;
		state_timer = 0;
		
		is_dancing = false;
		dance_timer = 0;
	}
	
	private void changeToWalkState(float state_timer_offset){
		resetDimensions(PurpleGhost_STANDARD_STATE_WIDTH, PurpleGhost_STANDARD_STATE_HEIGHT);
		state = STATE.STATE_WALK;
		state_timer = state_timer_offset;
		
		velocity.x = -PurpleGhost_WALK_VEL * facedirection;
	}
	
	private void changeToWalkState(){
		resetDimensions(PurpleGhost_STANDARD_STATE_WIDTH, PurpleGhost_STANDARD_STATE_HEIGHT);
		state = STATE.STATE_WALK;
		state_timer = 0;
		
		facedirection = -facedirection;
		velocity.x = -velocity.x;
	}
	
	public void updateWalkState(float deltaTime){
		dance_timer += deltaTime;
		
		if(dance_timer > 2.4f){
			if(is_dancing){
				resetDimensions(PurpleGhost_STANDARD_STATE_WIDTH, PurpleGhost_STANDARD_STATE_HEIGHT);
			}
			else{
				resetDimensions(PurpleGhost_DANCE_STATE_WIDTH, PurpleGhost_DANCE_STATE_HEIGHT);
				dance_type_toggler = !dance_type_toggler;
			}
			
			dance_timer = 0;
			is_dancing = !is_dancing;
		}
		
		if(state_timer >= 3.7f){
			changeToWalkState();
		}	
	}
	
	STATE future_state;
	public void changeToCollidedState(){
		future_state = state;
		
		resetDimensions(PurpleGhost_COLLISION_STATE_WIDTH, PurpleGhost_COLLISION_STATE_HEIGHT);
		state = STATE.STATE_COLLIDED;
		
		collided_timer = 0;
		velocity.x = 0;
		velocity.y = 0;
	}
	
	public void updateCollidedState(float deltaTime){
		state_timer = state_timer - deltaTime;
		collided_timer = collided_timer + deltaTime;
		
		if(collided_timer > 1.0f){
			switch(future_state){
			case STATE_STANDARD:
				break;
			case STATE_SHOOT_THEN_STANDARD:
				break;
			case STATE_FLOAT_VERTICAL:
				break;
			case STATE_SHOOT_THEN_FLOAT_VERTICAL:
				break;
			case STATE_WALK:
				changeToWalkState(state_timer);
				break;
			}
		}
	}
	
	public void activatePurpleFlame(){			
		PurpleFlame purple_flame = PoolManager.pool_manager_singleton.purple_flame_pool.newObject();
		purple_flame.reset(this);
		purple_flames.add(purple_flame);
	}
}

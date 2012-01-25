package com.slimejumper.world.enemies;



public class RedWhaleDemon extends Enemy{

	public static final float RedWhaleDemon_FLOAT_WIDTH = 1.9375f;
	public static final float RedWhaleDemon_FLOAT_HEIGHT = 1.25f;
	public static final float RedWhaleDemon_CHARGE_WIDTH = 1.975f;
	public static final float RedWhaleDemon_CHARGE_HEIGHT = 1.3125f;
	public static final float RedWhaleDemon_COLLISION_WIDTH = 1.825f;
	public static final float RedWhaleDemon_COLLISION_HEIGHT = 1.4625f;
	
	
	public static final int RedWhaleDemon_HEALTH = 2;
	
	public static final float RedWhaleDemon_VERTICAL_VEL = 0.8f;
	public static final float RedWhaleDemon_TACKLE_VEL = 5.0f;
	public static final float RedWhaleDemon_TACKLE_ACCEL = 2.0f;
	
	public STATE state;
	public float state_timer;
	public float collided_timer;
	
	public enum STATE{
		STATE_FLOAT_VERTICAL,
		STATE_TACKLE,
		STATE_PAUSE,
		STATE_COLLIDED
	}
	
	public RedWhaleDemon() {
		super(0, 0, RedWhaleDemon_CHARGE_WIDTH, RedWhaleDemon_CHARGE_HEIGHT);
	}
	
	public RedWhaleDemon(float x, float y){
		super(x, y, RedWhaleDemon_CHARGE_WIDTH, RedWhaleDemon_CHARGE_HEIGHT);
		state_timer = 0;
		velocity.y = RedWhaleDemon_VERTICAL_VEL;		
	}
	
	public void reset(float x_coord, float y_coord) {
		super.reset();
		resetPositionLowerLeft(x_coord, y_coord);

		state_timer = 0;
		health_bar = RedWhaleDemon_HEALTH;
	}
	
	public void update(float deltaTime){
		super.update(deltaTime);
		
		state_timer += deltaTime;
		switch(state){
		case STATE_FLOAT_VERTICAL:
			updateFloatVerticalState(deltaTime);
			break;
		case STATE_TACKLE:
			updateTackleState(deltaTime);
			break;
		case STATE_PAUSE:
			updatePauseState(deltaTime);
			break;
		case STATE_COLLIDED:
			updateCollidedState(deltaTime);
			break;	
		}
	}
	
	public void changeToStillStateRight(){
		facedirection = SPRITE_RIGHT;
		changeToStillState();
	}
	
	public void changeToStillStateLeft(){
		facedirection = SPRITE_LEFT;
		changeToStillState();
	}
	
	public void changeToStillState(){
		resetDimensions(RedWhaleDemon_FLOAT_WIDTH, RedWhaleDemon_FLOAT_HEIGHT);
		state = STATE.STATE_FLOAT_VERTICAL;
		state_timer = 0;
		velocity.x = 0;
		velocity.y = 0;
	}
	
	public void changeToFloatState(){
		resetDimensions(RedWhaleDemon_FLOAT_WIDTH, RedWhaleDemon_FLOAT_HEIGHT);
		state = STATE.STATE_FLOAT_VERTICAL;
		velocity.x = 0;
		velocity.y = RedWhaleDemon_VERTICAL_VEL;
		facedirection = SPRITE_LEFT;
	}
	
	
	/*
	 * Use when returning from collided state
	 */
	public void changeToFloatState(float state_timer_offset) {
		resetDimensions(RedWhaleDemon_FLOAT_WIDTH, RedWhaleDemon_FLOAT_HEIGHT);
		state = STATE.STATE_FLOAT_VERTICAL;
		velocity.x = 0;
		velocity.y = old_vel;
		facedirection = SPRITE_LEFT;
		state_timer = state_timer_offset;
	}
	

	public void changeToFloatStateLarge(float state_timer_offset) {
		resetDimensions(RedWhaleDemon_FLOAT_WIDTH, RedWhaleDemon_FLOAT_HEIGHT);
		state = STATE.STATE_FLOAT_VERTICAL;
		velocity.x = 0;
		velocity.y = 2 * RedWhaleDemon_VERTICAL_VEL;
		facedirection = SPRITE_LEFT;
		state_timer = state_timer_offset;
	}

	private void updateFloatVerticalState(float deltaTime) {		
		if(state_timer > 3.2f){
			velocity.y = -velocity.y;
			state_timer = 0;
		}		
	}
	
	public void changeToTackleStateLeft(){
		resetDimensions(RedWhaleDemon_CHARGE_WIDTH, RedWhaleDemon_CHARGE_HEIGHT);
		facedirection = SPRITE_LEFT;
		velocity.x = -RedWhaleDemon_TACKLE_VEL;
		accel.x = RedWhaleDemon_TACKLE_ACCEL;
		
		state = STATE.STATE_TACKLE;
		state_timer = 0;
	}
	
	public void changeToTackleStateRight(){
		resetDimensions(RedWhaleDemon_CHARGE_WIDTH, RedWhaleDemon_CHARGE_HEIGHT);
		facedirection = SPRITE_RIGHT;
		velocity.x = RedWhaleDemon_TACKLE_VEL;
		accel.x = -RedWhaleDemon_TACKLE_ACCEL;
		
		state = STATE.STATE_TACKLE;
		state_timer = 0;
	}
	/*
	public void changeToTackleStateRightFAST(){
		resetDimensions(RedWhaleDemon_CHARGE_WIDTH, RedWhaleDemon_CHARGE_HEIGHT);
		facedirection = SPRITE_RIGHT;
		velocity.x = RedWhaleDemon_TACKLE_VEL;
		accel.x = -RedWhaleDemon_TACKLE_ACCEL;
		
		state = STATE.STATE_TACKLE;
		state_timer = 0;
	}
	*/
	private void changeToTackleState(){
		state = STATE.STATE_TACKLE;
		state_timer = 0;
		
		velocity.x = (-facedirection) * RedWhaleDemon_TACKLE_VEL;
		accel.x = facedirection * RedWhaleDemon_TACKLE_ACCEL;		
	}
	
	private void updateTackleState(float deltaTime){
		if(state_timer > 2.3f)
			changeToPauseState();
	}
	
	private void changeToPauseState(){
		state = STATE.STATE_PAUSE;
		state_timer = 0;
		facedirection = -facedirection;
		
		velocity.x = 0;
		accel.x = 0;
	}
	
	private void updatePauseState(float deltaTime){
		if(state_timer > 1.0f){
			changeToTackleState();
		}
	}
	
	STATE future_state;
	public float old_vel;
	
	public void changeToCollidedState(){
		future_state = state;
		old_vel = velocity.y;
		
		resetDimensions(RedWhaleDemon_COLLISION_WIDTH, RedWhaleDemon_COLLISION_HEIGHT);
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
			case STATE_FLOAT_VERTICAL:
				changeToFloatState(state_timer);
				break;
			case STATE_TACKLE:
				break;
			case STATE_PAUSE:
				break;
			}
		}
	}
}

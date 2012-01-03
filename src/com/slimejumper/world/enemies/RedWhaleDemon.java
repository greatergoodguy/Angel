package com.slimejumper.world.enemies;


public class RedWhaleDemon extends Enemy{

	public static final float RedWhaleDemon_WIDTH = 1.25f;
	public static final float RedWhaleDemon_HEIGHT = 1.00f;
	public static final int RedWhaleDemon_HEALTH = 2;
	
	public static final float RedWhaleDemon_VERTICAL_VEL = 0.6f;
	public static final float RedWhaleDemon_TACKLE_VEL = 5.0f;
	public static final float RedWhaleDemon_TACKLE_ACCEL = 2.0f;
	
	public STATE state;
	private float state_timer;
	
	public enum STATE{
		STATE_FLOAT_VERTICAL,
		STATE_TACKLE,
		STATE_PAUSE
	}
	
	public RedWhaleDemon() {
		super(0, 0, RedWhaleDemon_WIDTH, RedWhaleDemon_HEIGHT);
	}
	
	public RedWhaleDemon(float x, float y){
		super(x, y, RedWhaleDemon_WIDTH, RedWhaleDemon_HEIGHT);
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
		state = STATE.STATE_FLOAT_VERTICAL;
		state_timer = 0;
		velocity.x = 0;
		velocity.y = 0;
	}
	
	public void changeToFloatState(){
		state = STATE.STATE_FLOAT_VERTICAL;
		velocity.x = 0;
		velocity.y = RedWhaleDemon_VERTICAL_VEL;
		facedirection = SPRITE_LEFT;
	}
	
	public void changeToFloatState(float state_timer_offset) {
		changeToFloatState();
		state_timer = state_timer_offset;
	}

	private void updateFloatVerticalState(float deltaTime) {		
		if(state_timer > 2.0f){
			velocity.y = -velocity.y;
			state_timer = 0;
		}		
	}
	
	public void changeToTackleStateLeft(){
		facedirection = SPRITE_LEFT;
		velocity.x = -RedWhaleDemon_TACKLE_VEL;
		accel.x = RedWhaleDemon_TACKLE_ACCEL;
		
		state = STATE.STATE_TACKLE;
		state_timer = 0;
	}
	
	public void changeToTackleStateRight(){
		facedirection = SPRITE_RIGHT;
		velocity.x = RedWhaleDemon_TACKLE_VEL;
		accel.x = -RedWhaleDemon_TACKLE_ACCEL;
		
		state = STATE.STATE_TACKLE;
		state_timer = 0;
	}
	
	public void changeToTackleStateRightFAST(){
		facedirection = SPRITE_RIGHT;
		velocity.x = RedWhaleDemon_TACKLE_VEL;
		accel.x = -RedWhaleDemon_TACKLE_ACCEL;
		
		state = STATE.STATE_TACKLE;
		state_timer = 0;
	}
	
	private void changeToTackleState(){
		state = STATE.STATE_TACKLE;
		state_timer = 0;
		
		facedirection = -facedirection;
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
		
		velocity.x = 0;
		accel.x = 0;
	}
	
	private void updatePauseState(float deltaTime){
		if(state_timer > 1.0f){
			changeToTackleState();
		}
	}
}

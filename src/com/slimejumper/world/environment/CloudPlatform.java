package com.slimejumper.world.environment;


public class CloudPlatform extends Platform{
	public static final float CLOUD_PLATFORM_WIDTH = 3.5f;
	public static final float CLOUD_PLATFORM_HEIGHT = 0.525f;
	
	public static final float CLOUD_PLATFORM_OSCILLATE_HORIZONTAL_DEFAULT_VEL = 1.0f;
	public static final float CLOUD_PLATFORM_OSCILLATE_VERTICAL_DEFAULT_VEL = 1.0f;
	
	public static final float CLOUD_PLATFORM_OSCILLATE_VERTICAL_STATE_TIMER = 4.0f;

	public static enum STATE{
		CLOUD_STATE_STILL, CLOUD_STATE_OSCILLATE_HORIZONTAL, CLOUD_STATE_OSCILLATE_VERTICAL
	}

	public float state_timer;
	public STATE state;
	
	public CloudPlatform(){
		super(0, 0, CLOUD_PLATFORM_WIDTH, CLOUD_PLATFORM_HEIGHT);
	}
	
	public CloudPlatform(float x, float y){
		super(x, y, CLOUD_PLATFORM_WIDTH, CLOUD_PLATFORM_HEIGHT);
	}


	public void reset(float x_coord, float y_coord) {
		super.reset();
		resetPositionLowerLeft(x_coord, y_coord);
		state_timer = 0;
	}
	
	public void update(float deltaTime){
		
		super.update(deltaTime);
		
		state_timer += deltaTime;
		switch(state){
		case CLOUD_STATE_STILL:
			break;
		case CLOUD_STATE_OSCILLATE_HORIZONTAL:
			updateOscillateHorizontalState(deltaTime);
			break;
		case CLOUD_STATE_OSCILLATE_VERTICAL:
			updateOscillateVerticalState(deltaTime);
			break;
		}
	}

	public void changeToStillState(){
		state = STATE.CLOUD_STATE_STILL;
		velocity.x = 0;
		velocity.y = 0;
	}

	public void changeToOscillateHorizontalState(){
		state = STATE.CLOUD_STATE_OSCILLATE_HORIZONTAL;
		velocity.x = CLOUD_PLATFORM_OSCILLATE_HORIZONTAL_DEFAULT_VEL;
		velocity.y = 0;
	}
	
	public void changeToOscillateHorizontalState(float vertical_vel){
		state = STATE.CLOUD_STATE_OSCILLATE_HORIZONTAL;
		velocity.x = vertical_vel;
		velocity.y = 0;
	}
	
	private void updateOscillateHorizontalState(float deltaTime) {
		if(state_timer > 3){
			velocity.x = -velocity.x;
			state_timer = 0;
		}
	}

	public void changeToOscillateVerticalState(){
		state = STATE.CLOUD_STATE_OSCILLATE_VERTICAL;
		velocity.x = 0;
		velocity.y = CLOUD_PLATFORM_OSCILLATE_VERTICAL_DEFAULT_VEL;
	}
	
	public void changeToOscillateVerticalState(float vertical_vel){
		state = STATE.CLOUD_STATE_OSCILLATE_VERTICAL;
		velocity.x = 0;
		velocity.y = vertical_vel;
	}
	
	public void changeToOscillateVerticalStateCentered(float vertical_vel){
		state_timer = CLOUD_PLATFORM_OSCILLATE_VERTICAL_STATE_TIMER/2;
		state = STATE.CLOUD_STATE_OSCILLATE_VERTICAL;
		velocity.x = 0;
		velocity.y = vertical_vel;
	}

	private void updateOscillateVerticalState(float deltaTime) {
		if(state_timer > CLOUD_PLATFORM_OSCILLATE_VERTICAL_STATE_TIMER){
			velocity.y = -velocity.y;
			state_timer = 0;
		}
	}
}

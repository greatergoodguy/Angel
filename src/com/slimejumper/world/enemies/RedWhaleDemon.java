package com.slimejumper.world.enemies;


public class RedWhaleDemon extends Enemy{

	public static final float RedWhaleDemon_WIDTH = 1.25f;
	public static final float RedWhaleDemon_HEIGHT = 1.00f;
	
	public static final float RedWhaleDemon_VERTICAL_VEL = 0.2f;
	
	private float switch_motion_timer;
	
	public RedWhaleDemon() {
		super(0, 0, RedWhaleDemon_WIDTH, RedWhaleDemon_HEIGHT);
	}
	
	public RedWhaleDemon(float x, float y){
		super(x, y, RedWhaleDemon_WIDTH, RedWhaleDemon_HEIGHT);
		switch_motion_timer = 0;
		velocity.y = RedWhaleDemon_VERTICAL_VEL;		
	}

	public void update(float deltaTime){
		super.update(deltaTime);
		updateTimers(deltaTime);
		adjustVectors();
	}

	private void updateTimers(float deltaTime) {
		switch_motion_timer += deltaTime;
		
	}

	private void adjustVectors() {
		if(switch_motion_timer > 3){
			velocity.y = -velocity.y;
			switch_motion_timer = 0;
		}		
	}
	
	public void reset(){
		super.reset();
	}
}

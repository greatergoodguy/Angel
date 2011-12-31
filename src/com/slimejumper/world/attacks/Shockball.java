package com.slimejumper.world.attacks;

import com.slimejumper.world.GameObject;

public class Shockball extends Attack{
	public static final float SHOCKBALL_WIDTH = 1.5f;
	public static final float SHOCKBALL_HEIGHT = 1.625f;
	
	public static final float SHOCKBALL_LIFESPAN = 5.0f;

	public static final float SHOCKBALL_LAUNCH_VELOCITY_LEFT = -3.0f;
	public static final float SHOCKBALL_LAUNCH_VELOCITY_RIGHT = 3.0f;
	
	public Shockball() {
		this(3,3);
	}
	
	public Shockball(float x, float y){
		super(x, y, SHOCKBALL_WIDTH, SHOCKBALL_HEIGHT);
	}

	public void update(float deltaTime){
		super.update(deltaTime);
	}

	public void reset(GameObject gameObject) {
		super.reset(gameObject);
		velocity.x = SHOCKBALL_LAUNCH_VELOCITY_LEFT;		
	}

	public void reset(GameObject gameObject, float launch_vel_x) {
		super.reset(gameObject);
		velocity.x = launch_vel_x;

	}
}

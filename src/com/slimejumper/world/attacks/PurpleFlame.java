package com.slimejumper.world.attacks;

import com.slimejumper.world.GameObject;

public class PurpleFlame extends Attack{
	public static final float PURPLE_FLAME_WIDTH = 0.6f;
	public static final float PURPLE_FLAME_HEIGHT = 0.6f;
	
	public static final float PURPLE_FLAME_LIFESPAN = 4.0f;

	public static final float PURPLE_FLAME_LAUNCH_VELOCITY = -3.0f;
	
	public PurpleFlame() {
		this(3,3);
	}
	
	public PurpleFlame(float x, float y){
		super(x, y, PURPLE_FLAME_WIDTH, PURPLE_FLAME_HEIGHT);
	}

	public void update(float deltaTime){
		super.update(deltaTime);
	}

	public void reset(GameObject gameObject) {
		super.reset(gameObject);
		velocity.x = PURPLE_FLAME_LAUNCH_VELOCITY;		
	}

	public void reset(GameObject gameObject, float launch_vel_x) {
		super.reset(gameObject);
		velocity.x = launch_vel_x;

	}
}

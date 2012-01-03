package com.slimejumper.world.attacks;

import com.slimejumper.world.GameObject;

public class PurpleFlame extends Attack{
	public static final float PURPLE_FLAME_WIDTH = 1.05f;
	public static final float PURPLE_FLAME_HEIGHT = 0.375f;
	
	public static final float PURPLE_FLAME_LIFESPAN = 1.5f;

	public static final float PURPLE_FLAME_LAUNCH_VELOCITY = -2.5f;
	
	public static final float PURPLE_FLAME_HORIZONTAL_OFFSET = 0.4f;
	public static final float PURPLE_FLAME_VERTICAL_OFFSET = 0.4f;
	
	public PurpleFlame() {
		this(3,3);
	}
	
	public PurpleFlame(float x, float y){
		super(x, y, PURPLE_FLAME_WIDTH, PURPLE_FLAME_HEIGHT);
	}

	public void reset(GameObject gameObject) {
		super.reset(gameObject);
		velocity.x = PURPLE_FLAME_LAUNCH_VELOCITY * facedirection;
		resetPositionCenter(center.x + PURPLE_FLAME_HORIZONTAL_OFFSET * (-facedirection), center.y - PURPLE_FLAME_VERTICAL_OFFSET);
		
	}

	public void reset(GameObject gameObject, float launch_vel_x) {
		super.reset(gameObject);
		velocity.x = launch_vel_x * facedirection;
		resetPositionCenter(center.x + PURPLE_FLAME_HORIZONTAL_OFFSET * (-facedirection), center.y - PURPLE_FLAME_VERTICAL_OFFSET);
	}
	
	
	
	public void update(float deltaTime){
		super.update(deltaTime);
	}
}

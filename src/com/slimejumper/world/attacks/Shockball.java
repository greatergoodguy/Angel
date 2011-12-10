package com.slimejumper.world.attacks;

import com.slimejumper.GameScreen;
import com.slimejumper.tools.SpriteContainer;
import com.slimejumper.world.GameObject;

public class Shockball extends Attack{
	public static final float SHOCKBALL_WIDTH = 1.5f;
	public static final float SHOCKBALL_HEIGHT = 1.625f;
	
	public static final float SHOCKBALL_LIFESPAN = 5.0f;

	public static final float SHOCKBALL_LAUNCH_VELOCITY = -3.0f;
	
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
		super.reset();
		resetPositionCenter(gameObject.center);
	}

	public static void activateDualShot(GameObject gameObject) {

		Shockball shockball1 = GameScreen.pool_manager.shockball_pool.newObject();
		shockball1.reset(gameObject);
		shockball1.velocity.x = SHOCKBALL_LAUNCH_VELOCITY;			
		SpriteContainer.shockballs.add(shockball1);
			
		Shockball shockball2 = GameScreen.pool_manager.shockball_pool.newObject();
		shockball2.reset(gameObject);
		shockball2.velocity.x = -SHOCKBALL_LAUNCH_VELOCITY;
		SpriteContainer.shockballs.add(shockball2);

	}
}

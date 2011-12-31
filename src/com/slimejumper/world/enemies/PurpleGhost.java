package com.slimejumper.world.enemies;

import com.slimejumper.tools.PoolManager;
import com.slimejumper.world.attacks.PurpleFlame;


public class PurpleGhost extends Enemy{
	
	public static final float PurpleGhost_WIDTH = 0.8f;
	public static final float PurpleGhost_HEIGHT = 0.8f;
	public static final int PurpleGhost_HEALTH = 1;
		
	public static final float PurpleGhost_LIFESPAN = 14.0f;
	
	public PurpleFlame purple_flame;
	public float state_timer;
	
	public PurpleGhost(){
		super(0, 0, PurpleGhost_WIDTH, PurpleGhost_HEIGHT);
	}
	
	public PurpleGhost(float x, float y){
		super(x, y, PurpleGhost_WIDTH, PurpleGhost_HEIGHT);
	}
	
	public void reset(float x_coord, float y_coord){
		super.reset();
		resetPositionLowerLeft(x_coord, y_coord);
		state_timer = 0;
		health_bar = PurpleGhost_HEALTH;
	}
	
	public void update(float deltaTime){
		super.update(deltaTime);
		updatePurpleFlame(deltaTime);
		
		state_timer += deltaTime;
		if(state_timer >= 6.0f){
			state_timer = 0;
			activatePurpleFlame();
		}
	}
	
	private void updatePurpleFlame(float deltaTime) {
		if(purple_flame != null){
			purple_flame.update(deltaTime);
			if(purple_flame.life_timer > PurpleFlame.PURPLE_FLAME_LIFESPAN){
				PoolManager.pool_manager_singleton.purple_flame_pool.free(purple_flame);
				purple_flame = null;
			}
		}
		
	}

	public void activatePurpleFlame(){			
		purple_flame = PoolManager.pool_manager_singleton.purple_flame_pool.newObject();
		purple_flame.reset(this);
	}
}

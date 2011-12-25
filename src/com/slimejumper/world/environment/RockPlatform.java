package com.slimejumper.world.environment;

import java.util.LinkedList;

import com.slimejumper.levels.Level;
import com.slimejumper.tools.PoolManager;

public class RockPlatform extends Platform{
	public static final float ROCK_PLATFORM_WIDTH = 2.0f;
	public static final float ROCK_PLATFORM_HEIGHT = 0.375f;
	
	
	public RockPlatform(){
		super(0, 0, ROCK_PLATFORM_WIDTH, ROCK_PLATFORM_HEIGHT);
	}
	
	public RockPlatform(float x, float y){
		super(x, y, ROCK_PLATFORM_WIDTH, ROCK_PLATFORM_HEIGHT);
	}


	private void reset(float x_coord, float y_coord) {
		super.reset();
		resetPositionLowerLeft(x_coord, y_coord);
	}
	
	public static void initializeRockPlatformGround(Level level, LinkedList<RockPlatform> rock_platforms) {
		float x_coord = 0;
		float y_coord = 0;
		
		while(x_coord < level.world_width){
			// create new Platform and add to list
			RockPlatform rock_platform = PoolManager.pool_manager_singleton.rock_platform_pool.newObject();
			rock_platform.reset(x_coord, y_coord);
			rock_platforms.add(rock_platform);
			
			// Advance counter and repeat
			x_coord += ROCK_PLATFORM_WIDTH;
		}
	}
}

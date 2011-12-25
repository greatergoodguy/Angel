package com.slimejumper.world.environment;

import java.util.LinkedList;

import com.slimejumper.gameframework.Pool;
import com.slimejumper.levels.Level;
import com.slimejumper.tools.PoolManager;

public class CloudPlatform extends Platform{
	public static final float CLOUD_PLATFORM_WIDTH = 3.5f;
	public static final float CLOUD_PLATFORM_HEIGHT = 0.5f;
	
	public CloudPlatform(){
		super(0, 0, CLOUD_PLATFORM_WIDTH, CLOUD_PLATFORM_HEIGHT);
	}
	
	public CloudPlatform(float x, float y){
		super(x, y, CLOUD_PLATFORM_WIDTH, CLOUD_PLATFORM_HEIGHT);
	}


	private void reset(float x_coord, float y_coord) {
		super.reset();
		resetPositionLowerLeft(x_coord, y_coord);
	}
	
	public static void initializeCloudPlatformGround(Level level, LinkedList<CloudPlatform> cloud_platforms) {
		float x_coord = 0;
		float y_coord = 0;
		Pool<CloudPlatform> cloud_platform_pool = PoolManager.pool_manager_singleton.cloud_platform_pool;
		
		while(x_coord < level.world_width){
			// create new Platform and add to list
			CloudPlatform cloud_platform = cloud_platform_pool.newObject();
			cloud_platform.reset(x_coord, y_coord);
			cloud_platforms.add(cloud_platform);
			
			// Advance counter and repeat
			x_coord += CLOUD_PLATFORM_WIDTH;
		}
	}
}

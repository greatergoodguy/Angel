package com.slimejumper.tools;

import com.slimejumper.world.environment.CloudPlatform;

public class TesterFactory {
	PoolManager pool_manager = PoolManager.pool_manager_singleton;
	
	public TesterFactory(){
		
	}
	
	public CloudPlatform createStaticCloudPlatform(float x_coord, float y_coord){
		CloudPlatform cloud_platform = pool_manager.cloud_platform_pool.newObject();
		cloud_platform.reset(x_coord, y_coord);
		cloud_platform.changeToStillState();
		return cloud_platform;
	}
}

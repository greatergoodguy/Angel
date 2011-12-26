package com.slimejumper.tools;

import com.slimejumper.world.enemies.PurpleGhost;
import com.slimejumper.world.enemies.RedWhaleDemon;
import com.slimejumper.world.environment.CloudPlatform;

public class TreetopValleyFactory {
	
	PoolManager pool_manager = PoolManager.pool_manager_singleton;
	
	public TreetopValleyFactory(){
		
	}
	
	public CloudPlatform createStaticCloudPlatform(float x_coord, float y_coord){
		CloudPlatform cloud_platform = pool_manager.cloud_platform_pool.newObject();
		cloud_platform.reset(x_coord, y_coord);
		return cloud_platform;
	}

	public RedWhaleDemon createStillRedWhaleDemon(float x_coord, float y_coord){
		RedWhaleDemon red_whale_demon = pool_manager.red_whale_demon_pool.newObject();
		red_whale_demon.reset(x_coord, y_coord);
		red_whale_demon.changeToStillState();
		return red_whale_demon;
	}
	
	public RedWhaleDemon createFloatRedWhaleDemon(float x_coord, float y_coord){
		RedWhaleDemon red_whale_demon = pool_manager.red_whale_demon_pool.newObject();
		red_whale_demon.reset(x_coord, y_coord);
		red_whale_demon.changeToFloatState();
		return red_whale_demon;
	}
	
	public PurpleGhost createPurpleGhost(float x_coord, float y_coord){
		PurpleGhost purple_ghost = pool_manager.purple_ghost_pool.newObject();
		purple_ghost.reset(x_coord, y_coord);
		return purple_ghost;
	}
}

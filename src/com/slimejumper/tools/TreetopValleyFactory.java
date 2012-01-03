package com.slimejumper.tools;

import com.slimejumper.world.enemies.PurpleGhost;
import com.slimejumper.world.enemies.RedWhaleDemon;
import com.slimejumper.world.environment.CloudPlatform;

public class TreetopValleyFactory {
	
	PoolManager pool_manager = PoolManager.pool_manager_singleton;
	
	public TreetopValleyFactory(){
		
	}
	
	/*
	 * Cloud Platforms
	 */
	
	public CloudPlatform createStaticCloudPlatform(float x_coord, float y_coord){
		CloudPlatform cloud_platform = pool_manager.cloud_platform_pool.newObject();
		cloud_platform.reset(x_coord, y_coord);
		cloud_platform.changeToStillState();
		return cloud_platform;
	}
	
	public CloudPlatform createOscillateHorizontalCloudPlatform(float x_coord, float y_coord){
		CloudPlatform cloud_platform = pool_manager.cloud_platform_pool.newObject();
		cloud_platform.reset(x_coord, y_coord);
		cloud_platform.changeToOscillateHorizontalState();
		return cloud_platform;
	}
	
	public CloudPlatform createOscillateVerticalCloudPlatform(float x_coord, float y_coord){
		CloudPlatform cloud_platform = pool_manager.cloud_platform_pool.newObject();
		cloud_platform.reset(x_coord, y_coord);
		cloud_platform.changeToOscillateVerticalState();
		return cloud_platform;
	}
	
	public CloudPlatform createOscillateVerticalCloudPlatformCentered(float x_coord, float y_coord, float vertical_vel){
		CloudPlatform cloud_platform = pool_manager.cloud_platform_pool.newObject();
		cloud_platform.reset(x_coord, y_coord);
		cloud_platform.changeToOscillateVerticalStateCentered(vertical_vel);
		return cloud_platform;
	}

	/*
	 * Red Whale Demon
	 */
	
	public RedWhaleDemon createStillRedWhaleDemonLeft(float x_coord, float y_coord){
		RedWhaleDemon red_whale_demon = pool_manager.red_whale_demon_pool.newObject();
		red_whale_demon.reset(x_coord, y_coord);
		red_whale_demon.changeToStillStateLeft();
		return red_whale_demon;
	}
	
	public RedWhaleDemon createStillRedWhaleDemonRight(float x_coord, float y_coord){
		RedWhaleDemon red_whale_demon = pool_manager.red_whale_demon_pool.newObject();
		red_whale_demon.reset(x_coord, y_coord);
		red_whale_demon.changeToStillStateRight();
		return red_whale_demon;
	}
	
	public RedWhaleDemon createFloatRedWhaleDemon(float x_coord, float y_coord){
		RedWhaleDemon red_whale_demon = pool_manager.red_whale_demon_pool.newObject();
		red_whale_demon.reset(x_coord, y_coord);
		red_whale_demon.changeToFloatState();
		return red_whale_demon;
	}
	
	public RedWhaleDemon createFloatRedWhaleDemon(float x_coord, float y_coord, float state_timer_offset){
		RedWhaleDemon red_whale_demon = pool_manager.red_whale_demon_pool.newObject();
		red_whale_demon.reset(x_coord, y_coord);
		red_whale_demon.changeToFloatState(state_timer_offset);
		return red_whale_demon;
	}
	
	public RedWhaleDemon createTackleRedWhaleDemonLeft(float x_coord, float y_coord){
		RedWhaleDemon red_whale_demon = pool_manager.red_whale_demon_pool.newObject();
		red_whale_demon.reset(x_coord, y_coord);
		red_whale_demon.changeToTackleStateLeft();
		return red_whale_demon;
	}
	
	public RedWhaleDemon createTackleRedWhaleDemonRight(float x_coord, float y_coord){
		RedWhaleDemon red_whale_demon = pool_manager.red_whale_demon_pool.newObject();
		red_whale_demon.reset(x_coord, y_coord);
		red_whale_demon.changeToTackleStateRight();
		return red_whale_demon;
	}
	
	public RedWhaleDemon createTackleRedWhaleDemonRightFAST(float x_coord, float y_coord){
		RedWhaleDemon red_whale_demon = pool_manager.red_whale_demon_pool.newObject();
		red_whale_demon.reset(x_coord, y_coord);
		red_whale_demon.changeToTackleStateRightFAST();
		return red_whale_demon;
	}
	
	/*
	 * Purple Ghosts
	 */
	
	public PurpleGhost createPurpleGhostStandardPatternLeft(float x_coord, float y_coord){
		PurpleGhost purple_ghost = pool_manager.purple_ghost_pool.newObject();
		purple_ghost.reset(x_coord, y_coord);
		purple_ghost.changeToStandardStateLeft();
		return purple_ghost;
	}
	
	public PurpleGhost createPurpleGhostStandardPatternRight(float x_coord, float y_coord){
		PurpleGhost purple_ghost = pool_manager.purple_ghost_pool.newObject();
		purple_ghost.reset(x_coord, y_coord);
		purple_ghost.changeToStandardStateRight();
		return purple_ghost;
	}
	
	public PurpleGhost createPurpleGhostStandardPatternRight(float x_coord, float y_coord, float state_timer_offset){
		PurpleGhost purple_ghost = pool_manager.purple_ghost_pool.newObject();
		purple_ghost.reset(x_coord, y_coord);
		purple_ghost.changeToStandardStateRight(state_timer_offset);
		return purple_ghost;
	}
	
	public PurpleGhost createPurpleGhostFloatVerticalPatternLeft(float x_coord, float y_coord){
		PurpleGhost purple_ghost = pool_manager.purple_ghost_pool.newObject();
		purple_ghost.reset(x_coord, y_coord);
		purple_ghost.changeToFloatVerticalState();
		return purple_ghost;
	}
	
	public PurpleGhost createPurpleGhostWalkPatternRight(float x_coord, float y_coord){
		PurpleGhost purple_ghost = pool_manager.purple_ghost_pool.newObject();
		purple_ghost.reset(x_coord, y_coord);
		purple_ghost.changeToWalkStateRight();
		return purple_ghost;
	}
}

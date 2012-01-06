package com.slimejumper.tools;

import java.util.LinkedList;

import com.slimejumper.levels.TreetopValleyLevel;
import com.slimejumper.world.enemies.PurpleGhost;
import com.slimejumper.world.enemies.RedWhaleDemon;
import com.slimejumper.world.environment.CloudPlatform;
import com.slimejumper.world.environment.CloudPlatformShort;

public class TreetopValleyFactory {
	
	PoolManager pool_manager = PoolManager.pool_manager_singleton;
	
	public LinkedList<CloudPlatform> cloud_platforms;
	public LinkedList<CloudPlatformShort> cloud_platforms_short;
	
	public LinkedList<RedWhaleDemon> red_whale_demons;
	public LinkedList<PurpleGhost> purple_ghosts;
	
	public TreetopValleyFactory(TreetopValleyLevel treetopValleyLevel){
		cloud_platforms = treetopValleyLevel.cloud_platforms;
		cloud_platforms_short = treetopValleyLevel.cloud_platforms_short;
		
		red_whale_demons = treetopValleyLevel.red_whale_demons;
		purple_ghosts = treetopValleyLevel.purple_ghosts;
	}
	
	/*
	 * Cloud Platforms
	 */
	
	public void createStaticCloudPlatform(float x_coord, float y_coord){
		CloudPlatform cloud_platform = pool_manager.cloud_platform_pool.newObject();
		cloud_platform.reset(x_coord, y_coord);
		cloud_platform.changeToStillState();
		cloud_platforms.add(cloud_platform);
	}
	
	public void createOscillateHorizontalCloudPlatform(float x_coord, float y_coord){
		CloudPlatform cloud_platform = pool_manager.cloud_platform_pool.newObject();
		cloud_platform.reset(x_coord, y_coord);
		cloud_platform.changeToOscillateHorizontalState();
		cloud_platforms.add(cloud_platform);
	}
	
	public void createOscillateVerticalCloudPlatform(float x_coord, float y_coord){
		CloudPlatform cloud_platform = pool_manager.cloud_platform_pool.newObject();
		cloud_platform.reset(x_coord, y_coord);
		cloud_platform.changeToOscillateVerticalState();
		cloud_platforms.add(cloud_platform);
	}
	
	public void createOscillateVerticalCloudPlatformCentered(float x_coord, float y_coord, float vertical_vel){
		CloudPlatform cloud_platform = pool_manager.cloud_platform_pool.newObject();
		cloud_platform.reset(x_coord, y_coord);
		cloud_platform.changeToOscillateVerticalStateCentered(vertical_vel);
		cloud_platforms.add(cloud_platform);
	}
	
	/*
	 * Cloud Platforms Short
	 */

	public void createStaticCloudPlatformShort(float x_coord, float y_coord){
		CloudPlatformShort cloud_platform_short = pool_manager.cloud_platform_short_pool.newObject();
		cloud_platform_short.reset(x_coord, y_coord);
		cloud_platforms_short.add(cloud_platform_short);
	}
	
	/*
	 * Red Whale Demon
	 */
	
	public void createStillRedWhaleDemonLeft(float x_coord, float y_coord){
		RedWhaleDemon red_whale_demon = pool_manager.red_whale_demon_pool.newObject();
		red_whale_demon.reset(x_coord, y_coord);
		red_whale_demon.changeToStillStateLeft();
		red_whale_demons.add(red_whale_demon);
	}
	
	public void createStillRedWhaleDemonRight(float x_coord, float y_coord){
		RedWhaleDemon red_whale_demon = pool_manager.red_whale_demon_pool.newObject();
		red_whale_demon.reset(x_coord, y_coord);
		red_whale_demon.changeToStillStateRight();
		red_whale_demons.add(red_whale_demon);
	}
	
	public void createFloatRedWhaleDemon(float x_coord, float y_coord){
		RedWhaleDemon red_whale_demon = pool_manager.red_whale_demon_pool.newObject();
		red_whale_demon.reset(x_coord, y_coord);
		red_whale_demon.changeToFloatState();
		red_whale_demons.add(red_whale_demon);
	}
	
	public void createFloatRedWhaleDemon(float x_coord, float y_coord, float state_timer_offset){
		RedWhaleDemon red_whale_demon = pool_manager.red_whale_demon_pool.newObject();
		red_whale_demon.reset(x_coord, y_coord);
		red_whale_demon.changeToFloatState(state_timer_offset);
		red_whale_demons.add(red_whale_demon);
	}
	
	public void createFloatRedWhaleDemonLarge(float x_coord, float y_coord, float state_timer_offset){
		RedWhaleDemon red_whale_demon = pool_manager.red_whale_demon_pool.newObject();
		red_whale_demon.reset(x_coord, y_coord);
		red_whale_demon.changeToFloatStateLarge(state_timer_offset);
		red_whale_demons.add(red_whale_demon);
	}
	
	public void createTackleRedWhaleDemonLeft(float x_coord, float y_coord){
		RedWhaleDemon red_whale_demon = pool_manager.red_whale_demon_pool.newObject();
		red_whale_demon.reset(x_coord, y_coord);
		red_whale_demon.changeToTackleStateLeft();
		red_whale_demons.add(red_whale_demon);
	}
	
	public void createTackleRedWhaleDemonRight(float x_coord, float y_coord){
		RedWhaleDemon red_whale_demon = pool_manager.red_whale_demon_pool.newObject();
		red_whale_demon.reset(x_coord, y_coord);
		red_whale_demon.changeToTackleStateRight();
		red_whale_demons.add(red_whale_demon);
	}
	
	public void createTackleRedWhaleDemonRightFAST(float x_coord, float y_coord){
		RedWhaleDemon red_whale_demon = pool_manager.red_whale_demon_pool.newObject();
		red_whale_demon.reset(x_coord, y_coord);
		red_whale_demon.changeToTackleStateRightFAST();
		red_whale_demons.add(red_whale_demon);
	}
	
	/*
	 * Purple Ghosts
	 */
	
	public void createPurpleGhostStandardPatternLeft(float x_coord, float y_coord){
		PurpleGhost purple_ghost = pool_manager.purple_ghost_pool.newObject();
		purple_ghost.reset(x_coord, y_coord);
		purple_ghost.changeToStandardStateLeft();
		purple_ghosts.add(purple_ghost);
	}
	
	public void createPurpleGhostStandardPatternRight(float x_coord, float y_coord){
		PurpleGhost purple_ghost = pool_manager.purple_ghost_pool.newObject();
		purple_ghost.reset(x_coord, y_coord);
		purple_ghost.changeToStandardStateRight();
		purple_ghosts.add(purple_ghost);
	}
	
	public void createPurpleGhostStandardPatternRight(float x_coord, float y_coord, float state_timer_offset){
		PurpleGhost purple_ghost = pool_manager.purple_ghost_pool.newObject();
		purple_ghost.reset(x_coord, y_coord);
		purple_ghost.changeToStandardStateRight(state_timer_offset);
		purple_ghosts.add(purple_ghost);
	}
	
	public void createPurpleGhostFloatVerticalPatternLeft(float x_coord, float y_coord){
		PurpleGhost purple_ghost = pool_manager.purple_ghost_pool.newObject();
		purple_ghost.reset(x_coord, y_coord);
		purple_ghost.changeToFloatVerticalState();
		purple_ghosts.add(purple_ghost);
	}
	
	public void createPurpleGhostWalkPatternRight(float x_coord, float y_coord){
		PurpleGhost purple_ghost = pool_manager.purple_ghost_pool.newObject();
		purple_ghost.reset(x_coord, y_coord);
		purple_ghost.changeToWalkStateRight();
		purple_ghosts.add(purple_ghost);
	}
	
	// Complex

	public void createVeeCloudPlatformShort(float x_coord, float y_coord) {
		// x_coord and y_coord represents the vertex
		float x_offset = 2.4f;
		float y_offset = 1.6f;
		
		createStaticCloudPlatformShort(x_coord, y_coord);
		createStaticCloudPlatformShort(x_coord - x_offset, y_coord + y_offset);
		createStaticCloudPlatformShort(x_coord + x_offset, y_coord + y_offset);
		
		
	}
	
	public void createXrossCloudPlatformShort(float x_coord, float y_coord){
		// x_coord and y_coord represents the center
		float x_offset = 3.5f;
		float y_offset = 1.6f;
		
		createStaticCloudPlatformShort(x_coord, y_coord);
		createStaticCloudPlatformShort(x_coord - x_offset, y_coord + y_offset);
		createStaticCloudPlatformShort(x_coord + x_offset, y_coord + y_offset);
		createStaticCloudPlatformShort(x_coord - x_offset, y_coord - y_offset);
		createStaticCloudPlatformShort(x_coord + x_offset, y_coord - y_offset);
	}
	
	public void createHatCloudPlatformShort(float x_coord, float y_coord){
		// x_coord and y_coord represents the vertex
		float x_offset = 2.4f;
		float y_offset = 1.6f;
		
		createStaticCloudPlatformShort(x_coord, y_coord);
		createStaticCloudPlatformShort(x_coord - x_offset, y_coord - y_offset);
		createStaticCloudPlatformShort(x_coord + x_offset, y_coord - y_offset);
	}

	public void initializePart1() {
		createHatCloudPlatformShort(1.4f, 5f);
		createVeeCloudPlatformShort(4.6f, 1.6f);
		
		createStaticCloudPlatformShort(11.2f, 2.2f);
		
		createStaticCloudPlatform(-1.9f, 2.1f);
		createStaticCloudPlatform(0, 0);
		createStaticCloudPlatform(4, -0.2f);
		createStaticCloudPlatform(9.0f, 0.0f);
		createStaticCloudPlatform(12.5f, 0.0f);
		
		createPurpleGhostWalkPatternRight(9.5f, 1.2f);
	}
	
	public void initializePart2() {
		createStaticCloudPlatformShort(15.1f, 1.9f);
		createStaticCloudPlatformShort(19.9f, 1.1f);
		
		createStaticCloudPlatform(20, -0.2f);
		createStaticCloudPlatform(24.7f, 1.0f);
		createStaticCloudPlatform(27.0f, 0.9f);
		
		createFloatRedWhaleDemon(24.2f, 0.7f);
	}
	
	public void initializePart3() {
		createPurpleGhostStandardPatternLeft(30.5f, 5f);
		createOscillateHorizontalCloudPlatform(33, 0.2f);
		
		
		createXrossCloudPlatformShort(33.8f, 2.4f);
		createStaticCloudPlatformShort(35.7f, 3.8f);
		createStaticCloudPlatformShort(38.7f, 5.4f);
		createStaticCloudPlatformShort(32.7f, 5.4f);
		createStaticCloudPlatformShort(36.2f, 7f);
		createStaticCloudPlatformShort(39f, 8.4f);
		createHatCloudPlatformShort(34.5f, 9.7f);
		createStaticCloudPlatformShort(36.7f, 10f);
		createStaticCloudPlatformShort(38.5f, 11.3f);
		
		createStaticCloudPlatform(33, 15.2f);
	}
}

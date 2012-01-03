package com.slimejumper.levels;

import java.util.LinkedList;

import com.slimejumper.Controller;
import com.slimejumper.tools.CollisionManager;
import com.slimejumper.world.Background;
import com.slimejumper.world.Hero;
import com.slimejumper.world.environment.GreekPlatform;

public class MenuLevel extends Level{
	
	private final int BACKGROUND_CLOUDS_WIDTH = 15;	
	
	public Background cloud_background;
	
	public LinkedList<GreekPlatform> greek_platforms;
	
	public MenuLevel(WorldListener listener, Controller controller){
		super(listener, controller);
		hero.reset(3, 3);
		cloud_background = new Background(BACKGROUND_CLOUDS_WIDTH, 0, WORLD_DEFAULT_WIDTH, WORLD_DEFAULT_HEIGHT);
		
		initializeGreekPlatforms();
	}
	
	private void initializeGreekPlatforms() {
		greek_platforms = new LinkedList<GreekPlatform>();
		
		GreekPlatform.initializePlatformGround(this, greek_platforms);
		GreekPlatform.initializePlatformMap(this, greek_platforms);		
	}

	public void update(float deltaTime){		
//		super.update(deltaTime);
		updateHero(deltaTime);
		
		manageCollisions();
		
		if(Hero.hero_singleton.position.y < 0.0f){
			Hero.hero_singleton.resetPositionLowerLeft(2, 11);
			
			// Switch to next level
		}
		
		updateCenter();
		updatePosition();	
	}
	
	private void manageCollisions() {
		CollisionManager.HeroPlatformListCollision(hero, greek_platforms);
	}
	
	public void dispose() {
		hero.dispose();
		
		while(!greek_platforms.isEmpty()){
			GreekPlatform greek_platform = greek_platforms.removeFirst();
			pool_manager.greek_platform_pool.free(greek_platform);
		}
		
	}
}

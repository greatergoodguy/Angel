package com.slimejumper.levels;

import java.util.LinkedList;

import com.slimejumper.Assets;
import com.slimejumper.Controller;
import com.slimejumper.Settings;
import com.slimejumper.tools.SpriteContainer;
import com.slimejumper.tools.SpriteManager;
import com.slimejumper.world.Background;
import com.slimejumper.world.GreekPlatform;
import com.slimejumper.world.environment.RockPlatform;

public class TesterLevel extends Level{
	
	private static final float BACKGROUND_CLOUDS_WIDTH = 15;	
	private static final float BACKGROUND_TREES_WIDTH = 25.6f;
	
	private static final float TESTER_LEVEL_WIDTH = 40f;
	
	public RockPlatform rock_platform_1;
	public LinkedList<RockPlatform> rock_platforms;
	
	public Background background_clouds;
	public Background background_trees;
	
	public TesterLevel(WorldListener listener, SpriteManager sprite_manager, Controller controller){
		super(listener, sprite_manager, controller, TESTER_LEVEL_WIDTH, Level.WORLD_DEFAULT_HEIGHT);		
		background_clouds = new Background(BACKGROUND_CLOUDS_WIDTH, Level.WORLD_DEFAULT_WIDTH, Level.WORLD_DEFAULT_HEIGHT);
		background_trees = new Background(BACKGROUND_TREES_WIDTH, TESTER_LEVEL_WIDTH, Level.WORLD_DEFAULT_HEIGHT);
		
		rock_platforms = new LinkedList<RockPlatform>();
		
		if(Settings.soundEnabled)
	        	Assets.test_music.play();
		
		GreekPlatform.initializePlatformGround(this);
		GreekPlatform.initializePlatformMap();
		
		RockPlatform.initializeRockPlatformGround(this, rock_platforms);
		
		rock_platform_1 = new RockPlatform(5, 4);
	}
	
	public void update(float deltaTime){		
		super.update(deltaTime);
		
		manageCollisions();
		removeUnnecessary();
		
		if(SpriteContainer.hero.position.y < 0.0f){
			SpriteContainer.hero.resetPositionLowerLeft(2, 11);
			
			// Switch to next level
		}
		
		if(SpriteContainer.shadow_hero.position.y < 0.0f){
			SpriteContainer.shadow_hero.resetPositionLowerLeft(2, 11);
			
			// Switch to next level
		}
	}

	private void manageCollisions() {
		
		
	}

	private void removeUnnecessary() {
		// TODO Auto-generated method stub
		
	}
}

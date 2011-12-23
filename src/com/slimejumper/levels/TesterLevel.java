package com.slimejumper.levels;

import com.slimejumper.Controller;
import com.slimejumper.tools.SpriteContainer;
import com.slimejumper.tools.SpriteManager;
import com.slimejumper.world.Background;

public class TesterLevel extends Level{
	
	private final int BACKGROUND_CLOUDS_WIDTH = 15;	
	
	public Background cloud_background;
	
	public TesterLevel(WorldListener listener, SpriteManager sprite_manager, Controller controller){
		super(listener, sprite_manager, controller, 32, 13.5f);
		
		cloud_background = new Background(BACKGROUND_CLOUDS_WIDTH, Level.WORLD_DEFAULT_WIDTH, Level.WORLD_DEFAULT_HEIGHT);
	}
	
	public void update(float deltaTime){		
		super.update(deltaTime);
		
		if(SpriteContainer.hero.position.y < 0.0f){
			SpriteContainer.hero.resetPositionLowerLeft(2, 11);
			
			// Switch to next level
		}
		
		if(SpriteContainer.shadow_hero.position.y < 0.0f){
			SpriteContainer.shadow_hero.resetPositionLowerLeft(2, 11);
			
			// Switch to next level
		}
	}
}

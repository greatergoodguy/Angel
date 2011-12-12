package com.slimejumper.levels;

import com.slimejumper.GameScreen;
import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.tools.SpriteContainer;
import com.slimejumper.tools.SpriteManager;

public class MenuLevel extends Level{
	
	public MenuLevel(WorldListener listener, SpriteManager sprite_manager){
		super(listener, sprite_manager);
		
		center = new Vector2(WORLD_CENTER_DEFAULT_X, WORLD_CENTER_DEFAULT_Y);
		position = new Vector2();
	}
	
	public void update(float deltaTime){		
		super.update(deltaTime);
		if(SpriteContainer.hero.position.y < 0.0f){
			SpriteContainer.hero.resetPositionLowerLeft(2, 11);
			GameScreen.switchToGameWorld();
		}
	}
}

package com.slimejumper.levels;

import com.slimejumper.Controller;
import com.slimejumper.tools.SpriteContainer;
import com.slimejumper.tools.SpriteManager;

public class TesterLevel extends Level{
	public TesterLevel(WorldListener listener, SpriteManager sprite_manager, Controller controller){
		super(listener, sprite_manager, controller);
	}
	
	public void update(float deltaTime){		
		super.update(deltaTime);
		if(SpriteContainer.hero.position.y < 0.0f){
			SpriteContainer.hero.resetPositionLowerLeft(2, 11);
			
			// Switch to next level
		}
	}
}

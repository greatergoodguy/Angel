package com.slimejumper.levels;

import com.slimejumper.GameScreen;
import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.tools.PoolManager;
import com.slimejumper.tools.SpriteContainer;

public class MenuLevel extends Level{
	public interface WorldListener {
		public void jump();

		public void killJump();

		public void hit();

		public void coin();
	}
	
	public MenuLevel(){		
//		if(hero == null)
//			World.hero = new Hero();
		
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

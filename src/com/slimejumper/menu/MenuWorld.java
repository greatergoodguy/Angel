package com.slimejumper.menu;

import com.slimejumper.GameScreen;
import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.tools.PoolManager;
import com.slimejumper.tools.World;
import com.slimejumper.world.Backgrounds;
import com.slimejumper.world.Hero;
import com.slimejumper.world.Platform;
import com.slimejumper.world.GameWorld.WorldListener;

public class MenuWorld extends World{
	public interface WorldListener {
		public void jump();

		public void killJump();

		public void hit();

		public void coin();
	}
	
	public MenuWorld(){		
//		if(hero == null)
//			World.hero = new Hero();
		
		center = new Vector2(WORLD_CENTER_DEFAULT_X, WORLD_CENTER_DEFAULT_Y);
		position = new Vector2();
	}
	
	public void update(float deltaTime){		
		super.update(deltaTime);
		if(hero.position.y < 0.0f){
			hero.resetPositionLowerLeft(2, 11);
			GameScreen.switchToGameWorld();
		}
	}
}

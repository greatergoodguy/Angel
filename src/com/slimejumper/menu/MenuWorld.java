package com.slimejumper.menu;

import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.tools.PoolManager;
import com.slimejumper.tools.World;
import com.slimejumper.world.Background;
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
	
	public Background background = null;
	
	public MenuWorld(){		
		if(hero == null)
			World.hero = new Hero();

		collisionManager.setCollidingHero(hero);
		
		center = new Vector2(WORLD_CENTER_DEFAULT_X, WORLD_CENTER_DEFAULT_Y);
		position = new Vector2();

		Platform.initializePlatformGround();
	}
	
	public void update(float deltaTime){
		hero.update(deltaTime);
		
		super.update();
	}
}

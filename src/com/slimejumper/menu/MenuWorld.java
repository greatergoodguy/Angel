package com.slimejumper.menu;

import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.tools.PoolManager;
import com.slimejumper.tools.World;
import com.slimejumper.world.Background;
import com.slimejumper.world.Hero;
import com.slimejumper.world.Platform;

public class MenuWorld extends World{
	public interface WorldListener {
		public void jump();

		public void killJump();

		public void hit();

		public void coin();
	}
	
	public static final float MENU_WORLD_WIDTH = 10 * 2; // 10 refers to the visible width
	public static final float MENU_WORLD_HEIGHT = 6 * 2; // 6 refers to the visible height
	
	public static final float MENU_WORLD_GRAVITY = -8.5f;
	
	public static final float MENU_WORLD_LEFT_EDGE = 0f;
	public static final float MENU_WORLD_RIGHT_EDGE = 20.0f;
	public static final float MENU_WORLD_BOTTOM_EDGE = 0f;
	public static final float MENU_WORLD_TOP_EDGE = 12.0f;

	public static final float MENU_WORLD_LEFT_BOUND = 5.0f;
	public static final float MENU_WORLD_RIGHT_BOUND = 15.0f;
	public static final float MENU_WORLD_BOTTOM_BOUND = 3.0f;
	public static final float MENU_WORLD_TOP_BOUND = 9.0f;
	private static final float WORLD_VERTICAL_BOUND_ADJUSTER = 1.5f;
	
	public static PoolManager poolManager;
	
	public Background background = null;
	
	public Hero hero;
	public Vector2 center;
	
	public MenuWorld(PoolManager poolManager){
		MenuWorld.poolManager = poolManager;
		
		hero = new Hero();
		center = hero.center;

		Platform.initializePlatformGround();
	}
	
	public void update(float deltaTime){
		hero.update(deltaTime);
	}
}

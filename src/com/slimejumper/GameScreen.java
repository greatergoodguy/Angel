package com.slimejumper;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.slimejumper.framework.impl.GLScreen;
import com.slimejumper.gameframework.Game;
import com.slimejumper.gameframework.Input.TouchEvent;
import com.slimejumper.gameframework.gl.Camera2D;
import com.slimejumper.gameframework.gl.SpriteBatcher;
import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.levels.CaveLevel;
import com.slimejumper.levels.MenuLevel;
import com.slimejumper.levels.Level;
import com.slimejumper.levels.CaveLevel.WorldListener;
import com.slimejumper.renderer.WorldRenderer;
import com.slimejumper.tools.Remover;
import com.slimejumper.world.Backgrounds;
import com.slimejumper.world.Hero;
import com.slimejumper.world.Platform;


public class GameScreen extends GLScreen {
	
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_LEVEL_END = 3;
	static final int GAME_OVER = 4;
	
	int state;
	float game_timer;
	
	Camera2D guiCam;
	Controller controller;
	SpriteBatcher batcher;
	WorldListener worldListener;
	
	static Level active_world;
	static MenuLevel menuWorld;
	static Level gameWorld;
	
	static WorldRenderer renderer;
	Vector2 touchPoint;

	public GameScreen(Game game) {
		super(game);
		game_timer = 0;
		
		Level.initializeUniverse();
		
		guiCam = new Camera2D(glGraphics, 800, 480);
		controller = new Controller(guiCam);
		batcher = new SpriteBatcher(glGraphics, 500);
		
		worldListener = new WorldListener(){
			public void jump(){
			}
			public void killJump(){
			}
			public void hit(){
			}
			public void coin(){
			}
		};
		
		menuWorld = new MenuLevel();
		gameWorld = new CaveLevel(worldListener);
		active_world = menuWorld;
		
		renderer = new WorldRenderer(glGraphics, batcher, active_world);
		touchPoint = new Vector2();
		
		switchToMenuWorld();
	}
	
	public static void switchToMenuWorld(){
		active_world = menuWorld;
		Backgrounds.setActiveWorld(active_world);
		Platform.initializePlatformGroundMinusOne();
		Platform.initializePlatformMap();
	}

	public static void switchToGameWorld(){
		active_world = gameWorld;
		renderer.resetActiveWorld(active_world);
		Remover.clearAllLists();
		Platform.initializePlatformGround();
		Backgrounds.setActiveWorld(active_world);
	}
	
	boolean firstTime = true;
	
	@Override
	public void update(float deltaTime) {
		game_timer += deltaTime;
		if(deltaTime > 0.1f)
			deltaTime = 0.1f;
		
		updateRunning(deltaTime);

	}

	private void updateRunning(float deltaTime) {
		// Get Controller Input
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		active_world.processController(controller, touchEvents);
	
		// Update World
		active_world.update(deltaTime);
	}

	public void present(float deltaTime) {

		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		
		renderer.render();
		guiCam.setViewportAndMatrices();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

}

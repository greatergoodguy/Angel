package com.slimejumper;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.slimejumper.framework.impl.GLScreen;
import com.slimejumper.gameframework.Game;
import com.slimejumper.gameframework.Input.TouchEvent;
import com.slimejumper.gameframework.gl.Camera2D;
import com.slimejumper.gameframework.gl.SpriteBatcher;
import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.menu.MenuWorld;
import com.slimejumper.tools.Remover;
import com.slimejumper.tools.World;
import com.slimejumper.world.Backgrounds;
import com.slimejumper.world.GameWorld;
import com.slimejumper.world.Hero;
import com.slimejumper.world.Platform;
import com.slimejumper.world.WorldRenderer;
import com.slimejumper.world.GameWorld.WorldListener;


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
	
	static World active_world;
	static MenuWorld menuWorld;
	static World gameWorld;
	
	static WorldRenderer renderer;
	Vector2 touchPoint;

	public GameScreen(Game game) {
		super(game);
		game_timer = 0;
		
		World.initializeUniverse();
		
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
		
		menuWorld = new MenuWorld();
		gameWorld = new GameWorld(worldListener);
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
		
		int len = touchEvents.size();
		for(int i=0; i<len; i++){
			TouchEvent event = touchEvents.get(i);
			controller.input(event);
			if(controller.fireAttack){
				if(World.hero.state != Hero.HERO_STATE_BASIC_ATTACK)
					World.hero.changeToBasicAttackState();
				controller.fireAttack = false;
			}
			World.hero.moveDirection = processMoveDirection();
		}
		
		// Update World
		active_world.update(deltaTime);
	}

	private int processMoveDirection() {		
		if(controller.RightButtonDown && controller.LeftButtonDown){
			if(controller.active_control == Controller.CONTROLLER_LEFT)
				return Hero.HERO_LEFT;
			else if(controller.active_control == Controller.CONTROLLER_RIGHT)
				return Hero.HERO_RIGHT;
			else
				return Hero.HERO_RIGHT;
		}
		else if(controller.RightButtonDown)
			return Hero.HERO_RIGHT;
		else if(controller.LeftButtonDown)
			return Hero.HERO_LEFT;
		else
			return Hero.HERO_NEUTRAL;
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

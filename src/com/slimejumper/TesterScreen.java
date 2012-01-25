package com.slimejumper;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.slimejumper.framework.impl.GLScreen;
import com.slimejumper.gameframework.Game;
import com.slimejumper.gameframework.Input.TouchEvent;
import com.slimejumper.gameframework.gl.Camera2D;
import com.slimejumper.gameframework.gl.SpriteBatcher;
import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.levels.Level.WorldListener;
import com.slimejumper.levels.TesterLevel;
import com.slimejumper.renderer.TesterRenderer;

public class TesterScreen extends GLScreen{
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
	
	static TesterLevel tester_level;
	TesterRenderer tester_renderer;
	
	Vector2 touchPoint;
	
	public TesterScreen(Game game) {
		super(game);
		game_timer = 0;
		
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
		
		tester_level = new TesterLevel(worldListener, controller);		
		tester_renderer = new TesterRenderer(glGraphics, batcher, tester_level, controller);
		touchPoint = new Vector2();
	}
	
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
		float accelY = game.getInput().getAccelY();		
		tester_level.processController(controller, touchEvents, accelY);
	
		// Update World
		tester_level.update(deltaTime);
	}

	public void present(float deltaTime) {

		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		
		tester_renderer.render();
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
		tester_level.dispose();
	}
}

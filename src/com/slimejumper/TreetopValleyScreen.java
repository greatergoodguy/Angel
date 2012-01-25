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
import com.slimejumper.levels.TreetopValleyLevel;
import com.slimejumper.renderer.TreetopValleyRenderer;

public class TreetopValleyScreen extends GLScreen{
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
	
	TreetopValleyLevel treetop_valley_level;
	TreetopValleyRenderer treetop_valley_renderer;
	
	Vector2 touchPoint;
	
	public TreetopValleyScreen(Game game) {
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
		
		treetop_valley_level = new TreetopValleyLevel(worldListener, controller);		
		treetop_valley_renderer = new TreetopValleyRenderer(glGraphics, batcher, treetop_valley_level);
		touchPoint = new Vector2();
	}
	
	@Override
	public void update(float deltaTime) {
		game_timer += deltaTime;
		if(deltaTime > 0.05f)
			deltaTime = 0.05f;
		
		updateRunning(deltaTime);

	}

	private void updateRunning(float deltaTime) {
		// Get Controller Input
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		float accelY = game.getInput().getAccelY();		
		treetop_valley_level.processController(controller, touchEvents, accelY);
	
		// Update World
		treetop_valley_level.update(deltaTime);
	}

	public void present(float deltaTime) {

		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		
		treetop_valley_renderer.render();
		guiCam.setViewportAndMatrices();
	}

	@Override
	public void pause() {
		Assets.test_music.pause();
	}

	@Override
	public void resume() {
		Assets.test_music.play();
	}

	@Override
	public void dispose() {
		treetop_valley_level.dispose();
	}
}

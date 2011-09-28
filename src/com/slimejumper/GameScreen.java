package com.slimejumper;

import java.util.LinkedList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.slimejumper.framework.impl.GLScreen;
import com.slimejumper.gameframework.Game;
import com.slimejumper.gameframework.Input.TouchEvent;
import com.slimejumper.gameframework.gl.Camera2D;
import com.slimejumper.gameframework.gl.SpriteBatcher;
import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.menu.MenuWorld;
import com.slimejumper.tools.PoolManager;
import com.slimejumper.tools.World;
import com.slimejumper.world.Hero;
import com.slimejumper.world.Platform;
import com.slimejumper.world.GameWorld;
import com.slimejumper.world.WorldRenderer;
import com.slimejumper.world.GameWorld.WorldListener;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.attacks.MusicNote;
import com.slimejumper.world.attacks.Shockball;
import com.slimejumper.world.enemies.Enemy;
import com.slimejumper.world.enemies.FlyingSnake;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.enemies.PurpleGhost;


public class GameScreen extends GLScreen {
	
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_LEVEL_END = 3;
	static final int GAME_OVER = 4;
	
	int state;
	
	Camera2D guiCam;
	Controller controller;
	SpriteBatcher batcher;
	WorldListener worldListener;
	
	PoolManager poolManager;
	MenuWorld menuWorld;
	GameWorld world;
	WorldRenderer renderer;
	Vector2 touchPoint;

	public GameScreen(Game game) {
		super(game);

		World.initalizeUniverse();
		
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
		
		poolManager = new PoolManager();
		
//		menuWorld = new MenuWorld(poolManager);
		world = new GameWorld(worldListener, poolManager);
		
		renderer = new WorldRenderer(glGraphics, batcher, world);
		touchPoint = new Vector2();
	}

	private void initalizeUniverse() {
		Enemy.sample_enemies = new LinkedList<Enemy>();
		PurpleGhost.purple_ghosts = new LinkedList<PurpleGhost>();
		JellyfishDemon.jellyfish_demons = new LinkedList<JellyfishDemon>();
		FlyingSnake.flying_snakes = new LinkedList<FlyingSnake>();
		
		Platform.static_platforms = new LinkedList<Platform>();
		Platform.volatile_platforms = new LinkedList<Platform>();
		Platform.ground_platforms = new LinkedList<Platform>();
		
		HaloAttack.halo_attacks = new LinkedList<HaloAttack>();
		MusicNote.music_notes = new LinkedList<MusicNote>();
		Shockball.shockballs = new LinkedList<Shockball>();	
		
	}

	@Override
	public void update(float deltaTime) {
		if(deltaTime > 0.1f)
			deltaTime = 0.1f;
		
		updateRunning(deltaTime);

	}

	private void updateRunning(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		for(int i=0; i<len; i++){
			TouchEvent event = touchEvents.get(i);
			controller.input(event);
			if(controller.fireAttack){
				if(world.hero.state != Hero.HERO_STATE_BASIC_ATTACK)
					world.hero.changeToBasicAttackState();
				controller.fireAttack = false;
			}
			world.hero.moveDirection = processMoveDirection();
		}
		
		world.update(deltaTime);
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

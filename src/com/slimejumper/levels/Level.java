package com.slimejumper.levels;
import java.util.List;

import com.slimejumper.Controller;
import com.slimejumper.gameframework.Input.TouchEvent;
import com.slimejumper.gameframework.math.UnitCircle;
import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.renderer.BaseRenderer;
import com.slimejumper.tools.CollisionManager;
import com.slimejumper.tools.Remover;
import com.slimejumper.tools.SpriteContainer;
import com.slimejumper.tools.SpriteManager;
import com.slimejumper.world.Backgrounds;
import com.slimejumper.world.Hero;
import com.slimejumper.world.Platform;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.attacks.MusicNote;
import com.slimejumper.world.attacks.Shockball;
import com.slimejumper.world.attacks.SpiralAttack;
import com.slimejumper.world.enemies.FlyingSnake;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.enemies.PurpleGhost;

public abstract class Level {

	public interface WorldListener {
		public void jump();

		public void killJump();

		public void hit();

		public void coin();
	}
	
	public static final float METER = 80; // 1 meter equals 80 pixels

	public static final float WORLD_WIDTH = 10 * 2; // 10 refers to the visible
													// width
	public static final float WORLD_HEIGHT = 6 * 2; // 6 refers to the visible
													// height

	public static final float WORLD_CENTER_DEFAULT_X = BaseRenderer.BASE_RENDERER_FRUSTUM_WIDTH / 2;
	public static final float WORLD_CENTER_DEFAULT_Y = BaseRenderer.BASE_RENDERER_FRUSTUM_HEIGHT / 2;
	
	public static final float WORLD_GRAVITY = -8.5f;
	public static final float WORLD_GRAVITY_TIMES_TWO = WORLD_GRAVITY * 2;
	
	
	public static final float WORLD_LEFT_EDGE = 0f;
	public static final float WORLD_RIGHT_EDGE = 20.0f;
	public static final float WORLD_BOTTOM_EDGE = 0f;
	public static final float WORLD_TOP_EDGE = 12.0f;

	public static final float WORLD_LEFT_BOUND = 5.0f;
	public static final float WORLD_RIGHT_BOUND = 15.0f;
	public static final float WORLD_BOTTOM_BOUND = 3.0f;
	public static final float WORLD_TOP_BOUND = 9.0f;
	private static final float WORLD_VERTICAL_BOUND_ADJUSTER = 1.5f;

	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;

	public static final float COLLISION_TOLERANCE = 0.1f;
	
	public Vector2 center;
	public Vector2 position;
	
	public float width;
	public float height;


	public final WorldListener listener;
	public final SpriteManager sprite_manager;
	
	
	public Level(WorldListener listener, SpriteManager sprite_manager){
		this.listener = listener;
		this.sprite_manager = sprite_manager;
		
		initializeUniverse();
		UnitCircle.initializeUnitCircle();
	}
	
	public static void initializeUniverse(){
		CollisionManager.setCollidingHero(SpriteContainer.hero);
		Backgrounds.initalizeParameters();
		
	}

	public void update(float deltaTime) {

		updateSprites(deltaTime);
		CollisionManager.manageCollisions();
		Remover.remove();

		updateCenter();
		updatePosition();		
	}

	private void updateSprites(float deltaTime) {
		Backgrounds.update();
		updatePlatforms(deltaTime);
		updateEnemies(deltaTime);
		SpriteContainer.hero.update(deltaTime);
		updateAttacks(deltaTime);
	}

	private static void updatePlatforms(float deltaTime) {
		for(Platform platform : SpriteContainer.static_platforms)
			platform.update(deltaTime);
		for (Platform platform : SpriteContainer.volatile_platforms)
			platform.update(deltaTime);
	}

	private static void updateEnemies(float deltaTime) {
		for (PurpleGhost purple_ghost : SpriteContainer.purple_ghosts)
			purple_ghost.update(deltaTime);
		for(JellyfishDemon jellyfish_demon : SpriteContainer.jellyfish_demons)
			jellyfish_demon.update(deltaTime);
		for(FlyingSnake flying_snake : SpriteContainer.flying_snakes)
			flying_snake.update(deltaTime);
	}
	
	private void updateAttacks(float deltaTime) {
		for(HaloAttack halo_attack : SpriteContainer.halo_attacks)
			halo_attack.update(deltaTime);
		for(MusicNote music_note : SpriteContainer.music_notes)
			music_note.update(SpriteContainer.hero, deltaTime);
		for(Shockball shockball : SpriteContainer.shockballs)
			shockball.update(deltaTime);
		for(SpiralAttack spiral_attack : SpriteContainer.spiral_attacks)
			spiral_attack.update(deltaTime);
	}

	
	private void updateCenter() {
		// Checks Horizontal Bounds
		if (SpriteContainer.hero.center.x < WORLD_LEFT_BOUND)
			center.x = WORLD_LEFT_BOUND;
		else if (SpriteContainer.hero.center.x > WORLD_RIGHT_BOUND)
			center.x = WORLD_RIGHT_BOUND;
		else
			center.x = SpriteContainer.hero.center.x;
		
		// Checks Vertical Bound
		if(SpriteContainer.hero.center.y < WORLD_BOTTOM_BOUND + WORLD_VERTICAL_BOUND_ADJUSTER)
			center.y = WORLD_BOTTOM_BOUND;
		else if(SpriteContainer.hero.center.y > WORLD_TOP_BOUND)
			center.y = WORLD_TOP_BOUND - WORLD_VERTICAL_BOUND_ADJUSTER;
		else
			center.y = SpriteContainer.hero.center.y - WORLD_VERTICAL_BOUND_ADJUSTER;
	}

	private void updatePosition() {
		position.x = center.x - WORLD_CENTER_DEFAULT_X;
		position.y = center.y - WORLD_CENTER_DEFAULT_Y;

		if (position.x > WORLD_RIGHT_BOUND - WORLD_LEFT_BOUND)
			position.x = WORLD_RIGHT_BOUND - WORLD_LEFT_BOUND;
	}

	public void processController(Controller controller, List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for(int i=0; i<len; i++){
			TouchEvent event = touchEvents.get(i);
			controller.input(event);
			if(controller.fireAttack){
				if(SpriteContainer.hero.state != Hero.HERO_STATE_BASIC_ATTACK)
					SpriteContainer.hero.changeToBasicAttackState();
				controller.fireAttack = false;
			}
			SpriteContainer.hero.moveDirection = Controller.processMoveDirection(controller);
		}
		
	}
}
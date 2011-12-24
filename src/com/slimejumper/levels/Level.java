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
import com.slimejumper.world.Hero;
import com.slimejumper.world.GreekPlatform;
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

	public static final float WORLD_CENTER_DEFAULT_X = BaseRenderer.BASE_RENDERER_FRUSTUM_WIDTH / 2;
	public static final float WORLD_CENTER_DEFAULT_Y = BaseRenderer.BASE_RENDERER_FRUSTUM_HEIGHT / 2;
	
	public static final float WORLD_GRAVITY = -8.5f;
	public static final float WORLD_GRAVITY_TIMES_TWO = WORLD_GRAVITY * 2;
	
	public static final float WORLD_DEFAULT_WIDTH = 20;
	public static final float WORLD_DEFAULT_HEIGHT = 13.5f;
	
/*	
	public static final float WORLD_DEFAULT_LEFT_EDGE = 0f;
	public static final float WORLD_DEFAULT_RIGHT_EDGE = 20.0f;
	public static final float WORLD_DEFAULT_BOTTOM_EDGE = 0f;
	public static final float WORLD_DEFAULT_TOP_EDGE = 12.0f;
*/

	public static final float WORLD_HORIZONTAL_BOUND_ADJUSTER = 5.0f;
	public static final float WORLD_VERTICAL_BOUND_ADJUSTER = 3.0f;
/*
	public static final float WORLD_DEFAULT_LEFT_BOUND = 5.0f;
	public static final float WORLD_DEFAULT_RIGHT_BOUND = 15.0f;
	public static final float WORLD_DEFAULT_BOTTOM_BOUND = 3.0f;
	public static final float WORLD_DEFAULT_TOP_BOUND = 9.0f;
*/
	
	private static final float WORLD_VERTICAL_POSITIONING_ADJUSTER = 1.5f;

	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;

	public static final float COLLISION_TOLERANCE = 0.1f;
	
	public Vector2 center;
	public Vector2 position;
	
	public float world_width;
	public float world_height;
	
	public float world_left_bound;
	public float world_right_bound;
	public float world_bottom_bound;
	public float world_top_bound;

	public final WorldListener listener;
	public final SpriteManager sprite_manager;
	public final Controller controller;
	
	
	public Level(WorldListener listener, SpriteManager sprite_manager, Controller controller){
		this.listener = listener;
		this.sprite_manager = sprite_manager;
		this.controller = controller;

		UnitCircle.initializeUnitCircle();
		
		center = new Vector2(WORLD_CENTER_DEFAULT_X, WORLD_CENTER_DEFAULT_Y);
		position = new Vector2();
		
		world_width = WORLD_DEFAULT_WIDTH;
		world_height = WORLD_DEFAULT_HEIGHT;
		
		world_left_bound = WORLD_HORIZONTAL_BOUND_ADJUSTER;
		world_right_bound = world_width - WORLD_HORIZONTAL_BOUND_ADJUSTER;
		world_bottom_bound = WORLD_VERTICAL_BOUND_ADJUSTER;
		world_top_bound = world_height - WORLD_VERTICAL_BOUND_ADJUSTER;
	}

	public Level(WorldListener listener, SpriteManager sprite_manager, Controller controller,
			float world_width, float world_height){
		this.listener = listener;
		this.sprite_manager = sprite_manager;
		this.controller = controller;

		UnitCircle.initializeUnitCircle();
		
		center = new Vector2(WORLD_CENTER_DEFAULT_X, WORLD_CENTER_DEFAULT_Y);
		position = new Vector2();
		
		this.world_width = world_width;
		this.world_height = world_height;
		
		world_left_bound = WORLD_HORIZONTAL_BOUND_ADJUSTER;
		world_right_bound = world_width - WORLD_HORIZONTAL_BOUND_ADJUSTER;
		world_bottom_bound = WORLD_VERTICAL_BOUND_ADJUSTER;
		world_top_bound = world_height - WORLD_VERTICAL_BOUND_ADJUSTER;
	}
	
	public void update(float deltaTime) {

		updateSprites(deltaTime);
		CollisionManager.manageCollisions();
		Remover.remove();

		updateCenter();
		updatePosition();		
	}

	private void updateSprites(float deltaTime) {
		updatePlatforms(deltaTime);
		updateEnemies(deltaTime);
		updateAttacks(deltaTime);
		
		updateHero(deltaTime);
		updateShadowHero(deltaTime);
	}

	private static void updatePlatforms(float deltaTime) {
		for(GreekPlatform platform : SpriteContainer.static_platforms)
			platform.update(deltaTime);
		for (GreekPlatform platform : SpriteContainer.volatile_platforms)
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
				
		for(Shockball shockball : SpriteContainer.shockballs)
			shockball.update(deltaTime);
	}

	private void updateHero(float deltaTime) {
		/*
		 * Controller Input
		 */
		
		if(controller.fireAttack){
			if(SpriteContainer.hero.state != Hero.HERO_STATE_BASIC_ATTACK)
				SpriteContainer.hero.changeToBasicAttackState();
			
			controller.fireAttack = SpriteContainer.shadow_hero.controller_sync_on;
		}
		
		switch(Controller.processMoveDirection(controller)){
		case Controller.CONTROLLER_LEFT:
			SpriteContainer.hero.moveLeft();
			break;
		case Controller.CONTROLLER_RIGHT:
			SpriteContainer.hero.moveRight();
			break;
		case Controller.CONTROLLER_NEUTRAL:
			SpriteContainer.hero.moveCancel();
			break;
		}
		
		/*
		 * Update
		 */
		
		SpriteContainer.hero.update(deltaTime);
		
		/*
		 * Check Bounds
		 */
		
		SpriteContainer.hero.checkSideBounds(this);
	}
	
	private void updateShadowHero(float deltaTime) {
		/*
		 * Sync Ability
		 */
		
		if(SpriteContainer.shadow_hero.controller_sync_on){
			
			if(controller.fireAttack){
				if(SpriteContainer.shadow_hero.state != Hero.HERO_STATE_BASIC_ATTACK)
					SpriteContainer.shadow_hero.changeToBasicAttackState();
				controller.fireAttack = false;
			}
			
			switch(Controller.processMoveDirection(controller)){
			case Controller.CONTROLLER_LEFT:
				SpriteContainer.shadow_hero.moveLeft();
				break;
			case Controller.CONTROLLER_RIGHT:
				SpriteContainer.shadow_hero.moveRight();
				break;
			case Controller.CONTROLLER_NEUTRAL:
				SpriteContainer.shadow_hero.moveCancel();
				break;
			}
		}
		/*
		 * Update
		 */
		
		SpriteContainer.shadow_hero.update(deltaTime);
		
		/*
		 * Check Bounds
		 */
		
		SpriteContainer.shadow_hero.checkSideBounds(this);
	}
	
	private void updateCenter() {
		// Checks Horizontal Bounds
		if (SpriteContainer.hero.center.x < world_left_bound)
			center.x = world_left_bound;
		else if (SpriteContainer.hero.center.x > world_right_bound)
			center.x = world_right_bound;
		else
			center.x = SpriteContainer.hero.center.x;
		
		// Checks Vertical Bound
		if(SpriteContainer.hero.center.y < world_bottom_bound + WORLD_VERTICAL_POSITIONING_ADJUSTER)
			center.y = world_bottom_bound;
		else if(SpriteContainer.hero.center.y > world_top_bound)
			center.y = world_top_bound - WORLD_VERTICAL_POSITIONING_ADJUSTER;
		else
			center.y = SpriteContainer.hero.center.y - WORLD_VERTICAL_POSITIONING_ADJUSTER;
	}

	private void updatePosition() {
		position.x = center.x - WORLD_CENTER_DEFAULT_X;
		position.y = center.y - WORLD_CENTER_DEFAULT_Y;

		if (position.x > world_right_bound - world_left_bound)
			position.x = world_right_bound - world_left_bound;
	}

	public void processController(Controller controller, List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for(int i=0; i<len; i++){
			TouchEvent event = touchEvents.get(i);
			controller.input(event);
/*			
			if(controller.fireAttack){
				if(SpriteContainer.hero.state != Hero.HERO_STATE_BASIC_ATTACK)
					SpriteContainer.hero.changeToBasicAttackState();
				controller.fireAttack = false;
			}
*/

/*			
			SpriteContainer.hero.moveDirection = Controller.processMoveDirection(controller);
*/			
		}
		
	}
}
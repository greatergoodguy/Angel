package com.slimejumper.levels;
import java.util.List;

import com.slimejumper.Controller;
import com.slimejumper.gameframework.Input.TouchEvent;
import com.slimejumper.gameframework.math.UnitCircle;
import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.renderer.BaseRenderer;
import com.slimejumper.tools.PoolManager;
import com.slimejumper.world.Hero;
import com.slimejumper.world.ShadowHero;

public abstract class Level {

	public PoolManager pool_manager = PoolManager.pool_manager_singleton;
	public Hero hero = Hero.hero_singleton;
	
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
	public final Controller controller;
	
	
	public Level(WorldListener listener, Controller controller){
		this.listener = listener;
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

	public Level(WorldListener listener, Controller controller,
			float world_width, float world_height){
		this.listener = listener;
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
		updateCenter();
		updatePosition();		
	}


	protected void updateHero(float deltaTime) {
		/*
		 * Controller Input
		 */
		
		if(controller.fireAttack){
			if(hero.state != Hero.HERO_STATE_BASIC_ATTACK)
				hero.changeToBasicAttackState();
			
			controller.fireAttack = ShadowHero.shadow_hero_singleton.controller_sync_on;
		}

		if(hero.state != Hero.HERO_STATE_COLLIDED){
			switch(Controller.processMoveDirection(controller)){
			case Controller.CONTROLLER_LEFT:
				hero.moveLeft();
				break;
			case Controller.CONTROLLER_RIGHT:
				hero.moveRight();
				break;
			case Controller.CONTROLLER_NEUTRAL:
				hero.moveCancel();
				break;
			}
		}
		
		/*
		 * Update
		 */
		
		hero.update(deltaTime);
		
		/*
		 * Check Bounds
		 */
		
		hero.checkSideBounds(this);
	}
	
	private void updateShadowHero(float deltaTime) {
		/*
		 * Sync Ability
		 */
		
		if(ShadowHero.shadow_hero_singleton.controller_sync_on){
			
			if(controller.fireAttack){
				if(ShadowHero.shadow_hero_singleton.state != Hero.HERO_STATE_BASIC_ATTACK)
					ShadowHero.shadow_hero_singleton.changeToBasicAttackState();
				controller.fireAttack = false;
			}
			
			switch(Controller.processMoveDirection(controller)){
			case Controller.CONTROLLER_LEFT:
				ShadowHero.shadow_hero_singleton.moveLeft();
				break;
			case Controller.CONTROLLER_RIGHT:
				ShadowHero.shadow_hero_singleton.moveRight();
				break;
			case Controller.CONTROLLER_NEUTRAL:
				ShadowHero.shadow_hero_singleton.moveCancel();
				break;
			}
		}
		/*
		 * Update
		 */
		
		ShadowHero.shadow_hero_singleton.update(deltaTime);
		
		/*
		 * Check Bounds
		 */
		
		ShadowHero.shadow_hero_singleton.checkSideBounds(this);
	}
	
	protected void updateCenter() {
		// Checks Horizontal Bounds
		if (Hero.hero_singleton.center.x < world_left_bound)
			center.x = world_left_bound;
		else if (Hero.hero_singleton.center.x > world_right_bound)
			center.x = world_right_bound;
		else
			center.x = Hero.hero_singleton.center.x;
		
		// Checks Vertical Bound
		if(Hero.hero_singleton.center.y < world_bottom_bound + WORLD_VERTICAL_POSITIONING_ADJUSTER)
			center.y = world_bottom_bound;
		else if(Hero.hero_singleton.center.y > world_top_bound)
			center.y = world_top_bound - WORLD_VERTICAL_POSITIONING_ADJUSTER;
		else
			center.y = Hero.hero_singleton.center.y - WORLD_VERTICAL_POSITIONING_ADJUSTER;
	}

	protected void updatePosition() {
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
	
	public abstract void dispose();
}
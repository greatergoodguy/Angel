package com.slimejumper.levels;

import java.util.LinkedList;
import java.util.List;

import com.slimejumper.Assets;
import com.slimejumper.Controller;
import com.slimejumper.Settings;
import com.slimejumper.gameframework.Input.TouchEvent;
import com.slimejumper.tools.CollisionManager;
import com.slimejumper.tools.TreetopValleyFactory;
import com.slimejumper.world.Background;
import com.slimejumper.world.Hero;
import com.slimejumper.world.attacks.PurpleFlame;
import com.slimejumper.world.enemies.PurpleGhost;
import com.slimejumper.world.enemies.RedWhaleDemon;
import com.slimejumper.world.environment.CloudPlatform;
import com.slimejumper.world.environment.CloudPlatformShort;

public class TreetopValleyLevel extends Level{

	Hero hero = Hero.hero_singleton;
	
	private static final float BACKGROUND_TREES_WIDTH = 25.6f;
	private static final float BACKGROUND_TREES_HEIGHT = 12.8f;
	private static final float TREETOP_VALLEY_WIDTH = 40f;
	private static final float TREETOP_VALLEY_HEIGHT = 24f;
	
	TreetopValleyFactory treetop_valley_factory;
	
	public Background background_trees;

	public LinkedList<CloudPlatformShort> cloud_platforms_short;
	public LinkedList<CloudPlatform> cloud_platforms;
	
	public LinkedList<RedWhaleDemon> red_whale_demons;
	public LinkedList<PurpleGhost> purple_ghosts;
	public LinkedList<PurpleFlame> purple_flames;
	
	boolean level_one_activate;
	boolean level_two_activate;
	
	public TreetopValleyLevel(WorldListener listener, Controller controller){
		super(listener, controller, TREETOP_VALLEY_WIDTH, TREETOP_VALLEY_HEIGHT);
		//hero.reset(9, 12);
		hero.reset(2.4f, 3);
		//hero.reset(27, 3);
		
		background_trees = new Background(BACKGROUND_TREES_WIDTH, BACKGROUND_TREES_HEIGHT, TREETOP_VALLEY_WIDTH, TREETOP_VALLEY_HEIGHT);//Level.WORLD_DEFAULT_HEIGHT);
		
		cloud_platforms_short = new LinkedList<CloudPlatformShort>();
		cloud_platforms = new LinkedList<CloudPlatform>();
		red_whale_demons = new LinkedList<RedWhaleDemon>();
		purple_ghosts = new LinkedList<PurpleGhost>();
		purple_flames = new LinkedList<PurpleFlame>();
		PurpleGhost.registerPurpleFlames(purple_flames);
		treetop_valley_factory = new TreetopValleyFactory(this);
		
		//treetop_valley_factory.createTackleRedWhaleDemonRight(14, 1.4f);
		//initializeRedWhaleDemons();
		//initializePurpleGhostsAndPurpleFlames();
		
		//initializeCloudPlatformsShort();
		//initializeCloudPlatforms();
		
		initializeTreetopValley();
		
		if(Settings.soundEnabled)
        	Assets.test_music.play();
	}

	private void initializeTreetopValley() {
		treetop_valley_factory.initializePart1();
		treetop_valley_factory.initializePart2();
		treetop_valley_factory.initializePart3();
		treetop_valley_factory.initializePart4();
	}
	
	private void initializeCloudPlatformsShort() {				
		treetop_valley_factory.createStaticCloudPlatformShort(38.5f, 2.5f);
		treetop_valley_factory.createStaticCloudPlatformShort(35.7f, 3.8f);
		treetop_valley_factory.createStaticCloudPlatformShort(38.5f, 5.5f);
		treetop_valley_factory.createStaticCloudPlatformShort(36.2f, 7f);
		treetop_valley_factory.createStaticCloudPlatformShort(39f, 8.4f);
		treetop_valley_factory.createStaticCloudPlatformShort(36.7f, 10f);
		treetop_valley_factory.createStaticCloudPlatformShort(38.5f, 11.3f);
		
		treetop_valley_factory.createStaticCloudPlatformShort(30.8f, 9.2f);
		treetop_valley_factory.createStaticCloudPlatformShort(26.8f, 9.2f);
	}

	private void initializeCloudPlatforms() {				
		treetop_valley_factory.createOscillateHorizontalCloudPlatform(26, 0.2f);
		treetop_valley_factory.createStaticCloudPlatform(33, 0.6f);
		treetop_valley_factory.createStaticCloudPlatform(35, 0.5f);
		//treetop_valley_factory.createOscillateVerticalCloudPlatformCentered(36.5f, 5.5f, 2.4f);
		
		treetop_valley_factory.createStaticCloudPlatform(33, 9.2f);
		//treetop_valley_factory.createStaticCloudPlatform(30.8f, 9.2f);
		treetop_valley_factory.createStaticCloudPlatform(27, 7.3f);
		treetop_valley_factory.createStaticCloudPlatform(25, 7.5f);
		
		treetop_valley_factory.createOscillateVerticalCloudPlatformCentered(21.0f, 9.1f, -CloudPlatform.CLOUD_PLATFORM_OSCILLATE_VERTICAL_DEFAULT_VEL);
		treetop_valley_factory.createOscillateVerticalCloudPlatformCentered(15.0f, 9.1f, CloudPlatform.CLOUD_PLATFORM_OSCILLATE_VERTICAL_DEFAULT_VEL);
		
		treetop_valley_factory.createStaticCloudPlatform(0.4f, 8.1f);
		treetop_valley_factory.createStaticCloudPlatform(3.5f, 8.3f);
		treetop_valley_factory.createStaticCloudPlatform(6.5f, 8.3f);
		treetop_valley_factory.createStaticCloudPlatform(9.5f, 8.3f);
		
		treetop_valley_factory.createOscillateVerticalCloudPlatformCentered(0.5f, 12f, 2.4f);
		
		treetop_valley_factory.createStaticCloudPlatform(3.5f, 17f);
		treetop_valley_factory.createStaticCloudPlatform(7.0f, 16f);
		treetop_valley_factory.createStaticCloudPlatform(10.5f, 15f);
	}
	
	private void initializeRedWhaleDemons() {		
		treetop_valley_factory.createStillRedWhaleDemonLeft(9.6f, 4.3f);
		//treetop_valley_factory.createFloatRedWhaleDemon(16.5f, 2.3f, 1.0f);
		
		//treetop_valley_factory.createFloatRedWhaleDemon(38.0f, 2.5f, 0f);
		
		treetop_valley_factory.createTackleRedWhaleDemonRight(23.7f, 7.9f);
		
		//treetop_valley_factory.createStillRedWhaleDemonRight(18.8f, 9.9f);
		
		treetop_valley_factory.createTackleRedWhaleDemonRight(0.7f, 8.5f);
	}

	private void initializePurpleGhostsAndPurpleFlames() {
		treetop_valley_factory.createPurpleGhostStandardPatternLeft(24.5f, 4.2f);
		treetop_valley_factory.createPurpleGhostStandardPatternRight(18.8f, 10.3f);
		
		treetop_valley_factory.createPurpleGhostWalkPatternRight(7.1f, 8.5f);
		
		treetop_valley_factory.createPurpleGhostFloatVerticalPatternLeft(10.3f, 15.5f);
	}
	

	public void update(float deltaTime){
		/*
		 * Update
		 */		
		updateHero(deltaTime);
		
		for(CloudPlatform cloud_platform : cloud_platforms)
			cloud_platform.update(deltaTime);
		for(RedWhaleDemon red_whale_demon : red_whale_demons)
			red_whale_demon.update(deltaTime);
		for(PurpleGhost purple_ghost : purple_ghosts)
			purple_ghost.update(deltaTime);
		for(PurpleFlame purple_flame : purple_flames){
			purple_flame.update(deltaTime);
		}
		
		/*
		 * Remove Unnecessary
		 */
		
		if(!purple_flames.isEmpty()){
			PurpleFlame purple_flame = purple_flames.getFirst();
			if(purple_flame.life_timer > PurpleFlame.PURPLE_FLAME_LIFESPAN){
				purple_flames.removeFirst();
				pool_manager.purple_flame_pool.free(purple_flame);
			}
		}
		
		/*
		 * Collision
		 */
		manageCollisions();
		
		/*
		 * Events
		 */
				
		// Hero falls down a pit
		if(hero.position.y < world_bottom_edge - 1.0f){
			//hero.resetPositionLowerLeft(hero.position.x, 11);
			hero.changeToDeathByFallingState();
			hero.resetPositionLowerLeft(hero.position.x, world_bottom_edge - 1.0f);
			
			// Glitch Handler
		}
		
		
		// Second Level
		if(!level_one_activate && hero.center.y > 19.6f){
			world_bottom_edge = 15f;
			world_bottom_bound = world_bottom_edge + 3.0f;

			treetop_valley_factory.activateLevelTwo();
			level_one_activate = true;
		}
		/*
		// Third Level
		if(!level_two_activate && hero.center.y > 19f){
			world_bottom_edge = 14.5f;
			world_bottom_bound = world_bottom_edge + 3.0f;
			
			treetop_valley_factory.createStaticCloudPlatform(0.2f, 16.6f);
			level_two_activate = true;
		}
		*/
		
		/*
		if(ShadowHero.shadow_hero_singleton.position.y < 0.0f){
			ShadowHero.shadow_hero_singleton.resetPositionLowerLeft(2, 11);
			// Glitch Handler
		}
		*/
		
		updateCenter();
		updatePosition();
	}

	private void manageCollisions() {
		/*
		 * Hero against Platform
		 */
		CollisionManager.HeroPlatformListCollision(hero, cloud_platforms);
		CollisionManager.HeroPlatformListCollision(hero, cloud_platforms_short);
//		CollisionManager.HeroPlatformCollision(SpriteContainer.shadow_hero, rock_platforms);
		
		/*
		 * Hero Attacks against Enemy
		 */
		CollisionManager.HeroAttacksEnemyListCollision(hero, red_whale_demons, 0.15f);
		CollisionManager.HeroAttacksEnemyListCollision(hero, purple_ghosts, 0.15f);
		
		/*
		 * Hero against Enemy
		 */
		CollisionManager.HeroEnemyListCollision(hero, red_whale_demons, 0.22f);
		CollisionManager.HeroEnemyListCollision(hero, purple_ghosts, 0.22f);
		
		/*
		 * Enemy Attack against Hero
		 */
		CollisionManager.EnemyAttacksHeroCollision(purple_flames, hero, 0.15f);
//		for(PurpleGhost purple_ghost : purple_ghosts)
//			CollisionManager.EnemyAttackHeroCollision(purple_ghost.purple_flame, hero);
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
	
	public void dispose() {		
		hero.dispose();
		
		while(!cloud_platforms.isEmpty()){
			CloudPlatform cloud_platform = cloud_platforms.removeFirst();
			pool_manager.cloud_platform_pool.free(cloud_platform);
		}
		
		while(!cloud_platforms_short.isEmpty()){
			CloudPlatformShort cloud_platform_short = cloud_platforms_short.removeFirst();
			pool_manager.cloud_platform_short_pool.free(cloud_platform_short);
		}
		
		while(!red_whale_demons.isEmpty()){
			RedWhaleDemon red_whale_demon = red_whale_demons.removeFirst();
			pool_manager.red_whale_demon_pool.free(red_whale_demon);
		}
		
		while(!purple_ghosts.isEmpty()){
			PurpleGhost purple_ghost = purple_ghosts.removeFirst();
			pool_manager.purple_ghost_pool.free(purple_ghost);
			PurpleGhost.deregisterPurpleFlames();
		}
		
		while(!purple_flames.isEmpty()){
			PurpleFlame purple_flame = purple_flames.removeFirst();
			pool_manager.purple_flame_pool.free(purple_flame);
		}
		
		Assets.test_music.dispose();
	}
}

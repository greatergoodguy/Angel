package com.slimejumper.levels;

import java.util.LinkedList;

import com.slimejumper.Assets;
import com.slimejumper.Controller;
import com.slimejumper.Settings;
import com.slimejumper.tools.CollisionManager;
import com.slimejumper.tools.TreetopValleyFactory;
import com.slimejumper.world.Background;
import com.slimejumper.world.Hero;
import com.slimejumper.world.ShadowHero;
import com.slimejumper.world.attacks.PurpleFlame;
import com.slimejumper.world.enemies.PurpleGhost;
import com.slimejumper.world.enemies.RedWhaleDemon;
import com.slimejumper.world.environment.CloudPlatform;

public class TreetopValleyLevel extends Level{

	private static final float BACKGROUND_TREES_WIDTH = 25.6f;
	private static final float BACKGROUND_TREES_HEIGHT = 12.8f;
	private static final float TREETOP_VALLEY_WIDTH = 40f;
	private static final float TREETOP_VALLEY_HEIGHT = 30f;
	
	TreetopValleyFactory treetop_valley_factory;
	
	public Background background_trees;

	public LinkedList<CloudPlatform> cloud_platforms;
	public LinkedList<RedWhaleDemon> red_whale_demons;
	public LinkedList<PurpleGhost> purple_ghosts;
	public LinkedList<PurpleFlame> purple_flames;
	
	Hero hero = Hero.hero_singleton;
	
	public TreetopValleyLevel(WorldListener listener, Controller controller){
		super(listener, controller, TREETOP_VALLEY_WIDTH, TREETOP_VALLEY_HEIGHT);
		hero.reset(34, 10);
		//hero.reset(3, 3);
		
		treetop_valley_factory = new TreetopValleyFactory();
		background_trees = new Background(BACKGROUND_TREES_WIDTH, BACKGROUND_TREES_HEIGHT, TREETOP_VALLEY_WIDTH, TREETOP_VALLEY_HEIGHT);//Level.WORLD_DEFAULT_HEIGHT);

		initializeCloudPlatforms();
		initializeRedWhaleDemons();
		initializePurpleGhostsAndPurpleFlames();
		
		if(Settings.soundEnabled)
        	Assets.test_music.play();
	}

	private void initializeCloudPlatforms() {
		cloud_platforms = new LinkedList<CloudPlatform>();

		/*
		 * Test Platforms
		 */
		
//		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(3, 3.5f));
//		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(3, 6.5f));
//		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(0, 8.5f));
//		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(3, 10.5f));
//		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(0, 12.5f));
		
		/////////////////
		
		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(0.4f, 5.5f));
		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(-1.9f, 1.9f));
		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(4.8f, 2.1f));
		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(0, 0));
		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(4, -0.2f));
		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(7.5f, 0.3f));
		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(11f, 0.0f));
		
		
		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(17, -0.2f));
		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(20, 0.3f));
		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(22.3f, 0.2f));
		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(21.6f, 2.6f));
		
		cloud_platforms.add(treetop_valley_factory.createOscillateHorizontalCloudPlatform(26, 0.2f));
		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(33, 0.6f));
		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(35, 0.5f));
		cloud_platforms.add(treetop_valley_factory.createOscillateVerticalCloudPlatformCentered(36.5f, 3.3f, 1.7f));
		
		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(33, 7.0f));
		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(29, 6.8f));
		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(28, 7.3f));
		
		cloud_platforms.add(treetop_valley_factory.createOscillateVerticalCloudPlatformCentered(23.5f, 8.0f, -CloudPlatform.CLOUD_PLATFORM_OSCILLATE_VERTICAL_DEFAULT_VEL));
		cloud_platforms.add(treetop_valley_factory.createOscillateVerticalCloudPlatformCentered(18.0f, 8.0f, CloudPlatform.CLOUD_PLATFORM_OSCILLATE_VERTICAL_DEFAULT_VEL));
		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(12.5f, 7.7f));
		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(9.5f, 7.7f));
		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(6.5f, 7.7f));
		cloud_platforms.add(treetop_valley_factory.createStaticCloudPlatform(3.5f, 7.7f));
	}
	
	private void initializeRedWhaleDemons() {
		red_whale_demons = new LinkedList<RedWhaleDemon>();
		
		red_whale_demons.add(treetop_valley_factory.createStillRedWhaleDemonLeft(9.6f, 4.3f));
		red_whale_demons.add(treetop_valley_factory.createFloatRedWhaleDemon(16.5f, 2.3f, 1.0f));
		red_whale_demons.add(treetop_valley_factory.createFloatRedWhaleDemon(35.0f, 4.0f, 2.5f));
		
		red_whale_demons.add(treetop_valley_factory.createTackleRedWhaleDemonRight(26.5f, 8.4f));
		red_whale_demons.add(treetop_valley_factory.createStillRedWhaleDemonRight(21.8f, 7.9f));
		
		red_whale_demons.add(treetop_valley_factory.createTackleRedWhaleDemonRight(3.1f, 9.0f));
	}

	private void initializePurpleGhostsAndPurpleFlames() {
		purple_ghosts = new LinkedList<PurpleGhost>();
		
		purple_ghosts.add(treetop_valley_factory.createPurpleGhostStandardPatternLeft(24.5f, 4.9f));
		purple_ghosts.add(treetop_valley_factory.createPurpleGhostStandardPatternRight(33.8f, 5.3f, 1.6f));
		
		purple_ghosts.add(treetop_valley_factory.createPurpleGhostStandardPatternRight(21.8f, 9.1f));
		
		purple_ghosts.add(treetop_valley_factory.createPurpleGhostWalkPatternRight(9.5f, 9.1f));
		
//		purple_ghosts.add(treetop_valley_factory.createPurpleGhostStandardPatternLeft(25, 5.2f));
//		purple_ghosts.add(treetop_valley_factory.createPurpleGhostStandardPatternLeft(39.2f, 2.2f));
		
//		purple_ghosts.add(treetop_valley_factory.createPurpleGhostStandardPatternLeft(7, 4.2f));
//		purple_ghosts.add(treetop_valley_factory.createPurpleGhostStandardPatternRight(8.4f, 4.2f));
//		purple_ghosts.add(treetop_valley_factory.createPurpleGhostFloatVerticalPattern(7, 2));
		
		purple_flames = new LinkedList<PurpleFlame>();
		PurpleGhost.registerPurpleFlames(purple_flames);
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
		if(hero.position.y < -1.0f){
//			hero.resetPositionLowerLeft(hero.position.x, 11);
			hero.changeToDeathByFallingState();
			hero.resetPositionLowerLeft(hero.position.x, -1.0f);
			
			// Glitch Handler
		}
		
		if(ShadowHero.shadow_hero_singleton.position.y < 0.0f){
			ShadowHero.shadow_hero_singleton.resetPositionLowerLeft(2, 11);
			
			// Glitch Handler
		}
		
		super.update();
	}

	private void manageCollisions() {
		/*
		 * Hero against Platform
		 */
		CollisionManager.HeroPlatformListCollision(hero, cloud_platforms);
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
		
		while(!red_whale_demons.isEmpty()){
			RedWhaleDemon red_whale_demon = red_whale_demons.removeFirst();
			pool_manager.red_whale_demon_pool.free(red_whale_demon);
		}
		
		Assets.test_music.dispose();
	}
}

package com.slimejumper.levels;

import java.util.LinkedList;

import com.slimejumper.Assets;
import com.slimejumper.Controller;
import com.slimejumper.Settings;
import com.slimejumper.tools.CollisionManager;
import com.slimejumper.tools.TesterFactory;
import com.slimejumper.world.Background;
import com.slimejumper.world.ShadowHero;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.enemies.RedWhaleDemon;
import com.slimejumper.world.environment.CloudPlatform;
import com.slimejumper.world.environment.RockPlatform;

public class TesterLevel extends Level{
	
	private static final float BACKGROUND_TREES_WIDTH = 25.6f;
	private static final float BACKGROUND_TREES_HEIGHT = 12.8f;
	private static final float TESTER_LEVEL_WIDTH = 40f;
	private static final float TESTER_LEVEL_HEIGHT = 20f;
	
	TesterFactory tester_factory;
	
	public LinkedList<RockPlatform> rock_platforms;
	public LinkedList<CloudPlatform> cloud_platforms;
	public LinkedList<RedWhaleDemon> red_whale_demons;
	
	public JellyfishDemon jellyfish_demon_test;
	
	public Background background_trees;
	
	public TesterLevel(WorldListener listener, Controller controller){
		super(listener, controller, TESTER_LEVEL_WIDTH, TESTER_LEVEL_HEIGHT);
		hero.reset(3, 3);
		background_trees = new Background(BACKGROUND_TREES_WIDTH, BACKGROUND_TREES_HEIGHT, TESTER_LEVEL_WIDTH, TESTER_LEVEL_HEIGHT);
		
		jellyfish_demon_test = new JellyfishDemon();
		jellyfish_demon_test.reset(5);
		
		tester_factory = new TesterFactory();
		initializeTestClouds();
		
		rock_platforms = new LinkedList<RockPlatform>();
		red_whale_demons = new LinkedList<RedWhaleDemon>();
		
		red_whale_demons.add(new RedWhaleDemon(8, 0.5f));
		red_whale_demons.add(new RedWhaleDemon(18, 0.5f));
		
		RockPlatform.initializeRockPlatformGround(this, rock_platforms);
		if(Settings.soundEnabled)
        	Assets.test_music.play();
	}
	
	private void initializeTestClouds() {
		cloud_platforms = new LinkedList<CloudPlatform>();
		

		cloud_platforms.add(tester_factory.createStaticCloudPlatform(0, 1.5f));
		cloud_platforms.add(tester_factory.createStaticCloudPlatform(3, 3.5f));
		cloud_platforms.add(tester_factory.createStaticCloudPlatform(3, 6.5f));
		cloud_platforms.add(tester_factory.createStaticCloudPlatform(0, 8.5f));
		cloud_platforms.add(tester_factory.createStaticCloudPlatform(3, 10.5f));
		cloud_platforms.add(tester_factory.createStaticCloudPlatform(0, 12.5f));
		
		cloud_platforms.add(tester_factory.createStaticCloudPlatform(5, 1.5f));
		cloud_platforms.add(tester_factory.createStaticCloudPlatform(8, 3.5f));
		cloud_platforms.add(tester_factory.createStaticCloudPlatform(7, 6.5f));
		cloud_platforms.add(tester_factory.createStaticCloudPlatform(9, 8.5f));
		cloud_platforms.add(tester_factory.createStaticCloudPlatform(7, 10.5f));
		cloud_platforms.add(tester_factory.createStaticCloudPlatform(10, 12.5f));
		
		cloud_platforms.add(tester_factory.createStaticCloudPlatform(2, 2.8f));
		cloud_platforms.add(tester_factory.createStaticCloudPlatform(6, 3.5f));
		cloud_platforms.add(tester_factory.createStaticCloudPlatform(10, 3.1f));
		cloud_platforms.add(tester_factory.createStaticCloudPlatform(14, 2.5f));
		cloud_platforms.add(tester_factory.createStaticCloudPlatform(18, 3.2f));
		cloud_platforms.add(tester_factory.createStaticCloudPlatform(22, 4.2f));
		
	}

	public void update(float deltaTime){
		/*
		 * Update
		 */
		
		jellyfish_demon_test.update(deltaTime);
		updateHero(deltaTime);
		for(RedWhaleDemon red_whale_demon : red_whale_demons)
			red_whale_demon.update(deltaTime);
		
		/*
		 * Collision
		 */
		manageCollisions();
		
		/*
		 * Events
		 */
				
		if(hero.position.y < 0.0f){
			hero.resetPositionLowerLeft(hero.position.x, 11);
			
			// Switch to next level
		}
		
		if(ShadowHero.shadow_hero_singleton.position.y < 0.0f){
			ShadowHero.shadow_hero_singleton.resetPositionLowerLeft(2, 11);
			
			// Switch to next level
		}
		
		updateCenter();
		updatePosition();	
	}

	private void manageCollisions() {
		CollisionManager.HeroPlatformPlatformListCollision(hero, rock_platforms);
		CollisionManager.HeroPlatformPlatformListCollision(hero, cloud_platforms);
//		CollisionManager.HeroPlatformCollision(SpriteContainer.shadow_hero, rock_platforms);
		
		CollisionManager.HeroAttackEnemyListCollision(hero, red_whale_demons);
		CollisionManager.HeroEnemyListCollision(hero, red_whale_demons);
	}

	@Override
	public void dispose() {
		Assets.test_music.dispose();
		hero.dispose();
	}
}

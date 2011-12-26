package com.slimejumper.levels;

import java.util.LinkedList;

import com.slimejumper.Assets;
import com.slimejumper.Controller;
import com.slimejumper.Settings;
import com.slimejumper.tools.CollisionManager;
import com.slimejumper.world.Background;
import com.slimejumper.world.Hero;
import com.slimejumper.world.ShadowHero;
import com.slimejumper.world.enemies.RedWhaleDemon;
import com.slimejumper.world.environment.CloudPlatform;

public class TreetopValleyLevel extends Level{

	private static final float BACKGROUND_TREES_WIDTH = 25.6f;
	private static final float TREETOP_VALLEY_WIDTH = 40f;
	
	public Background background_trees;

	public LinkedList<CloudPlatform> cloud_platforms;
	public LinkedList<RedWhaleDemon> red_whale_demons;
	
	Hero hero = Hero.hero_singleton;
	
	public TreetopValleyLevel(WorldListener listener, Controller controller){
		super(listener, controller, TREETOP_VALLEY_WIDTH, Level.WORLD_DEFAULT_HEIGHT);
		hero.resetPositionCenter(3, 3);
		
		background_trees = new Background(BACKGROUND_TREES_WIDTH, TREETOP_VALLEY_WIDTH, Level.WORLD_DEFAULT_HEIGHT);
		
		cloud_platforms = new LinkedList<CloudPlatform>();
		red_whale_demons = new LinkedList<RedWhaleDemon>();
		
		red_whale_demons.add(new RedWhaleDemon(8, 0.5f));
		red_whale_demons.add(new RedWhaleDemon(18, 0.5f));
		
		CloudPlatform.initializeCloudPlatformGround(this, cloud_platforms);
		if(Settings.soundEnabled)
        	Assets.test_music.play();
	}
	
	public void update(float deltaTime){
		/*
		 * Update
		 */
//		super.update(deltaTime);		
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
			
			// Glitch Handler
		}
		
		if(ShadowHero.shadow_hero_singleton.position.y < 0.0f){
			ShadowHero.shadow_hero_singleton.resetPositionLowerLeft(2, 11);
			
			// Glitch Handler
		}
		
		updateCenter();
		updatePosition();	
	}

	private void manageCollisions() {
		CollisionManager.HeroPlatformCollision(hero, cloud_platforms);
//		CollisionManager.HeroPlatformCollision(SpriteContainer.shadow_hero, rock_platforms);
		
		CollisionManager.HeroAttackEnemyCollision(hero, red_whale_demons);
		CollisionManager.HeroEnemyCollision(hero, red_whale_demons);
	}

	public void dispose() {		
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

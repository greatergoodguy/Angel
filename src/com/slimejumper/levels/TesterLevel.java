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
import com.slimejumper.world.environment.RockPlatform;

public class TesterLevel extends Level{
	
	private static final float BACKGROUND_TREES_WIDTH = 25.6f;
	
	private static final float TESTER_LEVEL_WIDTH = 40f;
	
	public LinkedList<RockPlatform> rock_platforms;
	public LinkedList<RedWhaleDemon> red_whale_demons;
	
	CloudPlatform cloud_platform;
	
	public Background background_trees;
	
	public TesterLevel(WorldListener listener, Controller controller){
		super(listener, controller, TESTER_LEVEL_WIDTH, WORLD_DEFAULT_HEIGHT);		
		background_trees = new Background(BACKGROUND_TREES_WIDTH, 0, TESTER_LEVEL_WIDTH, WORLD_DEFAULT_HEIGHT);
		
		rock_platforms = new LinkedList<RockPlatform>();
		red_whale_demons = new LinkedList<RedWhaleDemon>();
		
		red_whale_demons.add(new RedWhaleDemon(8, 0.5f));
		red_whale_demons.add(new RedWhaleDemon(18, 0.5f));
		
		cloud_platform = new CloudPlatform(13, 2);
		
		RockPlatform.initializeRockPlatformGround(this, rock_platforms);
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
				
		if(Hero.hero_singleton.position.y < 0.0f){
			Hero.hero_singleton.resetPositionLowerLeft(Hero.hero_singleton.position.x, 11);
			
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
		CollisionManager.HeroPlatformCollision(Hero.hero_singleton, rock_platforms);
//		CollisionManager.HeroPlatformCollision(SpriteContainer.shadow_hero, rock_platforms);
		
		CollisionManager.HeroAttackEnemyCollision(Hero.hero_singleton, red_whale_demons);
		CollisionManager.HeroEnemyCollision(Hero.hero_singleton, red_whale_demons);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}

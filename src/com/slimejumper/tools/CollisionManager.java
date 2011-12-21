package com.slimejumper.tools;

import com.slimejumper.gameframework.math.OverlapTester;
import com.slimejumper.world.Hero;
import com.slimejumper.world.Platform;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.enemies.PurpleGhost;

public class CollisionManager {
	
	public static final float COLLISION_TOLERANCE = 0.17f;
	
	public static void manageCollisions(){
		heroConcentratedCollisions();
		shadowHeroConcentratedCollisions();
		jellyfishDemonConcentratedCollisions();
	}

	private static void heroConcentratedCollisions() {
		checkHeroPlatformCollisions();
		checkIncomingEnemyCollisions();
	}

	private static void checkIncomingEnemyCollisions() {
		if(SpriteContainer.hero.state == Hero.HERO_STATE_COLLIDED)
			return;
		
		for(PurpleGhost purple_ghost : SpriteContainer.purple_ghosts){
			if(OverlapTester.overlapRectangles(SpriteContainer.hero, purple_ghost)){
				SpriteContainer.hero.changeToCollidedState();
//				world.listener.hit();
			}
				
		}
		
		for(JellyfishDemon jellyfish_demon : SpriteContainer.jellyfish_demons){
			if(OverlapTester.overlapRectangles(SpriteContainer.hero, jellyfish_demon)){
				SpriteContainer.hero.changeToCollidedState();
//				world.listener.hit();
			}
				
		}
		
	}

	private static void checkHeroPlatformCollisions() {
		if (SpriteContainer.hero.velocity.y > 0)
			return;
	
		for (Platform platform : SpriteContainer.volatile_platforms) 
			heroPlatformRebound(platform);
		for(Platform platform : SpriteContainer.ground_platforms)
			heroPlatformRebound(platform);
		for(Platform platform : SpriteContainer.static_platforms)
			heroPlatformRebound(platform);
	}
	
	private static void shadowHeroConcentratedCollisions() {
		checkShadowHeroPlatformCollisions();
	}
	
	private static void checkShadowHeroPlatformCollisions() {
		if (SpriteContainer.shadow_hero.velocity.y > 0)
			return;
	
		for (Platform platform : SpriteContainer.volatile_platforms) 
			shadowHeroPlatformRebound(platform);
		for(Platform platform : SpriteContainer.ground_platforms)
			shadowHeroPlatformRebound(platform);
		for(Platform platform : SpriteContainer.static_platforms)
			shadowHeroPlatformRebound(platform);
		
	}

	public static void heroPlatformRebound(Platform platform){
		if ((OverlapTester.overlapRectangles(SpriteContainer.hero, platform))
				&& (platform.position.y	- SpriteContainer.hero.position.y < COLLISION_TOLERANCE)) {
			SpriteContainer.hero.reboundPlatform(platform);
			if(SpriteContainer.hero.state == Hero.HERO_STATE_BASIC_ATTACK){
				
			}
			else
				SpriteContainer.hero.changeToLandState();
//			world.listener.jump();
		}
	}

	public static void shadowHeroPlatformRebound(Platform platform){
		if ((OverlapTester.overlapRectangles(SpriteContainer.shadow_hero, platform))
				&& (platform.position.y	- SpriteContainer.shadow_hero.position.y < COLLISION_TOLERANCE)) {
			SpriteContainer.shadow_hero.reboundPlatform(platform);
			if(SpriteContainer.shadow_hero.state == Hero.HERO_STATE_BASIC_ATTACK){
				
			}
			else
				SpriteContainer.shadow_hero.changeToLandState();
//			world.listener.jump();
		}
	}
	
	private static void jellyfishDemonConcentratedCollisions() {
		checkIncomingHaloAttackCollision();	
	}

	private static void checkIncomingHaloAttackCollision() {
		if(SpriteContainer.halo_attacks.isEmpty() || SpriteContainer.jellyfish_demons.isEmpty())
			return;
		
		for(HaloAttack halo_attack : SpriteContainer.halo_attacks){
			for(JellyfishDemon jellyfish_demon : SpriteContainer.jellyfish_demons){
				
				if(jellyfish_demon.state == JellyfishDemon.JELLY_STATE_COLLIDED || 
						OverlapTester.overlapRectangles(halo_attack, jellyfish_demon)){
					jellyfish_demon.changeToCollidedState(halo_attack);
				}
			}
		}
			
	}
}

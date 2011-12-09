package com.slimejumper.tools;

import com.slimejumper.gameframework.math.OverlapTester;
import com.slimejumper.levels.CaveLevel;
import com.slimejumper.world.Hero;
import com.slimejumper.world.Platform;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.enemies.PurpleGhost;

public class CollisionManager {
	
	public static final float COLLISION_TOLERANCE = 0.17f;
	public static Hero hero = null;
	
	public static void setCollidingHero(Hero active_hero){
		hero = active_hero;
	}
	
	public static void manageCollisions(){
		heroConcentratedCollisions();
		jellyfishDemonConcentratedCollisions();
	}

	private static void heroConcentratedCollisions() {
		checkIncomingPlatformCollisions();
		checkIncomingEnemyCollisions();
	}

	private static void checkIncomingEnemyCollisions() {
		if(hero.state == Hero.HERO_STATE_COLLIDED)
			return;
		
		for(PurpleGhost purple_ghost : SpriteContainer.purple_ghosts){
			if(OverlapTester.overlapRectangles(hero, purple_ghost)){
				hero.changeToCollidedState();
//				world.listener.hit();
			}
				
		}
		
		for(JellyfishDemon jellyfish_demon : SpriteContainer.jellyfish_demons){
			if(OverlapTester.overlapRectangles(hero, jellyfish_demon)){
				hero.changeToCollidedState();
//				world.listener.hit();
			}
				
		}
		
	}

	private static void checkIncomingPlatformCollisions() {
		if (hero.velocity.y > 0)
			return;
	
		for (Platform platform : SpriteContainer.volatile_platforms) 
			heroPlatformRebound(platform);
		for(Platform platform : SpriteContainer.ground_platforms)
			heroPlatformRebound(platform);
		for(Platform platform : SpriteContainer.static_platforms)
			heroPlatformRebound(platform);
	}
	
	public static void heroPlatformRebound(Platform platform){
		if ((OverlapTester.overlapRectangles(hero, platform))
				&& (platform.position.y	- hero.position.y < COLLISION_TOLERANCE)) {
			hero.reboundPlatform(platform);
			if(hero.state == Hero.HERO_STATE_BASIC_ATTACK){
				
			}
			else
				hero.changeToLandState();
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

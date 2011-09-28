package com.slimejumper.tools;

import com.slimejumper.gameframework.math.OverlapTester;
import com.slimejumper.world.Hero;
import com.slimejumper.world.Platform;
import com.slimejumper.world.GameWorld;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.enemies.PurpleGhost;

public class CollisionManager {
	
	public static final float COLLISION_TOLERANCE = 0.17f;
	Hero hero = null;
	
	public CollisionManager(){
	}
	
	public void setCollidingHero(Hero hero){
		this.hero = hero;
	}
	
	public void manageCollisions(){
		heroConcentratedCollisions();
		jellyfishDemonConcentratedCollisions();
	}

	private void heroConcentratedCollisions() {
		checkIncomingPlatformCollisions();
		checkIncomingEnemyCollisions();
	}

	private void checkIncomingEnemyCollisions() {
		if(hero.state == Hero.HERO_STATE_COLLIDED)
			return;
		
		for(PurpleGhost purple_ghost : PurpleGhost.purple_ghosts){
			if(OverlapTester.overlapRectangles(hero, purple_ghost)){
				hero.changeToCollidedState();
//				world.listener.hit();
			}
				
		}
		
		for(JellyfishDemon jellyfish_demon : JellyfishDemon.jellyfish_demons){
			if(OverlapTester.overlapRectangles(hero, jellyfish_demon)){
				hero.changeToCollidedState();
//				world.listener.hit();
			}
				
		}
		
	}

	private void checkIncomingPlatformCollisions() {
		if (hero.velocity.y > 0)
			return;
	
		for (Platform platform : Platform.volatile_platforms) 
			heroPlatformRebound(platform);
		for(Platform platform : Platform.ground_platforms)
			heroPlatformRebound(platform);
		for(Platform platform : Platform.static_platforms)
			heroPlatformRebound(platform);
	}
	
	public void heroPlatformRebound(Platform platform){
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

	private void jellyfishDemonConcentratedCollisions() {
		checkIncomingHaloAttackCollision();	
	}

	private void checkIncomingHaloAttackCollision() {
		if(HaloAttack.halo_attacks.isEmpty() || JellyfishDemon.jellyfish_demons.isEmpty())
			return;
		
		for(HaloAttack halo_attack : HaloAttack.halo_attacks){
			for(JellyfishDemon jellyfish_demon : JellyfishDemon.jellyfish_demons){
				
				if(jellyfish_demon.state == JellyfishDemon.JELLY_STATE_COLLIDED || 
						OverlapTester.overlapRectangles(halo_attack, jellyfish_demon)){
					jellyfish_demon.changeToCollidedState(halo_attack);
				}
			}
		}
			
	}
}

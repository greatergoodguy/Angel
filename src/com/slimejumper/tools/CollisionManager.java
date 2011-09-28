package com.slimejumper.tools;

import com.slimejumper.gameframework.math.OverlapTester;
import com.slimejumper.world.Hero;
import com.slimejumper.world.Platform;
import com.slimejumper.world.World;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.enemies.PurpleGhost;

public class CollisionManager {
	World world;
	Hero hero;
	
	public CollisionManager(World world){
		this.world = world;
		this.hero = world.hero;
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
			world.heroPlatformRebound(platform);
		for(Platform platform : Platform.ground_platforms)
			world.heroPlatformRebound(platform);
		for(Platform platform : Platform.static_platforms)
			world.heroPlatformRebound(platform);
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

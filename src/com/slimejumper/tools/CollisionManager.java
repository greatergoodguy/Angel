package com.slimejumper.tools;

import java.util.Iterator;
import java.util.LinkedList;

import com.slimejumper.gameframework.math.OverlapTester;
import com.slimejumper.world.Hero;
import com.slimejumper.world.ShadowHero;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.attacks.MusicNote;
import com.slimejumper.world.attacks.SpiralAttack;
import com.slimejumper.world.enemies.Enemy;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.enemies.PurpleGhost;
import com.slimejumper.world.enemies.RedWhaleDemon;
import com.slimejumper.world.environment.GreekPlatform;
import com.slimejumper.world.environment.Platform;

public class CollisionManager {
	
	public static final float COLLISION_TOLERANCE = 0.25f;
	
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
		if(Hero.hero_singleton.state == Hero.HERO_STATE_COLLIDED)
			return;
		
		for(PurpleGhost purple_ghost : SpriteContainer.purple_ghosts){
			if(OverlapTester.overlapRectangles(Hero.hero_singleton, purple_ghost)){
				Hero.hero_singleton.changeToCollidedState();
//				world.listener.hit();
			}
				
		}
		
		for(JellyfishDemon jellyfish_demon : SpriteContainer.jellyfish_demons){
			if(OverlapTester.overlapRectangles(Hero.hero_singleton, jellyfish_demon)){
				Hero.hero_singleton.changeToCollidedState();
//				world.listener.hit();
			}	
		}
		
	}

	private static void checkHeroPlatformCollisions() {
		if (Hero.hero_singleton.velocity.y > 0)
			return;
	
		for (GreekPlatform platform : SpriteContainer.volatile_platforms) 
			heroPlatformRebound(platform);
		for(GreekPlatform platform : SpriteContainer.ground_platforms)
			heroPlatformRebound(platform);
		for(GreekPlatform platform : SpriteContainer.static_platforms)
			heroPlatformRebound(platform);
	}
	
	private static void shadowHeroConcentratedCollisions() {
		checkShadowHeroPlatformCollisions();
	}
	
	private static void checkShadowHeroPlatformCollisions() {
		if (ShadowHero.shadow_hero_singleton.velocity.y > 0)
			return;
	
		for (GreekPlatform platform : SpriteContainer.volatile_platforms) 
			shadowHeroPlatformRebound(platform);
		for(GreekPlatform platform : SpriteContainer.ground_platforms)
			shadowHeroPlatformRebound(platform);
		for(GreekPlatform platform : SpriteContainer.static_platforms)
			shadowHeroPlatformRebound(platform);
		
	}

	public static void heroPlatformRebound(GreekPlatform platform){
		if ((OverlapTester.overlapRectangles(Hero.hero_singleton, platform))
				&& (platform.position.y	- Hero.hero_singleton.position.y < COLLISION_TOLERANCE)) {
			Hero.hero_singleton.reboundPlatform(platform);
		}
	}

	public static void shadowHeroPlatformRebound(GreekPlatform platform){
		if ((OverlapTester.overlapRectangles(ShadowHero.shadow_hero_singleton, platform))
				&& (platform.position.y	- ShadowHero.shadow_hero_singleton.position.y < COLLISION_TOLERANCE)) {
			ShadowHero.shadow_hero_singleton.reboundPlatform(platform);
			if(ShadowHero.shadow_hero_singleton.state == Hero.HERO_STATE_BASIC_ATTACK){
				
			}
			else
				ShadowHero.shadow_hero_singleton.changeToLandState();
//			world.listener.jump();
		}
	}
	
	private static void jellyfishDemonConcentratedCollisions() {
		checkIncomingHaloAttackCollision();	
	}

	private static void checkIncomingHaloAttackCollision() {
		if(Hero.hero_singleton.halo_attacks.isEmpty() || SpriteContainer.jellyfish_demons.isEmpty())
			return;
		
		for(HaloAttack halo_attack : Hero.hero_singleton.halo_attacks){
			for(JellyfishDemon jellyfish_demon : SpriteContainer.jellyfish_demons){
				
				if(jellyfish_demon.state == JellyfishDemon.JELLY_STATE_COLLIDED || 
						OverlapTester.overlapRectangles(halo_attack, jellyfish_demon)){
					jellyfish_demon.changeToCollidedState(halo_attack);
				}
			}
		}
			
	}

	public static void HeroPlatformCollision(Hero hero, LinkedList<? extends Platform> platforms) {
		for(Platform platform : platforms){
		
			if ((OverlapTester.overlapRectangles(hero, platform))
				&& (platform.position.y	- hero.position.y < COLLISION_TOLERANCE))
				hero.reboundPlatform(platform);
		
		}
		
	}

	public static void HeroAttackEnemyCollision(Hero hero, LinkedList<? extends Enemy> enemies) {
		
		Iterator<? extends Enemy> iter_enemies = enemies.iterator();
		while(iter_enemies.hasNext()){
			Enemy enemy = iter_enemies.next();
		
			Iterator<HaloAttack> iter_halo_atks = hero.halo_attacks.iterator();
			while(iter_halo_atks.hasNext()){
				HaloAttack halo_attack = iter_halo_atks.next();
				if (OverlapTester.overlapRectangles(halo_attack, enemy)){
					iter_halo_atks.remove();
				}
			}	
			
			Iterator<SpiralAttack> iter_spiral_atks = hero.spiral_attacks.iterator();
			while(iter_spiral_atks.hasNext()){
				SpiralAttack spiral_attack = iter_spiral_atks.next();
				if (OverlapTester.overlapRectangles(spiral_attack, enemy)){
					iter_spiral_atks.remove();
				}
			}
			
			Iterator<MusicNote> iter_music_note_atks = hero.music_notes.iterator();
			while(iter_music_note_atks.hasNext()){
				MusicNote music_note = iter_music_note_atks.next();
				if (OverlapTester.overlapRectangles(music_note, enemy)){
					iter_music_note_atks.remove();
				}
			}
		}
	}

	public static void HeroEnemyCollision(Hero hero, LinkedList<? extends Enemy> enemies) {
		for(Enemy enemy : enemies){
			if(OverlapTester.overlapRectangles(hero, enemy)){
				hero.changeToCollidedState();
			}	
		}
		
	}
}

package com.slimejumper.tools;

import java.util.Iterator;
import java.util.LinkedList;

import com.slimejumper.gameframework.math.OverlapTester;
import com.slimejumper.world.Hero;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.attacks.MusicNote;
import com.slimejumper.world.attacks.SpiralAttack;
import com.slimejumper.world.enemies.Enemy;
import com.slimejumper.world.environment.Platform;

public class CollisionManager {
	
	public static final float COLLISION_TOLERANCE = 0.09f;

	public static void HeroPlatformCollision(Hero hero, LinkedList<? extends Platform> platforms) {
		for(Platform platform : platforms){		
			if ((OverlapTester.overlapRectangles(hero, platform))
				&& (platform.position.y	- hero.position.y < COLLISION_TOLERANCE)
				&& (hero.velocity.y < 0))
				hero.reboundPlatform(platform);		
		}
		
	}

	public static void HeroAttackEnemyCollision(Hero hero, LinkedList<? extends Enemy> enemies) {
		
		Iterator<? extends Enemy> iter_enemies = enemies.iterator();
		while(iter_enemies.hasNext()){
			Enemy enemy = iter_enemies.next();
			boolean is_enemy_hit = false;
			
			Iterator<HaloAttack> iter_halo_atks = hero.halo_attacks.iterator();
			while(iter_halo_atks.hasNext()){
				HaloAttack halo_attack = iter_halo_atks.next();
				if (OverlapTester.overlapRectangles(halo_attack, enemy)){
					iter_halo_atks.remove();
					is_enemy_hit = true;
				}
			}	
			
			Iterator<SpiralAttack> iter_spiral_atks = hero.spiral_attacks.iterator();
			while(iter_spiral_atks.hasNext()){
				SpiralAttack spiral_attack = iter_spiral_atks.next();
				if (OverlapTester.overlapRectangles(spiral_attack, enemy)){
					iter_spiral_atks.remove();
					is_enemy_hit = true;
				}
			}
			
			Iterator<MusicNote> iter_music_note_atks = hero.music_notes.iterator();
			while(iter_music_note_atks.hasNext()){
				MusicNote music_note = iter_music_note_atks.next();
				if (OverlapTester.overlapRectangles(music_note, enemy)){
					iter_music_note_atks.remove();
					is_enemy_hit = true;
				}
			}
			
			if(is_enemy_hit){
				enemy.health_bar--;
				if(enemy.health_bar == 0){
					iter_enemies.remove();
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

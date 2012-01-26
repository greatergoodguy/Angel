package com.slimejumper.tools;

import java.util.Iterator;
import java.util.LinkedList;

import com.slimejumper.gameframework.math.OverlapTester;
import com.slimejumper.world.Hero;
import com.slimejumper.world.attacks.AngelicFlame;
import com.slimejumper.world.attacks.Attack;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.attacks.MusicNote;
import com.slimejumper.world.attacks.SpiralAttack;
import com.slimejumper.world.enemies.Enemy;
import com.slimejumper.world.enemies.PurpleGhost;
import com.slimejumper.world.enemies.RedWhaleDemon;
import com.slimejumper.world.environment.Platform;

public class CollisionManager {
	
	public static final float COLLISION_TOLERANCE = 0.09f;
	
	public static void HeroPlatformListCollision(Hero hero, LinkedList<? extends Platform> platforms) {
		for(Platform platform : platforms){		
			if ((OverlapTester.overlapRectangles(hero, platform))
				&& (platform.position.y	- hero.position.y < COLLISION_TOLERANCE)
				&& (hero.velocity.y < 0))
				hero.reboundPlatform(platform);		
		}
		
	}

	public static void HeroAttacksEnemyListCollision(Hero hero, LinkedList<? extends Enemy> enemies) {
		
		Iterator<? extends Enemy> iter_enemies = enemies.iterator();
		while(iter_enemies.hasNext()){
			Enemy enemy = iter_enemies.next();
			boolean is_enemy_hit = false;
			
			Iterator<HaloAttack> iter_halo_atks = hero.halo_attacks.iterator();
			while(iter_halo_atks.hasNext()){
				HaloAttack halo_attack = iter_halo_atks.next();
				if (OverlapTester.overlapRectangles(halo_attack, enemy)){
					PoolManager.pool_manager_singleton.halo_attack_pool.free(halo_attack);
					iter_halo_atks.remove();
					is_enemy_hit = true;
				}
			}	
			
			Iterator<SpiralAttack> iter_spiral_atks = hero.spiral_attacks.iterator();
			while(iter_spiral_atks.hasNext()){
				SpiralAttack spiral_attack = iter_spiral_atks.next();
				if (OverlapTester.overlapRectangles(spiral_attack, enemy)){
					PoolManager.pool_manager_singleton.spiral_attack_pool.free(spiral_attack);
					iter_spiral_atks.remove();
					is_enemy_hit = true;
				}
			}
			
			Iterator<AngelicFlame> iter_angel_flame_atks = hero.angelic_flames.iterator();
			while(iter_angel_flame_atks.hasNext()){
				AngelicFlame angelic_flame = iter_angel_flame_atks.next();
				if (OverlapTester.overlapRectangles(angelic_flame, enemy)){
					PoolManager.pool_manager_singleton.angelic_flame_pool.free(angelic_flame);
					iter_angel_flame_atks.remove();
					is_enemy_hit = true;
				}
			}
			
			Iterator<MusicNote> iter_music_note_atks = hero.music_notes.iterator();
			while(iter_music_note_atks.hasNext()){
				MusicNote music_note = iter_music_note_atks.next();
				if (OverlapTester.overlapRectangles(music_note, enemy)){
					PoolManager.pool_manager_singleton.music_note_pool.free(music_note);
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

	public static void HeroAttacksEnemyListCollision(Hero hero, LinkedList<? extends Enemy> enemies, float collision_offset) {
		
		Iterator<? extends Enemy> iter_enemies = enemies.iterator();
		while(iter_enemies.hasNext()){
			Enemy enemy = iter_enemies.next();
			boolean is_enemy_hit = false;
			
			Iterator<HaloAttack> iter_halo_atks = hero.halo_attacks.iterator();
			while(iter_halo_atks.hasNext()){
				HaloAttack halo_attack = iter_halo_atks.next();
				if (OverlapTester.overlapRectangles(halo_attack, enemy, collision_offset)){
					PoolManager.pool_manager_singleton.halo_attack_pool.free(halo_attack);
					iter_halo_atks.remove();
					is_enemy_hit = true;
				}
			}	
			
			Iterator<SpiralAttack> iter_spiral_atks = hero.spiral_attacks.iterator();
			while(iter_spiral_atks.hasNext()){
				SpiralAttack spiral_attack = iter_spiral_atks.next();
				if (OverlapTester.overlapRectangles(spiral_attack, enemy, collision_offset)){
					PoolManager.pool_manager_singleton.spiral_attack_pool.free(spiral_attack);
					iter_spiral_atks.remove();
					is_enemy_hit = true;
				}
			}
			
			Iterator<AngelicFlame> iter_angel_flame_atks = hero.angelic_flames.iterator();
			while(iter_angel_flame_atks.hasNext()){
				AngelicFlame angelic_flame = iter_angel_flame_atks.next();
				if (OverlapTester.overlapRectangles(angelic_flame, enemy, collision_offset)){
					PoolManager.pool_manager_singleton.angelic_flame_pool.free(angelic_flame);
					iter_angel_flame_atks.remove();
					is_enemy_hit = true;
				}
			}
			
			Iterator<MusicNote> iter_music_note_atks = hero.music_notes.iterator();
			while(iter_music_note_atks.hasNext()){
				MusicNote music_note = iter_music_note_atks.next();
				if (OverlapTester.overlapRectangles(music_note, enemy, collision_offset)){
					PoolManager.pool_manager_singleton.music_note_pool.free(music_note);
					iter_music_note_atks.remove();
					is_enemy_hit = true;
				}
			}
			
			if(is_enemy_hit){
				enemy.health_bar--;
				
				if(enemy instanceof PurpleGhost){
					((PurpleGhost) enemy).changeToCollidedState();
				}
				else if(enemy instanceof RedWhaleDemon){
					((RedWhaleDemon) enemy).changeToCollidedState();
				}
				
				
				if(enemy.health_bar == 0){
					iter_enemies.remove();
				}
			}
		}
	}
	
	public static void HeroEnemyListCollision(Hero hero, LinkedList<? extends Enemy> enemies) {
		for(Enemy enemy : enemies){
			if(OverlapTester.overlapRectangles(hero, enemy)){
				if(hero.center.x < enemy.center.x)
					hero.changeToCollidedStateFromRight();
				else
					hero.changeToCollidedStateFromLeft();
			}	
		}		
	}
	
	public static void HeroEnemyListCollision(Hero hero, LinkedList<? extends Enemy> enemies, float collision_offset){
		for(Enemy enemy : enemies){
			if(OverlapTester.overlapRectangles(hero, enemy, collision_offset)){
				if(hero.center.x < enemy.center.x)
					hero.changeToCollidedStateFromRight();
				else
					hero.changeToCollidedStateFromLeft();
			}	
		}
	}

	public static void EnemyAttacksHeroCollision(LinkedList<? extends Attack> attacks, Hero hero) {
		for(Attack attack : attacks){
			if(OverlapTester.overlapRectangles(attack, hero)){
				if(attack.velocity.x < 0)
					hero.changeToCollidedStateFromRight();
				else
					hero.changeToCollidedStateFromLeft();
			}
		}
	}

	public static void EnemyAttacksHeroCollision(LinkedList<? extends Attack> attacks, Hero hero, float collision_offset) {
		for(Attack attack : attacks){
			if(OverlapTester.overlapRectangles(attack, hero, collision_offset)){
				if(attack.velocity.x < 0)
					hero.changeToCollidedStateFromRight();
				else
					hero.changeToCollidedStateFromLeft();
			}
		}		
	}
}

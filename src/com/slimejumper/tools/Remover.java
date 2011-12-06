package com.slimejumper.tools;

import com.slimejumper.levels.World;
import com.slimejumper.world.Platform;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.attacks.MusicNote;
import com.slimejumper.world.attacks.Shockball;
import com.slimejumper.world.enemies.FlyingSnake;
import com.slimejumper.world.enemies.JellyfishDemon;

public class Remover {
	
	/*
	 * Every List needs a remove method and a clear method
	 */
	
	// remove
	public static void remove(){
		removePlatforms();
		removeJellyfishDemons();
		removeFlyingSnakes();
		removeHaloAttacks();
		removeMusicNotes();
		removeShockballs();
	}
	
	private static void removePlatforms() {
		if(Platform.volatile_platforms.isEmpty())
			return;		

		Platform platform = Platform.volatile_platforms.getFirst();

		if (platform.life_timer > Platform.PLATFORM_LIFESPAN) {
			Platform.volatile_platforms.removeFirst();
			World.poolManager.platform_pool.free(platform);			
		}
	}
	
	private static void removeJellyfishDemons() {
		if(JellyfishDemon.jellyfish_demons.isEmpty())
			return;
		
		JellyfishDemon jellyfish_demon = JellyfishDemon.jellyfish_demons.getFirst();
		if(jellyfish_demon.life_timer > JellyfishDemon.JELLY_LIFESPAN){
			JellyfishDemon.jellyfish_demons.removeFirst();
			World.poolManager.jellyfish_demon_pool.free(jellyfish_demon);
		}
		
	}
	
	private static void removeFlyingSnakes() {
		if(FlyingSnake.flying_snakes.isEmpty())
			return;
		
		FlyingSnake flying_snake = FlyingSnake.flying_snakes.getFirst();
		if(flying_snake.life_timer > FlyingSnake.FLYING_SNAKE_LIFESPAN){
			FlyingSnake.flying_snakes.removeFirst();
			World.poolManager.flying_snake_pool.free(flying_snake);
		}
	}
	private static void removeHaloAttacks() {
		if(HaloAttack.halo_attacks.isEmpty())
			return;
		
		HaloAttack halo_attack = HaloAttack.halo_attacks.getFirst();
		if(halo_attack.life_timer > HaloAttack.HaloAttack_LIFESPAN){
			HaloAttack.halo_attacks.removeFirst();
			World.poolManager.halo_attack_pool.free(halo_attack);
		}
		
	}
	
	private static void removeMusicNotes() {
		if(MusicNote.music_notes.isEmpty())
			return;
		
		MusicNote music_note = MusicNote.music_notes.getFirst();
		if(music_note.life_timer > MusicNote.MUSIC_NOTE_LIFESPAN){
			MusicNote.music_notes.removeFirst();
			World.poolManager.music_note_pool.free(music_note);
		}
	}
	
	private static void removeShockballs() {
		if(Shockball.shockballs.isEmpty())
			return;
		
		Shockball shockball = Shockball.shockballs.getFirst();
		if(shockball.life_timer > Shockball.SHOCKBALL_LIFESPAN){
			Shockball.shockballs.removeFirst();
			World.poolManager.shockball_pool.free(shockball);
		}	
	}

	public static void clearAllLists(){
		clearPlatforms();
		clearJellyfishDemons();
		clearFlyingSnakes();
		clearHaloAttacks();
		clearMusicNotes();
		clearShockBalls();
	}

	
	// clear
	private static void clearPlatforms() {

		while(!Platform.volatile_platforms.isEmpty()){
			Platform platform = Platform.volatile_platforms.removeFirst();
			World.poolManager.platform_pool.free(platform);			
		}
		while(!Platform.ground_platforms.isEmpty()){
			Platform platform = Platform.ground_platforms.removeFirst();
			World.poolManager.platform_pool.free(platform);	
		}
		while(!Platform.static_platforms.isEmpty()){
			Platform platform = Platform.static_platforms.removeFirst();
			World.poolManager.platform_pool.free(platform);
		}
	}

	private static void clearJellyfishDemons() {
		while(!JellyfishDemon.jellyfish_demons.isEmpty()){
			JellyfishDemon jellyfish_demon = JellyfishDemon.jellyfish_demons.removeFirst();
			World.poolManager.jellyfish_demon_pool.free(jellyfish_demon);
		}
		
	}

	private static void clearFlyingSnakes() {
		while(!FlyingSnake.flying_snakes.isEmpty()){
			FlyingSnake flying_snake = FlyingSnake.flying_snakes.removeFirst();
			World.poolManager.flying_snake_pool.free(flying_snake);
		}
		
	}

	private static void clearHaloAttacks() {
		while(!HaloAttack.halo_attacks.isEmpty()){
			HaloAttack halo_attack = HaloAttack.halo_attacks.removeFirst();
			World.poolManager.halo_attack_pool.free(halo_attack);
		}
		
	}

	private static void clearMusicNotes() {
		while(!MusicNote.music_notes.isEmpty()){
			MusicNote music_note = MusicNote.music_notes.removeFirst();
			World.poolManager.music_note_pool.free(music_note);
		}		
	}

	private static void clearShockBalls() {
		while(!Shockball.shockballs.isEmpty()){
			Shockball shockball = Shockball.shockballs.removeFirst();
			World.poolManager.shockball_pool.free(shockball);
		}		
	}
	
/*	
	private void removePurpleGhosts() {
		if(PurpleGhost.purple_ghosts.isEmpty())
			return;
		
		PurpleGhost purple_ghost = PurpleGhost.purple_ghosts.getFirst();
		if(purple_ghost.life_timer > PurpleGhost.PurpleGhost_LIFESPAN){
			PurpleGhost.purple_ghosts.removeFirst();
			World.poolManager.purple_ghost_pool.free(purple_ghost);
		}
	}
*/
}

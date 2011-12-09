package com.slimejumper.tools;

import java.util.LinkedList;

import com.slimejumper.world.Hero;
import com.slimejumper.world.Platform;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.attacks.MusicNote;
import com.slimejumper.world.attacks.Shockball;
import com.slimejumper.world.attacks.SpiralAttack;
import com.slimejumper.world.enemies.FlyingSnake;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.enemies.PurpleGhost;

public class SpriteContainer {
	/*
	 * Attacks
	 */
	
	public static LinkedList<HaloAttack> halo_attacks;
	public static LinkedList<Shockball> shockballs;
	public static LinkedList<MusicNote> music_notes;
	public static LinkedList<SpiralAttack> spiral_attacks;
	
	/*
	 * Enemies
	 */
	
	public static LinkedList<PurpleGhost> purple_ghosts;
	public static LinkedList<JellyfishDemon> jellyfish_demons;
	public static LinkedList<FlyingSnake> flying_snakes;
	
	/*
	 * Platforms
	 */
	
	public static LinkedList<Platform> volatile_platforms;
	public static LinkedList<Platform> static_platforms;
	public static LinkedList<Platform> ground_platforms;

	public static Hero hero = null;
	
	public static void initializeLists() {
		purple_ghosts = new LinkedList<PurpleGhost>();
		jellyfish_demons = new LinkedList<JellyfishDemon>();
		flying_snakes = new LinkedList<FlyingSnake>();
		
		static_platforms = new LinkedList<Platform>();
		volatile_platforms = new LinkedList<Platform>();
		ground_platforms = new LinkedList<Platform>();
		
		halo_attacks = new LinkedList<HaloAttack>();
		music_notes = new LinkedList<MusicNote>();
		shockballs = new LinkedList<Shockball>();
		spiral_attacks = new LinkedList<SpiralAttack>();
		
		hero = new Hero();
	}
}

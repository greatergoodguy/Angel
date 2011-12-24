package com.slimejumper.tools;

import java.util.LinkedList;

import com.slimejumper.world.Hero;
import com.slimejumper.world.ShadowHero;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.attacks.MusicNote;
import com.slimejumper.world.attacks.Shockball;
import com.slimejumper.world.attacks.SpiralAttack;
import com.slimejumper.world.enemies.FlyingSnake;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.enemies.PurpleGhost;
import com.slimejumper.world.environment.GreekPlatform;

public class SpriteContainer {
	/*
	 * Attacks
	 */
	
	public static LinkedList<Shockball> shockballs;
	
	/*
	 * Enemies
	 */
	
	public static LinkedList<PurpleGhost> purple_ghosts;
	public static LinkedList<JellyfishDemon> jellyfish_demons;
	public static LinkedList<FlyingSnake> flying_snakes;
	
	/*
	 * Platforms
	 */
	
	public static LinkedList<GreekPlatform> volatile_platforms;
	public static LinkedList<GreekPlatform> static_platforms;
	public static LinkedList<GreekPlatform> ground_platforms;

	public static Hero hero = null;
	public static ShadowHero shadow_hero = null;
	
	public SpriteContainer(){
		initializeLists();
	}
	
	public void initializeLists() {
		purple_ghosts = new LinkedList<PurpleGhost>();
		jellyfish_demons = new LinkedList<JellyfishDemon>();
		flying_snakes = new LinkedList<FlyingSnake>();
		
		static_platforms = new LinkedList<GreekPlatform>();
		volatile_platforms = new LinkedList<GreekPlatform>();
		ground_platforms = new LinkedList<GreekPlatform>();

		SpriteContainer.shockballs = new LinkedList<Shockball>();
		
		hero = new Hero();
		shadow_hero = new ShadowHero(hero);
	}
}

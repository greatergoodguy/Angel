package com.slimejumper;

import com.slimejumper.framework.impl.GLGame;
import com.slimejumper.gameframework.Sound;
import com.slimejumper.gameframework.gl.Animation;
import com.slimejumper.gameframework.gl.Texture;
import com.slimejumper.gameframework.gl.TextureRegion;

public class Assets {
	
	public final static float HERO_HALO_ATTACK_1_FRAME_DURATION = 0.07f;
	public final static float HERO_LYRE_ATTACK_FRAME_DURATION = 0.15f;
	public final static float HERO_SPIRAL_ATTACK_1_FRAME_DURATION = 0.10f;
	
	public final static float HERO_LAND_FRAME_DURATION = 0.04f;
	public final static float HERO_COLLIDED_FRAME_DURATION = 0.20f;
	
	public final static float RED_WHALE_DEMON_FRAME_DURATION = 0.20f;
	
	public final static float JELLYFISH_DEMON_MOTION_FRAME_DURATION = 0.14f;
	public final static float JELLYFISH_DEMON_SHOCK_ATTACK_FRAME_DURATION = 0.11f;
	public final static int JELLYFISH_DEMON_MOTION_FRAME_COUNT = 12;
	public final static int JELLYFISH_DEMON_MOTION_FRAME_COUNT_MINUS_ONE = 11;
	public final static float JELLYFISH_DEMON_COLLIDED_FRAME_DURATION = 0.1f;
	
	public final static float FLYING_SNAKE_STANDARD_FRAME_DURATION = 0.15f;
	public final static float FLYING_SNAKE_ATTACK_FRAME_DURATION = 0.20f;
	
	public final static float SHOCKBALL_FRAME_DURATION = 0.14f;
	public final static float HALO_ATTACK_FRAME_DURATION = 0.12f;
	public final static float SPIRAL_ATTACK_FRAME_DURATION = 0.20f;
	
	/*
	 * Hero Atlas
	 */
	
	public static Texture hero;
	public static TextureRegion heroRegion;
	public static TextureRegion hero_jump;
	public static TextureRegion hero_fall;
	
	public static Animation hero_land;
	public static TextureRegion heroLandFrame1;
	public static TextureRegion heroLandFrame2;
	public static TextureRegion heroLandFrame3;
	public static TextureRegion heroLandFrame4;
	
	public static Animation hero_halo_attack_1;
	public static TextureRegion heroHaloAttack1Frame1;
	public static TextureRegion heroHaloAttack1Frame2;
	public static TextureRegion heroHaloAttack1Frame3;
	public static TextureRegion heroHaloAttack1Frame4;
	public static TextureRegion heroHaloAttack1Frame5;
	public static TextureRegion heroHaloAttack1Frame6;
	public static TextureRegion heroHaloAttack1Frame7;
	public static TextureRegion heroHaloAttack1Frame8;
	public static TextureRegion heroHaloAttack1Frame9;
	public static TextureRegion heroHaloAttack1Frame10;
	public static TextureRegion heroHaloAttack1Frame11;
	public static TextureRegion heroHaloAttack1Frame12;
	public static TextureRegion heroHaloAttack1Frame13;
	public static TextureRegion herohaloattack1frame14;
	
	public static Animation halo_attack;
	public static TextureRegion haloAttackFrame1;
	public static TextureRegion haloAttackFrame2;
	
	public static Animation hero_lyre_attack_1;
	public static TextureRegion heroLyreAttack1Frame1;
	public static TextureRegion heroLyreAttack1Frame2;
	public static TextureRegion heroLyreAttack1Frame3;
	public static TextureRegion heroLyreAttack1Frame4;
	public static TextureRegion heroLyreAttack1Frame5;
	public static TextureRegion heroLyreAttack1Frame6;
	
	public static TextureRegion musicNoteFrame1;
	
	public static Animation hero_spiral_attack_1;
	public static TextureRegion heroSpiralAttack1Frame1;
	public static TextureRegion heroSpiralAttack1Frame2;
	public static TextureRegion heroSpiralAttack1Frame3;
	public static TextureRegion heroSpiralAttack1Frame4;
	
	public static Animation spiral_attack;
	public static TextureRegion spiralAttackFrame1;
	public static TextureRegion spiralAttackFrame2;
	public static TextureRegion spiralAttackFrame3;
	
	public static Animation hero_collided;
	public static TextureRegion heroCollidedFrame1;
	public static TextureRegion heroCollidedFrame2;
	public static TextureRegion heroCollidedFrame3;

	// Foreground is on the Hero Atlas
	public static TextureRegion foregroundRegion;
	
	/*
	 * Backgrounds
	 */
	
	public static Texture background_clouds;
	public static TextureRegion backgroundCloudsRegion;
	public static Texture background_back_layer;
	public static TextureRegion backgroundBackLayerRegion;
	public static Texture background_back_layer_2;
	public static TextureRegion backgroundBackLayer2Region;
	public static Texture background_middle_layer;
	public static TextureRegion backgroundMiddleLayerRegion;
	
	/*
	 * Game Sprites Atlas
	 */
	
	public static Texture game_sprites;
	public static TextureRegion platform_left;
	public static TextureRegion platform_right;
	public static TextureRegion platform_middle;
	
	public static TextureRegion JellyfishDemon;
	public static TextureRegion PurpleGhost;
	
	public static Animation RedWhaleDemon;
	public static TextureRegion RedWhaleDemonFrame1;
	public static TextureRegion RedWhaleDemonFrame2;
	public static TextureRegion RedWhaleDemonFrame3;
	public static TextureRegion RedWhaleDemonFrame4;
	public static TextureRegion RedWhaleDemonFrame5;
	
	public static Animation jellyfish_demon_motion;
	public static Animation jellyfish_demon_boost_up;
	public static Animation jellyfish_demon_float_down;
	public static TextureRegion jellyfishDemonMotionFrame1;
	public static TextureRegion jellyfishDemonMotionFrame2;
	public static TextureRegion jellyfishDemonMotionFrame3;
	public static TextureRegion jellyfishDemonMotionFrame4;
	public static TextureRegion jellyfishDemonMotionFrame5;
	public static TextureRegion jellyfishDemonMotionFrame6;
	
	public static Animation jellyfish_demon_shock_attacking;
	public static TextureRegion jellyfishDemonShockAttackFrame1;
	public static TextureRegion jellyfishDemonShockAttackFrame2;
	public static TextureRegion jellyfishDemonShockAttackFrame3;
	
	public static Animation jellyfish_demon_collided;
	public static TextureRegion jellyfishDemonCollidedFrame1;
	public static TextureRegion jellyfishDemonCollidedFrame2;
	public static TextureRegion jellyfishDemonCollidedFrame3;
	public static TextureRegion jellyfishDemonCollidedFrame4;
	
	public static Animation shockball;
	public static TextureRegion shockballFrame1;
	public static TextureRegion shockballFrame2;
	public static TextureRegion shockballFrame3;
	public static TextureRegion shockballFrame4;
	public static TextureRegion shockballFrame5;

	public static Animation flying_snake_standard;
	public static TextureRegion flyingSnakeStandardFrame1;
	public static TextureRegion flyingSnakeStandardFrame2;
	public static TextureRegion flyingSnakeStandardFrame3;
	public static TextureRegion flyingSnakeStandardFrame4;
	public static TextureRegion flyingSnakeStandardFrame5;
	
	public static Animation flying_snake_attack;
	public static TextureRegion flyingSnakeAttackChargeFrame1;
	public static TextureRegion flyingSnakeChargeAttackFrame2;
	
	public static TextureRegion flyingSnakeAttackFrame;
	
	public static Animation flying_snake_reload;
	
	/*
	 * Controller Icon Atlas
	 */
	
	public static Texture controller_icons;
	public static TextureRegion arrow_pressed;
	public static TextureRegion arrow_unpressed;
	public static TextureRegion attack_pressed;
	public static TextureRegion attack_unpressed;
	
	/*
	 * Shadow Hero Atlas
	 */
	
	public static Texture shadow_hero;
	public static TextureRegion shadow_heroRegion;
	public static TextureRegion shadow_hero_jump;
	public static TextureRegion shadow_hero_fall;
	
	public static Animation shadow_hero_land;
	public static TextureRegion shadow_heroLandFrame1;
	public static TextureRegion shadow_heroLandFrame2;
	public static TextureRegion shadow_heroLandFrame3;
	public static TextureRegion shadow_heroLandFrame4;
	
	public static Animation shadow_hero_halo_attack_1;
	public static TextureRegion shadow_heroHaloAttack1Frame1;
	public static TextureRegion shadow_heroHaloAttack1Frame2;
	public static TextureRegion shadow_heroHaloAttack1Frame3;
	public static TextureRegion shadow_heroHaloAttack1Frame4;
	public static TextureRegion shadow_heroHaloAttack1Frame5;
	public static TextureRegion shadow_heroHaloAttack1Frame6;
	public static TextureRegion shadow_heroHaloAttack1Frame7;
	public static TextureRegion shadow_heroHaloAttack1Frame8;
	public static TextureRegion shadow_heroHaloAttack1Frame9;
	public static TextureRegion shadow_heroHaloAttack1Frame10;
	public static TextureRegion shadow_heroHaloAttack1Frame11;
	public static TextureRegion shadow_heroHaloAttack1Frame12;
	public static TextureRegion shadow_heroHaloAttack1Frame13;
	public static TextureRegion shadow_herohaloattack1frame14;
	
	public static Animation shadow_halo_attack;
	public static TextureRegion shadow_haloAttackFrame1;
	public static TextureRegion shadow_haloAttackFrame2;
	
	public static Animation shadow_hero_lyre_attack_1;
	public static TextureRegion shadow_heroLyreAttack1Frame1;
	public static TextureRegion shadow_heroLyreAttack1Frame2;
	public static TextureRegion shadow_heroLyreAttack1Frame3;
	public static TextureRegion shadow_heroLyreAttack1Frame4;
	public static TextureRegion shadow_heroLyreAttack1Frame5;
	public static TextureRegion shadow_heroLyreAttack1Frame6;
	
	public static TextureRegion shadow_musicNoteFrame1;
	
	public static Animation shadow_hero_spiral_attack_1;
	public static TextureRegion shadow_heroSpiralAttack1Frame1;
	public static TextureRegion shadow_heroSpiralAttack1Frame2;
	public static TextureRegion shadow_heroSpiralAttack1Frame3;
	public static TextureRegion shadow_heroSpiralAttack1Frame4;
	
	public static Animation shadow_spiral_attack;
	public static TextureRegion shadow_spiralAttackFrame1;
	public static TextureRegion shadow_spiralAttackFrame2;
	public static TextureRegion shadow_spiralAttackFrame3;
	
	public static Animation shadow_hero_collided;
	public static TextureRegion shadow_heroCollidedFrame1;
	public static TextureRegion shadow_heroCollidedFrame2;
	public static TextureRegion shadow_heroCollidedFrame3;
	
	public static void load(GLGame game){
		
		/*
		 * Hero
		 */
		
		hero = new Texture(game, "Hero.png");
		heroRegion = new TextureRegion(hero, 0, 0, 80, 80);
		hero_jump = new TextureRegion(hero, 400, 80, 80, 80);
		hero_fall = new TextureRegion(hero, 80, 0, 80, 80);
		heroLandFrame1 = new TextureRegion(hero, 160, 0, 80, 80);
		heroLandFrame2 = new TextureRegion(hero, 240, 0, 80, 80);
		heroLandFrame3 = new TextureRegion(hero, 320, 0, 80, 80);
		heroLandFrame4 = new TextureRegion(hero, 400, 0, 80, 80);
		hero_land = new Animation(HERO_LAND_FRAME_DURATION,
				heroLandFrame1,
				heroLandFrame2,
				heroLandFrame3,
				heroLandFrame4
				);
		heroHaloAttack1Frame1 = new TextureRegion(hero, 0, 80, 80, 90);
		heroHaloAttack1Frame2 = new TextureRegion(hero, 80, 80, 80, 90);
		heroHaloAttack1Frame3 = new TextureRegion(hero, 160, 80, 80, 90);
		heroHaloAttack1Frame4 = new TextureRegion(hero, 240, 80, 80, 90);
		heroHaloAttack1Frame5 = new TextureRegion(hero, 320, 80, 80, 90);
		heroHaloAttack1Frame6 = new TextureRegion(hero, 0, 170, 80, 90);
		heroHaloAttack1Frame7 = new TextureRegion(hero, 80, 170, 80, 90);
		heroHaloAttack1Frame8 = new TextureRegion(hero, 160, 170, 80, 90);
		heroHaloAttack1Frame9 = new TextureRegion(hero, 240, 170, 80, 90);
		heroHaloAttack1Frame10 = new TextureRegion(hero, 320, 170, 80, 90);
		heroHaloAttack1Frame11 = new TextureRegion(hero, 400, 170, 80, 90);
		heroHaloAttack1Frame12 = new TextureRegion(hero, 0, 260, 80, 90);
		heroHaloAttack1Frame13 = new TextureRegion(hero, 80, 260, 80, 90);
		herohaloattack1frame14 = new TextureRegion(hero, 160, 260, 80, 90);
		hero_halo_attack_1 = new Animation(HERO_HALO_ATTACK_1_FRAME_DURATION,
				heroHaloAttack1Frame1,
				heroHaloAttack1Frame2,
				heroHaloAttack1Frame3,
				heroHaloAttack1Frame4,
				heroHaloAttack1Frame5,
				heroHaloAttack1Frame6,
				heroHaloAttack1Frame7,
				heroHaloAttack1Frame8,
				heroHaloAttack1Frame9,
				heroHaloAttack1Frame10,
				heroHaloAttack1Frame11,
				heroHaloAttack1Frame12,
				heroHaloAttack1Frame13,
				herohaloattack1frame14
				);
		haloAttackFrame1 = new TextureRegion(hero, 240, 270, 35, 20);
		haloAttackFrame2 = new TextureRegion(hero, 240, 300, 43, 20);
		halo_attack = new Animation(HALO_ATTACK_FRAME_DURATION,
				haloAttackFrame1,
				haloAttackFrame2
				);
		//1 2 3 4 5 6 5 4 5 6 5 4 5 6 5 4 5 6
		heroLyreAttack1Frame1 = new TextureRegion(hero, 80, 350, 83, 85);
		heroLyreAttack1Frame2 = new TextureRegion(hero, 163, 350, 83, 85);
		heroLyreAttack1Frame3 = new TextureRegion(hero, 246, 350, 83, 85);
		heroLyreAttack1Frame4 = new TextureRegion(hero, 329, 350, 83, 85);
		heroLyreAttack1Frame5 = new TextureRegion(hero, 412, 350, 83, 85);
		heroLyreAttack1Frame6 = new TextureRegion(hero, 0, 430, 83, 85);
		hero_lyre_attack_1 = new Animation(HERO_LYRE_ATTACK_FRAME_DURATION,
				heroLyreAttack1Frame1,
				heroLyreAttack1Frame2,
				heroLyreAttack1Frame3,
				heroLyreAttack1Frame4,
				heroLyreAttack1Frame5,
				heroLyreAttack1Frame6,
				heroLyreAttack1Frame5,
				heroLyreAttack1Frame4,
				heroLyreAttack1Frame5,
				heroLyreAttack1Frame6,
				heroLyreAttack1Frame5,
				heroLyreAttack1Frame4,
				heroLyreAttack1Frame5,
				heroLyreAttack1Frame6,
				heroLyreAttack1Frame5,
				heroLyreAttack1Frame4,
				heroLyreAttack1Frame5,
				heroLyreAttack1Frame6
				);
		
		// Foreground is on the Hero Atlas
		foregroundRegion = new TextureRegion(hero, 0, 909, 400, 115);
		
		musicNoteFrame1 = new TextureRegion(hero, 472, 304, 13, 34);
		
		heroSpiralAttack1Frame1 = new TextureRegion(hero, 90, 440, 91, 80);
		heroSpiralAttack1Frame2 = new TextureRegion(hero, 185, 440, 91, 80);
		heroSpiralAttack1Frame3 = new TextureRegion(hero, 280, 440, 91, 80);
		heroSpiralAttack1Frame4 = new TextureRegion(hero, 375, 440, 91, 80);
		hero_spiral_attack_1 = new Animation(HERO_SPIRAL_ATTACK_1_FRAME_DURATION,
				heroSpiralAttack1Frame1,
				heroSpiralAttack1Frame2,
				heroSpiralAttack1Frame3,
				heroSpiralAttack1Frame4);
		
		spiralAttackFrame1 = new TextureRegion(hero, 470, 440, 28, 28);
		spiralAttackFrame2 = new TextureRegion(hero, 470, 470, 28, 28);
		spiralAttackFrame3 = new TextureRegion(hero, 470, 500, 28, 28);
		spiral_attack = new Animation(SPIRAL_ATTACK_FRAME_DURATION,
				spiralAttackFrame1,
				spiralAttackFrame2,
				spiralAttackFrame3,
				spiralAttackFrame2);
		
		heroCollidedFrame1 = new TextureRegion(hero, 285, 260, 80, 80);
		heroCollidedFrame2 = new TextureRegion(hero, 365, 260, 80, 80);
		heroCollidedFrame3 = new TextureRegion(hero, 0, 350, 80, 80);
		hero_collided = new Animation(HERO_COLLIDED_FRAME_DURATION,
				heroCollidedFrame1,
				heroCollidedFrame2,
				heroCollidedFrame3
				);
		
		/*
		 * Shadow Hero
		 */
		
		shadow_hero = new Texture(game, "ShadowHero.png");
		shadow_heroRegion = new TextureRegion(shadow_hero, 0, 0, 80, 80);
		shadow_hero_jump = new TextureRegion(shadow_hero, 400, 80, 80, 80);
		shadow_hero_fall = new TextureRegion(shadow_hero, 80, 0, 80, 80);
		shadow_heroLandFrame1 = new TextureRegion(shadow_hero, 160, 0, 80, 80);
		shadow_heroLandFrame2 = new TextureRegion(shadow_hero, 240, 0, 80, 80);
		shadow_heroLandFrame3 = new TextureRegion(shadow_hero, 320, 0, 80, 80);
		shadow_heroLandFrame4 = new TextureRegion(shadow_hero, 400, 0, 80, 80);
		shadow_hero_land = new Animation(HERO_LAND_FRAME_DURATION,
				shadow_heroLandFrame1,
				shadow_heroLandFrame2,
				shadow_heroLandFrame3,
				shadow_heroLandFrame4
				);
		shadow_heroHaloAttack1Frame1 = new TextureRegion(shadow_hero, 0, 80, 80, 90);
		shadow_heroHaloAttack1Frame2 = new TextureRegion(shadow_hero, 80, 80, 80, 90);
		shadow_heroHaloAttack1Frame3 = new TextureRegion(shadow_hero, 160, 80, 80, 90);
		shadow_heroHaloAttack1Frame4 = new TextureRegion(shadow_hero, 240, 80, 80, 90);
		shadow_heroHaloAttack1Frame5 = new TextureRegion(shadow_hero, 320, 80, 80, 90);
		shadow_heroHaloAttack1Frame6 = new TextureRegion(shadow_hero, 0, 170, 80, 90);
		shadow_heroHaloAttack1Frame7 = new TextureRegion(shadow_hero, 80, 170, 80, 90);
		shadow_heroHaloAttack1Frame8 = new TextureRegion(shadow_hero, 160, 170, 80, 90);
		shadow_heroHaloAttack1Frame9 = new TextureRegion(shadow_hero, 240, 170, 80, 90);
		shadow_heroHaloAttack1Frame10 = new TextureRegion(shadow_hero, 320, 170, 80, 90);
		shadow_heroHaloAttack1Frame11 = new TextureRegion(shadow_hero, 400, 170, 80, 90);
		shadow_heroHaloAttack1Frame12 = new TextureRegion(shadow_hero, 0, 260, 80, 90);
		shadow_heroHaloAttack1Frame13 = new TextureRegion(shadow_hero, 80, 260, 80, 90);
		shadow_herohaloattack1frame14 = new TextureRegion(shadow_hero, 160, 260, 80, 90);
		shadow_hero_halo_attack_1 = new Animation(HERO_HALO_ATTACK_1_FRAME_DURATION,
				shadow_heroHaloAttack1Frame1,
				shadow_heroHaloAttack1Frame2,
				shadow_heroHaloAttack1Frame3,
				shadow_heroHaloAttack1Frame4,
				shadow_heroHaloAttack1Frame5,
				shadow_heroHaloAttack1Frame6,
				shadow_heroHaloAttack1Frame7,
				shadow_heroHaloAttack1Frame8,
				shadow_heroHaloAttack1Frame9,
				shadow_heroHaloAttack1Frame10,
				shadow_heroHaloAttack1Frame11,
				shadow_heroHaloAttack1Frame12,
				shadow_heroHaloAttack1Frame13,
				shadow_herohaloattack1frame14
				);
		shadow_haloAttackFrame1 = new TextureRegion(shadow_hero, 240, 270, 35, 20);
		shadow_haloAttackFrame2 = new TextureRegion(shadow_hero, 240, 300, 43, 20);
		shadow_halo_attack = new Animation(HALO_ATTACK_FRAME_DURATION,
				shadow_haloAttackFrame1,
				shadow_haloAttackFrame2
				);
		//1 2 3 4 5 6 5 4 5 6 5 4 5 6 5 4 5 6
		shadow_heroLyreAttack1Frame1 = new TextureRegion(shadow_hero, 80, 350, 83, 85);
		shadow_heroLyreAttack1Frame2 = new TextureRegion(shadow_hero, 163, 350, 83, 85);
		shadow_heroLyreAttack1Frame3 = new TextureRegion(shadow_hero, 246, 350, 83, 85);
		shadow_heroLyreAttack1Frame4 = new TextureRegion(shadow_hero, 329, 350, 83, 85);
		shadow_heroLyreAttack1Frame5 = new TextureRegion(shadow_hero, 412, 350, 83, 85);
		shadow_heroLyreAttack1Frame6 = new TextureRegion(shadow_hero, 0, 430, 83, 85);
		shadow_hero_lyre_attack_1 = new Animation(HERO_LYRE_ATTACK_FRAME_DURATION,
				shadow_heroLyreAttack1Frame1,
				shadow_heroLyreAttack1Frame2,
				shadow_heroLyreAttack1Frame3,
				shadow_heroLyreAttack1Frame4,
				shadow_heroLyreAttack1Frame5,
				shadow_heroLyreAttack1Frame6,
				shadow_heroLyreAttack1Frame5,
				shadow_heroLyreAttack1Frame4,
				shadow_heroLyreAttack1Frame5,
				shadow_heroLyreAttack1Frame6,
				shadow_heroLyreAttack1Frame5,
				shadow_heroLyreAttack1Frame4,
				shadow_heroLyreAttack1Frame5,
				shadow_heroLyreAttack1Frame6,
				shadow_heroLyreAttack1Frame5,
				shadow_heroLyreAttack1Frame4,
				shadow_heroLyreAttack1Frame5,
				shadow_heroLyreAttack1Frame6
				);
		
		shadow_musicNoteFrame1 = new TextureRegion(shadow_hero, 472, 304, 13, 34);
		
		shadow_heroSpiralAttack1Frame1 = new TextureRegion(shadow_hero, 90, 440, 91, 80);
		shadow_heroSpiralAttack1Frame2 = new TextureRegion(shadow_hero, 185, 440, 91, 80);
		shadow_heroSpiralAttack1Frame3 = new TextureRegion(shadow_hero, 280, 440, 91, 80);
		shadow_heroSpiralAttack1Frame4 = new TextureRegion(shadow_hero, 375, 440, 91, 80);
		shadow_hero_spiral_attack_1 = new Animation(HERO_SPIRAL_ATTACK_1_FRAME_DURATION,
				shadow_heroSpiralAttack1Frame1,
				shadow_heroSpiralAttack1Frame2,
				shadow_heroSpiralAttack1Frame3,
				shadow_heroSpiralAttack1Frame4);
		
		shadow_spiralAttackFrame1 = new TextureRegion(shadow_hero, 470, 440, 28, 28);
		shadow_spiralAttackFrame2 = new TextureRegion(shadow_hero, 470, 470, 28, 28);
		shadow_spiralAttackFrame3 = new TextureRegion(shadow_hero, 470, 500, 28, 28);
		shadow_spiral_attack = new Animation(SPIRAL_ATTACK_FRAME_DURATION,
				shadow_spiralAttackFrame1,
				shadow_spiralAttackFrame2,
				shadow_spiralAttackFrame3,
				shadow_spiralAttackFrame2);
		
		shadow_heroCollidedFrame1 = new TextureRegion(shadow_hero, 285, 260, 80, 80);
		shadow_heroCollidedFrame2 = new TextureRegion(shadow_hero, 365, 260, 80, 80);
		shadow_heroCollidedFrame3 = new TextureRegion(shadow_hero, 0, 350, 80, 80);
		shadow_hero_collided = new Animation(HERO_COLLIDED_FRAME_DURATION,
				shadow_heroCollidedFrame1,
				shadow_heroCollidedFrame2,
				shadow_heroCollidedFrame3
				);
		
		/*
		 * Backgrounds
		 */
		
		background_clouds = new Texture(game, "BG1.png");
//		backgroundCloudsRegion = new TextureRegion(background_clouds, 0, 0, 1200, 960);
		backgroundCloudsRegion = new TextureRegion(background_clouds, 0, 0, 800, 480);
		background_back_layer = new Texture(game, "BG_back_layer.png");
		backgroundBackLayerRegion = new TextureRegion(background_back_layer, 0, 0, 800, 480);
		background_back_layer_2 = new Texture(game, "BG_back_layer_2.png");
		backgroundBackLayer2Region = new TextureRegion(background_back_layer_2, 0, 0, 800, 480);
		background_middle_layer = new Texture(game, "BG_middle_layer.png");
		backgroundMiddleLayerRegion = new TextureRegion(background_middle_layer, 0, 0, 800, 480);
		
		game_sprites = new Texture(game, "GameSpritesALL.png");
		platform_left = new TextureRegion(game_sprites, 0, 0, 40, 20);
		platform_right = new TextureRegion(game_sprites, 0, 32, 40, 20);
		platform_middle = new TextureRegion(game_sprites, 0, 64, 40, 20);

		JellyfishDemon = new TextureRegion(game_sprites, 50, 0, 64, 64);
		PurpleGhost = new TextureRegion(game_sprites, 0, 300, 64, 64);
		
		RedWhaleDemonFrame1 = new TextureRegion(game_sprites, 0, 140, 100, 80);
		RedWhaleDemonFrame2 = new TextureRegion(game_sprites, 100, 140, 100, 80);
		RedWhaleDemonFrame3 = new TextureRegion(game_sprites, 200, 140, 100, 80);
		RedWhaleDemonFrame4 = new TextureRegion(game_sprites, 300, 140, 100, 80);
		RedWhaleDemonFrame5 = new TextureRegion(game_sprites, 400, 140, 100, 80);
		RedWhaleDemon = new Animation(RED_WHALE_DEMON_FRAME_DURATION,
				RedWhaleDemonFrame1,
				RedWhaleDemonFrame2,
				RedWhaleDemonFrame3,
				RedWhaleDemonFrame4,
				RedWhaleDemonFrame5,
				RedWhaleDemonFrame4,
				RedWhaleDemonFrame3,
				RedWhaleDemonFrame2,
				RedWhaleDemonFrame1
				);
		
		jellyfishDemonMotionFrame1 = new TextureRegion(game_sprites, 50, 0, 64, 83);
		jellyfishDemonMotionFrame2 = new TextureRegion(game_sprites, 114, 0, 64, 83);
		jellyfishDemonMotionFrame3 = new TextureRegion(game_sprites, 178, 0, 64, 83);
		jellyfishDemonMotionFrame4 = new TextureRegion(game_sprites, 242, 0, 64, 83);
		jellyfishDemonMotionFrame5 = new TextureRegion(game_sprites, 306, 0, 64, 83);
		jellyfishDemonMotionFrame6 = new TextureRegion(game_sprites, 370, 0, 64, 83);
		jellyfish_demon_motion = new Animation(JELLYFISH_DEMON_MOTION_FRAME_DURATION,
				jellyfishDemonMotionFrame1,
				jellyfishDemonMotionFrame2,
				jellyfishDemonMotionFrame3,
				jellyfishDemonMotionFrame4,
				jellyfishDemonMotionFrame5,
				jellyfishDemonMotionFrame6,
				jellyfishDemonMotionFrame6,
				jellyfishDemonMotionFrame5,
				jellyfishDemonMotionFrame4,
				jellyfishDemonMotionFrame3,
				jellyfishDemonMotionFrame2,
				jellyfishDemonMotionFrame1
				);
		jellyfish_demon_boost_up = new Animation(JELLYFISH_DEMON_MOTION_FRAME_DURATION,
				jellyfishDemonMotionFrame2,
				jellyfishDemonMotionFrame3,
				jellyfishDemonMotionFrame4,
				jellyfishDemonMotionFrame5,
				jellyfishDemonMotionFrame6,
				jellyfishDemonMotionFrame6,
				jellyfishDemonMotionFrame5
				);
		 jellyfish_demon_float_down= new Animation(JELLYFISH_DEMON_MOTION_FRAME_DURATION,
					jellyfishDemonMotionFrame4,
					jellyfishDemonMotionFrame3,
					jellyfishDemonMotionFrame2,
					jellyfishDemonMotionFrame1,
					jellyfishDemonMotionFrame1					
					);
		
		
		jellyfishDemonShockAttackFrame1 = new TextureRegion(game_sprites, 434, 0, 70, 87);
		jellyfishDemonShockAttackFrame2 = new TextureRegion(game_sprites, 504, 0, 70, 87);
		jellyfishDemonShockAttackFrame3 = new TextureRegion(game_sprites, 574, 0, 70, 87);
		jellyfish_demon_shock_attacking = new Animation(JELLYFISH_DEMON_SHOCK_ATTACK_FRAME_DURATION,
				jellyfishDemonShockAttackFrame1,
				jellyfishDemonShockAttackFrame2,
				jellyfishDemonShockAttackFrame3
				);
		
		jellyfishDemonCollidedFrame1 = new TextureRegion(game_sprites, 740, 130, 64, 70);
		jellyfishDemonCollidedFrame2 = new TextureRegion(game_sprites, 804, 130, 64, 70);
		jellyfishDemonCollidedFrame3 = new TextureRegion(game_sprites, 868, 130, 64, 70);
		jellyfishDemonCollidedFrame4 = new TextureRegion(game_sprites, 932, 130, 64, 70);
		jellyfish_demon_collided = new Animation(JELLYFISH_DEMON_COLLIDED_FRAME_DURATION,
				jellyfishDemonCollidedFrame1,
				jellyfishDemonCollidedFrame2,
				jellyfishDemonCollidedFrame3,
				jellyfishDemonCollidedFrame4
				);
		
		shockballFrame1 = new TextureRegion(game_sprites, 644, 0, 120, 130);
		shockballFrame2 = new TextureRegion(game_sprites, 764, 0, 120, 130);
		shockballFrame3 = new TextureRegion(game_sprites, 884, 0, 120, 130);
		shockballFrame4 = new TextureRegion(game_sprites, 500, 130, 120, 130);
		shockballFrame5 = new TextureRegion(game_sprites, 620, 130, 120, 130);
		shockball = new Animation(SHOCKBALL_FRAME_DURATION,
				shockballFrame1,
				shockballFrame2,
				shockballFrame3,
				shockballFrame4,
				shockballFrame5,
				shockballFrame4,
				shockballFrame3,
				shockballFrame2
				);
		
		flyingSnakeStandardFrame1 = new TextureRegion(game_sprites, 0, 260, 180, 86);
		flyingSnakeStandardFrame2 = new TextureRegion(game_sprites, 180, 260, 180, 86);
		flyingSnakeStandardFrame3 = new TextureRegion(game_sprites, 360, 260, 180, 86);
		flyingSnakeStandardFrame4 = new TextureRegion(game_sprites, 540, 260, 180, 86);
		flyingSnakeStandardFrame5 = new TextureRegion(game_sprites, 720, 260, 180, 86);
		flying_snake_standard = new Animation(FLYING_SNAKE_STANDARD_FRAME_DURATION,
				flyingSnakeStandardFrame1,
				flyingSnakeStandardFrame2,
				flyingSnakeStandardFrame3,
				flyingSnakeStandardFrame2,
				flyingSnakeStandardFrame1,
				flyingSnakeStandardFrame4,
				flyingSnakeStandardFrame5,
				flyingSnakeStandardFrame4
				);
		
		flyingSnakeAttackChargeFrame1 = new TextureRegion(game_sprites, 0, 350, 141, 129);
		flyingSnakeChargeAttackFrame2 = new TextureRegion(game_sprites, 141, 350, 141, 129);
		flyingSnakeAttackFrame = new TextureRegion(game_sprites, 282, 350, 141, 129);
		flying_snake_attack = new Animation(FLYING_SNAKE_ATTACK_FRAME_DURATION,
				flyingSnakeAttackChargeFrame1,
				flyingSnakeChargeAttackFrame2
				);
		
		flying_snake_reload = new Animation(FLYING_SNAKE_STANDARD_FRAME_DURATION,
				flyingSnakeStandardFrame1,
				flyingSnakeStandardFrame2,
				flyingSnakeStandardFrame3,
				flyingSnakeStandardFrame2
				);
		
		controller_icons = new Texture(game, "Icons.png");
		arrow_unpressed = new TextureRegion(controller_icons, 0, 0, 240, 200);
		arrow_pressed = new TextureRegion(controller_icons, 0, 200, 240, 200);
		attack_unpressed = new TextureRegion(controller_icons, 240, 0, 240, 200);
		attack_pressed = new TextureRegion(controller_icons, 240, 200, 240, 200);
	}
	
	public static void reload(){
		game_sprites.reload();
		hero.reload();
		shadow_hero.reload();
		background_clouds.reload();
		background_back_layer.reload();
		background_back_layer_2.reload();
		background_middle_layer.reload();
		controller_icons.reload();
		
		if(Settings.soundEnabled)
			//turn on music
			;
	}
	
	public static void playSound(Sound sound){
		if(Settings.soundEnabled)
			//play effects
			;
	}
}

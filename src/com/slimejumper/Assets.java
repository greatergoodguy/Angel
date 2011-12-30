package com.slimejumper;

import com.slimejumper.framework.impl.GLGame;
import com.slimejumper.gameframework.Music;
import com.slimejumper.gameframework.Sound;
import com.slimejumper.gameframework.gl.Animation;
import com.slimejumper.gameframework.gl.Texture;
import com.slimejumper.gameframework.gl.TextureRegion;

public class Assets {
	public final static float DEFAULT_FRAME_DURATION = 0.08f;
	
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
	
	public static Texture hero_full_HP;
	public static TextureRegion heroRegion;
	public static TextureRegion hero_jump_old;
	public static TextureRegion hero_fall;
	
	public static Animation hero_land;
	public static TextureRegion heroLandFrame1;
	public static TextureRegion heroLandFrame2;
	public static TextureRegion heroLandFrame3;
	public static TextureRegion heroLandFrame4;
	
	public static Animation hero_jump;
	public static TextureRegion heroJumpFrame1;
	public static TextureRegion heroJumpFrame2;
	public static TextureRegion heroJumpFrame3;
	
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
	public static Texture background_caves;
	public static Texture background_treetop_valley;
	
	/*
	 * Game Sprites Atlas
	 */
	
	public static Texture game_sprites;
	public static TextureRegion PlatformLeft;
	public static TextureRegion PlatformRight;
	public static TextureRegion PlatformMiddle;
	
	public static TextureRegion RockPlatform;
	
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
	

	public static TextureRegion cloudPlatformBackLayer;
	public static TextureRegion cloudPlatformFrontLayer;
	
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

	/*
	 * Music
	 */
	
	public static Music test_music;
	
	public static void load(GLGame game){
		
		/*
		 * Hero
		 */
		
		hero_full_HP = new Texture(game, "Hero.png");
		heroRegion = new TextureRegion(hero_full_HP, 0, 0, 80, 80);
		hero_jump_old = new TextureRegion(hero_full_HP, 400, 80, 80, 80);
		
		hero_fall = new TextureRegion(hero_full_HP, 80, 0, 80, 80);
		heroLandFrame1 = new TextureRegion(hero_full_HP, 160, 0, 80, 80);
		heroLandFrame2 = new TextureRegion(hero_full_HP, 240, 0, 80, 80);
		heroLandFrame3 = new TextureRegion(hero_full_HP, 320, 0, 80, 80);
		heroLandFrame4 = new TextureRegion(hero_full_HP, 400, 0, 80, 80);
		hero_land = new Animation(HERO_LAND_FRAME_DURATION,
				heroLandFrame1,
				heroLandFrame2,
				heroLandFrame3,
				heroLandFrame4
				);
		
		heroJumpFrame1 = new TextureRegion(hero_full_HP, 240, 0, 80, 80);
		heroJumpFrame2 = new TextureRegion(hero_full_HP, 320, 0, 80, 80);
		heroJumpFrame3 = new TextureRegion(hero_full_HP, 400, 80, 80, 80);
		
		hero_jump = new Animation(DEFAULT_FRAME_DURATION,
				heroJumpFrame1,
				heroJumpFrame2,
				heroJumpFrame3
				);
		
		heroHaloAttack1Frame1 = new TextureRegion(hero_full_HP, 0, 80, 80, 90);
		heroHaloAttack1Frame2 = new TextureRegion(hero_full_HP, 80, 80, 80, 90);
		heroHaloAttack1Frame3 = new TextureRegion(hero_full_HP, 160, 80, 80, 90);
		heroHaloAttack1Frame4 = new TextureRegion(hero_full_HP, 240, 80, 80, 90);
		heroHaloAttack1Frame5 = new TextureRegion(hero_full_HP, 320, 80, 80, 90);
		heroHaloAttack1Frame6 = new TextureRegion(hero_full_HP, 0, 170, 80, 90);
		heroHaloAttack1Frame7 = new TextureRegion(hero_full_HP, 80, 170, 80, 90);
		heroHaloAttack1Frame8 = new TextureRegion(hero_full_HP, 160, 170, 80, 90);
		heroHaloAttack1Frame9 = new TextureRegion(hero_full_HP, 240, 170, 80, 90);
		heroHaloAttack1Frame10 = new TextureRegion(hero_full_HP, 320, 170, 80, 90);
		heroHaloAttack1Frame11 = new TextureRegion(hero_full_HP, 400, 170, 80, 90);
		heroHaloAttack1Frame12 = new TextureRegion(hero_full_HP, 0, 260, 80, 90);
		heroHaloAttack1Frame13 = new TextureRegion(hero_full_HP, 80, 260, 80, 90);
		herohaloattack1frame14 = new TextureRegion(hero_full_HP, 160, 260, 80, 90);
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
		haloAttackFrame1 = new TextureRegion(hero_full_HP, 240, 270, 35, 20);
		haloAttackFrame2 = new TextureRegion(hero_full_HP, 240, 300, 35, 20);
		halo_attack = new Animation(HALO_ATTACK_FRAME_DURATION,
				haloAttackFrame1,
				haloAttackFrame2
				);
		//1 2 3 4 5 6 5 4 5 6 5 4 5 6 5 4 5 6
		heroLyreAttack1Frame1 = new TextureRegion(hero_full_HP, 80, 350, 83, 85);
		heroLyreAttack1Frame2 = new TextureRegion(hero_full_HP, 163, 350, 83, 85);
		heroLyreAttack1Frame3 = new TextureRegion(hero_full_HP, 246, 350, 83, 85);
		heroLyreAttack1Frame4 = new TextureRegion(hero_full_HP, 329, 350, 83, 85);
		heroLyreAttack1Frame5 = new TextureRegion(hero_full_HP, 412, 350, 83, 85);
		heroLyreAttack1Frame6 = new TextureRegion(hero_full_HP, 0, 430, 83, 85);
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
		foregroundRegion = new TextureRegion(hero_full_HP, 0, 909, 400, 115);
		
		musicNoteFrame1 = new TextureRegion(hero_full_HP, 472, 304, 13, 34);
		
		heroSpiralAttack1Frame1 = new TextureRegion(hero_full_HP, 90, 440, 91, 80);
		heroSpiralAttack1Frame2 = new TextureRegion(hero_full_HP, 185, 440, 91, 80);
		heroSpiralAttack1Frame3 = new TextureRegion(hero_full_HP, 280, 440, 91, 80);
		heroSpiralAttack1Frame4 = new TextureRegion(hero_full_HP, 375, 440, 91, 80);
		hero_spiral_attack_1 = new Animation(HERO_SPIRAL_ATTACK_1_FRAME_DURATION,
				heroSpiralAttack1Frame1,
				heroSpiralAttack1Frame2,
				heroSpiralAttack1Frame3,
				heroSpiralAttack1Frame4);
		
		spiralAttackFrame1 = new TextureRegion(hero_full_HP, 470, 440, 28, 28);
		spiralAttackFrame2 = new TextureRegion(hero_full_HP, 470, 470, 28, 28);
		spiralAttackFrame3 = new TextureRegion(hero_full_HP, 470, 500, 28, 28);
		spiral_attack = new Animation(SPIRAL_ATTACK_FRAME_DURATION,
				spiralAttackFrame1,
				spiralAttackFrame2,
				spiralAttackFrame3,
				spiralAttackFrame2);
		
		heroCollidedFrame1 = new TextureRegion(hero_full_HP, 285, 260, 80, 80);
		heroCollidedFrame2 = new TextureRegion(hero_full_HP, 365, 260, 80, 80);
		heroCollidedFrame3 = new TextureRegion(hero_full_HP, 0, 350, 80, 80);
		hero_collided = new Animation(HERO_COLLIDED_FRAME_DURATION,
				heroCollidedFrame1,
				heroCollidedFrame2,
				heroCollidedFrame3
				);
		
		/*
		 * Shadow Hero
		 */
		
		shadow_hero = new Texture(game, "ShadowHero.png");
		
		/*
		 * Backgrounds
		 */
		
		background_clouds = new Texture(game, "BG1.png");
		background_caves = new Texture(game, "CaveBG.png");		
		background_treetop_valley = new Texture(game, "Level1BG.png");
		
		/*
		 * Game Sprites
		 */
		
		game_sprites = new Texture(game, "GameSpritesALL.png");
		PlatformLeft = new TextureRegion(game_sprites, 0, 0, 40, 20);
		PlatformRight = new TextureRegion(game_sprites, 0, 32, 40, 20);
		PlatformMiddle = new TextureRegion(game_sprites, 0, 64, 40, 20);
		
		RockPlatform = new TextureRegion(game_sprites, 50, 90, 160, 30);

		JellyfishDemon = new TextureRegion(game_sprites, 50, 0, 64, 64);
		PurpleGhost = new TextureRegion(game_sprites, 940, 220, 64, 64);
		
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
		
		cloudPlatformBackLayer = new TextureRegion(game_sprites, 450, 360, 290, 110);
		cloudPlatformFrontLayer = new TextureRegion(game_sprites, 450, 465, 290, 42);
		
		/*
		 * Controller
		 */
		
		controller_icons = new Texture(game, "Icons.png");
		arrow_unpressed = new TextureRegion(controller_icons, 0, 0, 240, 200);
		arrow_pressed = new TextureRegion(controller_icons, 0, 200, 240, 200);
		attack_unpressed = new TextureRegion(controller_icons, 240, 0, 240, 200);
		attack_pressed = new TextureRegion(controller_icons, 240, 200, 240, 200);
		
		/*
		 * Music
		 */
		
		test_music = game.getAudio().newMusic("test_music.ogg");
		test_music.setLooping(true);
		test_music.setVolume(0.5f);
	}
	
	public static void reload(){
		game_sprites.reload();
		hero_full_HP.reload();
		shadow_hero.reload();
		background_treetop_valley.reload();
		background_clouds.reload();
		background_caves.reload();
		controller_icons.reload();
	
	}
	
	public static void playSound(Sound sound){
		if(Settings.soundEnabled)
			sound.play(1);
	}
}

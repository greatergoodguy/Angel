package com.slimejumper;

import com.slimejumper.framework.impl.GLGame;
import com.slimejumper.gameframework.Music;
import com.slimejumper.gameframework.Sound;
import com.slimejumper.gameframework.gl.Animation;
import com.slimejumper.gameframework.gl.Texture;
import com.slimejumper.gameframework.gl.TextureRegion;

public class Assets {
	public final static float FAST_FRAME_DURATION = 0.08f;
	public final static float DEFAULT_FRAME_DURATION = 0.15f;
	
	public final static float HERO_HALO_ATTACK_1_FRAME_DURATION = 0.07f;
	public final static float HERO_LYRE_ATTACK_FRAME_DURATION = 0.15f;
	public final static float HERO_SPIRAL_ATTACK_1_FRAME_DURATION = 0.10f;
	
	public final static float HERO_LAND_FRAME_DURATION = 0.04f;
	public final static float HERO_COLLIDED_FRAME_DURATION = 0.1f;
	
	public final static float RED_WHALE_DEMON_FRAME_DURATION = 0.20f;
	
	public final static float JELLYFISH_DEMON_MOTION_FRAME_DURATION = 0.14f;
	public final static float JELLYFISH_DEMON_SHOCK_ATTACK_FRAME_DURATION = 0.11f;
	public final static int JELLYFISH_DEMON_MOTION_FRAME_COUNT = 12;
	public final static int JELLYFISH_DEMON_MOTION_FRAME_COUNT_MINUS_ONE = 11;
	public final static float JELLYFISH_DEMON_COLLIDED_FRAME_DURATION = 0.1f;
	
	public final static float FLYING_SNAKE_STANDARD_FRAME_DURATION = 0.15f;
	public final static float FLYING_SNAKE_ATTACK_FRAME_DURATION = 0.20f;
	
	public final static float PURPLE_GHOST_STANDARD_FRAME_DURATION = 0.20f;
	public final static float PURPLE_GHOST_ATTACK_FRAME_DURATION = 0.15f;
	
	public final static float SHOCKBALL_FRAME_DURATION = 0.14f;
	public final static float HALO_ATTACK_FRAME_DURATION = 0.12f;
	public final static float SPIRAL_ATTACK_FRAME_DURATION = 0.20f;
	
	/*
	 * Hero Atlas
	 */
	
	public static Texture heroHealthy;
	public static Texture heroHalfHP;
	public static Texture heroLowHP;
	public static Texture heroCriticalHPLeft;
	public static Texture heroCriticalHPRight;
	
	public static TextureRegion heroRegion;
	public static TextureRegion hero_jump_old;
	
	public static TextureRegion hero_fall;
	
	public static Animation hero_jump;
	public static TextureRegion heroJumpFrame1;
	public static TextureRegion heroJumpFrame2;
	public static TextureRegion heroJumpFrame3;
	
	public static Animation hero_land;
	public static TextureRegion heroLandFrame1;
	public static TextureRegion heroLandFrame2;
	public static TextureRegion heroLandFrame3;
	public static TextureRegion heroLandFrame4;
	
	/*
	 * Hero 4/5 HP Texture Regions
	 */
	public static TextureRegion hero_fall_minor_damage; 
	
	public static Animation hero_jump_minor_damage;
	public static TextureRegion heroJumpMinorDamageFrame1;
	public static TextureRegion heroJumpMinorDamageFrame2;
	public static TextureRegion heroJumpMinorDamageFrame3;
	//////////////////////////////
	
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
	
	public static Animation angelic_flame_charge;
	public static Animation angelic_flame_blast;
	public static TextureRegion angelicFlameFrame1;
	public static TextureRegion angelicFlameFrame2;
	public static TextureRegion angelicFlameFrame3;
	public static TextureRegion angelicFlameFrame4;
	
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
	 * Game Sprites 1
	 */
	
	public static Texture game_sprites_1;
	public static TextureRegion PlatformLeft;
	public static TextureRegion PlatformRight;
	public static TextureRegion PlatformMiddle;
	
	public static TextureRegion RockPlatform;
	
	public static TextureRegion JellyfishDemon;
	public static TextureRegion PurpleGhost;
	
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
	public static TextureRegion cloudPlatformShort;
	
	/*
	 * Game Sprites 2
	 */
	
	public static Texture game_sprites_2;
	
	public static Animation purple_ghost_attack;
	public static TextureRegion purpleGhostAttackFrame1;
	public static TextureRegion purpleGhostAttackFrame2;
	public static TextureRegion purpleGhostAttackFrame3;
	public static TextureRegion purpleGhostAttackFrame4;
	public static TextureRegion purpleGhostAttackFrame5;
	
	public static Animation purple_ghost_standard;
	public static TextureRegion purpleGhostStandardFrame1;
	public static TextureRegion purpleGhostStandardFrame2;
	public static TextureRegion purpleGhostStandardFrame3;	
	
	public static Animation purple_flame_attack;
	public static TextureRegion FlameAttackFrame1;
	public static TextureRegion FlameAttackFrame2;
	public static TextureRegion FlameAttackFrame3;
	
	public static Animation purple_ghost_dancing_1;
	public static Animation purple_ghost_dancing_2;
	public static TextureRegion purpleGhostDanceFrame1;
	public static TextureRegion purpleGhostDanceFrame2;
	public static TextureRegion purpleGhostDanceFrame3;
		
	public static Animation red_whale_demon_float;
	public static TextureRegion redWhaleDemonFloatFrame1;
	public static TextureRegion redWhaleDemonFloatFrame2;
	public static TextureRegion redWhaleDemonFloatFrame3;
	
	public static Animation red_whale_demon_charge_up;
	public static TextureRegion redWhaleDemonChargeUpFrame1;
	public static TextureRegion redWhaleDemonChargeUpFrame2;
	
	public static Animation red_whale_demon_charge_attack;
	public static TextureRegion redWhaleDemonChargeAttackFrame1;
	public static TextureRegion redWhaleDemonChargeAttackFrame2;
	
	public static Animation purple_ghost_collision;
	public static TextureRegion purpleGhostCollisionFrame1;
	public static TextureRegion purpleGhostCollisionFrame2;
	public static TextureRegion purpleGhostCollisionFrame3;
	public static TextureRegion purpleGhostCollisionFrame4;
	public static TextureRegion purpleGhostCollisionFrame5;
	
	public static Animation red_whale_demon_collision;
	public static TextureRegion redWhaleDemonCollisionFrame1;
	public static TextureRegion redWhaleDemonCollisionFrame2;
	public static TextureRegion redWhaleDemonCollisionFrame3;
	public static TextureRegion redWhaleDemonCollisionFrame4;
	public static TextureRegion redWhaleDemonCollisionFrame5;
	
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
		
		heroHealthy = new Texture(game, "Hero.png");
		heroHalfHP = new Texture(game, "HeroHalfHP.png");
		heroLowHP = new Texture(game, "HeroLowHP.png");
		heroCriticalHPLeft = new Texture(game, "HeroCriticalHPLeft.png");
		heroCriticalHPRight = new Texture(game, "HeroCriticalHPRight.png");
		
		/*
		 * When Hero is at 4/5 HP
		 */
		
		hero_fall_minor_damage = new TextureRegion(heroHealthy, 0, 530, 80, 80);
		
		heroJumpMinorDamageFrame1 = new TextureRegion(heroHealthy, 80, 530, 80, 80);
		heroJumpMinorDamageFrame2 = new TextureRegion(heroHealthy, 160, 530, 80, 80);
		heroJumpMinorDamageFrame3 = new TextureRegion(heroHealthy, 240, 530, 80, 80);
		hero_jump_minor_damage = new Animation(FAST_FRAME_DURATION,
				heroJumpMinorDamageFrame1,
				heroJumpMinorDamageFrame3,
				heroJumpMinorDamageFrame2
				); 
		//////////////////////////
		
		heroRegion = new TextureRegion(heroHealthy, 0, 0, 80, 80);
		hero_jump_old = new TextureRegion(heroHealthy, 400, 80, 80, 80);
		
		hero_fall = new TextureRegion(heroHealthy, 80, 0, 80, 80);
		
		heroJumpFrame1 = new TextureRegion(heroHealthy, 240, 0, 80, 80);
		heroJumpFrame2 = new TextureRegion(heroHealthy, 320, 0, 80, 80);
		heroJumpFrame3 = new TextureRegion(heroHealthy, 400, 80, 80, 80);
		hero_jump = new Animation(FAST_FRAME_DURATION,
				heroJumpFrame1,
				heroJumpFrame2,
				heroJumpFrame3
				);
		
		heroLandFrame1 = new TextureRegion(heroHealthy, 160, 0, 80, 80);
		heroLandFrame2 = new TextureRegion(heroHealthy, 240, 0, 80, 80);
		heroLandFrame3 = new TextureRegion(heroHealthy, 320, 0, 80, 80);
		heroLandFrame4 = new TextureRegion(heroHealthy, 400, 0, 80, 80);
		hero_land = new Animation(HERO_LAND_FRAME_DURATION,
				heroLandFrame1,
				heroLandFrame2,
				heroLandFrame3,
				heroLandFrame4
				);
		
		heroHaloAttack1Frame1 = new TextureRegion(heroHealthy, 0, 80, 80, 90);
		heroHaloAttack1Frame2 = new TextureRegion(heroHealthy, 80, 80, 80, 90);
		heroHaloAttack1Frame3 = new TextureRegion(heroHealthy, 160, 80, 80, 90);
		heroHaloAttack1Frame4 = new TextureRegion(heroHealthy, 240, 80, 80, 90);
		heroHaloAttack1Frame5 = new TextureRegion(heroHealthy, 320, 80, 80, 90);
		heroHaloAttack1Frame6 = new TextureRegion(heroHealthy, 0, 170, 80, 90);
		heroHaloAttack1Frame7 = new TextureRegion(heroHealthy, 80, 170, 80, 90);
		heroHaloAttack1Frame8 = new TextureRegion(heroHealthy, 160, 170, 80, 90);
		heroHaloAttack1Frame9 = new TextureRegion(heroHealthy, 240, 170, 80, 90);
		heroHaloAttack1Frame10 = new TextureRegion(heroHealthy, 320, 170, 80, 90);
		heroHaloAttack1Frame11 = new TextureRegion(heroHealthy, 400, 170, 80, 90);
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
				heroHaloAttack1Frame11
//				,heroHaloAttack1Frame12,
//				heroHaloAttack1Frame13,
//				herohaloattack1frame14
				);
		haloAttackFrame1 = new TextureRegion(heroHealthy, 240, 270, 35, 20);
		haloAttackFrame2 = new TextureRegion(heroHealthy, 240, 300, 35, 20);
		halo_attack = new Animation(HALO_ATTACK_FRAME_DURATION,
				haloAttackFrame1,
				haloAttackFrame2
				);
		//1 2 3 4 5 6 5 4 5 6 5 4 5 6 5 4 5 6
		heroLyreAttack1Frame1 = new TextureRegion(heroHealthy, 80, 350, 83, 85);
		heroLyreAttack1Frame2 = new TextureRegion(heroHealthy, 163, 350, 83, 85);
		heroLyreAttack1Frame3 = new TextureRegion(heroHealthy, 246, 350, 83, 85);
		heroLyreAttack1Frame4 = new TextureRegion(heroHealthy, 329, 350, 83, 85);
		heroLyreAttack1Frame5 = new TextureRegion(heroHealthy, 412, 350, 83, 85);
		heroLyreAttack1Frame6 = new TextureRegion(heroHealthy, 0, 430, 83, 85);
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
		foregroundRegion = new TextureRegion(heroHealthy, 0, 909, 400, 115);
		
		musicNoteFrame1 = new TextureRegion(heroHealthy, 472, 304, 13, 34);
		
		heroSpiralAttack1Frame1 = new TextureRegion(heroHealthy, 90, 440, 91, 80);
		heroSpiralAttack1Frame2 = new TextureRegion(heroHealthy, 185, 440, 91, 80);
		heroSpiralAttack1Frame3 = new TextureRegion(heroHealthy, 280, 440, 91, 80);
		heroSpiralAttack1Frame4 = new TextureRegion(heroHealthy, 375, 440, 91, 80);
		hero_spiral_attack_1 = new Animation(HERO_SPIRAL_ATTACK_1_FRAME_DURATION,
				heroSpiralAttack1Frame1,
				heroSpiralAttack1Frame2,
				heroSpiralAttack1Frame3,
				heroSpiralAttack1Frame4);
		
		spiralAttackFrame1 = new TextureRegion(heroHealthy, 470, 440, 28, 28);
		spiralAttackFrame2 = new TextureRegion(heroHealthy, 470, 470, 28, 28);
		spiralAttackFrame3 = new TextureRegion(heroHealthy, 470, 500, 28, 28);
		spiral_attack = new Animation(SPIRAL_ATTACK_FRAME_DURATION,
				spiralAttackFrame1,
				spiralAttackFrame2,
				spiralAttackFrame3,
				spiralAttackFrame2);
		
		angelicFlameFrame1 = new TextureRegion(heroHealthy, 290, 270, 52, 26);
		angelicFlameFrame2 = new TextureRegion(heroHealthy, 290, 296, 52, 26);
		angelicFlameFrame3 = new TextureRegion(heroHealthy, 342, 270, 52, 26);
		angelicFlameFrame4 = new TextureRegion(heroHealthy, 342, 296, 52, 26);
		angelic_flame_charge = new Animation(0.1f,
				angelicFlameFrame1,
				angelicFlameFrame2
				);
		angelic_flame_blast = new Animation(DEFAULT_FRAME_DURATION,
				angelicFlameFrame3,
				angelicFlameFrame4
				);
		
		//heroCollidedFrame1 = new TextureRegion(heroHealthy, 285, 260, 80, 80);
		//heroCollidedFrame2 = new TextureRegion(heroHealthy, 365, 260, 80, 80);
		//heroCollidedFrame3 = new TextureRegion(heroHealthy, 0, 350, 80, 80);
		
		heroCollidedFrame1 = new TextureRegion(heroHealthy, 0, 260, 71, 90);
		heroCollidedFrame2 = new TextureRegion(heroHealthy, 75, 260, 71, 90);
		heroCollidedFrame3 = new TextureRegion(heroHealthy, 150, 260, 71, 90);
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
		 * Game Sprites 1
		 */
		
		game_sprites_1 = new Texture(game, "GameSprites1.png");
		PlatformLeft = new TextureRegion(game_sprites_1, 0, 0, 40, 20);
		PlatformRight = new TextureRegion(game_sprites_1, 0, 32, 40, 20);
		PlatformMiddle = new TextureRegion(game_sprites_1, 0, 64, 40, 20);
		
		RockPlatform = new TextureRegion(game_sprites_1, 50, 90, 160, 30);

		JellyfishDemon = new TextureRegion(game_sprites_1, 50, 0, 64, 64);
		PurpleGhost = new TextureRegion(game_sprites_1, 940, 220, 64, 64);
		
		jellyfishDemonMotionFrame1 = new TextureRegion(game_sprites_1, 50, 0, 64, 83);
		jellyfishDemonMotionFrame2 = new TextureRegion(game_sprites_1, 114, 0, 64, 83);
		jellyfishDemonMotionFrame3 = new TextureRegion(game_sprites_1, 178, 0, 64, 83);
		jellyfishDemonMotionFrame4 = new TextureRegion(game_sprites_1, 242, 0, 64, 83);
		jellyfishDemonMotionFrame5 = new TextureRegion(game_sprites_1, 306, 0, 64, 83);
		jellyfishDemonMotionFrame6 = new TextureRegion(game_sprites_1, 370, 0, 64, 83);
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
		
		
		jellyfishDemonShockAttackFrame1 = new TextureRegion(game_sprites_1, 434, 0, 70, 87);
		jellyfishDemonShockAttackFrame2 = new TextureRegion(game_sprites_1, 504, 0, 70, 87);
		jellyfishDemonShockAttackFrame3 = new TextureRegion(game_sprites_1, 574, 0, 70, 87);
		jellyfish_demon_shock_attacking = new Animation(JELLYFISH_DEMON_SHOCK_ATTACK_FRAME_DURATION,
			jellyfishDemonShockAttackFrame1,
			jellyfishDemonShockAttackFrame2,
			jellyfishDemonShockAttackFrame3
			);
		
		jellyfishDemonCollidedFrame1 = new TextureRegion(game_sprites_1, 740, 130, 64, 70);
		jellyfishDemonCollidedFrame2 = new TextureRegion(game_sprites_1, 804, 130, 64, 70);
		jellyfishDemonCollidedFrame3 = new TextureRegion(game_sprites_1, 868, 130, 64, 70);
		jellyfishDemonCollidedFrame4 = new TextureRegion(game_sprites_1, 932, 130, 64, 70);
		jellyfish_demon_collided = new Animation(JELLYFISH_DEMON_COLLIDED_FRAME_DURATION,
			jellyfishDemonCollidedFrame1,
			jellyfishDemonCollidedFrame2,
			jellyfishDemonCollidedFrame3,
			jellyfishDemonCollidedFrame4
			);
		
		shockballFrame1 = new TextureRegion(game_sprites_1, 644, 0, 120, 130);
		shockballFrame2 = new TextureRegion(game_sprites_1, 764, 0, 120, 130);
		shockballFrame3 = new TextureRegion(game_sprites_1, 884, 0, 120, 130);
		shockballFrame4 = new TextureRegion(game_sprites_1, 500, 130, 120, 130);
		shockballFrame5 = new TextureRegion(game_sprites_1, 620, 130, 120, 130);
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
		
		flyingSnakeStandardFrame1 = new TextureRegion(game_sprites_1, 0, 260, 180, 86);
		flyingSnakeStandardFrame2 = new TextureRegion(game_sprites_1, 180, 260, 180, 86);
		flyingSnakeStandardFrame3 = new TextureRegion(game_sprites_1, 360, 260, 180, 86);
		flyingSnakeStandardFrame4 = new TextureRegion(game_sprites_1, 540, 260, 180, 86);
		flyingSnakeStandardFrame5 = new TextureRegion(game_sprites_1, 720, 260, 180, 86);
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
		
		flyingSnakeAttackChargeFrame1 = new TextureRegion(game_sprites_1, 0, 350, 141, 129);
		flyingSnakeChargeAttackFrame2 = new TextureRegion(game_sprites_1, 141, 350, 141, 129);
		flyingSnakeAttackFrame = new TextureRegion(game_sprites_1, 282, 350, 141, 129);
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
		
		cloudPlatformBackLayer = new TextureRegion(game_sprites_1, 450, 360, 290, 110);
		cloudPlatformFrontLayer = new TextureRegion(game_sprites_1, 450, 469, 290, 42);
		cloudPlatformShort = new TextureRegion(game_sprites_1, 740, 360, 100, 38);
		
		/*
		 * Game Sprites 2
		 */
		
		game_sprites_2 = new Texture(game, "GameSprites2.png");
		
		purpleGhostAttackFrame1 = new TextureRegion(game_sprites_2, 90, 0, 116, 95);
		purpleGhostAttackFrame2 = new TextureRegion(game_sprites_2, 206, 0, 116, 95);
		purpleGhostAttackFrame3 = new TextureRegion(game_sprites_2, 322, 0, 116, 95);
		purpleGhostAttackFrame4 = new TextureRegion(game_sprites_2, 438, 0, 116, 95);
		purpleGhostAttackFrame5 = new TextureRegion(game_sprites_2, 554, 0, 116, 95);
		purple_ghost_attack = new Animation(PURPLE_GHOST_ATTACK_FRAME_DURATION,
			purpleGhostAttackFrame1,
			purpleGhostAttackFrame2,
			purpleGhostAttackFrame3,
			purpleGhostAttackFrame4,
			purpleGhostAttackFrame5
			);
		
		purpleGhostStandardFrame1 = new TextureRegion(game_sprites_2, 0, 0, 89, 90);
		purpleGhostStandardFrame2 = new TextureRegion(game_sprites_2, 760, 0, 89, 90);
		purpleGhostStandardFrame3 = new TextureRegion(game_sprites_2, 850, 0, 89, 90);

		purple_ghost_standard = new Animation(PURPLE_GHOST_STANDARD_FRAME_DURATION,
			purpleGhostStandardFrame1,
			purpleGhostStandardFrame2,
			purpleGhostStandardFrame1,
			purpleGhostStandardFrame3
				);
		
		FlameAttackFrame1 = new TextureRegion(game_sprites_2, 670, 0, 84, 30);
		FlameAttackFrame2 = new TextureRegion(game_sprites_2, 670, 30, 84, 30);
		FlameAttackFrame3 = new TextureRegion(game_sprites_2, 670, 60, 84, 30);
		purple_flame_attack = new Animation(FAST_FRAME_DURATION,
			FlameAttackFrame1,
			FlameAttackFrame2,
			FlameAttackFrame3
			);
		
		purpleGhostDanceFrame1 = new TextureRegion(game_sprites_2, 0, 95, 88, 84);
		purpleGhostDanceFrame2 = new TextureRegion(game_sprites_2, 90, 95, 88, 84);
		purpleGhostDanceFrame3 = new TextureRegion(game_sprites_2, 180, 95, 88, 84);
		purple_ghost_dancing_1 = new Animation(PURPLE_GHOST_STANDARD_FRAME_DURATION,
			purpleGhostDanceFrame1,
			purpleGhostDanceFrame2,
			purpleGhostDanceFrame1,
			purpleGhostDanceFrame3
			);
		purple_ghost_dancing_2 = new Animation(PURPLE_GHOST_STANDARD_FRAME_DURATION,
			purpleGhostDanceFrame1,
			purpleGhostDanceFrame2,
			purpleGhostDanceFrame1,
			purpleGhostDanceFrame2,
			purpleGhostDanceFrame1,
			purpleGhostDanceFrame3,
			purpleGhostDanceFrame1,
			purpleGhostDanceFrame3	
			);
	
		purpleGhostCollisionFrame1 = new TextureRegion(game_sprites_2, 920, 90, 86, 100);
		purpleGhostCollisionFrame2 = new TextureRegion(game_sprites_2, 920, 190, 86, 100);
		purpleGhostCollisionFrame3 = new TextureRegion(game_sprites_2, 292, 317, 86, 100);
		purpleGhostCollisionFrame4 = new TextureRegion(game_sprites_2, 378, 317, 86, 100);
		purpleGhostCollisionFrame5 = new TextureRegion(game_sprites_2, 464, 317, 86, 100);
		purple_ghost_collision = new Animation(PURPLE_GHOST_STANDARD_FRAME_DURATION,
			purpleGhostCollisionFrame1,
			purpleGhostCollisionFrame2,
			purpleGhostCollisionFrame3,
			purpleGhostCollisionFrame4,
			purpleGhostCollisionFrame5
			);
		
		redWhaleDemonFloatFrame1 = new TextureRegion(game_sprites_2, 0, 200, 155, 100);
		redWhaleDemonFloatFrame2 = new TextureRegion(game_sprites_2, 155, 200, 155, 100);
		redWhaleDemonFloatFrame3 = new TextureRegion(game_sprites_2, 310, 200, 155, 100);
		red_whale_demon_float = new Animation(DEFAULT_FRAME_DURATION,
			redWhaleDemonFloatFrame1,
			redWhaleDemonFloatFrame2,
			redWhaleDemonFloatFrame3
			);
		
		redWhaleDemonChargeUpFrame1 = new TextureRegion(game_sprites_2, 270, 95, 158, 105);
		redWhaleDemonChargeUpFrame2 = new TextureRegion(game_sprites_2, 428, 95, 158, 105);
		red_whale_demon_charge_up = new Animation(DEFAULT_FRAME_DURATION,
			redWhaleDemonChargeUpFrame1,
			redWhaleDemonChargeUpFrame2
			);
		
		redWhaleDemonChargeAttackFrame1 = new TextureRegion(game_sprites_2, 586, 95, 158, 105);
		redWhaleDemonChargeAttackFrame2 = new TextureRegion(game_sprites_2, 744, 95, 158, 105);
		red_whale_demon_charge_attack = new Animation(DEFAULT_FRAME_DURATION,
			redWhaleDemonChargeAttackFrame1,
			redWhaleDemonChargeAttackFrame2
			);
		
		redWhaleDemonCollisionFrame1 = new TextureRegion(game_sprites_2, 465, 200, 146, 117);
		redWhaleDemonCollisionFrame2 = new TextureRegion(game_sprites_2, 611, 200, 146, 117);
		redWhaleDemonCollisionFrame3 = new TextureRegion(game_sprites_2, 757, 200, 146, 117);
		redWhaleDemonCollisionFrame4 = new TextureRegion(game_sprites_2, 0, 300, 146, 117);
		redWhaleDemonCollisionFrame5 = new TextureRegion(game_sprites_2, 146, 300, 146, 117);
		red_whale_demon_collision = new Animation(DEFAULT_FRAME_DURATION,
			redWhaleDemonCollisionFrame1,
			redWhaleDemonCollisionFrame2,
			redWhaleDemonCollisionFrame3,
			redWhaleDemonCollisionFrame4,
			redWhaleDemonCollisionFrame5
			);
		
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
		game_sprites_1.reload();
		game_sprites_2.reload();
		heroHealthy.reload();
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

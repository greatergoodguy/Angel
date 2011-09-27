package com.slimejumper;

import com.slimejumper.framework.impl.GLGame;
import com.slimejumper.gameframework.Sound;
import com.slimejumper.gameframework.gl.Animation;
import com.slimejumper.gameframework.gl.Texture;
import com.slimejumper.gameframework.gl.TextureRegion;

public class Assets {
	
	public final static float HERO_HALO_ATTACK_1_FRAME_DURATION = 0.07f;
	public final static float HERO_LYRE_ATTACK_FRAME_DURATION = 0.15f;
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
	
	public static Animation hero_collided;
	public static TextureRegion heroCollidedFrame1;
	public static TextureRegion heroCollidedFrame2;
	public static TextureRegion heroCollidedFrame3;

	public static Texture background;
	public static TextureRegion backgroundRegion;
	
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
	
	public static void load(GLGame game){
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
		heroLyreAttack1Frame1 = new TextureRegion(hero, 80, 350, 77, 97);
		heroLyreAttack1Frame2 = new TextureRegion(hero, 163, 350, 77, 97);
		heroLyreAttack1Frame3 = new TextureRegion(hero, 246, 350, 77, 97);
		heroLyreAttack1Frame4 = new TextureRegion(hero, 329, 350, 77, 97);
		heroLyreAttack1Frame5 = new TextureRegion(hero, 412, 350, 77, 97);
		heroLyreAttack1Frame6 = new TextureRegion(hero, 0, 430, 77, 97);
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
		
		musicNoteFrame1 = new TextureRegion(hero, 472, 304, 13, 34);
		
		heroCollidedFrame1 = new TextureRegion(hero, 285, 260, 80, 80);
		heroCollidedFrame2 = new TextureRegion(hero, 365, 260, 80, 80);
		heroCollidedFrame3 = new TextureRegion(hero, 0, 350, 80, 80);
		hero_collided = new Animation(HERO_COLLIDED_FRAME_DURATION,
				heroCollidedFrame1,
				heroCollidedFrame2,
				heroCollidedFrame3
				);
		
		background = new Texture(game, "BG1.png");
		backgroundRegion = new TextureRegion(background, 0, 0, 800, 480);
		
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
	}
	
	public static void reload(){
		game_sprites.reload();
		hero.reload();
		background.reload();
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

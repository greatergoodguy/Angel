package com.slimejumper.renderer;




import javax.microedition.khronos.opengles.GL10;

import com.slimejumper.Assets;
import com.slimejumper.framework.impl.GLGraphics;
import com.slimejumper.gameframework.gl.Animation;
import com.slimejumper.gameframework.gl.Camera2D;
import com.slimejumper.gameframework.gl.SpriteBatcher;
import com.slimejumper.gameframework.gl.TextureRegion;
import com.slimejumper.levels.Level;
import com.slimejumper.world.DynamicGameObject;
import com.slimejumper.world.GameObject;
import com.slimejumper.world.Hero;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.attacks.MusicNote;
import com.slimejumper.world.attacks.SpiralAttack;
import com.slimejumper.world.enemies.FlyingSnake;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.environment.GreekPlatform;


public abstract class BaseRenderer {
	Hero hero = Hero.hero_singleton;
	
	public static final float BASE_RENDERER_FRUSTUM_WIDTH = 10;
	public static final float BASE_RENDERER_FRUSTUM_HEIGHT = 6;
	public static final float FRUSTUM_WIDTH_OVER_TWO = BASE_RENDERER_FRUSTUM_WIDTH/2;
	
	public static final float BASE_RENDERER_FRUSTUM_WIDTH_PX = 800f;
	public static final float BASE_RENDERER_FRUSTUM_HEIGHT_PX = 480f;
		
	GLGraphics glGraphics;
	SpriteBatcher batcher;

	protected Camera2D cam;
	protected Level level;			// Different Sprites are rendered for Different Worlds;
	
	public BaseRenderer(GLGraphics glGraphics, SpriteBatcher batcher,
				Level level) {
		this.glGraphics = glGraphics;
		this.level = level;
		this.cam = new Camera2D(glGraphics, BASE_RENDERER_FRUSTUM_WIDTH, BASE_RENDERER_FRUSTUM_HEIGHT);
		this.batcher = batcher;
	}
	
	public void renderSetUp(){
		cam.center = level.center;
		cam.setViewportAndMatrices();
		GL10 gl = glGraphics.getGL();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public void renderTearDown(){
		GL10 gl = glGraphics.getGL();
		gl.glDisable(GL10.GL_BLEND);
	}
	
	public abstract void render();

	protected void renderGreekPlatform(GreekPlatform platform) {
		float platform_section_x_coord = platform.position.x;
		
		batcher.drawSpriteLowerLeft(platform_section_x_coord, platform.position.y,
				GreekPlatform.PLATFORM_UNIT_WIDTH, GreekPlatform.PLATFORM_HEIGHT, Assets.PlatformLeft);
		platform_section_x_coord += GreekPlatform.PLATFORM_UNIT_WIDTH;
		
		for(int i=0; i<platform.middle_length; i++){
			batcher.drawSpriteLowerLeft(platform_section_x_coord, platform.position.y,
					GreekPlatform.PLATFORM_UNIT_WIDTH, GreekPlatform.PLATFORM_HEIGHT, Assets.PlatformMiddle);
			platform_section_x_coord += GreekPlatform.PLATFORM_UNIT_WIDTH;
		}
		
		batcher.drawSpriteLowerLeft(platform_section_x_coord, platform.position.y,
				GreekPlatform.PLATFORM_UNIT_WIDTH, GreekPlatform.PLATFORM_HEIGHT, Assets.PlatformRight);
	}
	
	protected void renderJellyfishDemon(JellyfishDemon jellyfish_demon){
		switch(jellyfish_demon.state){
/*			
		case JellyfishDemon.JELLY_STATE_STANDARD:
			adjustGameSpriteOrientation(jellyfish_demon, Assets.jellyfish_demon_motion.getKeyFrame(jellyfish_demon.life_timer, 
				Animation.ANIMATION_LOOPING));
			break;
*/				
		case JellyfishDemon.JELLY_STATE_ATTACK:
			adjustGameSpriteOrientation(jellyfish_demon, Assets.jellyfish_demon_shock_attacking.getKeyFrame(jellyfish_demon.state_timer, 
				Animation.ANIMATION_LOOPING));
			break;
		case JellyfishDemon.JELLY_STATE_COLLIDED:
			adjustGameSpriteOrientation(jellyfish_demon, Assets.jellyfish_demon_collided.getKeyFrame(jellyfish_demon.life_timer, 
				Animation.ANIMATION_LOOPING));
			break;
		case JellyfishDemon.JELLY_STATE_BOOST_UP:
			adjustGameSpriteOrientation(jellyfish_demon, Assets.jellyfish_demon_boost_up.getKeyFrame(jellyfish_demon.state_timer, 
				Animation.ANIMATION_NONLOOPING));
			break;
		case JellyfishDemon.JELLY_STATE_FLOAT_DOWN:
			adjustGameSpriteOrientation(jellyfish_demon, Assets.jellyfish_demon_float_down.getKeyFrame(jellyfish_demon.state_timer, 
				Animation.ANIMATION_NONLOOPING));
			break;
		}
		
		if(jellyfish_demon.shockball_Left != null){
			batcher.drawSpriteCenter(jellyfish_demon.shockball_Left, 
				Assets.shockball.getKeyFrame(jellyfish_demon.shockball_Left.life_timer, Animation.ANIMATION_LOOPING));
		}
		
		if(jellyfish_demon.shockball_Right != null){
			batcher.drawSpriteCenter(jellyfish_demon.shockball_Right, 
				Assets.shockball.getKeyFrame(jellyfish_demon.shockball_Right.life_timer, Animation.ANIMATION_LOOPING));
		}
	}
	
	protected void renderFlyingSnake(FlyingSnake flying_snake){
		switch(flying_snake.state){
		case FlyingSnake.FLYING_SNAKE_STATE_STANDARD:
			batcher.drawSpriteCenter(flying_snake, Assets.flying_snake_standard.getKeyFrame(flying_snake.life_timer, Animation.ANIMATION_LOOPING));				
			break;
		case FlyingSnake.FLYING_SNAKE_STATE_ATTACK_CHARGE:
			batcher.drawSpriteCenter(flying_snake, Assets.flying_snake_attack.getKeyFrame(flying_snake.state_timer, Animation.ANIMATION_NONLOOPING));
			break;
		case FlyingSnake.FLYING_SNAKE_STATE_ATTACK:
			batcher.drawSpriteCenter(flying_snake, Assets.flyingSnakeAttackFrame);
			break;
		case FlyingSnake.FLYING_SNAKE_STATE_RELOAD:
			batcher.drawSpriteCenter(flying_snake, Assets.flying_snake_standard.getKeyFrame(flying_snake.life_timer, Animation.ANIMATION_LOOPING));				
			break;
			
		}
	}

	protected void renderHero(Hero hero) {
		boolean is_critical_hp = false;
		
		switch(hero.health){
		case 5:
		case 4:
			batcher.beginBatch(Assets.heroHealthy);
			break;
		case 3:
			batcher.beginBatch(Assets.heroHalfHP);
			break;
		case 2:
			batcher.beginBatch(Assets.heroLowHP);
			break;
		case 1:
		default:
			if(hero.facedirection == GameObject.SPRITE_LEFT)
				batcher.beginBatch(Assets.heroCriticalHPLeft);
			else
				batcher.beginBatch(Assets.heroCriticalHPRight);
			is_critical_hp = true;
			break;
		}
		
		if(!is_critical_hp){
			renderHeroState(hero);
		}
		else{
			renderHeroStateCritical(hero);
		}
		
		renderHaloAttacks(hero);
		renderMusicNotes(hero);
		renderSpiralAttacks(hero);
		
		batcher.endBatch();
	}
	
	protected void renderShadowHero(Hero hero) {
		batcher.beginBatch(Assets.shadow_hero);
		
		renderHeroState(hero);
		renderHaloAttacks(hero);
		renderMusicNotes(hero);
		renderSpiralAttacks(hero);
		
		batcher.endBatch();
	}

	private void renderHeroState(Hero hero) {		
		switch(hero.state){
		case Hero.HERO_STATE_JUMP:
			if(hero.health == 4){
				adjustHeroOrientation(hero, Assets.hero_jump_minor_damage.getKeyFrame(hero.state_timer, 
						Animation.ANIMATION_NONLOOPING));
			}
			else{
				adjustHeroOrientation(hero, Assets.hero_jump.getKeyFrame(hero.state_timer, 
					Animation.ANIMATION_NONLOOPING));
			}
			break;
		case Hero.HERO_STATE_FALL:
			if(hero.health == 4)
				adjustHeroOrientation(hero, Assets.hero_fall_minor_damage);
			else	
				adjustHeroOrientation(hero, Assets.hero_fall);
			break;
//		case Hero.HERO_STATE_LAND:
//			adjustHeroOrientation(hero, Assets.hero_land.getKeyFrame(hero.state_timer, 
//					Animation.ANIMATION_NONLOOPING));
//			break;
		case Hero.HERO_STATE_COLLIDED:
			adjustHeroOrientation(hero, Assets.hero_collided.getKeyFrame(hero.state_timer, 
				Animation.ANIMATION_LOOPING));
			break;	
		case Hero.HERO_STATE_BASIC_ATTACK:
			switch(hero.basic_attack_type){
			case Hero.HERO_BASIC_HALO_ATTACK:
				adjustHeroOrientation(hero, Assets.hero_halo_attack_1.getKeyFrame(hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			case Hero.HERO_BASIC_SPIRAL_ATTACK:
				adjustHeroOrientation(hero, Assets.hero_spiral_attack_1.getKeyFrame(hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			case Hero.HERO_BASIC_ATTACK_3:
				adjustHeroOrientation(hero, Assets.hero_halo_attack_1.getKeyFrame(hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			case Hero.HERO_BASIC_ATTACK_SPECIAL_LYRE_ATTACK:
				adjustHeroOrientation(hero, Assets.hero_lyre_attack_1.getKeyFrame(hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			}
			break;
		case Hero.HERO_STATE_DEATH_BY_FALLING:
			adjustHeroOrientation(hero, Assets.heroRegion);
			break;
		}			
	}

	private void renderHeroStateCritical(Hero hero) {		
		switch(hero.state){
		case Hero.HERO_STATE_JUMP:
			batcher.drawSpriteCenter(hero, Assets.hero_jump.getKeyFrame(hero.state_timer, 
				Animation.ANIMATION_NONLOOPING));
			break;
		case Hero.HERO_STATE_FALL:
			batcher.drawSpriteCenter(hero, Assets.hero_fall);
			break;
		case Hero.HERO_STATE_COLLIDED:
			batcher.drawSpriteCenter(hero, Assets.hero_collided.getKeyFrame(hero.state_timer, 
				Animation.ANIMATION_LOOPING));
			break;	
		case Hero.HERO_STATE_BASIC_ATTACK:
			switch(hero.basic_attack_type){
			case Hero.HERO_BASIC_HALO_ATTACK:
				batcher.drawSpriteCenter(hero, Assets.hero_halo_attack_1.getKeyFrame(hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			case Hero.HERO_BASIC_SPIRAL_ATTACK:
				batcher.drawSpriteCenter(hero, Assets.hero_spiral_attack_1.getKeyFrame(hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			case Hero.HERO_BASIC_ATTACK_3:
				batcher.drawSpriteCenter(hero, Assets.hero_halo_attack_1.getKeyFrame(hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			case Hero.HERO_BASIC_ATTACK_SPECIAL_LYRE_ATTACK:
				batcher.drawSpriteCenter(hero, Assets.hero_lyre_attack_1.getKeyFrame(hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			}
			break;
		case Hero.HERO_STATE_DEATH_BY_FALLING:
			batcher.drawSpriteCenter(hero, Assets.heroRegion);
			break;
		}			
	}
	
	private void renderHaloAttacks(Hero hero){
		if(hero.halo_attacks.isEmpty())
			return;
		for(HaloAttack halo_attack : hero.halo_attacks)
			adjustGameSpriteOrientation(halo_attack, Assets.halo_attack.getKeyFrame(halo_attack.life_timer,
						Animation.ANIMATION_LOOPING));
	}

	private void renderMusicNotes(Hero hero) {
		if(hero.music_notes.isEmpty())
			return;
		for(MusicNote music_note : hero.music_notes)
			batcher.drawSpriteCenter(music_note, Assets.musicNoteFrame1);
	}
	
	private void renderSpiralAttacks(Hero hero) {
		if(hero.spiral_attacks.isEmpty())
			return;
		for(SpiralAttack spiral_attack : hero.spiral_attacks)
			adjustGameSpriteOrientation(spiral_attack, Assets.spiral_attack.getKeyFrame(spiral_attack.life_timer,
						Animation.ANIMATION_LOOPING), spiral_attack.angle);
	}
	

	private void adjustHeroOrientation(Hero hero, TextureRegion region) {
		switch(hero.facedirection){
		case GameObject.SPRITE_LEFT:
			batcher.drawSpriteCenterReverse(hero, region);
			break;
		case GameObject.SPRITE_RIGHT:
			batcher.drawSpriteCenter(hero, region);
			break;
		}
	}
	
	private void adjustGameSpriteOrientation(DynamicGameObject object,
			TextureRegion region) {
		switch(object.facedirection){
		case GameObject.SPRITE_LEFT:
			batcher.drawSpriteCenterReverse(object, region);
			break;
		case GameObject.SPRITE_RIGHT:
			batcher.drawSpriteCenter(object, region);
			break;
		}
	}
	
	private void adjustGameSpriteOrientation(SpiralAttack spiral_attack,
			TextureRegion region, float angle) {
		switch(spiral_attack.facedirection){
		case GameObject.SPRITE_LEFT:
			
			batcher.drawSpriteCenter(spiral_attack, Assets.spiral_attack.getKeyFrame(spiral_attack.life_timer,
					Animation.ANIMATION_LOOPING), angle);
			break;
		case GameObject.SPRITE_RIGHT:
			float new_angle = 360 - angle;
			
			batcher.drawSpriteCenterReverse(spiral_attack, Assets.spiral_attack.getKeyFrame(spiral_attack.life_timer,
					Animation.ANIMATION_LOOPING), new_angle);
			break;
		}
	}
}
package com.slimejumper.renderer;




import javax.microedition.khronos.opengles.GL10;

import com.slimejumper.Assets;
import com.slimejumper.framework.impl.GLGraphics;
import com.slimejumper.gameframework.gl.Animation;
import com.slimejumper.gameframework.gl.Camera2D;
import com.slimejumper.gameframework.gl.SpriteBatcher;
import com.slimejumper.gameframework.gl.TextureRegion;
import com.slimejumper.levels.Level;
import com.slimejumper.tools.SpriteContainer;
import com.slimejumper.world.DynamicGameObject;
import com.slimejumper.world.GameObject;
import com.slimejumper.world.Hero;
import com.slimejumper.world.Platform;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.attacks.MusicNote;
import com.slimejumper.world.attacks.Shockball;
import com.slimejumper.world.attacks.SpiralAttack;
import com.slimejumper.world.enemies.FlyingSnake;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.enemies.PurpleGhost;


public abstract class BaseRenderer {
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

	protected void renderGameSprites(){
		batcher.beginBatch(Assets.game_sprites);
		renderPlatforms();
		renderPurpleGhosts();
		renderJellyfishDemons();
		renderFlyingSnakes();
		renderShockBalls();
		batcher.endBatch();
	}
	
	private void renderPlatforms() {
		if(!SpriteContainer.volatile_platforms.isEmpty()){		
			for(Platform platform : SpriteContainer.volatile_platforms)
				renderPlatform(platform);
		}
		if(!SpriteContainer.ground_platforms.isEmpty()){		
			for(Platform platform : SpriteContainer.ground_platforms)
				renderPlatform(platform);
		}
		if(!SpriteContainer.static_platforms.isEmpty()){
			for(Platform platform : SpriteContainer.static_platforms)
				renderPlatform(platform);
		}
	}
	
	private void renderPlatform(Platform platform) {
		float platform_section_x_coord = platform.position.x;
		
		batcher.drawSpriteLowerLeft(platform_section_x_coord, platform.position.y,
				Platform.PLATFORM_UNIT_WIDTH, Platform.PLATFORM_HEIGHT, Assets.platform_left);
		platform_section_x_coord += Platform.PLATFORM_UNIT_WIDTH;
		
		for(int i=0; i<platform.middle_length; i++){
			batcher.drawSpriteLowerLeft(platform_section_x_coord, platform.position.y,
					Platform.PLATFORM_UNIT_WIDTH, Platform.PLATFORM_HEIGHT, Assets.platform_middle);
			platform_section_x_coord += Platform.PLATFORM_UNIT_WIDTH;
		}
		
		batcher.drawSpriteLowerLeft(platform_section_x_coord, platform.position.y,
				Platform.PLATFORM_UNIT_WIDTH, Platform.PLATFORM_HEIGHT, Assets.platform_right);
	}
	

	private void renderPurpleGhosts() {
		if(SpriteContainer.purple_ghosts.isEmpty())
			return;
		for(PurpleGhost purple_ghost : SpriteContainer.purple_ghosts)
			batcher.drawSpriteCenterReverse(purple_ghost, Assets.PurpleGhost);
		
	}
	
	private void renderJellyfishDemons(){
		if(SpriteContainer.jellyfish_demons.isEmpty())
			return;
		for(JellyfishDemon jellyfish_demon : SpriteContainer.jellyfish_demons){
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
		}			
	}
	
	private void renderFlyingSnakes(){
		if(SpriteContainer.flying_snakes.isEmpty())
			return;
		for(FlyingSnake flying_snake : SpriteContainer.flying_snakes){
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
	}
	
	private void renderShockBalls(){
		if(SpriteContainer.shockballs.isEmpty())
			return;
		for(Shockball shockball : SpriteContainer.shockballs)
			batcher.drawSpriteCenter(shockball, Assets.shockball.getKeyFrame(shockball.life_timer, Animation.ANIMATION_LOOPING));
		
	}

	protected void renderHero() {
		batcher.beginBatch(Assets.hero);
		
		renderHeroState();
		renderHaloAttacks();
		renderMusicNotes();
		renderSpiralAttacks();
		
		batcher.endBatch();
	}

	private void renderHeroState() {
		Hero hero = SpriteContainer.hero;
		
		switch(hero.state){
		case Hero.HERO_STATE_JUMP:
			adjustHeroOrientation(hero, Assets.hero_jump);
			break;
		case Hero.HERO_STATE_FALL:
			adjustHeroOrientation(hero, Assets.hero_fall);
			break;
		case Hero.HERO_STATE_LAND:
			adjustHeroOrientation(hero, Assets.hero_land.getKeyFrame(SpriteContainer.hero.state_timer, 
					Animation.ANIMATION_NONLOOPING));
			break;
		case Hero.HERO_STATE_COLLIDED:
			adjustHeroOrientation(hero, Assets.hero_collided.getKeyFrame(SpriteContainer.hero.state_timer, 
					Animation.ANIMATION_LOOPING));
			break;	
		case Hero.HERO_STATE_BASIC_ATTACK:
			switch(hero.basic_attack_type){
			case Hero.HERO_BASIC_HALO_ATTACK:
				adjustHeroOrientation(hero, Assets.hero_halo_attack_1.getKeyFrame(SpriteContainer.hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			case Hero.HERO_BASIC_SPIRAL_ATTACK:
				adjustHeroOrientation(hero, Assets.hero_spiral_attack_1.getKeyFrame(SpriteContainer.hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			case Hero.HERO_BASIC_ATTACK_3:
				adjustHeroOrientation(hero, Assets.hero_halo_attack_1.getKeyFrame(SpriteContainer.hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			case Hero.HERO_BASIC_ATTACK_SPECIAL_LYRE_ATTACK:
				adjustHeroOrientation(hero, Assets.hero_lyre_attack_1.getKeyFrame(SpriteContainer.hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			}
		}			
	}

	private void renderHaloAttacks(){
		if(SpriteContainer.halo_attacks.isEmpty())
			return;
		for(HaloAttack halo_attack : SpriteContainer.halo_attacks)
			adjustGameSpriteOrientation(halo_attack, Assets.halo_attack.getKeyFrame(halo_attack.life_timer,
						Animation.ANIMATION_LOOPING));
	}

	private void renderMusicNotes() {
		if(SpriteContainer.music_notes.isEmpty())
			return;
		for(MusicNote music_note : SpriteContainer.music_notes)
			batcher.drawSpriteCenter(music_note, Assets.musicNoteFrame1);
	}
	
	private void renderSpiralAttacks() {
		if(SpriteContainer.spiral_attacks.isEmpty())
			return;
		for(SpiralAttack spiral_attack : SpriteContainer.spiral_attacks)
			adjustGameSpriteOrientation(spiral_attack, Assets.spiral_attack.getKeyFrame(spiral_attack.life_timer,
						Animation.ANIMATION_LOOPING), spiral_attack.angle);
	}

	protected void renderShadowHero() {
		batcher.beginBatch(Assets.shadow_hero);
		
		renderShadowHeroState();
		renderShadowHaloAttacks();
		renderShadowMusicNotes();
		renderShadowSpiralAttacks();
		
		batcher.endBatch();
	}
	
	
	private void renderShadowHeroState() {
		Hero shadow_hero = SpriteContainer.shadow_hero;
		
		switch(SpriteContainer.shadow_hero.state){
		case Hero.HERO_STATE_JUMP:
			adjustHeroOrientation(shadow_hero, Assets.shadow_hero_jump);
			break;
		case Hero.HERO_STATE_FALL:
			adjustHeroOrientation(shadow_hero, Assets.shadow_hero_fall);
			break;
		case Hero.HERO_STATE_LAND:
			adjustHeroOrientation(shadow_hero, Assets.shadow_hero_land.getKeyFrame(SpriteContainer.shadow_hero.state_timer, 
					Animation.ANIMATION_NONLOOPING));
			break;
		case Hero.HERO_STATE_COLLIDED:
			adjustHeroOrientation(shadow_hero, Assets.shadow_hero_collided.getKeyFrame(SpriteContainer.shadow_hero.state_timer, 
					Animation.ANIMATION_LOOPING));
			break;	
		case Hero.HERO_STATE_BASIC_ATTACK:
			switch(shadow_hero.basic_attack_type){
			case Hero.HERO_BASIC_HALO_ATTACK:
				adjustHeroOrientation(shadow_hero, Assets.shadow_hero_halo_attack_1.getKeyFrame(SpriteContainer.shadow_hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			case Hero.HERO_BASIC_SPIRAL_ATTACK:
				adjustHeroOrientation(shadow_hero, Assets.shadow_hero_spiral_attack_1.getKeyFrame(SpriteContainer.shadow_hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			case Hero.HERO_BASIC_ATTACK_3:
				adjustHeroOrientation(shadow_hero, Assets.shadow_hero_halo_attack_1.getKeyFrame(SpriteContainer.shadow_hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			case Hero.HERO_BASIC_ATTACK_SPECIAL_LYRE_ATTACK:
				adjustHeroOrientation(shadow_hero, Assets.shadow_hero_lyre_attack_1.getKeyFrame(SpriteContainer.shadow_hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			}
		}
		
	}

	private void renderShadowHaloAttacks() {
		// TODO Auto-generated method stub
		
	}

	private void renderShadowMusicNotes() {
		// TODO Auto-generated method stub
		
	}

	private void renderShadowSpiralAttacks() {
		// TODO Auto-generated method stub
		
	}

	private void adjustHeroOrientation(Hero hero, TextureRegion region) {
		switch(hero.facedirection){
		case Hero.HERO_LEFT:
			batcher.drawSpriteCenterReverse(hero, region);
			break;
		case Hero.HERO_RIGHT:
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
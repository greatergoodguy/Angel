package com.slimejumper.renderer;




import javax.microedition.khronos.opengles.GL10;

import com.slimejumper.Assets;
import com.slimejumper.framework.impl.GLGraphics;
import com.slimejumper.gameframework.gl.Animation;
import com.slimejumper.gameframework.gl.Camera2D;
import com.slimejumper.gameframework.gl.SpriteBatcher;
import com.slimejumper.gameframework.gl.TextureRegion;
import com.slimejumper.levels.CaveLevel;
import com.slimejumper.levels.Level;
import com.slimejumper.levels.MenuLevel;
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
	
	
		
	GLGraphics glGraphics;
	public Camera2D cam;
	SpriteBatcher batcher;

	Level active_world;			// Different Sprites are rendered for Different Worlds;
	
	public BaseRenderer(GLGraphics glGraphics, SpriteBatcher batcher,
				Level world) {
		this.glGraphics = glGraphics;
		this.active_world = world;
		this.cam = new Camera2D(glGraphics, BASE_RENDERER_FRUSTUM_WIDTH, BASE_RENDERER_FRUSTUM_HEIGHT);
		this.batcher = batcher;
	}
	
	public void renderSetUp(){
		setCamera();
		
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
	
/*
	public void render(){		
		renderSetUp();
		
		if(active_world instanceof CaveLevel){
			renderBackgroundBackLayer();
			renderBackgroundMiddleLayer();
		}
		else if(active_world instanceof MenuLevel)
			renderBackgroundClouds();
		
		renderGameSprites();
		renderHero();		// Foreground is rendered via a conditional in this function
		
		renderTearDown();
		
	}
*/

	private void setCamera() {
		cam.center = active_world.center;
	}

/*	
	private void renderBackgroundClouds() {
		batcher.beginBatch(Assets.background_clouds);
		batcher.drawSpriteLowerLeft(active_world.position.x, active_world.position.y, 
				BaseRenderer.BASE_RENDERER_FRUSTUM_WIDTH, BaseRenderer.BASE_RENDERER_FRUSTUM_HEIGHT, Assets.backgroundCloudsRegion);
		batcher.endBatch();
	}
	
	private void renderBackgroundBackLayer() {
		batcher.beginBatch(Assets.background_back_layer);
		batcher.drawSpriteLowerLeft(active_world.position.x, active_world.position.y, 
				BaseRenderer.BASE_RENDERER_FRUSTUM_WIDTH, BaseRenderer.BASE_RENDERER_FRUSTUM_HEIGHT, Assets.backgroundBackLayerRegion);
		batcher.endBatch();		
		
		batcher.beginBatch(Assets.background_back_layer_2);
		batcher.drawSpriteLowerLeft(active_world.position.x, active_world.position.y, 
				BaseRenderer.BASE_RENDERER_FRUSTUM_WIDTH, BaseRenderer.BASE_RENDERER_FRUSTUM_HEIGHT, Assets.backgroundBackLayer2Region);
		batcher.endBatch();		
	}
	
	private void renderBackgroundMiddleLayer() {
		batcher.beginBatch(Assets.background_middle_layer);
		batcher.drawSpriteLowerLeft(active_world.position.x, active_world.position.y, 
				BaseRenderer.BASE_RENDERER_FRUSTUM_WIDTH, BaseRenderer.BASE_RENDERER_FRUSTUM_HEIGHT, Assets.backgroundMiddleLayerRegion);
		batcher.endBatch();		
	}
*/

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
			batcher.drawSpriteReverse(purple_ghost, Assets.PurpleGhost);
		
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
				batcher.drawSprite(flying_snake, Assets.flying_snake_standard.getKeyFrame(flying_snake.life_timer, Animation.ANIMATION_LOOPING));				
				break;
			case FlyingSnake.FLYING_SNAKE_STATE_ATTACK_CHARGE:
				batcher.drawSprite(flying_snake, Assets.flying_snake_attack.getKeyFrame(flying_snake.state_timer, Animation.ANIMATION_NONLOOPING));
				break;
			case FlyingSnake.FLYING_SNAKE_STATE_ATTACK:
				batcher.drawSprite(flying_snake, Assets.flyingSnakeAttackFrame);
				break;
			case FlyingSnake.FLYING_SNAKE_STATE_RELOAD:
				batcher.drawSprite(flying_snake, Assets.flying_snake_standard.getKeyFrame(flying_snake.life_timer, Animation.ANIMATION_LOOPING));				
				break;
			}
		}
	}
	
	private void renderShockBalls(){
		if(SpriteContainer.shockballs.isEmpty())
			return;
		for(Shockball shockball : SpriteContainer.shockballs)
			batcher.drawSprite(shockball, Assets.shockball.getKeyFrame(shockball.life_timer, Animation.ANIMATION_LOOPING));
		
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
		switch(SpriteContainer.hero.state){
		case Hero.HERO_STATE_JUMP:
			adjustHeroOrientation(Assets.hero_jump);
			break;
		case Hero.HERO_STATE_FALL:
			adjustHeroOrientation(Assets.hero_fall);
			break;
		case Hero.HERO_STATE_LAND:
			adjustHeroOrientation(Assets.hero_land.getKeyFrame(SpriteContainer.hero.state_timer, 
					Animation.ANIMATION_NONLOOPING));
			break;
		case Hero.HERO_STATE_COLLIDED:
			adjustHeroOrientation(Assets.hero_collided.getKeyFrame(SpriteContainer.hero.state_timer, 
					Animation.ANIMATION_LOOPING));
			break;	
		case Hero.HERO_STATE_BASIC_ATTACK:
			switch(SpriteContainer.hero.basic_attack_type){
			case Hero.HERO_BASIC_HALO_ATTACK:
				adjustHeroOrientation(Assets.hero_halo_attack_1.getKeyFrame(SpriteContainer.hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			case Hero.HERO_BASIC_SPIRAL_ATTACK:
				adjustHeroOrientation(Assets.hero_spiral_attack_1.getKeyFrame(SpriteContainer.hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			case Hero.HERO_BASIC_ATTACK_3:
				adjustHeroOrientation(Assets.hero_halo_attack_1.getKeyFrame(SpriteContainer.hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			case Hero.HERO_BASIC_ATTACK_SPECIAL_LYRE_ATTACK:
				adjustHeroOrientation(Assets.hero_lyre_attack_1.getKeyFrame(SpriteContainer.hero.state_timer,
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
			batcher.drawSprite(music_note, Assets.musicNoteFrame1);
	}
	
	private void renderSpiralAttacks() {
		if(SpriteContainer.spiral_attacks.isEmpty())
			return;
		for(SpiralAttack spiral_attack : SpriteContainer.spiral_attacks)
			adjustGameSpriteOrientation(spiral_attack, Assets.spiral_attack.getKeyFrame(spiral_attack.life_timer,
						Animation.ANIMATION_LOOPING), spiral_attack.angle);
	}
/*	
	private void renderForeground(){
		float FOREGROUND_WIDTH = 5;
		float FOREGROUND_HEIGHT = 1.4375f;		

		float x_coord_lower_left = 0;
		float x_coord_center = FOREGROUND_WIDTH / 2;
		float y_coord_center = FOREGROUND_HEIGHT / 2; 
		int reflection_coefficient = 1;
		
		while(x_coord_lower_left < Level.WORLD_RIGHT_EDGE){
			// batcher.drawSpriteLowerLeft(x_coord_lower_left, 0, FOREGROUND_WIDTH*reflection_coefficient, FOREGROUND_HEIGHT, Assets.foregroundRegion);
			batcher.drawSpriteCenter(x_coord_center, y_coord_center, 
					FOREGROUND_WIDTH*reflection_coefficient, FOREGROUND_HEIGHT, Assets.foregroundRegion);
			x_coord_lower_left += FOREGROUND_WIDTH;
			x_coord_center += FOREGROUND_WIDTH;
			
			reflection_coefficient *= -1;
		}
	}
*/	
	private void adjustHeroOrientation(TextureRegion region) {
		switch(SpriteContainer.hero.facedirection){
		case Hero.HERO_LEFT:
			batcher.drawSpriteReverse(SpriteContainer.hero, region);
			break;
		case Hero.HERO_RIGHT:
			batcher.drawSprite(SpriteContainer.hero, region);
			break;
		}
	}
	
	private void adjustGameSpriteOrientation(DynamicGameObject object,
			TextureRegion region) {
		switch(object.facedirection){
		case GameObject.SPRITE_LEFT:
			batcher.drawSpriteReverse(object, region);
			break;
		case GameObject.SPRITE_RIGHT:
			batcher.drawSprite(object, region);
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
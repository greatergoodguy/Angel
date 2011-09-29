package com.slimejumper.world;




import javax.microedition.khronos.opengles.GL10;

import com.slimejumper.Assets;
import com.slimejumper.framework.impl.GLGraphics;
import com.slimejumper.gameframework.gl.Animation;
import com.slimejumper.gameframework.gl.Camera2D;
import com.slimejumper.gameframework.gl.SpriteBatcher;
import com.slimejumper.gameframework.gl.TextureRegion;
import com.slimejumper.menu.MenuWorld;
import com.slimejumper.tools.World;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.attacks.MusicNote;
import com.slimejumper.world.attacks.Shockball;
import com.slimejumper.world.enemies.Enemy;
import com.slimejumper.world.enemies.FlyingSnake;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.enemies.PurpleGhost;
import com.slimejumper.world.enemies.RedWhaleDemon;


public class WorldRenderer {
	public static final float FRUSTUM_WIDTH = 10;
	public static final float FRUSTUM_HEIGHT = 6;
	public static final float FRUSTUM_WIDTH_OVER_TWO = FRUSTUM_WIDTH/2;
		
	GLGraphics glGraphics;
	public Camera2D cam;
	SpriteBatcher batcher;
	
	World active_world;			// Different Sprites are rendered for Different Worlds;
	
	public WorldRenderer(GLGraphics glGraphics, SpriteBatcher batcher,
				World world) {
		this.glGraphics = glGraphics;
		this.active_world = world;
		this.cam = new Camera2D(glGraphics, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.batcher = batcher;
	}
	
	public void resetActiveWorld(World new_active_world){
		active_world = new_active_world;
	}
	
	public void render(){
		setCamera();
		
		cam.setViewportAndMatrices();
		GL10 gl = glGraphics.getGL();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		if(active_world instanceof GameWorld)
			renderBackground();
		else if(active_world instanceof MenuWorld)
			renderBackground();
		
		renderGameSprites();
		renderHero();
		
		gl.glDisable(GL10.GL_BLEND);
		
	}

	private void setCamera() {
		cam.center = active_world.center;
	}
	
	private void renderBackground() {
		batcher.beginBatch(Assets.background);
		batcher.drawBackground(active_world);
		batcher.endBatch();
	}

	private void renderGameSprites(){
		batcher.beginBatch(Assets.game_sprites);
		renderPlatforms();
		renderTestEnemies();
		renderPurpleGhosts();
		renderJellyfishDemons();
		renderFlyingSnakes();
		renderShockBalls();
		batcher.endBatch();
	}
	
	private void renderPlatforms() {
		if(!Platform.volatile_platforms.isEmpty()){		
			for(Platform platform : Platform.volatile_platforms)
				renderPlatform(platform);
		}
		if(!Platform.ground_platforms.isEmpty()){		
			for(Platform platform : Platform.ground_platforms)
				renderPlatform(platform);
		}
		if(!Platform.static_platforms.isEmpty()){
			for(Platform platform : Platform.static_platforms)
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
	
	private void renderTestEnemies() {
/*
		batcher.drawSprite(world.testJelly, Assets.jellyfish_demon_motion.getKeyFrame(world.rwd1.life_timer, 
				Animation.ANIMATION_LOOPING));
*/
		
		if(Enemy.sample_enemies.isEmpty())
			return;
		for(Enemy enemy : Enemy.sample_enemies){
			if(enemy instanceof RedWhaleDemon)
				batcher.drawSprite(enemy, Assets.RedWhaleDemon.getKeyFrame(enemy.life_timer, 
						Animation.ANIMATION_LOOPING));
			else if(enemy instanceof JellyfishDemon)
				batcher.drawSprite(enemy, Assets.JellyfishDemon);
			else if(enemy instanceof PurpleGhost)
				batcher.drawSprite(enemy, Assets.PurpleGhost);
			
		}		
	}

	private void renderPurpleGhosts() {
		if(PurpleGhost.purple_ghosts.isEmpty())
			return;
		for(PurpleGhost purple_ghost : PurpleGhost.purple_ghosts)
			batcher.drawSpriteReverse(purple_ghost, Assets.PurpleGhost);
		
	}
	
	private void renderJellyfishDemons(){
		if(JellyfishDemon.jellyfish_demons.isEmpty())
			return;
		for(JellyfishDemon jellyfish_demon : JellyfishDemon.jellyfish_demons){
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
		if(FlyingSnake.flying_snakes.isEmpty())
			return;
		for(FlyingSnake flying_snake : FlyingSnake.flying_snakes){
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
		if(Shockball.shockballs.isEmpty())
			return;
		for(Shockball shockball : Shockball.shockballs)
			batcher.drawSprite(shockball, Assets.shockball.getKeyFrame(shockball.life_timer, Animation.ANIMATION_LOOPING));
		
	}

	private void renderHero() {
		batcher.beginBatch(Assets.hero);
		
		renderHeroState();
		renderHaloAttacks();
		renderMusicNotes();
		batcher.endBatch();
	}

	private void renderHeroState() {
		switch(World.hero.state){
		case Hero.HERO_STATE_JUMP:
			adjustHeroOrientation(Assets.hero_jump);
			break;
		case Hero.HERO_STATE_FALL:
			adjustHeroOrientation(Assets.hero_fall);
			break;
		case Hero.HERO_STATE_LAND:
			adjustHeroOrientation(Assets.hero_land.getKeyFrame(active_world.hero.state_timer, 
					Animation.ANIMATION_NONLOOPING));
			break;
		case Hero.HERO_STATE_COLLIDED:
			adjustHeroOrientation(Assets.hero_collided.getKeyFrame(active_world.hero.state_timer, 
					Animation.ANIMATION_LOOPING));
			break;	
		case Hero.HERO_STATE_BASIC_ATTACK:
			switch(active_world.hero.basic_attack_type){
			case Hero.HERO_BASIC_HALO_ATTACK:
				adjustHeroOrientation(Assets.hero_halo_attack_1.getKeyFrame(active_world.hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			case Hero.HERO_BASIC_ATTACK_2:
				adjustHeroOrientation(Assets.hero_halo_attack_1.getKeyFrame(active_world.hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			case Hero.HERO_BASIC_ATTACK_3:
				adjustHeroOrientation(Assets.hero_halo_attack_1.getKeyFrame(active_world.hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			case Hero.HERO_BASIC_ATTACK_SPECIAL_LYRE_ATTACK:
				adjustHeroOrientation(Assets.hero_lyre_attack_1.getKeyFrame(active_world.hero.state_timer,
					Animation.ANIMATION_NONLOOPING));
				break;
			}
		}			
	}

	private void renderHaloAttacks(){
		if(HaloAttack.halo_attacks.isEmpty())
			return;
		for(HaloAttack halo_attack : HaloAttack.halo_attacks)
			adjustGameSpriteOrientation(halo_attack, Assets.halo_attack.getKeyFrame(halo_attack.life_timer,
						Animation.ANIMATION_LOOPING));
	}

	private void renderMusicNotes() {
		if(MusicNote.music_notes.isEmpty())
			return;
		for(MusicNote music_note : MusicNote.music_notes)
			batcher.drawSprite(music_note, Assets.musicNoteFrame1);
	}
	
	private void adjustHeroOrientation(TextureRegion region) {
		switch(active_world.hero.facedirection){
		case Hero.HERO_LEFT:
			batcher.drawSpriteReverse(active_world.hero, region);
			break;
		case Hero.HERO_RIGHT:
			batcher.drawSprite(active_world.hero, region);
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
}
package com.slimejumper.renderer;

import java.util.LinkedList;

import com.slimejumper.Assets;
import com.slimejumper.framework.impl.GLGraphics;
import com.slimejumper.gameframework.gl.Animation;
import com.slimejumper.gameframework.gl.SpriteBatcher;
import com.slimejumper.levels.Level;
import com.slimejumper.levels.TreetopValleyLevel;
import com.slimejumper.world.Background;
import com.slimejumper.world.attacks.PurpleFlame;
import com.slimejumper.world.enemies.PurpleGhost;
import com.slimejumper.world.enemies.RedWhaleDemon;
import com.slimejumper.world.environment.CloudPlatform;
import com.slimejumper.world.environment.CloudPlatformShort;

public class TreetopValleyRenderer extends BaseRenderer{
	
	public static final float CLOUD_PLATFORM_ASSET_WIDTH = 3.625f;
	public static final float CLOUD_PLATFORM_BACK_LAYER_ASSET_HEIGHT = 1.375f;
	public static final float CLOUD_PLATFORM_FRONT_LAYER_ASSET_HEIGHT = 0.525f;
	public static final float CLOUD_PLATFORM_ASSET_HORIZONTAL_OFFSET = 0.0625f;
	public static final float CLOUD_PLATFORM_ASSET_VERTICAL_OFFSET = 0.16f;
	
	public static final float CLOUD_PLATFORM_SHORT_ASSET_WIDTH = 1.25f;
	public static final float CLOUD_PLATFORM_SHORT_ASSET_HEIGHT = 0.525f;
	public static final float CLOUD_PLATFORM_SHORT_ASSET_HORIZONTAL_OFFSET = 0.125f;
	
	public TreetopValleyRenderer(GLGraphics glGraphics, SpriteBatcher batcher, Level level) {
		super(glGraphics, batcher, level);
	}
	
	public void render(){
		renderSetUp();
		
		renderBackgroundTrees();		
		renderGameSprites1();
		renderGameSprites2();
		renderHero(hero);
		
		/*
		 * Render Cloud Front Layer
		 */

		batcher.beginBatch(Assets.game_sprites_1);
		
		LinkedList<CloudPlatform> cloud_platforms = ((TreetopValleyLevel) level).cloud_platforms;
		for(CloudPlatform cloud_platform : cloud_platforms)
			batcher.drawSpriteLowerLeft(cloud_platform.position.x-CLOUD_PLATFORM_ASSET_HORIZONTAL_OFFSET, cloud_platform.position.y + CLOUD_PLATFORM_ASSET_VERTICAL_OFFSET, 
					CLOUD_PLATFORM_ASSET_WIDTH, CLOUD_PLATFORM_FRONT_LAYER_ASSET_HEIGHT, Assets.cloudPlatformFrontLayer);
		
		batcher.endBatch();
		
		renderTearDown();
	}

	protected void renderGameSprites1() {
		batcher.beginBatch(Assets.game_sprites_1);
				
		LinkedList<CloudPlatform> cloud_platforms = ((TreetopValleyLevel) level).cloud_platforms;
		for(CloudPlatform cloud_platform : cloud_platforms)
			batcher.drawSpriteLowerLeft(cloud_platform.position.x-CLOUD_PLATFORM_ASSET_HORIZONTAL_OFFSET, cloud_platform.position.y + CLOUD_PLATFORM_ASSET_VERTICAL_OFFSET, 
				CLOUD_PLATFORM_ASSET_WIDTH, CLOUD_PLATFORM_BACK_LAYER_ASSET_HEIGHT, Assets.cloudPlatformBackLayer);

		LinkedList<CloudPlatformShort> cloud_platforms_short = ((TreetopValleyLevel) level).cloud_platforms_short;
		for(CloudPlatformShort cloud_platform_short : cloud_platforms_short)
			batcher.drawSpriteLowerLeft(cloud_platform_short.position.x-CLOUD_PLATFORM_ASSET_HORIZONTAL_OFFSET, cloud_platform_short.position.y + CLOUD_PLATFORM_ASSET_VERTICAL_OFFSET, 
					CLOUD_PLATFORM_SHORT_ASSET_WIDTH, CLOUD_PLATFORM_SHORT_ASSET_HEIGHT, Assets.cloudPlatformShort);
//			batcher.drawSpriteLowerLeft(cloud_platform_short.position.x, cloud_platform_short.position.y + CLOUD_PLATFORM_ASSET_VERTICAL_OFFSET, 
//				cloud_platform_short.width, cloud_platform_short.height, Assets.cloudPlatformShort); 
		
		batcher.endBatch();
	}

	protected void renderGameSprites2() {
		batcher.beginBatch(Assets.game_sprites_2);
		
		LinkedList<PurpleGhost> purple_ghosts = ((TreetopValleyLevel) level).purple_ghosts;
		for(PurpleGhost purple_ghost : purple_ghosts){
			renderPurpleGhost(purple_ghost);
		}
		
		LinkedList<PurpleFlame> purple_flames = ((TreetopValleyLevel) level).purple_flames;
		for(PurpleFlame purple_flame : purple_flames)
			adjustGameSpriteOrientation(purple_flame, Assets.purple_flame_attack.getKeyFrame(purple_flame.life_timer, Animation.ANIMATION_LOOPING));
		
		LinkedList<RedWhaleDemon> red_whale_demons = ((TreetopValleyLevel) level).red_whale_demons;
		for(RedWhaleDemon red_whale_demon : red_whale_demons)
			renderRedWhaleDemon(red_whale_demon);
				
		batcher.endBatch();
	}

	private void renderPurpleGhost(PurpleGhost purple_ghost) {
		switch(purple_ghost.state){
		case STATE_STANDARD:
			adjustGameSpriteOrientation(purple_ghost, Assets.purple_ghost_standard.getKeyFrame(purple_ghost.state_timer, Animation.ANIMATION_LOOPING));
			break;
		case STATE_SHOOT_THEN_STANDARD:
			adjustGameSpriteOrientation(purple_ghost, Assets.purple_ghost_attack.getKeyFrame(purple_ghost.state_timer, Animation.ANIMATION_NONLOOPING));
			break;
		case STATE_FLOAT_VERTICAL:
			adjustGameSpriteOrientation(purple_ghost, Assets.purple_ghost_standard.getKeyFrame(purple_ghost.state_timer, Animation.ANIMATION_LOOPING));
			break;
		case STATE_SHOOT_THEN_FLOAT_VERTICAL:
			adjustGameSpriteOrientation(purple_ghost, Assets.purple_ghost_attack.getKeyFrame(purple_ghost.state_timer, Animation.ANIMATION_NONLOOPING));
			break;
		case STATE_WALK:
			if(!purple_ghost.is_dancing)
				adjustGameSpriteOrientation(purple_ghost, Assets.purple_ghost_standard.getKeyFrame(purple_ghost.state_timer, Animation.ANIMATION_LOOPING));
			else if(purple_ghost.dance_type_toggler)
				adjustGameSpriteOrientation(purple_ghost, Assets.purple_ghost_dancing_1.getKeyFrame(purple_ghost.state_timer, Animation.ANIMATION_LOOPING));
			else
				adjustGameSpriteOrientation(purple_ghost, Assets.purple_ghost_dancing_2.getKeyFrame(purple_ghost.state_timer, Animation.ANIMATION_LOOPING));
			break;		
		}
		
	}

	
	private void renderRedWhaleDemon(RedWhaleDemon red_whale_demon) {
		switch(red_whale_demon.state){
		case STATE_FLOAT_VERTICAL:
			adjustGameSpriteOrientation(red_whale_demon, 
					Assets.red_whale_demon_float.getKeyFrame(red_whale_demon.state_timer, Animation.ANIMATION_LOOPING));
			break;
		case STATE_TACKLE:
			adjustGameSpriteOrientation(red_whale_demon, 
					Assets.red_whale_demon_charge_attack.getKeyFrame(red_whale_demon.state_timer, Animation.ANIMATION_LOOPING));
			break;
		case STATE_PAUSE:
			adjustGameSpriteOrientation(red_whale_demon, 
					Assets.red_whale_demon_charge_up.getKeyFrame(red_whale_demon.state_timer, Animation.ANIMATION_NONLOOPING));
			break;
		}
		
	}
	
	private void renderBackgroundTrees() {
		Background background = ((TreetopValleyLevel) level).background_trees;
		batcher.beginBatch(Assets.background_treetop_valley);
		batcher.drawBackgroundLowerLeft(level, background, Assets.background_treetop_valley);
		batcher.endBatch();
		
	}
}

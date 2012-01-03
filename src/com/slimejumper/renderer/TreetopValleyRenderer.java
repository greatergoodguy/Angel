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

public class TreetopValleyRenderer extends BaseRenderer{
	
	public static final float CLOUD_PLATFORM_ASSET_WIDTH = 3.625f;
	public static final float CLOUD_PLATFORM_BACK_LAYER_ASSET_HEIGHT = 1.375f;
	public static final float CLOUD_PLATFORM_FRONT_LAYER_ASSET_HEIGHT = 0.525f;
	public static final float CLOUD_PLATFORM_ASSET_HORIZONTAL_OFFSET = 0.0625f;
	public static final float CLOUD_PLATFORM_ASSET_VERTICAL_OFFSET = 0.16f;
	

	
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
			batcher.drawSpriteLowerLeft(cloud_platform.position.x-CLOUD_PLATFORM_ASSET_HORIZONTAL_OFFSET, cloud_platform.position.y +  + CLOUD_PLATFORM_ASSET_VERTICAL_OFFSET, 
					CLOUD_PLATFORM_ASSET_WIDTH, CLOUD_PLATFORM_BACK_LAYER_ASSET_HEIGHT, Assets.cloudPlatformBackLayer);
			
			
		LinkedList<RedWhaleDemon> red_whale_demons = ((TreetopValleyLevel) level).red_whale_demons;
		for(RedWhaleDemon red_whale_demon : red_whale_demons)
			adjustGameSpriteOrientation(red_whale_demon, 
					Assets.RedWhaleDemon.getKeyFrame(red_whale_demon.life_timer, Animation.ANIMATION_LOOPING));

		/*
		LinkedList<PurpleGhost> purple_ghosts = ((TreetopValleyLevel) level).purple_ghosts;
		for(PurpleGhost purple_ghost : purple_ghosts)
			adjustGameSpriteOrientation(purple_ghost, Assets.PurpleGhost);
		
		
		LinkedList<PurpleFlame> purple_flames = ((TreetopValleyLevel) level).purple_flames;
		for(PurpleFlame purple_flame : purple_flames)
			batcher.drawSpriteCenter(purple_flame, Assets.shockballFrame3);
		*/	
			
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
			adjustGameSpriteOrientation(purple_ghost, Assets.purpleGhostStandardFrame1);
			break;
		case STATE_SHOOT_THEN_FLOAT_VERTICAL:
			adjustGameSpriteOrientation(purple_ghost, Assets.purpleGhostStandardFrame1);
			break;
		case STATE_WALK:
			adjustGameSpriteOrientation(purple_ghost, Assets.purple_ghost_standard.getKeyFrame(purple_ghost.state_timer, Animation.ANIMATION_LOOPING));
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

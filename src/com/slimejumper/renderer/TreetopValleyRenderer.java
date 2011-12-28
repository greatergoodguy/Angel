package com.slimejumper.renderer;

import java.util.LinkedList;

import com.slimejumper.Assets;
import com.slimejumper.framework.impl.GLGraphics;
import com.slimejumper.gameframework.gl.Animation;
import com.slimejumper.gameframework.gl.SpriteBatcher;
import com.slimejumper.levels.Level;
import com.slimejumper.levels.TreetopValleyLevel;
import com.slimejumper.world.Background;
import com.slimejumper.world.enemies.PurpleGhost;
import com.slimejumper.world.enemies.RedWhaleDemon;
import com.slimejumper.world.environment.CloudPlatform;

public class TreetopValleyRenderer extends BaseRenderer{
	
	public static final float CLOUD_PLATFORM_ASSET_WIDTH = 3.625f;
	public static final float CLOUD_PLATFORM_BACK_LAYER_ASSET_HEIGHT = 1.375f;
	public static final float CLOUD_PLATFORM_FRONT_LAYER_ASSET_HEIGHT = 0.525f;
	public static final float CLOUD_PLATFORM_ASSET_HORIZONTAL_OFFSET = 0.0625f;
	public static final float CLOUD_PLATFORM_ASSET_VERTICAL_OFFSET = 0.08f;
	

	
	public TreetopValleyRenderer(GLGraphics glGraphics, SpriteBatcher batcher, Level level) {
		super(glGraphics, batcher, level);
	}
	
	public void render(){
		renderSetUp();
		
		renderBackgroundTrees();		
		renderGameSprites();		
		renderHero(hero);
		
		/*
		 * Render Cloud Front Layer
		 */

		batcher.beginBatch(Assets.game_sprites);
		
		LinkedList<CloudPlatform> cloud_platforms = ((TreetopValleyLevel) level).cloud_platforms;
		for(CloudPlatform cloud_platform : cloud_platforms)
			batcher.drawSpriteLowerLeft(cloud_platform.position.x-CLOUD_PLATFORM_ASSET_HORIZONTAL_OFFSET, cloud_platform.position.y + CLOUD_PLATFORM_ASSET_VERTICAL_OFFSET, 
					CLOUD_PLATFORM_ASSET_WIDTH, CLOUD_PLATFORM_FRONT_LAYER_ASSET_HEIGHT, Assets.cloudPlatformFrontLayer);
		
		batcher.endBatch();
		
		renderTearDown();
	}

	protected void renderGameSprites() {
		batcher.beginBatch(Assets.game_sprites);
				
		LinkedList<CloudPlatform> cloud_platforms = ((TreetopValleyLevel) level).cloud_platforms;
		for(CloudPlatform cloud_platform : cloud_platforms)
			batcher.drawSpriteLowerLeft(cloud_platform.position.x-CLOUD_PLATFORM_ASSET_HORIZONTAL_OFFSET, cloud_platform.position.y +  + CLOUD_PLATFORM_ASSET_VERTICAL_OFFSET, 
					CLOUD_PLATFORM_ASSET_WIDTH, CLOUD_PLATFORM_BACK_LAYER_ASSET_HEIGHT, Assets.cloudPlatformBackLayer);
			
			
		LinkedList<RedWhaleDemon> red_whale_demons = ((TreetopValleyLevel) level).red_whale_demons;
		for(RedWhaleDemon red_whale_demon : red_whale_demons)
			batcher.drawSpriteCenterReverse(red_whale_demon, 
					Assets.RedWhaleDemon.getKeyFrame(red_whale_demon.life_timer, Animation.ANIMATION_LOOPING));
				
		LinkedList<PurpleGhost> purple_ghosts = ((TreetopValleyLevel) level).purple_ghosts;
		for(PurpleGhost purple_ghost : purple_ghosts)
			batcher.drawSpriteCenterReverse(purple_ghost, Assets.PurpleGhost);			
				
		batcher.endBatch();
	}

	private void renderBackgroundTrees() {
		Background background = ((TreetopValleyLevel) level).background_trees;
		batcher.beginBatch(Assets.background_treetop_valley);
//		batcher.drawBackgroundLowerLeft(level.position.x, level.position.y, 
//				background, Assets.background_treetop_valley);
//		batcher.drawBackgroundLowerLeftGlitch(level, background, Assets.background_treetop_valley);

		batcher.drawBackgroundLowerLeft(level, background, Assets.background_treetop_valley);
		batcher.endBatch();
		
	}
}

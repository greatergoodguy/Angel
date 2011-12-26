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
	
	public TreetopValleyRenderer(GLGraphics glGraphics, SpriteBatcher batcher, Level level) {
		super(glGraphics, batcher, level);
	}
	
	public void render(){
		renderSetUp();
		
		renderBackgroundTrees();		
		renderGameSprites();		
		renderHero(hero);
		
		renderTearDown();
	}

	protected void renderGameSprites() {
		batcher.beginBatch(Assets.game_sprites);
				
		LinkedList<CloudPlatform> cloud_platforms = ((TreetopValleyLevel) level).cloud_platforms;
		for(CloudPlatform cloud_platform : cloud_platforms)
			batcher.drawSpriteCenter(cloud_platform, Assets.RockPlatform);		
			
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
		batcher.beginBatch(Assets.background_trees);
		batcher.drawBackgroundLowerLeft(level.position.x, level.position.y, 
				background, Assets.background_trees);
		batcher.endBatch();
		
	}
}

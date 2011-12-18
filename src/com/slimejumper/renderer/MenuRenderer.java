package com.slimejumper.renderer;

import com.slimejumper.Assets;
import com.slimejumper.Controller;
import com.slimejumper.framework.impl.GLGraphics;
import com.slimejumper.gameframework.gl.SpriteBatcher;
import com.slimejumper.levels.Level;

public class MenuRenderer extends BaseRenderer{

	public MenuRenderer(GLGraphics glGraphics, SpriteBatcher batcher, Level level,
			Controller controller) {
		super(glGraphics, batcher, level);
	}
	
	public void render(){
		renderSetUp();
		
		renderBackgroundClouds();
		
		renderGameSprites();
		renderHero();
		
		renderController();
		
		renderTearDown();
	}

	private void renderController() {
		
		
	}

	private void renderBackgroundClouds() {
		batcher.beginBatch(Assets.background_clouds);
		batcher.drawSpriteLowerLeft(active_world.position.x, active_world.position.y, 
				BaseRenderer.BASE_RENDERER_FRUSTUM_WIDTH, BaseRenderer.BASE_RENDERER_FRUSTUM_HEIGHT, Assets.backgroundCloudsRegion);
		batcher.endBatch();
	}
}

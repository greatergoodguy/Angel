package com.slimejumper.renderer;

import com.slimejumper.Assets;
import com.slimejumper.framework.impl.GLGraphics;
import com.slimejumper.gameframework.gl.SpriteBatcher;
import com.slimejumper.levels.Level;
import com.slimejumper.world.Hero;

public class CaveRenderer extends BaseRenderer{

	public CaveRenderer(GLGraphics glGraphics, SpriteBatcher batcher, Level world) {
		super(glGraphics, batcher, world);
	}

	public void render(){
		renderSetUp();
		
		renderBackgroundBackLayer();
		renderBackgroundMiddleLayer();
		
		renderGameSprites();
		renderHero(Hero.hero_singleton);		// Foreground is rendered via a conditional in this function
		
		renderForeground();
		
		renderTearDown();
	}
	
	private void renderBackgroundBackLayer() {
		batcher.beginBatch(Assets.background_back_layer);
		batcher.drawSpriteLowerLeft(level.position.x, level.position.y, 
				BaseRenderer.BASE_RENDERER_FRUSTUM_WIDTH, BaseRenderer.BASE_RENDERER_FRUSTUM_HEIGHT, Assets.backgroundBackLayerRegion);
		batcher.endBatch();		
		
		batcher.beginBatch(Assets.background_back_layer_2);
		batcher.drawSpriteLowerLeft(level.position.x, level.position.y, 
				BaseRenderer.BASE_RENDERER_FRUSTUM_WIDTH, BaseRenderer.BASE_RENDERER_FRUSTUM_HEIGHT, Assets.backgroundBackLayer2Region);
		batcher.endBatch();		
	}
	
	private void renderBackgroundMiddleLayer() {
		batcher.beginBatch(Assets.background_middle_layer);
		batcher.drawSpriteLowerLeft(level.position.x, level.position.y, 
				BaseRenderer.BASE_RENDERER_FRUSTUM_WIDTH, BaseRenderer.BASE_RENDERER_FRUSTUM_HEIGHT, Assets.backgroundMiddleLayerRegion);
		batcher.endBatch();		
	}
	
	private void renderForeground(){
		batcher.beginBatch(Assets.hero);
		
		float FOREGROUND_WIDTH = 5;
		float FOREGROUND_HEIGHT = 1.4375f;		

		float x_coord_lower_left = 0;
		float x_coord_center = FOREGROUND_WIDTH / 2;
		float y_coord_center = FOREGROUND_HEIGHT / 2; 
		int reflection_coefficient = 1;
		
		while(x_coord_lower_left < Level.WORLD_DEFAULT_WIDTH){
			// batcher.drawSpriteLowerLeft(x_coord_lower_left, 0, FOREGROUND_WIDTH*reflection_coefficient, FOREGROUND_HEIGHT, Assets.foregroundRegion);
			batcher.drawSpriteCenter(x_coord_center, y_coord_center, 
					FOREGROUND_WIDTH*reflection_coefficient, FOREGROUND_HEIGHT, Assets.foregroundRegion);
			x_coord_lower_left += FOREGROUND_WIDTH;
			x_coord_center += FOREGROUND_WIDTH;
			
			reflection_coefficient *= -1;
		}
		
		batcher.endBatch();
	}
}

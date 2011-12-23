package com.slimejumper.renderer;

import com.slimejumper.Assets;
import com.slimejumper.Controller;
import com.slimejumper.framework.impl.GLGraphics;
import com.slimejumper.gameframework.gl.SpriteBatcher;
import com.slimejumper.levels.Level;
import com.slimejumper.levels.TesterLevel;
import com.slimejumper.tools.SpriteContainer;
import com.slimejumper.world.Background;

public class TesterRenderer extends BaseRenderer{

	Controller controller;
	
	public TesterRenderer(GLGraphics glGraphics, SpriteBatcher batcher, Level level,
			Controller controller) {
		super(glGraphics, batcher, level);
		
		this.controller = controller;
	}
	
	public void render(){
		renderSetUp();
		
		renderBackgroundClouds();
		
		renderGameSprites();
		renderShadowHero(SpriteContainer.shadow_hero);
		renderHero(SpriteContainer.hero);
		
		renderController();
		
		renderTearDown();
	}

	private void renderBackgroundClouds() {
		Background background = ((TesterLevel) level).cloud_background;
		
		batcher.beginBatch(Assets.background_clouds);
		
		batcher.drawBackgroundLowerLeft(level.position.x, level.position.y, 
					background, Assets.background_clouds);

/*		
		if(level.position.x + BASE_RENDERER_FRUSTUM_WIDTH > background.background_loop_width){
			float position_x = level.position.x + BASE_RENDERER_FRUSTUM_WIDTH;
			batcher.drawBackgroundLowerLeft(20, level.position.y, 
					background, Assets.background_clouds);
		}
*/		
		
		
//		batcher.drawSpriteLowerLeft(level.position.x, level.position.y, 
//				BaseRenderer.BASE_RENDERER_FRUSTUM_WIDTH, BaseRenderer.BASE_RENDERER_FRUSTUM_HEIGHT, Assets.backgroundCloudsRegion);
//		batcher.drawSpriteLowerLeft(0, 0, 
//				Level.WORLD_DEFAULT_WIDTH, Level.WORLD_DEFAULT_HEIGHT, Assets.backgroundCloudsRegion);
//		batcher.drawSpriteLowerLeft(Level.WORLD_DEFAULT_WIDTH, 0, 
//				Level.WORLD_DEFAULT_WIDTH, Level.WORLD_DEFAULT_HEIGHT, Assets.backgroundCloudsRegion);

		batcher.endBatch();
	}
	
	private void renderController() {
		batcher.beginBatch(Assets.controller_icons);

		/*
		 * Left Arrow
		 */
		if(!controller.LeftButtonDown)
			batcher.drawSpriteLowerLeftReverse(level.position.x, level.position.y,
				Controller.CONTROLLER_ICON_WIDTH, Controller.CONTROLLER_ICON_HEIGHT, Assets.arrow_unpressed);
		else
			batcher.drawSpriteLowerLeftReverse(level.position.x, level.position.y,
				Controller.CONTROLLER_ICON_WIDTH, Controller.CONTROLLER_ICON_HEIGHT, Assets.arrow_pressed);
		
		/*
		 * Right Arrow
		 */
		if(!controller.RightButtonDown)
			batcher.drawSpriteLowerLeft(level.position.x + 3*Controller.CONTROLLER_ICON_WIDTH, level.position.y,
				Controller.CONTROLLER_ICON_WIDTH, Controller.CONTROLLER_ICON_HEIGHT, Assets.arrow_unpressed);
		else
			batcher.drawSpriteLowerLeft(level.position.x + 3*Controller.CONTROLLER_ICON_WIDTH, level.position.y,
				Controller.CONTROLLER_ICON_WIDTH, Controller.CONTROLLER_ICON_HEIGHT, Assets.arrow_pressed);
			
		/*
		 * Attack Icon
		 */
		if(!controller.fireAttackDown){
			batcher.drawSpriteLowerLeftReverse(level.position.x, level.position.y + Controller.CONTROLLER_ICON_HEIGHT,
				Controller.CONTROLLER_ICON_WIDTH, Controller.CONTROLLER_ICON_HEIGHT, Assets.attack_unpressed);
		
			batcher.drawSpriteLowerLeft(level.position.x + 3*Controller.CONTROLLER_ICON_WIDTH, level.position.y + Controller.CONTROLLER_ICON_HEIGHT,
				Controller.CONTROLLER_ICON_WIDTH, Controller.CONTROLLER_ICON_HEIGHT, Assets.attack_unpressed);
		}
		else{
			batcher.drawSpriteLowerLeftReverse(level.position.x, level.position.y + Controller.CONTROLLER_ICON_HEIGHT,
				Controller.CONTROLLER_ICON_WIDTH, Controller.CONTROLLER_ICON_HEIGHT, Assets.attack_pressed);
			
			batcher.drawSpriteLowerLeft(level.position.x + 3*Controller.CONTROLLER_ICON_WIDTH, level.position.y + Controller.CONTROLLER_ICON_HEIGHT,
				Controller.CONTROLLER_ICON_WIDTH, Controller.CONTROLLER_ICON_HEIGHT, Assets.attack_pressed);
		}
		
		batcher.endBatch();		
	}
}

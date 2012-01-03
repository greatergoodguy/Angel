package com.slimejumper.renderer;

import java.util.LinkedList;

import com.slimejumper.Assets;
import com.slimejumper.Controller;
import com.slimejumper.framework.impl.GLGraphics;
import com.slimejumper.gameframework.gl.SpriteBatcher;
import com.slimejumper.levels.Level;
import com.slimejumper.levels.MenuLevel;
import com.slimejumper.world.environment.GreekPlatform;

public class MenuRenderer extends BaseRenderer{

	Controller controller;
	
	public MenuRenderer(GLGraphics glGraphics, SpriteBatcher batcher, Level level,
			Controller controller) {
		super(glGraphics, batcher, level);
		
		this.controller = controller;
	}
	
	public void render(){
		renderSetUp();
		
		renderBackgroundClouds();
		
		renderGreekPlatforms();
		renderHero(hero);
		
		renderController();
		
		renderTearDown();
	}

	private void renderBackgroundClouds() {
		batcher.beginBatch(Assets.background_clouds);
		batcher.drawBackgroundLowerLeft(level.position.x, level.position.y, ((MenuLevel) level).cloud_background, Assets.background_clouds);
		batcher.endBatch();
	}
	
	private void renderGreekPlatforms() {
		batcher.beginBatch(Assets.game_sprites_1);
		
		LinkedList<GreekPlatform> greek_platforms = ((MenuLevel) level).greek_platforms;
		for(GreekPlatform greek_platform : greek_platforms)
			renderGreekPlatform(greek_platform);
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

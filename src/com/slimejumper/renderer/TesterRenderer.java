package com.slimejumper.renderer;

import java.util.LinkedList;

import com.slimejumper.Assets;
import com.slimejumper.Controller;
import com.slimejumper.framework.impl.GLGraphics;
import com.slimejumper.gameframework.gl.SpriteBatcher;
import com.slimejumper.levels.Level;
import com.slimejumper.levels.TesterLevel;
import com.slimejumper.world.Background;
import com.slimejumper.world.ShadowHero;
import com.slimejumper.world.environment.CloudPlatform;
import com.slimejumper.world.environment.RockPlatform;

public class TesterRenderer extends BaseRenderer{

	Controller controller;
	
	public TesterRenderer(GLGraphics glGraphics, SpriteBatcher batcher, Level level,
			Controller controller) {
		super(glGraphics, batcher, level);
		
		this.controller = controller;
	}
	
	public void render(){
		renderSetUp();
		
		renderBackgroundTrees();
		
		renderRockPlatformsAndPurpleGhost();
		
		renderShadowHero(ShadowHero.shadow_hero_singleton);
		renderHero(hero);
		
		renderController();
		
		renderTearDown();
	}

	private void renderRockPlatformsAndPurpleGhost() {
		batcher.beginBatch(Assets.game_sprites_1);
		
		LinkedList<RockPlatform> rock_platforms = ((TesterLevel) level).rock_platforms;
		for(RockPlatform rock_platform : rock_platforms)
			batcher.drawSpriteCenter(rock_platform, Assets.RockPlatform);
				
		LinkedList<CloudPlatform> cloud_platforms = ((TesterLevel) level).cloud_platforms;
		for(CloudPlatform cloud_platform : cloud_platforms)
			batcher.drawSpriteCenter(cloud_platform, Assets.RockPlatform);
				
		renderJellyfishDemon(((TesterLevel) level).jellyfish_demon_test);
				
		batcher.endBatch();
		
	}

	private void renderBackgroundTrees() {
		Background background = ((TesterLevel) level).background_trees;
		batcher.beginBatch(Assets.background_treetop_valley);
		batcher.drawBackgroundLowerLeftGlitch(level, 
				background, Assets.background_treetop_valley);
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

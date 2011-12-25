package com.slimejumper.renderer;

import java.util.LinkedList;

import com.slimejumper.Assets;
import com.slimejumper.Controller;
import com.slimejumper.framework.impl.GLGraphics;
import com.slimejumper.gameframework.gl.Animation;
import com.slimejumper.gameframework.gl.SpriteBatcher;
import com.slimejumper.levels.Level;
import com.slimejumper.levels.TesterLevel;
import com.slimejumper.world.Background;
import com.slimejumper.world.Hero;
import com.slimejumper.world.ShadowHero;
import com.slimejumper.world.enemies.RedWhaleDemon;
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
		
		renderGameSprites();
		renderRockPlatformsAndPurpleGhost();
		
		renderShadowHero(ShadowHero.shadow_hero_singleton);
		renderHero(Hero.hero_singleton);
		
		renderController();
		
		renderTearDown();
	}

	private void renderRockPlatformsAndPurpleGhost() {
		batcher.beginBatch(Assets.game_sprites);
		
		LinkedList<RockPlatform> rock_platforms = ((TesterLevel) level).rock_platforms;
		for(RockPlatform rock_platform : rock_platforms)
			batcher.drawSpriteCenter(rock_platform, Assets.RockPlatform);
				
		LinkedList<RedWhaleDemon> red_whale_demons = ((TesterLevel) level).red_whale_demons;
		for(RedWhaleDemon red_whale_demon : red_whale_demons)
			batcher.drawSpriteCenterReverse(red_whale_demon, 
					Assets.RedWhaleDemon.getKeyFrame(red_whale_demon.life_timer, Animation.ANIMATION_LOOPING));
		batcher.endBatch();
		
	}

	private void renderBackgroundTrees() {
		Background background = ((TesterLevel) level).background_trees;
		batcher.beginBatch(Assets.background_trees);
		batcher.drawBackgroundLowerLeft(level.position.x, level.position.y, 
				background, Assets.background_trees);
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

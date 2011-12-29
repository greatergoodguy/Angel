package com.slimejumper.renderer;

import java.util.LinkedList;

import com.slimejumper.Assets;
import com.slimejumper.framework.impl.GLGraphics;
import com.slimejumper.gameframework.gl.SpriteBatcher;
import com.slimejumper.levels.CaveLevel;
import com.slimejumper.levels.Level;
import com.slimejumper.world.enemies.FlyingSnake;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.environment.GreekPlatform;

public class CaveRenderer extends BaseRenderer{

	public CaveRenderer(GLGraphics glGraphics, SpriteBatcher batcher, Level world) {
		super(glGraphics, batcher, world);
	}

	public void render(){
		renderSetUp();
		
		batcher.beginBatch(Assets.background_caves);
		batcher.drawBackgroundLowerLeft(level.position.x, level.position.y,
				((CaveLevel) level).cave_background, Assets.background_caves);
		batcher.endBatch();
		
		batcher.beginBatch(Assets.game_sprites);
		// Render Greek Platforms
		LinkedList<GreekPlatform> greek_platforms_volatile = ((CaveLevel) level).greek_platforms_volatile;
		for(GreekPlatform greek_platform : greek_platforms_volatile)
			renderGreekPlatform(greek_platform);		
		
		// Render Jellyfish Demons
		LinkedList<JellyfishDemon> jellyfish_demons = ((CaveLevel) level).jellyfish_demons;
		for(JellyfishDemon jellyfish_demon : jellyfish_demons)
			renderJellyfishDemon(jellyfish_demon);
		
		// Render Flying Snakes
		LinkedList<FlyingSnake> flying_snakes = ((CaveLevel) level).flying_snakes;
		for(FlyingSnake flying_snake : flying_snakes)
			renderFlyingSnake(flying_snake);
				
		batcher.endBatch();
		
		renderHero(hero);		// Foreground is rendered via a conditional in this function
		
		renderForeground();
		
		renderTearDown();
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

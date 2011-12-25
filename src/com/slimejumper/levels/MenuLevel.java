package com.slimejumper.levels;

import com.slimejumper.Assets;
import com.slimejumper.Controller;
import com.slimejumper.Settings;
import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.renderer.BaseRenderer;
import com.slimejumper.tools.SpriteManager;
import com.slimejumper.world.Background;
import com.slimejumper.world.Hero;

public class MenuLevel extends Level{
	
	private final int BACKGROUND_CLOUDS_WIDTH = 15;	
	
	Background cloud_background;
	
	public MenuLevel(WorldListener listener, SpriteManager sprite_manager, Controller controller){
		super(listener, sprite_manager, controller);
		
		 if(Settings.soundEnabled)
	        	Assets.test_music.play();
		
		cloud_background = new Background(BACKGROUND_CLOUDS_WIDTH, Level.WORLD_DEFAULT_WIDTH, Level.WORLD_DEFAULT_HEIGHT);
	}
	
	public void update(float deltaTime){		
		super.update(deltaTime);

		updateCloudBackground();
		
		if(Hero.hero_singleton.position.y < 0.0f){
			Hero.hero_singleton.resetPositionLowerLeft(2, 11);
			
			// Switch to next level
		}
	}
	
	private void updateCloudBackground() {
		Vector2 new_position = new Vector2();
		
		new_position.x = position.x * cloud_background.parallax_ratio;
		new_position.y = BaseRenderer.BASE_RENDERER_FRUSTUM_HEIGHT - position.y;
		Assets.backgroundCloudsRegion.adjust(new_position);		
	}
}

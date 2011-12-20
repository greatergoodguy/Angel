package com.slimejumper.levels;

import com.slimejumper.Assets;
import com.slimejumper.Controller;
import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.renderer.BaseRenderer;
import com.slimejumper.tools.SpriteContainer;
import com.slimejumper.tools.SpriteManager;
import com.slimejumper.world.Background;

public class MenuLevel extends Level{
	
	private final int BACKGROUND_CLOUDS_WIDTH = 15;	
	
	Background cloud_background;
	
	public MenuLevel(WorldListener listener, SpriteManager sprite_manager, Controller controller){
		super(listener, sprite_manager, controller);
		
		cloud_background = new Background(BACKGROUND_CLOUDS_WIDTH, Level.WORLD_WIDTH);
	}
	
	public void update(float deltaTime){		
		super.update(deltaTime);

		updateCloudBackground();
		
		if(SpriteContainer.hero.position.y < 0.0f){
			SpriteContainer.hero.resetPositionLowerLeft(2, 11);
			
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

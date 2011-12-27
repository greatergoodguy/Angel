package com.slimejumper.levels;

import com.slimejumper.Assets;
import com.slimejumper.Controller;
import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.renderer.BaseRenderer;
import com.slimejumper.tools.ObstacleGeneratorManager;
import com.slimejumper.world.Background;

public class CaveLevel extends Level{

	public final float BACKGROUND_CAVE_BACK_LAYER_WIDTH = 15;	
	public final float BACKGROUND_CAVE_MIDDLE_LAYER_WIDTH = 14.9f;	
	
	Background cave_back_layer_background;
	Background cave_middle_layer_background;
	
	ObstacleGeneratorManager obstacle_generator_manager;
	
	float level_timer;
	int level_counter;

	public CaveLevel(WorldListener listener, Controller controller) {
		super(listener, controller);
	
		cave_back_layer_background = new Background(BACKGROUND_CAVE_BACK_LAYER_WIDTH, 0, WORLD_DEFAULT_WIDTH, Level.WORLD_DEFAULT_HEIGHT);
		cave_middle_layer_background = new Background(BACKGROUND_CAVE_MIDDLE_LAYER_WIDTH, 0, WORLD_DEFAULT_WIDTH, Level.WORLD_DEFAULT_HEIGHT);
		
		obstacle_generator_manager = new ObstacleGeneratorManager();
		
		level_timer = 0;
		level_counter = 1;
	}

	public void update(float deltaTime) {
		super.update(deltaTime);
		updateCaveBackground();
		
		updateLevel(deltaTime);
	}

	private void updateLevel(float deltaTime) {
		level_timer += deltaTime;
		
		if(level_timer > 15.0f){
			level_counter++;
			level_timer = 0;
		}
		
//		manager.generateTestLevel(deltaTime);
		if(level_counter == 1 )
			obstacle_generator_manager.generateLevelOne(deltaTime);
		else if(level_counter == 2 )
			obstacle_generator_manager.generateLevelTwo(deltaTime);
		else
			obstacle_generator_manager.generateSampleLevel(deltaTime);		
	}
	
	private void updateCaveBackground() {
		Vector2 new_position = new Vector2();
		
		new_position.x = position.x * cave_back_layer_background.horizontal_parallax_ratio;
		new_position.y = BaseRenderer.BASE_RENDERER_FRUSTUM_HEIGHT - position.y;
		Assets.backgroundBackLayerRegion.adjust(new_position);
		Assets.backgroundBackLayer2Region.adjust(new_position);

		new_position.x = position.x * cave_middle_layer_background.horizontal_parallax_ratio;
		new_position.y = BaseRenderer.BASE_RENDERER_FRUSTUM_HEIGHT - position.y;
		Assets.backgroundMiddleLayerRegion.adjust(new_position);	
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
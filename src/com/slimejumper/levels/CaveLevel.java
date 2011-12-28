package com.slimejumper.levels;

import com.slimejumper.Assets;
import com.slimejumper.Controller;
import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.renderer.BaseRenderer;
import com.slimejumper.tools.ObstacleGeneratorManager;
import com.slimejumper.world.Background;

public class CaveLevel extends Level{

	public final float BACKGROUND_CAVE_BACK_LAYER_WIDTH = 15;
	
	public final Background cave_background;
	
	ObstacleGeneratorManager obstacle_generator_manager;
	
	float level_timer;
	int level_counter;

	public CaveLevel(WorldListener listener, Controller controller) {
		super(listener, controller);
	
		cave_background = new Background(BACKGROUND_CAVE_BACK_LAYER_WIDTH, 0, WORLD_DEFAULT_WIDTH, Level.WORLD_DEFAULT_HEIGHT);
		
		obstacle_generator_manager = new ObstacleGeneratorManager();
		
		level_timer = 0;
		level_counter = 1;
	}

	public void update(float deltaTime) {
		super.update(deltaTime);
		
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

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
package com.slimejumper.levels;

import com.slimejumper.tools.ObstacleGeneratorManager;
import com.slimejumper.tools.SpriteManager;

public class CaveLevel extends Level{

	ObstacleGeneratorManager obstacle_generator_manager;
	
	float level_timer;
	int level_counter;

	public CaveLevel(WorldListener listener, SpriteManager sprite_manager) {
		super(listener, sprite_manager);
	
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
}
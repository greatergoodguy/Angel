package com.slimejumper.levels;

import com.slimejumper.gameframework.math.UnitCircle;
import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.tools.ObstacleGeneratorManager;
import com.slimejumper.tools.Remover;

public class CaveLevel extends Level{
	public interface WorldListener {
		public void jump();

		public void killJump();

		public void hit();

		public void coin();
	}

	ObstacleGeneratorManager obstacle_generator_manager;
	public final WorldListener listener;

	float level_timer;
	int level_counter;

	public CaveLevel(WorldListener listener) {
//		initializeLists();
		
//		if(hero == null)
//			World.hero = new Hero();
		this.listener = listener;
	
		obstacle_generator_manager = new ObstacleGeneratorManager(this);

		center = new Vector2(WORLD_CENTER_DEFAULT_X, WORLD_CENTER_DEFAULT_Y);
		position = new Vector2();
		
		level_timer = 0;
		level_counter = 1;
		
//		Platform.initializePlatformMap();
	}

	public void update(float deltaTime) {
/*		
		//First Update Sprites of this extended Class
		updateSprites(deltaTime);
		updateLevel(deltaTime);
		
		//Update Sprites and General Parameters (center, position, collisionManager, remover)
		super.update();	
*/
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
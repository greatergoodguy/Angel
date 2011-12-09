package com.slimejumper.tools;

import java.util.Random;

import com.slimejumper.levels.Level;

public class ObstacleGeneratorManager {
	
	Level world;
	ObstacleGenerator obstacleShooter;
	Random random;
	
	float platform_timer;
	float enemy_launch_timer;
	float stair_level_counter;
	int timer_counter;
	float sample_level_timer;
	float level_one_timer;
	float level_two_timer;
	
	public ObstacleGeneratorManager(Level world){
		this.world = world;
		obstacleShooter = new ObstacleGenerator(world);
		random = new Random();
		
//		initializePlatformGround();
//		initializePlatformMap();
		
		platform_timer = 0;
		enemy_launch_timer = 0;
		stair_level_counter = 0;
		timer_counter = 0;
		sample_level_timer = 0;
		level_one_timer = 0;
		level_two_timer = 0;
	}
/*	
	public static void initializePlatformGround(){
		if(!Platform.ground_platforms.isEmpty()){
			Log.d("ObstacleGeneratorManager.initializePlatformGround()", "platform_ground already filled");
			return;
		}
		
		final int ground_platform_length = 5;
		float x_coord = 0;
		Platform platform;
		
		while(x_coord < World.WORLD_RIGHT_EDGE){
			// create new Platform and add to list
			platform = World.poolManager.platformPool.newObject();
			platform.reset(x_coord, 0, ground_platform_length, Platform.PLATFORM_STATE_STILL);;
			Platform.ground_platforms.add(platform);
			
			// Advance counter and repeat
			x_coord += 5 * Platform.PLATFORM_UNIT_WIDTH;
		}
		Collections.shuffle(Platform.ground_platforms);
	}
	
	public static void initializePlatformMap(){
		
		if(!Platform.static_platforms.isEmpty()){
			Log.d("ObstacleGeneratorManager.initializePlatformMap()", "static_platform already filled");
			return;
		}

		
		// Initial Parameters
		final int map_platform_length = 3;
		
		float x_coord = 4;
		float y_coord = 2.5f;
		
		Platform platform;
		
		while(y_coord < World.WORLD_TOP_BOUND){
			while(x_coord < World.WORLD_RIGHT_EDGE){
				platform = World.poolManager.platformPool.newObject();
				platform.reset(x_coord, y_coord, map_platform_length, Platform.PLATFORM_STATE_STILL);
				Platform.static_platforms.add(platform);
			
				x_coord += 8;
			}
			if(x_coord % 8 == 0)
				x_coord = 4;
			else
				x_coord = 0;
			
			y_coord += 2.5;
		}
		Collections.shuffle(Platform.static_platforms);
	}
*/
	
//---------------------------------------------------------------------//	
	
	private void generatePlatformStream(float deltaTime){
		platform_timer += deltaTime;
		
		if(platform_timer > 1.6f){
			obstacleShooter.generatePlatform(1.0f, 2.5f);
			platform_timer = 0;
		}
	}
	
	
	private void generatePlatformMiniMatrix(float deltaTime){
		platform_timer += deltaTime;

		if(platform_timer > 1.6f){
			if(timer_counter == 0){
				timer_counter++;
				obstacleShooter.generatePlatform(0, 2.5f);
				obstacleShooter.generatePlatform(2.5f, 5.0f);
			}
			else if(timer_counter >= 1){
				timer_counter = 0;
				obstacleShooter.generatePlatform(1.0f, 3.5f);
				obstacleShooter.generatePlatform(3.5f, 6.0f);
			}
			platform_timer = 0;
		}
	}
	
	private void generatePlatformMatrix(float deltaTime){
		platform_timer += deltaTime;
		
		if(platform_timer > 2.2f){
			obstacleShooter.generatePlatform(0, 2.0f);
			obstacleShooter.generatePlatform(2.0f, 4.0f);
			obstacleShooter.generatePlatform(4.0f, 6.0f);
			obstacleShooter.generatePlatform(6.0f, 8.0f);
			platform_timer = 0;
		}
	}
	
	private void generatePlatformStairs(float deltaTime){
		platform_timer += deltaTime;
		
		if(platform_timer > 1.6f){
			obstacleShooter.generatePlatform(stair_level_counter, stair_level_counter + 0.1f);
			stair_level_counter++;
			platform_timer = 0;
		}
		
		if(stair_level_counter > 8)
			stair_level_counter = 0;
		
	}

//---------------------------------------------------------------------//	
	
/*	
	private void generatePurpleGhostStream(float deltaTime){
		enemy_launch_timer += deltaTime;
		
		if(enemy_launch_timer > 4.6f){
			obstacleShooter.generatePurpleGhost(1.6f, 2.4f);
			enemy_launch_timer = 0;
		}
	}
*/	
	private void generateFlyingSnakeStream(float deltaTime){
		enemy_launch_timer += deltaTime;

		float spawnPositionY = random.nextFloat() * (8 - 5) + 5;
		if(enemy_launch_timer > 6.2f){
			obstacleShooter.generateFlyingSnake(spawnPositionY);
			enemy_launch_timer = 0;
		}
	}
	
	private void generateJellyfishDemonStream(float deltaTime){
		enemy_launch_timer += deltaTime;
		
		if(enemy_launch_timer > 14.0f){
			obstacleShooter.generateJellyfishDemon();
			enemy_launch_timer = 0;
		}
	}

//---------------------------------------------------------------------//	
	
/*	
	private void generateJellyfishDemonPairStream(float deltaTime) {
		enemy_launch_timer += deltaTime;
		
		if(enemy_launch_timer > 7.0f){
			obstacleShooter.generateJellyfishDemonPair();
			enemy_launch_timer = 0;
		}
	}
*/	
	public void generateSampleLevel(float deltaTime){
		sample_level_timer += deltaTime;
		
		generateJellyfishDemonStream(deltaTime);
		
		if(sample_level_timer < 3.0f)
			generatePlatformStream(deltaTime);
		else if(sample_level_timer >= 3.0f && sample_level_timer < 18.0f)
			generatePlatformStairs(deltaTime);
		else 
			generatePlatformMatrix(deltaTime);
	}
	
	public void generateLevelOne(float deltaTime){
		level_one_timer += deltaTime;
		generatePlatformStream(deltaTime);
		generateFlyingSnakeStream(deltaTime);
//		generateJellyfishDemonStream(deltaTime);
	}
	
	public void generateLevelTwo(float deltaTime){
		level_two_timer += deltaTime;
		generatePlatformMiniMatrix(deltaTime);
		generateFlyingSnakeStream(deltaTime);
//		generateJellyfishDemonStream(deltaTime);
	}

	public void generateTestLevel(float deltaTime) {
//		generatePlatformStream(deltaTime);
		generatePlatformMatrix(deltaTime);
		generateJellyfishDemonStream(deltaTime);
	}	
}

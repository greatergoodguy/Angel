package com.slimejumper.tools;

import java.util.Random;

import com.slimejumper.levels.Level;
import com.slimejumper.world.Platform;
import com.slimejumper.world.enemies.FlyingSnake;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.enemies.PurpleGhost;

public class ObstacleGenerator {
		
	Level world;
	Random random;
	
	public ObstacleGenerator(Level world){
		this.world = world;
		random = new Random();
	}
	
	//position represents vertical dimension
	//should be between 0 and 12
	public void generatePlatform(float min, float max){
		float spawnPositionY = random.nextFloat() * (max - min) + min;
		
		
//		Platform platform = world.platformPool.newObject();
		Platform platform = Level.poolManager.platform_pool.newObject();
		platform.reset(Level.WORLD_RIGHT_EDGE, spawnPositionY, Platform.PLATFORM_MEDIUM_LENGTH, Platform.PLATFORM_STATE_ZOOM);
		SpriteContainer.volatile_platforms.add(platform);
	}
	
	public void generatePurpleGhost(float min, float max){
		float spawnPositionY = random.nextFloat() * (max - min) + min;
		
		PurpleGhost purple_ghost = Level.poolManager.purple_ghost_pool.newObject();
		purple_ghost.reset(spawnPositionY);
		SpriteContainer.purple_ghosts.add(purple_ghost);

	}
	
	public void generateFlyingSnake(float spawnPositionY){
		
		FlyingSnake flying_snake = Level.poolManager.flying_snake_pool.newObject();
		flying_snake.reset(spawnPositionY);
		SpriteContainer.flying_snakes.add(flying_snake);
	}

	public void generateJellyfishDemon() {
		JellyfishDemon jellyfish_demon = Level.poolManager.jellyfish_demon_pool.newObject();
		float spawnPositionX = random.nextFloat() * Level.WORLD_WIDTH/2 + Level.WORLD_WIDTH/4;
		jellyfish_demon.reset(spawnPositionX);
		SpriteContainer.jellyfish_demons.add(jellyfish_demon);
	}
	
	public void generateJellyfishDemonPair() {
		float spawnPositionX1 = random.nextFloat() * Level.WORLD_WIDTH/2;
		float spawnPositionX2 = random.nextFloat() * Level.WORLD_WIDTH/2 + Level.WORLD_WIDTH/2;
		
		JellyfishDemon jellyfish_demon1 = Level.poolManager.jellyfish_demon_pool.newObject();
		JellyfishDemon jellyfish_demon2 = Level.poolManager.jellyfish_demon_pool.newObject();
		jellyfish_demon1.reset(spawnPositionX1);
		jellyfish_demon2.reset(spawnPositionX2);
		SpriteContainer.jellyfish_demons.add(jellyfish_demon1);
		SpriteContainer.jellyfish_demons.add(jellyfish_demon2);
		
		
	}
}

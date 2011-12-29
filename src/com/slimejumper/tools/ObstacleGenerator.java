package com.slimejumper.tools;

import java.util.Random;

import com.slimejumper.levels.CaveLevel;
import com.slimejumper.levels.Level;
import com.slimejumper.world.enemies.FlyingSnake;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.environment.GreekPlatform;

public class ObstacleGenerator {
	
	CaveLevel caveLevel;
	Random random;
	
	public ObstacleGenerator(CaveLevel caveLevel){
		this.caveLevel = caveLevel;
		random = new Random();
	}
	
	//position represents vertical dimension
	//should be between 0 and 12
	public void generatePlatform(float min, float max){
		float spawnPositionY = random.nextFloat() * (max - min) + min;
		
		
//		Platform platform = world.platformPool.newObject();
		GreekPlatform platform = PoolManager.pool_manager_singleton.greek_platform_pool.newObject();
		platform.reset(Level.WORLD_DEFAULT_WIDTH, spawnPositionY, GreekPlatform.PLATFORM_MEDIUM_LENGTH, GreekPlatform.PLATFORM_STATE_ZOOM);
		
		caveLevel.greek_platforms_volatile.isEmpty();
		caveLevel.greek_platforms_volatile.add(platform);
	}
/*	
	public void generatePurpleGhost(float min, float max){
		float spawnPositionY = random.nextFloat() * (max - min) + min;
		
		PurpleGhost purple_ghost = PoolManager.pool_manager_singleton.purple_ghost_pool.newObject();
		purple_ghost.reset(spawnPositionY);
		SpriteContainer.purple_ghosts.add(purple_ghost);

	}
*/	
	public void generateFlyingSnake(float spawnPositionY){
		
		FlyingSnake flying_snake = PoolManager.pool_manager_singleton.flying_snake_pool.newObject();
		flying_snake.reset(spawnPositionY);
		caveLevel.flying_snakes.add(flying_snake);
	}

	public void generateJellyfishDemon() {
		JellyfishDemon jellyfish_demon = PoolManager.pool_manager_singleton.jellyfish_demon_pool.newObject();
		float spawnPositionX = random.nextFloat() * Level.WORLD_DEFAULT_WIDTH/2 + Level.WORLD_DEFAULT_WIDTH/4;
		jellyfish_demon.reset(spawnPositionX);
		caveLevel.jellyfish_demons.add(jellyfish_demon);
	}
	
	public void generateJellyfishDemonPair() {
		float spawnPositionX1 = random.nextFloat() * Level.WORLD_DEFAULT_WIDTH/2;
		float spawnPositionX2 = random.nextFloat() * Level.WORLD_DEFAULT_WIDTH/2 + Level.WORLD_DEFAULT_WIDTH/2;
		
		JellyfishDemon jellyfish_demon1 = PoolManager.pool_manager_singleton.jellyfish_demon_pool.newObject();
		JellyfishDemon jellyfish_demon2 = PoolManager.pool_manager_singleton.jellyfish_demon_pool.newObject();
		jellyfish_demon1.reset(spawnPositionX1);
		jellyfish_demon2.reset(spawnPositionX2);
		caveLevel.jellyfish_demons.add(jellyfish_demon1);
		caveLevel.jellyfish_demons.add(jellyfish_demon2);
	}
}

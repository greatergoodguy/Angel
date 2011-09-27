package com.slimejumper.world;

import java.util.Random;

import com.slimejumper.world.enemies.FlyingSnake;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.enemies.PurpleGhost;

public class ObstacleGenerator {
		
	World world;
	Random random;
	
	public ObstacleGenerator(World world){
		this.world = world;
		random = new Random();
	}
	
	//position represents vertical dimension
	//should be between 0 and 12
	public void generatePlatform(float min, float max){
		float spawnPositionY = random.nextFloat() * (max - min) + min;
		
		
//		Platform platform = world.platformPool.newObject();
		Platform platform = World.poolManager.platformPool.newObject();
		platform.reset(World.WORLD_RIGHT_EDGE, spawnPositionY, Platform.PLATFORM_MEDIUM_LENGTH, Platform.PLATFORM_STATE_ZOOM);
		Platform.volatile_platforms.add(platform);
	}
	
	public void generatePurpleGhost(float min, float max){
		float spawnPositionY = random.nextFloat() * (max - min) + min;
		
		PurpleGhost purple_ghost = World.poolManager.purple_ghost_pool.newObject();
		purple_ghost.reset(spawnPositionY);
		PurpleGhost.purple_ghosts.add(purple_ghost);

	}
	
	public void generateFlyingSnake(float spawnPositionY){
		
		FlyingSnake flying_snake = World.poolManager.flying_snake_pool.newObject();
		flying_snake.reset(spawnPositionY);
		FlyingSnake.flying_snakes.add(flying_snake);
	}

	public void generateJellyfishDemon() {
		JellyfishDemon jellyfish_demon = World.poolManager.jellyfish_demon_pool.newObject();
		float spawnPositionX = random.nextFloat() * World.WORLD_WIDTH/2 + World.WORLD_WIDTH/4;
		jellyfish_demon.reset(spawnPositionX);
		JellyfishDemon.jellyfish_demons.add(jellyfish_demon);
	}
	
	public void generateJellyfishDemonPair() {
		float spawnPositionX1 = random.nextFloat() * World.WORLD_WIDTH/2;
		float spawnPositionX2 = random.nextFloat() * World.WORLD_WIDTH/2 + World.WORLD_WIDTH/2;
		
		JellyfishDemon jellyfish_demon1 = World.poolManager.jellyfish_demon_pool.newObject();
		JellyfishDemon jellyfish_demon2 = World.poolManager.jellyfish_demon_pool.newObject();
		jellyfish_demon1.reset(spawnPositionX1);
		jellyfish_demon2.reset(spawnPositionX2);
		JellyfishDemon.jellyfish_demons.add(jellyfish_demon1);
		JellyfishDemon.jellyfish_demons.add(jellyfish_demon2);
		
		
	}
}

package com.slimejumper.tools;

import java.util.Random;

import com.slimejumper.GameScreen;
import com.slimejumper.levels.Level;
import com.slimejumper.world.Platform;
import com.slimejumper.world.enemies.FlyingSnake;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.enemies.PurpleGhost;

public class SpriteFactory {
	
	Random random;
	
	public SpriteFactory(){
		random = new Random();
	}
	
	//position represents vertical dimension
	//should be between 0 and 12
	public void createMovingPlatform(float min, float max){
		float spawnPositionY = random.nextFloat() * (max - min) + min;
		
		
//		Platform platform = world.platformPool.newObject();
		Platform platform = GameScreen.pool_manager.platform_pool.newObject();
		platform.reset(Level.WORLD_RIGHT_EDGE, spawnPositionY, Platform.PLATFORM_MEDIUM_LENGTH, Platform.PLATFORM_STATE_ZOOM);
		SpriteContainer.volatile_platforms.add(platform);
	}
	
	public void createPurpleGhost(float min, float max){
		float spawnPositionY = random.nextFloat() * (max - min) + min;
		
		PurpleGhost purple_ghost = GameScreen.pool_manager.purple_ghost_pool.newObject();
		purple_ghost.reset(spawnPositionY);
		SpriteContainer.purple_ghosts.add(purple_ghost);

	}
	
	public void createFlyingSnake(float spawnPositionY){
		
		FlyingSnake flying_snake = GameScreen.pool_manager.flying_snake_pool.newObject();
		flying_snake.reset(spawnPositionY);
		SpriteContainer.flying_snakes.add(flying_snake);
	}

	public void createJellyfishDemon() {
		JellyfishDemon jellyfish_demon = GameScreen.pool_manager.jellyfish_demon_pool.newObject();
		float spawnPositionX = random.nextFloat() * Level.WORLD_WIDTH/2 + Level.WORLD_WIDTH/4;
		jellyfish_demon.reset(spawnPositionX);
		SpriteContainer.jellyfish_demons.add(jellyfish_demon);
	}
	
	public void createJellyfishDemonPair() {
		float spawnPositionX1 = random.nextFloat() * Level.WORLD_WIDTH/2;
		float spawnPositionX2 = random.nextFloat() * Level.WORLD_WIDTH/2 + Level.WORLD_WIDTH/2;
		
		JellyfishDemon jellyfish_demon1 = GameScreen.pool_manager.jellyfish_demon_pool.newObject();
		JellyfishDemon jellyfish_demon2 = GameScreen.pool_manager.jellyfish_demon_pool.newObject();
		jellyfish_demon1.reset(spawnPositionX1);
		jellyfish_demon2.reset(spawnPositionX2);
		SpriteContainer.jellyfish_demons.add(jellyfish_demon1);
		SpriteContainer.jellyfish_demons.add(jellyfish_demon2);		
	}
}

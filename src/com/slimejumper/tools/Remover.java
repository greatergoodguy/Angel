package com.slimejumper.tools;

import com.slimejumper.world.attacks.Shockball;

public class Remover {
	
	public Remover(){
		
	}
	
	/*
	 * Every List needs a remove method and a clear method
	 */
	
	/*
	public static void remove(){
		removePlatforms();
		
		removeJellyfishDemons();
		removeFlyingSnakes();
		
		removeShockballs();
	}
	
	private static void removePlatforms() {
		if(SpriteContainer.volatile_platforms.isEmpty())
			return;		

		GreekPlatform platform = SpriteContainer.volatile_platforms.getFirst();

		if (platform.life_timer > GreekPlatform.PLATFORM_LIFESPAN) {
			SpriteContainer.volatile_platforms.removeFirst();
			PoolManager.pool_manager_singleton.greek_platform_pool.free(platform);			
		}
	}
	
	private static void removeJellyfishDemons() {
		if(SpriteContainer.jellyfish_demons.isEmpty())
			return;
		
		JellyfishDemon jellyfish_demon = SpriteContainer.jellyfish_demons.getFirst();
		if(jellyfish_demon.life_timer > JellyfishDemon.JELLY_LIFESPAN){
			SpriteContainer.jellyfish_demons.removeFirst();
			PoolManager.pool_manager_singleton.jellyfish_demon_pool.free(jellyfish_demon);
		}
		
	}
	
	private static void removeFlyingSnakes() {
		if(SpriteContainer.flying_snakes.isEmpty())
			return;
		
		FlyingSnake flying_snake = SpriteContainer.flying_snakes.getFirst();
		if(flying_snake.life_timer > FlyingSnake.FLYING_SNAKE_LIFESPAN){
			SpriteContainer.flying_snakes.removeFirst();
			PoolManager.pool_manager_singleton.flying_snake_pool.free(flying_snake);
		}
	}

	private static void removeShockballs() {
		if(SpriteContainer.shockballs.isEmpty())
			return;
		
		Shockball shockball = SpriteContainer.shockballs.getFirst();
		if(shockball.life_timer > Shockball.SHOCKBALL_LIFESPAN){
			SpriteContainer.shockballs.removeFirst();
			PoolManager.pool_manager_singleton.shockball_pool.free(shockball);
		}	
	}

	*/
	public static void clearAllLists(){		
		clearShockBalls();
	}

/*
	// clear
	private static void clearPlatforms() {

		while(!SpriteContainer.volatile_platforms.isEmpty()){
			GreekPlatform platform = SpriteContainer.volatile_platforms.removeFirst();
			PoolManager.pool_manager_singleton.greek_platform_pool.free(platform);			
		}
		while(!SpriteContainer.ground_platforms.isEmpty()){
			GreekPlatform platform = SpriteContainer.ground_platforms.removeFirst();
			PoolManager.pool_manager_singleton.greek_platform_pool.free(platform);	
		}
	}

	private static void clearJellyfishDemons() {
		while(!SpriteContainer.jellyfish_demons.isEmpty()){
			JellyfishDemon jellyfish_demon = SpriteContainer.jellyfish_demons.removeFirst();
			PoolManager.pool_manager_singleton.jellyfish_demon_pool.free(jellyfish_demon);
		}
		
	}

	private static void clearFlyingSnakes() {
		while(!SpriteContainer.flying_snakes.isEmpty()){
			FlyingSnake flying_snake = SpriteContainer.flying_snakes.removeFirst();
			PoolManager.pool_manager_singleton.flying_snake_pool.free(flying_snake);
		}	
	}
*/
	private static void clearShockBalls() {
		while(!SpriteContainer.shockballs.isEmpty()){
			Shockball shockball = SpriteContainer.shockballs.removeFirst();
			PoolManager.pool_manager_singleton.shockball_pool.free(shockball);
		}		
	}
}

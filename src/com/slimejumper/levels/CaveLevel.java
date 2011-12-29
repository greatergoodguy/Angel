package com.slimejumper.levels;

import java.util.LinkedList;

import com.slimejumper.Controller;
import com.slimejumper.tools.CollisionManager;
import com.slimejumper.tools.ObstacleGeneratorManager;
import com.slimejumper.tools.PoolManager;
import com.slimejumper.world.Background;
import com.slimejumper.world.attacks.Shockball;
import com.slimejumper.world.enemies.FlyingSnake;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.environment.GreekPlatform;

public class CaveLevel extends Level{

	public final float BACKGROUND_CAVE_BACK_LAYER_WIDTH = 15;
	
	ObstacleGeneratorManager obstacle_generator_manager;
	public final Background cave_background;
	
	public LinkedList<GreekPlatform> greek_platforms_ground;
	
	public LinkedList<GreekPlatform> greek_platforms_volatile;
	
	public LinkedList<JellyfishDemon> jellyfish_demons;
	public LinkedList<FlyingSnake> flying_snakes;
	public LinkedList<Shockball> shockballs;
	
	float level_timer;
	int level_counter;

	public CaveLevel(WorldListener listener, Controller controller) {
		super(listener, controller);
	
		obstacle_generator_manager = new ObstacleGeneratorManager(this);
		cave_background = new Background(BACKGROUND_CAVE_BACK_LAYER_WIDTH, 0, WORLD_DEFAULT_WIDTH, Level.WORLD_DEFAULT_HEIGHT);
		
		initializeGreekPlatforms();
		initializeOtherLists();
		
		level_timer = 0;
		level_counter = 1;
	}

	private void initializeGreekPlatforms() {
		greek_platforms_ground = new LinkedList<GreekPlatform>();
		GreekPlatform.initializePlatformGround(this, greek_platforms_ground);
	}
	
	private void initializeOtherLists() {
		greek_platforms_volatile = new LinkedList<GreekPlatform>();
		jellyfish_demons = new LinkedList<JellyfishDemon>();
		flying_snakes = new LinkedList<FlyingSnake>();
		shockballs = new LinkedList<Shockball>();		
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		updateHero(deltaTime);
		updateSprites(deltaTime);
		manageCollisions();
		
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

	private void updateSprites(float deltaTime) {
		for(GreekPlatform greek_platform : greek_platforms_volatile)
			greek_platform.update(deltaTime);
		for(JellyfishDemon jellyfish_demon : jellyfish_demons)
			jellyfish_demon.update(deltaTime);
		for(FlyingSnake flying_snake : flying_snakes)
			flying_snake.update(deltaTime);
		for(Shockball shockball : shockballs)
			shockball.update(deltaTime);
	}
	
	private void manageCollisions() {
		CollisionManager.HeroPlatformCollision(hero, greek_platforms_ground);	
	}
	
	@Override
	public void dispose() {
		hero.dispose();
		
		while(!greek_platforms_ground.isEmpty()){
			GreekPlatform greek_platform = greek_platforms_ground.removeFirst();
			PoolManager.pool_manager_singleton.greek_platform_pool.free(greek_platform);
		}
		
		while(!greek_platforms_volatile.isEmpty()){
			GreekPlatform greek_platform = greek_platforms_volatile.removeFirst();
			PoolManager.pool_manager_singleton.greek_platform_pool.free(greek_platform);
		}
		
		while(!jellyfish_demons.isEmpty()){
			JellyfishDemon jellyfish_demon = jellyfish_demons.removeFirst();
			PoolManager.pool_manager_singleton.jellyfish_demon_pool.free(jellyfish_demon);
		}
		
		while(!flying_snakes.isEmpty()){
			FlyingSnake flying_snake = flying_snakes.removeFirst();
			PoolManager.pool_manager_singleton.flying_snake_pool.free(flying_snake);
		}
		
		while(!shockballs.isEmpty()){
			Shockball shockball = shockballs.removeFirst();
			PoolManager.pool_manager_singleton.shockball_pool.free(shockball);
		}
		
	}
}
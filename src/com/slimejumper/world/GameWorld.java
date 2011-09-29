package com.slimejumper.world;

import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.tools.Remover;
import com.slimejumper.tools.UnitCircle;
import com.slimejumper.tools.World;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.attacks.MusicNote;
import com.slimejumper.world.attacks.Shockball;
import com.slimejumper.world.enemies.Enemy;
import com.slimejumper.world.enemies.FlyingSnake;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.enemies.PurpleGhost;

public class GameWorld extends World{
	public interface WorldListener {
		public void jump();

		public void killJump();

		public void hit();

		public void coin();
	}

	public Background background;
//	public static PoolManager poolManager;
	
	ObstacleGeneratorManager level_manager;
	public final WorldListener listener;

	float level_timer;
	int level_counter;

	public GameWorld(WorldListener listener) {
//		initializeLists();
		
		if(hero == null)
			World.hero = new Hero();
		this.listener = listener;
		
		background = new Background(this);
		level_manager = new ObstacleGeneratorManager(this);
//		remover = new Remover();
		collisionManager.setCollidingHero(hero);

		center = new Vector2(WORLD_CENTER_DEFAULT_X, WORLD_CENTER_DEFAULT_Y);
		position = new Vector2();
		
		level_timer = 0;
		level_counter = 1;
		
		UnitCircle.initializeUnitCircle();
		Platform.initializePlatformGround();
//		Platform.initializePlatformMap();
	}

	public void update(float deltaTime) {
		//First Update Sprites of this extended Class
		updateSprites(deltaTime);
		updateLevel(deltaTime);
		
		//Update General Parameters second (center, position, collisionManager, remover)
		super.update();	
	}

	private void updateLevel(float deltaTime) {
		level_timer += deltaTime;
		
		if(level_timer > 15.0f){
			level_counter++;
			level_timer = 0;
		}
		
//		manager.generateTestLevel(deltaTime);
		
		
		if(level_counter == 1 )
			level_manager.generateLevelOne(deltaTime);
		else if(level_counter == 2 )
			level_manager.generateLevelTwo(deltaTime);
		else
			level_manager.generateSampleLevel(deltaTime);
			
	}

	private void updateSprites(float deltaTime) {
		updateBackground();
		updatePlatforms(deltaTime);
		updateEnemies(deltaTime);
		updateHero(deltaTime);
		updateAttacks(deltaTime);
	}

	private void updateBackground() {
		background.update();
	
	}

	private void updatePlatforms(float deltaTime) {
		for(Platform platform : Platform.static_platforms)
			platform.update(deltaTime);
		for (Platform platform : Platform.volatile_platforms)
			platform.update(deltaTime);
	}

	private void updateEnemies(float deltaTime) {
		for (Enemy enemy : Enemy.sample_enemies)
			enemy.update(deltaTime);
		for (PurpleGhost purple_ghost : PurpleGhost.purple_ghosts)
			purple_ghost.update(deltaTime);
		for(JellyfishDemon jellyfish_demon : JellyfishDemon.jellyfish_demons)
			jellyfish_demon.update(deltaTime);
		for(FlyingSnake flying_snake : FlyingSnake.flying_snakes)
			flying_snake.update(deltaTime);
	}
	
	private void updateHero(float deltaTime) {
		hero.update(deltaTime);
	}
	
	private void updateAttacks(float deltaTime) {
		for(HaloAttack halo_attack : HaloAttack.halo_attacks)
			halo_attack.update(deltaTime);
		for(MusicNote music_note : MusicNote.music_notes)
			music_note.update(hero, deltaTime);
		for(Shockball shockball : Shockball.shockballs)
			shockball.update(deltaTime);
		
	}
}
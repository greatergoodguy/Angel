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

	public static final float METER = 80; // 1 meter equals 80 pixels

	public static final float WORLD_WIDTH = 10 * 2; // 10 refers to the visible
													// width
	public static final float WORLD_HEIGHT = 6 * 2; // 6 refers to the visible
													// height

	public static final float WORLD_CENTER_DEFAULT_X = WorldRenderer.FRUSTUM_WIDTH / 2;
	public static final float WORLD_CENTER_DEFAULT_Y = WorldRenderer.FRUSTUM_HEIGHT / 2;
	
	public static final float WORLD_GRAVITY = -8.5f;
	public static final float WORLD_GRAVITY_TIMES_TWO = WORLD_GRAVITY * 2;
	
	
	public static final float WORLD_LEFT_EDGE = 0f;
	public static final float WORLD_RIGHT_EDGE = 20.0f;
	public static final float WORLD_BOTTOM_EDGE = 0f;
	public static final float WORLD_TOP_EDGE = 12.0f;

	public static final float WORLD_LEFT_BOUND = 5.0f;
	public static final float WORLD_RIGHT_BOUND = 15.0f;
	public static final float WORLD_BOTTOM_BOUND = 3.0f;
	public static final float WORLD_TOP_BOUND = 9.0f;
	private static final float WORLD_VERTICAL_BOUND_ADJUSTER = 1.5f;

	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;
	
	public Background background;
//	public static PoolManager poolManager;
	
	ObstacleGeneratorManager manager;
	Remover remover;
//	CollisionManager collisionManager;

	public final WorldListener listener;

	public Hero hero;
	public Vector2 center;
	public Vector2 position;
	
	float level_timer;
	int level_counter;

	public GameWorld(WorldListener listener) {
//		initializeLists();
		
		this.hero = new Hero();
		this.listener = listener;
		
		background = new Background(this);
		manager = new ObstacleGeneratorManager(this);
		remover = new Remover();
		collisionManager.setCollidingHero(hero);
//		collisionManager = new CollisionManager(this);

		center = new Vector2(WORLD_CENTER_DEFAULT_X, WORLD_CENTER_DEFAULT_Y);
		position = new Vector2();
		
		level_timer = 0;
		level_counter = 1;
		
		UnitCircle.initializeUnitCircle();
		Platform.initializePlatformGround();
//		Platform.initializePlatformMap();
	}

	public void update(float deltaTime) {

		updateSprites(deltaTime);
		collisionManager.manageCollisions();
		remover.remove();

		updateCenter();
		updatePosition();

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
			manager.generateLevelOne(deltaTime);
		else if(level_counter == 2 )
			manager.generateLevelTwo(deltaTime);
		else
			manager.generateSampleLevel(deltaTime);
			
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
	
	private void updateCenter() {
		// Checks Horizontal Bounds
		if (hero.center.x < WORLD_LEFT_BOUND)
			center.x = WORLD_LEFT_BOUND;
		else if (hero.center.x > WORLD_RIGHT_BOUND)
			center.x = WORLD_RIGHT_BOUND;
		else
			center.x = hero.center.x;
		
		// Checks Vertical Bound
		if(hero.center.y < WORLD_BOTTOM_BOUND + WORLD_VERTICAL_BOUND_ADJUSTER)
			center.y = WORLD_BOTTOM_BOUND;
		else if(hero.center.y > WORLD_TOP_BOUND)
			center.y = WORLD_TOP_BOUND - WORLD_VERTICAL_BOUND_ADJUSTER;
		else
			center.y = hero.center.y - WORLD_VERTICAL_BOUND_ADJUSTER;
	}

	private void updatePosition() {
		position.x = center.x - WORLD_CENTER_DEFAULT_X;
		position.y = center.y - WORLD_CENTER_DEFAULT_Y;

		if (position.x > WORLD_RIGHT_BOUND - WORLD_LEFT_BOUND)
			position.x = WORLD_RIGHT_BOUND - WORLD_LEFT_BOUND;
	}
/*
	private void updateSpriteCollisions(){
		adjustHeroCollisions();
		adjustJellyfishDemonsCollisions();
	}

	private void adjustHeroCollisions() {
			checkPlatformCollisions();
			checkEnemyCollisions();
		}

	private void adjustJellyfishDemonsCollisions() {
		checkHeroAttackCollision();
		
	}

	private void checkHeroAttackCollision() {
		if(HaloAttack.halo_attacks.isEmpty() || JellyfishDemon.jellyfish_demons.isEmpty())
			return;
		
		for(HaloAttack halo_attack : HaloAttack.halo_attacks){
			for(JellyfishDemon jellyfish_demon : JellyfishDemon.jellyfish_demons){
				
				if(jellyfish_demon.state == JellyfishDemon.JELLY_STATE_COLLIDED || 
						OverlapTester.overlapRectangles(halo_attack, jellyfish_demon)){
					jellyfish_demon.changeToCollidedState(halo_attack);
				}
			}
		}
			
	}

	private void checkEnemyCollisions() {
		if(hero.state == Hero.HERO_STATE_COLLIDED)
			return;
		
		for(PurpleGhost purple_ghost : PurpleGhost.purple_ghosts){
			if(OverlapTester.overlapRectangles(hero, purple_ghost)){
				hero.changeToCollidedState();
				listener.hit();hero_center
			}
				
		}
		
		for(JellyfishDemon jellyfish_demon : JellyfishDemon.jellyfish_demons){
			if(OverlapTester.overlapRectangles(hero, jellyfish_demon)){
				hero.changeToCollidedState();
				listener.hit();
			}
				
		}
		
	}

	private void checkPlatformCollisions() {
		if (hero.velocity.y > 0)
			return;
	
		for (Platform platform : Platform.volatile_platforms) 
			heroPlatformRebound(platform);
		for(Platform platform : Platform.ground_platforms)
			heroPlatformRebound(platform);
		for(Platform platform : Platform.static_platforms)
			heroPlatformRebound(platform);
	}

	private void removeUnnecessary() {
		removeUnnecessaryPlatform();
		removeUnnecessaryEnemies();
		removeHaloAttacks();
		removeMusicNotes();
		removeShockballs();
	}

	private void removeUnnecessaryPlatform() {
		if(Platform.volatile_platforms.isEmpty())
			return;		

		Platform platform = Platform.volatile_platforms.getFirst();

		if (platform.life_timer > Platform.PLATFORM_LIFESPAN) {
			Platform.volatile_platforms.removeFirst();
			poolManager.platformPool.free(platform);			
		}
	}

	private void removeUnnecessaryEnemies() {
		removePurpleGhosts();
		removeJellyfishDemons();
		removeFlyingSnakes();
	}

	private void removePurpleGhosts() {
		if(PurpleGhost.purple_ghosts.isEmpty())
			return;
		
		PurpleGhost purple_ghost = PurpleGhost.purple_ghosts.getFirst();
		if(purple_ghost.life_timer > PurpleGhost.PurpleGhost_LIFESPAN){
			PurpleGhost.purple_ghosts.removeFirst();
			poolManager.purple_ghost_pool.free(purple_ghost);
		}
	}

	private void removeJellyfishDemons() {
		if(JellyfishDemon.jellyfish_demons.isEmpty())
			return;
		
		JellyfishDemon jellyfish_demon = JellyfishDemon.jellyfish_demons.getFirst();
		if(jellyfish_demon.life_timer > JellyfishDemon.JELLY_LIFESPAN){
			JellyfishDemon.jellyfish_demons.removeFirst();
			poolManager.jellyfish_demon_pool.free(jellyfish_demon);
		}
		
	}
	
	private void removeFlyingSnakes() {
		if(FlyingSnake.flying_snakes.isEmpty())
			return;
		
		FlyingSnake flying_snake = FlyingSnake.flying_snakes.getFirst();
		if(flying_snake.life_timer > FlyingSnake.FLYING_SNAKE_LIFESPAN){
			FlyingSnake.flying_snakes.removeFirst();
			poolManager.flying_snake_pool.free(flying_snake);
		}
	}
	
	private void removeHaloAttacks() {
		if(HaloAttack.halo_attacks.isEmpty())
			return;
		
		HaloAttack halo_attack = HaloAttack.halo_attacks.getFirst();
		if(halo_attack.life_timer > HaloAttack.HaloAttack_LIFESPAN){
			HaloAttack.halo_attacks.removeFirst();
			poolManager.halo_attack_pool.free(halo_attack);
		}
		
	}
	
	private void removeMusicNotes() {
		if(MusicNote.music_notes.isEmpty())
			return;
		
		MusicNote music_note = MusicNote.music_notes.getFirst();
		if(music_note.life_timer > MusicNote.MUSIC_NOTE_LIFESPAN){
			MusicNote.music_notes.removeFirst();
			poolManager.music_note_pool.free(music_note);
		}
	}
	
	private void removeShockballs() {
		if(Shockball.shockballs.isEmpty())
			return;
		
		Shockball shockball = Shockball.shockballs.getFirst();
		if(shockball.life_timer > Shockball.SHOCKBALL_LIFESPAN){
			Shockball.shockballs.removeFirst();
			poolManager.shockball_pool.free(shockball);
		}
		
	}
	
*/
}
package com.slimejumper.tools;

import com.slimejumper.gameframework.Pool;
import com.slimejumper.gameframework.Pool.PoolObjectFactory;
import com.slimejumper.world.Platform;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.attacks.MusicNote;
import com.slimejumper.world.attacks.Shockball;
import com.slimejumper.world.attacks.SpiralAttack;
import com.slimejumper.world.enemies.FlyingSnake;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.enemies.PurpleGhost;

public class PoolManager {
	public static final PoolManager pool_manager_singleton = new PoolManager();

	public Pool<Platform> platform_pool;
	public Pool<PurpleGhost> purple_ghost_pool;
	public Pool<JellyfishDemon> jellyfish_demon_pool;
	public Pool<HaloAttack> halo_attack_pool;
	public Pool<Shockball> shockball_pool;
	public Pool<MusicNote> music_note_pool;
	public Pool<FlyingSnake> flying_snake_pool;
	public Pool<SpiralAttack> spiral_attack_pool;
	
	private PoolManager(){
		PoolObjectFactory<Platform> platform_factory = new PoolObjectFactory<Platform>() {
			public Platform createObject() {
				return new Platform();
			}
		};
		
		PoolObjectFactory<PurpleGhost> purple_ghost_factory = new PoolObjectFactory<PurpleGhost>(){
			public PurpleGhost createObject(){
				return new PurpleGhost();
			}
		};
		
		PoolObjectFactory<JellyfishDemon> jellyfish_demon_factory = new PoolObjectFactory<JellyfishDemon>(){
			public JellyfishDemon createObject(){
				return new JellyfishDemon();
			}
		};
		
		PoolObjectFactory<HaloAttack> halo_attack_factory = new PoolObjectFactory<HaloAttack>(){
			public HaloAttack createObject(){
				return new HaloAttack();
			}
		};
		
		PoolObjectFactory<Shockball> shockball_factory = new PoolObjectFactory<Shockball>(){
			public Shockball createObject(){
				return new Shockball();
			}
		};

		PoolObjectFactory<MusicNote> music_note_factory = new PoolObjectFactory<MusicNote>(){
			public MusicNote createObject(){
				return new MusicNote();
			}
		};
		
		PoolObjectFactory<FlyingSnake> flying_snake_factory = new PoolObjectFactory<FlyingSnake>(){
			public FlyingSnake createObject(){
				return new FlyingSnake();
			}
		};
		
		PoolObjectFactory<SpiralAttack> spiral_attack_factory = new PoolObjectFactory<SpiralAttack>(){
			public SpiralAttack createObject(){
				return new SpiralAttack();
			}
		};
		
		platform_pool = new Pool<Platform>(platform_factory, 50);
		purple_ghost_pool = new Pool<PurpleGhost>(purple_ghost_factory, 35);
		jellyfish_demon_pool = new Pool<JellyfishDemon>(jellyfish_demon_factory, 35);
		halo_attack_pool = new Pool<HaloAttack>(halo_attack_factory, 35);
		shockball_pool = new Pool<Shockball>(shockball_factory, 35);
		music_note_pool = new Pool<MusicNote>(music_note_factory, 60);
		flying_snake_pool = new Pool<FlyingSnake>(flying_snake_factory, 20);
		spiral_attack_pool = new Pool<SpiralAttack>(spiral_attack_factory, 20);
	}
}

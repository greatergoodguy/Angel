package com.slimejumper.tools;

import com.slimejumper.gameframework.Pool;
import com.slimejumper.gameframework.Pool.PoolObjectFactory;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.attacks.MusicNote;
import com.slimejumper.world.attacks.PurpleFlame;
import com.slimejumper.world.attacks.Shockball;
import com.slimejumper.world.attacks.SpiralAttack;
import com.slimejumper.world.enemies.FlyingSnake;
import com.slimejumper.world.enemies.JellyfishDemon;
import com.slimejumper.world.enemies.PurpleGhost;
import com.slimejumper.world.enemies.RedWhaleDemon;
import com.slimejumper.world.environment.CloudPlatform;
import com.slimejumper.world.environment.CloudPlatformShort;
import com.slimejumper.world.environment.GreekPlatform;
import com.slimejumper.world.environment.RockPlatform;

public class PoolManager {
	public static final PoolManager pool_manager_singleton = new PoolManager();

	public Pool<PurpleGhost> purple_ghost_pool;
	public Pool<JellyfishDemon> jellyfish_demon_pool;
	public Pool<FlyingSnake> flying_snake_pool;
	public Pool<RedWhaleDemon> red_whale_demon_pool;
	
	
	public Pool<HaloAttack> halo_attack_pool;
	public Pool<MusicNote> music_note_pool;
	public Pool<SpiralAttack> spiral_attack_pool;
	public Pool<Shockball> shockball_pool;
	public Pool<PurpleFlame> purple_flame_pool;
	
	public Pool<GreekPlatform> greek_platform_pool;
	public Pool<RockPlatform> rock_platform_pool;
	public Pool<CloudPlatform> cloud_platform_pool;
	public Pool<CloudPlatformShort> cloud_platform_short_pool;
	
	private PoolManager(){		
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
		
		PoolObjectFactory<RedWhaleDemon> red_whale_demon_factory = new PoolObjectFactory<RedWhaleDemon>(){
			public RedWhaleDemon createObject(){
				return new RedWhaleDemon();
			}
		};
		
		PoolObjectFactory<FlyingSnake> flying_snake_factory = new PoolObjectFactory<FlyingSnake>(){
			public FlyingSnake createObject(){
				return new FlyingSnake();
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
		
		PoolObjectFactory<SpiralAttack> spiral_attack_factory = new PoolObjectFactory<SpiralAttack>(){
			public SpiralAttack createObject(){
				return new SpiralAttack();
			}
		};
		
		PoolObjectFactory<GreekPlatform> platform_factory = new PoolObjectFactory<GreekPlatform>() {
			public GreekPlatform createObject() {
				return new GreekPlatform();
			}
		};
		
		PoolObjectFactory<RockPlatform> rock_platform_factory = new PoolObjectFactory<RockPlatform>(){
			public RockPlatform createObject(){
				return new RockPlatform();
			}
		};
		
		PoolObjectFactory<CloudPlatform> cloud_platform_factory = new PoolObjectFactory<CloudPlatform>(){
			public CloudPlatform createObject(){
				return new CloudPlatform();
			}
		};
		
		PoolObjectFactory<PurpleFlame> purple_flame_factory = new PoolObjectFactory<PurpleFlame>(){
			public PurpleFlame createObject(){
				return new PurpleFlame();
			}
		};
		
		PoolObjectFactory<CloudPlatformShort> cloud_platform_short_factory = new PoolObjectFactory<CloudPlatformShort>(){
			public CloudPlatformShort createObject(){
				return new CloudPlatformShort();
			}
		};
		
		purple_ghost_pool = new Pool<PurpleGhost>(purple_ghost_factory, 35);
		jellyfish_demon_pool = new Pool<JellyfishDemon>(jellyfish_demon_factory, 35);
		flying_snake_pool = new Pool<FlyingSnake>(flying_snake_factory, 20);
		red_whale_demon_pool = new Pool<RedWhaleDemon>(red_whale_demon_factory, 20);
		
		halo_attack_pool = new Pool<HaloAttack>(halo_attack_factory, 10);
		music_note_pool = new Pool<MusicNote>(music_note_factory, 35);
		spiral_attack_pool = new Pool<SpiralAttack>(spiral_attack_factory, 10);
		shockball_pool = new Pool<Shockball>(shockball_factory, 10);
		purple_flame_pool = new Pool<PurpleFlame>(purple_flame_factory, 10);

		greek_platform_pool = new Pool<GreekPlatform>(platform_factory, 35);
		rock_platform_pool = new Pool<RockPlatform>(rock_platform_factory, 20);
		cloud_platform_pool = new Pool<CloudPlatform>(cloud_platform_factory, 35);
		cloud_platform_short_pool = new Pool<CloudPlatformShort>(cloud_platform_short_factory, 35);
	}
}

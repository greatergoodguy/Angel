package com.slimejumper.world.attacks;

import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.world.Hero;
import com.slimejumper.world.GameWorld;

public class HaloAttack extends Attack{
	public static final float HaloAttack_WIDTH = 0.5375f;
	public static final float HaloAttack_HEIGHT = 0.325f;
	
	public static final float HaloAttack_LAUNCH_VELOCITY = -6.0f;
	
	public static final float HaloAttack_LIFESPAN = 3.0f;
	
	public HaloAttack() {
		this(3,3);
	}
	
	public HaloAttack(float x, float y){
		super(x, y, HaloAttack_WIDTH, HaloAttack_HEIGHT);
	}

	public void update(float deltaTime){
		super.update(deltaTime);
	}

	public void reset(Hero hero) {
		super.reset();
		resetPositionCenter(hero.center);
		facedirection = hero.facedirection;
		velocity.x = HaloAttack_LAUNCH_VELOCITY * facedirection;
	}

	public static void activate(Hero hero) {

		HaloAttack halo_attack = GameWorld.poolManager.halo_attack_pool.newObject();
		halo_attack.reset(hero);
		halo_attacks.add(halo_attack);
	}
	
	
}

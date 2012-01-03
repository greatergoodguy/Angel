package com.slimejumper.world.attacks;

import com.slimejumper.world.Hero;

public class HaloAttack extends Attack{
	public static final float HaloAttack_WIDTH = 0.5375f;
	public static final float HaloAttack_HEIGHT = 0.325f;
	
	public static final float HaloAttack_LAUNCH_VELOCITY = -6.0f;
	
	public static final float HaloAttack_LIFESPAN = 1.5f;
	
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

/*
	public static void activate(Hero hero) {

		HaloAttack halo_attack = GameScreen.pool_manager.halo_attack_pool.newObject();
		halo_attack.reset(hero);
		SpriteContainer.halo_attacks.add(halo_attack);
	}
*/	
	
}

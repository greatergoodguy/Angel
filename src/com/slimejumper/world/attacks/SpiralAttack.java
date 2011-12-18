package com.slimejumper.world.attacks;

import com.slimejumper.world.Hero;

public class SpiralAttack extends Attack{
//	public static final float SpiralAttack_WIDTH = 0.35f;	Actual Width
//	public static final float SpiralAttack_HEIGHT = 0.35f;	Actual Height
	
	public static final float SpiralAttack_WIDTH = 0.45f;	// Slightly Bigger Width
	public static final float SpiralAttack_HEIGHT = 0.45f;	// Slightly Bigger Height
		
	public static final float SpiralAttack_LAUNCH_VELOCITY = -6.0f;
		
	public static final float SpiralAttack_LIFESPAN = 3.0f;
	
	public float angle;
		
	public SpiralAttack() {
		this(3,3);
		angle = 0;
	}
		
	public SpiralAttack(float x, float y){
		super(x, y, SpiralAttack_WIDTH, SpiralAttack_HEIGHT);
		angle = 0;
	}

	public void update(float deltaTime){
		super.update(deltaTime);
		updateAngle(deltaTime);
	}

	private void updateAngle(float deltaTime) {
		angle += 512*deltaTime;
		if(angle > 360)
			angle = 0;		
	}

	public void reset(Hero hero) {
		super.reset();
		resetPositionCenter(hero.center);
		facedirection = hero.facedirection;
		velocity.x = SpiralAttack_LAUNCH_VELOCITY * facedirection;
		
		angle = 0;
	}
/*
	public static void activate(Hero hero) {
		SpiralAttack spiral_attack = GameScreen.pool_manager.spiral_attack_pool.newObject();
		spiral_attack.reset(hero);
		hero.hop();
		SpriteContainer.spiral_attacks.add(spiral_attack);
	}
*/	
}

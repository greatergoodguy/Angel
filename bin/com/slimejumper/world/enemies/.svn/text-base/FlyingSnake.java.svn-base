package com.slimejumper.world.enemies;

import com.slimejumper.world.World;

public class FlyingSnake extends Enemy{
	
	public static final float FLYING_SNAKE_STANDARD_TIMER_BOUND = 4.0f;
	public static final float FLYING_SNAKE_ATTACK_TIMER_BOUND = 0.8f;
	public static final float FLYING_SNAKE_ATTACK_CHARGE_TIMER_BOUND = 0.4f;
	public static final float FLYING_SNAKE_RELOAD_TIMER_BOUND = 2.0f;

	
	public static final int FLYING_SNAKE_STATE_STANDARD = 0;
	public static final int FLYING_SNAKE_STATE_ATTACK_CHARGE = 1;
	public static final int FLYING_SNAKE_STATE_ATTACK = 2;
	public static final int FLYING_SNAKE_STATE_RELOAD = 3;
	
	public static final float FLYING_SNAKE_STANDARD_WIDTH = 2.25f;
	public static final float FLYING_SNAKE_STANDARD_HEIGHT = 1.075f;
	public static final float FLYING_SNAKE_ATTACK_WIDTH = 1.7625f;
	public static final float FLYING_SNAKE_ATTACK_HEIGHT = 1.6125f;
	
	public static final float FLYING_SNAKE_LIFESPAN = 20.0f;
	
	public static final float FLYING_SNAKE_VERTICAL_VEL = 1.6f;
	public static final float FLYING_SNAKE_HORIZONTAL_VEL = -1.9f; 
	
	public static final float FLYING_SNAKE_VERTICAL_CHARGE_VEL = 1.1f;
	public static final float FLYING_SNAKE_HORIZONTAL_CHARGE_VEL = 1.1f;
	
	public static final float FLYING_SNAKE_VERTICAL_DIVE_VEL = -1.4f;
	public static final float FLYING_SNAKE_HORIZONTAL_DIVE_VEL = -2.4f; 
	
	public static final float FLYING_SNAKE_VERTICAL_RELOAD_VEL = 1.1f;
	public static final float FLYING_SNAKE_HORIZONTAL_RELOAD_VEL = -1.2f;
	
	public int state;
	public float state_timer;
	
	public FlyingSnake(){
		this(4, 4);
		velocity.set(0, 0);
	}
	
	public FlyingSnake(float x, float y) {
		super(x, y, FLYING_SNAKE_STANDARD_WIDTH, FLYING_SNAKE_STANDARD_HEIGHT);
		changeToStandardState();
	}
	
	public void reset(){
		super.reset();
		changeToStandardState();
	}
	
	public void reset(float spawnPositionY){
		this.reset();
		resetPositionLowerLeft(World.WORLD_RIGHT_EDGE, spawnPositionY);		
	}
	
	public void update(float deltaTime){

		super.update(deltaTime);
		
		switch(state){
		case FLYING_SNAKE_STATE_STANDARD:
			updateStandardState(deltaTime);
			break;
		case FLYING_SNAKE_STATE_ATTACK_CHARGE:
			updateAttackChargeState(deltaTime);
			break;
		case FLYING_SNAKE_STATE_ATTACK:
			updateAttackState(deltaTime);
			break;
		case FLYING_SNAKE_STATE_RELOAD:
			updateReloadState(deltaTime);
			break;
		}
	}
	
	public void changeToStandardState(){
		resetDimensions(FLYING_SNAKE_STANDARD_WIDTH, FLYING_SNAKE_STANDARD_HEIGHT);
		velocity.set(FLYING_SNAKE_HORIZONTAL_VEL, FLYING_SNAKE_VERTICAL_VEL);
		state = FLYING_SNAKE_STATE_STANDARD;
		state_timer = 0;
	}
	
	public void changeToAttackChargeState(){
		resetDimensions(FLYING_SNAKE_ATTACK_WIDTH, FLYING_SNAKE_ATTACK_HEIGHT);
		resetPositionCenter(center.x, center.y + 0.5f);
		state = FLYING_SNAKE_STATE_ATTACK_CHARGE;
		state_timer = 0;
	}
	
	public void changeToAttackState(){
		resetDimensions(FLYING_SNAKE_ATTACK_WIDTH, FLYING_SNAKE_ATTACK_HEIGHT);
		velocity.set(FLYING_SNAKE_HORIZONTAL_VEL, FLYING_SNAKE_VERTICAL_DIVE_VEL);
		state = FLYING_SNAKE_STATE_ATTACK;
		state_timer = 0;
	}
	
	public void changeToReloadState(){
		resetDimensions(FLYING_SNAKE_STANDARD_WIDTH, FLYING_SNAKE_STANDARD_HEIGHT);
		velocity.set(FLYING_SNAKE_HORIZONTAL_RELOAD_VEL, FLYING_SNAKE_VERTICAL_RELOAD_VEL);
		state = FLYING_SNAKE_STATE_RELOAD;
		state_timer = 0;
	}
	
	private void updateStandardState(float deltaTime){
		state_timer += deltaTime;
		velocity.y = (float) (FLYING_SNAKE_VERTICAL_VEL * Math.cos(state_timer));
		if(state_timer > FLYING_SNAKE_STANDARD_TIMER_BOUND){
			changeToAttackChargeState();
		}
	}
	
	private void updateAttackChargeState(float deltaTime){
		state_timer += deltaTime;
		velocity.set(FLYING_SNAKE_HORIZONTAL_CHARGE_VEL, FLYING_SNAKE_VERTICAL_CHARGE_VEL);
		if(state_timer > FLYING_SNAKE_ATTACK_CHARGE_TIMER_BOUND){
			changeToAttackState();
		}
	}
	
	private void updateAttackState(float deltaTime){
		state_timer += deltaTime;
		if(state_timer > FLYING_SNAKE_ATTACK_TIMER_BOUND){
			changeToReloadState();
		}
	}
	
	private void updateReloadState(float deltaTime){
		state_timer += deltaTime;
		if(state_timer > FLYING_SNAKE_ATTACK_TIMER_BOUND){
			changeToStandardState();
		}
	}
}

package com.slimejumper.world.enemies;

import com.slimejumper.Assets;
import com.slimejumper.gameframework.gl.Animation;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.attacks.Shockball;


public class JellyfishDemon extends Enemy{
		
	public static final float JELLY_STANDARD_STATE_TIMER_BOUND = Assets.JELLYFISH_DEMON_MOTION_FRAME_DURATION * Assets.JELLYFISH_DEMON_MOTION_FRAME_COUNT * 3;	
	public static final float JELLY_ATTACK_STATE_TIMER_BOUND = Assets.JELLYFISH_DEMON_MOTION_FRAME_DURATION * Assets.JELLYFISH_DEMON_MOTION_FRAME_COUNT;
	public static final float JELLY_COLLIDED_STATE_TIMER_BOUND = 0.1f;
	
	
	public static final int JELLY_STATE_STANDARD = 0;
	public static final int JELLY_STATE_ATTACK = 1;
	public static final int JELLY_STATE_COLLIDED = 2;
	public static final int JELLY_STATE_BOOST_UP = 3;
	public static final int JELLY_STATE_FLOAT_DOWN = 4;
	
	
	public static final float JELLY_STANDARD_WIDTH = 0.8f;
	public static final float JELLY_STANDARD_HEIGHT = 1.0375f;
	public static final float JELLY_ATTACK_WIDTH = 0.875f;
	public static final float JELLY_ATTACK_HEIGHT = 1.0875f;
	public static final float JELLY_COLLISION_WIDTH = 0.875f;
	public static final float JELLY_COLLISION_HEIGHT = 1.0875f;
	
	public static final float JELLY_LIFESPAN = 24.0f;
	
	public static final float JELLY_VERTICAL_VEL = 1.3f;
	public static final float JELLY_HORIZONTAL_VEL = 2.6f;
	
	float state_timer;
	public int state;
	int frame_number;
	
	public JellyfishDemon(){
		this(4, 4);
		velocity.set(0, 0);
	}
	
	public JellyfishDemon(float x, float y){
		super(x, y, JELLY_STANDARD_WIDTH, JELLY_STANDARD_HEIGHT);
		initializeParameters();
		velocity.set(JELLY_HORIZONTAL_VEL, JELLY_VERTICAL_VEL);		
	}
	
	public void reset(float spawnPositionX){
		
		super.reset();
		resetPositionLowerLeft(spawnPositionX, -JELLY_STANDARD_HEIGHT);
		changeToStandardState();
		initializeParameters();
	}
	
	public void initializeParameters(){
		state_timer = 0;
/*		
		frame_timer = 0;
		frame_counter = 0;
*/		
		state = JELLY_STATE_STANDARD;
		frame_number = 0;
	}
	
	public void changeToStandardState(){
		resetDimensions(JELLY_STANDARD_WIDTH, JELLY_STANDARD_HEIGHT);
		state_timer = 0;
		state = JELLY_STATE_STANDARD;
		velocity.set(JELLY_HORIZONTAL_VEL, JELLY_VERTICAL_VEL);
		facedirection = SPRITE_RIGHT;
	}
	
	public void changeToAttackState(){
		resetDimensions(JELLY_ATTACK_WIDTH, JELLY_ATTACK_HEIGHT);
		state_timer = 0;
		state = JELLY_STATE_ATTACK;
		velocity.set(0, 0);
		Shockball.activateDualShot(this);
	}
	
	public void changeToCollidedState(HaloAttack halo_attack){
		resetDimensions(JELLY_COLLISION_WIDTH, JELLY_COLLISION_HEIGHT);
		facedirection = oppositeFaceDirection(halo_attack.facedirection);
		state_timer = 0;
		state = JELLY_STATE_COLLIDED;
		velocity.set(0, 0);
	}

	public void update(float deltaTime){

		super.update(deltaTime);
		
		switch(state){
		case JELLY_STATE_STANDARD:
			updateStandardState(deltaTime);
			break;
		case JELLY_STATE_ATTACK:
			updateAttackState(deltaTime);
			break;
		case JELLY_STATE_COLLIDED:
			updateCollidedState(deltaTime);
			break;
		}
	}


	private void updateStandardState(float deltaTime) {
		state_timer += deltaTime;
		int new_frame_number = Assets.jellyfish_demon_motion.getFrameNumber(life_timer, 
				Animation.ANIMATION_LOOPING);
		
		if(new_frame_number != frame_number){
			frame_number = new_frame_number;
			if(frame_number == Assets.JELLYFISH_DEMON_MOTION_FRAME_COUNT_MINUS_ONE)
				reversal();
		}
		
		if(frame_number >= 1 && frame_number <= 7)
			canMove = true;
		else
			canMove = false;		
		
		
		if(frame_number == 11 && state_timer > JELLY_STANDARD_STATE_TIMER_BOUND){
			changeToAttackState();
		}
	}

	private void reversal() {
		reverseHorizontalVel();
		changeFaceDirection();
	}

	private void reverseHorizontalVel() {
		velocity.x = -velocity.x;		
	}
	
	private void changeFaceDirection() {
		if(facedirection == SPRITE_RIGHT)
			facedirection = SPRITE_LEFT;
		else if(facedirection == SPRITE_LEFT)
			facedirection = SPRITE_RIGHT;
		
		
	}

	private void updateAttackState(float deltaTime){
		state_timer += deltaTime;
		if(state_timer > JELLY_ATTACK_STATE_TIMER_BOUND){
			changeToStandardState();
		}
	}
	
	private void updateCollidedState(float deltaTime){
		state_timer += deltaTime;
		if(state_timer > JELLY_COLLIDED_STATE_TIMER_BOUND){
			changeToStandardState();
		}
	}
/*
	private void adjustVectors() {
		
		vertical_relative_vel = (float) Math.sin(4*life_timer);
		velocity.y = JellyfishDemon_VERTICAL_ABSOLUTE_VEL + vertical_relative_vel;
		
		if(switch_motion_timer > 3.2f){
			velocity.x = -velocity.x;
			switch_motion_timer = 0;
		}
				
	}
*/
}

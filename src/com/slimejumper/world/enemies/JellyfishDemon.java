package com.slimejumper.world.enemies;

import com.slimejumper.Assets;
import com.slimejumper.tools.PoolManager;
import com.slimejumper.world.attacks.HaloAttack;
import com.slimejumper.world.attacks.Shockball;


public class JellyfishDemon extends Enemy{
		
	public static final float JELLY_STANDARD_STATE_TIMER_BOUND = Assets.JELLYFISH_DEMON_MOTION_FRAME_DURATION * Assets.JELLYFISH_DEMON_MOTION_FRAME_COUNT * 3;	
	public static final float JELLY_ATTACK_STATE_TIMER_BOUND = 1.3f;
	public static final float JELLY_COLLIDED_STATE_TIMER_BOUND = 0.7f;
	public static final float JELLY_BOOST_UP_STATE_TIMER_BOUND = Assets.JELLYFISH_DEMON_MOTION_FRAME_DURATION * 7.5f;
	public static final float JELLY_FLOAT_DOWN_STATE_TIMER_BOUND = Assets.JELLYFISH_DEMON_MOTION_FRAME_DURATION * 6;
	
	
//	public static final int JELLY_STATE_STANDARD = 0;
	public static final int JELLY_STATE_ATTACK = 1;
	public static final int JELLY_STATE_COLLIDED = 2;
	public static final int JELLY_STATE_BOOST_UP = 3;
	public static final int JELLY_STATE_FLOAT_DOWN = 4;
	
	
	public static final float JELLY_STANDARD_WIDTH = 0.8f;
	public static final float JELLY_STANDARD_HEIGHT = 1.0375f;
	public static final float JELLY_ATTACK_WIDTH = 0.875f;
	public static final float JELLY_ATTACK_HEIGHT = 1.0875f;
	public static final float JELLY_COLLISION_WIDTH = 0.8f;
	public static final float JELLY_COLLISION_HEIGHT = 0.875f;
	
	public static final float JELLY_LIFESPAN = 50.0f;
	
	public static final float JELLY_BOOST_UP_VERTICAL_VEL = 1.3f;
	public float JELLY_BOOST_UP_HORIZONTAL_VEL = 1.5f;
	public static final float JELLY_FLOAT_DOWN_VERTICAL_VEL = -0.6f;
	
	public Shockball shockball_Left;
	public Shockball shockball_Right;
	
	public float state_timer;
	public int state;
	int frame_number;
	int number_of_cycles;
	
	public JellyfishDemon(){
		this(4, 4);
		velocity.set(0, 0);
	}
	
	public JellyfishDemon(float x, float y){
		super(x, y, JELLY_STANDARD_WIDTH, JELLY_STANDARD_HEIGHT);
		initializeParameters();
		velocity.set(JELLY_BOOST_UP_HORIZONTAL_VEL, JELLY_BOOST_UP_VERTICAL_VEL);		
	}
	
	public void reset(float spawnPositionX){
		
		super.reset();
		resetPositionLowerLeft(spawnPositionX, -JELLY_STANDARD_HEIGHT);
		startFloatDownState();
		initializeParameters();
	}
	
	public void initializeParameters(){
		state_timer = 0;
		number_of_cycles = 0;
		frame_number = 0;
		facedirection = SPRITE_RIGHT;
	}
	
	public void update(float deltaTime){
	
			super.update(deltaTime);
			// Update Shockball
			updateAttackShockballs(deltaTime);
			
			state_timer += deltaTime;
			switch(state){
	/*		
			case JELLY_STATE_STANDARD:
				updateStandardState(deltaTime);
				break;
	*/
			case JELLY_STATE_ATTACK:
				updateAttackState(deltaTime);
				break;
			case JELLY_STATE_COLLIDED:
				updateCollidedState(deltaTime);
				break;
			case JELLY_STATE_BOOST_UP:
				updateBoostUpState(deltaTime);
				break;
			case JELLY_STATE_FLOAT_DOWN:
				updateFloatDownState(deltaTime);
				break;
			}
		}

	private void updateAttackShockballs(float deltaTime) {
		if(shockball_Left != null){
			shockball_Left.update(deltaTime);
			if(shockball_Left.life_timer > Shockball.SHOCKBALL_LIFESPAN){
				PoolManager.pool_manager_singleton.shockball_pool.free(shockball_Left);
				shockball_Left = null;
			}
		}
		if(shockball_Right != null){
			shockball_Right.update(deltaTime);
			if(shockball_Right.life_timer > Shockball.SHOCKBALL_LIFESPAN){
				PoolManager.pool_manager_singleton.shockball_pool.free(shockball_Right);
				shockball_Right = null;
			}
		}
	}

	/*	
	public void changeToStandardState(){
		resetDimensions(JELLY_STANDARD_WIDTH, JELLY_STANDARD_HEIGHT);
		state_timer = 0;
		state = JELLY_STATE_STANDARD;
		velocity.set(JELLY_HORIZONTAL_VEL, JELLY_VERTICAL_VEL);
	}
*/	
	public void changeToAttackState(){
		resetDimensions(JELLY_ATTACK_WIDTH, JELLY_ATTACK_HEIGHT);
		state_timer = 0;
		state = JELLY_STATE_ATTACK;
		velocity.set(0, 0);
		activateDualShot();
	}
	
	public void activateDualShot(){			
		shockball_Left = PoolManager.pool_manager_singleton.shockball_pool.newObject();
		shockball_Left.reset(this, Shockball.SHOCKBALL_LAUNCH_VELOCITY_LEFT);
		
		shockball_Right = PoolManager.pool_manager_singleton.shockball_pool.newObject();
		shockball_Right.reset(this, Shockball.SHOCKBALL_LAUNCH_VELOCITY_RIGHT);
	}

	public void changeToCollidedState(HaloAttack halo_attack){
		resetDimensions(JELLY_COLLISION_WIDTH, JELLY_COLLISION_HEIGHT);
		facedirection = oppositeFaceDirection(halo_attack.facedirection);
		state_timer = 0;
		state = JELLY_STATE_COLLIDED;
		velocity.set(0, 0);
	}
	
/*	
	public void startBoostUpState(){		
		resetDimensions(JELLY_STANDARD_WIDTH, JELLY_STANDARD_HEIGHT);
		number_of_cycles = 0;
		changeToBoostUpState();
	}
*/
	public void startFloatDownState(){
		resetDimensions(JELLY_STANDARD_WIDTH, JELLY_STANDARD_HEIGHT);
		number_of_cycles = 0;
		changeToFloatDownState();
	}
	
	private void changeToBoostUpState(){
		
		state_timer = 0;
		state = JELLY_STATE_BOOST_UP;
		
		facedirection = -facedirection;
		JELLY_BOOST_UP_HORIZONTAL_VEL = -JELLY_BOOST_UP_HORIZONTAL_VEL;
		velocity.set(JELLY_BOOST_UP_HORIZONTAL_VEL, JELLY_BOOST_UP_VERTICAL_VEL);		
	}

	private void changeToFloatDownState(){		
		state_timer = 0;
		number_of_cycles++;
		state = JELLY_STATE_FLOAT_DOWN;
		
		velocity.set(0, JELLY_FLOAT_DOWN_VERTICAL_VEL);
	}
	
	private void updateBoostUpState(float deltaTime){
		if(state_timer > JELLY_BOOST_UP_STATE_TIMER_BOUND){
			if(number_of_cycles >= 3)
				changeToAttackState();	
			else
				changeToFloatDownState();
		}
	}
	
	private void updateFloatDownState(float deltaTime){
		if(state_timer > JELLY_FLOAT_DOWN_STATE_TIMER_BOUND){
			changeToBoostUpState();
		}
	}	
	
	/*
	private void updateStandardState(float deltaTime) {=
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
	 */
	private void updateAttackState(float deltaTime){
		if(state_timer > JELLY_ATTACK_STATE_TIMER_BOUND){
			startFloatDownState();
		}
	}

	private void updateCollidedState(float deltaTime){
		if(state_timer > JELLY_COLLIDED_STATE_TIMER_BOUND){
			startFloatDownState();
		}
	}
	
	public void dispose(){
		if(shockball_Left != null){
			PoolManager.pool_manager_singleton.shockball_pool.free(shockball_Left);
			shockball_Left = null;
		}
		
		if(shockball_Right != null){
			PoolManager.pool_manager_singleton.shockball_pool.free(shockball_Right);
			shockball_Right = null;
		}
	}
/*
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
*/
}

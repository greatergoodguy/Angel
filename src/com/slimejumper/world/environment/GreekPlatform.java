package com.slimejumper.world.environment;

import java.util.LinkedList;

import com.slimejumper.levels.Level;
import com.slimejumper.tools.PoolManager;

public class GreekPlatform extends Platform{
	
	/* 
	 * Platform Lists need to abide by certain rules
	 * static_platforms and ground platforms
	 */
	
	public static final float PLATFORM_OSCILLATE_TIMER_BOUND = 0.22f;
	public static final float PLATFORM_REST_TIMER_BOUND = 1.8f;
	
	public static final float PLATFORM_UNIT_WIDTH = 0.5f;
	public static final float PLATFORM_HEIGHT = 0.25f;
	public static final float PLATFORM_LIFESPAN = 8.0f;
	
	public static final float PLATFORM_LAUNCH_VEL = -3.0f;
	public static final float PLATFORM_OSCILLATE_LAUNCH_VEL = 8.0f;
	
	public static final int PLATFORM_MEDIUM_LENGTH = 4;

	public static final int PLATFORM_STATE_STILL = 0;
	public static final int PLATFORM_STATE_ZOOM = 1;
	public static final int PLATFORM_STATE_OSCILLATE_LEFT = 2;
	public static final int PLATFORM_STATE_OSCILLATE_RIGHT = 3;
	public static final int PLATFORM_STATE_OSCILLATE_UP = 4;
	public static final int PLATFORM_STATE_OSCILLATE_DOWN = 5;
	public static final int PLATFORM_STATE_REST = 6;
	
	public int state;
	public float state_timer;
	
	public int length;
	public int middle_length;
	
	public int next_state;
	
	public GreekPlatform(){
		this(0, 0, 0, 4);
	}
	
	// length must be at least 2
	public GreekPlatform(float x, float y, int length) {
		
		
		super(x, y, length*PLATFORM_UNIT_WIDTH, PLATFORM_HEIGHT);
		state = PLATFORM_STATE_STILL;
		state_timer = 0;
		next_state = 0;
		this.length = length;
		middle_length = length-2;
	}
	
	public GreekPlatform(float x, float y, float velX, int length) {
		
		
		super(x, y, length*PLATFORM_UNIT_WIDTH, PLATFORM_HEIGHT);
		state = PLATFORM_STATE_ZOOM;
		state_timer = 0;
		this.length = length;
		middle_length = length-2;
		
		velocity.set(velX, 0);
	}
/*
	public void reset(float spawnPositionY, int length){
		super.reset();
		
		position.set(World.WORLD_RIGHT_EDGE, spawnPositionY);
		velocity.set(PLATFORM_LAUNCH_VEL, 0);
		
		width = length * PLATFORM_UNIT_WIDTH;
		height = PLATFORM_HEIGHT;
		
		type = PLATFORM_TYPE_MOTION;
		
		this.length = length;
		middle_length = length - 2;
		
		bounds.createNewRectangle(position.x, position.y, width, height);
	}
*/	
	public void reset(float spawnPositionX, float spawnPositionY, int length, int platform_state){
		super.reset();
		
		switch(platform_state){
		case PLATFORM_STATE_STILL:
			changeToStillState();
			break;
		case PLATFORM_STATE_ZOOM:
			changeToZoomState();
			break;
		case PLATFORM_STATE_OSCILLATE_LEFT:
			changeToOscillateLeftState();
			break;
		case PLATFORM_STATE_OSCILLATE_RIGHT:
			changeToOscillateRightState();
			break;
		case PLATFORM_STATE_OSCILLATE_UP:
			changeToOscillateUpState();
			break;
		case PLATFORM_STATE_OSCILLATE_DOWN:
			changeToOscillateDownState();
			break;
		}
		
		width = length * PLATFORM_UNIT_WIDTH;
		height = PLATFORM_HEIGHT;
		
		this.length = length;
		middle_length = length - 2;
		
		resetPositionLowerLeft(spawnPositionX, spawnPositionY);
		resizeBounds(center.x, center.y, width, height);
	}
	
	public void update(float deltaTime){
		super.update(deltaTime);
		
		switch(state){
		case PLATFORM_STATE_OSCILLATE_LEFT:
			updateOscillateLeftState(deltaTime);
			break;
		case PLATFORM_STATE_OSCILLATE_RIGHT:
			updateOscillateRightState(deltaTime);
			break;
		case PLATFORM_STATE_OSCILLATE_UP:
			updateOscillateUpState(deltaTime);
			break;
		case PLATFORM_STATE_OSCILLATE_DOWN:
			updateOscillateDownState(deltaTime);
			break;
		case PLATFORM_STATE_REST:
			updateRestState(deltaTime);
			break;
		}
	}
	
	public void changeToZoomState(){
		state = PLATFORM_STATE_ZOOM;
		state_timer = 0;

		velocity.set(PLATFORM_LAUNCH_VEL, 0);
	}
	
	public void changeToStillState(){
		state = PLATFORM_STATE_STILL;
		state_timer = 0;
	}

	public void changeToOscillateLeftState(){
		canMove = true;
		state = PLATFORM_STATE_OSCILLATE_LEFT;
		state_timer = 0;
		
		velocity.set(-PLATFORM_OSCILLATE_LAUNCH_VEL, 0);
	}
	
	public void changeToOscillateRightState(){
		canMove = true;
		state = PLATFORM_STATE_OSCILLATE_RIGHT;
		state_timer = 0;

		velocity.set(PLATFORM_OSCILLATE_LAUNCH_VEL, 0);
	}
	
	public void changeToOscillateUpState(){
		canMove = true;
		state = PLATFORM_STATE_OSCILLATE_UP;
		state_timer = 0;
		
		velocity.set(0, PLATFORM_OSCILLATE_LAUNCH_VEL);
	}
	
	public void changeToOscillateDownState(){
		canMove = true;
		state = PLATFORM_STATE_OSCILLATE_DOWN;
		state_timer = 0;

		velocity.set(0, -PLATFORM_OSCILLATE_LAUNCH_VEL);
	}
	
	private void changeToRestState(int next_state){
		canMove = false;
		state = PLATFORM_STATE_REST;
		state_timer = 0;
		
		this.next_state = next_state;
	}
	
	private void updateOscillateLeftState(float deltaTime){
		state_timer += deltaTime;
		
		if(state_timer > PLATFORM_OSCILLATE_TIMER_BOUND)
			changeToRestState(PLATFORM_STATE_OSCILLATE_RIGHT);
	}

	private void updateOscillateRightState(float deltaTime){
		state_timer += deltaTime;
		
		if(state_timer > PLATFORM_OSCILLATE_TIMER_BOUND)
			changeToRestState(PLATFORM_STATE_OSCILLATE_LEFT);
	}	
	
	private void updateOscillateDownState(float deltaTime){
		state_timer += deltaTime;
		
		if(state_timer > PLATFORM_OSCILLATE_TIMER_BOUND)
			changeToRestState(PLATFORM_STATE_OSCILLATE_UP);
	}
	
	private void updateOscillateUpState(float deltaTime){
		state_timer += deltaTime;
		
		if(state_timer > PLATFORM_OSCILLATE_TIMER_BOUND)
			changeToRestState(PLATFORM_STATE_OSCILLATE_DOWN);
	}
	
	private void updateRestState(float deltaTime){
		state_timer += deltaTime;
		
		if(state_timer > PLATFORM_REST_TIMER_BOUND){
			switch(next_state){
			case PLATFORM_STATE_OSCILLATE_LEFT:
				changeToOscillateLeftState();
				break;
			case PLATFORM_STATE_OSCILLATE_RIGHT:
				changeToOscillateRightState();
				break;
			case PLATFORM_STATE_OSCILLATE_UP:
				changeToOscillateUpState();
				break;
			case PLATFORM_STATE_OSCILLATE_DOWN:
				changeToOscillateDownState();
				break;
			}
		}
	}

	public static void initializePlatformGround(Level level, LinkedList<GreekPlatform> greek_platforms) {
		final int ground_platform_length = 5;
		float x_coord = 0;
		float y_coord = 0.75f;
		
		while(x_coord < level.world_width){
			// create new Platform and add to list
			GreekPlatform platform = PoolManager.pool_manager_singleton.greek_platform_pool.newObject();
			platform.reset(x_coord, y_coord, ground_platform_length, PLATFORM_STATE_STILL);;
			greek_platforms.add(platform);
			
			// Advance counter and repeat
			x_coord += 5 * GreekPlatform.PLATFORM_UNIT_WIDTH;
		}
		
	}

	public static void initializePlatformMap(Level level, LinkedList<GreekPlatform> greek_platforms) {
		// Initial Parameters
		final int map_platform_length = 3;
		
		float x_coord = 4;
		float y_coord = 2.5f;
		
		while(y_coord < 8.0f){
			while(x_coord < level.world_width){
				GreekPlatform platform = PoolManager.pool_manager_singleton.greek_platform_pool.newObject();
				platform.reset(x_coord, y_coord, map_platform_length, PLATFORM_STATE_STILL);
				greek_platforms.add(platform);
			
				x_coord += 8;
			}
			if(x_coord % 8 == 0)
				x_coord = 4;
			else
				x_coord = 0;
			
			y_coord += 2.5;
		}
		
	}	
}

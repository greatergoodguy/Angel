package com.slimejumper.world.enemies;

import com.slimejumper.levels.Level;

public class PurpleGhost extends Enemy{
	
	public static final float PurpleGhost_WIDTH = 0.8f;
	public static final float PurpleGhost_HEIGHT = 0.8f;
	public static final float PurpleGhost_LIFESPAN = 14.0f;
	
	public static final float PurpleGhost_VERTICAL_VEL = 1.0f;
	public static final float PurpleGhost_HORIZONTAL_VEL = 1.9f;
	
	public static final int PurpleGhost_TYPE1 = 1;
	
	float switch_motion_timer;
	
	public PurpleGhost(){
		this(0, 0);
	}
	
	public PurpleGhost(float x, float y){
		super(x, y, PurpleGhost_WIDTH, PurpleGhost_HEIGHT);
		switch_motion_timer = 0;
		velocity.set(PurpleGhost_HORIZONTAL_VEL, PurpleGhost_VERTICAL_VEL);	
	}
	
	public PurpleGhost(int purpleghost_type){
		super(0, 0, PurpleGhost_WIDTH, PurpleGhost_HEIGHT);
		switch_motion_timer = 0;
		
		if(purpleghost_type == PurpleGhost_TYPE1){
			resetPositionLowerLeft(Level.WORLD_DEFAULT_WIDTH, 1.0f);
			velocity.set(PurpleGhost_HORIZONTAL_VEL, PurpleGhost_VERTICAL_VEL);
		}
		
		
	}
	
	public void reset(){
		super.reset();
	}
	
	public void reset(float spawnPositionY){
		
		this.reset();
		resetPositionLowerLeft(Level.WORLD_DEFAULT_WIDTH, spawnPositionY);
		velocity.set(PurpleGhost_HORIZONTAL_VEL, PurpleGhost_VERTICAL_VEL);
		switch_motion_timer = 0;
	}
	
	public void update(float deltaTime){

		super.update(deltaTime);
		updateTimers(deltaTime);
		adjustVectors();
	}

	private void updateTimers(float deltaTime) {
		switch_motion_timer += deltaTime;
	}

	private void adjustVectors() {
		velocity.y = (float) (PurpleGhost_VERTICAL_VEL * Math.sin(life_timer));
			
	}
	
}

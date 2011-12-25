package com.slimejumper.world.enemies;

import com.slimejumper.levels.Level;

public class PurpleGhost extends Enemy{
	
	public static final float PurpleGhost_WIDTH = 0.8f;
	public static final float PurpleGhost_HEIGHT = 0.8f;
	public static final float PurpleGhost_LIFESPAN = 14.0f;
	
	public PurpleGhost(){
		this(0, 0);
	}
	
	public PurpleGhost(float x, float y){
		super(x, y, PurpleGhost_WIDTH, PurpleGhost_HEIGHT);
	}
	
	public void reset(float x_coord, float y_coord){
		super.reset();
		resetPositionLowerLeft(x_coord, y_coord);
	}
	
	public void update(float deltaTime){

		super.update(deltaTime);
	}
	
}

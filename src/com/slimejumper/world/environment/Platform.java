package com.slimejumper.world.environment;

import com.slimejumper.world.DynamicGameObject;

public class Platform extends DynamicGameObject{
	public Platform(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	public void update(float deltaTime){
		super.update(deltaTime);
	}
	
	public void reset(){
		super.reset();
	}
}

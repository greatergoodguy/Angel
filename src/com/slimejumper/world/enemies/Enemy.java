package com.slimejumper.world.enemies;


import com.slimejumper.world.DynamicGameObject;

public class Enemy extends DynamicGameObject{
	
	public int health_bar;
	
	public Enemy(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	public void update(float deltaTime){
		super.update(deltaTime);
	}
	
	public void reset(){
		super.reset();
	}
}

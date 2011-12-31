package com.slimejumper.world.attacks;


import com.slimejumper.world.DynamicGameObject;
import com.slimejumper.world.GameObject;

public class Attack extends DynamicGameObject {
	
	public Attack(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	public void update(float deltaTime){
		super.update(deltaTime);
	}
	
	public void reset(){
		super.reset();
	}
	
	public void reset(GameObject gameObject){
		super.reset();
		resetPositionCenter(gameObject.center);
	}
	
}

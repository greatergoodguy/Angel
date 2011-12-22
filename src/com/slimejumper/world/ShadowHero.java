package com.slimejumper.world;

public class ShadowHero extends Hero{

	public boolean motion_sync_on;
	
	public ShadowHero(){
		super();
		
		motion_sync_on = false;
	}
	
	public void update(float deltaTime){
		super.update(deltaTime);
		
		if(life_timer > 5)
			motion_sync_on = true;
	}
}

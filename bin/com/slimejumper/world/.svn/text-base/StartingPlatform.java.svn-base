package com.slimejumper.world;

public class StartingPlatform extends GameObject{
	
	public static final float PLATFORM_WIDTH = 8.0f;
	public static final float PLATFORM_HEIGHT = 0.25f;
	public static final float REMOVE_TIME = 60.0f;  // 8.0f is the standard value
	
	boolean isPerceptible;
	float elapsedTime;

	public StartingPlatform(float x, float y) {
		super(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
		
		isPerceptible = true;
		elapsedTime = 0;
	}
	
	public void update(float deltaTime){
		elapsedTime += deltaTime;
		
		if(elapsedTime > REMOVE_TIME)
			isPerceptible = false;
	}

}

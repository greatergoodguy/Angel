package com.slimejumper.world.environment;

public class CloudPlatform extends Platform{
	public static final float CLOUD_PLATFORM_WIDTH = 3.5f;
	public static final float CLOUD_PLATFORM_HEIGHT = 0.5f;
	
	public CloudPlatform(){
		super(0, 0, CLOUD_PLATFORM_WIDTH, CLOUD_PLATFORM_HEIGHT);
	}
	
	public CloudPlatform(float x, float y){
		super(x, y, CLOUD_PLATFORM_WIDTH, CLOUD_PLATFORM_HEIGHT);
	}


	private void reset(float x_coord, float y_coord) {
		super.reset();
		resetPositionLowerLeft(x_coord, y_coord);
	}
}

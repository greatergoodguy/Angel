package com.slimejumper.world.environment;


public class CloudPlatformShort extends Platform{
	public static final float CLOUD_PLATFORM_WIDTH = 1.00f;	// Old Value is 1.25f
	public static final float CLOUD_PLATFORM_HEIGHT = 0.525f;
	
	public CloudPlatformShort(){
		super(0, 0, CLOUD_PLATFORM_WIDTH, CLOUD_PLATFORM_HEIGHT);
	}
	
	public CloudPlatformShort(float x, float y){
		super(x, y, CLOUD_PLATFORM_WIDTH, CLOUD_PLATFORM_HEIGHT);
	}


	public void reset(float x_coord, float y_coord) {
		super.reset();
		resetPositionLowerLeft(x_coord, y_coord);
	}
}

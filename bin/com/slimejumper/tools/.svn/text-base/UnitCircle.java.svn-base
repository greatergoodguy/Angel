package com.slimejumper.tools;

public class UnitCircle {
	
	public static final float TWO_PI = (float) Math.PI * 2;
	public static final float TWO_PI_OVER_TWENTY_FOUR = (float) (Math.PI / 12.0);
	
	public static final int UNIT_CIRCLE_SIZE = 24;
	
	public static float[] unit_circle_x;
	public static float[] unit_circle_y;
	
	public static void initializeUnitCircle(){
		unit_circle_x = new float[UNIT_CIRCLE_SIZE];
		unit_circle_y = new float[UNIT_CIRCLE_SIZE];
		
		float angle = 0;
		for(int i=0; i<UNIT_CIRCLE_SIZE; i++){
			unit_circle_x[i] =  (float) Math.sin(angle);
			unit_circle_y[i] =  (float) Math.cos(angle);
			angle += TWO_PI_OVER_TWENTY_FOUR;
		}
	}

}

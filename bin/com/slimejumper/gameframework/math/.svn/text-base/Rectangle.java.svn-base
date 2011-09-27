package com.slimejumper.gameframework.math;

public class Rectangle {
	public final Vector2 lowerLeft;
//	public final Vector2 center;
	public float width, height;
	
	public Rectangle(float x, float y, float width, float height){
		this.lowerLeft = new Vector2(x,y);
//		this.center = new Vector2(x + width/2, y + height/2);
		this.width = width;
		this.height = height;
	}
	
	public void createNewRectangle(float x, float y, float width, float height){
		lowerLeft.set(x - width/2, y - height/2);
//		center.set(x + width/2, y + height/2);
		this.width = width;
		this.height = height;
	}
	
}

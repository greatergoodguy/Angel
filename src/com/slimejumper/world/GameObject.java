package com.slimejumper.world;

import com.slimejumper.gameframework.math.Rectangle;
import com.slimejumper.gameframework.math.Vector2;

public class GameObject {
	public final Vector2 position;
	public final Vector2 center;
	private final Rectangle bounds;
	
	public float width;
	public float height;
	
	public static final int SPRITE_LEFT = 1;
	public static final int SPRITE_NEUTRAL = 0;
	public static final int SPRITE_RIGHT = -1;
	
	public int facedirection;
	public boolean canMove;
	
	public GameObject(){
		this.position = new Vector2(0,0);
		this.center = new Vector2(0,0);
		this.bounds = new Rectangle(0, 0, 0, 0);
		
		this.width = 0;
		this.height = 0;
		
		this.facedirection = SPRITE_NEUTRAL;
		this.canMove = true;
	}
	
	public GameObject(float x, float y, float width, float height){
		this.position = new Vector2(x,y);
		this.center = new Vector2(position.x + width/2, position.y + height);
		
		this.bounds = new Rectangle(position.x, position.y, width, height);
		
		this.width = width;
		this.height = height;

		this.facedirection = SPRITE_NEUTRAL;
		this.canMove = true;
	}
	
	public void update(float deltaTime) {
		
	}
	
	protected int oppositeFaceDirection(int facedirection) {
		if(facedirection == SPRITE_LEFT)
			return SPRITE_RIGHT;
		else if(facedirection == SPRITE_RIGHT)
			return SPRITE_LEFT;
		else
			return SPRITE_NEUTRAL;
	}
	
	protected void resetDimensions(float new_width, float new_height){
		this.width = new_width;
		this.height = new_height;
		
		bounds.createNewRectangle(center.x, center.y, new_width, new_height);
		position.set(center.x - new_width/2, center.y - new_height);
	}

	
	/*
	 * Resets the center and position to a new location.
	 * Bound is also repositioned.
	 */

	public void resetPositionLowerLeft(Vector2 new_lower_left){
		position.set(new_lower_left.x, new_lower_left.y);
		center.set(position.x + width/2, position.y + height/2);
		bounds.lowerLeft.set(position);
	}
	
	
	public void resetPositionLowerLeft(float lower_left_x, float lower_left_y){
		position.set(lower_left_x, lower_left_y);
		center.set(position.x + width/2, position.y + height/2);
		bounds.lowerLeft.set(position);
	}
	
	/*
	 * Resets the position and center to a new location.
	 * Bound is also repositioned.
	 */
	public void resetPositionCenter(Vector2 new_center) {
		center.set(new_center.x, new_center.y);
		position.set(center.x - width/2, center.y - height/2);
		bounds.lowerLeft.set(position);
	}
	
	public void resetPositionCenter(float center_x, float center_y){
		center.set(center_x, center_y);
		position.set(center.x - width/2, center.y - height/2);
		bounds.lowerLeft.set(position);
	}
	
	protected void setBounds(Vector2 lower_left_postition){
		bounds.lowerLeft.set(lower_left_postition);
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	protected void resizeBounds(float center_x, float center_y, float width, float height){
		bounds.createNewRectangle(center_x, center_y, width, height);
	}
}

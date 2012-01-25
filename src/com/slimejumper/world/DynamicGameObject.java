package com.slimejumper.world;

import com.slimejumper.gameframework.math.Vector2;

public class DynamicGameObject extends GameObject{

	public final Vector2 velocity;
	public final Vector2 accel;
	
	public float life_timer;
	
	public boolean is_invincible;
	
	public DynamicGameObject(){
		super();
		velocity = new Vector2(0,0);
		accel = new Vector2(0,0);
		life_timer = 0;
		
		is_invincible = false;
	}
	
	public DynamicGameObject(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		
		velocity = new Vector2();
		accel = new Vector2();
		
		life_timer = 0;
	}

	public void update(float deltaTime) {		
		if(canMove){
			velocity.add(accel.x * deltaTime, accel.y * deltaTime);
/*			
			position.add(velocity.x * deltaTime, velocity.y * deltaTime);
			center.set(position.x + width/2, position.y + height/2);
*/
			center.add(velocity.x * deltaTime, velocity.y * deltaTime);
			position.set(center.x - width/2, center.y - height/2);
			setBounds(position);
		}
		
		life_timer += deltaTime;		
	}
	
	public void reset(){
		life_timer = 0;
		is_invincible = false;
		position.set(0, 0);
		center.set(position.x + width/2, position.y + height/2);
		
		velocity.set(0, 0);
		accel.set(0, 0);
	}
}

package com.slimejumper.world.attacks;

import com.slimejumper.Assets;
import com.slimejumper.world.Hero;

public class AngelicFlame extends Attack{
	public static final float ANGELIC_FLAME_WIDTH = 0.65f;
	public static final float ANGELIC_FLAME_HEIGHT = 0.325f;
	
	public static final float ANGELIC_FLAME_LIFESPAN = 1.8f;

	public static final float ANGELIC_FLAME_LAUNCH_VELOCITY = -6.0f;
	
	public static final float ANGELIC_FLAME_HORIZONTAL_OFFSET = 0.4f;
	public static final float ANGELIC_FLAME_VERTICAL_OFFSET = 0.4f;
	
	public static final float ANGELIC_FLAME_CHARGE_TIMER_LIMIT = Assets.DEFAULT_FRAME_DURATION * 2;
	
	public static enum STATE{
		STATE_CHARGE,
		STATE_BLAST
	}
	
	public STATE state;
	public float state_timer;
	
	public AngelicFlame(){
		this(3, 3);
	}
	
	public AngelicFlame(float x, float y){
		super(x, y, ANGELIC_FLAME_WIDTH, ANGELIC_FLAME_HEIGHT);
	}
	
	public void update(float deltaTime){
		super.update(deltaTime);
		
		state_timer += deltaTime;
		switch(state){
		case STATE_CHARGE:
			updateChargeState();
			break;
				
		}
	}
	
	public void reset(Hero hero) {
		super.reset();
		resetPositionCenter(hero.center);
		facedirection = hero.facedirection;
		velocity.x = ANGELIC_FLAME_LAUNCH_VELOCITY * facedirection;
		
		changeToChargeState();
	}
	
	private void changeToChargeState(){
		state = STATE.STATE_CHARGE;
		state_timer = 0;
	}
	
	private void updateChargeState(){
		if(state_timer > ANGELIC_FLAME_CHARGE_TIMER_LIMIT)
			changeToBlastState();
	}
	
	private void changeToBlastState(){
		state = STATE.STATE_BLAST;
		state_timer = 0;
	}
	
	/*
	private void updateBlastState(){	
	}
	*/
}

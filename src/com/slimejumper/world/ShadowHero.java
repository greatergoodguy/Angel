package com.slimejumper.world;


public class ShadowHero extends Hero{
	
	public final static ShadowHero shadow_hero_singleton = new ShadowHero(Hero.hero_singleton);
	
	public Hero hero;	// A reference to the hero that the shadow hero is shadowing
	
	public boolean controller_sync_on;
	public float swap_timer;
	
	public ShadowHero(){
		super();
		
		controller_sync_on = false;
		swap_timer = 0;
		
		hero = null;
	}
	
	public ShadowHero(Hero hero){
		super();
		
		controller_sync_on = false;
		this.hero = hero;
	}
	
	public void update(float deltaTime){
		super.update(deltaTime);
		
	//	updateSwapTimer(deltaTime);
		
		if(life_timer > 5)
			controllerSyncActivate();
	}
	
	private void updateSwapTimer(float deltaTime) {
		swap_timer += deltaTime;
		
		if(swap_timer > 5.0f){
			swap_timer = 0;
			positionSwap();
		}
		
	}

	public void positionSwap(){
		if(hero == null)
			return;
		
		float shadow_hero_new_position_x = hero.position.x;
		float shadow_hero_new_position_y = hero.position.y;
		
		hero.resetPositionLowerLeft(this.position);
		this.resetPositionLowerLeft(shadow_hero_new_position_x, shadow_hero_new_position_y);
	}
	
	public void controllerSyncActivate(){
		controller_sync_on = true;
	}
	
	public void controllerSyncDeactivate(){
		controller_sync_on = false;
	}
}

package com.slimejumper.world.attacks;

import java.util.LinkedList;

import com.slimejumper.world.DynamicGameObject;

public class Attack extends DynamicGameObject {
	

	public static LinkedList<HaloAttack> halo_attacks;
	public static LinkedList<Shockball> shockballs;
	public static LinkedList<MusicNote> music_notes;
	public static LinkedList<SpiralAttack> spiral_attacks;
	
	public Attack(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	public void update(float deltaTime){
		super.update(deltaTime);
	}
	
	public void reset(){
		super.reset();
	}
	
}

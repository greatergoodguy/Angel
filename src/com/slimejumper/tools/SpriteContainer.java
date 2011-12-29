package com.slimejumper.tools;

import java.util.LinkedList;

import com.slimejumper.world.attacks.Shockball;

public class SpriteContainer {
	
	public static LinkedList<Shockball> shockballs;
	
	public static void initializeLists() {
		shockballs = new LinkedList<Shockball>();
	}
}

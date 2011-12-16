package com.slimejumper.tools;

public class SpriteManager {
	
	public final SpriteContainer sprite_container;
	public final Remover remover;
	public final CollisionManager collision_manager;
	public final SpriteFactory sprite_factory;
	
	public SpriteManager(){
		sprite_container = new SpriteContainer();
		remover = new Remover();
		collision_manager = new CollisionManager();
		sprite_factory = new SpriteFactory();
	}

}

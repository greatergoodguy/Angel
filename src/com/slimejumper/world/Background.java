package com.slimejumper.world;

import com.slimejumper.Assets;
import com.slimejumper.gameframework.math.Vector2;


public class Background{	
	
	public static int BACKGROUND_WIDTH = 15;
	public static int BACKGROUND_HEIGHT = 12;
	
	
	public Vector2 asset_position;
		
	GameWorld world;
	float parallax_ratio;
	
	public Background(GameWorld world){
		this.world = world;
		parallax_ratio = (BACKGROUND_WIDTH - WorldRenderer.FRUSTUM_WIDTH) / 
							(GameWorld.WORLD_WIDTH - WorldRenderer.FRUSTUM_WIDTH);
		
		asset_position = new Vector2();
	}


	public void update() {
		asset_position.x = world.position.x * parallax_ratio;
		asset_position.y = WorldRenderer.FRUSTUM_HEIGHT - world.position.y;
		Assets.backgroundRegion.adjust(asset_position);
	}

}

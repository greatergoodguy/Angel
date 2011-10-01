package com.slimejumper.world;

import com.slimejumper.Assets;
import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.tools.World;


public class Backgrounds{	
	
	public static int BACKGROUND_CLOUDS_WIDTH = 15;	
	public static int BACKGROUND_BACK_LAYER_WIDTH = 15;	
	public static int BACKGROUND_MIDDLE_LAYER_WIDTH = 15;	
	
	public static Vector2 new_position;
		
	public static World active_world = null;
	
	public static float background_clouds_parallax_ratio;
	public static float background_back_layer_parallax_ratio;
	public static float background_middle_layer_parallax_ratio;
	
	public Backgrounds(World new_active_world){
		active_world = new_active_world;
		background_clouds_parallax_ratio = (BACKGROUND_CLOUDS_WIDTH - WorldRenderer.FRUSTUM_WIDTH) / 
			(World.WORLD_WIDTH - WorldRenderer.FRUSTUM_WIDTH);
		background_back_layer_parallax_ratio = (BACKGROUND_BACK_LAYER_WIDTH - WorldRenderer.FRUSTUM_WIDTH) / 
			(World.WORLD_WIDTH - WorldRenderer.FRUSTUM_WIDTH);
		background_middle_layer_parallax_ratio = (BACKGROUND_MIDDLE_LAYER_WIDTH - WorldRenderer.FRUSTUM_WIDTH) / 
			(World.WORLD_WIDTH - WorldRenderer.FRUSTUM_WIDTH);
		
		new_position = new Vector2();
	}
	
	public static void initalizeParameters(){
		background_clouds_parallax_ratio = (BACKGROUND_CLOUDS_WIDTH - WorldRenderer.FRUSTUM_WIDTH) / 
			(World.WORLD_WIDTH - WorldRenderer.FRUSTUM_WIDTH);
		background_back_layer_parallax_ratio = (BACKGROUND_BACK_LAYER_WIDTH - WorldRenderer.FRUSTUM_WIDTH) / 
			(World.WORLD_WIDTH - WorldRenderer.FRUSTUM_WIDTH);
		background_middle_layer_parallax_ratio = (BACKGROUND_MIDDLE_LAYER_WIDTH - WorldRenderer.FRUSTUM_WIDTH) / 
			(World.WORLD_WIDTH - WorldRenderer.FRUSTUM_WIDTH);

		new_position = new Vector2();
	}
	


	public static void update() {
		new_position.x = active_world.position.x * background_clouds_parallax_ratio;
		new_position.y = WorldRenderer.FRUSTUM_HEIGHT - active_world.position.y;
		Assets.backgroundCloudsRegion.adjust(new_position);

		new_position.x = active_world.position.x * background_back_layer_parallax_ratio;
		new_position.y = WorldRenderer.FRUSTUM_HEIGHT - active_world.position.y;
		Assets.backgroundBackLayerRegion.adjust(new_position);

		new_position.x = active_world.position.x * background_middle_layer_parallax_ratio;
		new_position.y = WorldRenderer.FRUSTUM_HEIGHT - active_world.position.y;
		Assets.backgroundMiddleLayerRegion.adjust(new_position);
	}
	
	public static void setActiveWorld(World new_active_world){
		active_world = new_active_world;
	}

}
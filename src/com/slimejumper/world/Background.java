package com.slimejumper.world;

import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.renderer.BaseRenderer;

public class Background {
	public float background_width;
	public float background_loop_width;
	public float parallax_ratio;
	
	public Background(float background_width, float world_width, float world_height){
		this.background_width = background_width;
		this.background_loop_width = world_width;
		this.parallax_ratio = (background_width - BaseRenderer.BASE_RENDERER_FRUSTUM_WIDTH) / 
				(world_width - BaseRenderer.BASE_RENDERER_FRUSTUM_WIDTH);
	}
}

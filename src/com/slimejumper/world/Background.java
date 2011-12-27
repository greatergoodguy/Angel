package com.slimejumper.world;

import com.slimejumper.renderer.BaseRenderer;

public class Background {
	public float background_width;
	public float background_height;
	
	public float horizontal_parallax_ratio;
	public float vertical_parallax_ratio;
	
	public Background(float background_width, float background_height, float world_width, float world_height){
		this.background_width = background_width;
		this.background_height = background_height;
		
		this.horizontal_parallax_ratio = (background_width - BaseRenderer.BASE_RENDERER_FRUSTUM_WIDTH) / 
				(world_width - BaseRenderer.BASE_RENDERER_FRUSTUM_WIDTH);
		this.vertical_parallax_ratio = (background_height - BaseRenderer.BASE_RENDERER_FRUSTUM_HEIGHT) /
				(world_height - BaseRenderer.BASE_RENDERER_FRUSTUM_HEIGHT);
	}
}

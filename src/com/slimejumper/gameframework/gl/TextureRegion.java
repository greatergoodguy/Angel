package com.slimejumper.gameframework.gl;

import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.levels.Level;

public class TextureRegion {
	public float u1, v1;
	public float u2, v2;
	public final float width, height;
	public final Texture texture;
	
	public TextureRegion(Texture texture, float x, float y, float width, float height){
		this.u1 = x / texture.width;
		this.v1 = y / texture.height;
		this.u2 = this.u1 + width / texture.width;
		this.v2 = this.v1 + height / texture.height;
		this.texture = texture;
		
		this.width = width;
		this.height = height;
	}
	
	public void adjust(float x, float y){
		this.u1 = x / texture.width;
		this.v1 = y / texture.height;
		this.u2 = this.u1 + width / texture.width;
		this.v2 = this.v1 + height / texture.height;
	}


	/*
	 * This method takes a position and the parallax ratio to adjust
	 * to the correct TextureRegion area.
	 */


	public void adjust(Vector2 position) {
		//multiply 80 to convert from meters to pixels
		adjust(position.x*Level.METER, position.y*Level.METER);
	}

}

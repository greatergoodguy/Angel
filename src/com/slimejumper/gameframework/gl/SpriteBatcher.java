package com.slimejumper.gameframework.gl;

import javax.microedition.khronos.opengles.GL10;

import android.util.FloatMath;

import com.slimejumper.framework.impl.GLGraphics;
import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.levels.Level;
import com.slimejumper.world.GameObject;

public class SpriteBatcher {
	final float[] verticesBuffer;
	int bufferIndex;
	final Vertices vertices;
	int numSprites;
	
	public SpriteBatcher(GLGraphics glGraphics, int maxSprites){
		this.verticesBuffer = new float[maxSprites*4*4];
		this.vertices = new Vertices(glGraphics, maxSprites*4, maxSprites*6, false, true);
		this.bufferIndex = 0;
		this.numSprites = 0;
		
		short[] indices = new short[maxSprites*6];
		int len = indices.length;
		short j = 0;
		for(int i=0; i<len; i+=6, j+=4){
			indices[i+0] = (short)(j+0);
			indices[i+1] = (short)(j+1);
			indices[i+2] = (short)(j+2);
			indices[i+3] = (short)(j+2);
			indices[i+4] = (short)(j+3);
			indices[i+5] = (short)(j+0);
		}
		
		vertices.setIndices(indices, 0, indices.length);
	}
	
	public void beginBatch(Texture texture){
		texture.bind();
		numSprites = 0;
		bufferIndex = 0;
	}
	
	public void endBatch(){
		if(numSprites <= 0)
			return;
		
		vertices.setVertices(verticesBuffer, 0, bufferIndex);
		vertices.bind();
		vertices.draw(GL10.GL_TRIANGLES, 0, numSprites*6);
		vertices.unbind();
	}
/*	
	public void drawBackground(World world) {		
		drawSpriteLowerLeft(world.position.x, world.position.y, WorldRenderer.FRUSTUM_WIDTH, WorldRenderer.FRUSTUM_HEIGHT, Assets.backgroundCloudsRegion);
	}
*/
	
	
	public void drawSprite(GameObject sprite, TextureRegion region){
		drawSpriteCenter(sprite.center.x, sprite.center.y, sprite.width, sprite.height, region);
	}
	
	public void drawSpriteCenter(GameObject sprite, TextureRegion region, float angle){
		drawSpriteCenter(sprite.center.x, sprite.center.y, sprite.width, sprite.height, angle, region);
	}
	
	public void drawSpriteReverse(GameObject sprite, TextureRegion region){
		drawSpriteCenter(sprite.center.x, sprite.center.y, sprite.width * -1, sprite.height, region);
	}
	
	public void drawSpriteCenterReverse(GameObject sprite, TextureRegion region, float angle){
		drawSpriteCenter(sprite.center.x, sprite.center.y, sprite.width * -1, sprite.height, angle, region);
	}
	
	public void drawSpriteLowerLeft(float lower_left_x, float lower_left_y, float width, float height, TextureRegion region){
		// Check to make sure Sprite is within the World Bounds
		
		if(lower_left_x > Level.WORLD_RIGHT_EDGE || 
				lower_left_x + width < Level.WORLD_LEFT_EDGE ||
				lower_left_y > Level.WORLD_TOP_EDGE ||
				lower_left_y + height < Level.WORLD_BOTTOM_EDGE)
			return;
		
		///////////////////////////////////////////////////////
		
		float x1 = lower_left_x;
		float y1 = lower_left_y;
		float x2 = lower_left_x + width;
		float y2 = lower_left_y + height;
		
		verticesBuffer[bufferIndex++] = x1;
		verticesBuffer[bufferIndex++] = y1;
		verticesBuffer[bufferIndex++] = region.u1;
		verticesBuffer[bufferIndex++] = region.v2;
		
		verticesBuffer[bufferIndex++] = x2;
		verticesBuffer[bufferIndex++] = y1;
		verticesBuffer[bufferIndex++] = region.u2;
		verticesBuffer[bufferIndex++] = region.v2;
		
		verticesBuffer[bufferIndex++] = x2;
		verticesBuffer[bufferIndex++] = y2;
		verticesBuffer[bufferIndex++] = region.u2;
		verticesBuffer[bufferIndex++] = region.v1;
		
		verticesBuffer[bufferIndex++] = x1;
		verticesBuffer[bufferIndex++] = y2;
		verticesBuffer[bufferIndex++] = region.u1;
		verticesBuffer[bufferIndex++] = region.v1;
		
		numSprites++;
	}
	
	public void drawSpriteCenter(float center_x, float center_y, float width, float height, TextureRegion region){
		// Check to make sure Sprite is within the World Bounds
		
		if(center_x > Level.WORLD_RIGHT_EDGE || 
				center_x + 5.0 < Level.WORLD_LEFT_EDGE ||
				center_y > Level.WORLD_TOP_EDGE ||
				center_y + 5.0 < Level.WORLD_BOTTOM_EDGE)
			return;
		
		///////////////////////////////////////////////////////
		
		float x1 = center_x - width/2;
		float y1 = center_y - height/2;
		float x2 = center_x + width/2;
		float y2 = center_y + height/2;
		
		verticesBuffer[bufferIndex++] = x1;
		verticesBuffer[bufferIndex++] = y1;
		verticesBuffer[bufferIndex++] = region.u1;
		verticesBuffer[bufferIndex++] = region.v2;
		
		verticesBuffer[bufferIndex++] = x2;
		verticesBuffer[bufferIndex++] = y1;
		verticesBuffer[bufferIndex++] = region.u2;
		verticesBuffer[bufferIndex++] = region.v2;
		
		verticesBuffer[bufferIndex++] = x2;
		verticesBuffer[bufferIndex++] = y2;
		verticesBuffer[bufferIndex++] = region.u2;
		verticesBuffer[bufferIndex++] = region.v1;
		
		verticesBuffer[bufferIndex++] = x1;
		verticesBuffer[bufferIndex++] = y2;
		verticesBuffer[bufferIndex++] = region.u1;
		verticesBuffer[bufferIndex++] = region.v1;
		
		numSprites++;
	}
	
// Below Function must use center position coordinates	

	public void drawSpriteCenter(float center_x, float center_y, float width, float height, float angle, TextureRegion region){
		
		float halfWidth = width/2;
		float halfHeight = height/2;
		
		float rad = angle * Vector2.TO_RADIANS;
		float cos = FloatMath.cos(rad);
		float sin = FloatMath.sin(rad);
		
		float x1 = -halfWidth * cos - (-halfHeight) * sin;
		float y1 = -halfWidth * sin + (-halfHeight) * cos;
		
		float x2 = halfWidth * cos - (-halfHeight) * sin;
		float y2 = halfWidth * sin + (-halfHeight) * cos;
		
		float x3 = halfWidth * cos - halfHeight * sin;
		float y3 = halfWidth * sin + halfHeight * cos;
		
		float x4 = -halfWidth * cos - halfHeight * sin;
		float y4 = -halfWidth * sin + halfHeight * cos;
		
		x1 += center_x;
		y1 += center_y;
		x2 += center_x;
		y2 += center_y;
		x3 += center_x;
		y3 += center_y;
		x4 += center_x;
		y4 += center_y;
		
		verticesBuffer[bufferIndex++] = x1;
		verticesBuffer[bufferIndex++] = y1;
		verticesBuffer[bufferIndex++] = region.u1;
		verticesBuffer[bufferIndex++] = region.v2;
		
		verticesBuffer[bufferIndex++] = x2;
		verticesBuffer[bufferIndex++] = y2;
		verticesBuffer[bufferIndex++] = region.u2;
		verticesBuffer[bufferIndex++] = region.v2;
		
		verticesBuffer[bufferIndex++] = x3;
		verticesBuffer[bufferIndex++] = y3;
		verticesBuffer[bufferIndex++] = region.u2;
		verticesBuffer[bufferIndex++] = region.v1;
		
		verticesBuffer[bufferIndex++] = x4;
		verticesBuffer[bufferIndex++] = y4;
		verticesBuffer[bufferIndex++] = region.u1;
		verticesBuffer[bufferIndex++] = region.v1;
		
		numSprites++;
	}
	
}

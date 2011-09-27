package com.slimejumper.gameframework.gl;

import javax.microedition.khronos.opengles.GL10;

import com.slimejumper.framework.impl.GLGraphics;
import com.slimejumper.gameframework.math.Vector2;

public class Camera2D {
	
	public Vector2 center;
	public float zoom;
	public final float frustumWidth;
	public final float frustumHeight;
	final GLGraphics glGraphics;
	
	public Camera2D(GLGraphics glGraphics, float frustumWidth, float frustumHeight){
		this.glGraphics = glGraphics;
		this.frustumHeight = frustumHeight;
		this.frustumWidth = frustumWidth;
		this.center = new Vector2(frustumWidth/2, frustumHeight/2);
		this.zoom = 1.0f;
	}
	
	public void setViewportAndMatrices(){
		GL10 gl = glGraphics.getGL();
		gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(center.x - frustumWidth * zoom / 2,
					center.x + frustumWidth * zoom / 2, 
					center.y - frustumHeight * zoom / 2,
					center.y + frustumHeight * zoom / 2,
					1, -1);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	
	public void touchToWorld(Vector2 touch){
		touch.x = (touch.x / (float) glGraphics.getWidth()) * frustumWidth * zoom;
		touch.y = (1 - touch.y / (float) glGraphics.getHeight()) * frustumHeight * zoom;
		touch.add(center).sub(frustumWidth*zoom/2, frustumHeight*zoom/2);
	}
}

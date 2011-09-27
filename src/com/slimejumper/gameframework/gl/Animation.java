package com.slimejumper.gameframework.gl;

public class Animation {
	public static final int ANIMATION_LOOPING = 0;
	public static final int ANIMATION_NONLOOPING = 1;
	
	final TextureRegion[] keyFrames;
	final float frameDuration;
	
	public int frameNumber;
	
	public Animation(float frameDuration, TextureRegion ... keyFrames){
		this.frameDuration = frameDuration;
		this.keyFrames = keyFrames;
		frameNumber = 0;
	}
	
	public int getFrameNumber(float stateTime, int mode){
		frameNumber = (int)(stateTime / frameDuration);
		
		if(mode == ANIMATION_NONLOOPING){
			frameNumber = Math.min(keyFrames.length-1, frameNumber);
		} else {
			frameNumber = frameNumber % keyFrames.length;
		}
		
		return frameNumber;
	}
	
	public TextureRegion getKeyFrame(float stateTime, int mode){
		frameNumber = (int)(stateTime / frameDuration);
		
		if(mode == ANIMATION_NONLOOPING){
			frameNumber = Math.min(keyFrames.length-1, frameNumber);
		} else {
			frameNumber = frameNumber % keyFrames.length;
		}
		
		return keyFrames[frameNumber];
	}

}

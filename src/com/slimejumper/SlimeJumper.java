package com.slimejumper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.slimejumper.Assets;
import com.slimejumper.GameScreen;
import com.slimejumper.Settings;
import com.slimejumper.framework.impl.GLGame;
import com.slimejumper.gameframework.Screen;
import com.slimejumper.tools.PoolManager;

public class SlimeJumper extends GLGame {
	boolean firstTimeCreate = true;

	public Screen getStartScreen() {
		
		return new MenuScreen(this);
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config){
		super.onSurfaceCreated(gl, config);
		
		if(firstTimeCreate){
			Settings.load(getFileIO());
			Assets.load(this);			
			firstTimeCreate = false;
		} else {
			Assets.reload();
		}
		
	}
	
	public void onPause(){
		super.onPause();
/*		
		if(Settings.soundEnabled)
			Assets.music.pause();
*/
	}

}
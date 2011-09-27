package com.slimejumper.framework.impl;

import com.slimejumper.gameframework.Game;
import com.slimejumper.gameframework.Screen;

public abstract class GLScreen extends Screen{
	protected final GLGraphics glGraphics;
	protected final GLGame glGame;
	
	public GLScreen(Game game){
		super(game);
		glGame = (GLGame) game;
		glGraphics = ((GLGame)game).getGLGraphics();
	}

}

package com.slimejumper;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.slimejumper.framework.impl.GLScreen;
import com.slimejumper.gameframework.Game;
import com.slimejumper.gameframework.Input.TouchEvent;
import com.slimejumper.gameframework.gl.Camera2D;
import com.slimejumper.gameframework.gl.SpriteBatcher;
import com.slimejumper.gameframework.math.OverlapTester;
import com.slimejumper.gameframework.math.Rectangle;
import com.slimejumper.gameframework.math.Vector2;

public class SelectorScreen extends GLScreen{
	
	Camera2D guiCam;
	SpriteBatcher batcher;
	
	Rectangle TesterLevelRect;
	Rectangle MenuLevelRect;
	Rectangle CaveLevelRect;
	Vector2 touchPoint;
	
	public SelectorScreen(Game game){
		super(game);
		
		guiCam = new Camera2D(glGraphics, 800, 480);
		batcher = new SpriteBatcher(glGraphics, 10);
		
		TesterLevelRect = new Rectangle(0, 0, 300, 480);
		MenuLevelRect = new Rectangle(300, 0, 300, 480);
		CaveLevelRect = new Rectangle(600, 0, 200, 480);
		
		touchPoint = new Vector2();
		
	}

	@Override
	public void update(float deltaTime) {
		 List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	        game.getInput().getKeyEvents();
	        int len = touchEvents.size();
	        for(int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            touchPoint.set(event.x, event.y);
	            guiCam.touchToWorld(touchPoint);
	            
	            if(event.type == TouchEvent.TOUCH_UP) {
	                if(OverlapTester.pointInRectangle(TesterLevelRect, touchPoint)) {
	                    game.setScreen(new TesterScreen(game));
	                    return;
	                }
	            }
	            
	            if(event.type == TouchEvent.TOUCH_UP) {
	                if(OverlapTester.pointInRectangle(MenuLevelRect, touchPoint)) {
	                    game.setScreen(new MenuScreen(game));
	                    return;
	                }
	            }
	            
	            if(event.type == TouchEvent.TOUCH_UP) {
	                if(OverlapTester.pointInRectangle(CaveLevelRect, touchPoint)) {
	                    game.setScreen(new CaveScreen(game));
	                    return;
	                }
	            }
	        }
		
	}

	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();        
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();
        
        gl.glEnable(GL10.GL_TEXTURE_2D);
        
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        
        batcher.beginBatch(Assets.game_sprites);          
        batcher.drawSpriteLowerLeft(TesterLevelRect.lowerLeft.x, TesterLevelRect.lowerLeft.y, 
        		TesterLevelRect.width, TesterLevelRect.height, Assets.jellyfishDemonShockAttackFrame2);
        batcher.drawSpriteLowerLeft(MenuLevelRect.lowerLeft.x, MenuLevelRect.lowerLeft.y, 
        		MenuLevelRect.width, MenuLevelRect.height, Assets.flyingSnakeStandardFrame3);
        batcher.drawSpriteLowerLeft(CaveLevelRect.lowerLeft.x, CaveLevelRect.lowerLeft.y, 
        		CaveLevelRect.width, CaveLevelRect.height, Assets.RedWhaleDemonFrame3);
        batcher.endBatch();
        
        gl.glDisable(GL10.GL_BLEND);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}

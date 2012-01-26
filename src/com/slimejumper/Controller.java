package com.slimejumper;

import com.slimejumper.gameframework.Input.TouchEvent;
import com.slimejumper.gameframework.gl.Camera2D;
import com.slimejumper.gameframework.math.Vector2;

public class Controller {
	private boolean touchEnabled = Settings.touchEnabled;
	
	public static final float CONTROLLER_ICON_WIDTH = 2.5f;
	public static final float CONTROLLER_ICON_HEIGHT = 3;
	
	public static final int CONTROLLER_LEFT = 0;
	public static final int CONTROLLER_RIGHT = 1;
	public static final int CONTROLLER_NEUTRAL = 2;
	
	private static int pointerIdDefault = -30;
	public int leftPointerId = pointerIdDefault;
	public int rightPointerId = pointerIdDefault;
	public int attackPointerId = pointerIdDefault;
	public int attackLeftPointerId = pointerIdDefault;
	public int attackRightPointerId = pointerIdDefault;

	public boolean LeftButtonDown = false;
	public boolean RightButtonDown = false;
	public int active_control;
	
	public boolean fireAttack = false;
	public boolean fireAttackDown = false;
	public boolean fireAttackLeftDown = false;
	public boolean fireAttackRightDown = false;

	
	
	
	
	public float controller_accel;
	
	static Vector2 touchPoint;

	private static Camera2D guiCam;

	public Controller(Camera2D guiCam) {
		Controller.guiCam = guiCam;
		touchPoint = new Vector2();
		
		active_control = CONTROLLER_LEFT;
	}

	public void input(TouchEvent event) {
		touchPoint.set(event.x, event.y);
		guiCam.touchToWorld(touchPoint);
		if(touchEnabled){
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (touchPoint.x > guiCam.center.x + guiCam.frustumWidth / 4 &&
						touchPoint.y < guiCam.center.y ) {
				
					rightPointerId = event.pointer;
					RightButtonDown = true;
					active_control = CONTROLLER_RIGHT;
				} else if (touchPoint.x < guiCam.center.x - guiCam.frustumWidth / 4 &&
						touchPoint.y < guiCam.center.y) {
				
					leftPointerId = event.pointer;
					LeftButtonDown = true;
					active_control = CONTROLLER_LEFT;
				} else if((touchPoint.x > guiCam.center.x + guiCam.frustumWidth / 4 && touchPoint.y > guiCam.center.y) || 
						(touchPoint.x < guiCam.center.x - guiCam.frustumWidth / 4 && touchPoint.y > guiCam.center.y)){
					attackPointerId = event.pointer;
					fireAttack = true;
					fireAttackDown = true;
				}
			}

			else if (event.type == TouchEvent.TOUCH_DRAGGED) {
				if (event.pointer == rightPointerId) {
					if (touchPoint.x > guiCam.center.x + guiCam.frustumWidth / 4 &&
							touchPoint.y < guiCam.center.y) {
						RightButtonDown = true;
					} else {
						RightButtonDown = false;
					}

				} else if (event.pointer == leftPointerId) {
					if (touchPoint.x < guiCam.center.x - guiCam.frustumWidth / 4 &&
							touchPoint.y < guiCam.center.y) {
						LeftButtonDown = true;
					} else {
						LeftButtonDown = false;
					}

				} else if (event.pointer == attackPointerId) {
					if((touchPoint.x > guiCam.center.x + guiCam.frustumWidth / 4 && touchPoint.y > guiCam.center.y) || 
							(touchPoint.x < guiCam.center.x - guiCam.frustumWidth / 4 && touchPoint.y > guiCam.center.y)){
						fireAttackDown = true;
					} else {
						fireAttackDown = false;
					}
				}
			}
		

			else if (event.type == TouchEvent.TOUCH_UP) {
				if (event.pointer == rightPointerId){
					RightButtonDown = false;
					rightPointerId = pointerIdDefault;
				}
				else if (event.pointer == leftPointerId){
					LeftButtonDown = false;
					leftPointerId = pointerIdDefault;
				}
				else if (event.pointer == attackPointerId){
					fireAttackDown = false;
					attackPointerId = pointerIdDefault;
				}
			}
		}
		
		else{
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if(touchPoint.x > guiCam.center.x){
					fireAttack = true;
					attackRightPointerId = event.pointer;
					fireAttackRightDown = true;
				}
				else if(touchPoint.x < guiCam.center.x){
					fireAttack = true;
					attackLeftPointerId = event.pointer;
					fireAttackLeftDown = true;
				}
			}		
			else if (event.type == TouchEvent.TOUCH_DRAGGED) {				
				if (event.pointer == attackRightPointerId) {
					if(touchPoint.x > guiCam.center.x){
						fireAttackRightDown = true;
					} else {
						fireAttackRightDown = false;
					}
				}
				
				else if (event.pointer == attackLeftPointerId) {
					if(touchPoint.x < guiCam.center.x){
						fireAttackLeftDown = true;
					} else {
						fireAttackLeftDown = false;
					}
				}
			}
		
			else if (event.type == TouchEvent.TOUCH_UP) {
				if (event.pointer == attackRightPointerId){
					fireAttackRightDown = false;
					attackRightPointerId = pointerIdDefault;
				}
				
				else if (event.pointer == attackLeftPointerId){
					fireAttackLeftDown = false;
					attackLeftPointerId = pointerIdDefault;
				}
			}
		}
		
	}

/*
	public static int processMoveDirection(Controller controller) {		
		if(controller.RightButtonDown && controller.LeftButtonDown){
			if(controller.active_control == CONTROLLER_LEFT)
				return Hero.HERO_LEFT;
			else if(controller.active_control == CONTROLLER_RIGHT)
				return Hero.HERO_RIGHT;
			else
				return Hero.HERO_RIGHT;
		}
		else if(controller.RightButtonDown)
			return Hero.HERO_RIGHT;
		else if(controller.LeftButtonDown)
			return Hero.HERO_LEFT;
		else
			return Hero.HERO_NEUTRAL;
	}
*/	
	
	public static int processMoveDirection(Controller controller) {		
		if(controller.RightButtonDown && controller.LeftButtonDown){
			if(controller.active_control == CONTROLLER_LEFT)
				return CONTROLLER_LEFT;
			else if(controller.active_control == CONTROLLER_RIGHT)
				return CONTROLLER_RIGHT;
			else
				return CONTROLLER_RIGHT;
		}
		else if(controller.RightButtonDown)
			return CONTROLLER_RIGHT;
		else if(controller.LeftButtonDown)
			return CONTROLLER_LEFT;
		else
			return CONTROLLER_NEUTRAL;
	}

	public void setAccel(float accelX) {
		controller_accel = accelX;
	}
}
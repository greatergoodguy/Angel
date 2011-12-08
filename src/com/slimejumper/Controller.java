package com.slimejumper;

import com.slimejumper.gameframework.Input.TouchEvent;
import com.slimejumper.gameframework.gl.Camera2D;
import com.slimejumper.gameframework.math.Vector2;
import com.slimejumper.world.Hero;

public class Controller {
	
	public static final int CONTROLLER_LEFT = 0;
	public static final int CONTROLLER_RIGHT = 1;
	
	private static int pointerIdDefault = -30;
	public static int leftPointerId = pointerIdDefault;
	public static int rightPointerId = pointerIdDefault;

	public boolean LeftButtonDown = false;
	public boolean RightButtonDown = false;
	public int active_control;
	
	public boolean fireAttack = false;

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
				fireAttack = true;
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
		}
	}
	
	public static int processMoveDirection(Controller controller) {		
		if(controller.RightButtonDown && controller.LeftButtonDown){
			if(controller.active_control == Controller.CONTROLLER_LEFT)
				return Hero.HERO_LEFT;
			else if(controller.active_control == Controller.CONTROLLER_RIGHT)
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
}
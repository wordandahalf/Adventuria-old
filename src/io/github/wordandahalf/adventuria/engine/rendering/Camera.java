package io.github.wordandahalf.adventuria.engine.rendering;

import java.util.HashMap;

import org.lwjgl.input.Keyboard;

import io.github.wordandahalf.adventuria.controls.ControlManager;
import io.github.wordandahalf.adventuria.controls.KeyboardControllable;

public class Camera implements KeyboardControllable {
	public static final float CULL_LENIENCY = 0.5f;
	
	private float x, y;
	
	private float width, height;
	
	public Camera(float x, float y, float width, float height) {		
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		ControlManager.add(this);
	}
	
	public boolean isPointVisible(float x, float y) {
		return (((x - this.x) <= this.width) 	&& ((x - this.x) > 0)) &&
			   (((y - this.y) <= this.height) 	&& ((y - this.y) > 0));
	}
	
	public boolean isBoxVisible(float x, float y, float width, float height) {
		//Box is within camera
		if(isPointVisible(x, y) || isPointVisible(x + width, y)
		|| isPointVisible(x, y + height) || isPointVisible(x + width, y + height))
			return true;
		
		if(!((y <= this.y) && ((y + height) >= (this.y + this.height)))) {
			return false;
		}
		
		//Box encompasses camera
		if((x <=  this.x) && ((x + width) >= (this.x + this.width))) {
			return true;
		}
		
		//Box encompasses camera but is offset to the right
		if((x >=  this.x) && (x <= (this.x + this.width)) && ((x + width) >= (this.x + this.width))) {
			return true;
		}
		
		//Box encompasses camera but is offset to the left
		if((x <=  this.x) && ((x + width) > this.x) && ((x + width) <= (this.x + this.width))) {
			return true;
		}
		
		return false;
	}
	
	public float getX() { return this.x; }
	public void setX(float x) { this.x = x; }
	
	public float getY() { return this.y; }
	public void setY(float y) { this.y = y; }
	
	public float getWidth() { return this.width; }
	public float getHeight() { return this.height; }

	@Override
	public void updateInputs(HashMap<Integer, Boolean> keyStates) {
		float movementSpeed = 2f;
		
		if(keyStates.get(Keyboard.KEY_LSHIFT)) {
			movementSpeed /= 2;
		}
		if(keyStates.get(Keyboard.KEY_LCONTROL)) {
			movementSpeed *= 2;
		}
		if(keyStates.get(Keyboard.KEY_D)) {
			Renderer.getCamera().setX(Renderer.getCamera().getX() + movementSpeed);
		}
		if(keyStates.get(Keyboard.KEY_A)) {
			Renderer.getCamera().setX(Renderer.getCamera().getX() - movementSpeed);
		}
	}

	@Override
	public int[] getRegisteredKeys() {
		return new int[] {Keyboard.KEY_A, Keyboard.KEY_D, Keyboard.KEY_LSHIFT, Keyboard.KEY_LCONTROL};
	}
}

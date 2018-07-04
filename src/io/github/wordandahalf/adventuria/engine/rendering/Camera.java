package io.github.wordandahalf.adventuria.engine.rendering;

import java.util.HashMap;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import io.github.wordandahalf.adventuria.controls.ControlManager;
import io.github.wordandahalf.adventuria.controls.KeyboardControllable;
import io.github.wordandahalf.adventuria.engine.physics.Tickable;
import io.github.wordandahalf.adventuria.engine.rendering.Renderer.RenderPosition;

public class Camera implements Tickable, Renderable, KeyboardControllable {
	public static final float CULL_LENIENCY = 0.5f;
	
	private float x, y;
	
	private float width, height;
	
	public Camera(float x, float y, float width, float height) {		
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		Renderer.add(this);
		ControlManager.add(this);
	}
	
	//TODO: Better culling system?
	public boolean isPointVisible(float x, float y) {
		if((x >= (this.x * (1 - Camera.CULL_LENIENCY))) && (x <= ((this.x + this.width) * (1 + Camera.CULL_LENIENCY)))) {
			if((y >= this.y * (1 - Camera.CULL_LENIENCY)) && (y <= (this.y + this.height) * (1 + Camera.CULL_LENIENCY))) {
				return true;
			}
		}
		
		return false;
		
		/*if((x >= this.x) && (x <= (this.x + this.width))) {
			if((y >= this.y) && (y <= (this.y + this.height))) {
				return true;
			}
		}
		
		return false;*/
	}
	
	public boolean isBoxVisible(float x, float y, float width, float height) {
		if(this.x >= x && this.x <= (x + width)) {
			if(this.y >= y && this.y <= (y + height)) {
				return true;
			}
		}
		
		if((this.x + this.width) >= x && (this.x + this.width) <= (x + width)) {
			if(this.y >= y && this.y <= (y + height)) {
				return true;
			}
		}
		
		if((this.x + this.width) >= x && (this.x + this.width) <= (x + width)) {
			if((this.y + this.height) >= y && (this.y + this.height) <= (y + height)) {
				return true;
			}
		}
		
		if(this.x >= x && this.x <= (x + width)) {
			if((this.y + this.height) >= y && (this.y + this.height) <= (y + height)) {
				return true;
			}
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
	public void update() {
		
	}

	@Override
	public void render(Graphics g, Camera camera) {
		g.setColor(Color.red);
		g.drawRect(this.x - camera.getX(), this.y - camera.getY(), this.width, this.height);
	}

	@Override
	public RenderPosition getRenderPosition() {
		return RenderPosition.UI;
	}

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

package io.github.wordandahalf.adventuria.debug;

import java.util.HashMap;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import io.github.wordandahalf.adventuria.AdventuriaGame;
import io.github.wordandahalf.adventuria.controls.ControlManager;
import io.github.wordandahalf.adventuria.controls.KeyboardControllable;
import io.github.wordandahalf.adventuria.engine.physics.PhysicsEngine;
import io.github.wordandahalf.adventuria.engine.physics.Tickable;
import io.github.wordandahalf.adventuria.engine.rendering.Camera;
import io.github.wordandahalf.adventuria.engine.rendering.Renderable;
import io.github.wordandahalf.adventuria.engine.rendering.Renderer;
import io.github.wordandahalf.adventuria.engine.rendering.Renderer.RenderPosition;

public class DebugScreen implements Tickable, Renderable, KeyboardControllable {
	private boolean alwaysEnabled, enabled;
	
	public DebugScreen(boolean alwaysEnabled) {
		this.alwaysEnabled = alwaysEnabled;
		enabled = alwaysEnabled;
		
		PhysicsEngine.add(this);
		Renderer.add(this);
		ControlManager.add(this);
	}
	
	@Override
	public void updateInputs(HashMap<Integer, Boolean> keyStates) {
		if(keyStates.get(Keyboard.KEY_F3) || alwaysEnabled)
			enabled = true;
		else
			enabled = false;
	}

	@Override
	public int[] getRegisteredKeys() {
		return new int[] { Keyboard.KEY_F3 };
	}

	@Override
	public void render(Graphics g, Camera camera) {
		if(!enabled)
			return;
		
		g.setColor(Color.white);
		
		g.drawString("FPS: " + AdventuriaGame.getAppContainer().getFPS(), 10, 10);
		g.drawString("TPS: " + PhysicsEngine.getTPS(), 10, 30);
	}

	@Override
	public RenderPosition getRenderPosition() {
		return RenderPosition.UI;
	}

	@Override
	public void update() {
		
	}
}

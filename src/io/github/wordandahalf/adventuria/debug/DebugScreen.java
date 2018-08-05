package io.github.wordandahalf.adventuria.debug;

import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import io.github.wordandahalf.adventuria.AdventuriaGame;
import io.github.wordandahalf.adventuria.controls.ControlManager;
import io.github.wordandahalf.adventuria.controls.KeyState;
import io.github.wordandahalf.adventuria.controls.KeyboardControllable;
import io.github.wordandahalf.adventuria.engine.physics.PhysicsEngine;
import io.github.wordandahalf.adventuria.engine.physics.Tickable;
import io.github.wordandahalf.adventuria.engine.rendering.Camera;
import io.github.wordandahalf.adventuria.engine.rendering.Renderable;
import io.github.wordandahalf.adventuria.engine.rendering.Renderer;
import io.github.wordandahalf.adventuria.engine.rendering.Renderer.RenderPosition;
import io.github.wordandahalf.adventuria.world.Sky;
import io.github.wordandahalf.adventuria.world.WorldManager;
import io.github.wordandahalf.adventuria.world.generator.ChunkGenerationManager;
import io.github.wordandahalf.adventuria.world.io.AdventuriaWorldSaver;

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
	public void updateInputs(HashMap<Integer, KeyState> keyStates) {		
		if(keyStates.get(Keyboard.KEY_F) == KeyState.TOGGLED) {
			try {
				AdventuriaWorldSaver.save(WorldManager.getCurrentWorld());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(alwaysEnabled)
			return;
		
		if(keyStates.get(Keyboard.KEY_F3) == KeyState.TOGGLED)
			enabled = !enabled;
	}

	@Override
	public int[] getRegisteredKeys() {
		return new int[] { Keyboard.KEY_F3, Keyboard.KEY_F, Keyboard.KEY_L };
	}

	@Override
	public boolean render(Graphics g, Camera camera) {
		if(!enabled)
			return false;
		
		g.setColor(Color.white);
		
		g.drawString("FPS: " + AdventuriaGame.getAppContainer().getFPS(), 10, 10);
		g.drawString("TPS: " + PhysicsEngine.getTPS(), 10, 30);
		g.drawString("Time: " + Sky.ticksRemaining + ", (isDay = " + Sky.isDay() + ")", 10, 50);
		g.drawString("Draws: " + Renderer.getDrawnObjects(), 10, 70);
		g.drawString("Generating: " + ChunkGenerationManager.getLastGeneratedChunk() + 
					" (currently): " + ChunkGenerationManager.getAmountGenerating() +
					" (next): " + ChunkGenerationManager.getNextChunk(
							ChunkGenerationManager.getLastGeneratedChunk()), 
					10, 90);
		
		return true;
	}

	@Override
	public RenderPosition getRenderPosition() {
		return RenderPosition.UI;
	}

	@Override
	public void update() {
		
	}
}

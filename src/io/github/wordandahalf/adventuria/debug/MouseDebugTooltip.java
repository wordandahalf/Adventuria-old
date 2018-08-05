package io.github.wordandahalf.adventuria.debug;

import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import io.github.wordandahalf.adventuria.controls.ControlManager;
import io.github.wordandahalf.adventuria.controls.KeyState;
import io.github.wordandahalf.adventuria.controls.MouseControllable;
import io.github.wordandahalf.adventuria.engine.rendering.Camera;
import io.github.wordandahalf.adventuria.engine.rendering.Renderable;
import io.github.wordandahalf.adventuria.engine.rendering.Renderer;
import io.github.wordandahalf.adventuria.engine.rendering.Renderer.RenderPosition;
import io.github.wordandahalf.adventuria.world.Chunk;
import io.github.wordandahalf.adventuria.world.WorldManager;
import io.github.wordandahalf.adventuria.world.block.Block;
import io.github.wordandahalf.adventuria.world.block.BlockType;

public class MouseDebugTooltip implements MouseControllable, Renderable {
	private int mouseX = 0, mouseY = 0;
	
	public MouseDebugTooltip() {
		Renderer.add(this);
		ControlManager.add(this);
	}
	
	@Override
	public void updateInputs(HashMap<Integer, KeyState> buttonStates, int mouseX, int mouseY) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
	}

	@Override
	public int[] getRegisteredButtons() {
		return new int[] {};
	}

	@Override
	public boolean render(Graphics g, Camera camera) {
		float absoluteMouseX = this.mouseX + camera.getX();
		float absoluteMouseY = this.mouseY + camera.getY();
		
		int relativeBlockX = (int) ((Math.floor(absoluteMouseX / Block.TILE_SIZE)) + Chunk.CHUNK_WIDTH) % Chunk.CHUNK_WIDTH;
		int relativeBlockY = (int) ((Math.floor(absoluteMouseY / Block.TILE_SIZE)) + Chunk.CHUNK_HEIGHT) % Chunk.CHUNK_HEIGHT;
		
		int chunkX = (int) Math.floor(Math.floor(absoluteMouseX / Block.TILE_SIZE) / Chunk.CHUNK_WIDTH);
		int chunkY = (int) Math.floor(Math.floor(absoluteMouseY / Block.TILE_SIZE) / Chunk.CHUNK_HEIGHT);
		
		int absoluteBlockX = relativeBlockX + (chunkX * Chunk.CHUNK_WIDTH);
		int absoluteBlockY = relativeBlockY + (chunkY * Chunk.CHUNK_HEIGHT);
		
		BlockType type = WorldManager.getCurrentWorld().getBlock(absoluteBlockX, absoluteBlockY);
		
		g.setColor(new Color(1f, 1f, 1f, 0.8f));
		g.fillRect((absoluteBlockX * Block.TILE_SIZE) - camera.getX(), (absoluteBlockY * Block.TILE_SIZE) - camera.getY(), Block.TILE_SIZE, Block.TILE_SIZE);
		
		g.setColor(new Color(1f, 1f, 1f, 0.5f));
		g.fillRect(this.mouseX, this.mouseY, 17 * Block.TILE_SIZE, 5 * Block.TILE_SIZE);
		
		g.setColor(Color.white);
		g.drawString("Block: " + type.getName() + ", [" + relativeBlockX + ", " + relativeBlockY + "], ("
					+ absoluteBlockX + ", " + absoluteBlockY + ")", 
					this.mouseX + 7, this.mouseY + 5);
		
		g.drawString("Chunk: (" + chunkX + ", " + chunkY + ")", 
				this.mouseX + 7, this.mouseY + 20);
		
		g.drawString("Mouse: (" + this.mouseX + ", " + this.mouseY + ")", 
				this.mouseX + 7, this.mouseY + 35);
		
		g.drawString("Camera: (" + camera.getX() + ", " + camera.getY() + ")", 
				this.mouseX + 7, this.mouseY + 50);
		
		return true;
	}

	@Override
	public RenderPosition getRenderPosition() {
		return RenderPosition.UI;
	}
}

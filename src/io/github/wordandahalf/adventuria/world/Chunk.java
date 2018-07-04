package io.github.wordandahalf.adventuria.world;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import io.github.wordandahalf.adventuria.assets.AssetManager;
import io.github.wordandahalf.adventuria.engine.rendering.Camera;
import io.github.wordandahalf.adventuria.engine.rendering.Renderable;
import io.github.wordandahalf.adventuria.engine.rendering.Renderer;
import io.github.wordandahalf.adventuria.engine.rendering.Renderer.RenderPosition;
import io.github.wordandahalf.adventuria.world.block.Block;
import io.github.wordandahalf.adventuria.world.block.BlockType;

public class Chunk implements Renderable {
	public static final int CHUNK_WIDTH = 256;
	public static final int CHUNK_HEIGHT = 128;
	
	private final int x, y;
	
	private Block[][] blocks;
	
	public Chunk(int x, int y, Block... blocks) {
		this.x = x;
		this.y = y;
		
		this.blocks = new Block[CHUNK_WIDTH][CHUNK_HEIGHT];
		
		Renderer.add(this);
	}
	
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	
	public BlockType getBlock(int x, int y) { 
		try {
			return this.blocks[x][y].getBlockType(); 
		} catch(Exception e) {
			return BlockType.AIR;
		}
	}
	public void setBlock(int x, int y, Block block) { this.blocks[x][y] = block; }
	public void setBlock(int x, int y, BlockType type) { this.blocks[x][y] = new Block(x, y, type); }

	@Override
	public void render(Graphics g, Camera camera) {
		g.setColor(Color.green);
		g.drawRect((this.x * Chunk.CHUNK_WIDTH * Block.TILE_SIZE) - camera.getX(), (this.y * CHUNK_HEIGHT * Block.TILE_SIZE) - camera.getHeight(), Chunk.CHUNK_WIDTH * Block.TILE_SIZE, Chunk.CHUNK_HEIGHT * Block.TILE_SIZE);
		
		if(camera.isBoxVisible(this.x * Chunk.CHUNK_WIDTH * Block.TILE_SIZE, this.y * Chunk.CHUNK_HEIGHT * Block.TILE_SIZE, Chunk.CHUNK_WIDTH * Block.TILE_SIZE, Chunk.CHUNK_HEIGHT * Block.TILE_SIZE)) {
			g.setColor(Color.orange);
			
			for(Block[] bA : this.blocks) {
				for(Block b : bA) {
					try {
						int actualX = (b.getBlockX() * Block.TILE_SIZE) + (this.x * Chunk.CHUNK_WIDTH * Block.TILE_SIZE);
						int actualY = (b.getBlockY() * Block.TILE_SIZE) + (this.y * Chunk.CHUNK_HEIGHT * Block.TILE_SIZE);
						
						if(camera.isPointVisible(actualX, actualY)) {
							g.drawImage(AssetManager.getTexture(b.getBlockType().getTexture()), actualX - camera.getX(), actualY - camera.getY());
							g.drawRect(actualX - camera.getX(), actualY - camera.getY(), Block.TILE_SIZE, Block.TILE_SIZE);
						} else {
							g.setColor(Color.cyan);
							g.drawRect(actualX - camera.getX(), actualY - camera.getY(), Block.TILE_SIZE, Block.TILE_SIZE);
						}
					} catch(Exception e) {}
				}
			}
		}		
	}

	@Override
	public RenderPosition getRenderPosition() {
		return RenderPosition.FOREGROUND;
	}
}

package io.github.wordandahalf.adventuria.world;

import java.io.Serializable;

import org.newdawn.slick.Graphics;

import io.github.wordandahalf.adventuria.assets.AssetManager;
import io.github.wordandahalf.adventuria.engine.rendering.Camera;
import io.github.wordandahalf.adventuria.engine.rendering.Renderable;
import io.github.wordandahalf.adventuria.engine.rendering.Renderer;
import io.github.wordandahalf.adventuria.engine.rendering.Renderer.RenderPosition;
import io.github.wordandahalf.adventuria.world.block.Block;
import io.github.wordandahalf.adventuria.world.block.BlockType;

public class Chunk implements Renderable, Serializable {
	private static final long serialVersionUID = -2981886018539227915L;
	
	public static final transient int CHUNK_WIDTH = 256;
	public static final transient int CHUNK_HEIGHT = 128;
	
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
	
	public Block[][] getBlocks() { return this.blocks; }
	
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
	public boolean render(Graphics g, Camera camera) {
		if(camera.isBoxVisible(this.x * Chunk.CHUNK_WIDTH * Block.TILE_SIZE, this.y * Chunk.CHUNK_HEIGHT * Block.TILE_SIZE, Chunk.CHUNK_WIDTH * Block.TILE_SIZE, Chunk.CHUNK_HEIGHT * Block.TILE_SIZE)) {
			for(Block[] bA : this.blocks) {
				for(Block b : bA) {
					if(b == null || b.getBlockType() == BlockType.AIR)
						continue;
					
					int actualX = (b.getBlockX() * Block.TILE_SIZE) + (this.x * Chunk.CHUNK_WIDTH * Block.TILE_SIZE);
					int actualY = (b.getBlockY() * Block.TILE_SIZE) + (this.y * Chunk.CHUNK_HEIGHT * Block.TILE_SIZE);

					if(camera.isBoxVisible(actualX, actualY, Block.TILE_SIZE, Block.TILE_SIZE)) {
						g.drawImage(AssetManager.getTexture(b.getBlockType().getTexture()), actualX - camera.getX(), actualY - camera.getY());
					}
				}
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public RenderPosition getRenderPosition() {
		return RenderPosition.FOREGROUND;
	}
}

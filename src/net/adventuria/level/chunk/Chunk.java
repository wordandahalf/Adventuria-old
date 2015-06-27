package net.adventuria.level.chunk;

import net.adventuria.block.Block;
import net.adventuria.block.BlockID;
import net.adventuria.level.Generator;
import net.adventuria.location.Location;

public class Chunk {
	
	public static final int WIDTH = 100, HEIGHT = 100;
	
	public Block[][] Block = new Block[WIDTH][HEIGHT];
	private Chunk leftChunk, rightChunk;
	private boolean hasBeenGenerated = false;
	
	public Chunk() {
		for(int x = 0; x < Chunk.WIDTH; x++) {
			for(int y = 0; y < Chunk.HEIGHT; y++) {
				Block[x][y] = new Block(new Location(x, y), BlockID.AIR);
			}
		}
	}
	
	public void generate() {
		if(!hasBeenGenerated) {
			Generator.genWorld(Block);
			hasBeenGenerated = true;
		}
	}
	
	public boolean hasBeenGenerated() {
		return hasBeenGenerated;
	}
	
	public Chunk getLeftChunk() {
		if(leftChunk == null) {
			leftChunk = new Chunk();
		}
		return leftChunk;
	}
	
	public Chunk getRightChunk() {
		if(rightChunk == null) {
			rightChunk = new Chunk();
		}
		return rightChunk;
	}
	
	public void setLeftChunk(Chunk c) {
		leftChunk = c;
	}
	
	public void setRightChunk(Chunk c) {
		rightChunk = c;
	}
}

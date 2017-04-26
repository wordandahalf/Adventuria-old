package net.adventuria.level.chunk;

import net.adventuria.block.Block;
import net.adventuria.block.BlockType;
import net.adventuria.level.biome.Biome;
import net.adventuria.location.Location;

public class Chunk {
	public static final int CHUNK_WIDTH = 256, CHUNK_HEIGHT = 256;
	
	public Block[][] blocks = new Block[CHUNK_WIDTH][CHUNK_HEIGHT];

	private Location location;
	
	private int id;
	
	private Biome biome;
	
	public Chunk(Location loc, int id, Biome biome) {
		this.location = loc;
		
		this.id = id;
		
		this.biome = biome;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public int getID() {
		return this.id;
	}
	
	public Biome getBiome() {
		return this.biome;
	}
	
	public Block[][] getBlocks() {
		return this.blocks;
	}
	
	public Block getBlock(int x, int y) {
		if(x >= 0 && y >= 0 && x <= CHUNK_WIDTH && y <= CHUNK_HEIGHT) {
			return this.blocks[x][y];
		}
		return null;
	}
	
	public void setBlock(int x, int y, BlockType type) {
		if(x >= 0 && x < CHUNK_WIDTH && y >= 0 && y < CHUNK_HEIGHT)
			this.blocks[x][y] = new Block(new Location(x, y), type);
	}
}

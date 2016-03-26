package net.adventuria.level.generator;

import java.util.Random;

import net.adventuria.block.BlockType;
import net.adventuria.level.chunk.Chunk;

public class DefaultGenerator implements Generator {
	public static final int PLAINS_RANGE = 5;
	public static final int DESERT_RANGE = 14;
	
	public void generate(Chunk c) {
		for(int x = 0; x < Chunk.CHUNK_WIDTH; x++) {
			for(int y = 0; y < Chunk.CHUNK_HEIGHT; y++) {
				c.setBlock(x, y, BlockType.AIR);
			}
		}
		
		switch(c.getBiome()) {
			case PLAINS:
				this.generatePlains(c);
				break;
			case DESERT:
				this.generateDesert(c);
				break;
			case OCEAN:
				this.generateOcean(c);
				break;
			default:
				System.err.println("Oops! There seems to be an error with the biome at\n (" + c.getLocation().getX() + ", " + c.getLocation().getY() + ")!");
				break;
		}
	}
	
	private void generatePlains(Chunk c) {
		Random rand = new Random();
		
		for(int x = 1; x < Chunk.CHUNK_WIDTH - 1; x++) {
			for(int y = Chunk.CHUNK_HEIGHT / 8; y < Chunk.CHUNK_HEIGHT; y++) {
				if(y < Chunk.CHUNK_HEIGHT - (PLAINS_RANGE + 1)) {
					
					//Generate the random surface of the world
					c.setBlock(x, y + rand.nextInt(rand.nextInt(PLAINS_RANGE - 1) + 1), BlockType.EARTH);
				}
				
				if(c.getBlock(x, y - 1).getBlockType() == BlockType.EARTH) {
					c.setBlock(x, y, BlockType.EARTH);
				}
				
				if(c.getBlock(x, y).getBlockType() == BlockType.EARTH) {
					if(c.getBlock(x, y - 1).getBlockType() == BlockType.AIR) {
						c.setBlock(x, y - 1, BlockType.GRASS);
					}
				}
			}
		}
	}
	
	private void generateDesert(Chunk c) {
		Random rand = new Random();
		
		for(int x = 1; x < Chunk.CHUNK_WIDTH - 1; x++) {
			for(int y = Chunk.CHUNK_HEIGHT / 8; y < Chunk.CHUNK_HEIGHT; y++) {
				if(y < Chunk.CHUNK_HEIGHT - (DESERT_RANGE + 1)) {
					
					//Generate the random surface of the world
					c.setBlock(x, y + rand.nextInt(rand.nextInt(DESERT_RANGE - 1) + 1), BlockType.SAND);
				}
				
				if(c.getBlock(x, y - 1).getBlockType() == BlockType.SAND) {
					c.setBlock(x, y, BlockType.SAND);
				}
			}
		}
	}
	
	private void generateOcean(Chunk c) {
		Random rand = new Random();
		
		for(int x = 1; x < Chunk.CHUNK_WIDTH - 1; x++) {
			for(int y = Chunk.CHUNK_HEIGHT / 8; y < Chunk.CHUNK_HEIGHT; y++) {
				c.setBlock(x, y, BlockType.WATERSOURCE);
				
				if(y < Chunk.CHUNK_HEIGHT - (PLAINS_RANGE + 1)) {
					if(y > (Chunk.CHUNK_HEIGHT - (2 * Chunk.CHUNK_HEIGHT / 3))) {
						c.setBlock(x, y + rand.nextInt(rand.nextInt(PLAINS_RANGE - 1) + 1), BlockType.EARTH);
					}
					
					if(c.getBlock(x, y - 1).getBlockType() == BlockType.EARTH) {
						c.setBlock(x, y, BlockType.EARTH);
					}
				}
			}
		}
	}
}

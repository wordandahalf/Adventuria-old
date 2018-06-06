package io.github.wordandahalf.adventuria.world.generator;

import io.github.wordandahalf.adventuria.world.Chunk;
import io.github.wordandahalf.adventuria.world.block.BlockType;
import io.github.wordandahalf.adventuria.world.generator.utils.OpenSimplexNoise;
public class DefaultGenerator extends ChunkGenerator {
	private OpenSimplexNoise noise;
	
	public DefaultGenerator() {
		super();
		
		this.noise = new OpenSimplexNoise(this.getSeed());
	}
	
	public DefaultGenerator(long seed) {
		super(seed);
		
		this.noise = new OpenSimplexNoise(this.getSeed());
	}
	
	@Override
	public Chunk generate(int chunkX, int chunkY) {
		System.out.println("Generating a chunk at (" + chunkX + ", " + chunkY + ")");
		
		Chunk chunk = new Chunk(chunkX, chunkY);
		
		for(int x = 0; x < Chunk.CHUNK_WIDTH; x++) {
			int adjustedX = x + (chunkX * Chunk.CHUNK_WIDTH);				
			
			double grassNoise = Math.abs(this.noise.eval(adjustedX * (1.0 / 300.0), 0)) * 1.0
					+ Math.abs(this.noise.eval(adjustedX * (1.0 / 150.0), 0)) * 0.5
					+ Math.abs(this.noise.eval(adjustedX * (1.0 / 75.0), 0)) * 0.25
					+ Math.abs(this.noise.eval(adjustedX * (1.0 / 37.5), 0)) * 0.125;
			
			int grassY = (int) (grassNoise * Chunk.CHUNK_HEIGHT / 4);
			chunk.setBlock(x, grassY, BlockType.GRASS);
			
			int stoneY = 
					(int) (grassY + 
					Math.abs(this.noise.eval(adjustedX * (2.0 / 300.0), 1) * (Chunk.CHUNK_HEIGHT / 9)));
			chunk.setBlock(x, stoneY, BlockType.STONE);
			
			for(int dirtY = grassY + 1; dirtY < stoneY; dirtY++) {
				chunk.setBlock(x, dirtY, BlockType.DIRT);
			}
		}
		
		return chunk;
	}
}

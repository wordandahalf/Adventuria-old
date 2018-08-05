package io.github.wordandahalf.adventuria.world.generator;

import io.github.wordandahalf.adventuria.world.Chunk;
import io.github.wordandahalf.adventuria.world.block.BlockType;
import io.github.wordandahalf.adventuria.world.generator.utils.OpenSimplexNoise;

public class DefaultGenerator extends ChunkGenerator {
	private static final long serialVersionUID = 593980842149292300L;
	
	private transient OpenSimplexNoise noise;
	
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
			
			this.fillDown(chunk, x, grassY + 1, stoneY, BlockType.DIRT);
		}
		
		return chunk;
	}
	
	private void fillDown(Chunk chunk, int x, int startY, int endY, BlockType type) {
		for(int y = startY; y < endY; y++)
			chunk.setBlock(x, y, type);
	}
}

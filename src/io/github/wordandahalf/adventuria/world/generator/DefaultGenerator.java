package io.github.wordandahalf.adventuria.world.generator;

import io.github.wordandahalf.adventuria.utils.Pair;
import io.github.wordandahalf.adventuria.world.Chunk;
import io.github.wordandahalf.adventuria.world.block.BlockType;
import io.github.wordandahalf.adventuria.world.generator.utils.OpenSimplexNoise;

public class DefaultGenerator extends ChunkGenerator {
	public static final int			MAX_CHUNKS_GENERATING = 4;
	
	private OpenSimplexNoise 		noise;
	
	private int						numChunksGenerating = 0;
	
	private Pair<Integer, Integer> 	lastChunk = new Pair<Integer, Integer>(0, 0);
	
	public DefaultGenerator() {
		super();
		
		this.noise = new OpenSimplexNoise(this.getSeed());
	}
	
	public DefaultGenerator(long seed) {
		super(seed);
		
		this.noise = new OpenSimplexNoise(this.getSeed());
	}
	
	public boolean canGenerate() { return this.numChunksGenerating <= MAX_CHUNKS_GENERATING; }
	public int getAmountGenerating() { return this.numChunksGenerating; }
	public Pair<Integer, Integer> getLastGeneratedChunk() { return this.lastChunk; }
	
	public Pair<Integer, Integer> getNextChunk( Pair<Integer, Integer> position) {
		int x = position.left;
		int y = position.right;
		
		if(x == 0 && y == 0)
			return new Pair<Integer, Integer>(-1, 1);
		
		//Position is a corner
		if(Math.abs(x) == Math.abs(y)) {
			//Top left corner
			if((y > x) && ((x + y) > x))
				return new Pair<Integer, Integer>(++x, y);
			if((x > -x) && ((x + y) > x))
				return new Pair<Integer, Integer>(x, --y);
			if((x > y) && ((x + y) < x))
				return new Pair<Integer, Integer>(--x, y);
			if((-x > x) && ((x + y) < x))
				return new Pair<Integer, Integer>(x, ++y);
		}
		
		//Top row of chunks
		if((y > x) && (x > -y)) {			
			//Position is not top-right corner
			return new Pair<Integer, Integer>(++x, y);
		} else
		//Right row of chunks
		if((x > y) && (y > -x)) {
			return new Pair<Integer, Integer>(x, --y);
		} else
		//Bottom row of chunks
		if((-y > x) && (x > y)) {
			return new Pair<Integer, Integer>(--x, y);
		} else
		//Left row of chunks
		if((-x > y) && (y > x)) {			
			//Next position should be the start of a new "ring" of chunks
			if(Math.abs(x) == Math.abs(y + 1))
				return new Pair<Integer, Integer>(--x, y += 2);
			else
				return new Pair<Integer, Integer>(x, ++y);
		}
		
		return new Pair<Integer, Integer>(0, 0);
	}
	
	@Override
	public Chunk generate(int chunkX, int chunkY) {				
		Chunk chunk = new Chunk(chunkX, chunkY);
		
		numChunksGenerating++;
		
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

		this.lastChunk = new Pair<Integer, Integer>(chunkX, chunkY);
		
		numChunksGenerating--;
		
		return chunk;
	}
	
	private void fillDown(Chunk chunk, int x, int startY, int endY, BlockType type) {
		for(int y = startY; y < endY; y++)
			chunk.setBlock(x, y, type);
	}
}

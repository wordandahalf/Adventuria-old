package io.github.wordandahalf.adventuria.world.generator;

import java.util.concurrent.Callable;

import io.github.wordandahalf.adventuria.world.Chunk;

public class ChunkGeneratorWorker implements Callable<Chunk> {
	private ChunkGenerator generator;
	
	private final int chunkX, chunkY;
	
	public ChunkGeneratorWorker(ChunkGenerator generator, int chunkX, int chunkY) {
		this.generator = generator;
		
		this.chunkX = chunkX;
		this.chunkY = chunkY;
	}

	@Override
	public Chunk call() throws Exception {
		return this.generator.generate(this.chunkX, this.chunkY);
	}
}

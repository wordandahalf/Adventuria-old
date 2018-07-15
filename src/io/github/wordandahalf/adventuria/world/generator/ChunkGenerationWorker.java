package io.github.wordandahalf.adventuria.world.generator;

import java.util.concurrent.Callable;

import io.github.wordandahalf.adventuria.utils.Pair;
import io.github.wordandahalf.adventuria.world.Chunk;

public class ChunkGenerationWorker implements Callable<Chunk> {
	private ChunkGenerator generator;
	
	private final Pair<Integer, Integer> position;
	
	public ChunkGenerationWorker(ChunkGenerator generator, Pair<Integer, Integer> position) {
		this.generator = generator;
		this.position = position;
	}

	@Override
	public Chunk call() throws Exception {
		return this.generator.generate(position.left, position.right);
	}
}

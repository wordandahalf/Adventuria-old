package io.github.wordandahalf.adventuria.world.generator;

import java.util.Random;

import io.github.wordandahalf.adventuria.world.Chunk;

public abstract class ChunkGenerator {
	private long seed;
	
	public ChunkGenerator() { this(new Random().nextLong()); }
	public ChunkGenerator(long seed) { this.seed = seed; }
	
	public abstract Chunk generate(int x, int y);
	
	public long getSeed() { return this.seed; }
}

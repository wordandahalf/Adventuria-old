package io.github.wordandahalf.adventuria.world.generator;

import java.io.Serializable;
import java.util.Random;

import io.github.wordandahalf.adventuria.world.Chunk;

public abstract class ChunkGenerator implements Serializable {
	private static final long serialVersionUID = 5330169269719380793L;
	
	private long seed;
	
	public ChunkGenerator() { this(new Random().nextLong()); }
	public ChunkGenerator(long seed) { this.seed = seed; }
	
	public abstract Chunk generate(int x, int y);
	
	public long getSeed() { return this.seed; }
}

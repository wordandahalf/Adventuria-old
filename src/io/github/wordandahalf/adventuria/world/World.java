package io.github.wordandahalf.adventuria.world;

import java.util.Collection;

import io.github.wordandahalf.adventuria.engine.physics.PhysicsEngine;
import io.github.wordandahalf.adventuria.engine.physics.Tickable;
import io.github.wordandahalf.adventuria.engine.rendering.Renderer;
import io.github.wordandahalf.adventuria.utils.BiKeyHashMap;
import io.github.wordandahalf.adventuria.utils.MathUtils;
import io.github.wordandahalf.adventuria.utils.Pair;
import io.github.wordandahalf.adventuria.world.block.Block;
import io.github.wordandahalf.adventuria.world.block.BlockType;
import io.github.wordandahalf.adventuria.world.generator.ChunkGenerationManager;
import io.github.wordandahalf.adventuria.world.generator.DefaultGenerator;

public class World implements Tickable {
	public static final int PREGENERATED_CHUNKS = 5;
	
	private DefaultGenerator generator;
	private BiKeyHashMap<Integer, Integer, Chunk> chunkRegistery;

	private Sky sky;
	
	public World() {
		this.generator = new DefaultGenerator();
		this.chunkRegistery = new BiKeyHashMap<>();
		
		this.sky = new Sky();
		Renderer.add(this.sky);
		PhysicsEngine.add(this);
	}
	
	public DefaultGenerator getGenerator() { return this.generator; }
	
	public Collection<Chunk> getChunks() { return chunkRegistery.values(); }
	
	public Chunk getChunk(int x, int y) {
		return this.chunkRegistery.get(x, y);
	}
	
	@Deprecated
	public Pair<Integer, Integer> getCurrentChunk() {
		return new Pair<Integer, Integer> ((int) (Math.floor(Renderer.getCamera().getX() / Block.TILE_SIZE) / Chunk.CHUNK_WIDTH)
				, (int) (Math.floor(Renderer.getCamera().getY() / Block.TILE_SIZE) / Chunk.CHUNK_HEIGHT));
	}
	
	public BlockType getBlock(int x, int y) {
		int chunkX = (int) Math.floor((double) x / Chunk.CHUNK_WIDTH);
		int chunkY = (int) Math.floor((double) y / Chunk.CHUNK_HEIGHT);
		
		int relativeBlockX = (x + Chunk.CHUNK_WIDTH) % Chunk.CHUNK_WIDTH;
		int relativeBlockY = (y + Chunk.CHUNK_HEIGHT) % Chunk.CHUNK_HEIGHT;
		
		return this.getBlock(chunkX, chunkY, relativeBlockX, relativeBlockY);
	}
	
	public BlockType getBlock(int chunkX, int chunkY, int x, int y) {
		return this.getChunk(chunkX, chunkY).getBlock(x, y);
	}
	
	@Override
	public void update() {
		Pair<Integer, Integer> next = ChunkGenerationManager.getNextChunk(ChunkGenerationManager.getLastGeneratedChunk());
		
		if(ChunkGenerationManager.canGenerate() && (MathUtils.distance(this.getCurrentChunk(), next) < ChunkGenerationManager.MAX_CHUNK_GENERATION_DISTANCE)) {
			ChunkGenerationManager.queueChunk(this.generator, next);
		}
		
		for(Chunk c : ChunkGenerationManager.getFinishedChunks()) {			
			this.chunkRegistery.put(c.getX(), c.getY(), c);
		}
	}
	
	public void generate() {
		this.chunkRegistery.put(0, 0, this.getGenerator().generate(0, 0));
	}
}

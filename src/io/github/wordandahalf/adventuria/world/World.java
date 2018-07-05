package io.github.wordandahalf.adventuria.world;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.github.wordandahalf.adventuria.engine.rendering.Renderer;
import io.github.wordandahalf.adventuria.utils.BiKeyHashMap;
import io.github.wordandahalf.adventuria.utils.Pair;
import io.github.wordandahalf.adventuria.world.block.BlockType;
import io.github.wordandahalf.adventuria.world.generator.ChunkGeneratorWorker;
import io.github.wordandahalf.adventuria.world.generator.DefaultGenerator;

public class World {
	public static final int PREGENERATED_CHUNKS = 5;
	
	private DefaultGenerator generator;
	private BiKeyHashMap<Integer, Integer, Chunk> chunkRegistery;

	private Sky sky;
	
	private ExecutorService generationThread;
	
	public World() {
		this.generator = new DefaultGenerator();
		this.chunkRegistery = new BiKeyHashMap<>();
		this.generationThread = Executors.newSingleThreadExecutor();
		
		this.sky = new Sky();
		Renderer.add(this.sky);
	}
	
	public Chunk getChunk(int x, int y) {
		return this.chunkRegistery.get(x, y);
	}
	
	public BlockType getBlock(int chunkX, int chunkY, int x, int y) {
		return this.getChunk(chunkX, chunkY).getBlock(x, y);
	}
	
	public void generate() {
		if((PREGENERATED_CHUNKS % 2) > 0) {
			int numberOnSides = (int) Math.floor(PREGENERATED_CHUNKS / 2);
			
			BiKeyHashMap<Integer, Integer, Future<Chunk>> workers = new BiKeyHashMap<>();
			
			for(int x = numberOnSides; x > 0; x--) {
				ChunkGeneratorWorker worker = new ChunkGeneratorWorker(generator, x, 0);
				
				workers.put(x, 0, this.generationThread.submit(worker));
			}
			
			for(int x = 0; x > -numberOnSides - 1; x--) {
				ChunkGeneratorWorker worker = new ChunkGeneratorWorker(generator, x, 0);
				
				workers.put(x, 0, this.generationThread.submit(worker));
			}
			
			for(Pair<Integer, Integer> location : workers.keySet()) {
				try {
					this.chunkRegistery.put(location.left, location.right, workers.get(location.left, location.right).get());
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

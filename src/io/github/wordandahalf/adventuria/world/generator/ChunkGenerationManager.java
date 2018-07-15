package io.github.wordandahalf.adventuria.world.generator;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.wordandahalf.adventuria.utils.Pair;
import io.github.wordandahalf.adventuria.world.Chunk;

public class ChunkGenerationManager {
	private static class ChunkGenerationWorker implements Callable<Chunk> {
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
	
	public static final int NUMBER_CHUNK_GENERATION_THREADS = 2;
	public static final int MAX_CHUNK_GENERATION_DISTANCE = 8;
	
	private static ExecutorService generationThreads;
	private static ArrayList<Chunk> finishedChunks = new ArrayList<>();
	
	public static void init() {
		generationThreads = Executors.newFixedThreadPool(NUMBER_CHUNK_GENERATION_THREADS);
	}
	
	public static ArrayList<Chunk> getFinishedChunks() { return finishedChunks; }
	
	public static void queueChunk(ChunkGenerator generator, Pair<Integer, Integer> position) {
		generationThreads.execute(new Runnable() {
			@Override
			public void run() {
				ChunkGenerationWorker worker = new ChunkGenerationWorker(generator, position);
				try {
					Chunk c = worker.call();
					finishedChunks.add(c);
				} catch(Exception e) {
					System.err.println("Error generating chunk: " + e.getMessage());
				}
			}
		});
	}
}

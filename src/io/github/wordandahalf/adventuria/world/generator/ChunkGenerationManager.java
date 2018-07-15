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
	
	public static final int 				NUMBER_CHUNK_GENERATION_THREADS = 2;
	public static final int 				MAX_CHUNK_GENERATION_DISTANCE 	= 8;
	public static final int					MAX_CHUNKS_GENERATING 			= 4;
	
	private static ExecutorService 			generationThreads;
	private static ArrayList<Chunk> 		finishedChunks 					= new ArrayList<>();

	private static int						numChunksGenerating 			= 0;
	
	private static Pair<Integer, Integer> 	lastChunk 						= new Pair<Integer, Integer>(0, 0);
	
	public static void init() {
		generationThreads = Executors.newFixedThreadPool(NUMBER_CHUNK_GENERATION_THREADS);
	}
	
	public static ArrayList<Chunk> 			getFinishedChunks() 	{ return finishedChunks; }
	public static boolean 					canGenerate() 			{ return numChunksGenerating <= MAX_CHUNKS_GENERATING; }
	public static int 						getAmountGenerating() 	{ return numChunksGenerating; }
	public static Pair<Integer, Integer> 	getLastGeneratedChunk() { return lastChunk; }
	
	public static Pair<Integer, Integer> getNextChunk(Pair<Integer, Integer> position) {
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
	
	public static void queueChunk(ChunkGenerator generator, Pair<Integer, Integer> position) {
		generationThreads.execute(new Runnable() {
			@Override
			public void run() {
				ChunkGenerationWorker worker = new ChunkGenerationWorker(generator, position);
				try {
					numChunksGenerating++;
					Chunk c = worker.call();
					finishedChunks.add(c);
					numChunksGenerating--;
					lastChunk = position;
				} catch(Exception e) {
					System.err.println("Error generating chunk: " + e.getMessage());
				}
			}
		});
	}
}

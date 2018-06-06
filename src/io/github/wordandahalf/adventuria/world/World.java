package io.github.wordandahalf.adventuria.world;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.github.wordandahalf.adventuria.engine.physics.Tickable;
import io.github.wordandahalf.adventuria.utils.Pair;
import io.github.wordandahalf.adventuria.world.generator.ChunkGeneratorWorker;
import io.github.wordandahalf.adventuria.world.generator.DefaultGenerator;

public class World implements Tickable {
	public static final int PREGENERATED_CHUNKS = 5;
	
	private DefaultGenerator generator;
	private HashMap<Pair<Integer, Integer>, Chunk> chunks;
	
	private ExecutorService generationThread;
	
	public World() {
		this.generator = new DefaultGenerator();
		this.chunks = new HashMap<>();
		this.generationThread = Executors.newSingleThreadExecutor();
	}
	
	public void generate() {
		if((PREGENERATED_CHUNKS % 2) > 0) {
			int numberOnSides = (int) Math.floor(PREGENERATED_CHUNKS / 2);
			
			HashMap<Pair<Integer, Integer>, Future<Chunk>> workers = new HashMap<>();
			
			for(int x = numberOnSides; x > 0; x--) {
				ChunkGeneratorWorker worker = new ChunkGeneratorWorker(generator, x, 0);
				
				workers.put(new Pair<Integer, Integer>(x, 0), this.generationThread.submit(worker));
			}
			
			for(int x = 0; x > -numberOnSides - 1; x--) {
				ChunkGeneratorWorker worker = new ChunkGeneratorWorker(generator, x, 0);
				
				workers.put(new Pair<Integer, Integer>(x, 0), this.generationThread.submit(worker));
			}
			
			workers.forEach((location, chunk) -> {
				try {
					this.chunks.put(location, chunk.get());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
	}

	public void update() {
		
	}
}

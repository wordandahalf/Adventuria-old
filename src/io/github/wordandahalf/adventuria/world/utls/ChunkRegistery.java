package io.github.wordandahalf.adventuria.world.utls;

import java.util.HashMap;

import io.github.wordandahalf.adventuria.world.Chunk;

public class ChunkRegistery {
	private HashMap<Integer, HashMap<Integer, Chunk>> chunks;
	
	public ChunkRegistery() {
		this.chunks = new HashMap<>();
	}
	
	public Chunk getChunk(int x, int y) {
		return this.chunks.get(x).get(y);
	}
	
	public void setChunk(int x, int y, Chunk chunk) {
		if(!this.chunks.containsKey(x))
			this.chunks.put(x, new HashMap<Integer, Chunk>());
		
		this.chunks.get(x).put(y, chunk);
	}
}

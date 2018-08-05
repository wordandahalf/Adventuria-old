package io.github.wordandahalf.adventuria.world.io;

import java.io.File;
import java.io.IOException;

import io.github.wordandahalf.adventuria.utils.ObjectSerializerUtils;
import io.github.wordandahalf.adventuria.world.Chunk;
import io.github.wordandahalf.adventuria.world.World;

public class AdventuriaWorldSaver {
	public static final File 		SAVES_FOLDER 	= new File(new File("").getAbsolutePath(), "saves");
	public static final String 		CHUNK_FOLDER 	= "chunks";
	
	public static final String		WORLD_DATA_FILE = "level.advl";
	
	public static void load(String name) throws IOException {
		//TODO
	}
	
	public static void save(World world) throws IOException {
		if(!SAVES_FOLDER.exists()) {
			System.out.println("Saves directory does not exist, creating...");
			SAVES_FOLDER.mkdirs();
		}
		
		String worldName = "world-" + System.currentTimeMillis();
		
		File worldFolder = new File(SAVES_FOLDER, worldName);
		
		System.out.println("Saving a world to " + worldFolder.getAbsolutePath());
		
		worldFolder.mkdir();
		
		File chunksFolder = new File(worldFolder, CHUNK_FOLDER);
		chunksFolder.mkdir();
		
		File worldData = new File(worldFolder, WORLD_DATA_FILE);
		worldData.createNewFile();
		
		serializeWorldData(world, worldFolder);
	}
	
	private static void serializeWorldData(World world, File worldFolder) throws IOException {
		System.out.println("Writing data to world file...");
		
		ObjectSerializerUtils.writeToFile(world.getGenerator(), new File(worldFolder, WORLD_DATA_FILE));
		
		for(Chunk c : world.getChunks()) {
			serializeChunk(c, worldFolder);
		}
	}
	
	private static void serializeChunk(Chunk chunk, File worldFolder) throws IOException {
		File chunkFile = new File(new File(worldFolder, CHUNK_FOLDER), chunk.getX() + "." + chunk.getY() + ".advc");
		chunkFile.createNewFile();
		
		ObjectSerializerUtils.writeToFile(chunk, chunkFile);
		
		System.out.println("Wrote chunk at (" + chunk.getX() + ", " + chunk.getY() + ") to " + chunkFile.getAbsolutePath());
	}
}

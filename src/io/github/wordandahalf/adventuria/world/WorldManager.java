package io.github.wordandahalf.adventuria.world;

import java.util.HashMap;

public class WorldManager {
	private static final HashMap<String, World> worlds = new HashMap<>();
	
	private static String currentWorld;
	
	public static World getCurrentWorld() { return worlds.get(currentWorld); }
	
	public static void setCurrentWorld(String world) {
		currentWorld = world;
	}
	
	public static void addWorld(String world) {
		worlds.put(world, new World());
	}
	
	public static void removeWorld(String world) {
		worlds.remove(world);
	}
}

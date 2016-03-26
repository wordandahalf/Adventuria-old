package net.adventuria.level.biome;

import java.util.Random;

public enum Biome {
	PLAINS(0, "Plains"),
	DESERT(1, "Desert"),
	OCEAN(2, "Ocean");
	
	private int id;
	private String name;
	
	private Biome(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static Biome getRandomBiome() {
		return Biome.values()[new Random().nextInt(3)];
	}
}

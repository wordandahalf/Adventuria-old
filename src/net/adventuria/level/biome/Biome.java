package net.adventuria.level.biome;

import java.util.Random;
import java.awt.Color;

public enum Biome {
	PLAINS(0, "Plains", new Color(70, 120, 230)),
	DESERT(1, "Desert", new Color(70, 120, 230)),
	OCEAN(2, "Ocean", new Color(70, 120, 230));
	
	private int id;
	private String name;
	private Color sky;
	
	private Biome(int id, String name, Color skyColor) {
		this.id = id;
		this.name = name;
		this.sky = skyColor;
	}
	
	public int getID() {
		return this.id;
	}
	
	public Color getSkyColor() {
		return sky;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static Biome getRandomBiome() {
		return Biome.values()[new Random().nextInt(3)];
	}
}

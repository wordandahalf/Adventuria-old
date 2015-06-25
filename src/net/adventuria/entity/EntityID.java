package net.adventuria.entity;

public enum EntityID {
	NULL(0, new int[] { -1, -1 }, ""), ITEM(1, new int[] { 0, 0 },
			"Generic Item"), PIG(10, new int[] { 0, 0 }, 10, "Pig"), COW(11,
			new int[] { 0, 0 }, 10, "Cow"), SHEEP(12, new int[] { 0, 0 }, 10,
			"Sheep"), CHICKEN(13, new int[] { 0, 0 }, 5, "Chicken"), PLAYER(
			255, new int[] { 0, 18 }, 20, "Player");

	private int ID = 0;
	private int defaultHealth = 0;

	private int[] textureID = { -1, -1 };

	private String name = "";

	private EntityID(int ID, int[] textureID, String name) {
		this.ID = ID;
		this.textureID = textureID;
		this.name = name;
	}

	private EntityID(int ID, int[] textureID, int defaultHealth, String name) {
		this.ID = ID;
		this.textureID = textureID;
		this.name = name;
		this.defaultHealth = defaultHealth;
	}

	public int getID() {
		return this.ID;
	}

	public int[] getTextureID() {
		return this.textureID;
	}

	public String getName() {
		return this.name;
	}

	public int getDefaultHealth() {
		return this.defaultHealth;
	}
}

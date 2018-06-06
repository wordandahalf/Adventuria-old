package io.github.wordandahalf.adventuria.world.block;

public enum BlockType {
	AIR(0, "", "Air", 0, false),
	DIRT(1, "dirt", "Dirt", 8, true),
	STONE(2, "stone", "Stone", 16, true),
	GRASS(3, "grass", "Grass", 8, true),
	BEDROCK(4, "bedrock", "Bedrock", 255, true),
	WOOD_OAK(5, "wood_oak", "Oak Wood", 12, true),
	LEAF_OAK(6, "leaves_oak", "Oak Leaves", 2, true),
	GRASS_SNOW(7, "grass_snow", "Snowy Grass", 10, true),
	ICE(7, "ice", "Ice", 14, true),
	SAND(11, "sand", "Sand", 6, true),
	ORE_COAL(15, "ore_coal", "Coal Ore", 24, true),
	ORE_IRON(16, "ore_iron", "Iron Ore", 24, true),
	ORE_GOLD(17, "ore_gold", "Gold Ore", 20, true),
	ORE_DIAMOND(18, "ore_diamond", "Diamond Ore", 30, true),
	ORE_RUBY(19, "ore_ruby", "Ruby Ore", 28, true),
	ORE_EMERALD(20, "ore_emerald", "Emerald Ore", 28, true),
	ORE_COPPER(21, "ore_copper", "Copper Ore", 26, true),
	ORE_TIN(22, "ore_tin", "Tin Ore", 24, true),
	ORE_SAPPHIRE(23, "ore_sapphire", "Sapphire Ore", 28, true),
	SOLIDAIR(254, "", "Solid Air", 255, true),
	WATERSOURCE(255, "water_source", "Water Source", 255, 0, false);

	private int ID;
	private int meta;

	private String texture;

	private String name;

	private double hardness;
	private boolean solid;

	private BlockType(int ID, String texture, String name, double hardness, boolean solid) {
		this.ID = ID;
		this.texture = texture;
		this.name = name;
		this.hardness = hardness;
		this.solid = solid;
	}

	private BlockType(int ID, String texture, String name, double hardness, int meta, boolean solid) {
		this.ID = ID;
		this.texture = texture;
		this.name = name;
		this.hardness = hardness;
		this.setMetadata(meta);
		this.solid = solid;
	}

	public boolean isSolid() {
		return solid;
	}

	public int getID() {
		return this.ID;
	}

	public String getTexture() {
		return this.texture;
	}

	public String getName() {
		return this.name;
	}

	public double getHardness() {
		return this.hardness;
	}

	public int getMetadata() {
		return meta;
	}

	public void setMetadata(int meta) {
		this.meta = meta;
	}
}

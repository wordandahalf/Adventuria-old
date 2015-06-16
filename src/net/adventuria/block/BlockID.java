package net.adventuria.block;

public enum BlockID
{
	/**
	 * Hardness:
	 * 255: Unbreakable
	 * 254: Only can be picked up via a bucket
	 * 253: Block comes from a liquid source block; cannot be destroyed
	 * 252: Only can be blown up
	 * 251: Must be destroyed by a liquid block
	 * 1 - 250: Normal destroy time
	 */
	AIR(0, new int[] {-1, -1}, "Air", 0),
	EARTH(1, new int[] {0, 0}, "Dirt", 0),
	STONE(2, new int[] {1, 0}, "Stone", 0),
	GRASS(3, new int[] {3, 0}, "Grass", 0),
	BEDROCK(4, new int[] {4, 0}, "Bedrock", 255),
	WOOD_OAK(5, new int[] {0, 1}, "Oak Wood", 0),
	LEAF_OAK(6, new int[] {0, 2}, "Oak Leaves", 0),
	ORE_COAL(15, new int[] {5, 0}, "Coal Ore", 0),
	ORE_IRON(16, new int[] {6, 0}, "Iron Ore", 0),
	ORE_GOLD(17, new int[] {7, 0}, "Gold Ore", 0),
	ORE_DIAMOND(18, new int[] {8, 0}, "Diamond Ore", 0),
	ORE_RUBY(19, new int[] {9, 0}, "Ruby Ore", 0),
	ORE_EMERALD(20, new int[] {10, 0}, "Emerald Ore", 0),
	SOLIDAIR(254, new int[] {-1, -1}, "Solid Air", 255),
	WATERSOURCE(255, new int[] {7, 1}, "Water Source", 254, 0);
	
	private int ID = 0;
	private int meta = 0;
	
	private int[] textureID = {-1, -1};
	
	private String name = "";
	
	private double hardness = 0;
		
	private BlockID(int ID, int[] textureID, String name, double hardness)
	{
		this.ID = ID;
		this.textureID = textureID;
		this.name = name;
		this.hardness = hardness;
	}
	
	private BlockID(int ID, int[] textureID, String name, double hardness, int meta)
	{
		this.ID = ID;
		this.textureID = textureID;
		this.name = name;
		this.hardness = hardness;
		this.setMetadata(meta);
	}
	
	public int getID()
	{
		return this.ID;
	}
	
	public int[] getTextureID()
	{
		return this.textureID;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public double getHardness()
	{
		return this.hardness;
	}

	public int getMetadata()
	{
		return meta;
	}

	public void setMetadata(int meta)
	{
		this.meta = meta;
	}
}

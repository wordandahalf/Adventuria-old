package net.adventuria.entity;

public enum EntityID
{
	NULL(0, new int[] {-1, -1}, ""),
	ITEM(1, new int[] {0, 0}, "Generic Item"),
	PIG(10, new int[] {0, 0}, "Pig"),
	COW(11, new int[] {0, 0}, "Cow"),
	SHEEP(12, new int[] {0, 0}, "Sheep"),
	CHICKEN(13, new int[] {0, 0}, "Chicken"),
	PLAYER(255, new int[] {0, 18}, "Player");
	
	
	private int ID = 0;
	private int[] textureID = {-1, -1};
	private String name = "";
	
	private EntityID(int ID, int[] textureID, String name)
	{
		this.ID = ID;
		this.textureID = textureID;
		this.name = name;
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
}

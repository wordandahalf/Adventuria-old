package net.adventuria.location;

public class Location
{
	public int y = 0;
	public int x = 0;
	
	public Location(int x, int y)
	{
		this.y = y;
		this.x = x;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
}

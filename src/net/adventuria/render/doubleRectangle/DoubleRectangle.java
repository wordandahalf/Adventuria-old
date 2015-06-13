package net.adventuria.render.doubleRectangle;

import net.adventuria.level.Tile;
import net.adventuria.location.Location;

public class DoubleRectangle
{
  public int type = 0;
	
  public double x;
  public double y;
  public double width;
  public double height;
  
  public DoubleRectangle(int type)
  {
    setBounds(0.0D, 0.0D, 0.0D, 0.0D);
  }
  
  public DoubleRectangle(int type, Location loc)
  {
	if(type == 0)
	{
		setBounds(loc.getX(), loc.getY(), Tile.tileSize, Tile.tileSize * 2);
	}
	else
	{
		setBounds(loc.getX(), loc.getY(), Tile.tileSize * 2, Tile.tileSize);
	}
  }
  
  public void setBounds(int type, Location loc)
  {
    this.x = loc.getX();
    this.y = loc.getY();

    if(type == 0)
    {
    	width = Tile.tileSize;
    	height = Tile.tileSize * 2;
    }
    else
    {
    	width = Tile.tileSize * 2;
    	height = Tile.tileSize;
    }
  }
  
  public void setBounds(Location loc, double width, double height)
  {
    this.x = loc.getX();
    this.y = loc.getY();
    this.width = width;
    this.height = height;
  }
  
  public void setBounds(double x, double y, double width, double height)
  {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
}

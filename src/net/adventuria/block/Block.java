package net.adventuria.block;

	import java.awt.Graphics;
import java.awt.Rectangle;

import net.adventuria.Component;
import net.adventuria.block.BlockID;
import net.adventuria.level.Tile;
import net.adventuria.location.Location;

	public class Block
	  extends Rectangle
	{
	  private static final long serialVersionUID = 1L;
	  
	  private BlockID ID = BlockID.AIR;
	  
	  private int bx = 0;
	  private int by = 0;
	  
	  public Block(Location loc, BlockID ID)
	  {
		this.ID = ID;  
		this.bx = loc.getX();
		this.by = loc.getY();
				
	    setBounds(loc.getX() * Tile.tileSize, loc.getY() * Tile.tileSize, Tile.tileSize, Tile.tileSize);
	    
	    this.ID = ID;
	  }
	  
	  public BlockID getID()
	  {
		  return this.ID;
	  }
	  
	  public void setID(BlockID ID)
	  {
		  this.ID = ID;
	  }
	  
	  public int getBlockX()
	  {
		  return this.bx;
	  }
	  
	  public int getBlockY()
	  {
		  return this.by;
	  }
	  
	  public void Render(Graphics g)
	  {
	    if (this.ID != BlockID.AIR) {
	      g.drawImage(Tile.tileset_terrain, this.x - (int)Component.sX, this.y - (int)Component.sY, this.width + this.x - (int)Component.sX, this.height + this.y - (int)Component.sY, this.ID.getTextureID()[0] * Tile.tileSize, this.ID.getTextureID()[1] * Tile.tileSize, this.ID.getTextureID()[0] * Tile.tileSize + this.width, this.ID.getTextureID()[1] * Tile.tileSize + this.height, null);
	    }
	  }
}

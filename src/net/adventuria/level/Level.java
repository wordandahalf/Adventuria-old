package net.adventuria.level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import net.adventuria.Component;
import net.adventuria.block.Block;
import net.adventuria.block.BlockID;
import net.adventuria.gui.inventory.*;
import net.adventuria.inputs.Mouse;
import net.adventuria.level.chunk.Chunk;
import net.adventuria.location.Location;

public class Level
{
  public static int worldW = 100;
  public static int worldH = 100;
  public Chunk[] Chunks = new Chunk[10];
  public Block[][] Blocks = new Block[worldW][worldH];
  
  public Level()
  {
    for (int x = 0; x < this.Blocks.length; x++) {
      for (int y = 0; y < this.Blocks[0].length; y++) {
        this.Blocks[x][y] = new Block(new Location(x, y), BlockID.AIR);
      }
    }
    Generator.genWorld(this.Blocks);
  }
  
  public void Building(int camX, int camY, int renW, int renH)
  {
	if(Mouse.isLeftButtonClicked())
	{
		if(!Inventory.isOpen)
		{
			if(Mouse.getBlockX() < worldW && Mouse.getBlockY() < worldH)
			{
				if(this.getBlock(Mouse.getBlockX(), Mouse.getBlockY()) != BlockID.AIR)
				{
					if(this.getBlock(Mouse.getBlockX(), Mouse.getBlockY()).getHardness() < 128)
					{
						Component.inventory.addItemToInventory(this.getBlock(Mouse.getBlockX(), Mouse.getBlockY()));
						this.setBlock(BlockID.AIR, Mouse.getBlockX(), Mouse.getBlockY());
					}
				}
			}
		}
	}
	
	else if(Mouse.isRightButtonClicked())
	{
		if(!Inventory.isOpen)
		{
			if(Mouse.getBlockX() < worldW && Mouse.getBlockY() < worldH)
			{
				if(this.getBlock(Mouse.getBlockX(), Mouse.getBlockY()) == BlockID.AIR && Mouse.getBlockX() > -1 && Mouse.getBlockY() > -1 && Component.inventory.getHeldItemCount() >= 2)
				{
					Component.inventory.addItemToInventory(Component.inventory.getHeldItemID(), -1);
					this.setBlock(Component.inventory.getHeldItemID(), Mouse.getBlockX(), Mouse.getBlockY());
				}
				else if(this.getBlock(Mouse.getBlockX(), Mouse.getBlockY()) == BlockID.AIR && Mouse.getBlockX() > -1 && Mouse.getBlockY() > -1 && Component.inventory.getHeldItemCount() == 1)
				{
					this.setBlock(Component.inventory.getHeldItemID(), Mouse.getBlockX(), Mouse.getBlockY());
					Component.inventory.setItemInventory(BlockID.AIR, 0, Inventory.selected);
				}
			}
		}
	}
  }
  
  public void updateWater()
  {
    for (int y = 0; y < this.Blocks.length; y++) {
      for (int x = 0; x < this.Blocks[0].length; x++) {
          if (this.Blocks[x][y].getID() == BlockID.WATERSOURCE) {
            if (this.Blocks[x][(y + 1)].getID() == BlockID.AIR) {
              this.Blocks[x][(y + 1)].setID(BlockID.WATERSOURCE);
            } else if (this.Blocks[(x + 1)][y].getID() == BlockID.AIR) {
              this.Blocks[(x + 1)][y].setID(BlockID.WATERSOURCE);
            } else if (this.Blocks[(x - 1)][y].getID() == BlockID.AIR) {
              this.Blocks[(x - 1)][y].setID(BlockID.WATERSOURCE);
            }
        }
      }
    }
  }
  
  public BlockID getBlock(int x, int y)
  {
	  if(x > -1 && y > -1)
	  {
		  return this.Blocks[x][y].getID();
	  }
	  else
	  {
		  return BlockID.AIR;
	  }
  }
  
  public void setBlock(BlockID blk, int x, int y)
  {
	  if(x > -1 && y > -1)
	  {
		  this.Blocks[x][y].setID(blk);
	  }  
  }
  
  public void Tick(int camX, int camY, int renW, int renH)
  {
    Building(camX, camY, renW, renH);
    
    updateWater();
  }
  
  public void Render(Graphics g, int camX, int camY, int renW, int renH)
  {
    for (int x = camX / Block.tileSize; x < camX / Block.tileSize + renW; x++) {
      for (int y = camY / Block.tileSize; y < camY / Block.tileSize + renH; y++) {
        if ((x >= 0) && (y >= 0) && (x < worldW) && (y < worldH))
        {
          this.Blocks[x][y].Render(g);
          if ((this.Blocks[x][y].contains(new Point(Mouse.getX() / Component.pixelSize + (int)Component.sX, Mouse.getY() / Component.pixelSize + (int)Component.sY))) && (this.Blocks[x][y].getID() != BlockID.AIR) && (!Inventory.isOpen))
        	  
          {
            g.setColor(new Color(255, 255, 255, 64));
            g.fillRect(this.Blocks[x][y].x - camX, this.Blocks[x][y].y - camY, this.Blocks[x][y].width, this.Blocks[x][y].height);
          }
        }
      }
    }
  }
}

package net.adventuria.level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import net.adventuria.Component;
import net.adventuria.block.Block;
import net.adventuria.block.BlockID;
import net.adventuria.gui.inventory.*;
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
    if ((Component.isLeftMouseButton) || (Component.isRightMouseButton)) {
      for (int x = camX / Block.tileSize; x < camX / Block.tileSize + renW; x++) {
        for (int y = camY / Block.tileSize; y < camY / Block.tileSize + renH; y++) {
          if ((x >= 0) && (y >= 0) && (x < worldW) && (y < worldH)) {
            if ((this.Blocks[x][y].contains(new Point(Component.mouse.x / Component.pixelSize + (int)Component.sX, Component.mouse.y / Component.pixelSize + (int)Component.sY))) && (this.Blocks[x][y].getID().getHardness() < 128))
            {
              BlockID sid = Inventory.invHotBar[Inventory.selected].ID;
              if ((Component.isLeftMouseButton) && (!Inventory.isOpen))
              {
                if (this.Blocks[x][y].getID() == BlockID.AIR) {
                  break;
                }
                if (!Component.inventory.isOpenSlot(this.Blocks[x][y].getID())) {
                  break;
                }
                if ((this.Blocks[x][y].getID() != BlockID.AIR) && (this.Blocks[x][y].getID() != BlockID.WATERSOURCE)) {
                  Component.inventory.addItemToInventory(this.Blocks[x][y].getID());
                }
                this.Blocks[x][y].setID(BlockID.AIR);
                
                break;
              }
              if ((!Component.isRightMouseButton) || (Inventory.isOpen)) {
                break;
              }
              if (((sid == BlockID.AIR) || (this.Blocks[x][y].getID() != BlockID.AIR)) || (this.Blocks[x][y].getID() != BlockID.WATERSOURCE)) {
                break;
              }
              if (Inventory.invHotBar[Inventory.selected].Count == 1)
              {
                this.Blocks[x][y].setID(sid);
                Inventory.invHotBar[Inventory.selected].Count = 0;
                Inventory.invHotBar[Inventory.selected].ID = BlockID.AIR;
              }
              if (Inventory.invHotBar[Inventory.selected].Count <= 1) {
                break;
              }
              this.Blocks[x][y].setID(sid);
              Inventory.invHotBar[Inventory.selected].Count -= 1;
              
              break;
            }
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
          if ((this.Blocks[x][y].contains(new Point(Component.mouse.x / Component.pixelSize + (int)Component.sX, Component.mouse.y / Component.pixelSize + (int)Component.sY))) && (this.Blocks[x][y].getID() != BlockID.AIR) && (!Inventory.isOpen))
          {
            g.setColor(new Color(255, 255, 255, 64));
            g.fillRect(this.Blocks[x][y].x - camX, this.Blocks[x][y].y - camY, this.Blocks[x][y].width, this.Blocks[x][y].height);
          }
        }
      }
    }
  }
}

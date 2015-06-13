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
  public Block[][] Block = new Block[worldW][worldH];
  
  public Level()
  {
    for (int x = 0; x < this.Block.length; x++) {
      for (int y = 0; y < this.Block[0].length; y++) {
        this.Block[x][y] = new Block(new Location(x, y), BlockID.AIR);
      }
    }
    Generator.genWorld(this.Block);
  }
  
  public void Building(int camX, int camY, int renW, int renH)
  {
    if ((Component.isLeftMouseButton) || (Component.isRightMouseButton)) {
      for (int x = camX / Tile.tileSize; x < camX / Tile.tileSize + renW; x++) {
        for (int y = camY / Tile.tileSize; y < camY / Tile.tileSize + renH; y++) {
          if ((x >= 0) && (y >= 0) && (x < worldW) && (y < worldH)) {
            if ((this.Block[x][y].contains(new Point(Component.mouse.x / Component.pixelSize + (int)Component.sX, Component.mouse.y / Component.pixelSize + (int)Component.sY))) && (this.Block[x][y].getID().getHardness() < 128))
            {
              BlockID sid = Inventory.invHotBar[Inventory.selected].ID;
              if ((Component.isLeftMouseButton) && (!Inventory.isOpen))
              {
                if (this.Block[x][y].getID() == BlockID.AIR) {
                  break;
                }
                if (!Component.inventory.isOpenSlot(this.Block[x][y].getID())) {
                  break;
                }
                if ((this.Block[x][y].getID() != BlockID.AIR) && (this.Block[x][y].getID() != BlockID.WATERSOURCE)) {
                  Component.inventory.addItemToInventory(this.Block[x][y].getID());
                }
                this.Block[x][y].setID(BlockID.AIR);
                
                break;
              }
              if ((!Component.isRightMouseButton) || (Inventory.isOpen)) {
                break;
              }
              if (((sid == BlockID.AIR) || (this.Block[x][y].getID() != BlockID.AIR)) || (this.Block[x][y].getID() != BlockID.WATERSOURCE)) {
                break;
              }
              if (Inventory.invHotBar[Inventory.selected].Count == 1)
              {
                this.Block[x][y].setID(sid);
                Inventory.invHotBar[Inventory.selected].Count = 0;
                Inventory.invHotBar[Inventory.selected].ID = BlockID.AIR;
              }
              if (Inventory.invHotBar[Inventory.selected].Count <= 1) {
                break;
              }
              this.Block[x][y].setID(sid);
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
    for (int y = 0; y < this.Block.length; y++) {
      for (int x = 0; x < this.Block[0].length; x++) {
          if (this.Block[x][y].getID() == BlockID.WATERSOURCE) {
            if (this.Block[x][(y + 1)].getID() == BlockID.AIR) {
              this.Block[x][(y + 1)].setID(BlockID.WATERSOURCE);
            } else if (this.Block[(x + 1)][y].getID() == BlockID.AIR) {
              this.Block[(x + 1)][y].setID(BlockID.WATERSOURCE);
            } else if (this.Block[(x - 1)][y].getID() == BlockID.AIR) {
              this.Block[(x - 1)][y].setID(BlockID.WATERSOURCE);
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
    for (int x = camX / Tile.tileSize; x < camX / Tile.tileSize + renW; x++) {
      for (int y = camY / Tile.tileSize; y < camY / Tile.tileSize + renH; y++) {
        if ((x >= 0) && (y >= 0) && (x < worldW) && (y < worldH))
        {
          this.Block[x][y].Render(g);
          if ((this.Block[x][y].contains(new Point(Component.mouse.x / Component.pixelSize + (int)Component.sX, Component.mouse.y / Component.pixelSize + (int)Component.sY))) && (this.Block[x][y].getID() != BlockID.AIR) && (!Inventory.isOpen))
          {
            g.setColor(new Color(255, 255, 255, 64));
            g.fillRect(this.Block[x][y].x - camX, this.Block[x][y].y - camY, this.Block[x][y].width, this.Block[x][y].height);
          }
        }
      }
    }
  }
}

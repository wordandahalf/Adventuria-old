package net.adventuria.gui.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import net.adventuria.block.BlockID;
import net.adventuria.level.Tile;

public class Cell
  extends Rectangle
{
  private static final long serialVersionUID = 1L;
  public BlockID ID = BlockID.AIR;
  public int Count = 0;
  public boolean isSelected = false;
  
  public Cell(Rectangle size, BlockID ID, int Count)
  {
    setBounds(size);
    
    this.ID = ID;
    
    this.Count = Count;
  }
  
  public void Render(Graphics g, boolean isSelected)
  {
    g.setColor(Color.WHITE);
    
    g.drawImage(Tile.tile_cell, this.x, this.y, this.width, this.height, null);
    if (this.ID != BlockID.AIR)
    {
      g.drawImage(Tile.tileset_terrain, this.x + Inventory.invItemBorder, this.y + Inventory.invItemBorder, this.x - Inventory.invItemBorder + this.width, this.y - Inventory.invItemBorder + this.height, this.ID.getTextureID()[0] * Tile.tileSize, this.ID.getTextureID()[1] * Tile.tileSize, this.ID.getTextureID()[0] * Tile.tileSize + this.width - 5, this.ID.getTextureID()[1] * Tile.tileSize + this.height - 5, null);
      g.drawString("" + this.Count, this.x + 7, this.y + 20);
    }
    if (isSelected) {
      g.drawImage(Tile.tile_select, this.x - 1, this.y - 1, this.width + 2, this.height + 2, null);
    }
  }
}

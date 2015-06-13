package net.adventuria.gui.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import net.adventuria.Component;
import net.adventuria.block.BlockID;
import net.adventuria.level.Tile;

public class Inventory
{
  public static BlockID currentHeldItemID = BlockID.AIR;
  public static int currentHeldItemCount = 0;
  public static int invLength = 8;
  public static int invCellSize = 25;
  public static int invCellSpace = 4;
  public static int invBorderSpace = 4;
  public static int invItemBorder = 4;
  public static int invHeight = 3;
  public static Cell[] invHotBar = new Cell[invLength];
  public static Cell[] invBag = new Cell[invLength * invHeight];
  public static boolean isOpen = false;
  public static int selected = 0;
  
  public Inventory()
  {
    for (int i = 0; i < invHotBar.length; i++) {
      invHotBar[i] = new Cell(new Rectangle(Component.pixel.width / 2 - invLength * (invCellSize + invCellSpace) / 2 + i * (invCellSize + invCellSpace), Component.pixel.height - (invCellSize + invBorderSpace), invCellSize, invCellSize), BlockID.AIR, 0);
    }
    int x = 0;int y = 0;
    for (int i = 0; i < invBag.length; i++)
    {
      invBag[i] = new Cell(new Rectangle(Component.pixel.width / 2 - invLength * (invCellSize + invCellSpace) / 2 + x * (invCellSize + invCellSpace), Component.pixel.height - (invCellSize + invBorderSpace) - invHeight * (invCellSize + invCellSpace) + y * (invCellSize + invCellSpace) - (invCellSize + invCellSpace), invCellSize, invCellSize), BlockID.AIR, 0);
      
      x++;
      if (x == invLength)
      {
        x = 0;
        y++;
      }
    }
  }
  
  public void Render(Graphics g)
  {
    renderHotBar(g);
    if (isOpen) {
      renderInvBag(g);
    }
    if ((currentHeldItemID != BlockID.AIR) && (currentHeldItemCount != 0))
    {
      g.drawImage(Tile.tileset_terrain, Component.mouse.x / Component.pixelSize, Component.mouse.y / Component.pixelSize, Tile.tileSize + Component.mouse.x / Component.pixelSize, Tile.tileSize + Component.mouse.y / Component.pixelSize, currentHeldItemID.getTextureID()[0] * Tile.tileSize, currentHeldItemID.getTextureID()[1] * Tile.tileSize, currentHeldItemID.getTextureID()[0] * Tile.tileSize + Tile.tileSize, currentHeldItemID.getTextureID()[1] * Tile.tileSize + Tile.tileSize, null);
      g.drawString("" + currentHeldItemCount, Component.mouse.x / Component.pixelSize + 3, Component.mouse.y / Component.pixelSize + 19);
    }
  }
  
  public void renderHotBar(Graphics g)
  {
    for (int i = 0; i < invHotBar.length; i++)
    {
      boolean isSelected = false;
      if ((i == selected) && (!isOpen)) {
        isSelected = true;
      }
      if ((invHotBar[i].contains(new Point(Component.mouse.x / Component.pixelSize, Component.mouse.y / Component.pixelSize))) && (isOpen))
      {
        g.setColor(new Color(255, 255, 255, 6));
        g.fillRect(invHotBar[i].x, invHotBar[i].y, invHotBar[i].width, invHotBar[i].height);
      }
      invHotBar[i].Render(g, isSelected);
    }
  }
  
  public void renderInvBag(Graphics g)
  {
    g.setColor(Color.WHITE);
    
    g.drawString("Inventory", Component.pixel.width / 2 - invLength * (invCellSize + invCellSpace) / 2 + (invCellSize + invCellSpace) - (invCellSize + 2 * invCellSpace), Component.pixel.height - (invCellSize + invBorderSpace) - invHeight * (invCellSize + invCellSpace) + (invCellSize + invCellSpace) - (invCellSize + invCellSpace) - (invCellSize + 2 * invCellSpace));
    for (int i = 0; i < invBag.length; i++)
    {
      invBag[i].Render(g, false);
      renderHotBar(g);
      if (invBag[i].contains(new Point(Component.mouse.x / Component.pixelSize, Component.mouse.y / Component.pixelSize)))
      {
        g.setColor(new Color(255, 255, 255, 64));
        g.fillRect(invBag[i].x, invBag[i].y, invBag[i].width, invBag[i].height);
      }
    }
  }
  
  public void addItemToInventory(BlockID ID)
  {
    boolean hasAdded = false;
    if (!isFull())
    {
      if ((invHotBar[selected].ID == BlockID.AIR) && (!hasAdded) && (invHotBar[selected].Count < 64))
      {
        invHotBar[selected].ID = ID;
        invHotBar[selected].Count = 1;
        hasAdded = true;
        return;
      }
      if ((invHotBar[selected].ID != BlockID.AIR) && (invHotBar[selected].ID != ID) && (!hasAdded))
      {
        for (int i = 0; i < invHotBar.length; i++)
        {
          if ((invHotBar[i].ID == BlockID.AIR) && (i != selected) && (!hasAdded) && (invHotBar[i].Count < 64))
          {
            invHotBar[i].ID = ID;
            invHotBar[i].Count = 1;
            hasAdded = true;
            return;
          }
          if ((invHotBar[i].ID == ID) && (i != selected) && (!hasAdded) && (invHotBar[i].Count < 64))
          {
            invHotBar[i].Count += 1;
            hasAdded = true;
            return;
          }
        }
      }
      else if ((invHotBar[selected].ID == ID) && (!hasAdded) && (invHotBar[selected].Count < 64))
      {
        invHotBar[selected].Count += 1;
        hasAdded = true;
        return;
      }
    }
  }
  
  public boolean isFull()
  {
    int hits = 0;
    
    boolean isFull = false;
    for (int a = 0; a < invLength; a++) {
      if ((invHotBar[a].ID != BlockID.AIR) && (invHotBar[a].Count >= 64)) {
        hits++;
      }
    }
    if (hits == invLength) {
      isFull = true;
    }
    return isFull;
  }
  
  public boolean isOpenSlot(BlockID ID)
  {
    boolean isOpenSlot = false;
    if (((invHotBar[selected].ID == ID) && (invHotBar[selected].Count < 64)) || (invHotBar[selected].ID == BlockID.AIR)) {
      isOpenSlot = true;
    } else {
      for (int i = 0; i < invLength; i++) {
        if (((invHotBar[i].ID == ID) && (invHotBar[i].Count < 64)) || (invHotBar[i].ID == BlockID.AIR)) {
          isOpenSlot = true;
        }
      }
    }
    return isOpenSlot;
  }
}

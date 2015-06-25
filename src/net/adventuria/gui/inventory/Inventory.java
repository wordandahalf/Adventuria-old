package net.adventuria.gui.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import net.adventuria.Component;
import net.adventuria.assets.AssetManager;
import net.adventuria.block.BlockID;
import net.adventuria.block.Block;
import net.adventuria.inputs.Mouse;

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
    if (isOpen)
    {
      renderInvBag(g);
      renderHotBar(g);
    }
    else
    {
        renderHotBar(g);    	
    }
    
    if ((currentHeldItemID != BlockID.AIR) && (currentHeldItemCount != 0))
    {
      g.setColor(new Color(255, 255, 255, 255));
      g.drawImage(AssetManager.tileset_terrain, Mouse.getX() / Component.pixelSize, Mouse.getY() / Component.pixelSize, Block.tileSize + Mouse.getX() / Component.pixelSize, Block.tileSize + Mouse.getY() / Component.pixelSize, currentHeldItemID.getTextureID()[0] * Block.tileSize, currentHeldItemID.getTextureID()[1] * Block.tileSize, currentHeldItemID.getTextureID()[0] * Block.tileSize + Block.tileSize, currentHeldItemID.getTextureID()[1] * Block.tileSize + Block.tileSize, null);
      g.drawString("" + currentHeldItemCount, Mouse.getX() / Component.pixelSize + 3, Mouse.getY() / Component.pixelSize + 19);
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
      if ((invHotBar[i].contains(new Point(Mouse.getX() / Component.pixelSize, Mouse.getY() / Component.pixelSize))) && (isOpen))
      {;
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
      if (invBag[i].contains(new Point(Mouse.getX() / Component.pixelSize, Mouse.getY() / Component.pixelSize)))
      {
        g.setColor(new Color(255, 255, 255, 64));
        g.fillRect(invBag[i].x, invBag[i].y, invBag[i].width, invBag[i].height);
      }
    }
  }
  
  public boolean addItemToInventory(BlockID ID)
  {	  
	  boolean didAdd = false;
	  
	  if(!this.isFull())
	  {
		  if(findOpenSlot(ID) > -1)
		  {
			  if(findOpenSlot(ID) - 8 >= 0)
			  {
				  if(invBag[findOpenSlot(ID) - 8].ID == BlockID.AIR)
				  {
					  invBag[findOpenSlot(ID) - 8].ID = ID;
					  invBag[findOpenSlot(ID) - 8].Count = 1;
					  didAdd = true;
				  }				  
				  else if(invBag[findOpenSlot(ID) - 8].Count <= 63 && invBag[findOpenSlot(ID) - 8].ID == ID)
				  {
					  invBag[findOpenSlot(ID) - 8].Count++;
					  didAdd = true;
				  }
			  }
			  else
			  {
				  if(invHotBar[findOpenSlot(ID)].ID == BlockID.AIR)
				  {
					  invHotBar[findOpenSlot(ID)].ID = ID;
					  invHotBar[findOpenSlot(ID)].Count = 1;
					  didAdd = true;
				  }
				  
				  else if(invHotBar[findOpenSlot(ID)].Count <= 63 && invHotBar[findOpenSlot(ID)].ID == ID)
				  {
					  invHotBar[findOpenSlot(ID)].Count++;
					  didAdd = true;
				  }
			  }
		  }
	  }
	  
	  return didAdd;
  }
  
  public boolean addItemToInventory(BlockID ID, int Count)
  {
	  boolean didAdd = false;
	  
	  if(!this.isFull())
	  {
		  if(findOpenSlot(ID) > -1)
		  {
			  if(findOpenSlot(ID) - 8 >= 0)
			  {
				  if(invBag[findOpenSlot(ID) - 8].ID == BlockID.AIR)
				  {
					  invBag[findOpenSlot(ID) - 8].ID = ID;
					  invBag[findOpenSlot(ID) - 8].Count += Count;
					  didAdd = true;
				  }				  
				  else if(invBag[findOpenSlot(ID) - 8].Count + Count <= 63 && invBag[findOpenSlot(ID) - 8].ID == ID)
				  {
					  invBag[findOpenSlot(ID) - 8].Count += Count;
					  didAdd = true;
				  }
			  }
			  else
			  {
				  if(invHotBar[findOpenSlot(ID)].ID == BlockID.AIR)
				  {
					  invHotBar[findOpenSlot(ID)].ID = ID;
					  invHotBar[findOpenSlot(ID)].Count += Count;
					  didAdd = true;
				  }
				  
				  else if(invHotBar[findOpenSlot(ID)].Count + Count <= 63 && invHotBar[findOpenSlot(ID)].ID == ID)
				  {
					  invHotBar[findOpenSlot(ID)].Count += Count;
					  didAdd = true;
				  }
			  }
		  }
	  }
	  
	  return didAdd;
  }
  
  public void setItemInventory(BlockID ID, int Count, int index)
  {
	  if(Count < 1 && ID != BlockID.AIR) {
		  setItemInventory(BlockID.AIR, 0, index);
		  return;
	  }
	  if(index < 8)
	  {
		  invHotBar[index].ID = ID;
		  invHotBar[index].Count = Count;
	  }
	  else
	  {
		  invBag[index - 8].ID = ID;
		  invBag[index - 8].Count = Count;
	  }
  }
  
  public boolean isFull()
  {
    int hits = 0;
    
    for (int a = 0; a < invLength; a++)
    {
      if ((invHotBar[a].ID != BlockID.AIR) && (invHotBar[a].Count >= 64))
      {
        hits++;
      }
    }
    
    return hits == invLength;
  }
  
  public int findOpenSlot(BlockID ID)
  {
	  int slot = -1;
	  
	  for(int i = 0; i < invHotBar.length; i++)
	  {
		  if(invHotBar[i].ID == BlockID.AIR || (invHotBar[i].ID == ID && invHotBar[i].Count < 64))
		  {
			  slot = i;
			  break;
		  }
	  }
	  
	  if(slot == -1)
	  {
		  for(int i = 0; i < invBag.length; i++)
		  {
			  if(invBag[i].ID == BlockID.AIR || (invBag[i].ID == ID && invBag[i].Count < 64))
			  {
				  slot = i + 8;
				  break;
			  }
		  }
	  }
	  
	  return slot;
  }
  
  public BlockID getHeldItemID()
  {
	  return invHotBar[selected].ID;
  }
  
  public int getHeldItemCount()
  {
	  return invHotBar[selected].Count;
  }
}

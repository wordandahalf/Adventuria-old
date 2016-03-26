package net.adventuria.gui.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import net.adventuria.Component;
import net.adventuria.assets.AssetManager;
import net.adventuria.block.BlockType;
import net.adventuria.inputs.Mouse;

public class Inventory {
	public static BlockType currentHeldItemID = BlockType.AIR;
	public static int currentHeldItemCount = 0;
	public static int invLength = 8;
	public static int invCellSize = 24;
	public static int invCellSpace = 4;
	public static int invBorderSpace = 4;
	public static int invItemBorder = 4;
	public static int invHeight = 3;
	public static Cell[] invHotBar = new Cell[invLength];
	public static Cell[] invBag = new Cell[invLength * invHeight];
	public static Cell[] craftingGrid = new Cell[4];
	public static Cell craftingOutput;
	public static boolean isOpen = false;
	public static int selected = 0;

	public Inventory() {
		
		for (int i = 0; i < invHotBar.length; i++)
		{
			invHotBar[i] = new Cell(new Rectangle(Component.pixel.width / 2 - invLength * (invCellSize + invCellSpace) / 2 + i * (invCellSize + invCellSpace), Component.pixel.height - (invCellSize + invBorderSpace), invCellSize, invCellSize), BlockType.AIR, 0);
		}
		
		int x = 0, y = 0;
		
		for(int i = 0; i < invBag.length; i++)
		{
			invBag[i] = new Cell(new Rectangle(Component.pixel.width / 2 - invLength * (invCellSize + invCellSpace) / 2 + x * (invCellSize + invCellSpace), Component.pixel.height - (invCellSize + invBorderSpace) - invHeight * (invCellSize + invCellSpace) + y * (invCellSize + invCellSpace) - (invCellSize + invCellSpace), invCellSize, invCellSize), BlockType.AIR, 0);

			x++;
			if (x == invLength)
			{
				x = 0;
				y++;
			}
		}
		
		for(int i = 0; i < craftingGrid.length; i++)
		{
			craftingGrid[i] = new Cell(new Rectangle((Component.pixel.width / 2 - invLength * (invCellSize + invCellSpace) / 2 + (i == 0 ? 4 : i == 1 ? 5 : i == 2 ? 4 : i == 3 ? 5 : 4) * (invCellSize + invCellSpace)), (Component.pixel.height - (invCellSize + invBorderSpace) - invHeight * (invCellSize + invCellSpace) + (i < 2 ? 0 : 1) * (invCellSize + invCellSpace) - (invCellSize + invCellSpace)) - 74, invCellSize, invCellSize), BlockType.AIR, 0);
		}
		
		craftingOutput = new Cell(new Rectangle((Component.pixel.width / 2 - invLength * (invCellSize + invCellSpace) / 2 + 7 * (invCellSize + invCellSpace)), (Component.pixel.height - (invCellSize + invBorderSpace) - invHeight * (invCellSize + invCellSpace) + 2 * (invCellSize + invCellSpace) - (invCellSize + invCellSpace)) - 116, invCellSize, invCellSize), BlockType.AIR, 0);
	}

	public void Render(Graphics g) {
		if (isOpen) {
			renderInvBag(g);
		}
		renderHotBar(g);

		if ((currentHeldItemID != BlockType.AIR) && (currentHeldItemCount != 0)) {
			if (isOpen) {
				g.setColor(new Color(255, 255, 255, 255));
				
				g.drawImage(AssetManager.getTexture(currentHeldItemID.getTextureAlias()), Mouse.getX() / Component.pixelSize, Mouse.getY() / Component.pixelSize, null);
				g.drawString("" + currentHeldItemCount, Mouse.getX() / Component.pixelSize + 3, Mouse.getY() / Component.pixelSize + 16);
			}
		}
	}

	public void renderHotBar(Graphics g) {
		for (int i = 0; i < invHotBar.length; i++) {
			boolean isSelected = false;
			if ((i == selected) && (!isOpen)) {
				isSelected = true;
			}
			invHotBar[i].Render(g, isSelected);
			if ((invHotBar[i].contains(new Point(Mouse.getX() / Component.pixelSize, Mouse.getY() / Component.pixelSize))) && (isOpen)) {
				g.setColor(new Color(255, 255, 255, 64));
				g.fillRect(invHotBar[i].x, invHotBar[i].y, invHotBar[i].width, invHotBar[i].height);
			}
		}
	}

	public void renderInvBag(Graphics g) {
		g.setColor(Color.WHITE);

		g.drawString("Inventory", Component.pixel.width / 2 - invLength * (invCellSize + invCellSpace) / 2 + (invCellSize + invCellSpace) - (invCellSize + 2 * invCellSpace), Component.pixel.height - (invCellSize + invBorderSpace) - invHeight * (invCellSize + invCellSpace) + (invCellSize + invCellSpace) - (invCellSize + invCellSpace) - (invCellSize + 2 * invCellSpace));
		for (int i = 0; i < invBag.length; i++) {
			invBag[i].Render(g, false);
			if (invBag[i].contains(new Point(Mouse.getX() / Component.pixelSize, Mouse.getY() / Component.pixelSize))) {
				g.setColor(new Color(255, 255, 255, 64));
				g.fillRect(invBag[i].x, invBag[i].y, invBag[i].width, invBag[i].height);
			}
		}
		
		for(int i = 0; i < craftingGrid.length; i++)
		{
			craftingGrid[i].Render(g, false);
			if (craftingGrid[i].contains(new Point(Mouse.getX() / Component.pixelSize, Mouse.getY() / Component.pixelSize))) {
				g.setColor(new Color(255, 255, 255, 64));
				g.fillRect(craftingGrid[i].x, craftingGrid[i].y, craftingGrid[i].width, craftingGrid[i].height);
			}
		}
		
		craftingOutput.Render(g, false);
		
		if(craftingOutput.contains(new Point(Mouse.getX() / Component.pixelSize, Mouse.getY() / Component.pixelSize)))
		{
			g.setColor(new Color(255, 255, 255, 64));
			g.fillRect(craftingOutput.x, craftingOutput.y, craftingOutput.width, craftingOutput.height);
		}
	}

	public boolean addItemToInventory(BlockType ID) {
		boolean didAdd = false;

		if (!this.isFull()) {
			if (findOpenSlot(ID) > -1) {
				if (findOpenSlot(ID) - 8 >= 0) {
					if (invBag[findOpenSlot(ID) - 8].ID == BlockType.AIR) {
						invBag[findOpenSlot(ID) - 8].ID = ID;
						invBag[findOpenSlot(ID) - 8].Count = 1;
						didAdd = true;
					} else if (invBag[findOpenSlot(ID) - 8].Count <= 63 && invBag[findOpenSlot(ID) - 8].ID == ID) {
						invBag[findOpenSlot(ID) - 8].Count++;
						didAdd = true;
					}
				} else {
					if (invHotBar[findOpenSlot(ID)].ID == BlockType.AIR) {
						invHotBar[findOpenSlot(ID)].ID = ID;
						invHotBar[findOpenSlot(ID)].Count = 1;
						didAdd = true;
					}

					else if (invHotBar[findOpenSlot(ID)].Count <= 63 && invHotBar[findOpenSlot(ID)].ID == ID) {
						invHotBar[findOpenSlot(ID)].Count++;
						didAdd = true;
					}
				}
			}
		}

		return didAdd;
	}

	public boolean addItemToInventory(BlockType ID, int Count) {
		boolean didAdd = false;

		if (!this.isFull()) {
			if (findOpenSlot(ID) > -1) {
				if (findOpenSlot(ID) - 8 >= 0) {
					if (invBag[findOpenSlot(ID) - 8].ID == BlockType.AIR) {
						invBag[findOpenSlot(ID) - 8].ID = ID;
						invBag[findOpenSlot(ID) - 8].Count += Count;
						didAdd = true;
					} else if (invBag[findOpenSlot(ID) - 8].Count + Count <= 63 && invBag[findOpenSlot(ID) - 8].ID == ID) {
						invBag[findOpenSlot(ID) - 8].Count += Count;
						didAdd = true;
					}
				} else {
					if (invHotBar[findOpenSlot(ID)].ID == BlockType.AIR) {
						invHotBar[findOpenSlot(ID)].ID = ID;
						invHotBar[findOpenSlot(ID)].Count += Count;
						didAdd = true;
					}

					else if (invHotBar[findOpenSlot(ID)].Count + Count <= 63 && invHotBar[findOpenSlot(ID)].ID == ID) {
						invHotBar[findOpenSlot(ID)].Count += Count;
						didAdd = true;
					}
				}
			}
		}

		return didAdd;
	}

	public void setItemInventory(BlockType ID, int Count, int index) {
		if (Count < 1 && ID != BlockType.AIR) {
			setItemInventory(BlockType.AIR, 0, index);
			return;
		}
		if (index < 8) {
			invHotBar[index].ID = ID;
			invHotBar[index].Count = Count;
		} else {
			invBag[index - 8].ID = ID;
			invBag[index - 8].Count = Count;
		}
	}

	public boolean isFull() {
		int hits = 0;

		for (int a = 0; a < invLength; a++) {
			if ((invHotBar[a].ID != BlockType.AIR) && (invHotBar[a].Count >= 64)) {
				hits++;
			}
		}

		return hits == invLength;
	}

	public int findOpenSlot(BlockType ID) {
		int slot = -1;

		for (int i = 0; i < invHotBar.length; i++) {
			if (invHotBar[i].ID == BlockType.AIR || (invHotBar[i].ID == ID && invHotBar[i].Count < 64)) {
				slot = i;
				break;
			}
		}

		if (slot == -1) {
			for (int i = 0; i < invBag.length; i++) {
				if (invBag[i].ID == BlockType.AIR || (invBag[i].ID == ID && invBag[i].Count < 64)) {
					slot = i + 8;
					break;
				}
			}
		}

		return slot;
	}

	public BlockType getHeldItemID() {
		return invHotBar[selected].ID;
	}

	public int getHeldItemCount() {
		return invHotBar[selected].Count;
	}
}

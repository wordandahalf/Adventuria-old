package net.adventuria.gui.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import net.adventuria.Component;
import net.adventuria.assets.AssetManager;
import net.adventuria.block.*;

public class Cell extends Rectangle {
	private static final long serialVersionUID = 1L;
	public BlockType ID = BlockType.AIR;
	public int Count = 0;
	public boolean isSelected = false;

	public Cell(Rectangle size, BlockType ID, int Count) {
		setBounds(size);

		this.ID = ID;

		this.Count = Count;
	}

	public void Render(Graphics g, boolean isSelected) {
		g.setColor(Color.WHITE);

		g.drawImage(AssetManager.getTexture("gui_inventory_cell"), this.x, this.y, this.width, this.height, null);
		if (this.ID != BlockType.AIR) {
			g.drawImage(AssetManager.getTexture(this.ID.getTextureAlias()), this.x + 2, this.y + 2, null);
			
			g.drawString("" + this.Count, this.x + 7, this.y + 20);
		}
		if (isSelected) {
			g.drawImage(AssetManager.getTexture("gui_inventory_select"), this.x - 1, this.y - 1, this.width + 2, this.height + 2, null);
			if (ID != BlockType.AIR)
				g.drawString(ID.getName(), (Component.pixel.width / 2) - ID.getName().length() * 4, 16);
		}
	}
}

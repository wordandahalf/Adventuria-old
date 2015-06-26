package net.adventuria.block;

import java.awt.Graphics;
import java.awt.Rectangle;

import net.adventuria.Component;
import net.adventuria.assets.AssetManager;
import net.adventuria.block.BlockID;
import net.adventuria.location.Location;

public class Block extends Rectangle {
	public static int tileSize = 20;

	private static final long serialVersionUID = 1L;

	private BlockID ID = BlockID.AIR;

	private int bx = 0;
	private int by = 0;
	private double hardness = 0;

	public Block(Location loc, BlockID ID) {
		this.ID = ID;
		this.bx = loc.getX();
		this.by = loc.getY();
		setBounds(loc.getX() * tileSize, loc.getY() * tileSize, tileSize, tileSize);

		this.ID = ID;
	}

	public boolean isSolid() {
		return ID.isSolid();
	}

	public void damage(double strength) {
		hardness -= strength;
	}

	public double getHardness() {
		return hardness;
	}

	public BlockID getID() {
		return this.ID;
	}

	public void setID(BlockID ID) {
		this.ID = ID;
		hardness = ID.getHardness();
	}

	public int getBlockX() {
		return this.bx;
	}

	public int getBlockY() {
		return this.by;
	}

	public void Render(Graphics g) {
		if (this.ID != BlockID.AIR) {
			g.drawImage(AssetManager.tileset_terrain, this.x - (int) Component.sX, this.y - (int) Component.sY, this.width + this.x - (int) Component.sX, this.height + this.y - (int) Component.sY, this.ID.getTextureID()[0] * Block.tileSize, this.ID.getTextureID()[1] * Block.tileSize, this.ID.getTextureID()[0] * Block.tileSize + this.width, this.ID.getTextureID()[1] * Block.tileSize + this.height, null);
			// Block damage overlay
			if (hardness <= ID.getHardness() / 6) {
				g.drawImage(AssetManager.tileset_terrain, this.x - (int) Component.sX, this.y - (int) Component.sY, this.width + this.x - (int) Component.sX, this.height + this.y - (int) Component.sY, 100, 20, 119, 39, null);
			} else if (hardness <= ID.getHardness() / 3) {
				g.drawImage(AssetManager.tileset_terrain, this.x - (int) Component.sX, this.y - (int) Component.sY, this.width + this.x - (int) Component.sX, this.height + this.y - (int) Component.sY, 80, 20, 99, 39, null);
			} else if (hardness <= ID.getHardness() / 2) {
				g.drawImage(AssetManager.tileset_terrain, this.x - (int) Component.sX, this.y - (int) Component.sY, this.width + this.x - (int) Component.sX, this.height + this.y - (int) Component.sY, 60, 20, 79, 39, null);
			} else if (hardness <= ID.getHardness() * 2 / 3) {
				g.drawImage(AssetManager.tileset_terrain, this.x - (int) Component.sX, this.y - (int) Component.sY, this.width + this.x - (int) Component.sX, this.height + this.y - (int) Component.sY, 40, 20, 59, 39, null);
			} else if (hardness <= ID.getHardness() * 5 / 6) {
				g.drawImage(AssetManager.tileset_terrain, this.x - (int) Component.sX, this.y - (int) Component.sY, this.width + this.x - (int) Component.sX, this.height + this.y - (int) Component.sY, 20, 20, 39, 39, null);
			}
		}
	}
}

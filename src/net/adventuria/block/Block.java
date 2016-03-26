package net.adventuria.block;

import java.awt.Graphics;
import java.awt.Rectangle;

import net.adventuria.Component;
import net.adventuria.assets.AssetManager;
import net.adventuria.block.BlockType;
import net.adventuria.location.Location;

public class Block extends Rectangle {
	public static int TILE_SIZE = 20;

	private static final long serialVersionUID = 1L;

	private BlockType type = BlockType.AIR;

	private int bx = 0;
	private int by = 0;
	private double hardness = 0;

	public Block(Location loc, BlockType ID) {
		this.type = ID;
		this.bx = loc.getX();
		this.by = loc.getY();
		
		this.hardness = ID.getHardness();
		
		setBounds(loc.getX() * TILE_SIZE, loc.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);

		this.type = ID;
	}

	public boolean isSolid() {
		return this.type.isSolid();
	}

	public void damage(double strength) {
		hardness -= strength;
	}

	public double getHardness() {
		return hardness;
	}

	public BlockType getBlockType() {
		return this.type;
	}

	public int getBlockX() {
		return this.bx;
	}

	public int getBlockY() {
		return this.by;
	}

	public void Render(Graphics g) {
		if (this.type != BlockType.AIR)
		{
			g.drawImage(AssetManager.getTexture(this.getBlockType().getTextureAlias()), this.x - (int) Component.sX, this.y - (int) Component.sY, null);
			
			// Block damage overlay
			if (hardness <= this.getBlockType().getHardness() / 6) {
				g.drawImage(AssetManager.getTexture("animation_break_4"), this.x - (int) Component.sX, this.y - (int) Component.sY, null);
			} else if (hardness <= this.getBlockType().getHardness() / 3) {
				g.drawImage(AssetManager.getTexture("animation_break_3"), this.x - (int) Component.sX, this.y - (int) Component.sY, null);
			} else if (hardness <= this.getBlockType().getHardness() / 2) {
				g.drawImage(AssetManager.getTexture("animation_break_2"), this.x - (int) Component.sX, this.y - (int) Component.sY, null);
			} else if (hardness <= this.getBlockType().getHardness() * 2 / 3) {
				g.drawImage(AssetManager.getTexture("animation_break_1"), this.x - (int) Component.sX, this.y - (int) Component.sY, null);
			} else if (hardness <= this.getBlockType().getHardness() * 5 / 6) {
				g.drawImage(AssetManager.getTexture("animation_break_0"), this.x - (int) Component.sX, this.y - (int) Component.sY, null);
			}
		}
	}
}

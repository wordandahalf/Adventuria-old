package net.adventuria.render.doubleRectangle;

import java.awt.Rectangle;

import net.adventuria.block.Block;
import net.adventuria.location.Location;

public class DoubleRectangle {
	public int type = 0;

	public double x;
	public double y;
	public double width;
	public double height;
	public int dX, dY, dWidth, dHeight;
	public Rectangle bounds = new Rectangle();

	public DoubleRectangle(int type) {
		setBounds(0.0D, 0.0D, 0.0D, 0.0D);
	}

	public DoubleRectangle(int type, Location loc) {
		if (type == 0) {
			setBounds(loc.getX(), loc.getY(), Block.TILE_SIZE, Block.TILE_SIZE * 2);
		} else {
			setBounds(loc.getX(), loc.getY(), Block.TILE_SIZE * 2, Block.TILE_SIZE);
		}
	}

	public void setBounds(int type, Location loc) {
		this.x = loc.getX();
		this.y = loc.getY();

		if (type == 0) {
			width = Block.TILE_SIZE;
			height = Block.TILE_SIZE * 2;
		} else {
			width = Block.TILE_SIZE * 2;
			height = Block.TILE_SIZE;
		}
	}

	public void setBounds(Location loc, double width, double height) {
		this.x = loc.getX();
		this.y = loc.getY();
		this.width = width;
		this.height = height;
	}

	public void setBounds(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void setBoundingTranslations(int dX, int dY, int dWidth, int dHeight) {
		this.dX = dX;
		this.dY = dY;
		this.dWidth = dWidth;
		this.dHeight = dHeight;
	}

	public Rectangle getBoundingRectangle() {
		bounds.setBounds((int) Math.round(x + dX), (int) Math.round(y + dY), (int) Math.round(width + dWidth), (int) Math.round(height + dHeight));
		return bounds;
	}
}

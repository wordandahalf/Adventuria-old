package net.adventuria.entity;

import java.awt.Point;

import net.adventuria.Component;
import net.adventuria.block.Block;
import net.adventuria.location.Location;
import net.adventuria.render.doubleRectangle.DoubleRectangle;

public abstract class EntityLiving extends DoubleRectangle implements EntityBase {
	private Location loc = new Location(0, 0);
	private EntityID ID = EntityID.NULL;
	protected int health = 20;

	public EntityLiving(Location loc, EntityID ID) {
		super(0, loc);
		this.loc = loc;
		this.ID = ID;
		this.health = ID.getDefaultHealth();
	}

	public Location getLocation() {
		return this.loc;
	}

	public EntityID getID() {
		return this.ID;
	}

	public int getHealth() {
		return this.health;
	}

	public void setHealth(int newHealth) {
		health = newHealth;
	}

	public boolean isCollidingWithBlock(Location pt1, Location pt2) {
		int x1 = pt1.x >= 0 ? pt1.x : 0;
		int x2 = pt2.x >= 0 ? pt2.x : 0;
		int y1 = pt1.y >= 0 ? pt1.y : 0;
		int y2 = pt2.y >= 0 ? pt2.y : 0;
		if ((Component.level.Blocks[x1][y1].getBounds().intersects(getBoundingRectangle()) && Component.level.Blocks[x1][y1].isSolid()) || (Component.level.Blocks[x2][y2].getBounds().intersects(getBoundingRectangle()) && Component.level.Blocks[x2][y2].isSolid())) {
			return true;
		}
		return false;
	}

	public int getBlockX() {
		return (int) Math.round(x / Block.tileSize);
	}

	public int getBlockY() {
		return (int) Math.round(y / Block.tileSize);
	}
}

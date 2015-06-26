package net.adventuria.entity;

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
		if ((Component.level.Blocks[pt1.getX()][pt1.getY()].getBounds().intersects(getBoundingRectangle()) && Component.level.Blocks[pt1.getX()][pt1.getY()].isSolid()) || (Component.level.Blocks[pt2.getX()][pt2.getY()].getBounds().intersects(getBoundingRectangle()) && Component.level.Blocks[pt2.getX()][pt2.getY()].isSolid())) {

			return true;
		}
		return false;
	}

	public int getBlockX() {
		return (int) (x / Block.tileSize);
	}

	public int getBlockY() {
		return (int) (y / Block.tileSize);
	}
}

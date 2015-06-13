package net.adventuria.entity;

import net.adventuria.location.Location;
import net.adventuria.render.doubleRectangle.DoubleRectangle;

public abstract class EntityLiving extends DoubleRectangle implements EntityBase 
{
	private Location loc = new Location(0, 0);
	private EntityID ID = EntityID.NULL;
	private int health = 20;
	
	public EntityLiving(Location loc, EntityID ID)
	{
		super(0, loc);
		
		this.loc = loc;
		this.ID = ID;
		this.health = ID.getDefaultHealth();
	}
	
	public Location getLocation()
	{
		return this.loc;
	}
	
	public EntityID getID()
	{
		return this.ID;
	}
	
	public int getHealth()
	{
		return this.health;
	}
}

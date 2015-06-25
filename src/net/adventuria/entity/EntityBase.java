package net.adventuria.entity;

import java.awt.Graphics;

import net.adventuria.block.Block;
import net.adventuria.item.Item;
import net.adventuria.location.Location;

public interface EntityBase {
	public Location loc = new Location(0, 0);

	public EntityID ID = EntityID.NULL;

	public abstract void Tick();

	public abstract void onCollidingWithBlock(Block b);

	public abstract void onAttacked(EntityLiving ent);

	public abstract void onAttack(EntityLiving ent);

	public abstract void onUseItem(Item i, Location loc);

	public abstract void Render(Graphics g);
}

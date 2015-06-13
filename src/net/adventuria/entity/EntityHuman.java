package net.adventuria.entity;

import java.awt.Graphics;

import net.adventuria.block.Block;
import net.adventuria.item.Item;
import net.adventuria.location.Location;

public class EntityHuman extends EntityLiving 
{
	public EntityHuman(Location loc)
	{
		super(loc, EntityID.PLAYER);
	}

	@Override
	public void Tick()
	{
		
	}

	@Override
	public void onCollidingWithBlock(Block b)
	{
		
	}

	@Override
	public void onAttacked(EntityLiving ent)
	{
		
	}

	@Override
	public void onAttack(EntityLiving ent) 
	{
		
	}

	@Override
	public void onUseItem(Item i, Location loc)
	{
		
	}

	@Override
	public void Render(Graphics g)
	{
		
	}
}

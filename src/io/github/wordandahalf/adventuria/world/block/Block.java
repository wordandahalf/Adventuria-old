package io.github.wordandahalf.adventuria.world.block;

import java.io.Serializable;

import io.github.wordandahalf.adventuria.engine.physics.Collidable;
import io.github.wordandahalf.adventuria.engine.physics.Tickable;

public class Block implements Collidable, Tickable, Serializable {
	private static final long serialVersionUID = -3169945812526827903L;

	//TODO: Dynamically detect tile size
	public static transient int TILE_SIZE = 16;
	
	private int x, y;
	
	private BlockType type;
	private double hardness;

	public Block(int x, int y, BlockType type) {			
		this.x = x;
		this.y = y;
		
		this.type = type;
		this.hardness = type.getHardness();
	}

	public boolean isSolid() {
		return this.type.isSolid();
	}

	public void damage(double strength) {
		hardness -= hardness == 255 ? 0 : strength;
	}

	public double getHardness() {
		return hardness;
	}

	public BlockType getBlockType() {
		return this.type;
	}

	public int getBlockX() {
		return this.x;
	}

	public int getBlockY() {
		return this.y;
	}

	@Override
	public void update() {
		
	}

	@Override
	public boolean collides(float x, float y) {
		return false;
	}
}

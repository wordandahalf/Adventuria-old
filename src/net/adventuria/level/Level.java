package net.adventuria.level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import net.adventuria.Component;
import net.adventuria.block.Block;
import net.adventuria.block.BlockID;
import net.adventuria.gui.inventory.*;
import net.adventuria.inputs.Mouse;
import net.adventuria.level.chunk.Chunk;
import net.adventuria.location.Location;

public class Level {
	
	private List<Chunk> chunks = new ArrayList<>();
	
	private Chunk current;
	
	public Level() {
		current = new Chunk();
		current.generate();
		
		chunks.add(current);
	}
	
	public Chunk getCurrentChunk() {
		return current;
	}
	
	public void setCurrentChunk(Chunk c) {
		current = c;
		c.generate();
		
		chunks.add(current);
	}

	public void Building(int camX, int camY, int renW, int renH) {
		if (Mouse.isLeftButtonClicked()) {
			if (!Inventory.isOpen) {
				if (Mouse.getBlockX() < Chunk.WIDTH && Mouse.getBlockY() < Chunk.HEIGHT && Mouse.isBlockInRange()) {
					Block b = this.getBlock(Mouse.getBlockX(), Mouse.getBlockY());
					if (b.getID() != BlockID.AIR) {
						if (b.getID().getHardness() < 128) {
							 /*b.damage(0.01);
							 if (b.getHardness() <= 0) {
								 Component.inventory.addItemToInventory(b.getID());
								 this.setBlock(BlockID.AIR,Mouse.getBlockX(), Mouse.getBlockY()); 
							 }*/
							 Component.inventory.addItemToInventory(b.getID());
							 this.setBlock(BlockID.AIR,Mouse.getBlockX(), Mouse.getBlockY()); 
						}
					}
				}
			}
		} else if (Mouse.isRightButtonClicked()) {
			if (!Inventory.isOpen) {
				if (Mouse.getBlockX() < Chunk.WIDTH && Mouse.getBlockY() < Chunk.HEIGHT && Mouse.isBlockInRange()) {
					if (!(Mouse.getBlockLocation().equals(Component.character.getBlockLocation())) && !(Mouse.getBlockLocation().equals(new Location((int) Component.character.getBlockX(), (int) Component.character.getBlockY() + 1)))) {
						if (this.getBlock(Mouse.getBlockX(), Mouse.getBlockY()).getID() == BlockID.AIR || this.getBlock(Mouse.getBlockX(), Mouse.getBlockY()).getID() == BlockID.WATERSOURCE && Mouse.getBlockX() > -1 && Mouse.getBlockY() > -1) {
							if (Component.inventory.getHeldItemID() != BlockID.AIR) {
								this.setBlock(Component.inventory.getHeldItemID(), Mouse.getBlockX(), Mouse.getBlockY());
								Component.inventory.setItemInventory(Component.inventory.getHeldItemID(), Component.inventory.getHeldItemCount() - 1, Inventory.selected);
							}
						}
					}
				}
			}
		}
	}

	public void updateWater() {
		for (Block[] bArray : getCurrentChunk().Block) {
			for (Block b : bArray) {
				if (b.getID() == BlockID.WATERSOURCE) {
					if (this.getBlock(b.getBlockX(), b.getBlockY() + 1).getID() == BlockID.AIR) {
						this.setBlock(BlockID.WATERSOURCE, b.getBlockX(), b.getBlockY() + 1);
					} else if (this.getBlock(b.getBlockX() + 1, b.getBlockY()).getID() == BlockID.AIR) {
						this.setBlock(BlockID.WATERSOURCE, b.getBlockX() + 1, b.getBlockY());
					} else if (this.getBlock(b.getBlockX() - 1, b.getBlockY()).getID() == BlockID.AIR) {
						this.setBlock(BlockID.WATERSOURCE, b.getBlockX() - 1, b.getBlockY());
					}
				}
			}
		}
	}

	public Block getBlock(int x, int y) {
		if (x > -1 && y > -1 && x < Chunk.WIDTH && y < Chunk.HEIGHT) {
			return getCurrentChunk().Block[x][y];
		} else {
			return new Block(new Location(x, y), BlockID.AIR);
		}
	}

	public void setBlock(BlockID blk, int x, int y) {
		if (x > -1 && y > -1 && x < Chunk.WIDTH && y < Chunk.HEIGHT) {
			getCurrentChunk().Block[x][y].setID(blk);
		}
	}

	public List<Chunk> getChunks()
	{
		return this.chunks;
	}
	
	public void Tick(int camX, int camY, int renW, int renH) {
		Building(camX, camY, renW, renH);

		updateWater();
	}

	public void Render(Graphics g, int camX, int camY, int renW, int renH) {
		for (int x = camX / Block.tileSize; x < camX / Block.tileSize + renW; x++) {
			for (int y = camY / Block.tileSize; y < camY / Block.tileSize + renH; y++) {
				if ((x >= 0) && (y >= 0) && (x < Chunk.WIDTH) && (y < Chunk.HEIGHT)) {
					getCurrentChunk().Block[x][y].Render(g);
					if ((getCurrentChunk().Block[x][y].contains(new Point(Mouse.getX() / Component.pixelSize + (int) Component.sX, Mouse.getY() / Component.pixelSize + (int) Component.sY))) && (getCurrentChunk().Block[x][y].getID() != BlockID.AIR) && (!Inventory.isOpen) && Mouse.isBlockInRange())

					{
						g.setColor(new Color(255, 255, 255, 64));
						g.fillRect(getCurrentChunk().Block[x][y].x - camX, getCurrentChunk().Block[x][y].y - camY, getCurrentChunk().Block[x][y].width, getCurrentChunk().Block[x][y].height);
					}
				}
			}
		}
	}
}

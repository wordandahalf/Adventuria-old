package net.adventuria.level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import net.adventuria.Component;
import net.adventuria.block.Block;
import net.adventuria.block.BlockType;
import net.adventuria.entity.player.EntityPlayer;
import net.adventuria.gui.inventory.Inventory;
import net.adventuria.inputs.Mouse;
import net.adventuria.level.biome.Biome;
import net.adventuria.level.chunk.Chunk;
import net.adventuria.level.generator.Generator;
import net.adventuria.location.Location;

public class World {	
	private List<Chunk> chunks = new ArrayList<Chunk>();
	
	private Chunk leftChunk;
	private Chunk currentChunk;
	private Chunk rightChunk;
	
	private Generator generator;
	
	EntityPlayer player;
	
	public World(Generator gen) {
		this.generator = gen;
		
		this.generate();
		
		int blockY = 0;
		int fitBlocks = 0; // Checks to ensure head and feet will fit in an
							// area.
		for (int y = Chunk.CHUNK_HEIGHT - 1; y >= 0; y--) {
			if (this.getCurrentChunk().getBlock((Chunk.CHUNK_WIDTH / 2), y).getBlockType().equals(BlockType.AIR)) {
				fitBlocks++;
				if (fitBlocks == 2) {
					blockY = y;
					break;
				}
			} else {
				fitBlocks = 0;
			}
		}
		
		Component.sX = (int) (Math.floor(50) * 20 - (Component.pixel.width / 2D) + 10);
		Component.sY = (int) (Math.floor(blockY) * 20 - (Component.pixel.height / 2D) + 10);
		
		this.player = new EntityPlayer(new Location((int) (Math.floor(50) * 20), (int) (Math.floor(blockY) * 20)));
	}
	
	private void generate() {
		this.currentChunk = this.generateNewChunk(new Location(0, 0));
		this.leftChunk = this.generateNewChunk(new Location(-1, 0));
		this.rightChunk = this.generateNewChunk(new Location(1, 0));
	}
	
	public EntityPlayer getPlayer() {
		return this.player;
	}
	
	public Generator getGenerator() {
		return this.generator;
	}
	
	public List<Chunk> getChunks() {
		return this.chunks;
	}
	
	public Chunk getLeftAdjacentChunk() {
		return this.leftChunk;
	}
	
	public Chunk getRightAdjacentChunk() {
		return this.rightChunk;
	}
	
	public Chunk getCurrentChunk() {
		return this.currentChunk;
	}
	
	public Chunk generateNewChunk(Location loc) {
		Chunk c = new Chunk(loc, 0, Biome.getRandomBiome());
		
		this.getGenerator().generate(c);
		
		chunks.add(c);
		
		return c;
	}
	
	public Chunk getChunkById(int id) {
		for(Chunk c : this.getChunks()) {
			if(c.getID() == id) {
				return c;
			}
		}
		
		return null;
	}
	
	public Chunk getChunkByLocation(Location loc) {
		for(Chunk c : this.getChunks()) {
			if(c.getLocation().equals(loc)) {
				return c;
			}
		}
		
		return null;
	}
	
	public void update() {
		this.getPlayer().Tick();
		
		if(Mouse.isLeftButtonClicked()) {
			Block selectedBlock = this.getCurrentChunk().getBlock(Mouse.getBlockX(), Mouse.getBlockY());
			
			if(selectedBlock instanceof Block && !Inventory.isOpen) {
				if(selectedBlock.isSolid() && Mouse.isBlockInRange()) {
					double blockHardness = selectedBlock.getHardness();
					
					if((blockHardness - 0.05) <= 0) {
						this.getCurrentChunk().setBlock(selectedBlock.getBlockX(), selectedBlock.getBlockY(), BlockType.AIR);
						Component.inventory.addItemToInventory(selectedBlock.getBlockType());
					} else {
						selectedBlock.damage(0.05);
					}
				}
			}
		}
		
		if(Mouse.isRightButtonClicked()) {
			if(!Inventory.isOpen) {				
				if(Mouse.getBlockY() > -1 && Mouse.getBlockY() < Chunk.CHUNK_HEIGHT) {
					if(!Mouse.getBlockLocation().equals(this.getPlayer().getBlockLocation())) {
						Block selectedBlock = this.getCurrentChunk().getBlock(Mouse.getBlockX(), Mouse.getBlockY());
						
						if(Component.inventory.getHeldItemID() != BlockType.AIR) {
							if(!selectedBlock.isSolid()) {
								this.getCurrentChunk().setBlock(Mouse.getBlockX(), Mouse.getBlockY(), Component.inventory.getHeldItemID());
								Component.inventory.setItemInventory(Component.inventory.getHeldItemID(), Component.inventory.getHeldItemCount() - 1, Inventory.selected);
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Renders the world!
	 * @param g - The java.awt.Graphics object (for drawing images to the display)
	 */
	public void draw(Graphics g) {
		//Frustum culling (only rendering blocks that are visible)
		int frustumWidth = ((Component.size.width / Component.pixelSize) / Block.TILE_SIZE);
		int frustumHeight = ((Component.size.height / Component.pixelSize) / Block.TILE_SIZE);
		
		int minFrustumX = getPlayer().getBlockX() - (frustumWidth / 2) - 2;
		int maxFrustumX = getPlayer().getBlockX() + (frustumWidth / 2) + 2;
		if(minFrustumX < 0) minFrustumX = 0;
		if(maxFrustumX >= Chunk.CHUNK_WIDTH) maxFrustumX = Chunk.CHUNK_WIDTH - 1;
		
		int minFrustumY = getPlayer().getBlockY() - (frustumHeight / 2) - 2;
		int maxFrustumY = getPlayer().getBlockY() + (frustumHeight / 2) + 2;
		if(minFrustumY < 0) minFrustumY = 0;
		if(maxFrustumY >= Chunk.CHUNK_HEIGHT) maxFrustumY = Chunk.CHUNK_HEIGHT - 1;
		
		for(int x = minFrustumX; x < maxFrustumX; x++) {
			for(int y = minFrustumY; y < maxFrustumY; y++) {
				getCurrentChunk().getBlock(x, y).Render(g);
			}
		}
		
		//Draw the selection box (for destroying and placing blocks)
		int selectedX = (int) ((Mouse.getX() / 2) + Component.sX) / Block.TILE_SIZE;
		int selectedY = (int) ((Mouse.getY() / 2) + Component.sY) / Block.TILE_SIZE;
		
		Block b = getCurrentChunk().getBlock(selectedX, selectedY);
		if(b != null) {
			//Make sure that the point is within a block and is within the destroyable range
			if(Mouse.isBlockInRange() && !Inventory.isOpen) {
				if(b.getBlockType().isSolid()) {
					g.setColor(new Color(0, 0, 0, 64));
					
					g.fillRect(b.getLocation().x - (int) Component.sX, b.getLocation().y - (int) Component.sY, Block.TILE_SIZE, Block.TILE_SIZE);
				}
			}
		}
		
		player.Render(g);
	}
}

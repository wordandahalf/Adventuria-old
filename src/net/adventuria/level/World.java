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
		Chunk c = new Chunk(loc, 1/2 * (loc.x + loc.y) * (loc.x + loc.y + 1) + loc.y, Biome.getRandomBiome());
		
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
		
	}
	
	//TODO: Fix this (it should only need one parameter, the Graphics object)
	public void draw(Graphics g) {
		for(Block[] blockArray : this.getCurrentChunk().getBlocks()) {
			for(Block b : blockArray) {
				if(b.getBlockY() - this.getPlayer().getBlockY() <= 8) {
					if((b.getBlockX() - this.getPlayer().getBlockX() <= 9)) {
						if((b.getBlockX() - this.getPlayer().getBlockX() >= -9)) {
							b.Render(g);
						}
					}
				}
			}
		}
		
		player.Render(g);
	}
}

package net.adventuria.level.generator;

import java.util.Random;

import net.adventuria.block.Block;
import net.adventuria.block.BlockType;
import net.adventuria.level.chunk.Chunk;
import net.adventuria.location.Location;

public class DefaultGenerator implements Generator {
	public static final int BIOME_PLAINS_RANGE = 5;
	public static final int BIOME_DESERT_RANGE = 8;
	
	
	public static final int BEDROCK_RANGE = 4;
	public static final int STONE_RANGE = 8;
	

	public static final int COAL_Y_LEVEL = 64;
	public static final int IRON_Y_LEVEL = 72;
	public static final int GOLD_Y_LEVEL = 128;
	public static final int RUBY_Y_LEVEL = 128;
	public static final int EMERALD_Y_LEVEL = 128;
	public static final int DIAMOND_Y_LEVEL = 160;
	public static final int COPPER_Y_LEVEL = 72;
	public static final int TIN_Y_LEVEL = 96;
	public static final int SAPPHIRE_Y_LEVEL = 144;
	

	public static final int COAL_MAX_VEIN_SIZE = 10;
	public static final int IRON_MAX_VEIN_SIZE = 8;
	public static final int GOLD_MAX_VEIN_SIZE = 6;
	public static final int RUBY_MAX_VEIN_SIZE = 4;
	public static final int EMERALD_MAX_VEIN_SIZE = 4;
	public static final int DIAMOND_MAX_VEIN_SIZE = 3;
	public static final int COPPER_MAX_VEIN_SIZE = 7;
	public static final int TIN_MAX_VEIN_SIZE = 7;
	public static final int SAPPHIRE_MAX_VEIN_SIZE = 4;
	
	public static final int COAL_RARITY = 20;
	public static final int IRON_RARITY = 19;
	public static final int GOLD_RARITY = 7;
	public static final int RUBY_RARITY = 5;
	public static final int EMERALD_RARITY = 5;
	public static final int DIAMOND_RARITY = 2;
	public static final int COPPER_RARITY = 15;
	public static final int TIN_RARITY = 15;
	public static final int SAPPHIRE_RARITY = 5;
	
	public void generate(Chunk c) {
		for(int x = 0; x < Chunk.CHUNK_WIDTH; x++) {
			for(int y = 0; y < Chunk.CHUNK_HEIGHT; y++) {
				c.setBlock(x, y, BlockType.AIR);
			}
		}

		switch(c.getBiome()) {
			case PLAINS:
				generatePlains(c);
				generateLakes(c);
				generatePlainsTrees(c);
				break;
			case DESERT:
				generateDesert(c);
				break;
			case OCEAN:
				generateOcean(c);
				break;
			case TUNDRA:
				generateTundra(c);
				generateIce(c);
				break;
			default:
				System.err.println("Oops! There seems to be an error with the biome at\n (" + c.getLocation().getX() + ", " + c.getLocation().getY() + ")!");
				break;
		}
		generateStone(c);
		generateOre(c);
		generateBedrock(c);
	}
	
	private void generateIce(Chunk c) {
		Random rand = new Random();
		for(int x = 1; x < Chunk.CHUNK_WIDTH - 1; x++) {
			for(int y = 32; y < 64; y++) {
				if(c.getBlock(x, y).getBlockType() == BlockType.GRASS_SNOW) {
					if(rand.nextInt(25) == 0) {
						createIceLake(c, x, y, rand.nextInt(2) + 2);
					}
				}
			}
		}
	}
	
	//TODO: Make lakes look more natural
	private void createIceLake(Chunk c, int centerX, int topY, int radius) {
		for(int x = centerX - radius; x < centerX + radius; x++) {
			for(int y = topY; y < topY + radius; y++) {
				c.setBlock(x, y, BlockType.ICE);
			}
		}
	}
	
	private void generateLakes(Chunk c) {
		Random rand = new Random();
		for(int x = 1; x < Chunk.CHUNK_WIDTH - 1; x++) {
			for(int y = 32; y < 64; y++) {
				if(c.getBlock(x, y).getBlockType() == BlockType.GRASS) {
					if(rand.nextInt(25) == 0) {
						createLake(c, x, y, rand.nextInt(5) + 2);
					}
				}
			}
		}
	}
	
	//TODO: Make lakes look more natural
	private void createLake(Chunk c, int centerX, int topY, int radius) {
		for(int x = centerX - radius; x < centerX + radius; x++) {
			for(int y = topY; y < topY + radius; y++) {
				c.setBlock(x, y, BlockType.WATERSOURCE);
			}
		}
	}
	
	//TODO: Create tree generation method
	private void generatePlainsTrees(Chunk c) {
		
	}
	
	private void generateOre(Chunk c) {
		Random rand = new Random();
		int vein = 1;
		
		for(int x = 1; x < Chunk.CHUNK_WIDTH - 1; x++) {
			for(int y = COAL_Y_LEVEL; y < Chunk.CHUNK_HEIGHT; y++) {
				if (rand.nextInt(10000) <= COAL_RARITY) {
					vein = rand.nextInt(COAL_MAX_VEIN_SIZE) + 1;
					for(int i = 0; i < vein; i++) {
						c.setBlock(x, y, BlockType.ORE_COAL);
						int dir = rand.nextInt(4);
						if(dir == 0) {
							x+= x < Chunk.CHUNK_WIDTH - 2 ? 1 : -1;
						} else if(dir == 1) {
							x-= x > 1 ? 1 : -1;
						} else if(dir == 2) {
							y+= y < Chunk.CHUNK_HEIGHT - 2 ? 1 : -1;
						} else {
							y-= y > 1 ? 1 : -1;
						}
					}
				}
			}
		}
		
		for(int x = 1; x < Chunk.CHUNK_WIDTH - 1; x++) {
			for(int y = IRON_Y_LEVEL; y < Chunk.CHUNK_HEIGHT; y++) {
				if (rand.nextInt(10000) <= IRON_RARITY) {
					vein = rand.nextInt(IRON_MAX_VEIN_SIZE) + 1;
					for(int i = 0; i < vein; i++) {
						c.setBlock(x, y, BlockType.ORE_IRON);
						int dir = rand.nextInt(4);
						if(dir == 0) {
							x+= x < Chunk.CHUNK_WIDTH - 2 ? 1 : -1;
						} else if(dir == 1) {
							x-= x > 1 ? 1 : -1;
						} else if(dir == 2) {
							y+= y < Chunk.CHUNK_HEIGHT - 2 ? 1 : -1;
						} else {
							y-= y > 1 ? 1 : -1;
						}
					}
				}
			}
		}
		
		for(int x = 1; x < Chunk.CHUNK_WIDTH - 1; x++) {
			for(int y = GOLD_Y_LEVEL; y < Chunk.CHUNK_HEIGHT; y++) {
				if (rand.nextInt(10000) <= GOLD_RARITY) {
					vein = rand.nextInt(GOLD_MAX_VEIN_SIZE) + 1;
					for(int i = 0; i < vein; i++) {
						c.setBlock(x, y, BlockType.ORE_GOLD);
						int dir = rand.nextInt(4);
						if(dir == 0) {
							x+= x < Chunk.CHUNK_WIDTH - 2 ? 1 : -1;
						} else if(dir == 1) {
							x-= x > 1 ? 1 : -1;
						} else if(dir == 2) {
							y+= y < Chunk.CHUNK_HEIGHT - 2 ? 1 : -1;
						} else {
							y-= y > 1 ? 1 : -1;
						}
					}
				}
			}
		}
		
		for(int x = 1; x < Chunk.CHUNK_WIDTH - 1; x++) {
			for(int y = RUBY_Y_LEVEL; y < Chunk.CHUNK_HEIGHT; y++) {
				if (rand.nextInt(10000) <= RUBY_RARITY) {
					vein = rand.nextInt(RUBY_MAX_VEIN_SIZE) + 1;
					for(int i = 0; i < vein; i++) {
						c.setBlock(x, y, BlockType.ORE_RUBY);
						int dir = rand.nextInt(4);
						if(dir == 0) {
							x+= x < Chunk.CHUNK_WIDTH - 2 ? 1 : -1;
						} else if(dir == 1) {
							x-= x > 1 ? 1 : -1;
						} else if(dir == 2) {
							y+= y < Chunk.CHUNK_HEIGHT - 2 ? 1 : -1;
						} else {
							y-= y > 1 ? 1 : -1;
						}
					}
				}
			}
		}
		
		for(int x = 1; x < Chunk.CHUNK_WIDTH - 1; x++) {
			for(int y = EMERALD_Y_LEVEL; y < Chunk.CHUNK_HEIGHT; y++) {
				if (rand.nextInt(10000) <= EMERALD_RARITY) {
					vein = rand.nextInt(EMERALD_MAX_VEIN_SIZE) + 1;
					for(int i = 0; i < vein; i++) {
						c.setBlock(x, y, BlockType.ORE_EMERALD);
						int dir = rand.nextInt(4);
						if(dir == 0) {
							x+= x < Chunk.CHUNK_WIDTH - 2 ? 1 : -1;
						} else if(dir == 1) {
							x-= x > 1 ? 1 : -1;
						} else if(dir == 2) {
							y+= y < Chunk.CHUNK_HEIGHT - 2 ? 1 : -1;
						} else {
							y-= y > 1 ? 1 : -1;
						}
					}
				}
			}
		}
		
		for(int x = 1; x < Chunk.CHUNK_WIDTH - 1; x++) {
			for(int y = COPPER_Y_LEVEL; y < Chunk.CHUNK_HEIGHT; y++) {
				if (rand.nextInt(10000) <= COPPER_RARITY) {
					vein = rand.nextInt(COPPER_MAX_VEIN_SIZE) + 1;
					for(int i = 0; i < vein; i++) {
						c.setBlock(x, y, BlockType.ORE_COPPER);
						int dir = rand.nextInt(4);
						if(dir == 0) {
							x+= x < Chunk.CHUNK_WIDTH - 2 ? 1 : -1;
						} else if(dir == 1) {
							x-= x > 1 ? 1 : -1;
						} else if(dir == 2) {
							y+= y < Chunk.CHUNK_HEIGHT - 2 ? 1 : -1;
						} else {
							y-= y > 1 ? 1 : -1;
						}
					}
				}
			}
		}
		
		for(int x = 1; x < Chunk.CHUNK_WIDTH - 1; x++) {
			for(int y = TIN_Y_LEVEL; y < Chunk.CHUNK_HEIGHT; y++) {
				if (rand.nextInt(10000) <= TIN_RARITY) {
					vein = rand.nextInt(TIN_MAX_VEIN_SIZE) + 1;
					for(int i = 0; i < vein; i++) {
						c.setBlock(x, y, BlockType.ORE_TIN);
						int dir = rand.nextInt(4);
						if(dir == 0) {
							x+= x < Chunk.CHUNK_WIDTH - 2 ? 1 : -1;
						} else if(dir == 1) {
							x-= x > 1 ? 1 : -1;
						} else if(dir == 2) {
							y+= y < Chunk.CHUNK_HEIGHT - 2 ? 1 : -1;
						} else {
							y-= y > 1 ? 1 : -1;
						}
					}
				}
			}
		}
		
		for(int x = 1; x < Chunk.CHUNK_WIDTH - 1; x++) {
			for(int y = SAPPHIRE_Y_LEVEL; y < Chunk.CHUNK_HEIGHT; y++) {
				if (rand.nextInt(10000) <= SAPPHIRE_RARITY) {
					vein = rand.nextInt(SAPPHIRE_MAX_VEIN_SIZE) + 1;
					for(int i = 0; i < vein; i++) {
						c.setBlock(x, y, BlockType.ORE_SAPPHIRE);
						int dir = rand.nextInt(4);
						if(dir == 0) {
							x+= x < Chunk.CHUNK_WIDTH - 2 ? 1 : -1;
						} else if(dir == 1) {
							x-= x > 1 ? 1 : -1;
						} else if(dir == 2) {
							y+= y < Chunk.CHUNK_HEIGHT - 2 ? 1 : -1;
						} else {
							y-= y > 1 ? 1 : -1;
						}
					}
				}
			}
		}
		
		for(int x = 1; x < Chunk.CHUNK_WIDTH - 1; x++) {
			for(int y = DIAMOND_Y_LEVEL; y < Chunk.CHUNK_HEIGHT; y++) {
				if (rand.nextInt(10000) <= DIAMOND_RARITY) {
					vein = rand.nextInt(DIAMOND_MAX_VEIN_SIZE) + 1;
					for(int i = 0; i < vein; i++) {
						c.setBlock(x, y, BlockType.ORE_DIAMOND);
						int dir = rand.nextInt(4);
						if(dir == 0) {
							x+= x < Chunk.CHUNK_WIDTH - 2 ? 1 : -1;
						} else if(dir == 1) {
							x-= x > 1 ? 1 : -1;
						} else if(dir == 2) {
							y+= y < Chunk.CHUNK_HEIGHT - 2 ? 1 : -1;
						} else {
							y-= y > 1 ? 1 : -1;
						}
					}
				}
			}
		}
	}
	
	private void generateStone(Chunk c) {
		Random rand = new Random();
		
		for(int x = 1; x < Chunk.CHUNK_WIDTH - 1; x++) {
			for(int y = 64; y < Chunk.CHUNK_HEIGHT; y++) {
				c.setBlock(x, y - rand.nextInt(rand.nextInt(STONE_RANGE - 1) + 1), BlockType.STONE);
				c.setBlock(x, y, BlockType.STONE);
			}
		}
	}
	
	private void generateBedrock(Chunk c) {
		Random rand = new Random();
		
		for(int x = 1; x < Chunk.CHUNK_WIDTH - 1; x++) {
			for(int y = Chunk.CHUNK_HEIGHT - 2; y < Chunk.CHUNK_HEIGHT; y++) {
				c.setBlock(x, y - rand.nextInt(rand.nextInt(BEDROCK_RANGE - 1) + 1), BlockType.BEDROCK);
				c.setBlock(x, y, BlockType.BEDROCK);
			}
		}
	}
	
	private void generatePlains(Chunk c) {
		Random rand = new Random();
		
		for(int x = 1; x < Chunk.CHUNK_WIDTH - 1; x++) {
			for(int y = 32; y < 64; y++) {
				if(y < Chunk.CHUNK_HEIGHT - (BIOME_PLAINS_RANGE + 1)) {
					
					//Generate the random surface of the world
					c.setBlock(x, y + rand.nextInt(rand.nextInt(BIOME_PLAINS_RANGE - 1) + 1), BlockType.EARTH);
				}
				
				if(c.getBlock(x, y - 1).getBlockType() == BlockType.EARTH) {
					c.setBlock(x, y, BlockType.EARTH);
				}
				
				if(c.getBlock(x, y).getBlockType() == BlockType.EARTH) {
					if(c.getBlock(x, y - 1).getBlockType() == BlockType.AIR) {
						c.setBlock(x, y - 1, BlockType.GRASS);
					}
				}
			}
		}
	}
	
	private void generateTundra(Chunk c) {
		Random rand = new Random();
		
		for(int x = 1; x < Chunk.CHUNK_WIDTH - 1; x++) {
			for(int y = 32; y < 64; y++) {
				if(y < Chunk.CHUNK_HEIGHT - (BIOME_PLAINS_RANGE + 1)) {
					
					//Generate the random surface of the world
					c.setBlock(x, y + rand.nextInt(rand.nextInt(BIOME_PLAINS_RANGE - 1) + 1), BlockType.EARTH);
				}
				
				if(c.getBlock(x, y - 1).getBlockType() == BlockType.EARTH) {
					c.setBlock(x, y, BlockType.EARTH);
				}
				
				if(c.getBlock(x, y).getBlockType() == BlockType.EARTH) {
					if(c.getBlock(x, y - 1).getBlockType() == BlockType.AIR) {
						c.setBlock(x, y - 1, BlockType.GRASS_SNOW);
					}
				}
			}
		}
	}
	
	private void generateDesert(Chunk c) {
		Random rand = new Random();
		
		for(int x = 1; x < Chunk.CHUNK_WIDTH - 1; x++) {
			for(int y = 32; y < 64; y++) {
				if(y < Chunk.CHUNK_HEIGHT - (BIOME_DESERT_RANGE + 1)) {
					
					//Generate the random surface of the world
					c.setBlock(x, y + rand.nextInt(rand.nextInt(BIOME_DESERT_RANGE - 1) + 1), BlockType.SAND);
				}
				
				if(c.getBlock(x, y - 1).getBlockType() == BlockType.SAND) {
					c.setBlock(x, y, BlockType.SAND);
				}
			}
		}
	}
	
	private void generateOcean(Chunk c) {
		Random rand = new Random();
		
		for(int x = 1; x < Chunk.CHUNK_WIDTH - 1; x++) {
			for(int y = 32; y < Chunk.CHUNK_HEIGHT; y++) {
				c.setBlock(x, y, BlockType.WATERSOURCE);
				
				if(y > 50) {
					c.setBlock(x, y - rand.nextInt(4), BlockType.SAND);
					c.setBlock(x, y, BlockType.SAND);
				}
			}
		}
	}
}

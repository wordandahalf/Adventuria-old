package net.adventuria.level;

import java.util.Random;

import net.adventuria.block.Block;
import net.adventuria.block.BlockType;
import net.adventuria.level.chunk.Chunk;
import net.adventuria.location.Location;

public class Generator {
	public static int offset = 1;

	public static void genWorld(Block[][] Block) {
		genPrelimMap(Block);
		genGrass(Block);
		genStone(Block);
		genBedrock(Block);
		genOre(Block, BlockType.ORE_COAL, 40, Chunk.CHUNK_HEIGHT - 10, 20);
		genOre(Block, BlockType.ORE_IRON, 60, Chunk.CHUNK_HEIGHT - 5, 19);
		genOre(Block, BlockType.ORE_GOLD, 80, Chunk.CHUNK_HEIGHT - 4, 7);
		genOre(Block, BlockType.ORE_RUBY, 80, Chunk.CHUNK_HEIGHT - 5,  5);
		genOre(Block, BlockType.ORE_EMERALD, 80, Chunk.CHUNK_HEIGHT - 5, 5);
		genOre(Block, BlockType.ORE_DIAMOND, 90, Chunk.CHUNK_HEIGHT - 5, 2);
		genOre(Block, BlockType.ORE_COPPER, 70, Chunk.CHUNK_HEIGHT - 5, 15);
		genOre(Block, BlockType.ORE_TIN, 70, Chunk.CHUNK_HEIGHT - 5, 15);
		genOre(Block, BlockType.ORE_SAPPHIRE, 80, Chunk.CHUNK_HEIGHT - 5, 5);
		genTreeBase(Block);
		genTree(Block);
		genTreeLeaves(Block);
		genLake(Block);
	}

	public static void genBarrier(Block[][] Block) {
		for (int y = 0; y < Block.length; y++) {
			for (int x = 0; x < Block[0].length; x++) {
				if (x == 0 || x == Block[0].length - 1) {
					Block[x][y] = new Block(new Location(x, y), BlockType.SOLIDAIR);
				}
			}
		}
	}

	public static void genPrelimMap(Block[][] Block) {
		for (int y = 0; y < Block.length; y++) {
			for (int x = offset; x < Block[0].length - offset; x++) {
				if (y > Chunk.CHUNK_HEIGHT / 8) {
					if (new Random().nextInt(100) > 20) {
						try {
							if (Block[(x - 1)][(y - 1)].getBlockType() == BlockType.EARTH) {
								Block[x][y] = new Block(new Location(x, y), BlockType.EARTH);
							}
						} catch (Exception localException) {
						}
					}
					if (new Random().nextInt(100) > 30) {
						try {
							if (Block[(x + 1)][(y - 1)].getBlockType() == BlockType.EARTH) {
								Block[x][y] = new Block(new Location(x, y), BlockType.EARTH);
							}
						} catch (Exception localException1) {
						}
					}
					try {
						if (Block[x][(y - 1)].getBlockType() == BlockType.EARTH) {
							Block[x][y] = new Block(new Location(x, y), BlockType.EARTH);
						}
					} catch (Exception localException2) {
					}
					if (new Random().nextInt(100) < 2) {
						Block[x][y] = new Block(new Location(x, y), BlockType.EARTH);
					}
				}
			}
		}
	}

	public static void genGrass(Block[][] Block) {
		for (int y = 0; y < Block.length; y++) {
			for (int x = offset; x < Block[0].length - offset; x++) {
				if ((Block[x][y].getBlockType() == BlockType.EARTH) && (Block[x][(y - 1)].getBlockType() == BlockType.AIR)) {
					Block[x][y] = new Block(new Location(x, y), BlockType.GRASS);
				}
			}
		}
	}

	public static void genStone(Block[][] Block) {
		for (int y = 0; y < Block.length; y++) {
			for (int x = offset; x < Block[0].length - offset; x++) {
				for (int y2 = 0; y2 < Block[0].length - 15 - offset; y2++) {
					if (y >= Chunk.CHUNK_HEIGHT / 2.4) {
						if (new Random().nextInt(10000) < 100) {
							Block[x][y] = new Block(new Location(x, y), BlockType.STONE);
						}
					}

					if (y >= (Chunk.CHUNK_HEIGHT / 2.4) + 15) {
						Block[x][y] = new Block(new Location(x, y), BlockType.STONE);
					}
				}
			}
		}
	}

	public static void genBedrock(Block[][] Block) {
		for (int y = 0; y < Block.length; y++) {
			for (int x = offset; x < Block[0].length - offset; x++) {
				if (y >= Chunk.CHUNK_HEIGHT - 1) {
					Block[x][y] = new Block(new Location(x, y), BlockType.BEDROCK);
				}
				if ((new Random().nextInt(100) <= 65) && (y >= Chunk.CHUNK_HEIGHT - 2)) {
					Block[x][y] = new Block(new Location(x, y), BlockType.BEDROCK);
				}
				if ((new Random().nextInt(100) <= 45) && (y == Chunk.CHUNK_HEIGHT - 3)) {
					Block[x][y] = new Block(new Location(x, y), BlockType.BEDROCK);
				}
			}
		}
	}

	public static void genOre(Block[][] Block, BlockType ORE_TYPE, int minY, int maxY, int rarity) throws IllegalArgumentException {
		if ((minY > Chunk.CHUNK_HEIGHT) || (maxY > Chunk.CHUNK_HEIGHT)) {
			throw new IllegalArgumentException("[ERROR]: " + minY + " or " + maxY + " is greater than the world height (" + Chunk.CHUNK_HEIGHT + ")");
		}
		for (int y = 0 + offset; y < Block.length - offset; y++) {
			for (int x = 0 + offset; x < Block[0].length - offset; x++) {
				if ((y >= minY) && (y <= maxY)) {
					if ((Block[x][y].getBlockType() == BlockType.STONE) && (new Random().nextInt(1000) <= rarity)) {
						Block[x][y] = new Block(new Location(x, y), ORE_TYPE);
					}
				}
			}
		}
	}

	public static void genTreeBase(Block[][] Block) {
		for (int y = 0 + offset; y < Block.length - offset; y++) {
			for (int x = 0 + offset; x < Block[0].length - offset; x++) {
				if ((x > 2) && (x < Block[0].length - 2)) {
					if (Block[x][y].getBlockType() == BlockType.GRASS) {
						if (new Random().nextInt(100) >= 95) {
							Block[x][(y - 1)] = new Block(new Location(x, y - 1), BlockType.WOOD_OAK);
						}
					}
				}
			}
		}
	}

	public static void genTree(Block[][] Block) {
		for (int y = 0 + offset; y < Block.length - offset; y++) {
			for (int x = 0 + offset; x < Block[0].length - offset; x++) {
				if ((x > 2) && (x < Block[0].length - 2)) {
					if (Block[x][y].getBlockType() == BlockType.WOOD_OAK) {
						int rand = new Random().nextInt(5);
						if (rand < 3) {
							rand = 5;
						}
						for (int height = 0; height <= rand; height++) {
							Block[x][(y - height)] = new Block(new Location(x, y - height), BlockType.WOOD_OAK);
						}
					}
				}
			}
		}
	}

	public static void genTreeLeaves(Block[][] Block) {
		for (int y = 0 + offset; y < Block.length - offset; y++) {
			for (int x = 0 + offset; x < Block[0].length - offset; x++) {
				if ((x > 2) && (x < Block[0].length - 2)) {
					if ((Block[x][y].getBlockType() == BlockType.AIR) && (Block[x][(y + 1)].getBlockType() == BlockType.WOOD_OAK)) {
						Block[(x + 1)][(y + 2)] = new Block(new Location(x + 1, y + 2), BlockType.LEAF_OAK);
						Block[(x + 1)][y] = new Block(new Location(x + 1, y), BlockType.LEAF_OAK);

						Block[(x + 1)][(y + 1)] = new Block(new Location(x + 1, y + 1), BlockType.LEAF_OAK);
						Block[(x + 2)][(y + 1)] = new Block(new Location(x + 2, y + 1), BlockType.LEAF_OAK);
						Block[(x + 2)][(y + 2)] = new Block(new Location(x + 2, y + 2), BlockType.LEAF_OAK);

						Block[(x - 1)][y] = new Block(new Location(x - 1, y), BlockType.LEAF_OAK);
						Block[(x - 1)][(y + 1)] = new Block(new Location(x - 1, y + 1), BlockType.LEAF_OAK);
						Block[(x - 1)][(y + 2)] = new Block(new Location(x - 1, y + 2), BlockType.LEAF_OAK);
						Block[(x - 1)][(y + 2)] = new Block(new Location(x - 1, y + 2), BlockType.LEAF_OAK);

						Block[(x - 2)][(y + 1)] = new Block(new Location(x - 2, y + 1), BlockType.LEAF_OAK);
						Block[(x - 2)][(y + 2)] = new Block(new Location(x - 2, y + 2), BlockType.LEAF_OAK);

						Block[x][y] = new Block(new Location(x, y), BlockType.LEAF_OAK);
						Block[x][(y + 1)] = new Block(new Location(x, y + 1), BlockType.LEAF_OAK);
						Block[x][(y + 2)] = new Block(new Location(x, y + 2), BlockType.LEAF_OAK);
					}
				}
			}
		}
	}

	public static void genLake(Block[][] Block) {
		for (int y = 0 + offset; y < Block.length - offset; y++) {
			for (int x = 0 + offset; x < Block[0].length - offset; x++) {
				if ((x > 2) && (x < Block[0].length - 2)) {
					if ((Block[x][y].getBlockType() == BlockType.AIR) && (Block[x][(y + 1)].getBlockType() == BlockType.GRASS) && (Block[(x + 1)][y].getBlockType() == BlockType.GRASS) && (Block[(x - 1)][y].getBlockType() == BlockType.GRASS)) {
						Block[x][(y - 1)] = new Block(new Location(x, y - 1), BlockType.WATERSOURCE);
					}
				}
			}
		}
	}
}

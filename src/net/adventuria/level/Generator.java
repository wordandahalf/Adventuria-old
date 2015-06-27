package net.adventuria.level;

import java.util.Random;

import net.adventuria.block.Block;
import net.adventuria.block.BlockID;
import net.adventuria.level.chunk.Chunk;

public class Generator {
	public static int offset = 1;

	public static void genWorld(Block[][] Block) {
		genPrelimMap(Block);
		genGrass(Block);
		genStone(Block);
		genBedrock(Block);
		genOre(Block, BlockID.ORE_COAL, 40, Chunk.HEIGHT - 10, 40);
		genOre(Block, BlockID.ORE_IRON, 60, Chunk.HEIGHT - 5, 35);
		genOre(Block, BlockID.ORE_GOLD, 80, Chunk.HEIGHT - 4, 30);
		genOre(Block, BlockID.ORE_RUBY, 80, Chunk.HEIGHT - 5, 25);
		genOre(Block, BlockID.ORE_EMERALD, 80, Chunk.HEIGHT - 5, 25);
		genOre(Block, BlockID.ORE_DIAMOND, 90, Chunk.HEIGHT - 5, 25);
		genTreeBase(Block);
		genTree(Block);
		genTreeLeaves(Block);
		genLake(Block);
		//genBarrier(Block);
	}

	public static void genBarrier(Block[][] Block) {
		for (int y = 0; y < Block.length; y++) {
			for (int x = 0; x < Block[0].length; x++) {
				if (x == 0 || x == Block[0].length - 1) {
					Block[x][y].setID(BlockID.SOLIDAIR);
				}
			}
		}
	}

	public static void genPrelimMap(Block[][] Block) {
		for (int y = 0; y < Block.length; y++) {
			for (int x = offset; x < Block[0].length - offset; x++) {
				if (y > Chunk.HEIGHT / 8) {
					if (new Random().nextInt(100) > 20) {
						try {
							if (Block[(x - 1)][(y - 1)].getID() == BlockID.EARTH) {
								Block[x][y].setID(BlockID.EARTH);
							}
						} catch (Exception localException) {
						}
					}
					if (new Random().nextInt(100) > 30) {
						try {
							if (Block[(x + 1)][(y - 1)].getID() == BlockID.EARTH) {
								Block[x][y].setID(BlockID.EARTH);
							}
						} catch (Exception localException1) {
						}
					}
					try {
						if (Block[x][(y - 1)].getID() == BlockID.EARTH) {
							Block[x][y].setID(BlockID.EARTH);
						}
					} catch (Exception localException2) {
					}
					if (new Random().nextInt(100) < 2) {
						Block[x][y].setID(BlockID.EARTH);
					}
				}
			}
		}
	}

	public static void genGrass(Block[][] Block) {
		for (int y = 0; y < Block.length; y++) {
			for (int x = offset; x < Block[0].length - offset; x++) {
				if ((Block[x][y].getID() == BlockID.EARTH) && (Block[x][(y - 1)].getID() == BlockID.AIR)) {
					Block[x][y].setID(BlockID.GRASS);
				}
			}
		}
	}

	public static void genStone(Block[][] Block) {
		for (int y = 0; y < Block.length; y++) {
			for (int x = offset; x < Block[0].length - offset; x++) {
				for (int y2 = 0; y2 < Block[0].length - 15 - offset; y2++) {
					if (y >= Chunk.HEIGHT / 2.4) {
						if (new Random().nextInt(10000) < 100) {
							Block[x][y].setID(BlockID.STONE);
						}
					}

					if (y >= (Chunk.HEIGHT / 2.4) + 15) {
						Block[x][y].setID(BlockID.STONE);
					}
				}
			}
		}
	}

	public static void genBedrock(Block[][] Block) {
		for (int y = 0; y < Block.length; y++) {
			for (int x = offset; x < Block[0].length - offset; x++) {
				if (y >= Chunk.HEIGHT - 1) {
					Block[x][y].setID(BlockID.BEDROCK);
				}
				if ((new Random().nextInt(100) <= 65) && (y >= Chunk.HEIGHT - 2)) {
					Block[x][y].setID(BlockID.BEDROCK);
				}
				if ((new Random().nextInt(100) <= 45) && (y == Chunk.HEIGHT - 3)) {
					Block[x][y].setID(BlockID.BEDROCK);
				}
			}
		}
	}

	public static void genOre(Block[][] Block, BlockID ORE_TYPE, int minY, int maxY, int rarity) throws IllegalArgumentException {
		if ((minY > Chunk.HEIGHT) || (maxY > Chunk.HEIGHT)) {
			throw new IllegalArgumentException("[ERROR]: " + minY + " or " + maxY + " is greater than the world height (" + Chunk.HEIGHT + ")");
		}
		for (int y = 0 + offset; y < Block.length - offset; y++) {
			for (int x = 0 + offset; x < Block[0].length - offset; x++) {
				if ((y >= minY) && (y <= maxY)) {
					if ((Block[x][y].getID() == BlockID.STONE) && (new Random().nextInt(1000) <= rarity)) {
						Block[x][y].setID(ORE_TYPE);
					}
				}
			}
		}
	}

	public static void genTreeBase(Block[][] Block) {
		for (int y = 0 + offset; y < Block.length - offset; y++) {
			for (int x = 0 + offset; x < Block[0].length - offset; x++) {
				if ((x > 2) && (x < Block[0].length - 2)) {
					if (Block[x][y].getID() == BlockID.GRASS) {
						if (new Random().nextInt(100) >= 95) {
							Block[x][(y - 1)].setID(BlockID.WOOD_OAK);
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
					if (Block[x][y].getID() == BlockID.WOOD_OAK) {
						int rand = new Random().nextInt(5);
						if (rand < 3) {
							rand = 5;
						}
						for (int height = 0; height <= rand; height++) {
							Block[x][(y - height)].setID(BlockID.WOOD_OAK);
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
					if ((Block[x][y].getID() == BlockID.AIR) && (Block[x][(y + 1)].getID() == BlockID.WOOD_OAK)) {
						Block[(x + 1)][(y + 2)].setID(BlockID.LEAF_OAK);
						Block[(x + 1)][y].setID(BlockID.LEAF_OAK);

						Block[(x + 1)][(y + 1)].setID(BlockID.LEAF_OAK);
						Block[(x + 2)][(y + 1)].setID(BlockID.LEAF_OAK);
						Block[(x + 2)][(y + 2)].setID(BlockID.LEAF_OAK);

						Block[(x - 1)][y].setID(BlockID.LEAF_OAK);
						Block[(x - 1)][(y + 1)].setID(BlockID.LEAF_OAK);
						Block[(x - 1)][(y + 2)].setID(BlockID.LEAF_OAK);
						Block[(x - 1)][(y + 2)].setID(BlockID.LEAF_OAK);

						Block[(x - 2)][(y + 1)].setID(BlockID.LEAF_OAK);
						Block[(x - 2)][(y + 2)].setID(BlockID.LEAF_OAK);

						Block[x][y].setID(BlockID.LEAF_OAK);
						Block[x][(y + 1)].setID(BlockID.LEAF_OAK);
						Block[x][(y + 2)].setID(BlockID.LEAF_OAK);
					}
				}
			}
		}
	}

	public static void genLake(Block[][] Block) {
		for (int y = 0 + offset; y < Block.length - offset; y++) {
			for (int x = 0 + offset; x < Block[0].length - offset; x++) {
				if ((x > 2) && (x < Block[0].length - 2)) {
					if ((Block[x][y].getID() == BlockID.AIR) && (Block[x][(y + 1)].getID() == BlockID.GRASS) && (Block[(x + 1)][y].getID() == BlockID.GRASS) && (Block[(x - 1)][y].getID() == BlockID.GRASS)) {
						Block[x][(y - 1)].setID(BlockID.WATERSOURCE);
					}
				}
			}
		}
	}
}

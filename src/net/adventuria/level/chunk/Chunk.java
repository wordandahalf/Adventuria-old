package net.adventuria.level.chunk;

import java.awt.Dimension;
import net.adventuria.block.Block;

public class Chunk {
	public static Dimension size = new Dimension(50, 50);
	public Block[][] Block = new Block[(int) size.getWidth()][(int) size
			.getHeight()];
}

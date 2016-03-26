package net.adventuria.level.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import net.adventuria.Component;
import net.adventuria.block.Block;
import net.adventuria.errorHandler.WorldSaveException;
import net.adventuria.level.Level;
import net.adventuria.level.World;

public class LevelSaver
{
	public static char DATA_SEPARATOR = '~';
			
	public static void Save(World w) throws WorldSaveException
	{
		List<Object> saveData = new ArrayList<>();
		
		saveData.add("chrpos: {" + Integer.toHexString(Component.world.getPlayer().getBlockX()) + ", " + Integer.toHexString(Component.world.getPlayer().getBlockY()) + "}" + DATA_SEPARATOR);
		saveData.add("crntchnk: {");
		
		for(Block[] bArray : w.getCurrentChunk().getBlocks())
		{
			for(Block b : bArray)
			{
				saveData.add(Integer.toHexString(b.getBlockX()) + "," + Integer.toHexString(b.getBlockY()) + "," + Integer.toHexString(b.getBlockType().getID()));
			}
		}
		
		saveData.add("}" + DATA_SEPARATOR);
		
		File f = new File("level.advlevel");
		
		if(f.exists())
		{
			if(!f.delete())
			{
				throw new WorldSaveException(0);
			}
		}
		
		try
		{
			f.createNewFile();
		}
		catch (IOException e)
		{
			throw new WorldSaveException(1);
		}
		
		PrintWriter pw = null;
		
		try
		{
			pw = new PrintWriter(f);
		}
		catch (FileNotFoundException e)
		{
			throw new WorldSaveException(2);
		}
		
		for(Object i : saveData)
		{
			pw.print(i);
		}
		
		pw.flush();
		pw.close();
	}
}

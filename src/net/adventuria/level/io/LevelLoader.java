package net.adventuria.level.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.adventuria.block.Block;
import net.adventuria.level.chunk.Chunk;

public class LevelLoader
{
	public static void Load(File f) throws IOException
	{	
		BufferedReader reader = null;
		
		List<String> file = new ArrayList<>();
		
		Block[][] loadedLevel = new Block[Chunk.WIDTH][Chunk.HEIGHT];
		
		try
		{
			reader = new BufferedReader(new FileReader(f));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		if(reader.ready())
		{
			while(reader.read() > -1)
			{
				file.add(reader.readLine());
			}
		}
		
		/*for(int x = 0; x < Chunk.WIDTH; x++)
		{
			for(int y = 0; y < Chunk.HEIGHT; y++)
			{
				
			}
		}*/
		
		for(Object o : file)
		{
			System.out.println(o);
		}
	}
}

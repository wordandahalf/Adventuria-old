package net.adventuria.level.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.adventuria.Component;
import net.adventuria.block.Block;
import net.adventuria.level.chunk.Chunk;
import net.adventuria.location.Location;

public class LevelLoader
{
	public static void Load(File f) throws IOException
	{	
		BufferedReader reader = null;
		
		List<String> file = new ArrayList<>();
		
		Block[][] loadedLevel = new Block[Chunk.WIDTH][Chunk.HEIGHT];
		
		List<Integer> chunkData = new ArrayList<>();
		
		int[] charData = new int[2];
		
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
			file.add(reader.readLine());
		}
		
		/*for(int x = 0; x < Chunk.WIDTH; x++)
		{
			for(int y = 0; y < Chunk.HEIGHT; y++)
			{
				
			}
		}*/
		
		charData = getPlayerPosition(file);
		
		Component.character.x = charData[0] * 20;
		
		Component.character.y = charData[1] * 20;
		
		Component.sX = charData[0] * 20 - (Component.pixel.width / 2D) + 10;
		
		Component.sY = charData[1] * 20 - (Component.pixel.height / 2D) + 10;
		
		for(Object o : file)
		{
			System.out.println(o);
		}
		
		reader.close();
	}
	
	private static int[] getPlayerPosition(List<String> data)
	{
		int[] pos = {0, 0};
		
		if(data.get(0).contains(new StringBuffer("chrpos: {")))
		{
			boolean passedComma = false;
			
			char currentChar = ' ';
			
			int searchX = "charpos:".length();
			
			String[] stringPos = {"", ""};
			
			while(currentChar != LevelSaver.DATA_SEPARATOR)
			{
				currentChar = data.get(0).charAt(searchX);
				
				if(currentChar == ',')
					passedComma = true;
				
				if(!passedComma && currentChar != '{' && currentChar != ' ')
				{
					stringPos[0] += currentChar;
				}
				
				if(passedComma && currentChar != '}' && currentChar != ' ' && currentChar != ',' && currentChar != LevelSaver.DATA_SEPARATOR)
				{
					stringPos[1] += currentChar;
				}
				
				searchX++;
			}
			
			pos[0] = Integer.valueOf(stringPos[0], 16);
			
			pos[1] = Integer.valueOf(stringPos[1], 16);
		}
		
		return pos;
	}
	
	private static List<Integer> getChunkData(File f)
	{
		
		return null;
	}
}

package net.adventuria.assets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.adventuria.errorHandler.MissingAssetsException;

public class AssetManager
{
	  public static int tileSize = 20;
	  
	  public static BufferedImage tileset_terrain;
	  public static BufferedImage tile_cell;
	  public static BufferedImage tile_select;
	  public static BufferedImage tileset_particle;
	  public static BufferedImage tileset_entity;
	  public static BufferedImage tileset_item;
	  
	  public AssetManager() throws MissingAssetsException
	  {
	    try
	    {
	      tileset_terrain = ImageIO.read(new File("res/tileset_terrain.png"));
	      tileset_particle = ImageIO.read(new File("res/tileset_particle.png"));
	      tile_cell = ImageIO.read(new File("res/tile_cell.png"));
	      tile_select = ImageIO.read(new File("res/tile_select.png"));
	      tileset_entity = ImageIO.read(new File("res/tileset_entity.png"));
	      tileset_item = ImageIO.read(new File("res/tileset_item.png"));
	    }
	    catch (IOException e)
	    {
	      throw new MissingAssetsException(0);
	    }
	  }
}

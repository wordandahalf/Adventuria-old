package net.adventuria.assets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

import net.adventuria.Component;
import net.adventuria.block.Block;
import net.adventuria.errorHandler.MissingAssetsException;
import net.adventuria.particle.Particle;

public class AssetManager {
	public static int tileSize = 20;

	private static HashMap<String, BufferedImage> textures = new HashMap<String, BufferedImage>();
	
	public static BufferedImage tileset_entity;
	public static BufferedImage tileset_item;

	public AssetManager() throws MissingAssetsException 
	{
		try
		{			
			//	----------
			//	| BLOCKS |
			//	----------
		
			BufferedImage tileset_terrain = ImageIO.read(new URL(Component.codeBase, "file:res/tileset_terrain.png"));
			
			textures.put("dirt", tileset_terrain.getSubimage(0, 0, Block.TILE_SIZE, Block.TILE_SIZE));
			textures.put("stone", tileset_terrain.getSubimage(20, 0, Block.TILE_SIZE, Block.TILE_SIZE));
			textures.put("sand", tileset_terrain.getSubimage(40, 0, Block.TILE_SIZE, Block.TILE_SIZE));
			textures.put("grass", tileset_terrain.getSubimage(60, 0, Block.TILE_SIZE, Block.TILE_SIZE));
			textures.put("bedrock", tileset_terrain.getSubimage(80, 0, Block.TILE_SIZE, Block.TILE_SIZE));
			textures.put("ore_coal", tileset_terrain.getSubimage(100, 0, Block.TILE_SIZE, Block.TILE_SIZE));
			textures.put("ore_iron", tileset_terrain.getSubimage(120, 0, Block.TILE_SIZE, Block.TILE_SIZE));
			textures.put("ore_gold", tileset_terrain.getSubimage(140, 0, Block.TILE_SIZE, Block.TILE_SIZE));
			textures.put("ore_diamond", tileset_terrain.getSubimage(160, 0, Block.TILE_SIZE, Block.TILE_SIZE));
			textures.put("ore_ruby", tileset_terrain.getSubimage(180, 0, Block.TILE_SIZE, Block.TILE_SIZE));
			textures.put("ore_emerald", tileset_terrain.getSubimage(200, 0, Block.TILE_SIZE, Block.TILE_SIZE));
			textures.put("ore_copper", tileset_terrain.getSubimage(220, 0, Block.TILE_SIZE, Block.TILE_SIZE));
			textures.put("ore_tin", tileset_terrain.getSubimage(240, 0, Block.TILE_SIZE, Block.TILE_SIZE));
			textures.put("ore_sapphire", tileset_terrain.getSubimage(260, 0, Block.TILE_SIZE, Block.TILE_SIZE));
			
			textures.put("wood_oak", tileset_terrain.getSubimage(0, 20, Block.TILE_SIZE, Block.TILE_SIZE));
			textures.put("water_source", tileset_terrain.getSubimage(140, 20, Block.TILE_SIZE, Block.TILE_SIZE));
			
			textures.put("leaves_oak", tileset_terrain.getSubimage(0, 40, Block.TILE_SIZE, Block.TILE_SIZE));
			
			//Technically not blocks, but still are stored in tileset_terrain.png
			
			textures.put("animation_break_0", tileset_terrain.getSubimage(20, 20, Block.TILE_SIZE, Block.TILE_SIZE));
			textures.put("animation_break_1", tileset_terrain.getSubimage(40, 20, Block.TILE_SIZE, Block.TILE_SIZE));
			textures.put("animation_break_2", tileset_terrain.getSubimage(60, 20, Block.TILE_SIZE, Block.TILE_SIZE));
			textures.put("animation_break_3", tileset_terrain.getSubimage(80, 20, Block.TILE_SIZE, Block.TILE_SIZE));
			textures.put("animation_break_4", tileset_terrain.getSubimage(100, 20, Block.TILE_SIZE, Block.TILE_SIZE));
			
			tileset_terrain.flush();
			
			//	-------------
			//	| PARTICLES |
			//	-------------
			
			BufferedImage tileset_particle = ImageIO.read(new URL(Component.codeBase, "file:res/tileset_particle.png"));

			textures.put("particle_heart_full", tileset_particle.getSubimage(0, 0, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_heart_half", tileset_particle.getSubimage(10, 0, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_heart_black_full", tileset_particle.getSubimage(20, 0, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_heart_black_half", tileset_particle.getSubimage(30, 0, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_heart_green_full", tileset_particle.getSubimage(40, 0, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_heart_green_half", tileset_particle.getSubimage(50, 0, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_heart_gold_full", tileset_particle.getSubimage(60, 0, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_heart_gold_half", tileset_particle.getSubimage(70, 0, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_heart_blue_full", tileset_particle.getSubimage(80, 0, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_heart_blue_half", tileset_particle.getSubimage(90, 0, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_meat_full", tileset_particle.getSubimage(0, 10, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_meat_half", tileset_particle.getSubimage(10, 10, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_meat_poisoned_full", tileset_particle.getSubimage(20, 10, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_meat_poisoned_half", tileset_particle.getSubimage(30, 10, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_meat_gold_full", tileset_particle.getSubimage(40, 10, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_meat_gold_half", tileset_particle.getSubimage(50, 10, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_heart_outline", tileset_particle.getSubimage(60, 10, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_bubble_full", tileset_particle.getSubimage(0, 20, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_bubble_half", tileset_particle.getSubimage(10, 20, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_mana_full", tileset_particle.getSubimage(0, 30, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_mana_3_4", tileset_particle.getSubimage(10, 30, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_mana_half", tileset_particle.getSubimage(20, 30, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			textures.put("particle_mana_1_4", tileset_particle.getSubimage(30, 30, Particle.PARTICLE_SIZE, Particle.PARTICLE_SIZE));
			
			tileset_particle.flush();
			
			//	-------
			//	| GUI |
			//	-------
			
			BufferedImage tile_cell = ImageIO.read(new URL(Component.codeBase, "file:res/tile_cell.png"));
			
			textures.put("gui_inventory_cell", tile_cell);
			
			tile_cell.flush();
			
			BufferedImage tile_select = ImageIO.read(new URL(Component.codeBase, "file:res/tile_select.png"));
			
			textures.put("gui_inventory_select", tile_select);
			
			tile_select.flush();
			
			//	---------------
			//	| ENVIRONMENT |
			//	---------------
			
			BufferedImage night_sky = ImageIO.read(new URL(Component.codeBase, "file:res/night_sky.png"));
			
			textures.put("sky_night", night_sky);
			
			night_sky.flush();
			
			BufferedImage cloud_map = ImageIO.read(new URL(Component.codeBase, "file:res/cloud_map.png"));
			
			textures.put("sky_cloudmap", cloud_map);
			
			cloud_map.flush();
			
			BufferedImage moon = ImageIO.read(new URL(Component.codeBase, "file:res/moon.png"));
			
			textures.put("sky_moon", moon);
			
			moon.flush();
			
			BufferedImage sun = ImageIO.read(new URL(Component.codeBase, "file:res/sun.png"));
			
			textures.put("sky_sun", sun);
			
			sun.flush();
			
			tileset_entity = ImageIO.read(new URL(Component.codeBase, "file:res/tileset_entity.png"));
			tileset_item = ImageIO.read(new URL(Component.codeBase, "file:res/tileset_item.png"));			
		}
		catch (IOException e)
		{
			throw new MissingAssetsException(0);
		}
	}
	
	public static BufferedImage getTexture(String alias) {
		return textures.get(alias);
	}
}

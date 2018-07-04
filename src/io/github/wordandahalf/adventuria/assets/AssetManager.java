package io.github.wordandahalf.adventuria.assets;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import io.github.wordandahalf.adventuria.AdventuriaGame;

public class AssetManager {
	public static final String NULL_TEXTURE = "null";
	
	private static HashMap<String, Image> 		textures = new HashMap<String, Image>();
	
	public static void load() {
		try {
			URL assetFolder = new URL(Paths.get("").toAbsolutePath().toUri().toURL(), "file:assets/");
			System.out.println("Loading assets from " + assetFolder.getPath());
			
			URL textureFolder = new URL(assetFolder, AdventuriaGame.class.getPackageName().replace(".", "/") + "/" + "textures");
			for(File texture : new File(textureFolder.toURI()).listFiles()) {
				textures.put(texture.getName().substring(0, texture.getName().lastIndexOf(".")), new Image(TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(texture.getAbsolutePath()))));
			}
			
			textures.put(NULL_TEXTURE, new Image(0, 0));
		} catch (IOException | URISyntaxException | SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static Image getTexture(String alias) { return textures.get(alias); }
}

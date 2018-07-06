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
	
	private static final HashMap<String, Image> textures = new HashMap<String, Image>();
	
	private static boolean hasStartedLoading = false;
	
	public static void load() {
		if(!hasStartedLoading) {
			try {
				hasStartedLoading = true;
				
				URL assetFolder = new URL(Paths.get("").toAbsolutePath().toUri().toURL(), "file:assets/");
				System.out.println("Loading assets from " + assetFolder.getPath());
				
				URL textureFolder = new URL(assetFolder, AdventuriaGame.class.getPackage().getName().replace(".", "/") + "/" + "textures");
				for(File textureFile : new File(textureFolder.toURI()).listFiles()) {
					Image texture = new Image(TextureLoader.getTexture("PNG", 
						ResourceLoader.getResourceAsStream(textureFile.getAbsolutePath())
					));
					
					textures.put(
						textureFile.getName().substring(0, textureFile.getName().lastIndexOf(".")), texture
					);
				}

				System.out.println("Loaded " + textures.values().size() + " textures!");
				
				textures.put(NULL_TEXTURE, new Image(0, 0));
			} catch (IOException | URISyntaxException | SlickException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Image getTexture(String alias) { return (textures.get(alias) != null ? textures.get(alias) : textures.get(NULL_TEXTURE)); }
}

package io.github.wordandahalf.adventuria.engine.rendering;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import io.github.wordandahalf.adventuria.AdventuriaGame;
import io.github.wordandahalf.adventuria.world.Chunk;
import io.github.wordandahalf.adventuria.world.block.Block;

public class Renderer {
	
	/*
	 * Background = sky, sun, clouds
	 * Entity = Entities (player, enemies, etc)
	 * Foreground = Blocks, environment
	 * Invisible = Things that can be ticked, but not rendered (or are out of chunk range)
	 */
	public static enum RenderPosition {
		BACKGROUND, ENTITY, FOREGROUND, UI, INVISIBLE;
	}

	private static final ArrayList<Renderable> BACKGROUND_OBJECTS = new ArrayList<>();
	private static final ArrayList<Renderable> ENTITIES = new ArrayList<>();
	private static final ArrayList<Renderable> FOREGROUND_OBJECTS = new ArrayList<>();
	private static final ArrayList<Renderable> UI_OBJECTS = new ArrayList<>();
	private static final ArrayList<Renderable> INVISIBLE_OBJECTS = new ArrayList<>();
	
	private static Camera camera = new Camera(0, 0, AdventuriaGame.WINDOW_SIZE.getWidth(), AdventuriaGame.WINDOW_SIZE.getHeight());
	
	public static Camera getCamera() { return camera; }
	
	public static void setCamera(Camera camera) {
		Renderer.camera = camera;
	}
	
	public static void add(Renderable o) {
		switch(o.getRenderPosition()) {
			case BACKGROUND:
				BACKGROUND_OBJECTS.add(o);
				break;
			case ENTITY:
				ENTITIES.add(o);
				break;
			case FOREGROUND:
				FOREGROUND_OBJECTS.add(o);
				break;
			case UI:
				UI_OBJECTS.add(o);
				break;
			default:
				INVISIBLE_OBJECTS.add(o);
				break;
		}
	}
	
	/* TODO: 
	 * See if it is more efficient to remove 'o' from all lists
	 * ==========================> OR <==========================
	 * Test which list it belongs to and only remove it from that list -----> [This is the current method]
	 */
	public static void remove(Renderable o) {
		switch(o.getRenderPosition()) {
			case BACKGROUND:
				BACKGROUND_OBJECTS.remove(o);
				break;
			case ENTITY:
				ENTITIES.remove(o);
				break;
			case FOREGROUND:
				FOREGROUND_OBJECTS.remove(o);
				break;
			case UI:
				UI_OBJECTS.remove(o);
				break;
			default:
				INVISIBLE_OBJECTS.remove(o);
				break;
		}
	}
	
	public static void render(Graphics g) {
		for(Renderable o : BACKGROUND_OBJECTS) {
			o.render(g, camera);
		}
		for(Renderable o : ENTITIES) {
			o.render(g, camera);
		}
		for(Renderable o : FOREGROUND_OBJECTS) {
			o.render(g, camera);
		}
		for(Renderable o : UI_OBJECTS) {
			o.render(g, camera);
		}
	}
}

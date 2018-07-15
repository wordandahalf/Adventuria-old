package io.github.wordandahalf.adventuria.engine.rendering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.newdawn.slick.Graphics;

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

	private static final List<Renderable> BACKGROUND_OBJECTS = new ArrayList<>();
	private static final List<Renderable> ENTITIES = new ArrayList<>();
	private static final List<Renderable> FOREGROUND_OBJECTS = new ArrayList<>();
	private static final List<Renderable> UI_OBJECTS = new ArrayList<>();
	private static final List<Renderable> INVISIBLE_OBJECTS = new ArrayList<>();
	
	private static Camera camera = new Camera(0, 0, WindowManager.WINDOW.getWidth(), WindowManager.WINDOW.getHeight());
	
	private static int drawnObjects = 0, draws = 0;
	public static int getDrawnObjects() { return drawnObjects; }
	
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
		draws = 0;
		
		try {
			for(Renderable o : Collections.synchronizedList(BACKGROUND_OBJECTS)) {
				if(o.render(g, camera))
					draws++;
			}
			for(Renderable o : Collections.synchronizedList(ENTITIES)) {
				if(o.render(g, camera))
					draws++;
			}
			for(Renderable o : Collections.synchronizedList(FOREGROUND_OBJECTS)) {
				if(o.render(g, camera))
					draws++;
			}
			for(Renderable o : Collections.synchronizedList(UI_OBJECTS)) {
				if(o.render(g, camera))
					draws++;
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		drawnObjects = draws;
	}
}

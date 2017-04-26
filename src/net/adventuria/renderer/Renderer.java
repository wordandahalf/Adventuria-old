package net.adventuria.renderer;

import java.awt.Graphics;
import java.util.ArrayList;

public class Renderer {
	
	/*
	 * Background = sky, sun, clouds
	 * Entity = Entities (player, enemies, etc)
	 * Foreground = Blocks, environment
	 * Invisible = Things that can be ticked, but not rendered (or are out of chunk range)
	 */
	public static enum RENDER_POSITION {
		BACKGROUND, ENTITY, FOREGROUND, INVISIBLE;
	}

	private final ArrayList<RenderableObject> BACKGROUND_OBJECTS = new ArrayList<>();
	private final ArrayList<RenderableObject> ENTITIES = new ArrayList<>();
	private final ArrayList<RenderableObject> FOREGROUND_OBJECTS = new ArrayList<>();
	private final ArrayList<RenderableObject> INVISIBLE_OBJECTS = new ArrayList<>();
	
	public void add(RenderableObject o) {
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
	public void remove(RenderableObject o) {
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
			default:
				INVISIBLE_OBJECTS.remove(o);
				break;
		}
	}
	
	public void render(Graphics g) {
		for(RenderableObject o : BACKGROUND_OBJECTS) {
			o.render(g);
			o.tick();
		}
		for(RenderableObject o : ENTITIES) {
			o.render(g);
			o.tick();
		}
		for(RenderableObject o : FOREGROUND_OBJECTS) {
			o.render(g);
			o.tick();
		}
		for(RenderableObject o : INVISIBLE_OBJECTS) {
			o.tick();
		}
	}
	
	public void cull() {
		
	}
}

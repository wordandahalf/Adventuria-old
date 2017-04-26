package net.adventuria.renderer;

import java.awt.Graphics;

public abstract class RenderableObject {
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract Renderer.RENDER_POSITION getRenderPosition();
	
	public void addToRenderer() {
		
	}
	
	public void removeFromRenderer() {
		
	}
}

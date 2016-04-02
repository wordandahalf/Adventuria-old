package net.adventuria.renderer;

public abstract class RenderableObject {
	
	public abstract void tick();
	
	public abstract void render();
	
	public abstract Renderer.RENDER_POSITION getRenderPosition();
	
	public void addToRenderer() {
		
	}
	
	public void removeFromRenderer() {
		
	}
}

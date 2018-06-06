package io.github.wordandahalf.adventuria.engine.rendering;

import org.newdawn.slick.Graphics;

public interface Renderable {
	public void render(Graphics g, Camera camera);
	
	public Renderer.RenderPosition getRenderPosition();
}

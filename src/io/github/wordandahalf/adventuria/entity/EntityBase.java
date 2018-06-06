package io.github.wordandahalf.adventuria.entity;

import org.newdawn.slick.Graphics;

import io.github.wordandahalf.adventuria.engine.physics.Collidable;
import io.github.wordandahalf.adventuria.engine.physics.Tickable;
import io.github.wordandahalf.adventuria.engine.rendering.Camera;
import io.github.wordandahalf.adventuria.engine.rendering.Renderable;
import io.github.wordandahalf.adventuria.engine.rendering.Renderer.RenderPosition;

public class EntityBase implements Renderable, Collidable, Tickable {

	@Override
	public void update() {
		
	}

	@Override
	public boolean collides(float x, float y) {
		return false;
	}

	@Override
	public void render(Graphics g, Camera camera) {
		
	}

	@Override
	public RenderPosition getRenderPosition() {
		return null;
	}

}

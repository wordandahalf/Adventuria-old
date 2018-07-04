package io.github.wordandahalf.adventuria.engine.physics;

public interface Collidable extends Tickable {	
	public boolean collides(float x, float y);
}

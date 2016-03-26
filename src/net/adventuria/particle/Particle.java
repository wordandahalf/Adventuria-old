package net.adventuria.particle;

import java.awt.Graphics;

import net.adventuria.assets.AssetManager;

public class Particle {
	public static int PARTICLE_SIZE = 10;
	
	private ParticleType type;
	private int x, y;
	
	public Particle(ParticleType type, int x, int y) {
		this.type = type;
		
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public ParticleType getType() {
		return this.type;
	}
	
	public void draw(Graphics g) {
		g.drawImage(AssetManager.getTexture(this.getType().getTextureAlias()), this.getX(), this.getY(), null);
	}
}

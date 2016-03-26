package net.adventuria.gui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import net.adventuria.Component;
import net.adventuria.particle.Particle;
import net.adventuria.particle.ParticleType;

public class HealthBar {
	public static int heartAmount = Component.world.getPlayer().getHealth() / 2, heartRemainder = Component.world.getPlayer().getHealth() % 2;
	public static int heartSpace = 10;
	
	private static List<Particle> hearts = new ArrayList<Particle>();
	private static List<Particle> heart_outlines = new ArrayList<Particle>();
	
	public HealthBar() {
		for (int i = 0; i < 10; i++) {
			hearts.add(new Particle(ParticleType.HEART_FULL, 65 + (heartSpace * i), 225));
			
			heart_outlines.add(new Particle(ParticleType.HEART_CONTAINER, 65 + (heartSpace * i), 225));
		}
	}

	public void update() {
		int fullHearts = Component.world.getPlayer().getHealth() / 2;
		int halfHearts = Component.world.getPlayer().getHealth() % 2;
		
		hearts.clear();
		
		for(int i = 0; i < fullHearts; i++) {
			hearts.add(new Particle(ParticleType.HEART_FULL, 65 + (heartSpace * i), 225));
		}
		
		for(int j = fullHearts; j < (fullHearts + halfHearts); j++) {
			hearts.add(new Particle(ParticleType.HEART_HALF, 65 + (heartSpace * j), 225));
		}
	}

	public void Render(Graphics g) {		
		for(Particle p : heart_outlines) {
			p.draw(g);
		}
		
		for (Particle p : hearts) {
			p.draw(g);
		}
	}
}

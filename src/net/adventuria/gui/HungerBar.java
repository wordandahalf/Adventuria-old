package net.adventuria.gui;

import java.awt.Graphics;
import net.adventuria.Component;
import net.adventuria.particle.Particle;
import net.adventuria.particle.ParticleType;

public class HungerBar {
	public static int hungerAmount = 10;
	public static int hungerSpace = 10;
	public static Particle[] hungers = new Particle[hungerAmount];

	public HungerBar() {
		for (int i = 0; i < hungers.length; i++) {
			hungers[i] = new Particle(ParticleType.HUNGER_FULL, 65 + (hungerSpace * i), 237);
		}
	}

	public void Render(Graphics g) {
		for (int i = 0; i < hungers.length; i++) {
			hungers[i].draw(g);
		}
	}
}

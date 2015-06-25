package net.adventuria.gui;

import java.awt.Graphics;
import net.adventuria.Component;
import net.adventuria.particle.Particle;

public class HungerBar {
	public static int hungerAmount = 10;
	public static int hungerSpace = 10;
	public static Particle[] hungers = new Particle[hungerAmount];

	public HungerBar() {
		for (int i = 0; i < hungers.length; i++) {
			hungers[i] = new Particle(Particle.HUNGER_FULL);
		}
	}

	public void Render(Graphics g) {
		for (int i = 0; i < hungers.length; i++) {
			hungers[i].Render(g, Component.pixel.width / 2 - i * hungerSpace
					+ (int) Component.sX - 20, (int) Component.sY
					+ (Component.pixel.height - 45));
		}
	}
}

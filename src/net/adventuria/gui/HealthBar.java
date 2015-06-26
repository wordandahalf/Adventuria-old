package net.adventuria.gui;

import java.awt.Graphics;
import net.adventuria.Component;
import net.adventuria.particle.Particle;

public class HealthBar {
	public static int heartAmount = Component.character.getHealth() / 2, heartRemainder = Component.character.getHealth() % 2;
	public static int heartSpace = 10;
	public static Particle[] hearts = new Particle[heartAmount];

	public HealthBar() {
		for (int i = 0; i < hearts.length; i++) {
			hearts[i] = new Particle(Particle.HEART_FULL);
		}
	}

	public void update() {
		heartAmount = Component.character.getHealth() / 2;
		heartRemainder = Component.character.getHealth() % 2;
		if (Component.character.getHealth() > 0) {
			hearts = new Particle[heartAmount + heartRemainder];
		} else {
			hearts = new Particle[0];
		}
		for (int i = 0; i < heartAmount; i++) {
			hearts[i] = new Particle(Particle.HEART_FULL);
		}
		for (int j = heartAmount; j < heartAmount + heartRemainder; j++) {
			hearts[j] = new Particle(Particle.HEART_HALF);
		}
	}

	public void Render(Graphics g) {
		for (int i = 0; i < 10; i++) {
			new Particle(Particle.HEART_CONTAINER).Render(g, Component.pixel.width / 2 - i * heartSpace + (int) Component.sX - 22, (int) Component.sY + (Component.pixel.height - 56));
		}
		for (int i = 0; i < hearts.length; i++) {
			hearts[i].Render(g, Component.pixel.width / 2 - i * heartSpace + (int) Component.sX - 22, (int) Component.sY + (Component.pixel.height - 56));
		}
	}
}

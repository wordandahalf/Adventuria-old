package net.adventuria.gui;

import java.awt.Graphics;
import net.adventuria.Component;
import net.adventuria.character.Character;
import net.adventuria.particle.Particle;

public class HealthBar
{
  public static int heartAmount = Character.health / 2;
  public static int heartSpace = 10;
  public static Particle[] hearts = new Particle[heartAmount];
  
  public HealthBar()
  {
    for (int i = 0; i < hearts.length; i++) {
      hearts[i] = new Particle(Particle.HEART_FULL);
    }
  }
  
  public void Render(Graphics g)
  {
    for (int i = 0; i < hearts.length; i++) {
      hearts[i].Render(g, Component.pixel.width / 2 - i * heartSpace + (int)Component.sX - 22, (int)Component.sY + (Component.pixel.height - 56));
    }
  }
}

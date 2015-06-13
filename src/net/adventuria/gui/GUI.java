package net.adventuria.gui;

import java.awt.Graphics;

public class GUI
{
  private static HealthBar healthBar;
  private static HungerBar hungerBar;
  private static ManaBar manaBar;
  
  public GUI()
  {
    healthBar = new HealthBar();
    
    hungerBar = new HungerBar();
    
    manaBar = new ManaBar();
  }
  
  public void Render(Graphics g)
  {
    healthBar.Render(g);
    
    hungerBar.Render(g);
    
    manaBar.Render(g);
    
    Debug.Render(g);
  }
}

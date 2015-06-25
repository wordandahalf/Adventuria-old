package net.adventuria.gui;

import java.awt.Graphics;

public class GUI
{
  public static HealthBar healthBar;
  public static HungerBar hungerBar;
  public static ManaBar manaBar;
  
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

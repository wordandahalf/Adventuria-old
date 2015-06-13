package net.adventuria.gui;

import java.awt.Graphics;
import net.adventuria.Component;
import net.adventuria.level.Sky;
import net.adventuria.level.Tile;

public class Debug
{
  public static boolean isDebugOpen = false;
  
  public static void Render(Graphics g)
  {
    if (isDebugOpen)
    {
      g.drawString(Component.GAME_TITLE + " v" + Component.GAME_VERSION, 5, 15);
      g.drawString("X: " + Component.character.x / Tile.tileSize, 5, 25);
      g.drawString("Y: " + Component.character.y / Tile.tileSize, 5, 35);
      g.drawString("Mouse X: " + Component.mouse.x, 5, 44);
      g.drawString("Mouse Y: " + Component.mouse.y, 5, 54);
      g.drawString("Time: " + Sky.dayFrame, 5, 64);
    }
  }
}

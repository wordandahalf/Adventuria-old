package net.adventuria.gui;

import java.awt.Color;
import java.awt.Graphics;
import net.adventuria.Component;
import net.adventuria.inputs.Mouse;
import net.adventuria.level.Sky;

public class Debug {
	public static boolean isDebugOpen = false;

	public static void Render(Graphics g) {
		if (isDebugOpen) {
			g.setColor(Color.white);
			g.drawString(Component.GAME_TITLE + " v" + Component.GAME_VERSION, 5, 15);
			g.drawString("X: " + Component.world.getPlayer().getBlockX(), 5, 25);
			g.drawString("Y: " + Component.world.getPlayer().getBlockY(), 5, 35);
			g.drawString("Mouse X: " + Mouse.getX(), 5, 44);
			g.drawString("Mouse Y: " + Mouse.getY(), 5, 54);
			g.drawString("Time: " + Sky.dayFrame, 5, 64);
			g.drawString("Biome: " + Component.world.getCurrentChunk().getBiome().getName(), 5, 74);
			g.drawString("Chunk ID: " + Component.world.getCurrentChunk().getID(), 5, 84);
			g.setColor(new Color(255, 255, 127, 200));
			int x1 = (int) Math.round(Component.world.getPlayer().getBoundingRectangle().x - Component.sX);
			int y1 = (int) Math.round(Component.world.getPlayer().getBoundingRectangle().y - Component.sY);
			g.drawRect(x1, y1, Component.world.getPlayer().getBoundingRectangle().width, Component.world.getPlayer().getBoundingRectangle().height);
			g.setColor(new Color(255, 127, 127, 200));
			g.drawRect((Component.world.getPlayer().getBlockX() * 20) - (int) Component.sX, Component.world.getPlayer().getBlockY() * 20 - (int) Component.sY, 20, 40);
		}
	}
}

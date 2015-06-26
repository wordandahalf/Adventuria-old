package net.adventuria.gui;

import java.awt.Color;
import java.awt.Graphics;

import net.adventuria.Component;
import net.adventuria.inputs.Mouse;
import net.adventuria.level.Sky;
import net.adventuria.block.Block;

public class Debug {
	public static boolean isDebugOpen = false;

	public static void Render(Graphics g) {
		if (isDebugOpen) {
			g.setColor(new Color(128, 128, 128, 105));
			g.fillRect(0, 0, 120, 68);
			g.setColor(Color.white);
			g.drawString(Component.GAME_TITLE + " v" + Component.GAME_VERSION, 5, 15);
			g.drawString("X: " + Component.character.getBlockX(), 5, 25);
			g.drawString("Y: " + Component.character.getBlockY(), 5, 35);
			g.drawString("Mouse X: " + Mouse.getX(), 5, 44);
			g.drawString("Mouse Y: " + Mouse.getY(), 5, 54);
			g.drawString("Time: " + Sky.dayFrame, 5, 64);
			g.setColor(new Color(255, 255, 127, 200));
			int x1 = (int) Math.round(Component.character.getBoundingRectangle().x - Component.sX);
			int y1 = (int) Math.round(Component.character.getBoundingRectangle().y - Component.sY);
			g.drawRect(x1, y1, Component.character.getBoundingRectangle().width, Component.character.getBoundingRectangle().height);
			g.setColor(new Color(255, 127, 127, 200));
			g.drawRect((Component.character.getBlockX() * 20) - (int) Component.sX, Component.character.getBlockY() * 20 - (int) Component.sY, 20, 40);
		}
	}
}

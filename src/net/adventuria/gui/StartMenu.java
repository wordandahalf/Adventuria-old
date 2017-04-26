package net.adventuria.gui;

import java.awt.Color;
import java.awt.Graphics;

import net.adventuria.Component;
import net.adventuria.renderer.RenderableObject;
import net.adventuria.renderer.Renderer.RENDER_POSITION;

public class StartMenu extends RenderableObject {

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.drawString("Main Menu", 300, 40);
	}

	@Override
	public RENDER_POSITION getRenderPosition() {
		return RENDER_POSITION.FOREGROUND;
	}
	
}

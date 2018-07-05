package io.github.wordandahalf.adventuria.world;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import io.github.wordandahalf.adventuria.engine.physics.PhysicsEngine;
import io.github.wordandahalf.adventuria.engine.physics.Tickable;
import io.github.wordandahalf.adventuria.engine.rendering.Camera;
import io.github.wordandahalf.adventuria.engine.rendering.Renderable;
import io.github.wordandahalf.adventuria.engine.rendering.Renderer.RenderPosition;
import io.github.wordandahalf.adventuria.engine.rendering.WindowManager;

public class Sky implements Renderable, Tickable {
	public static final int DAY = 0, NIGHT = 1, DUSK = 2, DAWN = 3, OVERCAST = 4;
	public static int time = DAWN;
	public static int dayFrame = 0;
	public static final int DAY_TIME = 18000, TRANSFER_TIME = 2500;
	public Color currentColor = Color.white, nextColor = Color.white, overcastColor = new Color(150, 150, 150), dayColor = new Color(79, 150, 238), nightColor = new Color(15, 8, 40), dawnColor = new Color(95, 89, 165), duskColor = new Color(64, 48, 127);
	//TODO: private int cloudX = 0, sunMoonX = -96, sunMoonY = 50;
	private double r = 0, g = 0, b = 0, starFade = 0;

	public Sky() {
		PhysicsEngine.add(this);
		
		switch (time) {
		case DAY:
			currentColor = dayColor;
			nextColor = duskColor;
			break;
		case NIGHT:
			currentColor = nightColor;
			nextColor = dawnColor;
			break;
		case DAWN:
			currentColor = dawnColor;
			nextColor = dayColor;
			break;
		case DUSK:
			currentColor = duskColor;
			nextColor = nightColor;
			break;
		}
		r = currentColor.getRed();
		g = currentColor.getGreen();
		b = currentColor.getBlue();
	}

	@Override
	public void update() {
		if (dayFrame >= (time == DAY || time == NIGHT ? DAY_TIME : TRANSFER_TIME)) {
			dayFrame = 0;
			currentColor = nextColor;
			time = (time == DAY ? DUSK : (time == NIGHT ? DAWN : (time == DUSK ? NIGHT : DAY)));
			nextColor = (time == DAY ? duskColor : (time == NIGHT ? dawnColor : (time == DUSK ? nightColor : dayColor)));
			r = currentColor.getRed();
			g = currentColor.getGreen();
			b = currentColor.getBlue();
		} else {
			dayFrame += 1;
		}
		if (time == NIGHT && dayFrame > (DAY_TIME * 0.75)) {
			r += ((nextColor.getRed() - nightColor.getRed()) / ((double) DAY_TIME * 0.25));
			g += ((nextColor.getGreen() - nightColor.getGreen()) / ((double) DAY_TIME * 0.25));
			b += ((nextColor.getBlue() - nightColor.getBlue()) / ((double) DAY_TIME * 0.25));
		} else if (time == DAY && dayFrame > (DAY_TIME * 0.75)) {
			r += ((nextColor.getRed() - dayColor.getRed()) / ((double) DAY_TIME * 0.25));
			g += ((nextColor.getGreen() - dayColor.getGreen()) / ((double) DAY_TIME * 0.25));
			b += ((nextColor.getBlue() - dayColor.getBlue()) / ((double) DAY_TIME * 0.25));
		} else if (time == DUSK && dayFrame > (TRANSFER_TIME * 0.75)) {
			r += ((nextColor.getRed() - duskColor.getRed()) / ((double) TRANSFER_TIME * 0.25));
			g += ((nextColor.getGreen() - duskColor.getGreen()) / ((double) TRANSFER_TIME * 0.25));
			b += ((nextColor.getBlue() - duskColor.getBlue()) / ((double) TRANSFER_TIME * 0.25));
		} else if (time == DAWN && dayFrame > (TRANSFER_TIME * 0.75)) {
			r += ((nextColor.getRed() - dawnColor.getRed()) / ((double) TRANSFER_TIME * 0.25));
			g += ((nextColor.getGreen() - dawnColor.getGreen()) / ((double) TRANSFER_TIME * 0.25));
			b += ((nextColor.getBlue() - dawnColor.getBlue()) / ((double) TRANSFER_TIME * 0.25));
		}
		if (r > 255)
			r = 255;
		if (g > 255)
			g = 255;
		if (b > 255)
			b = 255;
		if (r < 0)
			r = 0;
		if (g < 0)
			g = 0;
		if (b < 0)
			b = 0;
		currentColor = new Color((int) r, (int) g, (int) b);
	}

	@Override
	public void render(Graphics g, Camera cam) {
		g.setColor(currentColor);
		g.fillRect(0, 0, WindowManager.WINDOW.getWidth(), WindowManager.WINDOW.getHeight());
		if (time != DAY) {
			if(time == DUSK || time == DAWN) {
				double intervals = 500 / ((TRANSFER_TIME * 0.9) - (TRANSFER_TIME * 0.2));
				if (dayFrame > TRANSFER_TIME * 0.2)
					starFade += (time == DUSK ? -intervals : intervals);
			}
			if (starFade < 0)
				starFade = 0;
			if (starFade > 255)
				starFade = 255;
			//TODO: gr.drawImage(AssetManager.getTexture("sky_night"), 0, 0, Component.pixel.width, Component.pixel.height, null);
			g.setColor(new Color(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), (int) starFade));
			g.fillRect(0, 0, WindowManager.WINDOW.getWidth(), WindowManager.WINDOW.getHeight());
		}
		if (time == DAY) {
			//TODO: gr.drawImage(AssetManager.getTexture("sky_sun"), sunMoonX, sunMoonY, 80, 80, null);
		} else if (time == NIGHT) {
			//TODO: gr.drawImage(AssetManager.getTexture("sky_moon"), sunMoonX, sunMoonY, 40, 40, null);
		}
		/*if (dayFrame / (float) DAY_TIME < 0.4) {
			sunMoonY = 40 - (dayFrame / (DAY_TIME / 100));
		} else {
			sunMoonY = (dayFrame / (DAY_TIME / 100)) - 40;
		}*/
		//System.out.println(Math.sin(((float) dayFrame / DAY_TIME) / Component.WIDTH));
		
		//TODO: sunMoonY = 40 - (int) (60F * Math.sin((sunMoonX + 100) / (Component.realSize.getWidth() / 5)));
		
		//TODO: sunMoonX =  -100 + (int)(((float) dayFrame / DAY_TIME) * Component.realSize.getWidth() / 1.25);
		
		//TODO: cloudX++;
		//TODO: if ((cloudX / 15) - (Component.pixel.width * 6) > (Component.pixel.width * 6))
		//TODO:	cloudX = 0;
		//TODO: gr.drawImage(AssetManager.getTexture("sky_cloudmap"), (cloudX / 15) - (Component.pixel.width * 6), 0, Component.pixel.width * 6, 64, null);
	}

	@Override
	public RenderPosition getRenderPosition() {
		return RenderPosition.BACKGROUND;
	}
}

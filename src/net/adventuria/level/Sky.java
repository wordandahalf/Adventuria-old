package net.adventuria.level;

import java.awt.Color;
import java.awt.Graphics;

import net.adventuria.Component;
import net.adventuria.assets.AssetManager;

public class Sky {
	public static final int DAY = 0, NIGHT = 1, DUSK = 2, DAWN = 3;
	public static int time = DAWN;
	public static int dayFrame = 0;
	public static final int DAY_TIME = 18000, TRANSFER_TIME = 2500;
	public Color currentColor = Color.white, nextColor = Color.white,
			dayColor = new Color(70, 120, 230), nightColor = new Color(15, 8,
					40), dawnColor = new Color(92, 48, 181),
			duskColor = new Color(140, 113, 247);
	private int cloudX = 0, sunMoonX = -96, sunMoonY = 50, starFade = 255;
	private double r = 0, g = 0, b = 0;

	public Sky() {
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

	public void Tick() {
		if (dayFrame >= (time == DAY || time == NIGHT ? DAY_TIME
				: TRANSFER_TIME)) {
			dayFrame = 0;
			currentColor = nextColor;
			time = (time == DAY ? DUSK : (time == NIGHT ? DAWN
					: (time == DUSK ? NIGHT : DAY)));
			nextColor = (time == DAY ? duskColor : (time == NIGHT ? dawnColor
					: (time == DUSK ? nightColor : dayColor)));
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

	public void Render(Graphics gr) {
		gr.setColor(currentColor);
		gr.fillRect(0, 0, Component.pixel.width, Component.pixel.height);
		if (time == NIGHT) {
			if (dayFrame < 500)
				starFade -= 0.5;
			if (dayFrame > 16500)
				starFade++;
			if (starFade < 0)
				starFade = 0;
			if (starFade > 255)
				starFade = 255;
			gr.drawImage(AssetManager.night_sky, 0, 0, Component.pixel.width,
					Component.pixel.height, null);
			gr.setColor(new Color(currentColor.getRed(), currentColor
					.getGreen(), currentColor.getBlue(), starFade));
			gr.fillRect(0, 0, Component.pixel.width, Component.pixel.height);
		}
		if (time == DAY) {
			gr.drawImage(AssetManager.sun, sunMoonX, sunMoonY, 50, 50, null);
		} else if (time == NIGHT) {
			gr.drawImage(AssetManager.moon, sunMoonX, sunMoonY, 48, 48, null);
		}
		if (dayFrame / 9000 < 1) {
			sunMoonY = 50 - (dayFrame / 180);
		} else {
			sunMoonY = (dayFrame / 180) - 50;
		}
		sunMoonX = -96 + (dayFrame / 40);
		cloudX++;
		if ((cloudX / 15) - (Component.pixel.width * 6) > (Component.pixel.width * 6))
			cloudX = 0;
		gr.drawImage(AssetManager.cloud_map, (cloudX / 15)
				- (Component.pixel.width * 6), 0, Component.pixel.width * 6,
				64, null);
	}
}

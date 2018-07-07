package io.github.wordandahalf.adventuria.world;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import io.github.wordandahalf.adventuria.engine.physics.PhysicsEngine;
import io.github.wordandahalf.adventuria.engine.physics.Tickable;
import io.github.wordandahalf.adventuria.engine.rendering.Camera;
import io.github.wordandahalf.adventuria.engine.rendering.Renderable;
import io.github.wordandahalf.adventuria.engine.rendering.Renderer;
import io.github.wordandahalf.adventuria.engine.rendering.Renderer.RenderPosition;
import io.github.wordandahalf.adventuria.engine.rendering.WindowManager;

public class Sky implements Renderable, Tickable {	
	public static final int TICKS_PER_DAY = 18000;
	public static final int TICKS_PER_NIGHT = 18000;
	public static final int TICKS_PER_TRANSITION = TICKS_PER_DAY / 20;
	private static final int TICKS_PER_SKY_FRAME = 4;
	
	private static final int COLOR_ADJUST_RATE = 1;
	
	private static final Color DAY_SKY = new Color(70, 120, 230);
	private static final Color TRANSITION_SKY = new Color(255, 215, 0);
	private static final Color NIGHT_SKY = new Color(15, 15, 80);
	
	public static int ticksRemaining = TICKS_PER_DAY;
	public static int skyAnimationFrame = 0;

	private static boolean isDay = true, isTransitioning = false;
	
	private static Color skyColor = DAY_SKY;
	
	public Sky() {
		Renderer.add(this);
		PhysicsEngine.add(this);
	}

	public static boolean isDay()
	{
		return isDay;
	}
	
	@Override
	public void render(Graphics g, Camera camera)
	{
		g.setColor(skyColor);
		g.fillRect(0, 0, WindowManager.WINDOW.getWidth(), WindowManager.WINDOW.getHeight());
	}
	
	@Override
	public void update()
	{
		if (ticksRemaining <= 0)
			toggleDayNight();
		else
			ticksRemaining--;
		
		animateSky();
	}

	private void toggleDayNight()
	{		
		if(!isTransitioning)
		{
			isTransitioning = true;
			ticksRemaining = TICKS_PER_TRANSITION;
		}
		else
		if(isTransitioning)
		{
			isTransitioning = false;
			
			isDay = !isDay;
			
			if(isDay)
			{
				ticksRemaining = TICKS_PER_DAY;
			}
			else
			{
				ticksRemaining = TICKS_PER_NIGHT;
			}
		}
	}

	private void animateSky()
	{
		if (skyAnimationFrame >= TICKS_PER_SKY_FRAME)
		{
			if(isTransitioning)
			{
				skyColor = adjustColorTowardTarget(skyColor, TRANSITION_SKY);
			}
			else if (isDay)
			{
				skyColor = adjustColorTowardTarget(skyColor, DAY_SKY);
			}
			else
			{
				skyColor = adjustColorTowardTarget(skyColor, NIGHT_SKY);
			}
			
			skyAnimationFrame = 0;
		}
		else
		{
			skyAnimationFrame++;
		}
	}

	private Color adjustColorTowardTarget(Color color, Color target)
	{
		int[] colorRGB = new int[] {color.getRed(), color.getGreen(), color.getBlue()};
		int[] targetRGB = new int[] {target.getRed(), target.getGreen(), target.getBlue()};

		for (int i = 0; i < colorRGB.length; i++)
		{
			if (colorRGB[i] < targetRGB[i])
			{
				colorRGB[i] = Math.min(colorRGB[i] + COLOR_ADJUST_RATE, targetRGB[i]);
			}
			else if (colorRGB[i] > targetRGB[i])
			{
				colorRGB[i] = Math.max(colorRGB[i] - COLOR_ADJUST_RATE, targetRGB[i]);
			}
		}

		return new Color(colorRGB[0], colorRGB[1], colorRGB[2]);
	}

	@Override
	public RenderPosition getRenderPosition() {
		return RenderPosition.BACKGROUND;
	}
}

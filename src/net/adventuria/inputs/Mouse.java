package net.adventuria.inputs;

import java.awt.Point;

import net.adventuria.Component;

public class Mouse
{
	private static Point loc = new Point(0, 0);
	
	private static boolean leftButton = false;
	private static boolean rightButton = false;
	
	public static Point getLocation()
	{
		return loc;
	}
	
	public static void setLocation(Point pt)
	{
		loc = pt;
	}

	public static boolean isLeftButtonClicked() 
	{
		return leftButton;
	}

	public static void setLeftButton(boolean left)
	{
		leftButton = left;
	}

	public static boolean isRightButtonClicked() {
		return rightButton;
	}

	public static void setRightButton(boolean right)
	{
		rightButton = right;
	}
	
	public static int getX()
	{
		return loc.x;
	}
	
	public static int getY()
	{
		return loc.y;
	}
	
	public static int getBlockX()
	{
		return (int) Math.floor(((Mouse.getX() / Component.pixelSize) + Component.sX) / 20);
	}
	
	public static int getBlockY()
	{
		return (int) Math.floor(((Mouse.getY() / Component.pixelSize) + Component.sY) / 20);
	}
}

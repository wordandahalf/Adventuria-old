package net.adventuria.inputs;

import java.awt.Point;

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

	public static boolean isLeftButton() 
	{
		return leftButton;
	}

	public static void setLeftButton(boolean left)
	{
		leftButton = left;
	}

	public static boolean isRightButton() {
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
}

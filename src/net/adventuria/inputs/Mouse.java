package net.adventuria.inputs;

import net.adventuria.Component;
import net.adventuria.location.Location;

public class Mouse {
	private static Location loc = new Location(0, 0);

	private static boolean leftButton = false;
	private static boolean rightButton = false;

	public static Location getLocation() {
		return loc;
	}

	public static Location getBlockLocation() {
		return new Location(getBlockX(), getBlockY());
	}

	public static boolean isBlockInRange() {
		return Math.sqrt(Math.pow(getBlockX() - Component.character.getBlockX(), 2) + Math.pow(getBlockY() - Component.character.getBlockY(), 2)) <= 3;
	}

	public static void setLocation(Location l) {
		loc = l;
	}

	public static boolean isLeftButtonClicked() {
		return leftButton;
	}

	public static void setLeftButton(boolean left) {
		leftButton = left;
	}

	public static boolean isRightButtonClicked() {
		return rightButton;
	}

	public static void setRightButton(boolean right) {
		rightButton = right;
	}

	public static int getX() {
		return loc.getX();
	}

	public static int getY() {
		return loc.getY();
	}

	public static int getBlockX() {
		return (int) Math.floor(((Mouse.getX() / Component.pixelSize) + Component.sX) / 20);
	}

	public static int getBlockY() {
		return (int) Math.floor(((Mouse.getY() / Component.pixelSize) + Component.sY) / 20);
	}
}

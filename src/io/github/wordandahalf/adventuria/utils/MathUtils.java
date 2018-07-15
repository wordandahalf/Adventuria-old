package io.github.wordandahalf.adventuria.utils;

public class MathUtils {
	public static double distance(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
		return Math.sqrt(Math.pow((p2.left - p1.left), 2) + Math.pow((p2.right - p1.right), 2));
	}
}

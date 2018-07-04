package io.github.wordandahalf.adventuria.engine.physics;

import java.util.ArrayList;

public class PhysicsEngine {
	private static ArrayList<Tickable> tickables = new ArrayList<>();
	
	public static void add(Tickable tickable) {
		tickables.add(tickable);
	}
	
	public static void remove(Tickable tickable) {
		tickables.remove(tickable);
	}
	
	public static void update() {
		tickables.forEach((tickable) -> {
			tickable.update();
		});
	}
}

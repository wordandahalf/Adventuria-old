package io.github.wordandahalf.adventuria.engine.physics;

import java.util.ArrayList;

public class PhysicsEngine {
	public static final float 	UPDATES_PER_SECOND = 60;
	public static final float	MILLISECONDS_PER_UPDATE = (1 / UPDATES_PER_SECOND) * 1000;
	
	private static ArrayList<Tickable> tickables = new ArrayList<>();
	
	public static void add(Tickable tickable) {
		tickables.add(tickable);
	}
	
	public static void remove(Tickable tickable) {
		tickables.remove(tickable);
	}
	
	public static void update(int delta) {		
		tickables.forEach((tickable) -> {
			tickable.update();
		});
	}
}

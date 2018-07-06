package io.github.wordandahalf.adventuria.engine.physics;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PhysicsEngine {
	public static final float 	UPDATES_PER_SECOND = 20;
	public static final float	MILLISECONDS_PER_UPDATE = (1 / UPDATES_PER_SECOND) * 1000;
	
	private static ArrayList<Tickable> tickables = new ArrayList<>();
	
	private static ExecutorService physicsThread;
	
	public static void init() {
		physicsThread = Executors.newSingleThreadExecutor();
	}
	
	public static void add(Tickable tickable) {
		tickables.add(tickable);
	}
	
	public static void remove(Tickable tickable) {
		tickables.remove(tickable);
	}
	
	public static void update(int delta) {
		if(delta < MILLISECONDS_PER_UPDATE)
			physicsThread.submit(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep((long) (MILLISECONDS_PER_UPDATE - delta));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			
		tickables.forEach((tickable) -> {
			physicsThread.submit(new Runnable() {
				@Override
				public void run() {
					tickable.update();
				}
			});
		});
	}
}

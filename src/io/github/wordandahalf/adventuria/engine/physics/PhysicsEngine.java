package io.github.wordandahalf.adventuria.engine.physics;

import java.util.ArrayList;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PhysicsEngine {
	public static final double 	UPDATES_PER_SECOND = 60.0D;
	public static final double	MILLISECONDS_PER_UPDATE = (1.0D / UPDATES_PER_SECOND) * 1000.0D;
	
	private static ArrayList<Tickable> tickables = new ArrayList<>();
	
	private static ExecutorService physicsThread;
	
	private static int tps = 0;
	private static int ticks = 0;
	
	public static void init() {
		physicsThread = Executors.newSingleThreadExecutor();
		
		new java.util.Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				tps = ticks;
				ticks = 0;
			}
		}, 1000, 1000);
	}
	
	public static void add(Tickable tickable) {
		tickables.add(tickable);
	}
	
	public static void remove(Tickable tickable) {
		tickables.remove(tickable);
	}
	
	public static int getTPS() { return tps; }
	
	public static void update(int delta) {
		if(delta < MILLISECONDS_PER_UPDATE)
			physicsThread.submit(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep((long) Math.ceil(MILLISECONDS_PER_UPDATE - delta));
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
		
		physicsThread.submit(new Runnable() {
			@Override
			public void run() {
				ticks++;
			}
		});
	}
}

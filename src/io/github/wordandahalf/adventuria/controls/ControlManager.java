package io.github.wordandahalf.adventuria.controls;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Input;

public class ControlManager {
	private static ArrayList<Controllable> registeredControllables = new ArrayList<>();
	
	public static void updateInputs(Input input) {
		for(Controllable controllable : registeredControllables) {
			HashMap<Integer, Boolean> keyStates = new HashMap<>();
			
			for(Integer key : controllable.getRegisteredKeys()) {
				keyStates.put(key, input.isKeyDown(key));
			}
			
			controllable.updateInputs(keyStates);
		}
	}
	
	public static void add(Controllable controllable) {
		registeredControllables.add(controllable);
	}
	
	public static void remove(Controllable controllable) {
		registeredControllables.remove(controllable);
	}
}

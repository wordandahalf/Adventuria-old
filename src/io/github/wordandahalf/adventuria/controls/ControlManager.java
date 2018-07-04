package io.github.wordandahalf.adventuria.controls;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Input;

public class ControlManager {
	private static ArrayList<KeyboardControllable> registeredKeyboardControllables = new ArrayList<>();
	private static ArrayList<MouseControllable> registeredMouseControllables = new ArrayList<>();
	
	public static void updateInputs(Input input) {
		for(KeyboardControllable controllable : registeredKeyboardControllables) {
			HashMap<Integer, Boolean> keyStates = new HashMap<>();
			
			for(Integer key : controllable.getRegisteredKeys()) {
				keyStates.put(key, input.isKeyDown(key));
			}
			
			controllable.updateInputs(keyStates);
		}
		
		for(MouseControllable controllable : registeredMouseControllables) {
			HashMap<Integer, Boolean> buttonStates = new HashMap<>();
			
			for(Integer button : controllable.getRegisteredButtons()) {
				buttonStates.put(button, input.isMouseButtonDown(button));
			}
			
			controllable.updateInputs(buttonStates, input.getMouseX(), input.getMouseY());
		}
	}
	
	public static void add(KeyboardControllable controllable) {
		registeredKeyboardControllables.add(controllable);
	}
	
	public static void add(MouseControllable controllable) {
		registeredMouseControllables.add(controllable);
	}
	
	public static void remove(KeyboardControllable controllable) {
		registeredKeyboardControllables.remove(controllable);
	}
	
	public static void remove(MouseControllable controllable) {
		registeredMouseControllables.remove(controllable);
	}
}

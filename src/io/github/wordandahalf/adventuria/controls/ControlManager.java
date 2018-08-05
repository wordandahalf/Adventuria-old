package io.github.wordandahalf.adventuria.controls;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Input;

import io.github.wordandahalf.adventuria.AdventuriaGame;
import io.github.wordandahalf.adventuria.engine.physics.PhysicsEngine;
import io.github.wordandahalf.adventuria.engine.physics.Tickable;
import io.github.wordandahalf.adventuria.utils.Pair;

public class ControlManager implements Tickable {
	private static ArrayList<KeyboardControllable>	registeredKeyboardControllables = new ArrayList<>();
	private static ArrayList<MouseControllable> 	registeredMouseControllables 	= new ArrayList<>();
	
	private static HashMap<Integer, KeyState>		keyboardKeyStates				= new HashMap<>();
	
	private static HashMap<Integer, KeyState>		mouseButtonStates				= new HashMap<>();
	private static Pair<Integer, Integer>			mousePosition					= new Pair<Integer, Integer>(0, 0);
	
	public static void init() {
		PhysicsEngine.add(new ControlManager());
	}
	
	public static void add(KeyboardControllable controllable) {
		registeredKeyboardControllables.add(controllable);
	}
	
	public static void remove(KeyboardControllable controllable) {
		registeredKeyboardControllables.remove(controllable);
	}
	
	public static  void add(MouseControllable controllable) {
		registeredMouseControllables.add(controllable);
	}
	
	public static void remove(MouseControllable controllable) {
		registeredMouseControllables.remove(controllable);
	}
	
	@Override
	public void update() {
		Input input = AdventuriaGame.getAppContainer().getInput();
		
		updateKeyStates(input);
		updateButtonStates(input);
		
		for(KeyboardControllable c : registeredKeyboardControllables)
			c.updateInputs(keyboardKeyStates);
		
		for(MouseControllable c : registeredMouseControllables)
			c.updateInputs(mouseButtonStates, mousePosition.left, mousePosition.right);
	}
	
	private void updateKeyStates(Input input) {
		for(KeyboardControllable c : registeredKeyboardControllables) {
			for(int key : c.getRegisteredKeys()) {
				if((keyboardKeyStates.get(key) == KeyState.UP)
						&& (input.isKeyDown(key) == true)) {
					keyboardKeyStates.put(key, KeyState.TOGGLED);
				} 
				else if((keyboardKeyStates.get(key) == KeyState.TOGGLED) && (input.isKeyDown(key) == true)) {
					keyboardKeyStates.put(key, KeyState.DOWN);
				}
				else if((keyboardKeyStates.get(key) == KeyState.TOGGLED) && (input.isKeyDown(key) == false)) {
					keyboardKeyStates.put(key, KeyState.UP);
				} 
				else if(input.isKeyDown(key)) {
					keyboardKeyStates.put(key, KeyState.DOWN);
				}
				else if(!input.isKeyDown(key)) {
					keyboardKeyStates.put(key, KeyState.UP);
				}
			}
		}
	}
	
	private void updateButtonStates(Input input) {
		for(int button = 0; button < 2; button++) {
			if((keyboardKeyStates.get(button) == KeyState.UP)
					&& (input.isMouseButtonDown(button) == true)) {
				keyboardKeyStates.put(button, KeyState.TOGGLED);
			} 
			else if((keyboardKeyStates.get(button) == KeyState.TOGGLED)
					&& (input.isMouseButtonDown(button) == true)) {
				keyboardKeyStates.put(button, KeyState.DOWN);
			}
			else if((keyboardKeyStates.get(button) == KeyState.TOGGLED)
					&& (input.isMouseButtonDown(button) == false)) {
				keyboardKeyStates.put(button, KeyState.UP);
			} 
			else if(input.isMouseButtonDown(button)) {
				keyboardKeyStates.put(button, KeyState.DOWN);
			}
			else if(!input.isMouseButtonDown(button)) {
				keyboardKeyStates.put(button, KeyState.UP);
			}
		}
		
		mousePosition = Pair.of(input.getAbsoluteMouseX(), input.getAbsoluteMouseY());
	}
}

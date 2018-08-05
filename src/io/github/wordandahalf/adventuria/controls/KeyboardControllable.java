package io.github.wordandahalf.adventuria.controls;

import java.util.HashMap;

public interface KeyboardControllable {
	public void updateInputs(HashMap<Integer, KeyState> keyStates);
	
	public int[] getRegisteredKeys();
}

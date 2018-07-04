package io.github.wordandahalf.adventuria.controls;

import java.util.HashMap;

public interface KeyboardControllable {
	public void updateInputs(HashMap<Integer, Boolean> keyStates);
	
	public int[] getRegisteredKeys();
}

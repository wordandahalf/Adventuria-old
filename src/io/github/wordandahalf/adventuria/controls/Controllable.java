package io.github.wordandahalf.adventuria.controls;

import java.util.HashMap;

public interface Controllable {
	public void updateInputs(HashMap<Integer, Boolean> keyStates);
	
	public int[] getRegisteredKeys();
}

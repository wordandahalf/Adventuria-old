package io.github.wordandahalf.adventuria.controls;

import java.util.HashMap;

public interface MouseControllable {	
	public void updateInputs(HashMap<Integer, KeyState> buttonStates, int mouseX, int mouseY);
	
	public int[] getRegisteredButtons();
}

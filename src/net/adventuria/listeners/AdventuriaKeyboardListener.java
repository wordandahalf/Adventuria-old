package net.adventuria.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import net.adventuria.Component;
import net.adventuria.errorHandler.WorldSaveException;
import net.adventuria.gui.Debug;
import net.adventuria.gui.inventory.Inventory;
import net.adventuria.level.io.LevelLoader;
import net.adventuria.level.io.LevelSaver;

public class AdventuriaKeyboardListener implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case 68:
			if (!Inventory.isOpen) {
				Component.isMoving = true;
				Component.dir = Component.world.getPlayer().movementSpeed;
			}
			break;
		case 65:
			if (!Inventory.isOpen) {
				Component.isMoving = true;
				Component.dir = -Component.world.getPlayer().movementSpeed;
			}
			break;
		case 87:
			if (!Inventory.isOpen) {
				Component.isJumping = true;
			}
			break;
		case 49:
			Inventory.selected = 0;
			break;
		case 50:
			Inventory.selected = 1;
			break;
		case 51:
			Inventory.selected = 2;
			break;
		case 52:
			Inventory.selected = 3;
			break;
		case 53:
			Inventory.selected = 4;
			break;
		case 54:
			Inventory.selected = 5;
			break;
		case 55:
			Inventory.selected = 6;
			break;
		case 56:
			Inventory.selected = 7;
			break;
		case 114:
			Debug.isDebugOpen = !Debug.isDebugOpen;
			break;
		case 69:
			Inventory.isOpen = !Inventory.isOpen;
			break;
		case 70:
			try
			{
				LevelSaver.Save(Component.world);
			}
			catch (WorldSaveException wse)
			{
				wse.printStackTrace();
			}
			break;
		case 76:
			try
			{
				LevelLoader.Load(new File("level.advlevel"));
			} 
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
			break;
		case 27:
			Inventory.isOpen = false;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case 68:
			if (Component.dir == Component.world.getPlayer().movementSpeed) {
				Component.isMoving = false;
			}
			break;
		case 65:
			if (Component.dir == -Component.world.getPlayer().movementSpeed) {
				Component.isMoving = false;
			}
			break;
		case 87:
			Component.isJumping = false;
		}
	}
}

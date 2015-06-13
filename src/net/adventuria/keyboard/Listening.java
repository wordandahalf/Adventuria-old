package net.adventuria.keyboard;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import net.adventuria.Component;
import net.adventuria.block.BlockID;
import net.adventuria.gui.inventory.*;
import net.adventuria.gui.Debug;

public class Listening
  implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener
{
  public void keyPressed(KeyEvent e)
  {
    int key = e.getKeyCode();
    switch (key)
    {
    case 68: 
      if (!Inventory.isOpen)
      {
        Component.isMoving = true;
        Component.dir = Component.character.movementSpeed;
      }
      break;
    case 65: 
      if (!Inventory.isOpen)
      {
        Component.isMoving = true;
        Component.dir = -Component.character.movementSpeed;
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
    case 27: 
      Inventory.isOpen = false;
    }
  }
  
  public void keyReleased(KeyEvent e)
  {
    int key = e.getKeyCode();
    switch (key)
    {
    case 68: 
      if (Component.dir == Component.character.movementSpeed) {
        Component.isMoving = false;
      }
      break;
    case 65: 
      if (Component.dir == -Component.character.movementSpeed) {
        Component.isMoving = false;
      }
      break;
    case 87: 
      Component.isJumping = false;
    }
  }
  
  public void keyTyped(KeyEvent e) {}
  
  public void mouseWheelMoved(MouseWheelEvent e)
  {
    if (!Inventory.isOpen) {
      if (e.getWheelRotation() < 0)
      {
        if (Inventory.selected > 0) {
          Inventory.selected -= 1;
        } else if (Inventory.selected == 0) {
          Inventory.selected = Inventory.invLength - 1;
        }
      }
      else if (e.getWheelRotation() > 0) {
        if (Inventory.selected < Inventory.invLength - 1) {
          Inventory.selected += 1;
        } else if (Inventory.selected == Inventory.invLength - 1) {
          Inventory.selected = 0;
        }
      }
    }
  }
  
  public void mouseDragged(MouseEvent e)
  {
    Component.mouse = new Point(e.getX(), e.getY());
  }
  
  public void mouseMoved(MouseEvent e)
  {
    Component.mouse = new Point(e.getX(), e.getY());
  }
  
  public void mouseClicked(MouseEvent e)
  {

  }
  
  public void mouseEntered(MouseEvent e) {}
  
  public void mouseExited(MouseEvent e) {}
  
  public void mousePressed(MouseEvent e)
  {
    Component.mouse = new Point(e.getX(), e.getY());
    if (e.getButton() == 1) {
      Component.isLeftMouseButton = true;
    } else if (e.getButton() == 3) {
      Component.isRightMouseButton = true;
    }
    
    if (Inventory.isOpen)
    {
    	if(e.getButton() == 1)
    	{
	    	if(Inventory.currentHeldItemID == BlockID.AIR)
	    	{
		      for (int i = 0; i < Inventory.invBag.length; i++) {
		        if (Inventory.invBag[i].contains(new Point(Component.mouse.x / Component.pixelSize, Component.mouse.y / Component.pixelSize))) {
		          if ((Inventory.invBag[i].ID != BlockID.AIR) && (Inventory.currentHeldItemID == BlockID.AIR) && (Inventory.currentHeldItemCount == 0))
		          {
		            Inventory.currentHeldItemID = Inventory.invBag[i].ID;
		            Inventory.currentHeldItemCount = Inventory.invBag[i].Count;
		            
		            Inventory.invBag[i].ID = BlockID.AIR;
		            Inventory.invBag[i].Count = 0;
		          }
		        }
		      }
		      for (int i = 0; i < Inventory.invHotBar.length; i++) {
		        if (Inventory.invHotBar[i].contains(new Point(Component.mouse.x / Component.pixelSize, Component.mouse.y / Component.pixelSize))) {
		          if ((Inventory.invHotBar[i].ID != BlockID.AIR) && (Inventory.currentHeldItemID == BlockID.AIR) && (Inventory.currentHeldItemCount == 0))
		          {
		            Inventory.currentHeldItemID = Inventory.invHotBar[i].ID;
		            Inventory.currentHeldItemCount = Inventory.invHotBar[i].Count;
		            
		            Inventory.invHotBar[i].ID = BlockID.AIR;
		            Inventory.invHotBar[i].Count = 0;
		          }
		        }
		      }
		    }
		    else if ((Inventory.isOpen) && (Inventory.currentHeldItemID != BlockID.AIR))
		    {
		      for (int i = 0; i < Inventory.invBag.length; i++) {
		        if (Inventory.invBag[i].contains(new Point(Component.mouse.x / Component.pixelSize, Component.mouse.y / Component.pixelSize))) {
		          if ((Inventory.invBag[i].ID == BlockID.AIR) && (Inventory.currentHeldItemID != BlockID.AIR) && (Inventory.currentHeldItemCount != 0))
		          {
		            Inventory.invBag[i].ID = Inventory.currentHeldItemID;
		            Inventory.invBag[i].Count = Inventory.currentHeldItemCount;
		            
		            Inventory.currentHeldItemID = BlockID.AIR;
		            Inventory.currentHeldItemCount = 0;
		          }
		        }
		      }
		      for (int i = 0; i < Inventory.invHotBar.length; i++) {
		        if (Inventory.invHotBar[i].contains(new Point(Component.mouse.x / Component.pixelSize, Component.mouse.y / Component.pixelSize))) {
		          if ((Inventory.invHotBar[i].ID == BlockID.AIR) && (Inventory.currentHeldItemID != BlockID.AIR) && (Inventory.currentHeldItemCount != 0))
		          {
		            Inventory.invHotBar[i].ID = Inventory.currentHeldItemID;
		            Inventory.invHotBar[i].Count = Inventory.currentHeldItemCount;
		            
		            Inventory.currentHeldItemID = BlockID.AIR;
		            Inventory.currentHeldItemCount = 0;
		          }
		        }
		      }
		   }
	    }
    	else if(e.getButton() == 3)
    	{
    		if(Inventory.currentHeldItemID != BlockID.AIR)
    		{
    			if(Inventory.currentHeldItemCount > 1)
    			{
    				
    			}
    		}
    	}
  	}
  }
  
  public void mouseReleased(MouseEvent e)
  {
    Component.mouse = new Point(e.getX(), e.getY());
    if (e.getButton() == 1) {
      Component.isLeftMouseButton = false;
    } else if (e.getButton() == 3) {
      Component.isRightMouseButton = false;
    }
  }
}

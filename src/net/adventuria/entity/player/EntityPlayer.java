package net.adventuria.entity.player;

import java.awt.Graphics;
import java.awt.Point;

import net.adventuria.Component;
import net.adventuria.block.BlockID;
import net.adventuria.entity.EntityHuman;
import net.adventuria.gui.inventory.Inventory;
import net.adventuria.level.Tile;
import net.adventuria.location.Location;

public class EntityPlayer extends EntityHuman
{
	  public static int health = 20;
	  public double fallingSpeed = 1.0D;
	  public double movementSpeed = 0.5D;
	  public double jumpingSpeed = 1.0D;
	  public int jumpingHeight = 50;
	  public int jumpingCount = 0;
	  public int animation = 0;
	  public int animationFrame = 0;
	  public int animationTime = 30;
	  public boolean isJumping = false;
	
	  public EntityPlayer(Location loc)
	  {
		  super(loc);
	  }
	  
	  public void Tick()
	  {
	    if ((!this.isJumping) && (!isCollidingWithBlock(new Point((int)this.x + 2, (int)(this.y + this.height)), new Point((int)(this.x + this.width - 2.0D), (int)(this.y + this.height)))))
	    {
	      this.y += this.fallingSpeed;
	      Component.sY += this.fallingSpeed;
	    }
	    else if (Component.isJumping)
	    {
	      this.isJumping = true;
	    }
	    if ((Component.isMoving) && (!Inventory.isOpen))
	    {
	      boolean canMove = false;
	      if (Component.dir == this.movementSpeed) {
	        canMove = isCollidingWithBlock(new Point((int)(this.x + this.width), (int)this.y), new Point((int)(this.x + this.width), (int)(this.y + (this.height - 2.0D))));
	      } else if (Component.dir == -this.movementSpeed) {
	        canMove = isCollidingWithBlock(new Point((int)this.x - 1, (int)this.y), new Point((int)this.x - 1, (int)(this.y + this.height - 2.0D)));
	      }
	      if (this.animationFrame >= this.animationTime)
	      {
	        if (this.animation > 1) {
	          this.animation = 1;
	        } else {
	          this.animation += 1;
	        }
	        this.animationFrame = 0;
	      }
	      else
	      {
	        this.animationFrame += 1;
	      }
	      if (!canMove)
	      {
	        this.x += Component.dir;
	        Component.sX += Component.dir;
	      }
	    }
	    else
	    {
	      this.animation = 0;
	    }
	    if (this.isJumping) {
	      if (!isCollidingWithBlock(new Point((int)(this.x + 2.0D), (int)this.y), new Point((int)(this.x - this.width - 2.0D), (int)this.y)))
	      {
	        if (this.jumpingCount >= this.jumpingHeight)
	        {
	          this.isJumping = false;
	          this.jumpingCount = 0;
	        }
	        else
	        {
	          this.y -= this.jumpingSpeed;
	          Component.sY -= this.jumpingSpeed;
	          
	          this.jumpingCount += 1;
	        }
	      }
	      else
	      {
	        this.isJumping = false;
	        this.jumpingCount = 0;
	      }
	    }
	  }
	  
	  public boolean isCollidingWithBlock(Point pt1, Point pt2)
	  {
	    for (int x = (int)(this.x / Tile.tileSize); x < (int)(this.x / Tile.tileSize) + 3; x++) {
	      for (int y = (int)(this.y / Tile.tileSize); y < (int)(this.y / Tile.tileSize) + 3; y++) {
	        if ((x >= 0) && (y >= 0) && (x < Component.level.Block.length) && (y < Component.level.Block[0].length)) {
	          if ((Component.level.Block[x][y].getID() != BlockID.AIR) && (Component.level.Block[x][y].getID() != BlockID.WATERSOURCE)) {
	            if ((Component.level.Block[x][y].contains(pt1)) || (Component.level.Block[x][y].contains(pt2))) {
	              return true;
	            }
	          }
	        }
	      }
	    }
	    return false;
	  }
	  
	  public void Render(Graphics g)
	  {
	    if (Component.dir == this.movementSpeed) {
	      g.drawImage(Tile.tileset_terrain, (int)this.x - (int)Component.sX, (int)this.y - (int)Component.sY, (int)(this.width + this.x) - (int)Component.sX, (int)(this.height + this.y) - (int)Component.sY, Tile.Character[0] * Tile.tileSize + Tile.tileSize * this.animation, Tile.Character[1] * Tile.tileSize, Tile.Character[0] * Tile.tileSize + Tile.tileSize * this.animation + (int)this.width, Tile.Character[1] * Tile.tileSize + (int)this.height, null);
	    } else {
	      g.drawImage(Tile.tileset_terrain, (int)this.x - (int)Component.sX, (int)this.y - (int)Component.sY, (int)(this.width + this.x) - (int)Component.sX, (int)(this.height + this.y) - (int)Component.sY, Tile.Character[0] * Tile.tileSize + Tile.tileSize * this.animation + (int)this.width, Tile.Character[1] * Tile.tileSize, Tile.Character[0] * Tile.tileSize + Tile.tileSize * this.animation, Tile.Character[1] * Tile.tileSize + (int)this.height, null);
	    }
	  }
	}

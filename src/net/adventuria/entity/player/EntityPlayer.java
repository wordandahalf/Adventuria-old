package net.adventuria.entity.player;

import java.awt.Graphics;
import java.awt.Point;

import net.adventuria.Component;
import net.adventuria.assets.AssetManager;
import net.adventuria.block.Block;
import net.adventuria.block.BlockID;
import net.adventuria.entity.EntityHuman;
import net.adventuria.entity.EntityID;
import net.adventuria.gui.GUI;
import net.adventuria.gui.inventory.Inventory;
import net.adventuria.location.Location;

public class EntityPlayer extends EntityHuman
{
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
		  super(loc, EntityID.PLAYER);
	  }
	  
	  @Override
	  public void Tick()
	  {
	    if ((!this.isJumping) && (!isCollidingWithBlock(new Point((int)this.x + 2, (int)(this.y + this.height)), new Point((int)(this.x + this.width - 2.0D), (int)(this.y + this.height)))))
	    {
	      this.y += this.fallingSpeed;
	      Component.sY += this.fallingSpeed;
	      fallingSpeed+=fallingSpeed >= 3.5 ? 0 : 0.006;
	    }
	    else if (Component.isJumping)
	    {
	        this.isJumping = true;
	    }
	    if(isCollidingWithBlock(new Point((int)this.x + 2, (int)(this.y + this.height)), new Point((int)(this.x + this.width - 2.0D), (int)(this.y + this.height)))) {
	    	if(fallingSpeed > 1.4) {
	    		health -= Math.pow(2.25, fallingSpeed);
	    		GUI.healthBar.update();
	    	}
	    	fallingSpeed = 1;
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
	    for (int x = (int)(this.x / Block.tileSize); x < (int)(this.x / Block.tileSize) + 3; x++) {
	      for (int y = (int)(this.y / Block.tileSize); y < (int)(this.y / Block.tileSize) + 3; y++) {
	        if ((x >= 0) && (y >= 0) && (x < Component.level.Blocks.length) && (y < Component.level.Blocks[0].length)) {
	          if ((Component.level.Blocks[x][y].getID().isSolid())) {
	            if ((Component.level.Blocks[x][y].contains(pt1)) || (Component.level.Blocks[x][y].contains(pt2))) {
	              return true;
	            }
	          }
	        }
	      }
	    }
	    return false;
	  }
	  
	  @Override
	  public void Render(Graphics g)
	  {
	    if (Component.dir == this.movementSpeed) {
	      g.drawImage(AssetManager.tileset_entity, (int) (this.x - Component.sX), (int) (this.y - Component.sY), (int) ((this.width + this.x) - Component.sX), (int) ((this.height + this.y) - Component.sY), EntityID.PLAYER.getTextureID()[0] * Block.tileSize + Block.tileSize * this.animation, EntityID.PLAYER.getTextureID()[1] * Block.tileSize, EntityID.PLAYER.getTextureID()[0] * Block.tileSize + Block.tileSize * this.animation + (int)this.width, EntityID.PLAYER.getTextureID()[1] * Block.tileSize + (int)this.height, null);
	    } else {
	      g.drawImage(AssetManager.tileset_entity, (int) (this.x - Component.sX), (int) (this.y - Component.sY), (int) ((this.width + this.x) - Component.sX), (int) ((this.height + this.y) - Component.sY), EntityID.PLAYER.getTextureID()[0] * Block.tileSize + Block.tileSize * this.animation + (int)this.width, EntityID.PLAYER.getTextureID()[1] * Block.tileSize, EntityID.PLAYER.getTextureID()[0] * Block.tileSize + Block.tileSize * this.animation, EntityID.PLAYER.getTextureID()[1] * Block.tileSize + (int)this.height, null);
	    }
	  }
	  
	  @Override
	  public Location getLocation()
	  {
		  return new Location((int) this.x, (int) this.y);
	  }
	  
	  public Location getBlockLocation()
	  {
		  return new Location(this.getBlockX(), this.getBlockY());
	  }
	  
	  public int getBlockX()
	  {
		  return (int) Math.ceil(this.x / 20);
	  }
	  
	  public int getBlockY()
	  {
		  return (int) Math.ceil(this.y / 20);
	  }
	}

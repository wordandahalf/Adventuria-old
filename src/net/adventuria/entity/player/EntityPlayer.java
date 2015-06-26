package net.adventuria.entity.player;

import java.awt.Color;
import java.awt.Graphics;
import net.adventuria.Component;
import net.adventuria.assets.AssetManager;
import net.adventuria.block.Block;
import net.adventuria.entity.EntityHuman;
import net.adventuria.entity.EntityID;
import net.adventuria.gui.GUI;
import net.adventuria.gui.inventory.Inventory;
import net.adventuria.level.Level;
import net.adventuria.location.Location;

public class EntityPlayer extends EntityHuman {
	public double fallingSpeed = 1.0D;
	public double movementSpeed = 0.5D;
	public double jumpingSpeed = 1.0D;
	public int jumpingHeight = 50;
	public int jumpingCount = 0;
	public int animation = 0;
	public int animationFrame = 0;
	public int animationTime = 30;
	public boolean isJumping = false;

	public EntityPlayer(Location loc) {
		super(loc, EntityID.PLAYER);
		setBoundingTranslations(4, 2, -6, -2);
	}

	@Override
	public void Tick() {
		if ((!this.isJumping) && (!this.isCollidingWithBlock(new Location(getBlockX(), getBlockY() + 2), new Location(getBlockX() + 1, getBlockY() + 2)))) {
			this.y += this.fallingSpeed;
			Component.sY += this.fallingSpeed;
			fallingSpeed += fallingSpeed >= 3.5 ? 0 : 0.006;
		} else if (Component.isJumping) {
			this.isJumping = true;
		}
		if (this.isCollidingWithBlock(new Location(getBlockX(), getBlockY() + 2), new Location(getBlockX() + 1, getBlockY() + 2))) {
			if (fallingSpeed > 1.4) {
				setHealth((int) (getHealth() - Math.pow(2.25, fallingSpeed)));
				GUI.healthBar.update();
			}
			fallingSpeed = 1;
		}
		if ((Component.isMoving) && (!Inventory.isOpen)) {
			boolean canMove = false;
			if (Component.dir == this.movementSpeed) {
				canMove = this.isCollidingWithBlock(new Location(getBlockX() + 1, getBlockY()), new Location(getBlockX() + 1, getBlockY() + 1));
			} else if (Component.dir == -this.movementSpeed) {
				canMove = this.isCollidingWithBlock(new Location(getBlockX() - 1, getBlockY()), new Location(getBlockX() - 1, getBlockY() + 1));
			}
			if (this.animationFrame >= this.animationTime) {
				if (this.animation > 1) {
					this.animation = 1;
				} else {
					this.animation += 1;
				}
				this.animationFrame = 0;
			} else {
				this.animationFrame += 1;
			}
			if (!canMove) {
				this.x += Component.dir;
				if (x >= (Component.pixel.width / 2) + 10 && x <= (Level.worldW * Block.tileSize) - ((Component.pixel.width / 2) + 30))
					Component.sX += Component.dir;
			}
		} else {
			this.animation = 0;
		}
		if (this.isJumping) {
			if (!this.isCollidingWithBlock(new Location(getBlockX(), getBlockY()), new Location(getBlockX(), getBlockY() - 1))) {
				if (this.jumpingCount >= this.jumpingHeight) {
					this.isJumping = false;
					this.jumpingCount = 0;
				} else {
					this.y -= this.jumpingSpeed;
					Component.sY -= this.jumpingSpeed;

					this.jumpingCount += 1;
				}
			} else {
				this.isJumping = false;
				this.jumpingCount = 0;
			}
		}
	}

	@Override
	public void Render(Graphics g) {
		int xCoord = (int) Math.round(x - Component.sX);
		int yCoord = (int) Math.round(y - Component.sY);
		if (Component.dir == this.movementSpeed) {
			g.drawImage(AssetManager.tileset_entity, xCoord, yCoord, (int) this.width + xCoord, (int) this.height + yCoord, EntityID.PLAYER.getTextureID()[0] * Block.tileSize + Block.tileSize * this.animation, EntityID.PLAYER.getTextureID()[1] * Block.tileSize, EntityID.PLAYER.getTextureID()[0] * Block.tileSize + Block.tileSize * this.animation + (int) this.width, EntityID.PLAYER.getTextureID()[1] * Block.tileSize + (int) this.height, null);
		} else {
			g.drawImage(AssetManager.tileset_entity, xCoord, yCoord, (int) this.width + xCoord, (int) this.height + yCoord, EntityID.PLAYER.getTextureID()[0] * Block.tileSize + Block.tileSize * this.animation + (int) this.width, EntityID.PLAYER.getTextureID()[1] * Block.tileSize, EntityID.PLAYER.getTextureID()[0] * Block.tileSize + Block.tileSize * this.animation, EntityID.PLAYER.getTextureID()[1] * Block.tileSize + (int) this.height, null);
		}
		g.setColor(Color.yellow);
		int x1 = (int) Math.round(getBoundingRectangle().x - Component.sX);
		int y1 = (int) Math.round(getBoundingRectangle().y - Component.sY);
		g.drawRect(x1, y1, getBoundingRectangle().width, getBoundingRectangle().height);
	}

	@Override
	public Location getLocation() {
		return new Location((int) Math.round(this.x), (int) Math.round(this.y));
	}

	public Location getBlockLocation() {
		return new Location(getBlockX(), getBlockY());
	}
}

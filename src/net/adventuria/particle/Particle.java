package net.adventuria.particle;

import java.awt.Graphics;
import java.awt.Rectangle;

import net.adventuria.Component;
import net.adventuria.assets.AssetManager;

public class Particle extends Rectangle {
	public static int particleSize = 10;
	private static final long serialVersionUID = 1L;
	public static int[] SPRITE_NULL = { -1, -1 };
	public static int[] HEART_FULL = { 0, 0 };
	public static int[] HEART_CONTAINER = { 6, 1 };
	public static int[] HEART_HALF = { 1, 0 };
	public static int[] HEART_WITHER_FULL = { 2, 0 };
	public static int[] HEART_WITHER_HALF = { 3, 0 };
	public static int[] HEART_POISON_FULL = { 4, 0 };
	public static int[] HEART_POISON_HALF = { 5, 0 };
	public static int[] HEART_GOLDEN_FULL = { 6, 0 };
	public static int[] HEART_GOLDEN_HALF = { 7, 0 };
	public static int[] HEART_CRYSTAL_FULL = { 8, 0 };
	public static int[] HEART_CRYSTAL_HALF = { 9, 0 };
	public static int[] HUNGER_FULL = { 0, 1 };
	public static int[] HUNGER_HALF = { 1, 1 };
	public static int[] HUNGER_POISONED_FULL = { 2, 1 };
	public static int[] HUNGER_POISONED_HALF = { 3, 1 };
	public static int[] HUNGER_GOLDEN_FULL = { 4, 1 };
	public static int[] HUNGER_GOLDEN_HALF = { 5, 1 };
	public static int[] AIRBUBBLE_FULL = { 0, 2 };
	public static int[] AIRBUBBLE_HALF = { 1, 2 };
	public static int[] MANA_FULL = { 0, 3 };
	public static int[] MANA_34 = { 1, 3 };
	public static int[] MANA_HALF = { 2, 3 };
	public static int[] MANA_14 = { 3, 3 };
	public int[] ID = { -1, -1 };

	public Particle(int[] ID) {
		this.ID = ID;

		setBounds(new Rectangle(particleSize, particleSize));
	}

	public void Render(Graphics g, int x, int y) {
		if (this.ID != SPRITE_NULL) {
			g.drawImage(AssetManager.tileset_particle, x - (int) Component.sX,
					y - (int) Component.sY,
					this.width + x - (int) Component.sX, this.height + y
							- (int) Component.sY, this.ID[0] * particleSize,
					this.ID[1] * particleSize, this.ID[0] * particleSize
							+ this.width, this.ID[1] * particleSize
							+ this.height, null);
		}
	}
}

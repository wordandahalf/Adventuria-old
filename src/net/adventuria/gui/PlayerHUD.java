package net.adventuria.gui;

import java.awt.Graphics;

public class PlayerHUD {
	public static HealthBar healthBar;
	public static HungerBar hungerBar;
	public static ManaBar manaBar;
	public static AirBar airBar;

	public PlayerHUD() {
		healthBar = new HealthBar();

		hungerBar = new HungerBar();

		manaBar = new ManaBar();
		
		airBar = new AirBar();
	}

	public void Render(Graphics g) {
		healthBar.Render(g);
		healthBar.update();
		
		hungerBar.Render(g);

		manaBar.Render(g);
		
		airBar.Render(g);

		Debug.Render(g);
	}
}

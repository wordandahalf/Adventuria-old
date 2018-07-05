package io.github.wordandahalf.adventuria;

import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import io.github.wordandahalf.adventuria.assets.AssetManager;

public class SplashScreenState extends BasicGameState {
	private Image logo = null;
	
	private float logoWidth, logoHeight;
	
	private Timer timer;
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		this.logo = AssetManager.getTexture("banner_logo");
		
		float yRatio = ((float) container.getHeight() / (float) logo.getHeight());
		float xRatio = ((float) container.getWidth() / (float) logo.getWidth());
		
		float ratio = yRatio > xRatio ? xRatio : 1.0f;
		
		ratio *= 0.8f;
		
		logoWidth = logo.getWidth() * ratio;
		logoHeight = logo.getHeight() * ratio;
		
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				game.enterState(AdventuriaGame.MAIN_MENU_STATE, new FadeOutTransition(Color.white), new FadeInTransition(Color.black));
			}
		}, 2000L);
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.fillRect(0, 0, container.getWidth(), container.getHeight());
		
		g.drawImage(logo, 
			(container.getWidth() / 2) - (logoWidth / 2), (container.getHeight() / 2) - (logoHeight / 2),
			((container.getWidth() / 2) - (logoWidth / 2)) + logoWidth, ((container.getHeight() / 2) - (logoHeight / 2)) + logoHeight,
			0, 0, logo.getWidth(), logo.getHeight());
		g.flush();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {}

	@Override
	public int getID() {
		return 1;
	}
}

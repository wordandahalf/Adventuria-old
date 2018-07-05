package io.github.wordandahalf.adventuria;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import io.github.wordandahalf.adventuria.assets.AssetManager;

public class PreSplashState extends BasicGameState {
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		AssetManager.load();
		
		if(AdventuriaGame.DEBUG_MODE)
			game.enterState(AdventuriaGame.PLAY_GAME_STATE);
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.black);
		g.fillRect(0, 0, container.getWidth(), container.getHeight());
		g.flush();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {		
		game.enterState(AdventuriaGame.SPLASH_SCREEN_STATE, new FadeOutTransition(Color.white), new FadeInTransition(Color.white));
	}

	@Override
	public int getID() {
		return 0;
	}
}

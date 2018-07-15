package io.github.wordandahalf.adventuria;

import org.lwjgl.util.Dimension;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import io.github.wordandahalf.adventuria.engine.rendering.WindowManager;

public class AdventuriaGame extends StateBasedGame {
	public static final String GAME_TITLE = "Adventuria";
	public static final String GAME_VERSION = "0.5 Beta";

	public static boolean DEBUG_MODE = true;
	
	public static final int 	PRESPLASH_STATE 	= 0,
								SPLASH_SCREEN_STATE = 1,
								MAIN_MENU_STATE		= 2,
								PLAY_GAME_STATE		= 3;
	
	private static AppGameContainer gameContainer = null;
	
	public static AppGameContainer getAppContainer() { return gameContainer; }
	
	public AdventuriaGame() {
		super(GAME_TITLE);
	}
	
	public static void main(String[] args) {
		try {
			AppGameContainer game = new AppGameContainer(new AdventuriaGame());
			game.setTargetFrameRate(300);
			game.setShowFPS(false);
			WindowManager.WINDOW = new Dimension((int) (game.getScreenWidth() * 0.75f), (int) (game.getScreenHeight() * 0.75f));
			game.setDisplayMode(WindowManager.WINDOW.getWidth(), WindowManager.WINDOW.getHeight(), false);
			
			gameContainer = game;
			
			game.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(new PreSplashState());
		this.addState(new SplashScreenState());
		this.addState(new MainMenuState());
		this.addState(new PlayGameState());
	}
}

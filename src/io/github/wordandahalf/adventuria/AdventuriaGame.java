package io.github.wordandahalf.adventuria;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.Dimension;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import io.github.wordandahalf.adventuria.assets.AssetManager;
import io.github.wordandahalf.adventuria.engine.rendering.Renderer;
import io.github.wordandahalf.adventuria.world.WorldManager;

public class AdventuriaGame extends BasicGame {
	public static final String GAME_TITLE = "Adventuria";
	public static final String GAME_VERSION = "0.5 Beta";

	//TODO: Make separate window manager
	public static Dimension WINDOW_SIZE;
	
	private Image buffer;
	
	public AdventuriaGame() {
		super(GAME_TITLE);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		Graphics render = this.buffer.getGraphics();
		
		render.setBackground(Color.black);
		render.clear();
		Renderer.render(render);
		render.flush();
		
		g.drawImage(this.buffer, 0, 0);
		g.flush();
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		AssetManager.load();
		
		WINDOW_SIZE = new Dimension(container.getWidth(), container.getHeight());
		this.buffer = new Image(container.getWidth(), container.getHeight());
		
		WorldManager.addWorld("main");
		WorldManager.setCurrentWorld("main");
		WorldManager.getCurrentWorld().generate();
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {		
		//TODO: Separate input class
		float movementSpeed = 5f;
		
		if(container.getInput().isKeyDown(Keyboard.KEY_LSHIFT)) {
			movementSpeed /= 2;
		}
		
		if(container.getInput().isKeyDown(Keyboard.KEY_LCONTROL)) {
			movementSpeed *= 2;
		}
		
		if(container.getInput().isKeyDown(Keyboard.KEY_D)) {
			Renderer.getCamera().setX(Renderer.getCamera().getX() + movementSpeed);
		}
		
		if(container.getInput().isKeyDown(Keyboard.KEY_A)) {
			Renderer.getCamera().setX(Renderer.getCamera().getX() - movementSpeed);
		}
	}
	
	public static void main(String[] args) {
		try {
			AppGameContainer game = new AppGameContainer(new AdventuriaGame());
			game.setDisplayMode(840, 680, false);
			game.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}

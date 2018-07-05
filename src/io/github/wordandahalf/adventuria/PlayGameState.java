package io.github.wordandahalf.adventuria;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import io.github.wordandahalf.adventuria.controls.ControlManager;
import io.github.wordandahalf.adventuria.debug.MouseDebugTooltip;
import io.github.wordandahalf.adventuria.engine.physics.PhysicsEngine;
import io.github.wordandahalf.adventuria.engine.rendering.Renderer;
import io.github.wordandahalf.adventuria.world.WorldManager;

public class PlayGameState extends BasicGameState {
	private Image buffer;
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		try {
			this.buffer = new Image(container.getWidth(), container.getHeight());
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		WorldManager.addWorld("main");
		WorldManager.setCurrentWorld("main");
		WorldManager.getCurrentWorld().generate();
		
		if(AdventuriaGame.DEBUG_MODE)
			new MouseDebugTooltip();
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		Graphics render = null;
		try {
			render = buffer.getGraphics();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		render.setBackground(Color.black);
		render.clear();
		Renderer.render(render);
		render.flush();
		
		g.drawImage(buffer, 0, 0);
		g.flush();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		ControlManager.updateInputs(container.getInput());
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				PhysicsEngine.update(delta);
			}
		}).start();
	}

	@Override
	public int getID() {
		return 3;
	}
}

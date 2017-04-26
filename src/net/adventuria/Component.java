package net.adventuria;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

import javax.swing.JFrame;

import net.adventuria.assets.AssetManager;
import net.adventuria.errorHandler.MissingAssetsException;
import net.adventuria.gui.inventory.Inventory;
import net.adventuria.gui.PlayerHUD;
import net.adventuria.gui.StartMenu;
import net.adventuria.level.Sky;
import net.adventuria.level.World;
import net.adventuria.level.generator.DefaultGenerator;
import net.adventuria.listeners.AdventuriaKeyboardListener;
import net.adventuria.listeners.AdventuriaMouseListener;
import net.adventuria.renderer.Renderer;

public class Component extends Applet implements Runnable {
	private static final long serialVersionUID = 1L;
	public static int pixelSize = 2;
	public static int moveFromBorder = 0;
	public static double dir = 0.0D;
	public static Dimension realSize;
	public static Dimension size = new Dimension(700, 560);
	public static Dimension pixel = new Dimension(size.width / pixelSize, size.height / pixelSize);
	public static double sX = moveFromBorder;
	public static double sY = moveFromBorder;
	public static String GAME_TITLE = "Adventuria";
	public static String GAME_VERSION = "0.5 Beta";
	public static boolean isRunning = false;
	public static boolean isMoving = false;
	public static boolean isJumping = false;
	private Image screen;
	private Renderer renderer;
	public static World world;
	/*public static Level level;
	public static EntityPlayer character;*/
	public static Inventory inventory;
	public static Sky sky;
	public static PlayerHUD gui;
	public static URL codeBase;
	
	private static JFrame frame;

	public Component() {
		setPreferredSize(size);
		addKeyListener(new AdventuriaKeyboardListener());
		addMouseListener(new AdventuriaMouseListener());
		addMouseMotionListener(new AdventuriaMouseListener());
		addMouseWheelListener(new AdventuriaMouseListener());
	}

	public void start() {
		try {
			new AssetManager();
		} catch (MissingAssetsException e) {
			e.printStackTrace();
		}
		
		world = new World(new DefaultGenerator());
		inventory = new Inventory();
		sky = new Sky();
		gui = new PlayerHUD();
		

		isRunning = true;
		new Thread(this).start();
	}

	public void stop() {
		isRunning = false;
	}

	public static void main(String[] args) {
		Component component = new Component();

		frame = new JFrame();
		frame.add(component);
		frame.pack();

		realSize = new Dimension(frame.getWidth(), frame.getHeight());

		frame.setTitle(GAME_TITLE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(3);
		frame.setVisible(true);

		component.start();
	}

	public void Tick() {		
		if ((frame.getWidth() != realSize.width) || (frame.getHeight() != realSize.height)) {
			frame.pack();
		}
		
		world.update();
		
		sky.Tick();
	}

	public void Render() {
		Graphics g = this.screen.getGraphics();

		sky.Render(g);
		
		world.draw(g);
		
		if(Sky.time == Sky.NIGHT || Sky.time == Sky.DUSK) {
			//g.setColor(new Color(0, 0, 0, Sky.time == Sky.NIGHT ? (false ? 125 : (125 - ((Sky.dayFrame * 125) / Sky.DAY_TIME))) : ((Sky.dayFrame * 125) / Sky.TRANSFER_TIME)));
			//g.fillRect(0, 0, 800, 600);
		}
		
		gui.Render(g);
		inventory.Render(g);

		g = getGraphics();

		g.drawImage(this.screen, 0, 0, size.width, size.height, 0, 0, pixel.width, pixel.height, null);
		g.dispose();

		requestFocus();
	}
	
	
	
	public void run()
	{
		try
		{
			codeBase = null != System.getSecurityManager() ?  getCodeBase() : Paths.get("").toAbsolutePath().toUri().toURL();
		}
		catch (MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
		
		Component component = new Component();
		
		frame = new JFrame();
		frame.add(component);
		frame.pack();
		
		setSize(size);
		
		realSize = new Dimension(frame.getWidth(), frame.getHeight());
		
		this.screen = createVolatileImage(pixel.width, pixel.height);
		
		renderer = new Renderer();
		Graphics g = getGraphics();
		StartMenu s = new StartMenu();
		renderer.add(s);
		
		while (isRunning) {
			//renderer.render(g);
			Tick();
			Render();
			try {
				Thread.sleep(5L);
			} catch (Exception e) {
				System.out.println("[ERROR]: " + e.getMessage());
			}
		}
	}
}

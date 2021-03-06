package Window;

import java.awt.Canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;
import frameWork.KeyInput;
import frameWork.ObjectId;
import frameWork.Texture;
import objects.Player;

public class Game extends Canvas implements Runnable {
	public static int WIDTH, HEIGHT, Score = 0;
	private static final long serialVersionUID = -8921419424614180143L;
	private boolean running = false;
	private Thread thread;
	public BufferedImage level = null;
	// Object
	Handler handler;
	Camera cam;
	static Texture tex;
	public static int LEVEL = 1;
	Random rand = new Random();

	private void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight();
		tex = new Texture();
		BufferedImageLoad load = new BufferedImageLoad();
		level = load.loadImage("res/level1.png");// ?????? ????? ?? ??????
		cam = new Camera(0, 0);
		handler = new Handler(cam);

		handler.LoadImageLevel(level);
		handler.addObject(new Player(100, 100, handler, cam, ObjectId.Player));
		this.addKeyListener(new KeyInput(handler));
	}

	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	private void tick() {
		handler.tick();
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ObjectId.Player) {
				cam.tick(handler.object.get(i));
			}
		}

	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		// Draw here

		g.setColor(new Color(135, 206, 235));

		g.fillRect(0, 0, getWidth(), getHeight());
		g2d.translate(cam.getX(), cam.getY());// begun

		handler.render(g);

		g2d.translate(-cam.getX(), -cam.getY());// end
		g.dispose();
		bs.show();

	}

	public static Texture getInstance() {
		return tex;
	}

	public static void main(String[] args) throws InterruptedException {
		new Window(800, 600, "Game", new Game());
	}
}

package Window;

import java.awt.Graphics;


import java.awt.image.BufferedImage;
import java.util.LinkedList;

import frameWork.GameObject;
import frameWork.ObjectId;
import objects.Block;
import objects.Coins;
import objects.Flag;
import objects.Killed;
import objects.Player;

public class Handler {
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	private GameObject tempObject;
	private Camera cam;
	private BufferedImage level2 = null;
	private BufferedImage level3 = null;
	private BufferedImage level4 = null;
	public Handler(Camera cam) {
		this.cam = cam;
		BufferedImageLoad load = new BufferedImageLoad();
		level2 = load.loadImage("res/level2.png");// להכניס מיקום של התמונה
		level3 = load.loadImage("res/level3.png");// להכניס מיקום של התמונה
		level4 = load.loadImage("res/level4.png");// להכניס מיקום של התמונה
	}

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.tick(object);
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	public void LoadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				if (red == 255 && green == 255 && blue == 255) {
					addObject(new Block(xx * 32, yy * 32, 1, ObjectId.Block));
				}
				if (red == 195 && green == 195 && blue == 195) {
					addObject(new Block(xx * 32, yy * 32, 0, ObjectId.Block));
				}
				if (red == 195 && green == 0 && blue == 195) {
					addObject(new Block(xx * 32, yy * 32, 2, ObjectId.Block));
				}
				if (red == 0 && green == 0 && blue == 255) {
					addObject(new Player(xx * 32, yy * 32, this, cam, ObjectId.Player));
				}
				if (red == 255 && green == 216 && blue == 0) {
					addObject(new Flag(xx * 32, yy * 27, ObjectId.Flag));
				}
				if (red == 0 && green == 255 && blue == 0) {
					addObject(new Coins(xx * 32, yy * 32, ObjectId.Coins));
				}
				if (red == 255 && green == 0 && blue == 0) {
					addObject(new Killed(xx * 32, yy * 32,ObjectId.Killed));
				}
				if (red == 100 && green == 100 && blue == 100) {
					
				}
				
			}
		}

	}

	public void switchLevel() {
		clearLevel();
		cam.setX(0);
		cam.setY(0);
		
		switch(Game.LEVEL) {
		case 1:
			LoadImageLevel(level2);
			break;
		case 2:
			LoadImageLevel(level3);
			break;
		case 3:
			LoadImageLevel(level4);
			break;
			
		}
		System.out.println("Level: "+Game.LEVEL);
		Game.LEVEL++;
	}

	private void clearLevel() {
		object.clear();
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

}

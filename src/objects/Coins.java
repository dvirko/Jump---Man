package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import Window.Animation;
import Window.Game;
import frameWork.GameObject;
import frameWork.ObjectId;
import frameWork.Texture;

public class Coins extends GameObject {//מחלקה של המטבעות במשחק.
	Texture tex=Game.getInstance();
	private Animation coin;
	
	public Coins(float x, float y, ObjectId id) {
		super(x, y, id);
		coin= new Animation(4, tex.coins[0],tex.coins[1],tex.coins[2],tex.coins[3],tex.coins[4]);
	}

	public void tick(LinkedList<GameObject> object) {
		coin.runAnimation();
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		coin.drawAnimation(g,(int) x,(int)y);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int)y, 32, 32);

	}
}

package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import Window.Game;
import frameWork.GameObject;
import frameWork.ObjectId;
import frameWork.Texture;

public class Flag extends GameObject {
	Texture tex = Game.getInstance();

	public Flag(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> object) {

	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.drawImage(tex.flag[0], (int) x, (int) y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) 90, (int) 112 );

	}
}

package objects;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import frameWork.GameObject;
import frameWork.ObjectId;

public class Killed extends GameObject {
	public Killed(float x, float y, ObjectId id) {
		super(x, y, id);

	}

	public void tick(LinkedList<GameObject> object) {
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);

	}
}

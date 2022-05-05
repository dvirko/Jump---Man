package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import Window.Game;
import frameWork.GameObject;
import frameWork.ObjectId;
import frameWork.Texture;

public class Block extends GameObject {
	Texture tex = Game.getInstance();
	private int type;
	private float movement = 0,k=1;;

	public Block(float x, float y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}

	public void tick(LinkedList<GameObject> object) {

	}

	public void render(Graphics g) {
		if (type == 0) {
			g.drawImage(tex.block[0], (int) x, (int) y, null);
		}
		if (type == 1) {
			g.drawImage(tex.block[1], (int) x, (int) y, null);
		}
		if (type == 2) {
			Move();
			g.drawImage(tex.block[2], (int) (x + movement), (int) y, null);
		}
	}

	public Rectangle getBounds() {
		if (type == 2) {
			return new Rectangle((int) (x + movement), (int) y, 32, 32);
		} else {
			return new Rectangle((int) x, (int) y, 32, 32);
		}
		
	}


	private void Move() {
		if (movement >= 200) {
			k = -1;
		}
		if (movement == 0) {
			k = 1;
		}
		movement += k * 0.5f;
	}

}

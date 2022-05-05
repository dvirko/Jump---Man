package objects;

import java.awt.Color;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import Window.Animation;
import Window.Camera;
import Window.Game;
import Window.Handler;
import frameWork.GameObject;
import frameWork.ObjectId;
import frameWork.Texture;

public class Player extends GameObject {
	private float width = 48, height = 96;
	private float gravity = 0.5f;
	private Handler handler;
	private final float MAX_SPEED = 10;
	private int facing = 1;
	// 1=right , -1 = left
	Texture tex = Game.getInstance();
	private Animation playerWalk;
	private Animation playerWalkLeft;
	private Camera cam;

	public Player(float x, float y, Handler handler, Camera cam, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.cam = cam;

		playerWalk = new Animation(3, tex.player[0], tex.player[1], tex.player[2], tex.player[3], tex.player[4],
				tex.player[5]);
		playerWalkLeft = new Animation(3, tex.player[6], tex.player[7], tex.player[8], tex.player[9], tex.player[10],
				tex.player[11]);
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		if (velX < 0) {
			facing = -1;
		} else if (0 > facing) {
			facing = 1;
		}

		if (falling || jumping) {
			velY += gravity;
			if (velY > MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}
		Collision(object);
		playerWalk.runAnimation();
		playerWalkLeft.runAnimation();
	}

	private void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Coins) {// תחום ההגדרה של מטבעות
				if (getBoundsTop().intersects(tempObject.getBounds()) || getBounds().intersects(tempObject.getBounds())
						|| getBoundsLeft().intersects(tempObject.getBounds())
						|| getBoundsRight().intersects(tempObject.getBounds())) {
					Game.Score++;
					System.out.println(Game.Score);
					handler.removeObject(tempObject);
				}
			}
			if (tempObject.getId() == ObjectId.Killed) {
				if (getBoundsTop().intersects(tempObject.getBounds())) {
					Game.LEVEL--;
					handler.switchLevel();
				}
			}
			if (tempObject.getId() == ObjectId.Block) {// תחום ההגדרה של הבלוקים
				if (getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 32;
					velY = 0;
				}

				if (getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				} else {
					falling = true;
				}
				// Right
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - 50;
				}
				// Left
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 35;
				}

			} else if (tempObject.getId() == ObjectId.Flag) {
				// switch level.
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.switchLevel();
					cam.setX(0);
					cam.setY(0);
				}

			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		if (jumping) {
			if (facing == 1) {// If face to right
				g.drawImage(tex.player_jump[0], (int) x, (int) y, 48, 96, null);
			} else if (facing == -1) {// If face to left
				g.drawImage(tex.player_jump[3], (int) x, (int) y, 48, 96, null);
			}
		} else {
			if (velX != 0) {
				if (facing == 1) {// If face to right
					playerWalk.drawAnimation(g, (int) x, (int) y, 48, 96);
				}
				if (facing == -1) {// If face to left
					playerWalkLeft.drawAnimation(g, (int) x, (int) y, 48, 96);
				}
			} else {
				if (facing == 1) {// If face to right
					g.drawImage(tex.player[0], (int) x, (int) y, 48, 96, null);
				}
				if (facing == -1) {// If face to left
					g.drawImage(tex.player[6], (int) x, (int) y, 48, 96, null);
				}
			}
		}

	}

	public Rectangle getBounds() {
		return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)), (int) ((int) y + (height / 2)),
				(int) width / 2, (int) height / 2);
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)), (int) y, (int) width / 2,
				(int) height / 2);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int) x + width - 5), (int) y + 5, (int) 5, (int) height - 10);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10);
	}

}

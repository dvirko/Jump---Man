package frameWork;

import java.awt.image.BufferedImage;

public class SpriteSheets {
	private BufferedImage image;

	public SpriteSheets(BufferedImage image) {
		this.image = image;

	}

	public BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
		return img;
	}
}

package Window;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoad {
	private BufferedImage image;

	public BufferedImage loadImage(String path) {
		 image= null;
			try {
				image = ImageIO.read(new File(path));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return image;

	}
}
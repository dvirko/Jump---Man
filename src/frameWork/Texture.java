package frameWork;

import java.awt.image.BufferedImage;

import Window.BufferedImageLoad;

public class Texture {
	SpriteSheets bs, ps,cs,fs,as;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	private BufferedImage coins_sheet = null;
	private BufferedImage flag_sheet = null;
	public BufferedImage[] flag = new BufferedImage[1];
	public BufferedImage[] block = new BufferedImage[3];
	public BufferedImage[] coins = new BufferedImage[9];
	public BufferedImage[] player = new BufferedImage[12];
	public BufferedImage[] player_jump = new BufferedImage[4];

	public Texture() {
		BufferedImageLoad load = new BufferedImageLoad();
		try {
			block_sheet = load.loadImage("res/earth.JPG");// להכניס מיקום של התמונה
			player_sheet = load.loadImage("res/char.png");// להכניס מיקום של התמונה
			coins_sheet = load.loadImage("res/coin.png");// להכניס מיקום של התמונה
			flag_sheet = load.loadImage("res/bathroom.png");// להכניס מיקום של התמונה

		} catch (Exception e) {
		}
		bs = new SpriteSheets(block_sheet);
		ps = new SpriteSheets(player_sheet);
		cs=new SpriteSheets(coins_sheet);
		fs=new SpriteSheets(flag_sheet);
		getTexures();
	}

	private void getTexures() {
		//Coins
		coins[0]=cs.grabImage(1, 4,26, 36);
		coins[1]=cs.grabImage(2, 4,28, 36);
		coins[2]=cs.grabImage(3, 4,30, 36);
		coins[3]=cs.grabImage(4, 4,34, 36);
		coins[4]=cs.grabImage(5, 4,36, 36);
		
		
		//Flag
		
		flag[0] = fs.grabImage(1, 1,93, 112);
		
		//Blocks
		
		block[0] = bs.grabImage(2, 1, 32, 32);// grass block
		block[1] = bs.grabImage(4, 2, 32, 32);// dirt block
		block[2] = bs.grabImage(3, 2, 32, 32);//move block
		
		//Right
			
		player[0] = ps.grabImage(1, 4, 32, 64);// man body
		player[1] = ps.grabImage(2, 4, 32, 64);// man body
		player[2] = ps.grabImage(3, 4, 32, 64);// man body
		player[3] = ps.grabImage(4, 4, 32, 64);// man body
		player[4] = ps.grabImage(5, 4, 32, 64);// man body
		player[5] = ps.grabImage(6, 4, 32, 64);// man body
		
		//Left
		
		player[6] = ps.grabImage(1, 3, 32, 64);// man body
		player[7] = ps.grabImage(2, 3, 32, 64);// man body
		player[8] = ps.grabImage(3, 3, 32, 64);// man body
		player[9] = ps.grabImage(4, 3, 32, 64);// man body
		player[10] = ps.grabImage(5, 3, 32, 64);// man body
		player[11] = ps.grabImage(6, 3, 32, 64);// man body
		
		//jump_right
		
		player_jump[0] = ps.grabImage(5, 4, 32, 64);// man body
		player_jump[1] = ps.grabImage(1, 4, 32, 64);// man body
		
		//jump_left
		
		player_jump[2] = ps.grabImage(1, 3, 32, 64);// man body
		player_jump[3] = ps.grabImage(3, 3, 32, 64);// man body

		
		
	}
}

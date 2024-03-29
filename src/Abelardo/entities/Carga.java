package Abelardo.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Abelardo.word.Camera;
import Game.Game;

public class Carga extends Entity{
	
	private int frames = 0, maxframes = 10, index = 0, maxindex = 3;
	private BufferedImage[] sprites2;

	public Carga(int x, int y, int width, int heigth, BufferedImage sprite) {
		super(x, y, width, heigth, sprite);
		sprites2 = new BufferedImage[4];
		sprites2[0] = Game.spritesheet.getSprite(62, 53, 21, 15);
		sprites2[1] = Game.spritesheet.getSprite(87, 53, 22, 15);
		sprites2[2] = Game.spritesheet.getSprite(111, 53, 22, 15);
		sprites2[3] = Game.spritesheet.getSprite(134, 53, 23, 15);
	}
	
	public void render(Graphics g) {
		
		g.drawImage(sprites2[index], this.getX() - Camera.x,this.getY()-Camera.y, null);
				
	}
	public void tick() {
		
		frames++;
		if(frames==maxframes) {
			frames=0;
			index++;
			if(index>maxindex) {
				index=0;
			}
		}
	}

}

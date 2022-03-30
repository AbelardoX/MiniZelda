package Abelardo.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Abelardo.word.Camera;
import Abelardo.word.Word;
import Game.Game;

public class Inimigo extends Entity {
	
	private double spd = 1 ;
	
	private int maskx =0, masky = 0,maskw =12, maskh=15;
	private int frames = 0, maxframes = 15, index = 0, maxindex = 3;
	private BufferedImage[] sprites;
	public double dano = 1.5;
	
	private int vidae = 10;

	public Inimigo(int x, int y, int width, int heigth, BufferedImage sprite) {
		super(x, y, width, heigth, null);
		sprites = new BufferedImage[4];
		sprites[0] = Game.spritesheet.getSprite(95, 36, 15, 14);
		sprites[1] = Game.spritesheet.getSprite(111, 36, 15, 14);
		sprites[2] = Game.spritesheet.getSprite(127, 36, 15, 14);
		sprites[3] = Game.spritesheet.getSprite(143, 36, 15, 14);
	}
	
	public void tick() {
		
		//maskx = 0;
		//masky = 0;
		//maskw = 15;
		//maskh = 15;
		
		if(this.isCollidingPlayer() == false) {
		if((int)x < Game.player.getX() && Word.isFree((int) (x+spd), this.getY())
				&& !isColliding((int) (x+spd), this.getY())) {
			this.x+=spd;
		}else if((int)x > Game.player.getX()&& Word.isFree((int) (x-spd), this.getY())
				&& !isColliding((int) (x-spd), this.getY())) {
			this.x-=spd;
		}if((int)y < Game.player.getY()&& Word.isFree(this.getX(), (int) (y+spd))
				&& !isColliding(this.getX(), (int) (y+spd))) {
			this.y+=spd;
		}else if((int)y > Game.player.getY()&& Word.isFree(this.getX(), (int) (y-spd))
				&& !isColliding(this.getX(), (int) (y-spd))) {
			this.y-=spd;
		}
		}else {
			if(Game.rand.nextInt(100)<50) {
			Game.player.vida -= dano;
			Game.player.IsDano = true;
			//System.out.println(Game.player.vida);
			}
		}
		

			frames++;
			if(frames==maxframes) {
				frames=0;
				index++;
				if(index>maxindex) { 
					index=0;
				}
			}
			
			collisionBala();
			if(vidae<=0) {
				destroySelf();
				return;
			}
		
	}
	
	public void destroySelf() {
		
		Game.entities.remove(this);
	}
	
	public void collisionBala() {
		for(int i = 0; i < Game.balas.size();i++) {
			Entity e = Game.balas.get(i);
			if(e instanceof	TiroFogo) {
				if(Entity.isColliding(this, e)) {					
					vidae-=2;
					Game.balas.remove(e);
					return;
				}
				
			}
			
			
		}
		

		
	}
	
	public boolean isCollidingPlayer() {
		Rectangle inimigoCurrent = new Rectangle(this.getX() + maskx,this.getY() + masky,maskw, maskh);
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),16 ,16);
		
		
		return inimigoCurrent.intersects(player);
	}
	
	
		
	public boolean isColliding(int xNext, int yNext) {
		Rectangle inimigoCurrent = new Rectangle(xNext + maskx,yNext + masky,maskw, maskh);
		
		for(int i = 0; i< Game.inimigos.size();i++) {
			Inimigo e = Game.inimigos.get(i);
			if(e == this) 
				continue;
		Rectangle TargetInimigo =  new Rectangle(e.getX() + maskx,e.getY() + masky,maskw, maskh);
		if(inimigoCurrent.intersects(TargetInimigo)){
			return true;
		}
			
		}

		return false;
	}
	
	public void render(Graphics g) {
		
		g.drawImage(sprites[index], this.getX() - Camera.x,this.getY()-Camera.y, null);
		
		
		//g.setColor(Color.red);
		//g.fillRect(this.getX() + maskx - Camera.x,this.getY() + masky - Camera.y, maskw, maskh);
		
	}

}

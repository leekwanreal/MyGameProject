package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Projectile extends Entity{
	Entity user;
	
	public Projectile(GamePanel gp) {
		super(gp);
	}
	
	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
	}
	
	public void update() {
		if (user == gp.player) {
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			if (monsterIndex != 999) {
				gp.player.damageMonster(monsterIndex, attack);
				generateParticle(user.projectile, gp.monster[gp.currentMap][monsterIndex]);
				alive = false;
			}
		}
		
		if (user != gp.player) {
			boolean contactPlayer = gp.cChecker.checkPlayer(this);
			if (gp.player.invincible == false && contactPlayer == true) {
				damagePlayer(attack);
				generateParticle(user.projectile, gp.player);
				alive = false;
			}
		}
		
		switch(direction) {
		case "up": worldY -= speed; break;
		case "down": worldY += speed; break;
		case "left": worldX -= speed; break;
		case "right": worldX += speed; break;
		}
		
		life--;
		if (life <= 0) {
			alive = false;
		}
		
		spriteCounter++;
		if (spriteCounter > 12) {
			if (spriteNum == 1) {
				spriteNum = 2;
			}
			else if (spriteNum == 1) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	} 
	
	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		return haveResource;
	}
	
	public void subtractResource(Entity user) {}

	/* 
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
		if (spriteNum == 1) {image = up1;}
		if (spriteNum == 2) {image = up2;}
		break;
		case "down":
		if (spriteNum == 1) {image = down1;}
		if (spriteNum == 2) {image = down2;}
		break;
		case "left":
		if (spriteNum == 1) {image = left1;}
		if (spriteNum == 2) {image = left2;}
		break;
		case "right":
		if (spriteNum == 1) {image = right1;}
		if (spriteNum == 2) {image = right2;}
		break;
		}
		
		int x = gp.player.screenX;
		int y = gp.player.screenY;
		
		if (x > worldX) {
			x = worldX;
			if (direction.equals("left")) {
				x -= gp.tileSize;
			}
		}
		if (y > worldY) {
			y = worldY;
			if (direction.equals("up")) {
				y -= gp.tileSize;
			}
		}
		int rightOffset = gp.screenWidth - x;
		if (rightOffset > gp.worldWidth - worldX) {
			x = gp.screenWidth - (gp.worldWidth - worldX);
			if (direction.equals("left")) {
				x -= gp.tileSize;
			}
		}
		int bottomOffset = gp.screenHeight - y;
		if (bottomOffset > gp.worldHeight - worldY) {
			y = gp.screenHeight - (gp.worldHeight - worldY);
			if (direction.equals("up")) {
				y -= gp.tileSize;
			}
		}

		g2.drawImage(image, x, y, null);
	
	}
	*/
}

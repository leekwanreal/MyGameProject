package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Arrow extends Projectile {
	GamePanel gp;
	
	public OBJ_Arrow(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Arrow";
		speed = 20;
		maxLife = 80;
		life = maxLife;
		attack = 4;
		useCost = 0;
		alive = false;
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/projectile/Arrow_up.png", gp.tileSize*2/3, gp.tileSize);
		up2 = setup("/projectile/Arrow_up.png", gp.tileSize*2/3, gp.tileSize);
		down1 = setup("/projectile/Arrow_down.png", gp.tileSize*2/3, gp.tileSize);
		down2 = setup("/projectile/Arrow_down.png", gp.tileSize*2/3, gp.tileSize);
		left1 = setup("/projectile/Arrow_left.png", gp.tileSize, gp.tileSize*2/3);
		left2 = setup("/projectile/Arrow_left.png", gp.tileSize, gp.tileSize*2/3);
		right1 = setup("/projectile/Arrow_right.png", gp.tileSize, gp.tileSize*2/3);
		right2 = setup("/projectile/Arrow_right.png", gp.tileSize, gp.tileSize*2/3);	
	}
	
	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		if (user.ammo >= useCost) {
			haveResource = true;
		}
		return haveResource;
	}
	
	/*public void subtractResource(Entity user) {
		user.ammo -= useCost;
	}*/
	
	public Color getParticleColor() {
		Color color = new Color(40,50,0);
		return color;
	}
	
	public int getParticleSize() {
		int size = 6;
		return size;
	}
	
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}

}
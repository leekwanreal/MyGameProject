package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Rocket extends Projectile {
	GamePanel gp;
	
	public OBJ_Rocket(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Rock";
		speed = 30;
		maxLife = 80;
		life = maxLife;
		attack = 5;
		useCost = 1;
		alive = false;
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/projectile/turret_rocket_up.png", gp.tileSize, gp.tileSize);
		up2 = setup("/projectile/turret_rocket_up.png", gp.tileSize, gp.tileSize);
		down1 = setup("/projectile/turret_rocket_down.png", gp.tileSize, gp.tileSize);
		down2 = setup("/projectile/turret_rocket_down.png", gp.tileSize, gp.tileSize);
		left1 = setup("/projectile/turret_rocket_left.png", gp.tileSize, gp.tileSize);
		left2 = setup("/projectile/turret_rocket_left.png", gp.tileSize, gp.tileSize);
		right1 = setup("/projectile/turret_rocket_right.png", gp.tileSize, gp.tileSize);
		right2 = setup("/projectile/turret_rocket_right.png", gp.tileSize, gp.tileSize);	
	}
	
	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		if (user.ammo >= useCost) {
			haveResource = true;
		}
		return haveResource;
	}
	
	public void subtractResource(Entity user) {
		user.ammo -= useCost;
	}
	
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

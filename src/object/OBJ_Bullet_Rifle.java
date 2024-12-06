package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Bullet_Rifle extends Projectile {
	GamePanel gp;
	
	public OBJ_Bullet_Rifle(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Rifle Bullet";
		speed = 20;
		maxLife = 40;
		life = maxLife;
		attack = 4;
		useCost = 0;
		alive = false;
		getImage();

		solidArea.x = 20;
		solidArea.y = 20;
		solidArea.width = 8;
		solidArea.height = 8;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
	public void getImage() {
		up1 = setup("/projectile/rifle_bullet_up.png", gp.tileSize, gp.tileSize);
		up2 = setup("/projectile/rifle_bullet_up.png", gp.tileSize, gp.tileSize);
		down1 = setup("/projectile/rifle_bullet_down.png", gp.tileSize, gp.tileSize);
		down2 = setup("/projectile/rifle_bullet_down.png", gp.tileSize, gp.tileSize);
		left1 = setup("/projectile/rifle_bullet_left.png", gp.tileSize, gp.tileSize);
		left2 = setup("/projectile/rifle_bullet_left.png", gp.tileSize, gp.tileSize);
		right1 = setup("/projectile/rifle_bullet_right.png", gp.tileSize, gp.tileSize);
		right2 = setup("/projectile/rifle_bullet_right.png", gp.tileSize, gp.tileSize);	
	}
	
	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		if (user.mana >= useCost) {
			haveResource = true;
		}
		return haveResource;
	}
	
	public void subtractResource(Entity user) {
		user.mana -= useCost;
	}
	
	public Color getParticleColor() {
		Color color = new Color(240,50,0);
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

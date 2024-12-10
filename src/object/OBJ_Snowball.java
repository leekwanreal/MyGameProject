package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Snowball extends Projectile {
	GamePanel gp;
	
	public OBJ_Snowball(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Snow Ball";
		speed = 15;
		maxLife = 40;
		life = maxLife;
		attack = 0;
		useCost = 1;
		alive = false;
		snow = true;
		getImage();

		solidArea.x = 20;
		solidArea.y = 20;
		solidArea.width = 8;
		solidArea.height = 8;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
	public void getImage() {
		up1 = setup("/projectile/snowball_ud.png", gp.tileSize*3/4, gp.tileSize*3/4);
		up2 = setup("/projectile/snowball_ud.png", gp.tileSize*3/4, gp.tileSize*3/4);
		down1 = setup("/projectile/snowball_ud.png", gp.tileSize*3/4, gp.tileSize*3/4);
		down2 = setup("/projectile/snowball_ud.png", gp.tileSize*3/4, gp.tileSize*3/4);
		left1 = setup("/projectile/snowball_rl.png", gp.tileSize*3/4, gp.tileSize*3/4);
		left2 = setup("/projectile/snowball_rl.png", gp.tileSize*3/4, gp.tileSize*3/4);
		right1 = setup("/projectile/snowball_rl.png", gp.tileSize*3/4, gp.tileSize*3/4);
		right2 = setup("/projectile/snowball_rl.png", gp.tileSize*3/4, gp.tileSize*3/4);	
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
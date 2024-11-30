package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;
import object.OBJ_Arrow;
import object.OBJ_Fireball;

public class MON_Witch extends Entity{
	public MON_Witch(GamePanel gp) {
		super(gp);
		
		type = type_monster;
		name = "Skeleton";
		speed = 1;
		maxLife = 2;  
		life = maxLife;
		attack = 2;
		defense = 2;
		exp = 2;
		projectile = new OBJ_Fireball(gp);
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 96;
		solidArea.height = 96;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage(gp);
	}
	
	public void getImage(GamePanel gp) {
		up1 = setup("/monster/witch_up_1.png", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/witch_up_2.png", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/witch_down_1.png", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/witch_down_2.png", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/witch_down_1.png", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/witch_down_2.png", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/witch_right_1.png", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/witch_right_2.png", gp.tileSize, gp.tileSize);
	}
	
	public void setAction() {
		actionLockCounter++;
		
		if (actionLockCounter == 120) {
			Random random = new Random();
			// Get a number 1 - 100
			int i = random.nextInt(100) + 1; 
			if (i <= 25) {
				direction = "up";
			}
			if (i > 25 && i <= 50) {
				direction = "down";
			}
			if (i > 50 && i <= 75) {
				direction = "left";
			}
			if (i > 75 && i <= 100) {
				direction = "right";
			}
			actionLockCounter = 0;
		}
		
		int i = new Random().nextInt(100) + 1;
		if (i > 99 && projectile.alive == false && shotCoolDown == 30) {
			projectile.set(worldX, worldY, direction, true, this);
			gp.projectileList.add(projectile);
			shotCoolDown = 0;
		
		}
	}
	
	public void damageReaction() {
		actionLockCounter = 0;
		direction = gp.player.direction;
	}
	
	public void checkDrop() {
		int i = new Random().nextInt(100) + 1;
		
		// Set the monster drop
		if (i < 50) {
			dropItem(new OBJ_Coin_Bronze(gp));
		}
		if (i >= 50 && i < 75) {
			dropItem(new OBJ_Heart(gp));
		}
		if (i >= 75 && i < 100) {
			dropItem(new OBJ_ManaCrystal(gp));
		}
	}
}

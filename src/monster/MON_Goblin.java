package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Potion_Red;
import object.OBJ_Rock;

public class MON_Goblin extends Entity {

	public boolean Rage = false;
	public int rate = 20;

	public MON_Goblin(GamePanel gp) {
		super(gp);
		
		type = type_monster;
		boss = true;
		name = "Goblin";
		speed = 2;
		maxLife = 50;  
		life = maxLife;
		maxDefense = 3;
		maxSpeed = 2;
		attack = 5;
		defense = 3;
		exp = 50;
		projectile = new OBJ_Rock(gp);
		onPath = true;
		
		solidArea.x = 60;
		solidArea.y = 40;
		solidArea.width = 120;
		solidArea.height = 160;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = 100;
		attackArea.height = 170;
		
		getImage(gp);
		getAttackImage(gp);
	}
	
	public void getImage(GamePanel gp) {
		int i = 5;
		up1 = setup("/monster/goblin_up1.png", gp.tileSize*i, gp.tileSize*i);
		up2 = setup("/monster/goblin_up2.png", gp.tileSize*i, gp.tileSize*i);
		down1 = setup("/monster/goblin_down1.png", gp.tileSize*i, gp.tileSize*i);
		down2 = setup("/monster/goblin_down2.png", gp.tileSize*i, gp.tileSize*i);
		left1 = setup("/monster/goblin_left1.png", gp.tileSize*i, gp.tileSize*i);
		left2 = setup("/monster/goblin_left2.png", gp.tileSize*i, gp.tileSize*i);
		right1 = setup("/monster/goblin_right1.png", gp.tileSize*i, gp.tileSize*i);
		right2 = setup("/monster/goblin_right2.png", gp.tileSize*i, gp.tileSize*i);
	}

	public void getAttackImage(GamePanel gp) {
		int i = 5;
		attackUp1 = setup("/monster/goblin_attack_up_1.png", gp.tileSize*i, gp.tileSize*i);
		attackUp2 = setup("/monster/goblin_attack_up_2.png", gp.tileSize*i, gp.tileSize*i);
		attackDown1 = setup("/monster/goblin_attack_down_1.png", gp.tileSize*i, gp.tileSize*i);
		attackDown2 = setup("/monster/goblin_attack_down_2.png", gp.tileSize*i, gp.tileSize*i);
		attackLeft1 = setup("/monster/goblin_attack_left_2.png", gp.tileSize*i, gp.tileSize*i);
		attackLeft2 = setup("/monster/goblin_attack_left_1.png", gp.tileSize*i, gp.tileSize*i);
		attackRight1 = setup("/monster/goblin_attack_right_2.png", gp.tileSize*i, gp.tileSize*i);
		attackRight2 = setup("/monster/goblin_attack_right_1.png", gp.tileSize*i, gp.tileSize*i);
	}
	
	public void setAction() {

		if (getTileDistance(gp.player) < 10) {
			moveTowardPlayer(60);
		}
		else {
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
		}
		if (attacking == false) {
			checkAttackOrNot(rate, gp.tileSize*3, gp.tileSize*3);
		}
		if (life <= maxLife/2 && Rage == false) {
			Rage = true;
			rageMode();
		}
	}

	public void rageMode() {
		maxSpeed *= 2;
		attack *= 2;
		rate /= 2;
	}
	
	public void checkDrop() {
		int i = new Random().nextInt(100) + 1;
		
		if (i < 50) {
			dropItem(new OBJ_Potion_Red(gp));
		}
	}
}

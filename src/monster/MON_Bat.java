package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Potion_Red;
import object.OBJ_Rock;

public class MON_Bat extends Entity {
	public MON_Bat(GamePanel gp) {
		super(gp);
		
		type = type_monster;
		name = "Bat";
		speed = 2;
		maxLife = 6;  
		life = maxLife;
		attack = 2;
		defense = 0;
		exp = 2;
		projectile = new OBJ_Rock(gp);
		onPath = true;
		
		solidArea.x = 6;
		solidArea.y = 6;
		solidArea.width = 36;
		solidArea.height = 36;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage(gp);
	}
	
	public void getImage(GamePanel gp) {
		up1 = setup("/monster/Bat2.png", gp.tileSize, gp.tileSize*2/3);
		up2 = setup("/monster/Bat3.png", gp.tileSize, gp.tileSize*2/3);
		down1 = setup("/monster/Bat2.png", gp.tileSize, gp.tileSize*2/3);
		down2 = setup("/monster/Bat3.png", gp.tileSize, gp.tileSize*2/3);
		left1 = setup("/monster/Bat2.png", gp.tileSize, gp.tileSize*2/3);
		left2 = setup("/monster/Bat3.png", gp.tileSize, gp.tileSize*2/3);
		right1 = setup("/monster/Bat2.png", gp.tileSize, gp.tileSize*2/3);
		right2 = setup("/monster/Bat3.png", gp.tileSize, gp.tileSize*2/3);
	}
	
	public void setAction() {

		if (onPath == true) {
			int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
			int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

			searchPath(goalCol, goalRow);
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
	}
	
	public void checkDrop() {
		int i = new Random().nextInt(100) + 1;
		
		if (i < 50) {
			dropItem(new OBJ_Potion_Red(gp));
		}
	}
}

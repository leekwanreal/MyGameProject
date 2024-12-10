package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

public class MON_BigSlime extends Entity{
	public MON_BigSlime(GamePanel gp) {
		super(gp);
		
		type = type_monster;
		name = "Big Slime";
		speed = 1;
		maxLife = 12;  
		life = maxLife;
		maxDefense = 0;
		maxSpeed = 1;
		attack = 2;
		defense = 0;
		exp = 0;
		projectile = new OBJ_Rock(gp);
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 144;
		solidArea.height = 144;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage(gp);
	}
	
	public void getImage(GamePanel gp) {
		up1 = setup("/monster/big_slime_1.png", gp.tileSize*3, gp.tileSize*3);
		up2 = setup("/monster/big_slime_2.png", gp.tileSize*3, gp.tileSize*3);
		down1 = setup("/monster/big_slime_1.png", gp.tileSize*3, gp.tileSize*3);
		down2 = setup("/monster/big_slime_2.png", gp.tileSize*3, gp.tileSize*3);
		left1 = setup("/monster/big_slime_1.png", gp.tileSize*3, gp.tileSize*3);
		left2 = setup("/monster/big_slime_2.png", gp.tileSize*3, gp.tileSize*3);
		right1 = setup("/monster/big_slime_1.png", gp.tileSize*3, gp.tileSize*3);
		right2 = setup("/monster/big_slime_2.png", gp.tileSize*3, gp.tileSize*3);
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
	
	@Override
	public void checkDrop() {
	    // Tạo các Small Slime
	    int[] xOffset = { -gp.tileSize, gp.tileSize, 0, 0 };  // Vị trí lệch ngang và dọc
	    int[] yOffset = { 0, 0, -gp.tileSize, gp.tileSize };

	    // Xác định bản đồ hiện tại
	    int currentMap = gp.currentMap; 

	    for (int i = 0; i < 4; i++) {
	        MON_GreenSlime smallSlime = new MON_GreenSlime(gp);
	        smallSlime.worldX = this.worldX + xOffset[i];
	        smallSlime.worldY = this.worldY + yOffset[i];

	        // Thêm Small Slime vào mảng monster của bản đồ hiện tại
	        for (int j = 0; j < gp.monster[currentMap].length; j++) {
	            if (gp.monster[currentMap][j] == null) {
	                gp.monster[currentMap][j] = smallSlime;
	                break;
	            }
	        }
	    }

	    // Logic rơi vật phẩm
	    int i = new Random().nextInt(100) + 1;

	    if (i < 50) {
	        dropItem(new OBJ_Coin_Bronze(gp));
	    } else if (i >= 50 && i < 75) {
	        dropItem(new OBJ_Heart(gp));
	    } else if (i >= 75 && i < 100) {
	        dropItem(new OBJ_ManaCrystal(gp));
	    }
	}
}

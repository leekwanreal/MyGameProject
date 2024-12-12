package monster;

import entity.Entity;
import java.util.Random;
import main.GamePanel;
import object.OBJ_Fireball;
import object.OBJ_Potion_Red;

public class MON_Witch extends Entity {
    public MON_Witch(GamePanel gp) {
        super(gp);
        type = type_monster;
        name = "Skeleton";
        speed = 1;
        maxLife = 6;
        life = maxLife;
        maxDefense = 2;
        maxSpeed = 1;
        attack = 2;
        defense = 2;
        exp = 2;
        projectile = new OBJ_Fireball(gp);
        onPath = true;
        solidArea.x = 12;
        solidArea.y = 6;
        solidArea.width = 24;
        solidArea.height = 36;
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
        if (onPath == true) {
            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
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

        if (i < 50)
        {
            dropItem(new OBJ_Potion_Red(gp));
        }
    }
}
package monster;

import entity.Entity;
import java.util.Random;
import main.GamePanel;
import object.OBJ_Arrow;
import object.OBJ_Potion_Red;

public class MON_Skeleton extends Entity {
    public MON_Skeleton(GamePanel gp) {
        super(gp);
        type = type_monster;
        name = "Skeleton";
        speed = 1;
        maxLife = 8;
        life = maxLife;
        maxDefense = 0;
        maxSpeed = 1;
        attack = 2;
        defense = 0;
        exp = 2;
        projectile = new OBJ_Arrow(gp);
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
        up1 = setup("/monster/skeleton_up1.png", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/skeleton_up2.png", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/skeleton_down1.png", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/skeleton_down2.png", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/skeleton_left1.png", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/skeleton_left2.png", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/skeleton_right1.png", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/skeleton_right2.png", gp.tileSize, gp.tileSize);
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

    public void checkDrop() {
        int i = new Random().nextInt(100) + 1;

        if (i < 50) {
            dropItem(new OBJ_Potion_Red(gp));
        }
    }
}
package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class Entity {
    protected GamePanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 40, 40);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String dialogue[] = new String[20];

    // State
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean onPath = false;
    public Entity loot;
    public boolean opened = false;
    public boolean turnOn = true;
    public boolean frozen = false;

    // Counter
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotCoolDown = 0;
    public int dyingCounter = 0;
    public int hpBarCounter;
    public int freezeCounter;

    // Character Attributes
    public String name;
    public int speed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int maxDefense;
    public int maxSpeed;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile;
    public boolean boss;

    // Item Attributes
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public boolean stackable = false;
    public int amount = 1;

    // Type
    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
    public final int type_obstacle = 8;
    public final int type_gun = 9;
    public final int type_nothing = 10;

    // Gun Type
    public int gun_type;
    public final int type_pistol = 0;
    public final int type_uzi = 1;
    public final int type_rifle = 2;
    public final int type_ak47 = 3;
    public final int type_awm = 4;
    public final int type_shotgun = 5;
    public final int type_snowgun = 6;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public int getScreenX() {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        return screenX;
    }

    public int getScreenY() {
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        return screenY;
    }

    public int getLeftX() {
        return worldX + solidArea.x;
    }

    public int getRightX() {
        return worldX + solidArea.x + solidArea.width;
    }

    public int getTopY() {
        return worldY + solidArea.y;
    }

    public int getBottomY() {
        return worldY + solidArea.y + solidArea.height;
    }

    public int getCol() {
        return (worldX + solidArea.x) / gp.tileSize;
    }

    public int getRow() {
        return (worldY + solidArea.y) / gp.tileSize;
    }

    public int getCenterX() {
        int centerX = worldX + up1.getWidth() / 2;
        return centerX;
    }

    public int getCenterY() {
        int centerY = worldY + up1.getHeight() / 2;
        return centerY;
    }

    public int getXdistance(Entity target) {
        int xDistance = Math.abs(getCenterX() - target.getCenterX());
        return xDistance;
    }

    public int getYdistance(Entity target) {
        int yDistance = Math.abs(getCenterY() - target.getCenterY());
        return yDistance;
    }

    public int getTileDistance(Entity target) {
        int xTile = getXdistance(target) / gp.tileSize;
        int yTile = getYdistance(target) / gp.tileSize;
        return xTile < yTile ? xTile : yTile;
    }

    public void setLoot() {
    }

    public void setAction() {
    }

    public void damageReaction() {
    }

    public void speak() {
        if (dialogue[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogue[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public boolean use(Entity entity) {
        return true;
    }

    public void interact() {
    }

    public void checkDrop() {
    }

    public void dropItem(Entity droppedItem) {
        for (int i = 0; i < gp.obj[1].length; ++i) {
            if (gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }

    public Color getParticleColor() {
        Color color = null;
        return color;
    }

    public int getParticleSize() {
        int size = 0;
        return size;
    }

    public int getParticleSpeed() {
        int speed = 0;
        return speed;
    }

    public int getParticleMaxLife() {
        int maxLife = 0;
        return maxLife;
    }

    public void generateParticle(Entity generator, Entity target) {
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();
        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }

    public void checkCollision() {
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == type_monster && contactPlayer == true) {
            damagePlayer(attack);
        }
    }

    public void update() {
        if (attacking == true) {
            attacking();
        }
        else {
            setAction();
            checkCollision();

            if (frozen) {
                freezeCounter++;

                if (freezeCounter > 180) {
                    // 3 seconds freeze
                    frozen = false;
                    freezeCounter = 0;
                }
            }

            if (!frozen && this != gp.npc[2][0]) {
                speed = maxSpeed;
            }

            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            spriteCounter++;

            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

            if (invincible) {
                invincibleCounter++;

                if (invincibleCounter > 20) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }

            if (shotCoolDown < 30) {
                shotCoolDown++;
            }
        }
    }

    public void checkAttackOrNot(int rate, int straight, int horizontal) {
        boolean targetInRange = false;
        int xDis = getXdistance(gp.player);
        int yDis = getYdistance(gp.player);

        switch (direction) {
            case "up":
                if (gp.player.getCenterY() < getCenterY() && yDis < straight && xDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "down":
                if (gp.player.getCenterY() > getCenterY() && yDis < straight && xDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "left":
                if (gp.player.getCenterX() < getCenterX() && yDis < straight && xDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "right":
                if (gp.player.getCenterX() > getCenterX() && yDis < straight && xDis < horizontal) {
                    targetInRange = true;
                }
                break;
        }

        if (targetInRange == true) {
            int i = new Random().nextInt(rate);

            if (i == 0) {
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
            }
        }
    }

    public void attacking() {
        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 1;
        }

        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            // Save the current worldX, worldY, solidArrea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX/Y for the attackArea
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if (type == type_monster) {
                if (gp.cChecker.checkPlayer(this) == true) {
                    damagePlayer(attack);
                }
            }
            else {
                // Check monster collision
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                gp.player.damageMonster(monsterIndex, attack);
            }

            // Restore the initial position
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }

        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void damagePlayer(int attack) {
        if (!gp.player.invincible) {
            gp.playSE(6);
            int damage = attack - gp.player.defense;

            if (damage < 0) {
                damage = 0;
            }
            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }

    public boolean inCamera() {
        boolean inCamera = false;

        if (worldX + gp.tileSize * 5 > gp.player.worldX - gp.player.screenX &&
        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
        worldY + gp.tileSize * 5 > gp.player.worldY - gp.player.screenY &&
        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            inCamera = true;
        }
        return inCamera;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = getScreenX();
        int screenY = getScreenY();

        if (inCamera() == true) {
            switch (direction) {
                case "up":
                    if (attacking == true) {
                        if (spriteNum == 1) {
                            image = attackUp1;
                        }

                        if (spriteNum == 2) {
                            image = attackUp2;
                        }
                    }
                    else {
                        if (spriteNum == 1) {
                            image = up1;
                        }

                        if (spriteNum == 2) {
                            image = up2;
                        }
                    }
                    break;
                case "down":
                    if (attacking == true) {
                        if (spriteNum == 1) {
                            image = attackDown1;
                        }

                        if (spriteNum == 2) {
                            image = attackDown2;
                        }
                    }
                    else {
                        if (spriteNum == 1) {
                            image = down1;
                        }

                        if (spriteNum == 2) {
                            image = down2;
                        }
                    }
                    break;
                case "left":
                    if (attacking == true) {
                        if (spriteNum == 1) {
                            image = attackLeft1;
                        }

                        if (spriteNum == 2) {
                            image = attackLeft2;
                        }
                    }
                    else {
                        if (spriteNum == 1) {
                            image = left1;
                        }

                        if (spriteNum == 2) {
                            image = left2;
                        }
                    }
                    break;
                case "right":
                    if (attacking == true) {
                        if (spriteNum == 1) {
                            image = attackRight1;
                        }

                        if (spriteNum == 2) {
                            image = attackRight2;
                        }
                    }
                    else {
                        if (spriteNum == 1) {
                            image = right1;
                        }

                        if (spriteNum == 2) {
                            image = right2;
                        }
                    }
                    break;
            }
            gp.ui.drawMonsterLife();

            if (invincible) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }

            if (dying) {
                dyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY, null);
            changeAlpha(g2, 1f);
        }
    }

    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        int i = 5;

        if (dyingCounter <= i) {
            changeAlpha(g2, 0f);
        }

        if (dyingCounter > i && dyingCounter <= i * 2) {
            changeAlpha(g2, 1f);
        }

        if (dyingCounter > i * 2 && dyingCounter <= i * 3) {
            changeAlpha(g2, 0f);
        }

        if (dyingCounter > i * 3 && dyingCounter <= i * 4) {
            changeAlpha(g2, 1f);
        }

        if (dyingCounter > i * 4 && dyingCounter <= i * 5) {
            changeAlpha(g2, 0f);
        }

        if (dyingCounter > i * 5 && dyingCounter <= i * 6) {
            changeAlpha(g2, 1f);
        }

        if (dyingCounter > i * 6 && dyingCounter <= i * 7) {
            changeAlpha(g2, 0f);
        }

        if (dyingCounter > i * 7 && dyingCounter <= i * 8) {
            changeAlpha(g2, 1f);
        }

        if (dyingCounter > i * 8) {
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            image = uTool.scaleImage(image, width, height);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void moveTowardPlayer(int interval) {
        actionLockCounter++;

        if (actionLockCounter > interval) {
            if (getXdistance(gp.player) > getYdistance(gp.player)) {
                if (gp.player.getCenterX() < getCenterX()) {
                    direction = "left";
                }
                else {
                    direction = "right";
                }
            }
            else if (getXdistance(gp.player) < getYdistance(gp.player)) {
                if (gp.player.getCenterY() < getCenterY()) {
                    direction = "up";
                }
                else {
                    direction = "down";
                }
            }
            actionLockCounter = 0;
        }
    }

    public void searchPath(int goalCol, int goalRow) {
        int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;
        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if (gp.pFinder.search() == true) {
            // Next worldX & worldY
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            // Entity's solidArea position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            }
            else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            }
            else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                if (enLeftX > nextX) {
                    direction = "left";
                }

                if (enLeftX < nextX) {
                    direction = "right";
                }
            }
            else if (enTopY > nextY && enLeftX > nextX) {
                direction = "up";
                checkCollision();

                if (collisionOn == true) {
                    direction = "left";
                }
            }
            else if (enTopY > nextY && enLeftX < nextX) {
                direction = "up";
                checkCollision();

                if (collisionOn == true) {
                    direction = "right";
                }
            }
            else if (enTopY < nextY && enLeftX > nextX) {
                direction = "down";
                checkCollision();

                if (collisionOn == true) {
                    direction = "left";
                }
            }
            else if (enTopY < nextY && enLeftX < nextX) {
                direction = "down";
                checkCollision();

                if (collisionOn == true) {
                    direction = "right";
                }
            }
        }
    }

    public int getDetected(Entity user, Entity target[][], String targetName) {
        int index = 999;

        // Check the surrounding object
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch (user.direction) {
            case "up":
                nextWorldY = user.getTopY() - 1;
                break;
            case "down":
                nextWorldY = user.getBottomY() + 1;
                break;
            case "left":
                nextWorldX = user.getLeftX() - 1;
                break;
            case "right":
                nextWorldX = user.getRightX() + 1;
                break;
        }
        int col = nextWorldX / gp.tileSize;
        int row = nextWorldY / gp.tileSize;

        for (int i = 0; i < target[1].length; ++i) {
            if (target[gp.currentMap][i] != null) {
                if (target[gp.currentMap][i].getRow() == row &&
                target[gp.currentMap][i].getCol() == col &&
                target[gp.currentMap][i].name.equals(targetName)) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
}
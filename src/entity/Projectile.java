package entity;

import main.GamePanel;

public abstract class Projectile extends Entity {
    protected Entity user;
    protected boolean snow = false;

    public Projectile(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }

    public void update() {
        if (user == gp.player) {
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);

            if (monsterIndex != 999) {
                gp.player.damageMonster(monsterIndex, attack);
                generateParticle(user.projectile, gp.monster[gp.currentMap][monsterIndex]);

                if (snow == true) {
                    gp.monster[gp.currentMap][monsterIndex].frozen = true;
                    gp.monster[gp.currentMap][monsterIndex].speed = 0;
                }
                alive = false;
            }
            collisionOn = false;
            gp.cChecker.checkTile(this);

            if (collisionOn) {
                alive = false;
            }
        }

        if (user == gp.turret) {
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);

            if (monsterIndex != 999) {
                gp.player.damageMonster(monsterIndex, attack);
                generateParticle(user.projectile, gp.monster[gp.currentMap][monsterIndex]);
                alive = false;
            }
            collisionOn = false;
            gp.cChecker.checkTile(this);

            if (collisionOn) {
                alive = false;
            }
        }

        if (user != gp.player && user != gp.turret) {
            boolean contactPlayer = gp.cChecker.checkPlayer(this);

            if (gp.player.invincible == false && contactPlayer == true) {
                damagePlayer(attack);
                generateParticle(user.projectile, gp.player);
                alive = false;
            }
            collisionOn = false;
            gp.cChecker.checkTile(this);

            if (collisionOn) {
                alive = false;
            }
        }

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
        life--;

        if (life <= 0) {
            alive = false;
        }
        spriteCounter++;

        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            }
            else if (spriteNum == 1) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public abstract boolean haveResource(Entity user);

    public abstract void subtractResource(Entity user);
}
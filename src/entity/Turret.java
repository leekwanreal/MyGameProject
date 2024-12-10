package entity;

import java.util.Random;

import main.GamePanel;
import object.OBJ_Potion_Red;
import object.OBJ_Rocket;

public class Turret extends Entity{
	public int pauseCount = 0;
	public Turret(GamePanel gp) {
		super(gp);

		type = type_obstacle;
		name = "Turret";
		collision = true;
		
		speed = 0;
		maxLife = 600;  
		life = maxLife;
		attack = 5;
		defense = 222;
		exp = 0;
		projectile = new OBJ_Rocket(gp);
		turnOn = false;
		onPath = false;
		Paused = true;
		
		solidArea.x = 24;
		solidArea.y = 24;
		solidArea.width = 96;
		solidArea.height = 96;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage(gp);
	}
	
	public void getImage(GamePanel gp) {
		up1 = setup("/npc/secret_weapon_up.png", gp.tileSize*3, gp.tileSize*3);
		up2 = setup("/npc/secret_weapon_up.png", gp.tileSize*3, gp.tileSize*3);
		down1 = setup("/npc/secret_weapon_down.png", gp.tileSize*3, gp.tileSize*3);
		down2 = setup("/npc/secret_weapon_down.png", gp.tileSize*3, gp.tileSize*3);
		left1 = setup("/npc/secret_weapon_left.png", gp.tileSize*3, gp.tileSize*3);
		left2 = setup("/npc/secret_weapon_left.png", gp.tileSize*3, gp.tileSize*3);
		right1 = setup("/npc/secret_weapon_right.png", gp.tileSize*3, gp.tileSize*3);
		right2 = setup("/npc/secret_weapon_right.png", gp.tileSize*3, gp.tileSize*3);
	}
	
	public void interact() {
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "You need a key to activate this weapon.";
	}
	
	
	private int i = 0; 
	protected boolean Paused = false;
	
	public void setAction() {
		
	    pauseCount++;
	    if (pauseCount == 3) {
	    	Paused = false;
	    	pauseCount = 0;
	    }

		int offset = gp.tileSize;
	    
		if (turnOn == true && Paused == false) {
	        if (i >= 0 && i < 3 && projectile.alive == false && shotCoolDown == 10) {	
	        	direction = "up";
	        	projectile.set(worldX + offset,worldY + offset,direction,true,this);
	        	gp.projectileList.add(projectile);
	            shotCoolDown = 0;
	            i++;
	            Paused = true;
	        }
	        if (i >= 3 && i < 6 && projectile.alive == false && shotCoolDown == 10) {
	            direction = "right";
	        	projectile.set(worldX + offset,worldY + offset, direction,true,this);
	        	gp.projectileList.add(projectile);
	            shotCoolDown = 0;
	            i++;
	            Paused = true;
	        }	        
	        if (i >= 6 && i < 9 && projectile.alive == false && shotCoolDown == 10) {
	            direction = "down";
	        	projectile.set(worldX + offset, worldY + offset, direction,true,this);
	        	gp.projectileList.add(projectile);
	            shotCoolDown = 0;
	            i++;
	            Paused = true;
	        }
	        if (i >= 9 && i < 12 && projectile.alive == false && shotCoolDown == 10) {
	            direction = "left";
	        	projectile.set(worldX + offset,worldY + offset,direction,true,this);
	        	gp.projectileList.add(projectile);
	            shotCoolDown = 0;
	            i++;
	            Paused = true;
	        }
	        if (i >= 12) {
	            i = 0; 
	        }
	    }			
	}

	public void update() {

		if (attacking == true) {
			attacking();
		}

		else {
			setAction();
			checkCollision();
			
			if (!collisionOn) {
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
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
			
			if (shotCoolDown < 10) {
				shotCoolDown++;
			}
		}
	}
	
	public void damageReaction() {
		actionLockCounter = 0;
		direction = gp.player.direction;
	}
	
	public void checkDrop() {
		int i = new Random().nextInt(100) + 1;
		
		if (i < 50) {
			dropItem(new OBJ_Potion_Red(gp));
		}
	}
}

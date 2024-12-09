package entity;

import java.util.Random;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Red;
import object.OBJ_Rock;
import object.OBJ_Arrow;
import object.OBJ_Fireball;

public class Turret extends Entity{
	public int pauseCount = 0;
	//Hieu
	//GamePanel gp;
	//
	public Turret(GamePanel gp) {
		super(gp);
		//Hieu
		this.gp = gp;
		//
		//type = type_monster;
		type = type_obstacle;
		name = "Turret";
		//Hieu
		collision = true;
		//
		speed = 0;
		maxLife = 600;  
		life = maxLife;
		attack = 5;
		defense = 222;
		exp = 0;
		projectile = new OBJ_Rock(gp);
		turnOn = false;
		onPath = false;
		Paused = true;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 144;
		solidArea.height = 144;
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
	
	//Hieu
	public void interact() {
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "You need a key to activate this weapon.";
	}
	//
	
	private int i = 0; 
	protected boolean Paused = false;
	
	private void turretPause(int seconds) {

	}

	public void setAction() {
		
	    pauseCount++;
	    if (pauseCount == 3) {
	    	Paused = false;
	    	pauseCount = 0;
	    }
	    
		if (turnOn == true && Paused == false) {
	        if (i >= 0 && i < 3 && projectile.alive == false && shotCoolDown == 10) {	
	        	direction = "up";
	        	projectile.set(worldX + 54,worldY + 54,direction,true,this);
	        	gp.projectileList.add(projectile);

	            //gp.projectileList.add(upBullet);
	            //projectile.subtractResource(this);
	            shotCoolDown = 0;
	            i++;
	            Paused = true;
	            //turretPause(1);
	        }
	        if (i >= 3 && i < 6 && projectile.alive == false && shotCoolDown == 10) {
	            direction = "right";
	        	projectile.set(worldX + 54,worldY + 54, direction,true,this);
	        	gp.projectileList.add(projectile);
	            shotCoolDown = 0;
	            i++;
	            Paused = true;
	        }	        
	        if (i >= 6 && i < 9 && projectile.alive == false && shotCoolDown == 10) {
	            direction = "down";
	        	projectile.set(worldX + 54, worldY + 54, direction,true,this);
	        	gp.projectileList.add(projectile);

	            shotCoolDown = 0;
	            i++;
	            Paused = true;

	            //turretPause(1);
	        }
	        if (i >= 9 && i < 12 && projectile.alive == false && shotCoolDown == 10) {
	            direction = "left";
	        	projectile.set(worldX + 54,worldY + 54,direction,true,this);
	        	gp.projectileList.add(projectile);

	            shotCoolDown = 0;
	            i++;
	            Paused = true;

	        }
	        if (i >= 12) {
	            i = 0; 
	        }
			/*actionLockCounter++;
			int j = 0;
			
			if (actionLockCounter == 120) {
				Random random = new Random();
				// Get a number 1 - 100
				int i = random.nextInt(100) + 1; 
				if (i <= 25) {
					direction = "up";
					j = 0;
				}
				if (i > 25 && i <= 50) {
					direction = "down";
					j = 0;
				}
				if (i > 50 && i <= 75) {
					direction = "left";
					j = 0;
				}
				if (i > 75 && i <= 100) {
					direction = "right";
					j = 0;
				}
			}	
			actionLockCounter = 0;
		    if (projectile.alive == false && shotCoolDown == 5) {
			projectile.set(worldX, worldY, direction, true, this);
			gp.projectileList.add(projectile);
			projectile.subtractResource(this);
			shotCoolDown = 0;
		    }  */  
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

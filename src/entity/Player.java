package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Bullet_AK47;
import object.OBJ_Bullet_AWM;
import object.OBJ_Bullet_Pistol;
import object.OBJ_Bullet_Rifle;
import object.OBJ_Snowball;
import object.OBJ_Bullet_Shotgun;
import object.OBJ_Bullet_Uzi;
import object.OBJ_Fist;
import object.OBJ_Key_Cell;
import object.OBJ_Key_Turret;
import object.OBJ_Pistol;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Wood;
import object.OBJ_Snow_Gun;

public class Player extends Entity {
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	int standCounter;
	public boolean attackCanceled = false;
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;
	public BufferedImage avatar = setup("/player/boy_rifle_right_1.png", gp.tileSize*2, gp.tileSize);

	// Bullets
	Projectile pistol_bullet = new OBJ_Bullet_Pistol(gp);
	Projectile uzi_bullet = new OBJ_Bullet_Uzi(gp);
	Projectile rifle_bullet = new OBJ_Bullet_Rifle(gp);
	Projectile ak47_bullet = new OBJ_Bullet_AK47(gp);
	Projectile awm_bullet = new OBJ_Bullet_AWM(gp);
	Projectile shotgun_bullet = new OBJ_Bullet_Shotgun(gp);
	Projectile snowball = new OBJ_Snowball(gp);

	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize / 2);
		screenY = gp.screenHeight/2 - (gp.tileSize / 2);
		
		setDefaultValues();
		
		solidArea = new Rectangle(12, 16, 32, 32);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		attackArea.width = 36;
		attackArea.height = 36;
	}
	public void setDefaultValues() {
		worldX = gp.tileSize * 39;
		worldY = gp.tileSize * 24;
		//worldX = gp.tileSize * 10;
		//worldY = gp.tileSize * 13;
		speed = 4;
		direction = "left";
		
		// Player Status
		level = 1;
		maxLife = 6;
		life = maxLife;
		maxMana = 4;
		mana = maxMana;
		ammo = 3;
		strength = 1;
		dexterity = 1;
		exp = 0;
		nextLevelExp = 5;
		coin = 0;
		currentWeapon = new OBJ_Fist(gp);
		currentShield = new OBJ_Shield_Wood(gp);
		projectile = pistol_bullet;
		//projectile = new OBJ_Rock(gp);
		attack = getAttack();
		defense = getDefense();

		getPlayerImage();
		getPlayerAttackImage();
		setItem();
	}
	
	
	public void setDefaultPosition() {
		if (gp.currentMap == 2) {
			worldX = gp.tileSize * 16;
			worldY = gp.tileSize * 16;
			direction = "down";
		}
		else if (gp.setMonster5 == true) {
			worldX = gp.tileSize * 26;
			worldY = gp.tileSize * 36;
			direction = "right";
		}
		else if (gp.setMonster3 == true) {
			worldX = gp.tileSize * 20;
			worldY = gp.tileSize * 23;
			direction = "down";
		}
		else if (gp.setMonster4 == true) {
			worldX = gp.tileSize * 29;
			worldY = gp.tileSize * 26;
			direction = "down";
		}
		else if (gp.setMonster2 == true) {
			worldX = gp.tileSize * 20;
			worldY = gp.tileSize * 16;
			direction = "left";
		}
		else if (gp.setMonster1 == true) {
			worldX = gp.tileSize * 28;
			worldY = gp.tileSize * 23;
			direction = "left";		
		}
	}
	
	
	public void restoreLifeAndMana() {
		life = maxLife;
		mana = maxMana;
		invincible = false;
	}
	
	public void setItem() {
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		inventory.add(new OBJ_Pistol(gp));
		inventory.add(new OBJ_Snow_Gun(gp));
		OBJ_Potion_Red potion = new OBJ_Potion_Red(gp);
		potion.amount = 3;
		inventory.add(potion);
		inventory.add(new OBJ_Key_Turret(gp));
		inventory.add(new OBJ_Key_Cell(gp));
	}
	
	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		return attack = strength * currentWeapon.attackValue;
	}
	
	public int getDefense() {
		return defense = dexterity * currentShield.defenseValue;
	}

	public int getCurrentWeaponSlot() {
		int currentWeaponSlot = 0;
		for (int i = 0; i < inventory.size(); ++i) {
			if (inventory.get(i) == currentWeapon) {
				currentWeaponSlot = i;
			}
		}
		return currentWeaponSlot;
	}

	public int getCurrentShieldSlot() {
		int currentShieldSlot = 0;
		for (int i = 0; i < inventory.size(); ++i) {
			if (inventory.get(i) == currentShield) {
				currentShieldSlot = i;
			}
		}
		return currentShieldSlot;
	}
	
	public void getPlayerImage() {
		up1 = setup("/player/boy_up_1.png", gp.tileSize, gp.tileSize);
		up2 = setup("/player/boy_up_2.png", gp.tileSize, gp.tileSize);
		down1 = setup("/player/boy_down_1.png", gp.tileSize, gp.tileSize);
		down2 = setup("/player/boy_down_2.png", gp.tileSize, gp.tileSize);
		left1 = setup("/player/boy_left_1.png", gp.tileSize, gp.tileSize);
		left2 = setup("/player/boy_left_2.png", gp.tileSize, gp.tileSize);
		right1 = setup("/player/boy_right_1.png", gp.tileSize, gp.tileSize);
		right2 = setup("/player/boy_right_2.png", gp.tileSize, gp.tileSize);
	}

	public void getPlayerBullet() {
		if (currentWeapon.gun_type == type_rifle) {
			projectile = rifle_bullet;
		}
		if (currentWeapon.gun_type == type_uzi) {
			projectile = uzi_bullet;
		}
		if (currentWeapon.gun_type == type_pistol) {
			projectile = pistol_bullet;
		}
		if (currentWeapon.gun_type == type_ak47) {
			projectile = ak47_bullet;
		}
		if (currentWeapon.gun_type == type_awm) {
			projectile = awm_bullet;
		}
		if (currentWeapon.gun_type == type_shotgun) {
			projectile = shotgun_bullet;
		}
		if (currentWeapon.gun_type == type_snowgun) {
			projectile = snowball;
		}
	}
	
	public void getPlayerAttackImage() {
		if (currentWeapon.type == type_sword) {
			
			attackUp1 = setup("/player/boy_up_1.png", gp.tileSize, gp.tileSize*2);
			attackUp2 = setup("/player/boy_up_2.png", gp.tileSize, gp.tileSize*2);
			attackDown1 = setup("/player/boy_down_1.png", gp.tileSize, gp.tileSize*2);
			attackDown2 = setup("/player/boy_down_2.png", gp.tileSize, gp.tileSize*2);
			attackLeft1 = setup("/player/boy_sword_left.png", gp.tileSize*2, gp.tileSize);
			attackLeft2 = setup("/player/boy_sword_left.png", gp.tileSize*2, gp.tileSize);
			attackRight1 = setup("/player/boy_sword_right.png", gp.tileSize*2, gp.tileSize);
			attackRight2 = setup("/player/boy_sword_right.png", gp.tileSize*2, gp.tileSize);
			
			getPlayerImage();
		}
		if (currentWeapon.type == type_nothing) {
			getPlayerImage();
		}
		if (currentWeapon.type == type_gun && currentWeapon.gun_type == type_rifle) {
			up1 = setup("/player/boy_rifle_up_1.png", gp.tileSize, gp.tileSize*2);
			up2 = setup("/player/boy_rifle_up_2.png", gp.tileSize, gp.tileSize*2);
			down1 = setup("/player/boy_rifle_down_1.png", gp.tileSize, gp.tileSize*2);
			down2 = setup("/player/boy_rifle_down_2.png", gp.tileSize, gp.tileSize*2);
			left1 = setup("/player/boy_rifle_left_1.png", gp.tileSize*2, gp.tileSize);
			left2 = setup("/player/boy_rifle_left_2.png", gp.tileSize*2, gp.tileSize);
			right1 = setup("/player/boy_rifle_right_1.png", gp.tileSize*2, gp.tileSize);
			right2 = setup("/player/boy_rifle_right_2.png", gp.tileSize*2, gp.tileSize);
		}
		if (currentWeapon.type == type_gun && currentWeapon.gun_type == type_pistol) {
			up1 = setup("/player/boy_pistol_up_1.png", gp.tileSize, gp.tileSize*2);
			up2 = setup("/player/boy_pistol_up_2.png", gp.tileSize, gp.tileSize*2);
			down1 = setup("/player/boy_pistol_down_1.png", gp.tileSize, gp.tileSize*2);
			down2 = setup("/player/boy_pistol_down_2.png", gp.tileSize, gp.tileSize*2);
			left1 = setup("/player/boy_pistol_left_1.png", gp.tileSize*2, gp.tileSize);
			left2 = setup("/player/boy_pistol_left_2.png", gp.tileSize*2, gp.tileSize);
			right1 = setup("/player/boy_pistol_right_1.png", gp.tileSize*2, gp.tileSize);
			right2 = setup("/player/boy_pistol_right_2.png", gp.tileSize*2, gp.tileSize);
		}
		if (currentWeapon.type == type_gun && currentWeapon.gun_type == type_uzi) {
			up1 = setup("/player/boy_uzi_up_1.png", gp.tileSize, gp.tileSize*2);
			up2 = setup("/player/boy_uzi_up_2.png", gp.tileSize, gp.tileSize*2);
			down1 = setup("/player/boy_uzi_down_1.png", gp.tileSize, gp.tileSize*2);
			down2 = setup("/player/boy_uzi_down_2.png", gp.tileSize, gp.tileSize*2);
			left1 = setup("/player/boy_uzi_left_1.png", gp.tileSize*2, gp.tileSize);
			left2 = setup("/player/boy_uzi_left_2.png", gp.tileSize*2, gp.tileSize);
			right1 = setup("/player/boy_uzi_right_1.png", gp.tileSize*2, gp.tileSize);
			right2 = setup("/player/boy_uzi_right_2.png", gp.tileSize*2, gp.tileSize);
		}
		if (currentWeapon.type == type_gun && currentWeapon.gun_type == type_ak47) {
			up1 = setup("/player/boy_ak47_up_1.png", gp.tileSize, gp.tileSize*2);
			up2 = setup("/player/boy_ak47_up_2.png", gp.tileSize, gp.tileSize*2);
			down1 = setup("/player/boy_ak47_down_1.png", gp.tileSize, gp.tileSize*2);
			down2 = setup("/player/boy_ak47_down_2.png", gp.tileSize, gp.tileSize*2);
			left1 = setup("/player/boy_ak47_left_1.png", gp.tileSize*2, gp.tileSize);
			left2 = setup("/player/boy_ak47_left_2.png", gp.tileSize*2, gp.tileSize);
			right1 = setup("/player/boy_ak47_right_1.png", gp.tileSize*2, gp.tileSize);
			right2 = setup("/player/boy_ak47_right_2.png", gp.tileSize*2, gp.tileSize);
		}
		if (currentWeapon.type == type_gun && currentWeapon.gun_type == type_awm) {
			up1 = setup("/player/boy_awm_up_1.png", gp.tileSize, gp.tileSize*2);
			up2 = setup("/player/boy_awm_up_2.png", gp.tileSize, gp.tileSize*2);
			down1 = setup("/player/boy_awm_down_1.png", gp.tileSize, gp.tileSize*2);
			down2 = setup("/player/boy_awm_down_2.png", gp.tileSize, gp.tileSize*2);
			left1 = setup("/player/boy_awm_left_1.png", gp.tileSize*2, gp.tileSize);
			left2 = setup("/player/boy_awm_left_2.png", gp.tileSize*2, gp.tileSize);
			right1 = setup("/player/boy_awm_right_1.png", gp.tileSize*2, gp.tileSize);
			right2 = setup("/player/boy_awm_right_2.png", gp.tileSize*2, gp.tileSize);
		}
		if (currentWeapon.type == type_gun && currentWeapon.gun_type == type_shotgun) {
			up1 = setup("/player/boy_shotgun_up_1.png", gp.tileSize, gp.tileSize*2);
			up2 = setup("/player/boy_shotgun_up_2.png", gp.tileSize, gp.tileSize*2);
			down1 = setup("/player/boy_shotgun_down_1.png", gp.tileSize, gp.tileSize*2);
			down2 = setup("/player/boy_shotgun_down_2.png", gp.tileSize, gp.tileSize*2);
			left1 = setup("/player/boy_shotgun_left_1.png", gp.tileSize*2, gp.tileSize);
			left2 = setup("/player/boy_shotgun_left_2.png", gp.tileSize*2, gp.tileSize);
			right1 = setup("/player/boy_shotgun_right_1.png", gp.tileSize*2, gp.tileSize);
			right2 = setup("/player/boy_shotgun_right_2.png", gp.tileSize*2, gp.tileSize);
		}
		if (currentWeapon.type == type_gun && currentWeapon.gun_type == type_snowgun) {
			up1 = setup("/player/boy_snowgun_up_1.png", gp.tileSize, gp.tileSize*2);
			up2 = setup("/player/boy_snowgun_up_2.png", gp.tileSize, gp.tileSize*2);
			down1 = setup("/player/boy_snowgun_down_1.png", gp.tileSize, gp.tileSize*2);
			down2 = setup("/player/boy_snowgun_down_2.png", gp.tileSize, gp.tileSize*2);
			left1 = setup("/player/boy_snowgun_left_1.png", gp.tileSize*2, gp.tileSize);
			left2 = setup("/player/boy_snowgun_left_2.png", gp.tileSize*2, gp.tileSize);
			right1 = setup("/player/boy_snowgun_right_1.png", gp.tileSize*2, gp.tileSize);
			right2 = setup("/player/boy_snowgun_right_2.png", gp.tileSize*2, gp.tileSize);
		}
	}
	
	public void update() {
		
		if (attacking) {
			attacking();
		}
		
		else if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {
			if (keyH.upPressed) {
				direction = "up";
			}
			else if (keyH.downPressed) {
				direction = "down";
			}
			else if (keyH.rightPressed) {
				direction = "right";
			}
			else if (keyH.leftPressed) {
				direction = "left";
			}
			// Check collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// Check Object Collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			// Check NPC Collision
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			// Check Monster Collision
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
			// Check Interactive Tile Collision
			// int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
			
			
			// Check Event
			gp.eHandler.checkEvent();
			
			if (!collisionOn && !keyH.enterPressed) {
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
			/*
			if (keyH.enterPressed && attackCanceled == false) {
				if (currentWeapon.type != type_gun) {
					gp.playSE(7);
				}
				attacking = true;
				spriteCounter = 0;
			}
			
			attackCanceled = false;
			*/
			gp.keyH.enterPressed = false;

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
		}
		else {
			standCounter++;
			if (standCounter == 20) {
				spriteNum = 1;
				standCounter = 0;
			}
		}
		
		if (gp.keyH.shotKeyPressed && !projectile.alive 
				&& shotCoolDown == 30 && projectile.haveResource(this) && currentWeapon.type == type_gun) { // 30 
			// Set Default Values
			projectile.set(worldX, worldY, direction, true, this);
			
			// Subtract the cost
			projectile.subtractResource(this);
			
			// Add to the list
			gp.projectileList.add(projectile);
			
			shotCoolDown = 0;
			//gp.playSE(10);
		}
		
		if (shotCoolDown < 30) { // 30
			shotCoolDown++;
		}
		
		if (invincible) {
			invincibleCounter++;
			if (invincibleCounter > 20) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		
		if (life > maxLife) {
			life = maxLife;
		}
		
		if (mana > maxMana) {
			mana = maxMana;
		}
		
		if (life <= 0) {
			gp.gameState = gp.gameOverState;
			gp.ui.commandNum = 0; // -1
			//gp.stopMusic();
			gp.playSE(12);
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
			switch(direction) {
			case "up": worldY -= attackArea.height; break;
			case "down": worldY += attackArea.height; break;
			case "left": worldX -= attackArea.width; break;
			case "right": worldX += attackArea.width; break;
			}
			
			// attackArea becomes solidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			// Check monster collision
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex, attack);
			
			int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
			damageInteractiveTile(iTileIndex);
			
			
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
	
	public void pickUpObject(int i) {
		if (i != 999) {
			// Pick Up Only Items
			if (gp.obj[gp.currentMap][i].type == type_pickupOnly) {
				gp.obj[gp.currentMap][i].use(this);
				gp.obj[gp.currentMap][i] = null;
				
			}

			// Obstacle
			else if (gp.obj[gp.currentMap][i].type == type_obstacle) {
				if (keyH.enterPressed == true) {
					attackCanceled = true;
					gp.obj[gp.currentMap][i].interact();
				}				
			}

			else {
				// Inventory Items
				//String text;
				if (canObtainItem(gp.obj[gp.currentMap][i])) {
					gp.playSE(1);
					//text = "Got a " + gp.obj[gp.currentMap][i].name + "!";
				}
				else {
					//text = "Your inventory is full!";
				}
				//gp.ui.addMessage(text);
				gp.obj[gp.currentMap][i] = null;
			}
		}
	}
	
	public void interactNPC(int i) {
		if (gp.keyH.enterPressed) {
			if (i != 999) {
				attackCanceled = true;
				gp.gameState = gp.dialogueState;
				gp.npc[gp.currentMap][i].speak();
			}
		}
	}
	
	public void contactMonster(int i) {
		if (i != 999) {
			if (!invincible && !gp.monster[gp.currentMap][i].dying) {
				gp.playSE(6);
				life -= 1;
				invincible = true;
			}
		}
	}
	
	public void damageMonster(int i, int attack) {
		if (i != 999) {
			if (gp.monster[gp.currentMap][i].invincible == false) {
				gp.playSE(5);
				
				int damage = attack - gp.monster[gp.currentMap][i].defense;
				if (damage < 0) {
					damage = 0;
				}
				
				gp.monster[gp.currentMap][i].life -= damage;
				//gp.ui.addMessage(damage + "damage!");
				
				gp.monster[gp.currentMap][i].invincible = true;
				gp.monster[gp.currentMap][i].damageReaction();
						
				if (gp.monster[gp.currentMap][i].life <= 0) {
					gp.monster[gp.currentMap][i].life = 0;
					gp.monster[gp.currentMap][i].dying = true;
					//gp.ui.addMessage("killed the " + gp.monster[gp.currentMap][i].name + "!");
					//gp.ui.addMessage("Exp + " + gp.monster[gp.currentMap][i].exp);
					exp += gp.monster[gp.currentMap][i].exp;
					checkLevelUp();
				}
			}
		}
	}
	
	public void damageInteractiveTile(int i) {
		if (i != 999 && gp.iTile[gp.currentMap][i].destructible && gp.iTile[gp.currentMap][i].isCorrectItem(this) && gp.iTile[gp.currentMap][i].invincible == false) {
			gp.iTile[gp.currentMap][i].playSE();
			gp.iTile[gp.currentMap][i].life--;
			gp.iTile[gp.currentMap][i].invincible = true;
			
			generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);
			
			if (gp.iTile[gp.currentMap][i].life == 0) {
				gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
			}
		}
	}
	
	public void checkLevelUp() {
		if (exp >= nextLevelExp) {
			level++;
			nextLevelExp = nextLevelExp + 15;
			maxLife += 2;
			//strength++;
			//dexterity++;
			//attack = getAttack();
		
			//defense = getDefense();
			
			gp.playSE(8);
			//gp.gameState = gp.dialogueState;
			//gp.ui.currentDialogue = "You are level " + level + " now!\n";
		}
	}
	
	public void selectItem() {
		int itemIndex  = gp.ui.getItemIndexOnSlot();
		if (itemIndex < inventory.size()) {
			Entity selectedItem = inventory.get(itemIndex);
			if (selectedItem.type == type_sword || selectedItem.type == type_axe) {
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			}
			if (selectedItem.type == type_nothing) {
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();

			}
			if (selectedItem.type == type_gun) {
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerBullet();
				getPlayerAttackImage();

			}
			if (selectedItem.type == type_shield) {
				currentShield = selectedItem;
				defense = getDefense();
			}
			if (selectedItem.type == type_consumable) {
				if (selectedItem.use(this) == true) {
					if (selectedItem.amount > 1) {
						selectedItem.amount--;
					}
					else {
						inventory.remove(itemIndex);
					}
				}
		
			}
		}
	}
	
	public int searchItemInInventory(String itemName) {
		int itemIndex = 999;
		
		for (int i = 0; i < inventory.size(); ++i) {
			if (inventory.get(i).name.equals(itemName)) {
				itemIndex = i;
				break;
			}
		}

		return itemIndex;
	}

	public boolean canObtainItem(Entity item) {
		boolean canObtain = false;

		// Check if stackable
		if (item.stackable == true) {
			int index = searchItemInInventory(item.name);
			if (index != 999) {
				inventory.get(index).amount++;
				canObtain = true;
			}
			else {
				if (inventory.size() != maxInventorySize) {
					inventory.add(item);
					canObtain = true;
				}
			}
		}

		else {
			if (inventory.size() != maxInventorySize) {
				inventory.add(item);
				canObtain = true;
			}		
		}
		return canObtain;
	}

 	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		switch(direction) {
		case "up":
			if (attacking && currentWeapon.type != type_gun) {
				tempScreenY = screenY - gp.tileSize;
				if (spriteNum == 1) {image = attackUp1;}
				if (spriteNum == 2) {image = attackUp2;}
			}
			else {
				if (currentWeapon.type == type_gun) {
					tempScreenY = screenY - gp.tileSize;
				}
				if (spriteNum == 1) {image = up1;}
				if (spriteNum == 2) {image = up2;}
			}
			break;
		case "down":
			if (attacking && currentWeapon.type != type_gun) {
				if (spriteNum == 1) {image = attackDown1;}
				if (spriteNum == 2) {image = attackDown2;}	
			}
			else {
				if (spriteNum == 1) {image = down1;}
				if (spriteNum == 2) {image = down2;}	
			}
			break;
		case "left":
			if (attacking && currentWeapon.type != type_gun) {
				tempScreenX = screenX - gp.tileSize;
				if (spriteNum == 1) {image = attackLeft1;}
				if (spriteNum == 2) {image = attackLeft2;}
			}
			else {
				if (currentWeapon.type == type_gun) {
					tempScreenX = screenX - gp.tileSize;
				}
				if (spriteNum == 1) {image = left1;}
				if (spriteNum == 2) {image = left2;}
			}
			break;
		case "right":
			if (attacking && currentWeapon.type != type_gun) {
				if (spriteNum == 1) {image = attackRight1;}
				if (spriteNum == 2) {image = attackRight2;}
			}
			else {
				if (spriteNum == 1) {image = right1;}
				if (spriteNum == 2) {image = right2;}
			}
			break;
		}
		
		int x = tempScreenX;
		int y = tempScreenY;
		
		/* 
		if (tempScreenX > worldX) {
			x = worldX;
			if (attacking && direction.equals("left")) {
				x -= gp.tileSize;
			}
		}
		if (tempScreenY > worldY) {
			y = worldY;
			if (attacking && direction.equals("up")) {
				y -= gp.tileSize;
			}
		}
		int rightOffset = gp.screenWidth - tempScreenX;
		if (rightOffset > gp.worldWidth - worldX) {
			x = gp.screenWidth - (gp.worldWidth - worldX);
			if (attacking && direction.equals("left")) {
				x -= gp.tileSize;
			}
		}
		int bottomOffset = gp.screenHeight - tempScreenY;
		if (bottomOffset > gp.worldHeight - worldY) {
			y = gp.screenHeight - (gp.worldHeight - worldY);
			if (attacking && direction.equals("up")) {
				y -= gp.tileSize;
			}
		}
		*/

		if (invincible) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		
		g2.drawImage(image, x, y, null);
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

		// Debug
		//g2.setFont(new Font("Arial", Font.PLAIN, 26));
		//g2.setColor(Color.white);
		//g2.drawString("Invincible: " + invincibleCounter, 10, 400);
	}
	
}

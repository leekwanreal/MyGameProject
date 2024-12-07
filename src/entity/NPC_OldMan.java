package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity{
	public NPC_OldMan(GamePanel gp) {
		super(gp);
		direction = "down";
		speed = 1;
		getNPCImage();
		setDialogue();
		onPath = true;
	}
	
	public void getNPCImage() {
		up1 = setup("/npc/oldman_up_1.png", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/oldman_up_2.png", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/oldman_down_1.png", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/oldman_down_2.png", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/oldman_left_1.png", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/oldman_left_2.png", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/oldman_right_1.png", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/oldman_right_2.png", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		dialogue[0] = "Hello, buddy.";
		dialogue[1] = "You must find the treasure on this \nisland.";
		dialogue[2] = "I'm too old for an adventure.";
		dialogue[3] = "Good luck!";
		
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
	public void speak() {
		super.speak();
	}
}

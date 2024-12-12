package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Girl extends Entity{
	public NPC_Girl(GamePanel gp) {
		super(gp);
		direction = "down";
		speed = 1;
		maxSpeed = 1;
		getNPCImage();
		setDialogue();
		onPath = true;
	}
	
	public void getNPCImage() {
		up1 = setup("/npc/girl_up_1.png", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/girl_up_2.png", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/girl_down_1.png", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/girl_down_2.png", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/girl_left_1.png", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/girl_left_2.png", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/girl_right_1.png", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/girl_right_2.png", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		clearDialogue();
		if (gp.currentMap == 0) {
			dialogue[0] = "Eva: Open the chests in the cell.";
			dialogue[1] = "Marc: What's in the chests?";
			dialogue[2] = "Eva: The keys in there can lead us to the \nfootball stadium.";
			dialogue[3] = "Eva: We have to get pass a lot of witches.";
			dialogue[4] = "Marc: And then where to?";
			dialogue[5] = "Eva: After that we must get to the Laboratory.";
		}
		if (gp.currentMap == 1) {
			dialogue[0] = "Eva: This is the Laboratory.";
			dialogue[1] = "Marc: What should we do here?";
			dialogue[2] = "Eva: There are many weapons, but the most \nimportant one is the turret.";
			dialogue[3] = "Marc: How do we activate it?";
			dialogue[4] = "Eva: Get the turret key in one of these chests. \nStand at the top left corner of the turret to \nactivate.";
		}
		if (gp.currentMap == 2) {
			dialogue[0] = "Eva: We need to escape the school.";
			dialogue[1] = "Marc: Do you have the keys?";
			dialogue[2] = "Eva: No, but the Goblin does.";
			dialogue[3] = "Marc: You should stay here and let me \n use the turret to defeat the Goblin.";
			dialogue[4] = "Eva: Okay. Be careful!";
		}
	}

	public void clearDialogue() {
		for (int i = 0; i < dialogue.length; i++) {
			dialogue[i] = null;
		}
	}
	
	public void setAction() {
		setDialogue();
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

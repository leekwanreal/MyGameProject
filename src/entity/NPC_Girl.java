package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Girl extends Entity{
	public NPC_Girl(GamePanel gp) {
		super(gp);
		direction = "down";
		speed = 1;
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
		dialogue[0] = "Open the chests in the cell.";
		dialogue[1] = "The keys in there can lead us to the football \nstadium.";
		dialogue[2] = "Then we have to get pass a lot of witches.";
		dialogue[3] = "After that we must get to the school gate to \nescape.";
		
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

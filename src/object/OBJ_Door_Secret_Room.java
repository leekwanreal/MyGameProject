package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door_Secret_Room extends Entity {

	GamePanel gp; 

	public OBJ_Door_Secret_Room(GamePanel gp) {
		super(gp);
		this.gp = gp;

		name = "Secret Room Door";
		down1 = setup("/objects/door.png", gp.tileSize, gp.tileSize);
		collision = true;
		type = type_obstacle;
		
		solidArea.x = 0; 
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

	public void interact() {
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "You need a key to open this door.";
	}
}

package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Gate_Right extends Entity {

	GamePanel gp; 

	public OBJ_Gate_Right(GamePanel gp) {
		super(gp);
		this.gp = gp;

		name = "Right Gate";
		down1 = setup("/objects/gate_top_right.png", gp.tileSize*2, gp.tileSize*2);
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
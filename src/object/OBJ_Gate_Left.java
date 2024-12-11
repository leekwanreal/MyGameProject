package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Gate_Left extends Entity {

	GamePanel gp; 

	public OBJ_Gate_Left(GamePanel gp) {
		super(gp);
		this.gp = gp;

		name = "Left Gate";
		down1 = setup("/objects/gate_top_left.png", gp.tileSize*2, gp.tileSize*2);
		collision = true;
		type = type_obstacle;
		
		solidArea.x = 0; 
		solidArea.y = 32;
		solidArea.width = 96;
		solidArea.height = 64;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

	public void interact() {
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "You need a key to open this gate.";
	}
}

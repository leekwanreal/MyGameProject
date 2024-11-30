package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Cell_Gate extends Entity {

	GamePanel gp; 

	public OBJ_Cell_Gate(GamePanel gp) {
		super(gp);
		this.gp = gp;

		name = "Cell Gate";
		down1 = setup("/objects/cellgate.png", gp.tileSize, gp.tileSize);
		collision = true;
		type = type_obstacle;
		
		solidArea.x = 12; 
		solidArea.y = 0;
		solidArea.width = 24;
		solidArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

	public void interact() {
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "You need a key to open this gate.";
	}
}

package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key_Cell extends Entity {
	public OBJ_Key_Cell(GamePanel gp) {
		super(gp);

		type = type_consumable;
		name = "Cell Key";
		down1 = setup("/objects/key.png", gp.tileSize, gp.tileSize);

		description = "[" + name + "]\nIt opens the cell.";
	}

	public boolean use(Entity entity) {
		gp.gameState = gp.dialogueState;

		int objIndex = getDetected(entity, gp.obj, "Cell Gate");
		if (objIndex != 999) {
			gp.ui.currentDialogue = "You use the " + name + " and open the Cell.";
			gp.obj[gp.currentMap][objIndex] = null;
			return true;
		}
		else {
			gp.ui.currentDialogue = "Get to the Cell to open the Gate";
			return false;
		}
	}	
}

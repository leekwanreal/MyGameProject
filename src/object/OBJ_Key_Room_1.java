package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key_Room_1 extends Entity {
	public OBJ_Key_Room_1(GamePanel gp) {
		super(gp);

		type = type_consumable;
		name = "Room 1 Key";
		down1 = setup("/objects/key.png", gp.tileSize, gp.tileSize);

		description = "[" + name + "]\nIt opens Room 1 door.";
	}

	public boolean use(Entity entity) {
		gp.gameState = gp.dialogueState;

		int objIndex = getDetected(entity, gp.obj, "Room 1 Door");
		if (objIndex != 999) {
			gp.ui.currentDialogue = "You use the " + name + " and open the door.";
			gp.obj[gp.currentMap][objIndex] = null;
			return true;
		}
		else {
			gp.ui.currentDialogue = "Get to Room 1 Door and open it!";
			return false;
		}
	}	
}

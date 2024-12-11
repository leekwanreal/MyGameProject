package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key_Room_3 extends Entity {
	public OBJ_Key_Room_3(GamePanel gp) {
		super(gp);

		type = type_consumable;
		name = "Room 3 Key";
		down1 = setup("/objects/key.png", gp.tileSize, gp.tileSize);

		description = "[" + name + "]\nIt opens Room 3 door.";
	}

	public boolean use(Entity entity) {
		gp.gameState = gp.dialogueState;

		int objIndex = getDetected(entity, gp.obj, "Room 3 Door");
		if (objIndex != 999) {
			gp.ui.currentDialogue = "You used the " + name + " and opened the door.";
			gp.obj[gp.currentMap][objIndex] = null;
			return true;
		}
		else {
			gp.ui.currentDialogue = "Get to Room 3 Door and open it!";
			return false;
		}
	}	
}

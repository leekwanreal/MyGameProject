package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key_Secret_Room extends Entity {
	public OBJ_Key_Secret_Room(GamePanel gp) {
		super(gp);

		type = type_consumable;
		name = "Secret Room Key";
		down1 = setup("/objects/key.png", gp.tileSize, gp.tileSize);

		description = "[" + name + "]\nIt opens the Secret \nRoom.";
	}

	public boolean use(Entity entity) {
		gp.gameState = gp.dialogueState;

		int objIndex = getDetected(entity, gp.obj, "Secret Room Door");
		if (objIndex != 999) {
			gp.ui.currentDialogue = "You used the " + name + " and opened the door.";
			gp.obj[gp.currentMap][objIndex] = null;
			return true;
		}
		else {
			gp.ui.currentDialogue = "Get to the Secret Room to open!";
			return false;
		}
	}	
}

package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {
	public OBJ_Key(GamePanel gp) {
		super(gp);

		type = type_consumable;
		name = "Key";
		down1 = setup("/objects/key.png", gp.tileSize, gp.tileSize);

		description = "[" + name + "]\nIt opens a door.";
	}

	public boolean use(Entity entity) {
		gp.gameState = gp.dialogueState;

		int objIndex = getDetected(entity, gp.obj, "Door");
		if (objIndex != 999) {
			gp.ui.currentDialogue = "You use the " + name + " and open the door.";
			gp.obj[gp.currentMap][objIndex] = null;
			return true;
		}
		else {
			gp.ui.currentDialogue = "What are you doing?";
			return false;
		}
	}	
}

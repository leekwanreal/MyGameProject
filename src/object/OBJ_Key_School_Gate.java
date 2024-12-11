package object;

import data.Progress;
import entity.Entity;
import main.GamePanel;

public class OBJ_Key_School_Gate extends Entity {
	public OBJ_Key_School_Gate(GamePanel gp) {
		super(gp);

		type = type_consumable;
		name = "School Gate Key";
		down1 = setup("/objects/key.png", gp.tileSize, gp.tileSize);

		description = "[" + name + "]\nIt opens the \nSchool Gate.";
	}

	public boolean use(Entity entity) {
		gp.gameState = gp.dialogueState;

		int objIndex = getDetected(entity, gp.obj, "Left Gate");
		if (objIndex != 999) {
			gp.ui.currentDialogue = "You used the " + name + " and opened the gate.";
			gp.obj[gp.currentMap][objIndex] = null;
            Progress.bossDefeated = true;
			return true;
		}
        objIndex = getDetected(entity, gp.obj, "Right Gate");
		if (objIndex != 999) {
			gp.ui.currentDialogue = "You used the " + name + " and opened the gate.";
			gp.obj[gp.currentMap][objIndex] = null;
            Progress.bossDefeated = true;
			return true;
		}
		else {
			gp.ui.currentDialogue = "Get to the School Gate to open!";
			return false;
		}
	}	
}

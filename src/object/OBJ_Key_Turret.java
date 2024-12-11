package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key_Turret extends Entity {

	public OBJ_Key_Turret(GamePanel gp) {
		super(gp);

		type = type_consumable;
		name = "Turret Key";
		down1 = setup("/objects/key.png", gp.tileSize, gp.tileSize);

		description = "[" + name + "]\nIt activates the \nsecret weapon.";
	}

	public boolean use(Entity entity) {
		gp.gameState = gp.dialogueState;

		int objIndex = getDetected(entity, gp.obj, "Turret");
		if (objIndex != 999) {
			gp.ui.currentDialogue = "You used the " + name + " and activated \nthe weapon. Now use it to beat the goblin.";
            gp.obj[gp.currentMap][objIndex].turnOn = true;
			gp.player.inventory.add(new OBJ_Key_Turret(gp));
			return true;
		}
		else {
			gp.ui.currentDialogue = "Get to the secret weapon and activate it!";
			return false;
		}
	}	

    @Override
    public int getDetected(Entity user, Entity target[][], String targetName) {
		int index = 999;

		// Check the surrounding object
		int nextWorldX = user.getLeftX();
		int nextWorldY = user.getTopY();

		switch(user.direction) {
		case "up": nextWorldY = user.getTopY() - 1; break;
		case "down": nextWorldY = user.getBottomY() + 1; break;
		case "left": nextWorldX = user.getLeftX() - 2*gp.tileSize -1; break;
		case "right": nextWorldX = user.getRightX() + 1; break;
		}

		int col = nextWorldX/gp.tileSize;
		int row = nextWorldY/gp.tileSize;

		for (int i = 0; i < target[1].length; ++i) {
			if (target[gp.currentMap][i] != null) {
				if (target[gp.currentMap][i].getRow() == row &&
					target[gp.currentMap][i].getCol() == col &&
					target[gp.currentMap][i].name.equals(targetName)) {
						index = i;
						break;
					}
			}
		}

		return index;
	}
}

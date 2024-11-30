package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Fist extends Entity{
	public OBJ_Fist(GamePanel gp) {
		super(gp);
		
		type = type_nothing;
		name = "Fist";
		down1 = setup("/objects/fist.png",gp.tileSize,gp.tileSize);
		attackValue = 0;
		
		description = "[" + name + "]\nHolding nothing.";
		attackArea.width = 36;
		attackArea.height = 36;
	}
}

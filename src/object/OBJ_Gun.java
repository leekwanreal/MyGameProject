package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Gun extends Entity{
	public OBJ_Gun(GamePanel gp) {
		super(gp);
		
		type = type_gun;
		name = "Gun";
		down1 = setup("/objects/gun.png",gp.tileSize,gp.tileSize);
		attackValue = 4;
		
		description = "[" + name + "]\nA gun.";
		attackArea.width = 36;
		attackArea.height = 36;
	}
}

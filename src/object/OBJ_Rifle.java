package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Rifle extends Entity{
	public OBJ_Rifle(GamePanel gp) {
		super(gp);
		
		type = type_gun;
		gun_type = type_rifle;

		name = "Rifle";
		down1 = setup("/objects/rifle.png",gp.tileSize,gp.tileSize);
		attackValue = 4;
		
		description = "[" + name + "]\nDamage: 4\nSpeed: 20";
		attackArea.width = 36;
		attackArea.height = 36;
	}
}

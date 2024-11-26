package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Uzi extends Entity{
	public OBJ_Uzi(GamePanel gp) {
		super(gp);
		
		type = type_gun;
        gun_type = type_uzi;

		name = "Uzi";
		down1 = setup("/objects/uzi.png",gp.tileSize,gp.tileSize);
		attackValue = 4;
		
		description = "[" + name + "]\nDamage: 3\nSpeed: 25";
		attackArea.width = 36;
		attackArea.height = 36;
	}
}

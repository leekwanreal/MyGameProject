package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pistol extends Entity{
	public OBJ_Pistol(GamePanel gp) {
		super(gp);
		
		type = type_gun;
        gun_type = type_pistol;

		name = "Pistol";
		down1 = setup("/objects/pistol.png",gp.tileSize,gp.tileSize);
		attackValue = 4;
		
		description = "[" + name + "]\nDamage: 2\nSpeed: 15";
		attackArea.width = 36;
		attackArea.height = 36;
	}
}

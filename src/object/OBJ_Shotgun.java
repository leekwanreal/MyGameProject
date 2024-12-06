package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shotgun extends Entity{
	public OBJ_Shotgun(GamePanel gp) {
		super(gp);
		
		type = type_gun;
		gun_type = type_shotgun;

		name = "Shotgun";
		down1 = setup("/objects/shotgun.png",gp.tileSize,gp.tileSize);
		attackValue = 8;
		
		description = "[" + name + "]\nDamage: 8 (Close)\nSpeed: 15";
		attackArea.width = 36;
		attackArea.height = 36;
	}
}

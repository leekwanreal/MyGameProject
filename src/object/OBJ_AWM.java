package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_AWM extends Entity{
	public OBJ_AWM(GamePanel gp) {
		super(gp);
		
		type = type_gun;
		gun_type = type_awm;

		name = "AWM";
		down1 = setup("/objects/awm.png",gp.tileSize,gp.tileSize);
		attackValue = 8;
		
		description = "[" + name + "]\nDamage: 8\nSpeed: 15";
		attackArea.width = 36;
		attackArea.height = 36;
	}
}

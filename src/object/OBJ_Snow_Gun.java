package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Snow_Gun extends Entity{
	public OBJ_Snow_Gun(GamePanel gp) {
		super(gp);
		
		type = type_gun;
        gun_type = type_snowgun;

		name = "Snow Gun";
		down1 = setup("/objects/snowgun.png",gp.tileSize,gp.tileSize);
		attackValue = 0;
		
		description = "[" + name + "]\nDamage: 0\nSpeed: 15";
		attackArea.width = 36;
		attackArea.height = 36;
	}
}

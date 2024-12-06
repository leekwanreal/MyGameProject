package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_AK47 extends Entity{
	public OBJ_AK47(GamePanel gp) {
		super(gp);
		
		type = type_gun;
		gun_type = type_ak47;

		name = "AK47";
		down1 = setup("/objects/ak47.png",gp.tileSize,gp.tileSize);
		attackValue = 6;
		
		description = "[" + name + "]\nDamage: 6\nSpeed: 25";
		attackArea.width = 36;
		attackArea.height = 36;
	}
}

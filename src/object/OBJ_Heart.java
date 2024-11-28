package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {
	public OBJ_Heart(GamePanel gp) {
		super(gp);
		
		type = type_pickupOnly;
		name = "Heart";
		value = 2;
		down1 = setup("/objects/heart_full.png", gp.tileSize, gp.tileSize);
		image = setup("/objects/heart_full.png", gp.tileSize, gp.tileSize);
		image2 = setup("/objects/heart_half.png", gp.tileSize, gp.tileSize);
		image3 = setup("/objects/heart_blank.png", gp.tileSize, gp.tileSize);
	}
	
	public boolean use(Entity entity) {
		gp.playSE(2);
		//gp.ui.addMessage("Life +" + value);
		entity.life += value;
		if (entity.life > entity.maxLife) {
			entity.life = entity.maxLife; 
		}
		return true;
	}
}

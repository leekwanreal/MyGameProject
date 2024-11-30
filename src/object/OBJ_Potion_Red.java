package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity{
	GamePanel gp;
	int value = 4;
	
	public OBJ_Potion_Red(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		stackable = true;
		type = type_consumable;
		name = "Red Potion";
		down1 = setup("/objects/potion_red.png", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\nHeals your life by " + value + ".";
	}
	
	public boolean use(Entity entity) {
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "You drank the " + name + "!\n" 
				+ "Your life has been recovered by " + value + ".";
		entity.life += value;
		if (gp.player.life > gp.player.maxLife) {
			gp.player.life = gp.player.maxLife;
		}
		gp.playSE(2);
		return true;
	}
}

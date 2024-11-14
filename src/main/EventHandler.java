package main;

import java.awt.Rectangle;

public class EventHandler {
	GamePanel gp;
	EventRect eventRect[][];
	
	int previousEventX, previousEventY;
	boolean canTouchEvent;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
		
		for (int i = 0; i < gp.maxWorldRow; ++i) {
			for (int j = 0; j < gp.maxWorldCol; ++j) {
				eventRect[j][i] = new EventRect();
				eventRect[j][i].x = 23;
				eventRect[j][i].y = 23;
				eventRect[j][i].width = 2;
				eventRect[j][i].height = 2;
				eventRect[j][i].eventRectDefaultX = eventRect[j][i].x;
				eventRect[j][i].eventRectDefaultY = eventRect[j][i].y;
			}
		}

	}
	
	public void checkEvent() {
		// Check if the player character is more than 1 tile away from the last event
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if (distance > gp.tileSize) {
			canTouchEvent = true;
		}
		
		if (canTouchEvent) {
			//if (hit(27,16,"right")) {damagePit(27,16,gp.dialogueState);}
			//if (hit(23,19,"any")) {damagePit(27,16,gp.dialogueState);}
			//if (hit(23,12,"up")) {healingPool(23,12,gp.dialogueState);}
			if (hit(18,15,"any")) {
				healingPool(18,15,gp.dialogueState);
			}
		}
	}
	
	public boolean hit(int col, int row, String reqDirection) {
		boolean hit = false;
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
		eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;
		
		if (gp.player.solidArea.intersects(eventRect[col][row]) && ! eventRect[col][row].eventDone) {
			if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;
				
				previousEventX = gp.player.worldX;
				previousEventY = gp.player.worldY;
			}
		}
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
		eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
		
		return hit;
	}
	
	public void teleport(int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "Teleport!";
		gp.player.worldX = gp.tileSize * 37;
		gp.player.worldY = gp.tileSize * 10;
		
	}
	
	public void damagePit(int col, int row, int gameState) {
		gp.gameState = gameState;
		gp.playSE(6);
		gp.ui.currentDialogue = "You have fallen into a pit!";
		gp.player.life -= 1;
		
		//eventRect[col][row].eventDone = true;
		canTouchEvent = false;
	}
	
	public void healingPool(int col, int row, int gameState) {
		
		if (gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.player.attackCanceled = true;
			gp.playSE(2);
			gp.ui.currentDialogue = "You drink the water. \nYour life has been recovered.";
			gp.player.life = gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
			gp.aSetter.setMonster();
		}
		
	}
}

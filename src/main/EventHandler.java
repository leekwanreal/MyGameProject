package main;

public class EventHandler {
	GamePanel gp;
	EventRect eventRect[][][];
	
	int previousEventX, previousEventY;
	boolean canTouchEvent;
	int talkToNPCindex = 0;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		for (int map = 0; map < gp.maxMap; map++) {
			for (int i = 0; i < gp.maxWorldRow; ++i) {
				for (int j = 0; j < gp.maxWorldCol; ++j) {
					eventRect[map][j][i] = new EventRect();
					eventRect[map][j][i].x = 23;
					eventRect[map][j][i].y = 23;
					eventRect[map][j][i].width = 2;
					eventRect[map][j][i].height = 2;
					eventRect[map][j][i].eventRectDefaultX = eventRect[map][j][i].x;
					eventRect[map][j][i].eventRectDefaultY = eventRect[map][j][i].y;
				}
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
			if (hit(0,27,24,"left") || hit(0,27,25,"left")) {
				talkToNPC(gp.dialogueState);
			}	

			if (hit(0,39,36,"any") || hit(0,39,37,"any")) {
				transitMap(1, 28, 25);
			}

			if (hit(1,45,24,"any") || hit(1,45,25,"any")) {
				transitMap(2, 16, 11);
			}

			
			if ((hit(0,27,22,"any") || 
				hit(0,28,22,"any") ||
				hit(0,29,22,"any")) && gp.setMonster1 == false) {
				gp.setMonster1 = true;
				gp.aSetter.setMonster1();
			}
			
			if ((hit(0,18,17,"any") || 
				hit(0,18,16,"any")) && gp.setMonster2 == false) {
				gp.setMonster2 = true;
				gp.aSetter.setMonster2();
			}
			
			if ((hit(0,20,23,"any") || 
				hit(0,21,23,"any")) && gp.setMonster3 == false) {
				gp.setMonster3 = true;
				gp.aSetter.setMonster3();
			}

			if ((hit(0,28,28,"any") || 
				hit(0,29,28,"any")) && gp.setMonster4 == false) {
				gp.setMonster4 = true;
				gp.aSetter.setMonster4();
			}		
			
			if ((hit(0,27,34,"any") || 
				hit(0,27,35,"any") ||
				hit(0,27,36,"any") ||
				hit(0,27,37,"any") ||
				hit(0,27,38,"any") ||
				hit(0,27,39,"any")) && gp.setMonster5 == false) {
				gp.setMonster5 = true;
				gp.aSetter.setMonster5();
			}
		}
	}
	
	public boolean hit(int map, int col, int row, String reqDirection) {
		boolean hit = false;
		
		if (map == gp.currentMap) {
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;
			
			if (gp.player.solidArea.intersects(eventRect[map][col][row]) && ! eventRect[map][col][row].eventDone) {
				if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hit = true;
					
					previousEventX = gp.player.worldX;
					previousEventY = gp.player.worldY;
				}
			}
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}

		return hit;
	}
	
	public void teleport(int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "Teleport!";
		gp.player.worldX = gp.tileSize * 37;
		gp.player.worldY = gp.tileSize * 10;
		
	}
	
	public void damagePit(int gameState) {
		gp.gameState = gameState;
		gp.playSE(6);
		gp.ui.currentDialogue = "You have fallen into a pit!";
		gp.player.life -= 1;
		
		//eventRect[col][row].eventDone = true;
		canTouchEvent = false;
	}
	
	public void healingPool(int gameState) {
		
		if (gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.player.attackCanceled = true;
			gp.playSE(2);
			gp.ui.currentDialogue = "You drink the water. \nYour life has been recovered.";
			gp.player.life = gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
			//gp.aSetter.setMonster();
		}
		
	}

	public void talkToNPC(int gameState) {
		if (gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.player.attackCanceled = true;
			switch(talkToNPCindex) {
			case 0: gp.ui.currentDialogue = "Eva: Help me please!"; break;
			case 1: gp.ui.currentDialogue = "Eva: I am trapped here because the monsters \nhave taken over the school!"; break;
			case 2: gp.ui.currentDialogue = "Eva: You need to rescue the school from the \nmonsters!"; break;

			}
			talkToNPCindex++;
			if (talkToNPCindex > 2) {
				talkToNPCindex = 0;
			}
			//gp.saveLoad.save();
		}
	}

	public void transitMap(int map, int col, int row) {
		gp.currentMap = map;
		gp.player.worldX = col * gp.tileSize;
		gp.player.worldY = row * gp.tileSize;
		previousEventX = gp.player.worldX;
		previousEventY = gp.player.worldY;
		canTouchEvent = false;
	}

}

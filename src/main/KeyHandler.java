package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;
	//Debug
	public boolean showDebugText;
	GamePanel gp;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	} 
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code =  e.getKeyCode();
		
		// Title State
		if (gp.gameState == gp.titleState) {
			titleState(code);
		}
	
		// Play State
		else if (gp.gameState == gp.playState) {
			playState(code);
		}
		
		// Pause State
		else if (gp.gameState == gp.pauseState) {
			pauseState(code);
		}
		
		// Dialogue State
		else if (gp.gameState == gp.dialogueState) {
			dialogueState(code);
		}
		
		// Character State
		else if (gp.gameState == gp.characterState) {
			characterState(code);
		}
		
		// Option State
		else if (gp.gameState == gp.optionState) {
			optionState(code);
		}
		
		// Game Over State
		else if (gp.gameState == gp.gameOverState) {
			gameOverState(code);
		}
		

		/*
		if (code == KeyEvent.VK_F11) {
			gp.zoomInOut(1);
		}
		if (code == KeyEvent.VK_F12) {
			gp.zoomInOut(-1);
		}
		*/
	}
	
	public void titleState(int code) {
		if ((code == KeyEvent.VK_W || code == KeyEvent.VK_UP) && gp.ui.commandNum > 0) {
			gp.ui.commandNum--;
		}
		if ((code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) && gp.ui.commandNum < 2) {
			gp.ui.commandNum++;
		}
		if (code == KeyEvent.VK_ENTER) {
			if (gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				//gp.playMusic(0);
			}
			if (gp.ui.commandNum == 1) {
				
			}
			if (gp.ui.commandNum == 2) {
				System.exit(0);
			}
		}
	}
	
	public void playState(int code) {
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
		if (code == KeyEvent.VK_P) {
				gp.gameState = gp.pauseState;
		}
		if (code == KeyEvent.VK_C) {
			gp.gameState = gp.characterState;
		}
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if (code == KeyEvent.VK_SPACE) {
			shotKeyPressed = true;
		}
		if (code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.optionState;
		}
		
		// Debug
		if (code == KeyEvent.VK_T) {
			if (!showDebugText) {
				showDebugText = true;
			}
			else {
				showDebugText = false;
			}
		}
		if (code == KeyEvent.VK_R) {
			switch(gp.currentMap) {
			case 0: gp.tileM.loadMap("/maps/mymap1.txt", 0);
			case 1: gp.tileM.loadMap("/maps/mymap2.txt", 1);
			}
		}
	}
	
	public void pauseState(int code) {
		if (code == KeyEvent.VK_P) {
			gp.gameState = gp.playState;
		}
	}
	
	public void dialogueState(int code) {
		if (code == KeyEvent.VK_ENTER) {
			gp.gameState = gp.playState;
		}
	}
	
	public void characterState(int code) {
		if (code == KeyEvent.VK_C) {
			gp.gameState = gp.playState;
		}
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			if (gp.ui.slotRow > 0) { 
				gp.ui.slotRow--;
				gp.playSE(9);
			}
		}
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			if (gp.ui.slotCol > 0) {
				gp.ui.slotCol--;
				gp.playSE(9);
			}
		}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			if (gp.ui.slotRow < 3) {
				gp.ui.slotRow++;
				gp.playSE(9);	
			}
		}
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			if (gp.ui.slotCol < 4) {
				gp.ui.slotCol++;
				gp.playSE(9);	
			}
		}
		if (code == KeyEvent.VK_ENTER) {
			gp.player.selectItem();
		}
	}
	
	public void optionState(int code) {
		if (code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		int maxCommandNum = 0;
		switch(gp.ui.subState) {
		case 0: maxCommandNum = 5; break;
		case 3: maxCommandNum = 1; break;
		}
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			if (gp.ui.commandNum > 0) {
				gp.ui.commandNum--;
				gp.playSE(9);
			}
		}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			if (gp.ui.commandNum < maxCommandNum) {
				gp.ui.commandNum++;
				gp.playSE(9);			
			}
		}
		
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			 if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
				 gp.music.volumeScale--;
				 gp.music.checkVolume();
				 gp.playSE(9);
			 }
			 
			 if (gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
				 gp.se.volumeScale--;
				 gp.playSE(9);
			 }
		}
		
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			 if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
				 gp.music.volumeScale++;
				 gp.music.checkVolume();
				 gp.playSE(9);
			 }
			 
			 if (gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
				 gp.se.volumeScale++;
				 gp.playSE(9);
			 }
		}
	}
	
	public void gameOverState(int code) {
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			if (gp.ui.commandNum > 0) {
				gp.ui.commandNum--;
				gp.playSE(9);
			}
		}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			if (gp.ui.commandNum  < 1) {
				gp.ui.commandNum++;
				gp.playSE(9);
			}
		}
		if (code == KeyEvent.VK_ENTER) {
			if (gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				gp.ui.commandNum = 0;
				gp.retry();
				gp.playMusic(0);
			}
			else if (gp.ui.commandNum == 1) {
				gp.gameState = gp.titleState;
				gp.ui.commandNum = 0;
				gp.restart();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code =  e.getKeyCode();
		
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}		
		if (code == KeyEvent.VK_SPACE) {
			shotKeyPressed = false;
		}
	}	

}

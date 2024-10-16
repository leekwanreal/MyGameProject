package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
	//Debug
	public boolean checkDrawTime;
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
			if (gp.ui.titleScreenState == 0) {
				if ((code == KeyEvent.VK_W || code == KeyEvent.VK_UP) && gp.ui.commandNum > 0) {
					gp.ui.commandNum--;
				}
				if ((code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) && gp.ui.commandNum < 2) {
					gp.ui.commandNum++;
				}
				if (code == KeyEvent.VK_ENTER) {
					if (gp.ui.commandNum == 0) {
						gp.ui.titleScreenState = 1;
					}
					if (gp.ui.commandNum == 1) {
						
					}
					if (gp.ui.commandNum == 2) {
						System.exit(0);
					}
				}
			}
			else if (gp.ui.titleScreenState == 1) {
				if ((code == KeyEvent.VK_W || code == KeyEvent.VK_UP) && gp.ui.commandNum > 0) {
					gp.ui.commandNum--;
				}
				if ((code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) && gp.ui.commandNum < 3) {
					gp.ui.commandNum++;
				}
				if (code == KeyEvent.VK_ENTER) {
					if (gp.ui.commandNum == 0) {
						System.out.println("Do some fighter specific stuff!");
						gp.gameState = gp.playState;
						gp.playMusic(0);
					}
					if (gp.ui.commandNum == 1) {
						System.out.println("Do some thief specific stuff!");
						gp.gameState = gp.playState;
						gp.playMusic(0);
					}
					if (gp.ui.commandNum == 2) {
						System.out.println("Do some sorcerer specific stuff!");
						gp.gameState = gp.playState;
						gp.playMusic(0);
					}
					if (gp.ui.commandNum == 3) {
						gp.ui.titleScreenState = 0;
						gp.ui.commandNum = 0;
					}
				}
			}
		}
		
		if (gp.gameState == gp.titleState) {}
		
		// Play State
		if (gp.gameState == gp.playState) {
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
			if (code == KeyEvent.VK_ENTER) {
				enterPressed = true;;
			}
			
			// Debug
			if (code == KeyEvent.VK_T) {
				if (!checkDrawTime) {
					checkDrawTime = true;
				}
				else {
					checkDrawTime = false;
				}
			}
		}
		
		// Pause State
		else if (gp.gameState == gp.pauseState) {
			if (code == KeyEvent.VK_P) {
				gp.gameState = gp.playState;
			}
		}
		
		// Dialogue State
		else if (gp.gameState == gp.dialogueState) {
			if (code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
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
	}	

}

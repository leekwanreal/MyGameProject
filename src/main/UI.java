package main;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import entity.Entity;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font cooperBlack, purisaB;
	BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank;
	public boolean messageOn = false;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public boolean gameFinished = false;
	public String currentDialogue;
	public int commandNum = 0;
	public int titleScreenState = 0; // 0: first screen, 1: second screen
	public int slotCol = 0;
	public int slotRow = 0;
	int subState = 0;
	BufferedImage background1, background2, background3, background4;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		try {
			InputStream is = getClass().getResourceAsStream("/font/COOPBL.TTF");
			cooperBlack = Font.createFont(Font.TRUETYPE_FONT, is);	
			//is = getClass().getResourceAsStream("/font/Purisa Bold.tff");
			//purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
			is.close();		}
		catch (FontFormatException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		// Create HUD Object
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		Entity crystal = new OBJ_ManaCrystal(gp);
		crystal_full = crystal.image;
		crystal_blank = crystal.image2;
		setup();
		
	}
	
	public void addMessage(String text) {
		message.add(text);
		messageCounter.add(0);
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
	
		g2.setFont(cooperBlack);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		
		// Title State
		if (gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		// Play State
		if (gp.gameState == gp.playState) {
			drawPlayerLife();
			drawMessage();
		}
		// Pause State
		if (gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		// Dialogue State
		if (gp.gameState == gp.dialogueState) {
			drawPlayerLife();
			drawDialogueScreen();
		}
		// Character State
		if (gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory();
		}
		// Option State
		if (gp.gameState == gp.optionState) {
			drawOptionScreen();
		}
		// Game Over State
		if (gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
	}
	
	public void drawPlayerLife() {
		
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		int iconSize = 32;
		
		// Draw Blank Heart
		while (i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, iconSize, iconSize, null);
			i++;
			x += iconSize;
		}
		
		// Reset
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		
		// Draw Current Life
		while (i < gp.player.life) {
			g2.drawImage(heart_half, x, y, iconSize, iconSize, null);
			i++;
			if (i < gp.player.life) {
				g2.drawImage(heart_full, x, y, iconSize, iconSize, null);
			}
			i++;
			x += iconSize;
		}
		
		iconSize = 32;
		// Draw Max Mana
		x = gp.tileSize/2-5;
		y = (int) (gp.tileSize*1.25);
		i = 0;
		while (i < gp.player.maxMana) {
			g2.drawImage(crystal_blank, x, y, iconSize, iconSize, null);
			i++;
			x += 20;
		}
		
		// Draw Mana
		x = gp.tileSize/2-5;
		y = (int) (gp.tileSize*1.25);
		i = 0;
		while (i < gp.player.mana) {
			g2.drawImage(crystal_full, x, y, iconSize, iconSize, null);
			i++;
			x += 20;
		}
		
	}

	public void drawMonsterLife() {			
		// Monster HP bar

		for (int i = 0; i < gp.monster[1].length; ++i) {

			Entity monster = gp.monster[gp.currentMap][i];

			if (monster != null && monster.inCamera() == true) {
				if (monster.hpBarOn == true && monster.boss == false) {
					double oneScale = (double)gp.tileSize/monster.maxLife;
					double hpBarValue = oneScale*monster.life;
					
					g2.setColor(new Color(35, 35, 35));
					g2.fillRect(monster.getScreenX()-1, monster.getScreenY()-16, gp.tileSize+2, 12);
					g2.setColor(new Color(250, 0, 30));
					g2.fillRect(monster.getScreenX(), monster.getScreenY() - 15, (int)hpBarValue, 10);
					
					monster.hpBarCounter++;
		
					if (monster.hpBarCounter > 600) {
						monster.hpBarCounter = 0;
						monster.hpBarOn = false;
					}
				}
				else if (monster.boss == true) {
					double oneScale = (double)gp.tileSize*8/monster.maxLife;
					double hpBarValue = oneScale*monster.life;

					int x = gp.screenWidth/2 - gp.tileSize*4;
					int y = gp.tileSize * 10;
					
					g2.setColor(new Color(35, 35, 35));
					g2.fillRect(x-1, y-1, gp.tileSize*8+2, 24);
					g2.setColor(new Color(250, 0, 30));
					g2.fillRect(x, y, (int)hpBarValue, 20);

					g2.setFont(g2.getFont().deriveFont(Font.BOLD,24f));
					g2.setColor(Color.white);
					g2.drawString(monster.name, x+4, y-10);
				}
			}
		}

		
	}
	
	public void drawMessage() {
		int messageX = gp.tileSize;
		int messageY = gp.tileSize * 4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		
		for (int i = 0; i < message.size(); ++i) {
			if (message.get(i) != null) {
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX+2, messageY+2);
				
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1;
				messageCounter.set(i, counter);
				messageY += 50;
				
				if (messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
	}
	
	public void drawTitleScreen() {				
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		// Draw background
		float alpha = 0.5f; // 50% transparency
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
		g2.setComposite(ac);
		g2.drawImage(background1, 0, 0, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
	
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
		String text = "Damsterams";
		int x = getXforCenteredText(text);
		int y = gp.tileSize*2;
		
		g2.setColor(Color.black);
		g2.drawString(text, x, y+5);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		text = "Has Fallen";
		x = getXforCenteredText(text);
		y += gp.tileSize*2;
		
		g2.setColor(Color.black);
		g2.drawString(text, x, y+5);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		// Menu shadow.
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
		g2.setColor(Color.black);

		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize*3.5;
		g2.drawString(text, x, y+3);
		if (commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y+3);
		}
		
		text = "LOAD GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y+3);
		if (commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y+3);
		}
		
		text = "QUIT";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y+3);
		if (commandNum == 2) {
			g2.drawString(">", x - gp.tileSize, y+3);
		}
		
		// Menu white
		g2.setColor(Color.white);
		y = gp.tileSize * 4;

		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize*3.5;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "LOAD GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "QUIT";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 2) {
			g2.drawString(">", x - gp.tileSize, y);
		}
	}
	
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	public void drawDialogueScreen() {
		// Window
		int x = gp.tileSize * 2;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize * 4);
		int height = gp.tileSize * 4;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
	}
	
	public void drawCharacterScreen() {
		final int frameX = gp.tileSize;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize * 6;
		final int frameHeight = gp.tileSize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// Text
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35;
		
		// Names
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Life", textX, textY);
		textY += lineHeight;
		g2.drawString("Mana", textX, textY);
		textY += lineHeight;
		g2.drawString("Strength", textX, textY);
		textY += lineHeight;
		g2.drawString("Dexterity", textX, textY);
		textY += lineHeight;
		g2.drawString("Attack", textX, textY);
		textY += lineHeight;
		g2.drawString("Defense", textX, textY);
		textY += lineHeight;
		g2.drawString("Exp", textX, textY);
		textY += lineHeight;
		g2.drawString("Next Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Coin", textX, textY);
		textY += lineHeight + 10;
		g2.drawString("Weapon", textX, textY);
		textY += lineHeight + 10;
		g2.drawString("Shield", textX, textY);
		textY += lineHeight;
		
		// Values
		int tailX = (frameX + frameWidth) - 30;
		// Reset textY
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.strength);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.dexterity);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.attack);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.defense);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.exp);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.coin);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
	
		g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 24, null);
		textY += gp.tileSize;
		g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 24, null);
	}
	
	public void drawInventory() {
		// Frame
		int frameX = gp.tileSize * 12;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize * 6 + 10;
		int frameHeight = gp.tileSize * 5;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// Slot
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.tileSize + 3;
		
		// Draw Player's Item
		for (int i = 0; i < gp.player.inventory.size(); ++i) {
			// Equip Cursor
			if (gp.player.inventory.get(i) == gp.player.currentWeapon ||
				gp.player.inventory.get(i) == gp.player.currentShield) {
				g2.setColor(new Color(240,190,90));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
			
			
			g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
			
			// Display Amount
			if (gp.player.inventory.get(i).amount > 1) {
				g2.setFont(g2.getFont().deriveFont(32f));
				int amountX;
				int amountY;

				String s = "" + gp.player.inventory.get(i).amount;
				amountX = getXforAlignToRightText(s, slotX + 44);
				amountY = slotY + gp.tileSize;
				
				// Shadow
				g2.setColor(new Color(60,60,60));
				g2.drawString(s,amountX,amountY);

				// Number
				g2.setColor(Color.white);
				g2.drawString(s, amountX, amountY);
			}


			
			slotX += slotSize;
			if (i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += slotSize;
			}
		}
		
		// Cursor
		int cursorX = slotXstart + (slotSize * slotCol);
		int curSorY = slotYstart + (slotSize * slotRow);
		int curSorWidth = gp.tileSize;
		int curSorHeight = gp.tileSize;
	
		// Draw Cursor
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, curSorY, curSorWidth, curSorHeight, 10, 10);
		
		// Description Frame
		int dFrameX = frameX;
		int dFrameY = frameY + frameHeight;
		int dFrameWidth = frameWidth;
		int dFrameHeight = gp.tileSize*3;
		
		// Draw Description Text
		int textX = dFrameX + 20;
		int textY = dFrameY + gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(25F));
		
		int itemIndex = getItemIndexOnSlot();
		if (itemIndex < gp.player.inventory.size()) {
			drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
			for (String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
				g2.drawString(line, textX, textY);
				textY += 32;
			}
		}
	}
	
	public void drawGameOverScreen() {
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
		
		text = "Game Over";
		
		// Shadow
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.tileSize*4;
		g2.drawString(text,x,y);
		// Main
		g2.setColor(Color.white);
		g2.drawString(text,x-4,y-4);
		
		// Retry
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Retry";
		x = getXforCenteredText(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - 40, y);
		}
	
		// Back to the title screen
		text = "Quit";
		x = getXforCenteredText(text);
		y += 55;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x - 40, y);
		}
		
	}
	
	public void drawOptionScreen() {
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(30F));
		
		// Sub Window
		int frameX = gp.tileSize*6;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*8;
		int frameHeight = gp.tileSize*10;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
				
		switch(subState) {
		case 0: options_top(frameX, frameY); break;
		case 1: options_fullScreenNotification(frameX, frameY); break;
		case 2: options_control(frameX, frameY); break;
		case 3: options_endGameConfirmation(frameX, frameY); break;
		}	
		
		
		gp.keyH.enterPressed = false;
	}
	
	public void options_top(int frameX, int frameY) {
		int textX;
		int textY;
		
		// Title
		String text = "Options";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		// Full Screen On/Off
		textX = frameX + gp.tileSize;
		textY += gp.tileSize*2;
		g2.drawString("Full Screen", textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed == true) {
				if (gp.fullScreenOn == false) {
					gp.fullScreenOn = true;
				}
				else if (gp.fullScreenOn == true) {
					gp.fullScreenOn = false;
				}
				subState = 1;
			}
		}
		
		// Music
		textY += gp.tileSize;
		g2.drawString("Music", textX, textY);
		if (commandNum == 1) {
			g2.drawString(">", textX - 25, textY);
		}
		
		// SE
		textY += gp.tileSize;
		g2.drawString("SE", textX, textY);
		if (commandNum == 2) {
			g2.drawString(">", textX - 25, textY);
		}
		
		// Control
		textY += gp.tileSize;
		g2.drawString("Control", textX, textY);
		if (commandNum == 3) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}
		
		// End Game
		textY += gp.tileSize;
		g2.drawString("End Game", textX, textY);
		if (commandNum == 4) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 3;
				commandNum = 0;
			}
		}
		
		// Back
		textY += gp.tileSize * 2;
		g2.drawString("Back", textX, textY);
		if (commandNum == 5) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed == true) {
				gp.gameState = gp.playState;
				commandNum= 0;
			}
		}
		
		// Full Screen Check Box
		textX = frameX + gp.tileSize*5;
		textY = frameY + gp.tileSize*2 + 24;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY, 24, 24);
		if (gp.fullScreenOn == true) {
			g2.fillRect(textX, textY, 24, 24);
		}
		
		// Music Volume
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24); // 120/5 = 24
		int volumeWidth = 24 * gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		// SE Volume
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24 * gp.se.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		gp.config.saveConfig();
	
	}
	
	public void options_control(int frameX, int frameY) {
		int textX;
		int textY;
		
		// Resize Font
		g2.setFont(g2.getFont().deriveFont(24F));

		
		// Title
		String text = "Control";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize/2;
		textY += gp.tileSize;
		
		g2.drawString("Move", textX, textY); textY += gp.tileSize;
		g2.drawString("Confirm/Interact", textX, textY); textY += gp.tileSize;
		g2.drawString("Shoot/Cast", textX, textY); textY += gp.tileSize;
		g2.drawString("Character Screen", textX, textY); textY += gp.tileSize;
		g2.drawString("Pause", textX, textY); textY += gp.tileSize;
		g2.drawString("Options", textX, textY); textY += gp.tileSize;

		textX = frameX + gp.tileSize*11/2;
		textY = frameY + gp.tileSize*2;
		g2.drawString("WASD", textX, textY); textY += gp.tileSize;
		g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
		g2.drawString("SPACE", textX, textY); textY += gp.tileSize;
		g2.drawString("C", textX, textY); textY += gp.tileSize;
		g2.drawString("P", textX, textY); textY += gp.tileSize;
		g2.drawString("ESC", textX, textY); textY += gp.tileSize;

		// Back
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize*9;
		g2.drawString("Back", textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 3;
			}
		} 
	}
	
	public void options_fullScreenNotification(int frameX, int frameY) {
		int textX = frameX + 24;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "The change will take \neffect after restarting \nthe game.";
		for (String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// Back
		textX += 12;
		textY = frameY + gp.tileSize*9;
		g2.drawString("Back", textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 0;
			}
		}

	}
	
	public void options_endGameConfirmation(int frameX, int frameY) {
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "Quit the game and \nreturn to the title \nscreen.";
		
		for (String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// Yes
		String text = "Yes";
		textX = getXforCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 0;
				gp.gameState = gp.titleState;
				gp.stopMusic();
			}
		}
		
		// No
		text = "No";
		textX = getXforCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if (commandNum == 1) {
			g2.drawString(">", textX-25, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 4;
			}
		}
	
	}
	
	public int getItemIndexOnSlot() {
		int itemIndex = slotCol + (slotRow*5);
		return itemIndex;
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		Color c =  new Color(0,0,0,220);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x, y, width-10, height-10, 25, 25);
	}
	
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	
	public int getXforAlignToRightText(String text, int tailX) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}

	public void setup() {
		UtilityTool uTool = new UtilityTool();
		try {	
			background1 = ImageIO.read(getClass().getResourceAsStream("/background/background.png"));
			background1 = uTool.scaleImage(background1, gp.screenWidth, gp.screenHeight);
	
			background2 = ImageIO.read(getClass().getResourceAsStream("/background/rescueNPC.png"));
			background2 = uTool.scaleImage(background1, gp.screenWidth, gp.screenHeight);
	
			background3 = ImageIO.read(getClass().getResourceAsStream("/background/vsGoblin.png"));
			background3 = uTool.scaleImage(background1, gp.screenWidth, gp.screenHeight);
	
			background4 = ImageIO.read(getClass().getResourceAsStream("/background/victory.png"));
			background4 = uTool.scaleImage(background1, gp.screenWidth, gp.screenHeight);				
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}

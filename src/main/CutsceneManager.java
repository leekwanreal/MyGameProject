package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CutsceneManager {
    GamePanel gp;
    Graphics2D g2;
    public int sceneNum = 0;
    public int scenePhase;
    int counter = 0;
    float alpha = 0f;
    int y;
    String endCredit;
    BufferedImage background_ending;
    public final int ending = 0;

    public CutsceneManager(GamePanel gp) {
        this.gp = gp;
        endCredit = "\n\n\n\n\n\n\n\n\n\n"
                    + "A project done by:\n\n"
                    + "Nguyen Le Quan\n\n"
                    + "Vu Nam Khanh\n\n"
                    + "Hoang Anh Vu\n\n"
                    + "Nguyen Trinh Minh Hieu\n\n"
                    + "Vu Tien Dat";
        getBackground();
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        switch (sceneNum) {
            case ending:
                scene_ending();
                break;
        }
    }

    public void scene_ending() {
        if (scenePhase == 0) {
            gp.stopMusic();
            scenePhase++;
        }

        if (scenePhase == 1) {
            scenePhase++;
        }

        if (scenePhase == 2) {
            gp.playSE(4);
            scenePhase++;
        }

        if (scenePhase == 3) {
            // Wait until the sound effect ends
            if (counterReached(300) == true) {
                scenePhase++;
            }
        }

        if (scenePhase == 4) {
            // The screen gets darker
            alpha += 0.005;

            if (alpha > 1f) {
                alpha = 1f;
            }
            drawBlackBackground(alpha);

            if (alpha == 1f) {
                alpha = 0;
                scenePhase++;
            }
        }

        if (scenePhase == 5) {
            drawBlackBackground(1f);
            alpha += 0.005f;

            if (alpha > 1) {
                alpha = 1f;
            }
            String text = "After the fierce battle with the Goblin, \n"
                          + "Marc successfully defeated all monsters to \n"
                          + "save Damsterams High School and protect \n"
                          + "its special weapon.";
            drawString(alpha, 38f, 200, text, 70);

            if (counterReached(300) == true) {
                scenePhase++;
            }
        }

        if (scenePhase == 6) {
            y = gp.screenHeight / 2;
            drawBlackBackground(1f);
            drawBackground(0.5f);
            drawString(1f, 60f, y, "Damsterams Has Fallen", 40);

            if (counterReached(300) == true) {
                scenePhase++;
            }
        }

        if (scenePhase == 7) {
            y = gp.screenHeight / 2;
            drawBlackBackground(1f);
            drawBackground(0.5f);
            drawString(1f, 60f, y, "Damsterams Has N Fallen", 40);

            if (counterReached(30) == true) {
                scenePhase++;
            }
        }

        if (scenePhase == 8) {
            y = gp.screenHeight / 2;
            drawBlackBackground(1f);
            drawBackground(0.5f);
            drawString(1f, 60f, y, "Damsterams Has NO Fallen", 40);

            if (counterReached(30) == true) {
                scenePhase++;
            }
        }

        if (scenePhase == 9) {
            y = gp.screenHeight / 2;
            drawBlackBackground(1f);
            drawBackground(0.5f);
            drawString(1f, 60f, y, "Damsterams Has NOT Fallen", 40);

            if (counterReached(30) == true) {
                scenePhase++;
            }
        }

        if (scenePhase == 10) {
            drawBlackBackground(1f);
            drawBackground(0.5f);
            drawString(1f, 38f, y, endCredit, 40);

            if (counterReached(600) == true) {
                scenePhase++;
            }
        }

        if (scenePhase == 11) {
            drawBlackBackground(1f);
            drawBackground(0.5f);
            y--;
            drawString(1f, 38f, y, endCredit, 40);

            if (counterReached(600) == true) {
                scenePhase++;
            }
        }

        if (scenePhase == 12) {
            gp.gameState = gp.titleState;
        }
    }

    public boolean counterReached(int target) {
        boolean counterReached = false;
        counter++;

        if (counter > target) {
            counterReached = true;
            counter = 0;
        }
        return counterReached;
    }

    public void getBackground() {
        UtilityTool uTool = new UtilityTool();

        try {
            background_ending = ImageIO.read(getClass().getResourceAsStream("/background/victory.png"));
            background_ending = uTool.scaleImage(background_ending, gp.screenWidth, gp.screenHeight);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawBlackBackground(float alpha) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void drawBackground(float alpha) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.drawImage(background_ending, 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for (String line : text.split("\n")) {
            int x = gp.ui.getXforCenteredText(line);
            g2.drawString(line, x, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
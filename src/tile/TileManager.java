package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[200];
		mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		
		getTileImage();
		loadMap("/maps/mymap1.txt", 0);
		loadMap("/maps/mymap2.txt", 1);
	}
	public void getTileImage() {
		// Placeholder
		setup(0, "mydirt", false);
		setup(1, "mydirt", false);
		setup(2, "mydirt", false);
		setup(3, "mydirt", false);
		setup(4, "mydirt", false);
		setup(5, "mydirt", false);
		setup(6, "mydirt", false);
		setup(7, "mydirt", false);
		setup(8, "mydirt", false);
		setup(9, "mydirt", false);
		
		// Real setup
		setup(10, "mydirt", false);
		setup(11, "grass01", false);
		setup(12, "table2", true);
		setup(13, "table1", true);
		setup(14, "chair_1", true);
		setup(15, "chair_2", true);
		setup(16, "chair_3", true);
		setup(17, "chair_4", true);
		setup(18, "brick", true);
		setup(19, "stonelinedown", false);
		setup(20, "stonelineup", false);
		setup(21, "cellright", true);
		setup(22, "cellup", true);
		setup(23, "cellleft", true);
		setup(24, "celldown", true);
		setup(25, "celltopright", true);
		setup(26, "celltopleft", true);
		setup(27, "celldownleft", true);
		setup(28, "celldownright", true);
		setup(29, "cellfloor", false);
		setup(30, "mybush", true);
		setup(31, "boardtopright", true);
		setup(32, "boardtopleft", true);
		setup(33, "boarddownleft", true);
		setup(34, "boarddownright", true);
		setup(35, "boarddown", true);
		setup(36, "boardtop", true);
		setup(37, "tableboard2", true);
		setup(38, "bookshelf2", true);
		setup(39, "chairboard", true);
		setup(40, "stair1", false);
		setup(41, "holder1", false);
		setup(42, "england1", true);
		setup(43, "vietnam1", true);
		setup(44, "usa1", true);
		setup(45, "bed1", true);
		setup(46, "bed2", true);
		setup(47, "drawer", true);
		setup(48, "schoollogo", true);
		setup(49, "math_sign", true);
		setup(50, "computer", true);
		setup(51, "stadium_bot_horizontal_line", false); 
		setup(52, "stadium_botleft_corner", false); 
		setup(53, "stadium_botright_corner", false); 
		setup(54, "stadium_box_botleft", false); 
		setup(55, "stadium_box_botright", false); 
		setup(56, "stadium_box_topleft", false); 
		setup(57, "stadium_box_topright", false); 
		setup(58, "stadium_horizontal_line", false); 
		setup(59, "stadium_left_vertical_line", false); 
		setup(60, "stadium_top_horizontal_line", false); 
		setup(61, "stadium_topleft_corner", false); 
		setup(62, "stadium_topright_corner", false); 
		setup(63, "stadium_Tshape_down", false); 
		setup(64, "stadium_Tshape_left", false); 
		setup(65, "stadium_Tshape_right", false); 
		setup(66, "stadium_Tshape_up", false); 
		setup(67, "stadium_right_vertical_line", false); 
		setup(68, "stadium_plain", false); 
		setup(69, "stadium_vertical_line", false);
		setup(70, "kickoff", false);

		// 3 digits map 2
		setup(100, "ground(10)", false);
		setup(101, "blackstone(11)", true);
		setup(102, "bluegraystonefloor(12)", false);
		setup(103, "whitestair1(13)", false);
		setup(104, "whitestair2(14)", false);
		setup(105, "whitestair3(15)", false);
		setup(106, "blackstair(16)", true);
		setup(107, "pavement(17)", false);
		setup(108, "grass(18)", false);
		setup(109, "brick(19)", true);
		setup(123, "whitestair2c(20)", true);
		setup(110, "wall1(21)", true);
		setup(111, "wall2(22)", true);
		setup(112, "wall3(23)", true);
		setup(113, "glass(30)", true);
		setup(114, "glassdooroutside(31)", true);
		setup(115, "glassdoorleft(32)", true);
		setup(116, "glassdoorright(33)", true);
		setup(117, "window(34)", true);
		setup(118, "grey(90)", false);
		setup(119, "road1(91)", false);
		setup(120, "road2(92)", false);
		setup(121, "road3(93)", false);
		setup(122, "sidepavement(94)", true);
		
	}
	
	public void setup(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath, int map) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			for (int i = 0; i < gp.maxWorldRow; ++i) {
				String line = br.readLine();
				String numbers[] = line.split(" ");
				for (int j = 0; j < gp.maxWorldCol; ++j) {
					int num = Integer.parseInt(numbers[j]);
					mapTileNum[map][j][i] = num;
				}
			}
			br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2) {
		for (int i = 0; i < gp.maxWorldRow; ++i) {
			for (int j = 0; j < gp.maxWorldCol; ++j) {	
				int tileNum = mapTileNum[gp.currentMap][j][i];
				
				int worldX = j * gp.tileSize;
				int worldY = i * gp.tileSize;
				int screenX = worldX - gp.player.worldX + gp.player.screenX;
				int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
				// Stop moving the camera at the edge
				if (gp.player.screenX > gp.player.worldX) {
					screenX = worldX;
				}
				if (gp.player.screenY > gp.player.worldY) {
					screenY = worldY;
				}
				int rightOffset = gp.screenWidth - gp.player.screenX;
				if (rightOffset > gp.worldWidth - gp.player.worldX) {
					screenX = gp.screenWidth - (gp.worldWidth - worldX);
				}
				int bottomOffset = gp.screenHeight - gp.player.screenY;
				if (bottomOffset > gp.worldHeight - gp.player.worldY) {
					screenY = gp.screenHeight - (gp.worldHeight - worldY);
				}
				
				if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
					worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
					worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
					worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
					g2.drawImage(tile[tileNum].image, screenX, screenY, null);
				}
				else if (gp.player.screenX > gp.player.worldX ||
						 gp.player.screenY > gp.player.worldY ||
						 rightOffset > gp.worldWidth - gp.player.worldX ||
						 bottomOffset > gp.worldHeight - gp.player.worldY) {
						g2.drawImage(tile[tileNum].image, screenX, screenY, null);
				}
			}
		}
	}
}

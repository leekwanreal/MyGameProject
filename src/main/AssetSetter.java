package main;

import entity.NPC_OldMan;
import entity.NPC_Girl;
import monster.MON_Bat;
import monster.MON_GreenSlime;
import object.OBJ_Axe;
import object.OBJ_Boots;
import object.OBJ_Cell_Gate;
import object.OBJ_Chest;
import object.OBJ_Coin_Bronze;
import object.OBJ_Door;
import object.OBJ_Door_Room_1;
import object.OBJ_Door_Room_2;
import object.OBJ_Door_Room_3;
import object.OBJ_Door_Secret_Room;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_Key_Cell;
import object.OBJ_Key_Room_1;
import object.OBJ_Key_Room_2;
import object.OBJ_Key_Room_3;
import object.OBJ_Key_Secret_Room;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Red;
import object.OBJ_Rifle;
import object.OBJ_Shield_Blue;
import object.OBJ_Uzi;
import tile_interactive.IT_DryTree;

public class AssetSetter {
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		int mapNum = 0;
		int i = 0;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Uzi(gp));
		gp.obj[mapNum][i].setLoot();
		gp.obj[mapNum][i].worldX = gp.tileSize*31;
		gp.obj[mapNum][i].worldY = gp.tileSize*10;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Shield_Blue(gp));
		gp.obj[mapNum][i].setLoot();
		gp.obj[mapNum][i].worldX = gp.tileSize*32;
		gp.obj[mapNum][i].worldY = gp.tileSize*10;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Key_Room_1(gp));
		gp.obj[mapNum][i].setLoot();
		gp.obj[mapNum][i].worldX = gp.tileSize*25;
		gp.obj[mapNum][i].worldY = gp.tileSize*10;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Key_Room_2(gp));
		gp.obj[mapNum][i].setLoot();
		gp.obj[mapNum][i].worldX = gp.tileSize*24;
		gp.obj[mapNum][i].worldY = gp.tileSize*10;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door_Secret_Room(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*28;
		gp.obj[mapNum][i].worldY = gp.tileSize*27;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door_Secret_Room(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*29;
		gp.obj[mapNum][i].worldY = gp.tileSize*27;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Key_Cell(gp));
		gp.obj[mapNum][i].setLoot();
		gp.obj[mapNum][i].worldX = gp.tileSize*38;
		gp.obj[mapNum][i].worldY = gp.tileSize*32;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Key_Cell(gp));
		gp.obj[mapNum][i].setLoot();
		gp.obj[mapNum][i].worldX = gp.tileSize*39;
		gp.obj[mapNum][i].worldY = gp.tileSize*32;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Key_Secret_Room(gp));
		gp.obj[mapNum][i].setLoot();
		gp.obj[mapNum][i].worldX = gp.tileSize*10;
		gp.obj[mapNum][i].worldY = gp.tileSize*10;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Rifle(gp));
		gp.obj[mapNum][i].setLoot();
		gp.obj[mapNum][i].worldX = gp.tileSize*11;
		gp.obj[mapNum][i].worldY = gp.tileSize*10;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Key_Room_3(gp));
		gp.obj[mapNum][i].setLoot();
		gp.obj[mapNum][i].worldX = gp.tileSize*24;
		gp.obj[mapNum][i].worldY = gp.tileSize*24;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Key_Room_3(gp));
		gp.obj[mapNum][i].setLoot();
		gp.obj[mapNum][i].worldX = gp.tileSize*24;
		gp.obj[mapNum][i].worldY = gp.tileSize*25;
		i++;
		gp.obj[mapNum][i] = new OBJ_Cell_Gate(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*26;
		gp.obj[mapNum][i].worldY = gp.tileSize*24;
		i++;
		gp.obj[mapNum][i] = new OBJ_Cell_Gate(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*26;
		gp.obj[mapNum][i].worldY = gp.tileSize*25;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door_Room_1(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*22;
		gp.obj[mapNum][i].worldY = gp.tileSize*16;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door_Room_1(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*22;
		gp.obj[mapNum][i].worldY = gp.tileSize*17;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door_Room_2(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*19;
		gp.obj[mapNum][i].worldY = gp.tileSize*16;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door_Room_2(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*19;
		gp.obj[mapNum][i].worldY = gp.tileSize*17;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door_Room_3(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*20;
		gp.obj[mapNum][i].worldY = gp.tileSize*22;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door_Room_3(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*21;
		gp.obj[mapNum][i].worldY = gp.tileSize*22;
		i++;
		// Map 2
		
	}
	
	public void setNPC() {
		int mapNum = 0;
		gp.npc[mapNum][0] = new NPC_Girl(gp);
		gp.npc[mapNum][0].worldX = gp.tileSize * 25;
		gp.npc[mapNum][0].worldY = gp.tileSize * 24;

		mapNum = 1;
		gp.npc[mapNum][0] = new NPC_Girl(gp);
		gp.npc[mapNum][0].worldX = gp.tileSize * 14;
		gp.npc[mapNum][0].worldY = gp.tileSize * 16;

	}
	
	public void setMonster1() {
		
		int mapNum = 0;

		// Spawn first wave
		gp.monster[mapNum][1] = new MON_Bat(gp);
		gp.monster[mapNum][1].worldX = gp.tileSize * 23;
		gp.monster[mapNum][1].worldY = gp.tileSize * 10;

		gp.monster[mapNum][2] = new MON_Bat(gp);
		gp.monster[mapNum][2].worldX = gp.tileSize * 33;
		gp.monster[mapNum][2].worldY = gp.tileSize * 10;
		
		gp.monster[mapNum][3] = new MON_Bat(gp);
		gp.monster[mapNum][3].worldX = gp.tileSize * 36;
		gp.monster[mapNum][3].worldY = gp.tileSize * 10;

		gp.monster[mapNum][4] = new MON_Bat(gp);
		gp.monster[mapNum][4].worldX = gp.tileSize * 39;
		gp.monster[mapNum][4].worldY = gp.tileSize * 10;


		new Thread(() -> {
			try {
				Thread.sleep(15000); // Pause for 8 seconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Spawn second wave
			gp.monster[mapNum][5] = new MON_Bat(gp);
			gp.monster[mapNum][5].worldX = gp.tileSize * 23;
			gp.monster[mapNum][5].worldY = gp.tileSize * 10;
			
			gp.monster[mapNum][6] = new MON_Bat(gp);
			gp.monster[mapNum][6].worldX = gp.tileSize * 33;
			gp.monster[mapNum][6].worldY = gp.tileSize * 10;

			gp.monster[mapNum][7] = new MON_Bat(gp);
			gp.monster[mapNum][7].worldX = gp.tileSize * 36;
			gp.monster[mapNum][7].worldY = gp.tileSize * 10;

			gp.monster[mapNum][8] = new MON_Bat(gp);
			gp.monster[mapNum][8].worldX = gp.tileSize * 39;
			gp.monster[mapNum][8].worldY = gp.tileSize * 10;
		}).start();

		new Thread(() -> {
			try {
				Thread.sleep(30000); // Pause for 16 seconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Spawn third wave
			gp.monster[mapNum][9] = new MON_Bat(gp);
			gp.monster[mapNum][9].worldX = gp.tileSize * 23;
			gp.monster[mapNum][9].worldY = gp.tileSize * 10;

			gp.monster[mapNum][10] = new MON_Bat(gp);
			gp.monster[mapNum][10].worldX = gp.tileSize * 33;
			gp.monster[mapNum][10].worldY = gp.tileSize * 10;

			gp.monster[mapNum][11] = new MON_Bat(gp);
			gp.monster[mapNum][11].worldX = gp.tileSize * 36;
			gp.monster[mapNum][11].worldY = gp.tileSize * 10;

			gp.monster[mapNum][12] = new MON_Bat(gp);
			gp.monster[mapNum][12].worldX = gp.tileSize * 39;
			gp.monster[mapNum][12].worldY = gp.tileSize * 10;


		}).start();


	}

	public void clearMonster() {
		for (int i = 0; i < gp.maxMap; ++i) {
			for (int j = 0; j < 20; ++j) {
				gp.monster[i][j]  = null;
			}
		}
		gp.setMonster1 = false;
	}
	
	public void setInteractiveTile() {
		/* 
		int mapNum = 0;
		int i = 0;
		gp.iTile[i] = new IT_DryTree(gp,27,12);i++;
		gp.iTile[i] = new IT_DryTree(gp,28,12);i++;
		gp.iTile[i] = new IT_DryTree(gp,29,12);i++;
		gp.iTile[i] = new IT_DryTree(gp,30,12);i++;
		gp.iTile[i] = new IT_DryTree(gp,31,12);i++;
		gp.iTile[i] = new IT_DryTree(gp,32,12);i++;
		gp.iTile[i] = new IT_DryTree(gp,33,12);i++;
		*/
	}
}

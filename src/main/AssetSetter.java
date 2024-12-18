package main;

import entity.Turret;
import entity.NPC_Girl;
import monster.MON_Bat;
import monster.MON_Goblin;
import monster.MON_Skeleton;
import monster.MON_Witch;
import object.OBJ_AK47;
import object.OBJ_AWM;
import object.OBJ_Cell_Gate;
import object.OBJ_Chest;
import object.OBJ_Door_Room_1;
import object.OBJ_Door_Room_2;
import object.OBJ_Door_Room_3;
import object.OBJ_Door_Secret_Room;
import object.OBJ_Gate_Left;
import object.OBJ_Gate_Right;
import object.OBJ_Key_Cell;
import object.OBJ_Key_Room_1;
import object.OBJ_Key_Room_2;
import object.OBJ_Key_Room_3;
import object.OBJ_Key_Secret_Room;
import object.OBJ_Key_Turret;
import object.OBJ_Rifle;
import object.OBJ_Shield_Blue;
import object.OBJ_Shotgun;
import object.OBJ_Snow_Gun;
import object.OBJ_Uzi;

public class AssetSetter {
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		// Map 1
		int mapNum = 0;
		int i = 0;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Uzi(gp));
		gp.obj[mapNum][i].setLoot();
		gp.obj[mapNum][i].worldX = gp.tileSize*25;
		gp.obj[mapNum][i].worldY = gp.tileSize*10;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Shield_Blue(gp));
		gp.obj[mapNum][i].setLoot();
		gp.obj[mapNum][i].worldX = gp.tileSize*39;
		gp.obj[mapNum][i].worldY = gp.tileSize*10;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Key_Room_1(gp));
		gp.obj[mapNum][i].setLoot();
		gp.obj[mapNum][i].worldX = gp.tileSize*38;
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
		gp.obj[mapNum][i].worldX = gp.tileSize*39;
		gp.obj[mapNum][i].worldY = gp.tileSize*31;
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
		gp.obj[mapNum][i] = new OBJ_Door_Room_2(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*19;
		gp.obj[mapNum][i].worldY = gp.tileSize*16;
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
		i = 0;
		mapNum = 1;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_AK47(gp));
		gp.obj[mapNum][i].setLoot();
		gp.obj[mapNum][i].worldX = gp.tileSize*31;
		gp.obj[mapNum][i].worldY = gp.tileSize*20;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_AWM(gp));
		gp.obj[mapNum][i].setLoot();
		gp.obj[mapNum][i].worldX = gp.tileSize*32;
		gp.obj[mapNum][i].worldY = gp.tileSize*20;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Shotgun(gp));
		gp.obj[mapNum][i].setLoot();
		gp.obj[mapNum][i].worldX = gp.tileSize*31;
		gp.obj[mapNum][i].worldY = gp.tileSize*29;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Key_Turret(gp));
		gp.obj[mapNum][i].setLoot();
		gp.obj[mapNum][i].worldX = gp.tileSize*32;
		gp.obj[mapNum][i].worldY = gp.tileSize*29;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Snow_Gun(gp));
		gp.obj[mapNum][i].setLoot();
		gp.obj[mapNum][i].worldX = gp.tileSize*33;
		gp.obj[mapNum][i].worldY = gp.tileSize*20;
		i++;
		gp.obj[mapNum][i] = new Turret(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 35;
		gp.obj[mapNum][i].worldY = gp.tileSize * 23;
		i++;

		// Map 3
		i = 0;
		mapNum = 2;
		gp.obj[mapNum][i] = gp.turret;
		gp.obj[mapNum][i].worldX = gp.tileSize * 14;
		gp.obj[mapNum][i].worldY = gp.tileSize * 26;
		i++;
		gp.obj[mapNum][i] = new OBJ_Gate_Left(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 23;
		gp.obj[mapNum][i].worldY = gp.tileSize * 33;
		i++;
		gp.obj[mapNum][i] = new OBJ_Gate_Right(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 25;
		gp.obj[mapNum][i].worldY = gp.tileSize * 33;
		i++;
		
	}
	
	public void setNPC() {
		int mapNum = 0;
		gp.npc[mapNum][0] = new NPC_Girl(gp);
		gp.npc[mapNum][0].worldX = gp.tileSize * 25;
		gp.npc[mapNum][0].worldY = gp.tileSize * 24;

		if (gp.setMonster3 == true) {
			gp.npc[mapNum][0].worldX = gp.tileSize * 20;
			gp.npc[mapNum][0].worldY = gp.tileSize * 21;
		}

		if (gp.setMonster5 == true) {
			gp.npc[mapNum][0].worldX = gp.tileSize * 25;
			gp.npc[mapNum][0].worldY = gp.tileSize * 36;
		}
		mapNum = 1;
		gp.npc[mapNum][0] = new NPC_Girl(gp);
		gp.npc[mapNum][0].worldX = gp.tileSize * 28;
		gp.npc[mapNum][0].worldY = gp.tileSize * 24;

		mapNum = 2;
		gp.npc[mapNum][0] = new NPC_Girl(gp);
		gp.npc[mapNum][0].worldX = gp.tileSize * 15;
		gp.npc[mapNum][0].worldY = gp.tileSize * 11;
		gp.npc[mapNum][0].speed = 0;

	}
	
	public void setMonster1() {
		int mapNum = 0;

		// Spawn first wave
		gp.monster[mapNum][0] = new MON_Bat(gp);
		gp.monster[mapNum][0].worldX = gp.tileSize * 23;
		gp.monster[mapNum][0].worldY = gp.tileSize * 10;

		gp.monster[mapNum][1] = new MON_Bat(gp);
		gp.monster[mapNum][1].worldX = gp.tileSize * 23;
		gp.monster[mapNum][1].worldY = gp.tileSize * 21;
		
		gp.monster[mapNum][2] = new MON_Bat(gp);
		gp.monster[mapNum][2].worldX = gp.tileSize * 39;
		gp.monster[mapNum][2].worldY = gp.tileSize * 21;

		gp.monster[mapNum][3] = new MON_Bat(gp);
		gp.monster[mapNum][3].worldX = gp.tileSize * 37;
		gp.monster[mapNum][3].worldY = gp.tileSize * 10;

		new Thread(() -> {
			try {
				Thread.sleep(15000); // Pause for 15 seconds
			}
            catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Spawn second wave
			if (gp.setMonster1 == true) {
				gp.monster[mapNum][4] = new MON_Bat(gp);
				gp.monster[mapNum][4].worldX = gp.tileSize * 23;
				gp.monster[mapNum][4].worldY = gp.tileSize * 10;
				
				gp.monster[mapNum][5] = new MON_Bat(gp);
				gp.monster[mapNum][5].worldX = gp.tileSize * 23;
				gp.monster[mapNum][5].worldY = gp.tileSize * 21;
	
				gp.monster[mapNum][6] = new MON_Bat(gp);
				gp.monster[mapNum][6].worldX = gp.tileSize * 39;
				gp.monster[mapNum][6].worldY = gp.tileSize * 21;
	
				gp.monster[mapNum][7] = new MON_Bat(gp);
				gp.monster[mapNum][7].worldX = gp.tileSize * 37;
				gp.monster[mapNum][7].worldY = gp.tileSize * 10;
			}

		}).start();

		new Thread(() -> {
			try {
				Thread.sleep(30000); // Pause for 30 seconds
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Spawn third wave
			if (gp.setMonster1 == true) {
				gp.monster[mapNum][8] = new MON_Bat(gp);
				gp.monster[mapNum][8].worldX = gp.tileSize * 23;
				gp.monster[mapNum][8].worldY = gp.tileSize * 10;
	
				gp.monster[mapNum][9] = new MON_Bat(gp);
				gp.monster[mapNum][9].worldX = gp.tileSize * 23;
				gp.monster[mapNum][9].worldY = gp.tileSize * 21;
	
				gp.monster[mapNum][10] = new MON_Bat(gp);
				gp.monster[mapNum][10].worldX = gp.tileSize * 39;
				gp.monster[mapNum][10].worldY = gp.tileSize * 21;
	
				gp.monster[mapNum][11] = new MON_Bat(gp);
				gp.monster[mapNum][11].worldX = gp.tileSize * 37;
				gp.monster[mapNum][11].worldY = gp.tileSize * 10;
			}
		}).start();
	}

	public void setMonster2() {
		int mapNum = 0;

		// Spawn first wave
		gp.monster[mapNum][0] = new MON_Skeleton(gp);
		gp.monster[mapNum][0].worldX = gp.tileSize * 10;
		gp.monster[mapNum][0].worldY = gp.tileSize * 11;

		gp.monster[mapNum][1] = new MON_Skeleton(gp);
		gp.monster[mapNum][1].worldX = gp.tileSize * 18;
		gp.monster[mapNum][1].worldY = gp.tileSize * 11;
		
		gp.monster[mapNum][2] = new MON_Skeleton(gp);
		gp.monster[mapNum][2].worldX = gp.tileSize * 10;
		gp.monster[mapNum][2].worldY = gp.tileSize * 21;
		
		new Thread(() -> {
			try {
				Thread.sleep(20000); // Pause for 20 seconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Spawn second wave
			if (gp.setMonster2) {
				gp.monster[mapNum][3] = new MON_Skeleton(gp);
				gp.monster[mapNum][3].worldX = gp.tileSize * 10;
				gp.monster[mapNum][3].worldY = gp.tileSize * 11;
		
				gp.monster[mapNum][4] = new MON_Skeleton(gp);
				gp.monster[mapNum][4].worldX = gp.tileSize * 18;
				gp.monster[mapNum][4].worldY = gp.tileSize * 11;
				
				gp.monster[mapNum][5] = new MON_Skeleton(gp);
				gp.monster[mapNum][5].worldX = gp.tileSize * 10;
				gp.monster[mapNum][5].worldY = gp.tileSize * 21;
			}
		}).start();

		new Thread(() -> {
			try {
				Thread.sleep(40000); // Pause for 40 seconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Spawn third wave
			if (gp.setMonster2 == true) {
				gp.monster[mapNum][6] = new MON_Skeleton(gp);
				gp.monster[mapNum][6].worldX = gp.tileSize * 10;
				gp.monster[mapNum][6].worldY = gp.tileSize * 11;
		
				gp.monster[mapNum][7] = new MON_Skeleton(gp);
				gp.monster[mapNum][7].worldX = gp.tileSize * 18;
				gp.monster[mapNum][7].worldY = gp.tileSize * 11;
				
				gp.monster[mapNum][8] = new MON_Skeleton(gp);
				gp.monster[mapNum][8].worldX = gp.tileSize * 10;
				gp.monster[mapNum][8].worldY = gp.tileSize * 21;
			}
		}).start();
	}

	public void setMonster3() {
		int mapNum = 0;

		// Spawn first wave
		gp.monster[mapNum][0] = new MON_Witch(gp);
		gp.monster[mapNum][0].worldX = gp.tileSize * 10;
		gp.monster[mapNum][0].worldY = gp.tileSize * 30;

		gp.monster[mapNum][1] = new MON_Witch(gp);
		gp.monster[mapNum][1].worldX = gp.tileSize * 10;
		gp.monster[mapNum][1].worldY = gp.tileSize * 39;
		
		gp.monster[mapNum][2] = new MON_Witch(gp);
		gp.monster[mapNum][2].worldX = gp.tileSize * 18;
		gp.monster[mapNum][2].worldY = gp.tileSize * 39;

		gp.monster[mapNum][3] = new MON_Witch(gp);
		gp.monster[mapNum][3].worldX = gp.tileSize * 26;
		gp.monster[mapNum][3].worldY = gp.tileSize * 30;

		gp.monster[mapNum][4] = new MON_Witch(gp);
		gp.monster[mapNum][4].worldX = gp.tileSize * 26;
		gp.monster[mapNum][4].worldY = gp.tileSize * 39;
		
		new Thread(() -> {
			try {
				Thread.sleep(20000); // Pause for 20 seconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Spawn second wave
			if (gp.setMonster3) {
				gp.monster[mapNum][5] = new MON_Witch(gp);
				gp.monster[mapNum][5].worldX = gp.tileSize * 10;
				gp.monster[mapNum][5].worldY = gp.tileSize * 30;
		
				gp.monster[mapNum][6] = new MON_Witch(gp);
				gp.monster[mapNum][6].worldX = gp.tileSize * 10;
				gp.monster[mapNum][6].worldY = gp.tileSize * 39;
				
				gp.monster[mapNum][7] = new MON_Witch(gp);
				gp.monster[mapNum][7].worldX = gp.tileSize * 18;
				gp.monster[mapNum][7].worldY = gp.tileSize * 39;
		
				gp.monster[mapNum][8] = new MON_Witch(gp);
				gp.monster[mapNum][8].worldX = gp.tileSize * 26;
				gp.monster[mapNum][8].worldY = gp.tileSize * 30;
		
				gp.monster[mapNum][9] = new MON_Witch(gp);
				gp.monster[mapNum][9].worldX = gp.tileSize * 26;
				gp.monster[mapNum][9].worldY = gp.tileSize * 39;
			}
		}).start();

		new Thread(() -> {
			try {
				Thread.sleep(40000); // Pause for 40 seconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Spawn third wave
			if (gp.setMonster3 == true) {
				gp.monster[mapNum][10] = new MON_Witch(gp);
				gp.monster[mapNum][10].worldX = gp.tileSize * 10;
				gp.monster[mapNum][10].worldY = gp.tileSize * 30;
		
				gp.monster[mapNum][11] = new MON_Witch(gp);
				gp.monster[mapNum][11].worldX = gp.tileSize * 10;
				gp.monster[mapNum][11].worldY = gp.tileSize * 39;
				
				gp.monster[mapNum][12] = new MON_Witch(gp);
				gp.monster[mapNum][12].worldX = gp.tileSize * 18;
				gp.monster[mapNum][12].worldY = gp.tileSize * 39;
		
				gp.monster[mapNum][13] = new MON_Witch(gp);
				gp.monster[mapNum][13].worldX = gp.tileSize * 26;
				gp.monster[mapNum][13].worldY = gp.tileSize * 30;
		
				gp.monster[mapNum][14] = new MON_Witch(gp);
				gp.monster[mapNum][14].worldX = gp.tileSize * 26;
				gp.monster[mapNum][14].worldY = gp.tileSize * 39;
			}
		}).start();
	}

	public void setMonster4() {
		int mapNum = 0;

		// Spawn first wave
		gp.monster[mapNum][0] = new MON_Bat(gp);
		gp.monster[mapNum][0].worldX = gp.tileSize * 37;
		gp.monster[mapNum][0].worldY = gp.tileSize * 31;

		gp.monster[mapNum][1] = new MON_Bat(gp);
		gp.monster[mapNum][1].worldX = gp.tileSize * 37;
		gp.monster[mapNum][1].worldY = gp.tileSize * 32;
		
		gp.monster[mapNum][2] = new MON_Skeleton(gp);
		gp.monster[mapNum][2].worldX = gp.tileSize * 38;
		gp.monster[mapNum][2].worldY = gp.tileSize * 31;

		gp.monster[mapNum][3] = new MON_Skeleton(gp);
		gp.monster[mapNum][3].worldX = gp.tileSize * 38;
		gp.monster[mapNum][3].worldY = gp.tileSize * 32;

	}

	public void setMonster5() {
		int mapNum = 0;

		// Spawn first wave
		gp.monster[mapNum][0] = new MON_Witch(gp);
		gp.monster[mapNum][0].worldX = gp.tileSize * 39;
		gp.monster[mapNum][0].worldY = gp.tileSize * 34;

		gp.monster[mapNum][1] = new MON_Skeleton(gp);
		gp.monster[mapNum][1].worldX = gp.tileSize * 39;
		gp.monster[mapNum][1].worldY = gp.tileSize * 35;
		
		gp.monster[mapNum][2] = new MON_Bat(gp);
		gp.monster[mapNum][2].worldX = gp.tileSize * 39;
		gp.monster[mapNum][2].worldY = gp.tileSize * 36;

		gp.monster[mapNum][3] = new MON_Bat(gp);
		gp.monster[mapNum][3].worldX = gp.tileSize * 39;
		gp.monster[mapNum][3].worldY = gp.tileSize * 37;

		gp.monster[mapNum][4] = new MON_Skeleton(gp);
		gp.monster[mapNum][4].worldX = gp.tileSize * 39;
		gp.monster[mapNum][4].worldY = gp.tileSize * 38;

		gp.monster[mapNum][5] = new MON_Witch(gp);
		gp.monster[mapNum][5].worldX = gp.tileSize * 39;
		gp.monster[mapNum][5].worldY = gp.tileSize * 39;
		
		new Thread(() -> {
			try {
				Thread.sleep(25000); // Pause for 25 seconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Spawn second wave
			if (gp.setMonster5) {
				gp.monster[mapNum][6] = new MON_Witch(gp);
				gp.monster[mapNum][6].worldX = gp.tileSize * 39;
				gp.monster[mapNum][6].worldY = gp.tileSize * 34;
		
				gp.monster[mapNum][7] = new MON_Skeleton(gp);
				gp.monster[mapNum][7].worldX = gp.tileSize * 39;
				gp.monster[mapNum][7].worldY = gp.tileSize * 35;
				
				gp.monster[mapNum][8] = new MON_Bat(gp);
				gp.monster[mapNum][8].worldX = gp.tileSize * 39;
				gp.monster[mapNum][8].worldY = gp.tileSize * 36;
		
				gp.monster[mapNum][9] = new MON_Bat(gp);
				gp.monster[mapNum][9].worldX = gp.tileSize * 39;
				gp.monster[mapNum][9].worldY = gp.tileSize * 37;
		
				gp.monster[mapNum][10] = new MON_Skeleton(gp);
				gp.monster[mapNum][10].worldX = gp.tileSize * 39;
				gp.monster[mapNum][10].worldY = gp.tileSize * 38;
		
				gp.monster[mapNum][11] = new MON_Witch(gp);
				gp.monster[mapNum][11].worldX = gp.tileSize * 39;
				gp.monster[mapNum][11].worldY = gp.tileSize * 39;
			}
		}).start();

		new Thread(() -> {
			try {
				Thread.sleep(50000); // Pause for 50 seconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Spawn third wave
			if (gp.setMonster5) {
				gp.monster[mapNum][12] = new MON_Witch(gp);
				gp.monster[mapNum][12].worldX = gp.tileSize * 39;
				gp.monster[mapNum][12].worldY = gp.tileSize * 34;
		
				gp.monster[mapNum][13] = new MON_Skeleton(gp);
				gp.monster[mapNum][13].worldX = gp.tileSize * 39;
				gp.monster[mapNum][13].worldY = gp.tileSize * 35;
				
				gp.monster[mapNum][14] = new MON_Bat(gp);
				gp.monster[mapNum][14].worldX = gp.tileSize * 39;
				gp.monster[mapNum][14].worldY = gp.tileSize * 36;
		
				gp.monster[mapNum][15] = new MON_Bat(gp);
				gp.monster[mapNum][15].worldX = gp.tileSize * 39;
				gp.monster[mapNum][15].worldY = gp.tileSize * 37;
		
				gp.monster[mapNum][16] = new MON_Skeleton(gp);
				gp.monster[mapNum][16].worldX = gp.tileSize * 39;
				gp.monster[mapNum][16].worldY = gp.tileSize * 38;
		
				gp.monster[mapNum][17] = new MON_Witch(gp);
				gp.monster[mapNum][17].worldX = gp.tileSize * 39;
				gp.monster[mapNum][17].worldY = gp.tileSize * 39;
			}
		}).start();
	}

	public void setBoss() {
		int mapNum = 2;
		gp.monster[mapNum][0] = new MON_Goblin(gp);
		gp.monster[mapNum][0].worldX = gp.tileSize * 39;
		gp.monster[mapNum][0].worldY = gp.tileSize * 27;
	}

	public void clearMonster() {
		for (int i = 0; i < gp.maxMap; ++i) {
			for (int j = 0; j < 20; ++j) {
				gp.monster[i][j]  = null;
			}
		}
		gp.setMonster1 = false;
		gp.setMonster2 = false;
		gp.setMonster3 = false;
		gp.setMonster4 = false;
		gp.setMonster5 = false;
	}
}

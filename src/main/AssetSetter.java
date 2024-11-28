package main;

import entity.NPC_OldMan;
import entity.NPC_Girl;
import monster.MON_GreenSlime;
import object.OBJ_Axe;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Coin_Bronze;
import object.OBJ_Door;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import tile_interactive.IT_DryTree;

public class AssetSetter {
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		int mapNum = 0;
		int i = 0;
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*35;
		gp.obj[mapNum][i].worldY = gp.tileSize*24;
		i++;
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*33;
		gp.obj[mapNum][i].worldY = gp.tileSize*24;
		i++;
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*31;
		gp.obj[mapNum][i].worldY = gp.tileSize*24;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*28;
		gp.obj[mapNum][i].worldY = gp.tileSize*27;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*29;
		gp.obj[mapNum][i].worldY = gp.tileSize*27;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot();
		gp.obj[mapNum][i].worldX = gp.tileSize*38;
		gp.obj[mapNum][i].worldY = gp.tileSize*32;
		i++;
		// Map 2
		mapNum = 1;
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*25;
		gp.obj[mapNum][i].worldY = gp.tileSize*19;
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
	
	public void setMonster() {
		
		int mapNum = 0;
		int i = 0;
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 29;
		gp.monster[mapNum][i].worldY = gp.tileSize * 17;
		i++;
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 30;
		gp.monster[mapNum][i].worldY = gp.tileSize * 17;
		i++;
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 32;
		gp.monster[mapNum][i].worldY = gp.tileSize * 17;
		i++;
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 34;
		gp.monster[mapNum][i].worldY = gp.tileSize * 17;
		/*
		i++;
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 38;
		gp.monster[mapNum][i].worldY = gp.tileSize * 42;
		i++;
		mapNum = 2;
		*/
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

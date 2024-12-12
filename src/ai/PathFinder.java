package ai;

import java.util.ArrayList;
import main.GamePanel;

public class PathFinder {
    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    public PathFinder(GamePanel gp) {
        this.gp = gp;
        instantiateNodes();
    }

    public void instantiateNodes() {
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];

        for (int i = 0; i < gp.maxWorldRow; ++i) {
            for (int j = 0; j < gp.maxWorldCol; ++j) {
                node[j][i] = new Node(j, i);
            }
        }
    }

    public void resetNodes() {
        for (int i = 0; i < gp.maxWorldRow; ++i) {
            for (int j = 0; j < gp.maxWorldCol; ++j) {
                node[j][i].open = false;
                node[j][i].checked = false;
                node[j][i].solid = false;
            }
        }
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
        resetNodes();
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        // Check Interactive Tiles
        for (int k = 0; k < gp.iTile[1].length; ++k) {
            if (gp.iTile[gp.currentMap][k] != null && gp.iTile[gp.currentMap][k].destructible == true) {
                int itCol = gp.iTile[gp.currentMap][k].worldX / gp.tileSize;
                int itRow = gp.iTile[gp.currentMap][k].worldY / gp.tileSize;
                node[itCol][itRow].solid = true;
            }
        }

        for (int i = 0; i < gp.maxWorldRow; ++i) {
            for (int j = 0; j < gp.maxWorldCol; ++j) {
                // Check Tiles
                int tileNum = gp.tileM.mapTileNum[gp.currentMap][j][i];

                if (gp.tileM.tile[tileNum].collision == true) {
                    node[j][i].solid = true;
                }

                // Set Cost
                getCost(node[j][i]);
            }
        }
    }

    public void getCost(Node node) {
        // G Cost
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        // H Cost
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        // F Cost
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search() {
        while (goalReached == false && step < 300) {
            int col = currentNode.col;
            int row = currentNode.row;

            // Check the current node
            currentNode.checked = true;
            openList.remove(currentNode);

            // Open the Up node
            if (row - 1 >= 0) {
                openNode(node[col][row - 1]);
            }

            // open the Left node
            if (col - 1 >= 0) {
                openNode(node[col - 1][row]);
            }

            // Open the Down node
            if (row + 1 < gp.maxWorldRow) {
                openNode(node[col][row + 1]);
            }

            // Open the Right node
            if (col + 1 < gp.maxWorldCol) {
                openNode(node[col + 1][row]);
            }

            // Find the best node
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < openList.size(); ++i) {
                // Check if this node's fcost is better
                if (openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }

                // If fcost is equal, check the g cost;
                else if (openList.get(i).fCost == bestNodefCost) {
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }

            // If there is no node in the openList, end the loop
            if (openList.size() == 0) {
                break;
            }

            // After the loop, openList[bestNodeIndex] is the next step (= currentNode)
            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
            step++;
        }
        return goalReached;
    }

    public void openNode(Node node) {
        if (node.open == false && node.checked == false && node.solid == false) {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    public void trackThePath() {
        Node current = goalNode;

        while (current != startNode) {
            pathList.add(0, current);
            current = current.parent;
        }
    }
}
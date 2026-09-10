package com.coloradocollege.cp122homework.project.map;

import com.coloradocollege.cp122homework.project.map.node.*;

import java.io.*;

import java.util.*;

public class Map {
    private MapNode[] nodes;
    private int currentNodeIndex;
    private String mapFileName;
    public Map() {
        nodes = new MapNode[7];
        currentNodeIndex = 0;
        mapFileName = "/Users/apple/IdeaProjects/CP122/src/com/coloradocollege/cp122homework/project/dungeonMap.txt";
    }
    public void generateMap() throws IOException {
        nodes[0] = new MonsterNode(0);
        nodes[1] = new MonsterNode(1);
        nodes[2] = new EventNode(2);
        nodes[3] = new MonsterNode(3);
        nodes[4] = new ShopNode(4);
        nodes[5] = new CampfireNode(5);
        nodes[6] = new BossNode(6);
        saveMap();
    }
    public boolean travelToNextNode() {
        if (hasNextNode()) {
            currentNodeIndex++;
            return true;
        } else {
            return false;
        }
    }
    public MapNode getCurrentNode() {
        return nodes[currentNodeIndex];
    }
    public void markCurrentNodeVisited() {
        nodes[currentNodeIndex].markVisited();
    }
    public void saveMap() throws IOException{
        File file = new File(mapFileName);
        file.createNewFile();
        PrintStream out = new PrintStream(file);
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].isVisited()) {
                out.print(" --> [X]");
            } else {
                out.print(" --> " + nodes[i]);
            }
        }
        out.println();
        out.close();
    }
    public void updateMap() throws IOException {
        File oldFile = new File(mapFileName);
        oldFile.delete();
        saveMap();
    }
    public Scanner loadMap() throws FileNotFoundException {
        File file = new File(mapFileName);
        Scanner fileScanner = new Scanner(file);
        return fileScanner;
    }
    public void displayMap() throws FileNotFoundException {
        Scanner fileScanner = loadMap();
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            System.out.println(line);
        }
    }
    public boolean hasNextNode() {
        return currentNodeIndex < nodes.length - 1;
    }
    public boolean isComplete() {
        return currentNodeIndex == nodes.length - 1;
    }
}

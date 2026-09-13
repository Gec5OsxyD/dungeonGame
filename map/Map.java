package com.coloradocollege.cp122homework.project.map;

import com.coloradocollege.cp122homework.project.map.node.*;
import com.coloradocollege.cp122homework.project.entity.Boss;
import com.coloradocollege.cp122homework.project.entity.Monster;

import java.io.*;

import java.util.*;

public class Map {
    private MapNode[] nodes;
    private int currentNodeIndex;
    private String mapFileName;
    private Scanner console;
    private Random random;

    public Map() {
        this(new Scanner(System.in));
    }
    public Map(Scanner console) {
        nodes = new MapNode[7];
        currentNodeIndex = 0;
        mapFileName = "dungeonMap.txt";
        this.console = console;
        random = new Random();
    }
    public void setConsole(Scanner console) {
        this.console = console;
    }
    public void generateMap() throws IOException {
        nodes[0] = new MonsterNode(0,
                new Monster("Slime", "Slime", "monster1", 12, 4, 0), console, random);
        nodes[1] = new MonsterNode(1,
                new Monster("Goblin", "Goblin", "monster2", 16, 5, 1), console, random);
        nodes[2] = new EventNode(2, console, random);
        nodes[3] = new MonsterNode(3, new Monster("Ogre", "Ogre", "monster3", 20, 6, 1), console, random);
        nodes[4] = new ShopNode(4, console);
        nodes[5] = new CampfireNode(5, console);
        nodes[6] = new BossNode(6, new Boss("Guardian", "boss", 28, 7, 2), console, random);
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
        fileScanner.close();
    }
    public boolean hasNextNode() {
        return currentNodeIndex < nodes.length - 1;
    }
    public boolean isComplete() {
        return currentNodeIndex == nodes.length - 1;
    }
}

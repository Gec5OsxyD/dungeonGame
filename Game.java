package com.coloradocollege.cp122homework.project;

import java.io.IOException;
import java.util.Scanner;

import com.coloradocollege.cp122homework.project.entity.*;
import com.coloradocollege.cp122homework.project.map.*;


public class Game {
    private Player player;
    private Map map;
    private boolean gameOver;
    private boolean victory;
    private Scanner console;

    public Game(Player player, Map map) {
        this(player, map, new Scanner(System.in));
    }

    public Game(Player player, Map map, Scanner console) {
        this.player = player;
        this.map = map;
        this.gameOver = false;
        this.victory = false;
        this.console = console;
        this.map.setConsole(console);
    }

    public Game(Scanner console) {
        this(null, new Map(console), console);
    }

    public void start() {
        System.out.println("You walked into a dungeon, holding a sword/bow/staff.");
        System.out.println("Choose your adventurer:");
        System.out.println("1. Warrior  2. Archer  3. Mage");
        player = createPlayer(readChoice(1, 3));
        try {
            map.generateMap();
            System.out.println("\nA map appears in your hand:");
            map.displayMap();
            System.out.println("Enter anything to continue...");
            console.nextLine();
        } catch (IOException exception) {
            throw new IllegalStateException("The dungeon map could not be created.", exception);
        }
    }
    public Player createPlayer(int choice) {
        switch (choice) {
            case 1:
                System.out.println("You chose to be a warrior.");
                return new Warrior();
            case 2:
                System.out.println("You chose to be an archer.");
                return new Archer();
            case 3:
                System.out.println("You chose to be a mage.");
                return new Mage();
            default:
                System.out.println("I don't know what you did.");
                System.out.println("You are now a warrior anyway.");
                return new Warrior();
        }
    }
    public void run() throws IOException {
        while (!(gameOver || victory)) {
            processCurrentNode();
            map.markCurrentNodeVisited();
            map.updateMap();
            map.displayMap();
            System.out.println("Enter anything to continue...");
            console.nextLine();
            if (!player.isAlive()) {
                gameOver = true;
            } else if (map.isComplete()) {
                victory = true;
            } else {
                moveToNextNode();
            }
        }
        if (victory) {
            victory();
        } else if (gameOver) {
            gameOver();
        } else {
            System.out.println("Something went wrong.");
        }
    }
    public void processCurrentNode() {
        map.getCurrentNode().enter(player);
    }
    public void moveToNextNode() {
        map.travelToNextNode();
    }
    public void displayUpdatedMap() throws IOException {
        map.markCurrentNodeVisited();
        map.updateMap();
        map.displayMap();
    }
    public void gameOver() {
        System.out.println("Game over.");
        System.out.println("Thanks for playing.");
    }
    public void victory() {
        System.out.println("====================");
        System.out.println("      YOU WIN!");
        System.out.println("====================");
        System.out.println("Thanks for playing.");
    }
    public int readChoice(int minimum, int maximum) {
        while (true) {
            if (!console.hasNextLine()) {
                return minimum;
            }
            try {
                int choice = Integer.parseInt(console.nextLine().trim());
                if (choice >= minimum && choice <= maximum) {
                    return choice;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
            System.out.println("Enter a number from " + minimum + " to " + maximum + ".");
        }
    }
}

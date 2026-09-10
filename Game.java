package com.coloradocollege.cp122homework.project;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.InputMismatchException;

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
        this.player = player;
        this.map = map;
        this.gameOver = false;
        this.victory = false;
        console = new Scanner(System.in);
    }
    public void start() {
        console = new Scanner(System.in);
        System.out.println("You walked into a dungeon, holding a sword/bow/wand?");
        System.out.println("(press 1 to be a warrior, 2 to be a ranger, or 3 to be a mage)");
        try {
            int choice = console.nextInt();
            console.nextLine();
            player = createPlayer(choice);
            try {
                map.generateMap();
                System.out.println("A map appears in your hand.");
                map.displayMap();
                System.out.println("Enter anything to continue");
                console.nextLine();
            } catch (IOException e) {
                System.err.println("File IO err.");
                //e.printStackTrace();
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number.");
            start();
        }
    }
    public Player createPlayer(int choice) {
        switch (choice) {
            case 1:
                System.out.println("You chose to be a warrior.");
                return new Warrior();
            case 2:
                System.out.println("You chose to be a ranger.");
                return new Ranger();

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
        while (! (gameOver || victory)) {
            //processCurrentNode();
            displayUpdatedMap();
            moveToNextNode();
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
        System.out.println("Enter anything to continue");
        console.nextLine();
    }
    public void gameOver() {
        System.out.println("Game over.");
        System.out.println("Thanks for playing.");
    }
    public void victory() {
        System.out.println("You won!");
        System.out.println("Thanks for playing.");
    }
}

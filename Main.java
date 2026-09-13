package com.coloradocollege.cp122homework.project;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(new Scanner(System.in));
        game.start();
        try {
            game.run();
        } catch (java.io.IOException e) {
            System.err.println("The map could not be updated");
        }
    }
}

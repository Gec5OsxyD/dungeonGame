package com.coloradocollege.cp122homework.project;

import java.io.*;

import java.util.Scanner;

import com.coloradocollege.cp122homework.project.entity.*;

import com.coloradocollege.cp122homework.project.map.*;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(new Player(), new Map());
        game.start();
        try {
            game.run();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
}

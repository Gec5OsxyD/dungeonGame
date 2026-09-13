package com.coloradocollege.cp122homework.project.event;

import java.util.Scanner;

import com.coloradocollege.cp122homework.project.entity.Player;
import com.coloradocollege.cp122homework.project.item.Weapon;

/** Lets the player choose a +2 weapon or 20 gold. */
public class Treasure extends Event {
    public Treasure(Scanner console) {
        super(console);
    }

    public void execute(Player player) {
        System.out.println("You find a treasure chest.");
        System.out.println("1. Take a +2 weapon");
        System.out.println("2. Take 20 gold");
        int choice = readChoice();
        if (choice == 1) {
            player.receiveItem(new Weapon("Treasure Weapon", 2, 0, "Basic"), console);
        } else {
            player.addGold(20);
            System.out.println("You received 20 gold.");
        }
    }
    public int readChoice() {
        while (true) {
            if (!console.hasNextLine()) {
                return 2;
            }
            try {
                int choice = Integer.parseInt(console.nextLine().trim());
                if (choice == 1 || choice == 2) {
                    return choice;
                }
            } catch (NumberFormatException ignored) {
                // Print one common message below.
            }
            System.out.println("Enter 1 or 2.");
        }
    }
}

package com.coloradocollege.cp122homework.project.map.node;

import com.coloradocollege.cp122homework.project.entity.*;

import java.util.Scanner;

public class CampfireNode extends MapNode {
    private Scanner console;

    public CampfireNode(int nodeId, Scanner console) {
        super(nodeId);
        this.console = console;
    }
    public void enter(Player player) {
        System.out.println("\nAt the campfire:");
        if (player.getEquipment().getWeapon() == null) {
            rest(player);
            return;
        }
        System.out.println("1. Rest (restore all HP)");
        System.out.println("2. Forge (+1 weapon Attack)");
        int choice = readChoice();
        if (choice == 1) {
            rest(player);
        } else {
            forge(player);
        }
    }
    public void rest(Player player) {
        player.healToFull();
        System.out.println("You rest and recover to full HP.");
    }
    public void forge(Player player) {
        player.getEquipment().getWeapon().increaseAttackBonus(1);
        System.out.println("Your weapon now grants +"
                + player.getEquipment().getWeapon().getAttackBonus() + " Attack.");
    }
    public int readChoice() {
        while (true) {
            if (!console.hasNextLine()) {
                return 1;
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
    public String toString() {
        return "[Campfire]";
    }
}

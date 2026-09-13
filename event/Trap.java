package com.coloradocollege.cp122homework.project.event;

import java.util.Scanner;

import com.coloradocollege.cp122homework.project.entity.Player;

/** Deals 10 unavoidable damage and awards 15 gold. */
public class Trap extends Event {
    public Trap(Scanner console) {
        super(console);
    }
    public void execute(Player player) {
        player.takeRawDamage(10);
        player.addGold(15);
        System.out.println("A trap hits you for 10 damage, but you find 15 gold.");
        System.out.println("HP: " + player.getHp() + "/" + player.getMaxHp());
    }
}

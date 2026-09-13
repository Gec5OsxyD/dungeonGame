package com.coloradocollege.cp122homework.project.loot;

import java.util.Random;
import java.util.Scanner;

import com.coloradocollege.cp122homework.project.entity.Boss;
import com.coloradocollege.cp122homework.project.entity.Enemy;
import com.coloradocollege.cp122homework.project.entity.Monster;
import com.coloradocollege.cp122homework.project.entity.Player;
import com.coloradocollege.cp122homework.project.item.Armor;
import com.coloradocollege.cp122homework.project.item.AttackPotion;
import com.coloradocollege.cp122homework.project.item.HealthPotion;
import com.coloradocollege.cp122homework.project.item.Item;
import com.coloradocollege.cp122homework.project.item.Weapon;

/** Implements the demo's three monster loot tables and Boss reward. */
public class LootGenerator {
    private Random random;

    public LootGenerator(Random random) {
        this.random = random;
    }
    public void award(Player player, Enemy enemy, Scanner console) {
        if (enemy instanceof Boss) {
            player.addGold(50);
            Item rare = random.nextBoolean()
                    ? new Weapon("Rare Weapon", 2, 0, "Rare")
                    : new Armor("Rare Armor", 2, 0, "Rare");
            System.out.println("Boss loot: 50 gold, " + rare.getName() + ", and a Large Health Potion.");
            player.receiveItem(rare, console);
            player.receiveItem(new HealthPotion(30), console);
            return;
        }
        Monster monster = (Monster) enemy;
        String table = monster.getLootTableId();
        if ("monster1".equals(table)) {
            awardGold(player, 15, 25);
            if (chance(20)) {
                receive(player, new HealthPotion(15), console);
            }
        } else if ("monster2".equals(table)) {
            awardGold(player, 20, 30);
            if (chance(15)) {
                receive(player, random.nextBoolean() ? new HealthPotion(15) : new AttackPotion(), console);
            }
        } else {
            awardGold(player, 25, 35);
            if (chance(20)) {
                Item equipment = random.nextBoolean()
                        ? new Weapon("Looted Weapon", 1)
                        : new Armor("Looted Armor", 1);
                receive(player, equipment, console);
            }
        }
    }
    public void awardGold(Player player, int minimum, int maximum) {
        int gold = minimum + random.nextInt(maximum - minimum + 1);
        player.addGold(gold);
        System.out.println("Loot: " + gold + " gold.");
    }
    public void receive(Player player, Item item, Scanner console) {
        System.out.println("Extra drop: " + item.getName());
        player.receiveItem(item, console);
    }
    public boolean chance(int percent) {
        return random.nextInt(100) < percent;
    }
}

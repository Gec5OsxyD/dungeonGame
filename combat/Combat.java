package com.coloradocollege.cp122homework.project.combat;

import java.util.List;
import java.util.Scanner;

import com.coloradocollege.cp122homework.project.action.Action;
import com.coloradocollege.cp122homework.project.action.Attack;
import com.coloradocollege.cp122homework.project.action.Buff;
import com.coloradocollege.cp122homework.project.action.Debuff;
import com.coloradocollege.cp122homework.project.action.Defend;
import com.coloradocollege.cp122homework.project.entity.Enemy;
import com.coloradocollege.cp122homework.project.entity.Player;
import com.coloradocollege.cp122homework.project.item.Consumable;

/** Runs the 3 AP turn loop while keeping enemy intentions visible first. */
public class Combat {
    private int apPerTurn = 3;
    private Scanner console;

    public Combat(Scanner console) {
        this.console = console;
    }
    public boolean run(Player player, Enemy enemy) {
        player.beginBattle();
        System.out.println("\nA " + enemy.getName() + " blocks your path!");
        while (player.isAlive() && enemy.isAlive()) {
            EnemyIntention intention = enemy.revealIntention(player);
            displayIntention(enemy, intention);
            runPlayerTurn(player, enemy);
            enemy.clearBlock();

            if (!enemy.isAlive()) {
                break;
            }

            System.out.println("\n=== ENEMY TURN ===");
            System.out.println(enemy.getName() + " uses " + intention.getAction().getName() + ".");
            int hpBefore = player.getHp();
            enemy.executeIntention(player);
            System.out.println("Your HP: " + hpBefore + " -> " + player.getHp());
            player.clearBlock();
            player.updateStatusEffects();
            enemy.updateStatusEffects();
        }
        player.endBattle();
        if (player.isAlive()) {
            System.out.println("You defeated " + enemy.getName() + "!");
        }
        return player.isAlive();
    }
    public void displayIntention(Enemy enemy, EnemyIntention intention) {
        System.out.println("\n=== ENEMY INTENTION ===");
        System.out.println(enemy.getName() + " intends to use " + intention.getAction().getName() + ".");
        System.out.println("Expected value: " + intention.getExpectedValue());
    }
    public void runPlayerTurn(Player player, Enemy enemy) {
        int actionPoints = apPerTurn;
        while (actionPoints > 0 && enemy.isAlive()) {
            List<Action> actions = player.getActions();
            List<Consumable> consumables = player.getInventory().getConsumables();
            System.out.println("\n=== YOUR TURN ===");
            System.out.println("HP: " + player.getHp() + "/" + player.getMaxHp() + " | Block: " + player.getBlock() + " | AP: " + actionPoints + " | Effective Attack: " + player.getEffectiveAttack() + " | Effective Defense: " + player.getEffectiveDefense());
            System.out.println(enemy.getName() + " HP: " + enemy.getHp() + "/" + enemy.getMaxHp());
            for (int i = 0; i < actions.size(); i++) {
                Action action = actions.get(i);
                System.out.println((i + 1) + ". " + action.getName() + " (" + action.getAPCost() + " AP)" + " : " + action.getDescription());
            }
            int consumableChoice = actions.size() + 1;
            int endChoice = actions.size() + 2;
            System.out.println(consumableChoice + ". Use Consumable (0 AP)");
            System.out.println(endChoice + ". End Turn");

            int choice = readChoice(1, endChoice);
            if (choice == endChoice) {
                return;
            }
            if (choice == consumableChoice) {
                useConsumable(player, enemy, consumables);
                continue;
            }

            Action action = actions.get(choice - 1);
            if (action.getAPCost() > actionPoints) {
                System.out.println("You do not have enough AP.");
                continue;
            }
            try {
                executePlayerAction(player, enemy, action);
                actionPoints -= action.getAPCost();
                System.out.println("You used " + action.getName() + ".");
            } catch (IllegalStateException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
    public void executePlayerAction(Player player, Enemy enemy, Action action) {
        if (action instanceof Attack) {
            player.attack(enemy, (Attack) action);
        } else if (action instanceof Defend) {
            player.defend((Defend) action);
        } else if (action instanceof Buff) {
            player.useBuff((Buff) action);
        } else if (action instanceof Debuff) {
            player.useDebuff((Debuff) action, enemy);
        }
    }
    public void useConsumable(Player player, Enemy enemy, List<Consumable> consumables) {
        if (consumables.isEmpty()) {
            System.out.println("You have no consumables.");
            return;
        }
        for (int i = 0; i < consumables.size(); i++) {
            System.out.println((i + 1) + ". " + consumables.get(i).getName());
        }
        int index = readChoice(1, consumables.size()) - 1;
        Consumable item = consumables.get(index);
        if (item.getName().contains("Health")) {
            player.useConsumable(index, player);
        } else {
            player.useConsumable(index, enemy);
        }
        System.out.println("You used " + item.getName() + ".");
    }

    public int readChoice(int minimum, int maximum) {
        while (true) {
            if (!console.hasNextLine()) {
                return maximum;
            }
            String input = console.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                if (choice >= minimum && choice <= maximum) {
                    return choice;
                }
            } catch (NumberFormatException ignored) {
                // Print one common validation message below.
            }
            System.out.println("Enter a number from " + minimum + " to " + maximum + ".");
        }
    }
}

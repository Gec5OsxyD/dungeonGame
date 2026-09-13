package com.coloradocollege.cp122homework.project.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.coloradocollege.cp122homework.project.entity.Player;
import com.coloradocollege.cp122homework.project.item.Armor;
import com.coloradocollege.cp122homework.project.item.AttackPotion;
import com.coloradocollege.cp122homework.project.item.HealthPotion;
import com.coloradocollege.cp122homework.project.item.Item;
import com.coloradocollege.cp122homework.project.item.Weapon;

/** Shared purchasing system for the Shop and wandering merchant. */
public class Shop extends Event {
    private List<Item> availableItems;

    public Shop(Scanner console) {
        super(console);
        availableItems = new ArrayList<Item>();
        availableItems.add(new HealthPotion(15));
        availableItems.add(new AttackPotion());
        availableItems.add(new Weapon("Iron Weapon", 2, 25, "Basic"));
        availableItems.add(new Armor("Iron Armor", 2, 25, "Basic"));
    }
    public void execute(Player player) {
        while (!availableItems.isEmpty()) {
            System.out.println("\nShop Inventory (Gold: " + player.getGold() + ")");
            displayItems();
            System.out.println((availableItems.size() + 1) + ". Leave shop");
            int choice = readChoice(1, availableItems.size() + 1);
            if (choice == availableItems.size() + 1) {
                return;
            }
            purchased(player, choice - 1);
        }
    }
    public void displayItems() {
        for (int i = 0; i < availableItems.size(); i++) {
            Item item = availableItems.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - "
                    + item.getPrice() + " gold (" + item.getDescription() + ")");
        }
    }

    public void addItem(Item item) {
        availableItems.add(item);
    }
    public boolean purchased(Player player, int itemIndex) {
        if (itemIndex < 0 || itemIndex >= availableItems.size()) {
            return false;
        }
        Item item = availableItems.get(itemIndex);
        if (player.getGold() < item.getPrice()) {
            System.out.println("You do not have enough gold.");
            return false;
        }
        if (!player.receiveItem(item, console)) {
            System.out.println("You discarded " + item.getName() + ".");
            return false;
        }
        player.spendGold(item.getPrice());
        availableItems.remove(itemIndex);
        System.out.println("You purchased " + item.getName() + ". Gold: " + player.getGold());
        return true;
    }
    public int readChoice(int minimum, int maximum) {
        while (true) {
            if (!console.hasNextLine()) {
                return maximum;
            }
            try {
                int choice = Integer.parseInt(console.nextLine().trim());
                if (choice >= minimum && choice <= maximum) {
                    return choice;
                }
            } catch (NumberFormatException ignored) {
                // Print one common message below.
            }
            System.out.println("Enter a number from " + minimum + " to " + maximum + ".");
        }
    }
}

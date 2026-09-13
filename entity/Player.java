package com.coloradocollege.cp122homework.project.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.coloradocollege.cp122homework.project.action.Action;
import com.coloradocollege.cp122homework.project.action.Attack;
import com.coloradocollege.cp122homework.project.action.Buff;
import com.coloradocollege.cp122homework.project.action.Debuff;
import com.coloradocollege.cp122homework.project.action.Defend;
import com.coloradocollege.cp122homework.project.inventory.Equipped;
import com.coloradocollege.cp122homework.project.inventory.Inventory;
import com.coloradocollege.cp122homework.project.item.Armor;
import com.coloradocollege.cp122homework.project.item.Consumable;
import com.coloradocollege.cp122homework.project.item.Item;
import com.coloradocollege.cp122homework.project.item.Weapon;

/** Shared player state and behavior for all three character classes. */
public abstract class Player extends Entity {
    private int gold;
    private Inventory inventory;
    private Equipped equipment;
    private List<Action> actions;
    private int buffsUsed;
    private int debuffsUsed;

    protected Player(int maxHp, int baseAttack, int baseDefense) {
        super(maxHp, baseAttack, baseDefense);
        inventory = new Inventory();
        equipment = new Equipped();
        actions = new ArrayList<Action>();
    }
    public abstract void initializeActions();
    public void addAction(Action action) {
        actions.add(action);
    }
    public void giveStarterItems(String weaponName) {
        equipment.equipWeapon(new Weapon(weaponName, 1));
        equipment.equipArmor(new Armor("Starter Armor", 1));
        inventory.addConsumable(new com.coloradocollege.cp122homework.project.item.HealthPotion(15));
        inventory.addConsumable(new com.coloradocollege.cp122homework.project.item.HealthPotion(15));
    }
    public void attack(Entity target, Attack action) {
        action.execute(this, target);
    }
    public void defend(Defend action) {
        action.execute(this, this);
    }
    public void useBuff(Buff action) {
        if (!canUseBuff()) {
            throw new IllegalStateException("The buff limit has been reached.");
        }
        action.execute(this, this);
        buffsUsed++;
    }
    public void useDebuff(Debuff action, Entity target) {
        if (!canUseDebuff()) {
            throw new IllegalStateException("The debuff limit has been reached.");
        }
        action.execute(this, target);
        debuffsUsed++;
    }
    public void useConsumable(int index, Entity target) {
        inventory.useConsumable(index, this, target);
    }
    public void addGold(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Gold amount cannot be negative.");
        }
        gold += amount;
    }
    public boolean spendGold(int amount) {
        if (amount < 0 || amount > gold) {
            return false;
        }
        gold -= amount;
        return true;
    }
    public boolean canUseBuff() {
        return buffsUsed < 2;
    }
    public boolean canUseDebuff() {
        return debuffsUsed < 2;
    }
    public int getBuffsUsed() {
        return buffsUsed;
    }
    public int getDebuffsUsed() {
        return debuffsUsed;
    }
    public void beginBattle() {
        buffsUsed = 0;
        debuffsUsed = 0;
        clearBlock();
        clearStatusEffects();
    }
    public void endBattle() {
        clearBlock();
        clearStatusEffects();
    }
    /** Adds an item and prompts only when an inventory conflict exists. */
    public boolean receiveItem(Item item, Scanner console) {
        if (item instanceof Weapon) {
            Weapon current = equipment.getWeapon();
            if (current == null || chooseReplacement(current.getName(), item.getName(), console)) {
                equipment.equipWeapon((Weapon) item);
                return true;
            }
            return false;
        }
        if (item instanceof Armor) {
            Armor current = equipment.getArmor();
            if (current == null || chooseReplacement(current.getName(), item.getName(), console)) {
                equipment.equipArmor((Armor) item);
                return true;
            }
            return false;
        }
        if (item instanceof Consumable) {
            Consumable consumable = (Consumable) item;
            if (inventory.addConsumable(consumable)) {
                return true;
            }
            System.out.println("Both consumable slots are full.");
            List<Consumable> items = inventory.getConsumables();
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". Replace " + items.get(i).getName());
            }
            System.out.println("3. Discard " + item.getName());
            int choice = readChoice(console, 1, 3);
            if (choice <= inventory.getMaxConsumableSlots()) {
                inventory.replaceConsumable(choice - 1, consumable);
                return true;
            }
        }
        return false;
    }
    public boolean chooseReplacement(String oldName, String newName, Scanner console) {
        System.out.println("Current: " + oldName + " | New: " + newName);
        System.out.println("1. Replace current item");
        System.out.println("2. Discard new item");
        return readChoice(console, 1, 2) == 1;
    }
    public int readChoice(Scanner console, int minimum, int maximum) {
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
                // The message below handles all invalid choices.
            }
            System.out.println("Enter a number from " + minimum + " to " + maximum + ".");
        }
    }
    public List<Action> getActions() {
        return new ArrayList<Action>(actions);
    }
    public int getEffectiveAttack() {
        int bonus = equipment.getWeapon() == null ? 0 : equipment.getWeapon().getAttackBonus();
        return super.getEffectiveAttack() + bonus;
    }
    public int getEffectiveDefense() {
        int bonus = equipment.getArmor() == null ? 0 : equipment.getArmor().getDefenseBonus();
        return super.getEffectiveDefense() + bonus;
    }
    public int getGold() {
        return gold;
    }
    public Inventory getInventory() {
        return inventory;
    }
    public Equipped getEquipment() {
        return equipment;
    }
}

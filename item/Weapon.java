package com.coloradocollege.cp122homework.project.item;

/** Equipment that increases effective Attack. */
public class Weapon extends Equipment {
    private int attackBonus;

    public Weapon(String name, int attackBonus, int price, String rarity) {
        super(name, "+" + attackBonus + " Attack", price, rarity);
        this.attackBonus = attackBonus;
    }
    public Weapon(String name, int attackBonus) {
        this(name, attackBonus, 0, "Basic");
    }
    public int getAttackBonus() {
        return attackBonus;
    }
    public void increaseAttackBonus(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Upgrade amount cannot be negative.");
        }
        attackBonus += amount;
    }
}

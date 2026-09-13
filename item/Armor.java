package com.coloradocollege.cp122homework.project.item;

/** Equipment that increases effective Defense. */
public class Armor extends Equipment {
    private int defenseBonus;

    public Armor(String name, int defenseBonus, int price, String rarity) {
        super(name, "+" + defenseBonus + " Defense", price, rarity);
        if (defenseBonus < 0) {
            throw new IllegalArgumentException("Defense bonus cannot be negative.");
        }
        this.defenseBonus = defenseBonus;
    }
    public Armor(String name, int defenseBonus) {
        this(name, defenseBonus, 0, "Basic");
    }
    public int getDefenseBonus() {
        return defenseBonus;
    }
}

package com.coloradocollege.cp122homework.project.item;

/** An item that remains equipped and provides a stat bonus. */
public abstract class Equipment extends Item {
    private String rarity;

    public Equipment(String name, String description, int price, String rarity) {
        super(name, description, price);
        this.rarity = rarity;
    }
    public String getRarity() {
        return rarity;
    }
}

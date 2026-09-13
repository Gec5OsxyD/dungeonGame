package com.coloradocollege.cp122homework.project.item;

/** Base type for everything a player can receive or purchase. */
public abstract class Item {
    private String name;
    private String description;
    private int price;

    public Item(String name, String description, int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.name = name;
        this.description = description;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getPrice() {
        return price;
    }
    public String toString() {
        return name + " - " + description;
    }
}

package com.coloradocollege.cp122homework.project.inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.coloradocollege.cp122homework.project.entity.Entity;
import com.coloradocollege.cp122homework.project.item.Consumable;

/** Holds the player's two consumable slots. */
public class Inventory {
    private int maxConsumableSlots = 2;
    private List<Consumable> consumables;

    public Inventory() {
        consumables = new ArrayList<Consumable>();
    }
    public boolean addConsumable(Consumable item) {
        if (item == null || isFull()) {
            return false;
        }
        consumables.add(item);
        return true;
    }
    public Consumable removeConsumable(int index) {
        return consumables.remove(index);
    }
    public Consumable replaceConsumable(int index, Consumable item) {
        return consumables.set(index, item);
    }
    public void useConsumable(int index, Entity source, Entity target) {
        Consumable item = removeConsumable(index);
        item.use(source, target);
    }
    public boolean isFull() {
        return consumables.size() >= maxConsumableSlots;
    }
    public void reorder(int first, int second) {
        Collections.swap(consumables, first, second);
    }
    public List<Consumable> getConsumables() {
        return new ArrayList<Consumable>(consumables);
    }
    public int getMaxConsumableSlots() {
        return maxConsumableSlots;
    }
}

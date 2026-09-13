package com.coloradocollege.cp122homework.project.item;

import com.coloradocollege.cp122homework.project.entity.Entity;

/** A one-use item with an immediate effect. */
public abstract class Consumable extends Item {
    public Consumable(String name, String description, int price) {
        super(name, description, price);
    }
    public abstract void use(Entity source, Entity target);
}

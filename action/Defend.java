package com.coloradocollege.cp122homework.project.action;

import com.coloradocollege.cp122homework.project.entity.Entity;

/** Shared behavior for actions that give temporary Block. */
public abstract class Defend implements Action {
    private final String name;
    private final String description;
    private final int apCost;

    public Defend(String name, String description, int apCost) {
        this.name = name;
        this.description = description;
        this.apCost = apCost;
    }
    public void execute(Entity source, Entity target) {
        source.addBlock(getBlockValue(source));
    }
    public abstract int getBlockValue(Entity source);
    public int getAPCost() {
        return apCost;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}

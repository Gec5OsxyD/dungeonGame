package com.coloradocollege.cp122homework.project.action;

import com.coloradocollege.cp122homework.project.entity.Entity;

/** Shared behavior for actions that damage a target. */
public abstract class Attack implements Action {
    private String name;
    private String description;
    private int apCost;

    public Attack(String name, String description, int apCost) {
        this.name = name;
        this.description = description;
        this.apCost = apCost;
    }
    public void execute(Entity source, Entity target) {
        target.takeDamage(getAttackValue(source));
    }
    public abstract int getAttackValue(Entity source);
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

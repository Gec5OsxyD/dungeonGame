package com.coloradocollege.cp122homework.project.action;

import com.coloradocollege.cp122homework.project.entity.Entity;

/** A positive status effect that can also be selected as an action. */
public abstract class Buff extends StatusEffect implements Action {
    private String description;

    public Buff(String name, String description, int duration) {
        super(name, duration);
        this.description = description;
    }
    public void execute(Entity source, Entity target) {
        apply(target);
    }
    public void apply(Entity target) {
        target.addBuff(this);
    }
    public int getAPCost() {
        return 1;
    }
    public String getDescription() {
        return description;
    }
    public int getAttackModifier() {
        return 0;
    }
    public int getDefenseModifier() {
        return 0;
    }
}

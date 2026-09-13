package com.coloradocollege.cp122homework.project.action;

import com.coloradocollege.cp122homework.project.entity.Entity;

/** A negative status effect that can also be selected as an action. */
public abstract class Debuff extends StatusEffect implements Action {
    private String description;

    public Debuff(String name, String description, int duration) {
        super(name, duration);
        this.description = description;
    }
    public void execute(Entity source, Entity target) {
        apply(target);
    }
    public void apply(Entity target) {
        target.addDebuff(this);
    }
    public int getAPCost() {
        return 1;
    }
    public String getDescription() {
        return description;
    }
    public double getIncomingDamageMultiplier() {
        return 1.0;
    }
}

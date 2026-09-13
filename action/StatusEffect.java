package com.coloradocollege.cp122homework.project.action;

import com.coloradocollege.cp122homework.project.entity.Entity;

/** Common duration and naming behavior for buffs and debuffs. */
public abstract class StatusEffect {
    private int duration;
    private String name;

    public StatusEffect(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }
    public abstract void apply(Entity target);
    public void update() {
        if (duration > 0) {
            duration--;
        }
    }
    public void extendDuration(int amount) {
        if (amount > 0) {
            duration += amount;
        }
    }
    public boolean isExpired() {
        return duration <= 0;
    }
    public int getDuration() {
        return duration;
    }
    public String getName() {
        return name;
    }
}

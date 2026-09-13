package com.coloradocollege.cp122homework.project.item;

import com.coloradocollege.cp122homework.project.entity.Entity;

/** Restores a fixed amount of HP. */
public class HealthPotion extends Consumable {
    private int healingAmount;

    public HealthPotion(String name, int healingAmount, int price) {
        super(name, "Restore " + healingAmount + " HP", price);
        this.healingAmount = healingAmount;
    }

    public HealthPotion(int healingAmount) {
        this(healingAmount >= 30 ? "Large Health Potion" : "Small Health Potion",
                healingAmount, healingAmount >= 30 ? 25 : 12);
    }

    public void use(Entity source, Entity target) {
        target.heal(healingAmount);
    }

    public int getHealingAmount() {
        return healingAmount;
    }
}

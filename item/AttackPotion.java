package com.coloradocollege.cp122homework.project.item;

import com.coloradocollege.cp122homework.project.entity.Entity;

/** Deals damage immediately and costs no AP to use. */
public class AttackPotion extends Consumable {
    private int damageAmount;

    public AttackPotion(int damageAmount, int price) {
        super("Small Attack Potion", "Deal " + damageAmount + " damage", price);
        this.damageAmount = damageAmount;
    }
    public AttackPotion() {
        this(8, 15);
    }
    public void use(Entity source, Entity target) {
        target.takeDamage(damageAmount);
    }
    public int getDamageAmount() {
        return damageAmount;
    }
}

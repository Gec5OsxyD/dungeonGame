package com.coloradocollege.cp122homework.project.action.concrete;

import com.coloradocollege.cp122homework.project.action.Attack;
import com.coloradocollege.cp122homework.project.entity.Entity;

/** The Archer's inexpensive ranged attack. */
public class QuickShot extends Attack {
    public QuickShot() {
        super("Quick Shot", "Deal damage equal to effective Attack.", 1);
    }
    public int getAttackValue(Entity source) {
        return source.getEffectiveAttack();
    }
}

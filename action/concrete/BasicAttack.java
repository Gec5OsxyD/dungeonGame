package com.coloradocollege.cp122homework.project.action.concrete;

import com.coloradocollege.cp122homework.project.action.Attack;
import com.coloradocollege.cp122homework.project.entity.Entity;

/** A standard 1 AP attack. */
public class BasicAttack extends Attack {
    public BasicAttack() {
        super("Basic Attack", "Deal damage equal to effective Attack.", 1);
    }
    public int getAttackValue(Entity source) {
        return source.getEffectiveAttack();
    }
}

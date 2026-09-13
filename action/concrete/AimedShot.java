package com.coloradocollege.cp122homework.project.action.concrete;

import com.coloradocollege.cp122homework.project.action.Attack;
import com.coloradocollege.cp122homework.project.entity.Entity;

/** The Archer's stronger ranged attack. */
public class AimedShot extends Attack {
    public AimedShot() {
        super("Aimed Shot", "Deal effective Attack plus 2 damage.", 2);
    }
    public int getAttackValue(Entity source) {
        return source.getEffectiveAttack() + 2;
    }
}

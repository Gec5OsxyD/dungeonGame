package com.coloradocollege.cp122homework.project.action.concrete;

import com.coloradocollege.cp122homework.project.action.Attack;
import com.coloradocollege.cp122homework.project.entity.Entity;

/** A stronger 2 AP attack used by Warriors and the Boss. */
public class HeavySlash extends Attack {
    public HeavySlash() {
        super("Heavy Slash", "Deal effective Attack plus 3 damage.", 2);
    }
    public int getAttackValue(Entity source) {
        return source.getEffectiveAttack() + 3;
    }
}

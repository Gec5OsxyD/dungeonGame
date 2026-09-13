package com.coloradocollege.cp122homework.project.action.concrete;

import com.coloradocollege.cp122homework.project.action.Buff;

/** A battle-long buff that adds one Attack. */
public class Focus extends Buff {
    public Focus() {
        super("Focus", "Gain +1 Attack for this battle.", Integer.MAX_VALUE);
    }
    public int getAttackModifier() {
        return 1;
    }
}

package com.coloradocollege.cp122homework.project.action.concrete;

import com.coloradocollege.cp122homework.project.action.Debuff;

/** A two-turn debuff that makes an enemy take 50% more damage. */
public class Vulnerable extends Debuff {
    public Vulnerable() {
        super("Vulnerable", "Take 50% more damage for 2 turns.", 2);
    }
    public double getIncomingDamageMultiplier() {
        return 1.5;
    }
}

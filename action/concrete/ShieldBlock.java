package com.coloradocollege.cp122homework.project.action.concrete;

import com.coloradocollege.cp122homework.project.action.Defend;
import com.coloradocollege.cp122homework.project.entity.Entity;

/** Converts effective Defense into temporary Block. */
public class ShieldBlock extends Defend {
    public ShieldBlock() {
        super("Shield Block", "Gain Block equal to effective Defense.", 1);
    }
    public int getBlockValue(Entity source) {
        return source.getEffectiveDefense();
    }
}

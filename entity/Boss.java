package com.coloradocollege.cp122homework.project.entity;

import com.coloradocollege.cp122homework.project.action.concrete.BasicAttack;
import com.coloradocollege.cp122homework.project.action.concrete.HeavySlash;
import com.coloradocollege.cp122homework.project.action.concrete.ShieldBlock;

/** Final enemy with a stronger repeating pattern. */
public class Boss extends Enemy {
    private final String lootTableId;

    public Boss(String name, String lootTableId, int hp, int attack, int defense) {
        super(name, hp, attack, defense);
        this.lootTableId = lootTableId;
        finishInitialization();
    }
    public void initializeActions() {
        addAction(new BasicAttack());
        addAction(new HeavySlash());
        addAction(new ShieldBlock());
    }
    public String getLootTableId() {
        return lootTableId;
    }
}

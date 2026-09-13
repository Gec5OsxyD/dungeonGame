package com.coloradocollege.cp122homework.project.entity;

import com.coloradocollege.cp122homework.project.action.concrete.BasicAttack;
import com.coloradocollege.cp122homework.project.action.concrete.ShieldBlock;

/** A normal dungeon enemy with a fixed attack-then-defend pattern. */
public class Monster extends Enemy {
    private String monsterId;
    private String lootTableId;

    public Monster(String monsterId, String name, String lootTableId,
            int hp, int attack, int defense) {
        super(name, hp, attack, defense);
        this.monsterId = monsterId;
        this.lootTableId = lootTableId;
        finishInitialization();
    }
    public void initializeActions() {
        addAction(new BasicAttack());
        addAction(new ShieldBlock());
    }
    public String getMonsterId() {
        return monsterId;
    }
    public String getLootTableId() {
        return lootTableId;
    }
}

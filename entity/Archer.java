package com.coloradocollege.cp122homework.project.entity;

import com.coloradocollege.cp122homework.project.action.concrete.AimedShot;
import com.coloradocollege.cp122homework.project.action.concrete.QuickShot;
import com.coloradocollege.cp122homework.project.action.concrete.ShieldBlock;

/** Offensive class with the design document's Archer statistics. */
public class Archer extends Player {
    public Archer() {
        super(22, 6, 1);
        initializeActions();
        giveStarterItems("Starter Bow");
    }
    public void initializeActions() {
        addAction(new QuickShot());
        addAction(new AimedShot());
        addAction(new ShieldBlock());
    }
}

package com.coloradocollege.cp122homework.project.entity;

import com.coloradocollege.cp122homework.project.action.concrete.BasicAttack;
import com.coloradocollege.cp122homework.project.action.concrete.HeavySlash;
import com.coloradocollege.cp122homework.project.action.concrete.ShieldBlock;

public class Warrior extends Player {
    public Warrior() {
        super(30, 5, 2);
        initializeActions();
        giveStarterItems("Starter Sword");
    }
    public void initializeActions() {
        addAction(new BasicAttack());
        addAction(new HeavySlash());
        addAction(new ShieldBlock());
    }
}

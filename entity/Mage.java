package com.coloradocollege.cp122homework.project.entity;

import com.coloradocollege.cp122homework.project.action.concrete.BasicAttack;
import com.coloradocollege.cp122homework.project.action.concrete.Focus;
import com.coloradocollege.cp122homework.project.action.concrete.ShieldBlock;
import com.coloradocollege.cp122homework.project.action.concrete.Vulnerable;

public class Mage extends Player {
    private int mageEffectLimit = 3;

    public Mage() {
        super(18, 4, 1);
        initializeActions();
        giveStarterItems("Starter Wand");
    }
    public void initializeActions() {
        addAction(new BasicAttack());
        addAction(new ShieldBlock());
        addAction(new Focus());
        addAction(new Vulnerable());
    }
    public boolean canUseBuff() {
        return getBuffsUsed() < mageEffectLimit;
    }
    public boolean canUseDebuff() {
        return getDebuffsUsed() < mageEffectLimit;
    }
}

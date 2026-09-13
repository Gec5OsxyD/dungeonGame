package com.coloradocollege.cp122homework.project.entity;

import java.util.ArrayList;
import java.util.List;

import com.coloradocollege.cp122homework.project.action.Buff;
import com.coloradocollege.cp122homework.project.action.Debuff;

/** Shared health, combat-stat, Block, and status-effect behavior. */
public abstract class Entity {
    private int hp;
    private int maxHp;
    private int baseAttack;
    private int baseDefense;
    private int block;
    private List<Buff> activeBuffs;
    private List<Debuff> activeDebuffs;

    public Entity(int maxHp, int baseAttack, int baseDefense) {
        if (maxHp <= 0 || baseAttack < 0 || baseDefense < 0) {
            throw new IllegalArgumentException("HP must be positive and combat stats cannot be negative.");
        }
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        activeBuffs = new ArrayList<Buff>();
        activeDebuffs = new ArrayList<Debuff>();
    }
    /** Resolves Block, then Defense, then HP. */
    public void takeDamage(int attackValue) {
        if (attackValue < 0) {
            throw new IllegalArgumentException("Attack value cannot be negative.");
        }
        int adjustedAttack = (int) Math.round(attackValue * getIncomingDamageMultiplier());
        int damageAfterBlock = Math.max(0, adjustedAttack - block);
        block = Math.max(0, block - adjustedAttack);
        int finalDamage = Math.max(0, damageAfterBlock - getEffectiveDefense());
        hp = Math.max(0, hp - finalDamage);
    }
    /** Applies damage that ignores Block and Defense, such as a trap. */
    public void takeRawDamage(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Damage cannot be negative.");
        }
        hp = Math.max(0, hp - amount);
    }
    public void heal(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Healing cannot be negative.");
        }
        hp = Math.min(maxHp, hp + amount);
    }
    public void healToFull() {
        hp = maxHp;
    }
    public int getEffectiveAttack() {
        int attack = baseAttack;
        for (Buff buff : activeBuffs) {
            attack += buff.getAttackModifier();
        }
        return Math.max(0, attack);
    }

    public int getEffectiveDefense() {
        int defense = baseDefense;
        for (Buff buff : activeBuffs) {
            defense += buff.getDefenseModifier();
        }
        return Math.max(0, defense);
    }
    public double getIncomingDamageMultiplier() {
        double multiplier = 1.0;
        for (Debuff debuff : activeDebuffs) {
            multiplier *= debuff.getIncomingDamageMultiplier();
        }
        return multiplier;
    }
    public void addBuff(Buff buff) {
        if (buff != null) {
            activeBuffs.add(buff);
        }
    }
    /** Reapplying the same debuff extends its duration. */
    public void addDebuff(Debuff debuff) {
        if (debuff == null) {
            return;
        }
        for (Debuff active : activeDebuffs) {
            if (active.getName().equals(debuff.getName())) {
                active.extendDuration(debuff.getDuration());
                return;
            }
        }
        activeDebuffs.add(debuff);
    }
    public void updateStatusEffects() {
        updateBuffs();
        updateDebuffs();
    }
    public void updateBuffs() {
        for (int i = activeBuffs.size() - 1; i >= 0; i--) {
            activeBuffs.get(i).update();
            if (activeBuffs.get(i).isExpired()) {
                activeBuffs.remove(i);
            }
        }
    }

    private void updateDebuffs() {
        for (int i = activeDebuffs.size() - 1; i >= 0; i--) {
            activeDebuffs.get(i).update();
            if (activeDebuffs.get(i).isExpired()) {
                activeDebuffs.remove(i);
            }
        }
    }

    public void clearStatusEffects() {
        activeBuffs.clear();
        activeDebuffs.clear();
    }

    public void addBlock(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Block cannot be negative.");
        }
        block += amount;
    }

    public void clearBlock() {
        block = 0;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public int getBlock() {
        return block;
    }
}

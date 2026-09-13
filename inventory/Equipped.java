package com.coloradocollege.cp122homework.project.inventory;

import com.coloradocollege.cp122homework.project.item.Armor;
import com.coloradocollege.cp122homework.project.item.Weapon;

/** Stores the player's one weapon and one armor. */
public class Equipped {
    private Weapon weapon;
    private Armor armor;

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    public void equipArmor(Armor armor) {
        this.armor = armor;
    }
    public Weapon getWeapon() {
        return weapon;
    }
    public Armor getArmor() {
        return armor;
    }
}

package com.coloradocollege.cp122homework.project.map.node;

import com.coloradocollege.cp122homework.project.entity.*;
import com.coloradocollege.cp122homework.project.combat.Combat;
import com.coloradocollege.cp122homework.project.loot.LootGenerator;

import java.util.Random;
import java.util.Scanner;

public class MonsterNode extends MapNode {
    private Monster monster;
    private Combat combat;
    private LootGenerator lootGenerator;
    private Scanner console;

    public MonsterNode(int nodeId, Monster monster, Scanner console, Random random) {
        super(nodeId);
        this.monster = monster;
        this.console = console;
        combat = new Combat(console);
        lootGenerator = new LootGenerator(random);
    }
    public void enter(Player player) {
        if (combat.run(player, monster)) {
            lootGenerator.award(player, monster, console);
        }
    }
    public String toString() {
        return "[Monster: " + monster.getName() + "]";
    }
}

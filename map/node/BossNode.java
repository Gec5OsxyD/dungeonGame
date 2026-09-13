package com.coloradocollege.cp122homework.project.map.node;

import com.coloradocollege.cp122homework.project.entity.*;
import com.coloradocollege.cp122homework.project.combat.Combat;
import com.coloradocollege.cp122homework.project.loot.LootGenerator;

import java.util.Random;
import java.util.Scanner;

public class BossNode extends MapNode {
    private Boss boss;
    private Combat combat;
    private LootGenerator lootGenerator;
    private Scanner console;

    public BossNode(int nodeId, Boss boss, Scanner console, Random random) {
        super(nodeId);
        this.boss = boss;
        this.console = console;
        combat = new Combat(console);
        lootGenerator = new LootGenerator(random);
    }
    public void enter(Player player) {
        if (combat.run(player, boss)) {
            lootGenerator.award(player, boss, console);
        }
    }
    public String toString() {
        return "[Boss]";
    }
}

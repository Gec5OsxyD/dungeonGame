package com.coloradocollege.cp122homework.project.map.node;

import com.coloradocollege.cp122homework.project.entity.*;
import com.coloradocollege.cp122homework.project.event.Event;
import com.coloradocollege.cp122homework.project.event.Shop;
import com.coloradocollege.cp122homework.project.event.Trap;
import com.coloradocollege.cp122homework.project.event.Treasure;

import java.util.Random;
import java.util.Scanner;

public class EventNode extends MapNode {
    private Scanner console;
    private Random random;

    public EventNode(int nodeId, Scanner console, Random random) {
        super(nodeId);
        this.console = console;
        this.random = random;
    }
    public void enter(Player player) {
        Event event;
        int choice = random.nextInt(3);
        if (choice == 0) {
            System.out.println("A wandering merchant appears.");
            event = new Shop(console);
        } else if (choice == 1) {
            event = new Treasure(console);
        } else {
            event = new Trap(console);
        }
        event.execute(player);
    }
    public String toString() {
        return "[???]";
    }
}

package com.coloradocollege.cp122homework.project.event;

import java.util.Scanner;

import com.coloradocollege.cp122homework.project.entity.Player;

/** Base type for non-combat dungeon events. */
public abstract class Event {
    public Scanner console;
    public Event(Scanner console) {
        this.console = console;
    }
    public abstract void execute(Player player);
}

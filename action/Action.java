package com.coloradocollege.cp122homework.project.action;

import com.coloradocollege.cp122homework.project.entity.Entity;

/** A combat choice that can be shown in a menu and executed. */
public interface Action {
    void execute(Entity source, Entity target);
    int getAPCost();
    String getName();
    String getDescription();
}

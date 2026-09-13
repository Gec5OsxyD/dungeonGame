package com.coloradocollege.cp122homework.project.combat;

import com.coloradocollege.cp122homework.project.action.Action;

/** The action an enemy has announced but not yet executed. */
public class EnemyIntention {
    private Action action;
    private String description;
    private int expectedValue;

    public EnemyIntention(Action action, String description, int expectedValue) {
        this.action = action;
        this.description = description;
        this.expectedValue = expectedValue;
    }
    public Action getAction() {
        return action;
    }
    public String getDescription() {
        return description;
    }
    public int getExpectedValue() {
        return expectedValue;
    }
}

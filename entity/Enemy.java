package com.coloradocollege.cp122homework.project.entity;

import java.util.ArrayList;
import java.util.List;

import com.coloradocollege.cp122homework.project.action.Action;
import com.coloradocollege.cp122homework.project.combat.EnemyIntention;
import com.coloradocollege.cp122homework.project.combat.IntentionProvider;
import com.coloradocollege.cp122homework.project.combat.PatternIntentionProvider;

/** Shared parent for normal monsters and the final Boss. */
public abstract class Enemy extends Entity {
    private final String name;
    private IntentionProvider intentionProvider;
    private EnemyIntention currentIntention;
    private final List<Action> actions;

    public Enemy(String name, int maxHp, int baseAttack, int baseDefense) {
        super(maxHp, baseAttack, baseDefense);
        this.name = name;
        actions = new ArrayList<Action>();
    }
    public abstract void initializeActions();
    public void finishInitialization() {
        initializeActions();
        intentionProvider = new PatternIntentionProvider(actions);
    }
    public EnemyIntention revealIntention(Entity target) {
        currentIntention = intentionProvider.determineNextAction(this, target);
        return currentIntention;
    }
    public void executeIntention(Entity target) {
        if (currentIntention == null) {
            throw new IllegalStateException("Reveal the enemy intention before executing it.");
        }
        currentIntention.getAction().execute(this, target);
        currentIntention = null;
    }
    public void setIntentionProvider(IntentionProvider provider) {
        if (provider == null) {
            throw new IllegalArgumentException("Intention provider cannot be null.");
        }
        intentionProvider = provider;
    }
    public EnemyIntention getCurrentIntention() {
        return currentIntention;
    }
    public void addAction(Action action) {
        actions.add(action);
    }
    public List<Action> getActions() {
        return new ArrayList<Action>(actions);
    }
    public String getName() {
        return name;
    }
}

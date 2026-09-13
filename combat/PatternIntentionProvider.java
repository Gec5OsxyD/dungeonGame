package com.coloradocollege.cp122homework.project.combat;

import java.util.ArrayList;
import java.util.List;

import com.coloradocollege.cp122homework.project.action.Action;
import com.coloradocollege.cp122homework.project.action.Attack;
import com.coloradocollege.cp122homework.project.action.Defend;
import com.coloradocollege.cp122homework.project.entity.Enemy;
import com.coloradocollege.cp122homework.project.entity.Entity;

/** Cycles through a fixed action list. */
public class PatternIntentionProvider implements IntentionProvider {
    private List<Action> actionPattern;
    private int currentIndex;

    public PatternIntentionProvider(List<Action> actionPattern) {
        if (actionPattern == null || actionPattern.isEmpty()) {
            throw new IllegalArgumentException("An enemy needs at least one action.");
        }
        this.actionPattern = new ArrayList<Action>(actionPattern);
    }
    public EnemyIntention determineNextAction(Enemy enemy, Entity target) {
        Action action = actionPattern.get(currentIndex);
        currentIndex = (currentIndex + 1) % actionPattern.size();
        int expectedValue = 0;
        if (action instanceof Attack) {
            expectedValue = ((Attack) action).getAttackValue(enemy);
        } else if (action instanceof Defend) {
            expectedValue = ((Defend) action).getBlockValue(enemy);
        }
        return new EnemyIntention(action, action.getDescription(), expectedValue);
    }
}

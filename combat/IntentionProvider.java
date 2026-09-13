package com.coloradocollege.cp122homework.project.combat;

import com.coloradocollege.cp122homework.project.entity.Enemy;
import com.coloradocollege.cp122homework.project.entity.Entity;

/** Strategy used to choose an enemy's next announced action. */
public interface IntentionProvider {
    EnemyIntention determineNextAction(Enemy enemy, Entity target);
}

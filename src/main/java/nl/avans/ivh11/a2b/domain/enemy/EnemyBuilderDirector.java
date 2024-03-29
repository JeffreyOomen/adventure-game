package nl.avans.ivh11.a2b.domain.enemy;

import nl.avans.ivh11.a2b.domain.battle.ActionBehavior;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.util.Media;
import nl.avans.ivh11.a2b.domain.util.Stats;

import java.util.List;

/**
 * EnemyBuilderDirector class
 * creates a Enemy using the builder.
 */
public class EnemyBuilderDirector {
    private Builder builder;

    public EnemyBuilderDirector(final Builder builder) {
        this.builder = builder;
    }

    public Enemy createEnemy(String name, Media media, String description, ActionBehavior actionBehavior) {
        return this.builder.newEnemy()
                .setName(name)
                .setMedia(media)
                .setDescription(description)
                .setActionBehaviour(actionBehavior)
                .buildEnemy();
    }

}

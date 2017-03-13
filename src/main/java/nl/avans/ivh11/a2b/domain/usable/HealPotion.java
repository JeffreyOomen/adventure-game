package nl.avans.ivh11.a2b.domain.usable;

import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.character.Character;

import javax.persistence.Entity;

/**
 * HealPotion
 * is a usable that can be created by the UsableFactory.
 */
@Entity
@Getter
@Setter
public class HealPotion extends Usable {

    public HealPotion(UsableType type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }

    @Override
    public void use(Character character) {
        character.setState(character.getPoweredState());
    }

}
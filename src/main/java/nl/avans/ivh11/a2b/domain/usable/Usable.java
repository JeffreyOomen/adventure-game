package nl.avans.ivh11.a2b.domain.usable;

import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.character.Character;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public abstract class Usable {

    @Id
    @GeneratedValue
    protected Long id;
    protected String name;
    protected String description;

    public abstract void use(Character character);
}

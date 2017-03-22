package nl.avans.ivh11.a2b.domain.usable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue()
    @Column(name = "inventory_id")
    protected Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Usable> usables;

    public Inventory() {
        usables = new ArrayList<>();
    }

    public boolean addUsable(Usable usable) {
        return this.usables.add(usable);
    }

    public boolean dropUsable(Usable usable) {
        return this.usables.remove(usable);
    }

    public List<HealPotion> getHealPotions() {
        List<HealPotion> healPotions = new ArrayList<>();
        healPotions.add(new HealPotion(UsableType.POTION_HEAL, 10));
        return healPotions;
    }

    public Usable getUsable(int index) {
        return this.usables.get(index);
    }

}

package nl.avans.ivh11.a2b.domain.usable;//package nl.avans.ivh11.a2b.domain.usable;

/**
 * PotionFactory concrete implementation of the UsableFactory
 * can create potion items.
 */
public class PotionFactory implements UsableFactory {

    /**
     * createUsable
     * creates an Potion object
     * @return potion usable
     **/
    @Override
    public Usable createUsable(UsableType type, int level) {
        Usable usable = null;
        if (type == UsableType.POTION_HEAL) {
            usable = new HealPotion(type, level);
        } else if (type == UsableType.POTION_OVERLOAD) {
            usable = new OverloadPotion(type, level);
        }
        return usable;
    }
}

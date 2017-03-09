package nl.avans.ivh11.a2b.domain.enemy;

import nl.avans.ivh11.a2b.domain.util.Opponent;
import nl.avans.ivh11.a2b.domain.util.Stats;
import nl.avans.ivh11.a2b.domain.util.observer.Observer;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Enemy
 * Created by matthijs on 8-3-17.
 */
public class Enemy implements Opponent {

    @Transient
    private Stats stats;

    @Transient
    private List<Observer> observers;

    public Enemy(Stats stats) {
        this.stats = stats;
        this.observers = new ArrayList<>();
    }

    /**
     * Get the Observable's state
     * @return String
     */
    public String getState() {
        return Integer.toString(stats.getCurrentHitpoints());
    }

    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        if(this.observers.contains(observer)) {
            this.observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.update();
        }
    }
}

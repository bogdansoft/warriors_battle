package softserve.com.model.damage;

import softserve.com.model.interfaces.WarriorInterface;

public class PiercingDamage extends SimpleDamage {
    private int counter;

    public PiercingDamage(int hitPoints, WarriorInterface damageDealer) {
        super(hitPoints, damageDealer);
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}

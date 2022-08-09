package softserve.com.model.damage;

import softserve.com.model.damage.interfaces.Damage;
import softserve.com.model.interfaces.WarriorInterface;

public class SimpleDamage implements Damage{
    private int hitPoints;
    private final WarriorInterface damageDealer;

    public SimpleDamage(int hitPoints, WarriorInterface damageDealer) {
        this.hitPoints = hitPoints;
        this.damageDealer = damageDealer;
    }

    @Override
    public int hitPoints() {
        return hitPoints;
    }

    @Override
    public WarriorInterface getDamageDealer() {
        return damageDealer;
    }
}

package softserve.com.model.damage_impl;

import softserve.com.model.interfaces.Damage;

public class SimpleDamage implements Damage {
   private final int hitPoints;

    public SimpleDamage(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public int getHitPoints() {
        return hitPoints;
    }
}

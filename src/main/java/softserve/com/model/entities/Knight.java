package softserve.com.model.entities;

import softserve.com.model.damage_impl.SimpleDamage;
import softserve.com.model.interfaces.CanFight;
import softserve.com.model.interfaces.Damage;

public class Knight extends Warrior {
    public static final int ATTACK = 7;

    public Knight() {
        super(INITIAL_HEALTH, ATTACK);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    @Override
    public void hit(CanFight opponent) {
        opponent.receiveDamage(new SimpleDamage(getAttack()), this);
    }

    @Override
    public void receiveDamage(Damage damage, CanFight damageDealer) {
        setHealth(getHealth() - damage.getHitPoints());
    }
}

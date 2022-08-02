package softserve.com.model.entities;

import softserve.com.model.interfaces.CanDefend;
import softserve.com.model.interfaces.CanFight;
import softserve.com.model.interfaces.Damage;

public class Defender extends Warrior implements CanDefend {
    protected static final int ATTACK = 3;
    public static final int INITIAL_HEALTH = 60;
    public static final int DEFENSE = 2;
    int defense = DEFENSE;

    private int health = INITIAL_HEALTH;

    public Defender() {
        super(INITIAL_HEALTH, ATTACK);
    }

    @Override
    public void receiveDamage(Damage damage, CanFight damageDealer) {
        setHealth(getHealth() - (damage.getHitPoints() - getDefence()));
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    @Override
    public int getDefence() {
        return DEFENSE;
    }
}

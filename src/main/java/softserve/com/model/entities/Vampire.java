package softserve.com.model.entities;

import softserve.com.model.interfaces.CanFight;
import softserve.com.model.interfaces.HasVampirism;

public class Vampire extends Warrior implements HasVampirism {
    public static final int ATTACK = 4;
    public static final int INITIAL_HEALTH = 40;
    private int vampirism = 50;
    private int health = INITIAL_HEALTH;

    public Vampire() {
        super(INITIAL_HEALTH, ATTACK);
    }

    @Override
    public void hit(CanFight opponent) {
        super.hit(opponent);
        setHealth(this.healHimself(opponent));
    }

    @Override
    public int healHimself(CanFight opponent) {
        if (opponent instanceof Defender defender) {
            return getHealth() + ((getAttack() - defender.getDefence()) * getVampirismLevel()) / 100;
        } else {
            return getHealth() + (getAttack() * getVampirismLevel()) / 100;
        }
    }

    @Override
    public int getVampirismLevel() {
        return vampirism;
    }
}

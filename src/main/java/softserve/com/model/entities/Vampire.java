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
        health += healHimself();
        if (health > 40) {
            health = 40;
        }
    }

    @Override
    public int healHimself() {
        return getAttack() * getVampirismLevel();
    }

    @Override
    public int getVampirismLevel() {
        return vampirism / 100;
    }
}

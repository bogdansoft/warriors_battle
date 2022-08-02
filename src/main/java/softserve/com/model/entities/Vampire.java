package softserve.com.model.entities;

import softserve.com.model.interfaces.CanFight;
import softserve.com.model.interfaces.HasVampirism;

public class Vampire extends Warrior implements HasVampirism {
    public static final int ATTACK = 4;
    public static final int INITIAL_HEALTH = 40;
    private int vampirism = 50;

    public Vampire() {
        super(INITIAL_HEALTH, ATTACK);
    }

    @Override
    public void hit(CanFight opponent) {
        super.hit(opponent);
        setHealth(healHimself(this));
    }

    @Override
    public int healHimself(Vampire vampire) {
        return vampire.getHealth() + (vampire.getAttack() * vampire.getVampirismLevel()) / 100;
    }

    @Override
    public int getVampirismLevel() {
        return vampirism;
    }
}

package softserve.com.model.entities;

import softserve.com.model.interfaces.CanFight;
import softserve.com.model.interfaces.CanHeal;

public class Healer extends Warrior implements CanHeal {
    protected static final int ATTACK = 0;
    public static final int INITIAL_HEALTH = 60;
    public static final int HEALING = 2;
    private int health = INITIAL_HEALTH;

    public Healer() {
        super(INITIAL_HEALTH, ATTACK);
    }

    @Override
    public int getHealing() {
        return HEALING;
    }

    @Override
    public void letMeHealYou(CanFight colleague) {

    }
}

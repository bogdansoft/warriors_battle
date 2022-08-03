package softserve.com.model.entities;

import softserve.com.model.interfaces.CanHeal;

public class Healer extends Warrior implements CanHeal {
    protected static final int ATTACK = 0;
    public static final int INITIAL_HEALTH = 60;
    private int health = INITIAL_HEALTH;

    public Healer() {
        super(INITIAL_HEALTH, ATTACK);
    }

    @Override
    public void letMeHealYou() {

    }
}

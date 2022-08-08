package softserve.com.model.entities;

import softserve.com.model.interfaces.Unit;
import softserve.com.model.interfaces.WarriorInterface;

public class Warrior implements Unit, WarriorInterface {
    protected static final int ATTACK = 5;
    public static final int INITIAL_HEALTH = 50;
    int attack;
    private int health = INITIAL_HEALTH;
    private WarriorInterface nextBehind;

    public Warrior() {
        this(INITIAL_HEALTH, ATTACK);
    }

    protected Warrior(int health, int attack) {
        this.health = health;
        this.attack = attack;
    }

    @Override
    public int getInitialHealth() {
        return health;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

   /* public boolean isAlive() {
        return health > 0;
    }*/

    @Override
    public boolean isUnitAlive() {
        return this.getHealth() > 0;
    }

    @Override
    public WarriorInterface getNextBehind() {
        return nextBehind;
    }

    @Override
    public void setNextBehind(WarriorInterface nextBehind) {
        this.nextBehind = nextBehind;
    }
}

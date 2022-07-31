package softserve.com.model;

public class Warrior {
    protected static final int ATTACK = 5;
    public static final int INITIAL_HEALTH = 50;
    private int health = INITIAL_HEALTH;

    public boolean isAlive() {
        return health > 0;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return ATTACK;
    }

    public void hit(Warrior opponent) {
        opponent.health -= getAttack();
    }
}

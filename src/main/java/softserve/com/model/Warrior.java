package softserve.com.model;

public class Warrior implements Unit {
    protected static final int ATTACK = 5;
    public static final int INITIAL_HEALTH = 50;
    private int attack;
    private int health = INITIAL_HEALTH;

    public Warrior() {
        this(INITIAL_HEALTH, ATTACK);
    }

    protected Warrior(int health, int attack) {
        this.health = health;
        this.attack = attack;
    }

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

    @Override
    public boolean isUnitAlive() {
        return this.getHealth() > 0;
    }
}

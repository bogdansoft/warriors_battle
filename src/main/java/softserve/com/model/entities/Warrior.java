package softserve.com.model.entities;

import softserve.com.model.damage_impl.SimpleDamage;
import softserve.com.model.interfaces.CanFight;
import softserve.com.model.interfaces.Damage;
import softserve.com.model.interfaces.Unit;

public class Warrior implements Unit, CanFight {
    protected static final int ATTACK = 5;
    public static final int INITIAL_HEALTH = 50;
    int attack;
    private int health = INITIAL_HEALTH;

    public Warrior() {
        this(INITIAL_HEALTH, ATTACK);
    }

    protected Warrior(int health, int attack) {
        this.health = health;
        this.attack = attack;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return ATTACK;
    }

    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void hit(CanFight opponent) {
        opponent.receiveDamage(new SimpleDamage(getAttack()), this);
    }

    @Override
    public void receiveDamage(Damage damage, CanFight damageDealer) {
        setHealth(getHealth() - damage.hitPoints());
    }

    @Override
    public boolean isUnitAlive() {
        return this.getHealth() > 0;
    }
}

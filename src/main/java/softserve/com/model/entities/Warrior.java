package softserve.com.model.entities;

import softserve.com.model.entities.weapons.Weapon;
import softserve.com.model.interfaces.Unit;
import softserve.com.model.interfaces.WarriorInterface;
import softserve.com.model.interfaces.WeaponInterface;

import java.util.ArrayList;
import java.util.List;

public class Warrior implements Unit, WarriorInterface, WeaponInterface {
    protected static final int ATTACK = 5;
    public static final int INITIAL_HEALTH = 50;
    public int attack = ATTACK;
    private int health = INITIAL_HEALTH;
    private Warrior nextWarrior = null;
    private Warrior frontWarrior = null;
    private List<Weapon> weapons = new ArrayList<>();

    public Warrior() {
        this(INITIAL_HEALTH, ATTACK);
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    @Override
    public void hit(WarriorInterface opponent) {
        WarriorInterface.super.hit(opponent);
        process(this);
    }

    protected Warrior(int health, int attack) {
        this.health = health;
        this.attack = attack;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public boolean isUnitAlive() {
        return this.getHealth() > 0;
    }

    @Override
    public Warrior getNextWarrior() {
        return nextWarrior;
    }

    @Override
    public void setNextWarrior(Warrior nextWarrior) {
        this.nextWarrior = nextWarrior;
    }

    @Override
    public Warrior getFrontWarrior() {
        return frontWarrior;
    }

    @Override
    public void setFrontWarrior(Warrior frontWarrior) {
        this.frontWarrior = frontWarrior;
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        weapons.add(weapon);

        var healthSum = weapons.stream()
                .mapToInt(Weapon::getHealth)
                .sum();
        setHealth(INITIAL_HEALTH + healthSum);

        var attackSum = weapons.stream()
                .mapToInt(Weapon::getAttack)
                .sum();
        setAttack(getAttack() + attackSum);
    }

    @Override
    public void process(WarriorInterface warrior) {
        if (nextWarrior != null) {
            warrior.process(warrior.getNextWarrior());
        }
    }
}
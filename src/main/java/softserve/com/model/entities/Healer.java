package softserve.com.model.entities;

import softserve.com.model.interfaces.CanHeal;
import softserve.com.model.interfaces.WarriorInterface;

public class Healer extends Warrior implements CanHeal {
    private static final int INITIAL_HEALTH = 60;
    private static final int ATTACK = 0;
    private static final int HEALING = 2;

    private int healing = HEALING;

    public Healer() {
        super(INITIAL_HEALTH, ATTACK);
    }

    public void heal(WarriorInterface warrior) {
        warrior.setHealth(warrior.getHealth() + getHealValue());
        if (warrior.getHealth() > INITIAL_HEALTH) {
            warrior.setHealth(INITIAL_HEALTH);
        }
    }

    @Override
    public void hit(WarriorInterface opponent) {
    }

    @Override
    public void process(WarriorInterface warrior) {
        if (getFrontWarrior() != null) {
            heal(getFrontWarrior());
        }
        super.process(warrior);
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        super.equipWeapon(weapon);

        var healPowerSum = getWeapons().stream()
                .mapToInt(Weapon::getHealPower)
                .sum();
        setHealing(HEALING + healPowerSum);
    }

    public void setHealing(int healing) {
        this.healing = healing;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    public int getHealValue() {
        return HEALING;
    }
}
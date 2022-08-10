package softserve.com.model.entities;

import softserve.com.model.interfaces.HasVampirism;
import softserve.com.model.interfaces.WarriorInterface;

public class Vampire extends Warrior implements HasVampirism {
    public static final int ATTACK = 4;
    public static final int INITIAL_HEALTH = 40;
    private int vampirism = 50;
    private int health = INITIAL_HEALTH;

    public Vampire() {
        super(INITIAL_HEALTH, ATTACK);
    }

    @Override
    public void hit(WarriorInterface opponent) {
        super.hit(opponent);
        health += healHimself();
        if (health > 40) {
            health = 40;
        }
    }

    public void setVampirism(int vampirism) {
        this.vampirism = vampirism;
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        super.equipWeapon(weapon);

        var vampirismValue = getVampirismLevel() + weapon.getVampirism();
        if (vampirismValue <= 0) setVampirism(0);
        else setVampirism(vampirismValue);
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

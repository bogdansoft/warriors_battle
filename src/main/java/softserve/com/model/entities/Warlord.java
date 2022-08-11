package softserve.com.model.entities;

import softserve.com.model.damage.interfaces.Damage;
import softserve.com.model.entities.weapons.Weapon;
import softserve.com.model.interfaces.CanDefend;

public class Warlord extends Warrior implements CanDefend {
    public static final int ATTACK = 4;
    public static final int INITIAL_HEALTH = 100;
    public static final int DEFENSE = 2;
    private int attack = ATTACK;
    private int health = INITIAL_HEALTH;
    private int defense = DEFENSE;

    public Warlord() {
        super(INITIAL_HEALTH, ATTACK);
    }
    @Override
    public void receiveDamage(Damage damage) {
        if (damage.hitPoints() > getDefence()) {
            setHealth(getHealth() - (damage.hitPoints() - getDefence()));
        }
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        super.equipWeapon(weapon);

        var defenseSum = getWeapons().stream()
                .mapToInt(Weapon::getDefense)
                .sum();
        setDefense(DEFENSE + defenseSum);
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getDefence() {
        return defense;
    }
}

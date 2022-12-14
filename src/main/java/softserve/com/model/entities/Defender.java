package softserve.com.model.entities;

import softserve.com.model.damage.interfaces.Damage;
import softserve.com.model.entities.weapons.Weapon;
import softserve.com.model.interfaces.CanDefend;
import softserve.com.model.interfaces.WerewolfState;

public class Defender extends Warrior implements WerewolfState, CanDefend {
    protected static final int ATTACK = 3;
    public static final int INITIAL_HEALTH = 60;
    public static final int DEFENSE = 2;
    private int attack = ATTACK;
    private int health = INITIAL_HEALTH;
    private int defense = DEFENSE;

    public Defender() {
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

    @Override
    public int getVampirismLevel() {
        return 0;
    }

    @Override
    public int healHimself() {
        return 0;
    }
}

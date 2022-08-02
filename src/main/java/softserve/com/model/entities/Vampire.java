package softserve.com.model.entities;

import softserve.com.model.damage_impl.SimpleDamage;
import softserve.com.model.interfaces.CanFight;
import softserve.com.model.interfaces.HasVampirism;

public class Vampire extends Warrior implements HasVampirism {
    public static final int ATTACK = 4;
    public static final int INITIAL_HEALTH = 40;
    private int vampirism = 50;

    public Vampire() {
        super(INITIAL_HEALTH, ATTACK);
    }

    @Override
    public void hit(CanFight opponent) {
        opponent.receiveDamage(new SimpleDamage(getAttack()), this);
        healHimself(this, opponent);
    }

    @Override
    public int healHimself(Vampire vampire, CanFight opponent) {
        return vampire.getHealth() + (getOpponentAttack(opponent) * this.getVampirismLevel()) / 100;
    }

    @Override
    public int getOpponentAttack(CanFight opponent) {
        if (opponent instanceof Defender defender) return defender.getAttack() - defender.getDefence();
        else if (opponent instanceof Warrior warrior) return warrior.getAttack();
        else return 0;
    }

    @Override
    public int getVampirismLevel() {
        return vampirism;
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }
}

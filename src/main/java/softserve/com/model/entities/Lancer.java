package softserve.com.model.entities;

import softserve.com.model.damage_impl.SimpleDamage;
import softserve.com.model.interfaces.CanFight;
import softserve.com.service.Battle;

import java.util.*;

public class Lancer extends Warrior {
    protected static final int ATTACK = 6;
    public static final int INITIAL_HEALTH = 50;
    private int health = INITIAL_HEALTH;
   // private int count = Battle.count;
    private Set<CanFight> lancerOpponents = new LinkedHashSet<>();

    public Lancer() {
        super(INITIAL_HEALTH, ATTACK);
    }

    private CanFight getWarrior() {
        return lancerOpponents
                .stream()
                .skip(1)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    @Override
    public void hit(CanFight opponent) {
        lancerOpponents.add(opponent);
        opponent.receiveDamage(new SimpleDamage(getAttack()), this);

        if (lancerOpponents.size() > 1) {
            CanFight secondOpponent = getWarrior();
            secondOpponent.receiveDamage(new SimpleDamage(getAttack() / 2 * Battle.count), this);
        }

       // lancerOpponents.removeIf(o -> !o.isAlive());
    }
}

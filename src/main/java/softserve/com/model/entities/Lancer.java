package softserve.com.model.entities;

import softserve.com.model.damage.SimpleDamage;
import softserve.com.model.interfaces.WarriorInterface;

public class Lancer extends Warrior {
    protected static final int ATTACK = 6;
    private final int PIERCING = 50;

    public Lancer() {
        super(INITIAL_HEALTH, ATTACK);
    }

    @Override
    public void hit(WarriorInterface opponent) {
        super.hit(opponent);

        var nextWarrior = opponent.getNextWarrior();
        if (nextWarrior != null) {
            if (opponent instanceof Defender defender) {
                nextWarrior.receiveDamage(new SimpleDamage((getAttack() - defender.getDefence()) * getPiercing() / 100, this));
            } else {
                nextWarrior.receiveDamage(new SimpleDamage(getAttack() * getPiercing() / 100, this));
            }
        }
    }

    public int getPiercing() {
        return PIERCING;
    }

    @Override
    public int getAttack() {
        return attack;
    }
}

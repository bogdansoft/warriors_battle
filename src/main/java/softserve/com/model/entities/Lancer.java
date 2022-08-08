package softserve.com.model.entities;

import softserve.com.model.damage.PiercingDamage;
import softserve.com.model.damage.SimpleDamage;
import softserve.com.model.interfaces.CanPierce;
import softserve.com.model.interfaces.WarriorInterface;

public class Lancer extends Warrior implements CanPierce {
    protected static final int ATTACK = 6;
    public static final int INITIAL_HEALTH = 50;
    private int health = INITIAL_HEALTH;

    public Lancer() {
        super(INITIAL_HEALTH, ATTACK);
    }


    @Override
    public void hit(WarriorInterface opponent) {
        int healthBeforeHit = opponent.getHealth();
        super.hit(opponent);
        int dealtDamage = healthBeforeHit - opponent.getHealth();

        WarriorInterface nextBehind = opponent.getNextBehind();
        if (nextBehind != null) {
            int pierceDamage = dealtDamage * getPierce() / 100;
            nextBehind.receiveDamage(new PiercingDamage(pierceDamage, this));
        }
    }

    @Override
    public int getPierce() {
        return this.ATTACK / 2;
    }
}

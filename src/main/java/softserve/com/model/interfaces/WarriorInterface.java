package softserve.com.model.interfaces;

import softserve.com.model.damage.SimpleDamage;
import softserve.com.model.damage.interfaces.Damage;
import softserve.com.model.entities.Warrior;

public interface WarriorInterface extends CanAttack, HasHealth {
    void process(WarriorInterface warrior);

    default void hit(WarriorInterface opponent) {
        opponent.receiveDamage(new SimpleDamage(getAttack(), this));
    }

    default void receiveDamage(Damage damage) {
        setHealth(getHealth()- damage.hitPoints());
    }

    Warrior getNextWarrior();

     void setNextWarrior(Warrior nextBehind);

    Warrior getFrontWarrior();

    void setFrontWarrior(Warrior frontWarrior);
}
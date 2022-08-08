package softserve.com.model.interfaces;

import softserve.com.model.command.HealCommand;
import softserve.com.model.command.interfaces.CommandInterface;
import softserve.com.model.damage.SimpleDamage;
import softserve.com.model.damage.interfaces.Damage;

public interface WarriorInterface extends CanAttack, HasHealth {
    default void hit(WarriorInterface opponent) {
        opponent.receiveDamage(new SimpleDamage(getAttack(), this));
        processCommand(new HealCommand(), this);
    }

    default void receiveDamage(Damage damage) {
        reduceHealthBasedOnDamage(damage.hitPoints());
    }

    default WarriorInterface getNextBehind() {
        return null;
    }

    default void setNextBehind(WarriorInterface nextBehind) {
        throw new UnsupportedOperationException();
    }

    default void processCommand(CommandInterface command, WarriorInterface sender) {
        var behind = getNextBehind();
        if (behind != null) {
            behind.processCommand(command, this);
        }
    }
}
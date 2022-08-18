package softserve.com.model.interfaces;

import softserve.com.model.entities.*;

public interface Unit {

    enum UnitType {
        KNIGHT, WARRIOR, DEFENDER, VAMPIRE, WARLORD, WEREWOLF, HEALER, LANCER
    }

    static Unit newUnit(UnitType type) {
        return switch (type) {
            case KNIGHT -> new Knight();
            case WARRIOR -> new Warrior();
            case DEFENDER -> new Defender();
            case VAMPIRE -> new Vampire();
            case WARLORD -> new Warlord();
            case WEREWOLF -> new Werewolf();
            case HEALER -> new Healer();
            case LANCER -> new Lancer();
            default -> throw new IllegalArgumentException();
        };
    }

    int getUnitAttack();

    boolean isUnitAlive();
}

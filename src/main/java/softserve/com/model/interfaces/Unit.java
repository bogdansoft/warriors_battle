package softserve.com.model.interfaces;

import softserve.com.model.entities.Defender;
import softserve.com.model.entities.Knight;
import softserve.com.model.entities.Warrior;

public interface Unit {

    enum UnitType {
        KNIGHT, WARRIOR, DEFENDER
    }

    static Unit newUnit(UnitType type) {
        return switch (type) {
            case KNIGHT -> new Knight();
            case WARRIOR -> new Warrior();
            case DEFENDER -> new Defender();
            default -> throw new IllegalArgumentException();
        };
    }

    boolean isUnitAlive();
}

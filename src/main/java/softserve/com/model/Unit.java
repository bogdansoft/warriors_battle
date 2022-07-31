package softserve.com.model;

public interface Unit {

    enum UnitType {
        KNIGHT, WARRIOR;
    }

    static Unit newUnit(UnitType type) {
        return switch (type) {
            case KNIGHT -> new Knight();
            case WARRIOR -> new Warrior();
            default -> throw new IllegalArgumentException();
        };
    }

    boolean isUnitAlive();
}

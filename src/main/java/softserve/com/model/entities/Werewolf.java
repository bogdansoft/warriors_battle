package softserve.com.model.entities;

import softserve.com.model.day_time.Sun;
import softserve.com.model.interfaces.WerewolfState;

public class Werewolf extends Warrior {
    private Sun sun = Sun.getInstance();
    public static Defender defender = new Defender();
    public static Vampire vampire = new Vampire();
    private WerewolfState state = setWerewolfEntity();

    public Werewolf() {
        state = setWerewolfEntity();
    }

    public Werewolf(int value) {
        if (value == 1) this.state = defender;
        if (value == 2) this.state = vampire;
    }

    public WerewolfState setWerewolfEntity() {
        return switch (getDayTypeForEntity()) {
            case "DAY" -> state = defender;
            case "NIGHT" -> state = vampire;
            default -> throw new IllegalArgumentException();
        };
    }

    public WerewolfState getState() {
        return state;
    }

    public String getDayTypeForEntity() {
        return sun.getDayType().name();
    }
}

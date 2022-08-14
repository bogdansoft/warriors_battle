package softserve.com.model.entities;

import softserve.com.model.day_time.Sun;

public class Werewolf extends Warrior{
    private Sun sun = Sun.getInstance();
    private Warrior warrior;

    public Werewolf() {
        warrior = setWerewolfEntity();
    }

    public Werewolf(int value) {
        if (value == 1) this.warrior = new Defender();
        if (value == 2) this.warrior = new Vampire();
    }


    public Warrior setWerewolfEntity() {
        return switch (getDayTypeForEntity()) {
            case "DAY" -> new Defender();
            case "NIGHT" -> new Vampire();
            default -> throw new IllegalArgumentException();
        };
    }

    public Warrior getWarrior() {
        return warrior;
    }

    public String getDayTypeForEntity() {
        return sun.getDayType().name();
    }
}

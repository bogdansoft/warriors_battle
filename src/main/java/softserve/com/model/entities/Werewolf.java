package softserve.com.model.entities;

import softserve.com.model.day_time.Sun;
import softserve.com.model.interfaces.WerewolfState;

public class Werewolf extends Warrior implements WerewolfState {
    private Sun sun = Sun.getInstance();
    public Defender defender = new Defender();
    public Vampire vampire = new Vampire();
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
            case "DAY" -> defender;
            case "NIGHT" -> vampire;
            default -> throw new IllegalArgumentException();
        };
    }

    public WerewolfState getState() {
        return state;
    }

    public String getDayTypeForEntity() {
        return sun.getDayType().name();
    }

    @Override
    public int getHealth() {
        return this.getState().getHealth();
    }

    @Override
    public int getAttack() {
        return this.getState().getAttack();
    }

    @Override
    public int getDefence() {
        return this.getState().getDefence();
    }

    @Override
    public int getVampirismLevel() {
        return this.getState().getVampirismLevel();
    }

    @Override
    public int healHimself() {
        return this.getState().healHimself();
    }
}

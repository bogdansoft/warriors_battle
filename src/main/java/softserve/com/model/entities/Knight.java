package softserve.com.model.entities;

public class Knight extends Warrior {
    public static final int ATTACK = 7;
    private int attack = ATTACK;

    public Knight() {
        super(INITIAL_HEALTH, ATTACK);
    }

    @Override
    public int getAttack() {
        return attack;
    }
}

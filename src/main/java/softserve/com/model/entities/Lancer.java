package softserve.com.model.entities;

import softserve.com.model.damage_impl.SimpleDamage;
import softserve.com.model.interfaces.CanFight;
import softserve.com.service.Battle;

import java.util.ArrayList;
import java.util.List;

public class Lancer extends Warrior {
    protected static final int ATTACK = 6;
    public static final int INITIAL_HEALTH = 50;
    private int health = INITIAL_HEALTH;
    private List<CanFight> lancerOpponents = new ArrayList<>();

    public Lancer() {
        super(INITIAL_HEALTH, ATTACK);
    }

    @Override
    public void hit(CanFight opponent) {
        int count = 0;
        lancerOpponents.add(opponent);
        super.hit(opponent);
        if (lancerOpponents.size() > 1) {
            CanFight secondOpponent = lancerOpponents.get(0);
            for (int i = 0; i < Battle.count; i++) {
                secondOpponent.receiveDamage(new SimpleDamage(3),this);
            }
        }
    }
}

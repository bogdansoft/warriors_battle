package softserve.com;

import softserve.com.model.entities.Warrior;

class Rookie extends Warrior {
    @Override
    public int getAttack() {
        return 1;
    }
}

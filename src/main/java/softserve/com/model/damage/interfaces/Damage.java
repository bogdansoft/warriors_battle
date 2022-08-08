package softserve.com.model.damage.interfaces;

import softserve.com.model.interfaces.WarriorInterface;

public interface Damage {
    int hitPoints();

    WarriorInterface getDamageDealer();
}

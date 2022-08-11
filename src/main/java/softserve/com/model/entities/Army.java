package softserve.com.model.entities;

import softserve.com.model.entities.weapons.Weapon;
import softserve.com.model.interfaces.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Army {
    private List<Unit> warriors;

    public Army() {
        warriors = new ArrayList<>();
    }

    public Army addUnits(Supplier<Warrior> factory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            warriors.add(factory.get());
        }
        return this;
    }

    public List<Unit> getWarriors() {
        return this.warriors;
    }

    public void equipWarriorAtPosition(int index, Weapon weapon) {
        var warriorFromArmy = (Warrior) this.warriors.get(index);
        warriorFromArmy.equipWeapon(weapon);
    }
}

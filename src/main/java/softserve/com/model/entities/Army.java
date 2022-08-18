package softserve.com.model.entities;

import softserve.com.model.entities.weapons.Weapon;
import softserve.com.model.interfaces.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toCollection;

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

    public boolean isWarlordInArmy() {
        return this.getWarriors()
                .stream()
                .anyMatch(Warlord.class::isInstance);
    }

    public boolean isLancerInArmy() {
        return this.getWarriors()
                .stream()
                .anyMatch(Lancer.class::isInstance);
    }

    public boolean isHealerInArmy() {
        return this.getWarriors()
                .stream()
                .anyMatch(Healer.class::isInstance);
    }

    public boolean isAnyAttackerInArmy() {
        List<Unit> getAllAttackers = this.getWarriors()
                .stream()
                .filter(w -> w.getUnitAttack() > 0)
                .collect(toCollection(ArrayList::new));

        return !getAllAttackers.isEmpty();
    }

    public void moveHealerAfterLancerInArmy() {
        List<Unit> healersList = this.getWarriors().stream()
                .filter(Healer.class::isInstance)
                .collect(toCollection(ArrayList::new));

        if (!healersList.isEmpty()) {
            this.getWarriors().removeIf(Healer.class::isInstance);
            this.getWarriors().addAll(1, healersList);
        }
    }

    public void moveWarlordToTheEndOFArmy() {
        if (!(this.getWarriors().get(getWarriors().size() - 1) instanceof Warlord)) {
            var warlord = this.getWarriors().stream()
                    .filter(Warlord.class::isInstance)
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new);

            this.getWarriors().removeIf(Warlord.class::isInstance);
            this.getWarriors().add(warlord);
        }
    }

    public void moveLancerToFrontOFArmy() {
        if (!(this.getWarriors().get(0) instanceof Lancer)) {
            var lancer = this.getWarriors().stream()
                    .filter(Lancer.class::isInstance)
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new);

            this.getWarriors().remove(lancer);
            this.getWarriors().add(0, lancer);
        }
    }

    public void moveAnyAttackerToFrontOfArmy() {
        var firstWarriorValue = this.getWarriors().get(0);

        if (firstWarriorValue instanceof Healer) {
            var anyWarrior = this.getWarriors().stream()
                    .filter(w -> w.getUnitAttack() > 0)
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new);

            this.getWarriors().remove(anyWarrior);
            this.getWarriors().add(0, anyWarrior);
        }
    }

    public void moveUnits() {
        if (this.isWarlordInArmy()) {
            moveWarlordToTheEndOFArmy();
            if (this.isLancerInArmy()) {
                moveLancerToFrontOFArmy();
            } else {
                if (this.isAnyAttackerInArmy()) {
                    moveAnyAttackerToFrontOfArmy();
                }
            }
            if (this.isHealerInArmy()) {
                moveHealerAfterLancerInArmy();
            }
        }
    }
}

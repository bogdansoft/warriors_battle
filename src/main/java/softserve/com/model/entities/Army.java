package softserve.com.model.entities;

import softserve.com.model.entities.weapons.Weapon;
import softserve.com.model.interfaces.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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

    /*Also, when the Warlord is included in any of the armies, that particular army gets the new moveUnits() method which
    allows to rearrange the units of that army for the better battle result. The rearrangement is done not only before the battle,
    but during the battle too, each time the allied units die. The rules for the rearrangement are as follow:
    If there are Lancers in the army, they should be placed in front of everyone else.
    If there is a Healer in the army, he should be placed right after the first soldier to heal him during the fight. If the number
    of Healers is > 1, all of them should be placed right behind the first Healer.
    If there are no more Lancers in the army, but there are other soldiers who can deal damage, they also should be placed in first
    position, and the Healer should stay in the 2nd row (if army still has Healers).
    Warlord should always stay way in the back to look over the battle and rearrange the soldiers when it's needed.
    Every army can have no more than 1 Warlord.
    If the army doesn’t have a Warlord, it can’t use the moveUnits() method. (it should do nothing in such case)
    */
    public boolean isWarlordInArmy() {
        return this.getWarriors()
                .stream()
                .anyMatch(
                        w -> w.getClass().getSimpleName().equalsIgnoreCase("warlord")
                );
    }

    public boolean isLancerInArmy() {
        return this.getWarriors()
                .stream()
                .anyMatch(
                        w -> w.getClass().getSimpleName().equalsIgnoreCase("lancer")
                );
    }

    public boolean isHealerInArmy() {
        return this.getWarriors()
                .stream()
                .anyMatch(
                        w -> w.getClass().getSimpleName().equalsIgnoreCase("healer")
                );
    }

    public void moveHealerAfterLancerInArmy() {
        var healersQuantityInArmy = this.getWarriors().stream()
                .filter(
                        w -> w.getClass().getSimpleName().equalsIgnoreCase("healer")
                ).count();

        List<Unit> healersList = new ArrayList<>();

        if (healersQuantityInArmy > 0) {
            for (int i = 0; i < this.getWarriors().size(); i++) {
                var value = this.getWarriors().get(i);

                if (value.getClass().getSimpleName().equalsIgnoreCase("healer")) {
                    healersList.add(value);
                    this.getWarriors().remove(value);
                }
            }
        }
        this.getWarriors().addAll(1, healersList);
    }

    public void moveWarlordToTheEndOFArmy() {
        if (!(this.getWarriors().get(getWarriors().size() - 1) instanceof Healer)) {
            var warlord = this.getWarriors().stream()
                    .filter(w -> w.getClass().getSimpleName().equalsIgnoreCase("warlord"))
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new);

            this.getWarriors().remove(warlord);
            this.getWarriors().add(new Warlord());
        }
    }

    public void moveLancerToFrontOFArmy() {
        if (!(this.getWarriors().get(0) instanceof Lancer)) {
            var lancer = this.getWarriors().stream()
                    .filter(w -> w.getClass().getSimpleName().equalsIgnoreCase("lancer"))
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new);

            this.getWarriors().remove(lancer);
            this.getWarriors().add(0, new Lancer());
        }
    }

    public void moveUnits() {
        if (this.isWarlordInArmy()) {
            moveWarlordToTheEndOFArmy();
            if (this.isLancerInArmy()) {
                moveLancerToFrontOFArmy();
            }
            if (this.isHealerInArmy()) {
                moveHealerAfterLancerInArmy();
            }
        }
    }

    public static void main(String[] args) {
        Army army = new Army();
        army.addUnits(Warrior::new, 2);
        army.addUnits(Warlord::new, 1);
        army.addUnits(Healer::new, 3);
        army.addUnits(Lancer::new, 3);
        army.addUnits(Knight::new, 1);
        /*army.moveLancerToFrontOFArmy();
        army.moveHealerAfterLancerOFArmy();
        army.moveWarlordToTheEndOFArmy();*/
        army.moveUnits();
        army.getWarriors().forEach(v -> System.out.println(v.toString()));
        //System.out.println(army.getWarriors().toString());
        /*var lancerQuantity = army.getWarriors().stream()
                .filter(w -> w.getClass().getSimpleName().equalsIgnoreCase("lancer"))
                .count();
        System.out.println(lancerQuantity);
        System.out.println(army.isWarlordInArmy());
        System.out.println(army.isLancerInArmy());
        System.out.println(army.isHealerInArmy());*/
        //Warlord warlord = new Warlord();
        //System.out.println(warlord.getClass().getSimpleName());
    }
}

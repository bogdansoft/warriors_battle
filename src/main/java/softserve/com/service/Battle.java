package softserve.com.service;

import softserve.com.model.entities.Army;
import softserve.com.model.interfaces.CanFight;
import softserve.com.model.entities.Warrior;

public class Battle {
    private Battle() {
    }

    public static boolean fight(Warrior warrior1, Warrior warrior2) {
        while (warrior1.isAlive() && warrior2.isAlive()) {
            warrior1.hit(warrior2);
            if (warrior2.isAlive()) {
                warrior2.hit(warrior1);
            }
        }
        return warrior1.isAlive();
    }

    public static boolean fight(CanFight warrior1, CanFight warrior2) {
        while (warrior1.isAlive() && warrior2.isAlive()) {
            warrior1.hit(warrior2);
            if (warrior2.isAlive()) {
                warrior2.hit(warrior1);
            }
        }
        return warrior1.isAlive();
    }

    public static boolean fight(Army attackerArmy, Army defenderArmy) {
        int lengthFirstArmy = attackerArmy.getWarriors().size() - 1;
        int lengthSecondArmy = defenderArmy.getWarriors().size() - 1;

        int cursorFirstArmy = 0;
        int cursorSecondArmy = 0;

        var warriorAttacker = attackerArmy.getWarriors().get(lengthFirstArmy);
        var warriorDefender = defenderArmy.getWarriors().get(lengthSecondArmy);

        while (warriorAttacker.isUnitAlive() && warriorDefender.isUnitAlive()) {
            if (fight((Warrior) attackerArmy.getWarriors().get(cursorFirstArmy),
                    (Warrior) defenderArmy.getWarriors().get(cursorSecondArmy))) {
                cursorSecondArmy++;
            } else {
                cursorFirstArmy++;
            }
        }
        return warriorAttacker.isUnitAlive();
    }
}

package softserve.com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import softserve.com.model.entities.Army;
import softserve.com.model.entities.Warrior;

public class Battle {
    private static final Logger LOGGER = LoggerFactory.getLogger(Battle.class);
    public static int count;

    private Battle() {
    }

    public static boolean fight(Warrior warrior1, Warrior warrior2) {
        count = 0;
        LOGGER.debug("Battle between {} and {} have been started, health of first = {}  health of second = {}",
                warrior1.getClass().getSimpleName(),
                warrior2.getClass().getSimpleName(),
                warrior1.getHealth(),
                warrior2.getHealth()
        );

        while (warrior1.isAlive() && warrior2.isAlive()) {
            warrior1.hit(warrior2);
            ++count;
            if (warrior2.isAlive()) {
                warrior2.hit(warrior1);
            }
        }
        LOGGER.debug("Battle between {} and {} have been completed, health after the fight of first = {}  health after the fight of second = {}",
                warrior1.getClass().getSimpleName(),
                warrior2.getClass().getSimpleName(),
                warrior1.getHealth(),
                warrior2.getHealth()
        );

        return warrior1.isAlive();
    }

    public static boolean fight(Army attackerArmy, Army defenderArmy) {
        LOGGER.debug("Armies battle have been started");
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
        LOGGER.debug("Armies battle have been completed");
        return warriorAttacker.isUnitAlive();
    }

}

package softserve.com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import softserve.com.model.entities.Army;
import softserve.com.model.entities.Warrior;

public class Battle {
    private static final Logger LOGGER = LoggerFactory.getLogger(Battle.class);

    private Battle() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static boolean fight(Warrior warrior1, Warrior warrior2) {

        LOGGER.debug("Battle between {} and {} have been started, health of first = {}  health of second = {}",
                warrior1.getClass().getSimpleName(),
                warrior2.getClass().getSimpleName(),
                warrior1.getHealth(),
                warrior2.getHealth()
        );

        while (warrior1.isAlive() && warrior2.isAlive()) {

            LOGGER.debug("Before hit warrior {} has health {}",
                    warrior2.getClass().getSimpleName(),
                    warrior2.getHealth()
            );

            warrior1.hit(warrior2);

            LOGGER.debug("After hit warrior {} has health {}",
                    warrior2.getClass().getSimpleName(),
                    warrior2.getHealth()
            );
            LOGGER.debug("Before hit warrior {} has health {}",
                    warrior1.getClass().getSimpleName(),
                    warrior1.getHealth()
            );
            if (warrior2.isAlive()) {
                warrior2.hit(warrior1);
            }
            LOGGER.debug("After hit warrior {} has health {}",
                    warrior1.getClass().getSimpleName(),
                    warrior1.getHealth()
            );
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

    public static boolean straightFight(Army attackers, Army defenders) {
        LOGGER.debug("Straight fight has been started");
        var currentWarriorAttacker = attackers.getWarriors().get(0);
        var currentWarriorDefender = defenders.getWarriors().get(0);

        if (currentWarriorAttacker != null && currentWarriorDefender != null) {
            var resultFight = fight((Warrior) currentWarriorAttacker, (Warrior) currentWarriorDefender);
            if (resultFight) {
                defenders.getWarriors().removeIf(w -> !w.isUnitAlive());
            } else {
                attackers.getWarriors().removeIf(w -> !w.isUnitAlive());
            }
        }

        LOGGER.debug("Straight fight has been completed");
        return attackers.getWarriors().size() > defenders.getWarriors().size();
    }
}

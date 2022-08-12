package softserve.com;

import org.junit.jupiter.api.Test;
import softserve.com.model.entities.*;

import static org.junit.jupiter.api.Assertions.*;
import static softserve.com.service.Battle.fight;

class WarlordTest {

    @Test
    void smokeTest() {
        var ronald = new Warlord();
        var heimdall = new Knight();

        assertFalse(fight(heimdall, ronald));

        var myArmy = new Army();
        myArmy.addUnits(Warlord::new, 1);
        myArmy.addUnits(Warrior::new, 2);
        myArmy.addUnits(Lancer::new, 2);
        myArmy.addUnits(Healer::new, 2);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warlord::new, 3);
        enemyArmy.addUnits(Vampire::new, 1);
        enemyArmy.addUnits(Healer::new, 2);
        enemyArmy.addUnits(Knight::new, 2);

        myArmy.moveUnits();
        enemyArmy.moveUnits();

// you can provide any other interface for testing the order
        assertEquals(Lancer.class, myArmy.getWarriors().get(0).getClass());
        assertEquals(Healer.class, myArmy.getWarriors().get(1).getClass());

// negative index means from the last position, so -1 == last
        assertEquals(Warlord.class, myArmy.getWarriors().get(myArmy.getWarriors().size() - 1).getClass());

        assertEquals(Vampire.class, enemyArmy.getWarriors().get(0).getClass());
        assertEquals(Warlord.class, enemyArmy.getWarriors().get(enemyArmy.getWarriors().size() - 1).getClass());
        assertEquals(Knight.class, enemyArmy.getWarriors().get(enemyArmy.getWarriors().size() - 2).getClass());

//6, not 8, because only 1 Warlord per army could be
        assertEquals(6, enemyArmy.getWarriors().size());

        assertTrue(fight(myArmy, enemyArmy));
    }
}

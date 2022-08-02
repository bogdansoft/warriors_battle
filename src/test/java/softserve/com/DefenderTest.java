package softserve.com;

import org.junit.jupiter.api.Test;
import softserve.com.model.entities.Army;
import softserve.com.model.entities.Defender;
import softserve.com.model.entities.Knight;
import softserve.com.model.entities.Warrior;
import softserve.com.service.Battle;

import static org.junit.jupiter.api.Assertions.*;

class DefenderTest {

    @Test
    void smokeTest() {
        //Given
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();
        var bob = new Defender();
        var rog = new Warrior();
        var mike = new Knight();
        var lancelot = new Defender();

        var myArmy = new Army();
        var enemyArmy = new Army();
        var army3 = new Army();
        var army4 = new Army();

        //When
        myArmy.addUnits(Defender::new, 1);
        enemyArmy.addUnits(Warrior::new, 2);
        army3.addUnits(Warrior::new, 1);
        army3.addUnits(Defender::new, 1);
        army4.addUnits(Warrior::new, 2);

        //Then
        assertAll(
                () -> assertFalse(Battle.fight(bob, mike)),
                () -> assertTrue(Battle.fight(lancelot, rog)),
                () -> assertTrue(Battle.fight(chuck, bruce)),
                () -> assertFalse(Battle.fight(dave, carl)),
                () -> assertTrue(chuck.isAlive()),
                () -> assertFalse(bruce.isAlive()),
                () -> assertTrue(carl.isAlive()),
                () -> assertFalse(dave.isAlive()),
                () -> assertFalse(Battle.fight(carl, mark)),
                () -> assertFalse(carl.isAlive()),
                () -> assertFalse(Battle.fight(myArmy, enemyArmy)),
                () -> assertTrue(Battle.fight(army3, army4))
        );
    }
}

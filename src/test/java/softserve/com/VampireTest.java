package softserve.com;

import org.junit.jupiter.api.Test;
import softserve.com.model.entities.*;
import softserve.com.service.Battle;

import static org.junit.jupiter.api.Assertions.*;

public class VampireTest {
    @Test
    void smokeTest() {
        //Given
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();
        var bob = new Defender();
        var mike = new Knight();
        var rog = new Warrior();
        var lancelot = new Defender();
        var eric = new Vampire();
        var adam = new Vampire();
        var richard = new Defender();
        var ogre = new Warrior();
        var myArmy = new Army();
        var enemyArmy = new Army();
        var army3 = new Army();
        var army4 = new Army();

        //When

        myArmy.addUnits(Defender::new, 2);
        myArmy.addUnits(Vampire::new, 2);
        myArmy.addUnits(Warrior::new, 1);
        enemyArmy.addUnits(Warrior::new, 2);
        enemyArmy.addUnits(Defender::new, 2);
        enemyArmy.addUnits(Vampire::new, 3);
        army3.addUnits(Warrior::new, 1);
        army3.addUnits(Defender::new, 4);
        army4.addUnits(Vampire::new, 3);
        army4.addUnits(Warrior::new, 2);

        //Then
        assertAll(
                () -> assertTrue(Battle.fight(richard, eric)),
                () -> assertFalse(Battle.fight(eric, richard)),
                () -> assertTrue(Battle.fight(chuck, bruce)),
                () -> assertFalse(Battle.fight(dave, carl)),
                () -> assertTrue(chuck.isAlive()),
                () -> assertFalse(bruce.isAlive()),
                () -> assertTrue(carl.isAlive()),
                () -> assertFalse(dave.isAlive()),
                () -> assertFalse(Battle.fight(carl, mark)),
                () -> assertFalse(carl.isAlive()),
                () -> assertFalse(Battle.fight(bob, mike)),
                () -> assertTrue(Battle.fight(lancelot, rog)),
                () -> assertFalse(Battle.fight(ogre, adam)),
                () -> assertFalse(Battle.fight(myArmy, enemyArmy)),
                () -> assertTrue(Battle.fight(army3, army4)));
    }
}

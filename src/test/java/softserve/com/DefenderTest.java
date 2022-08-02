package softserve.com;

import org.junit.jupiter.api.DisplayName;
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

    @Test
    @DisplayName("1.Fight between Defender and Rookie")
    void defenderFightsRookieAndDefenderLifeEqualsSixty() {
        //Given
        var bruce = new Defender();
        var carl = new Rookie();

        //When
        Battle.fight(bruce, carl);
        var result = bruce.getHealth();

        //Then
        assertEquals(60, result);
    }

    @Test
    @DisplayName("2.Fight between Defender and Warrior")
    void defenderFightWarriorAndDefenderWin() {
        //Given
        var lancelot = new Defender();
        var rog = new Warrior();

        //When
        var result = Battle.fight(lancelot, rog);

        //Then
        assertTrue(result);
    }

    @Test
    @DisplayName("3.Battle between two armies")
    void warriorsDefendersArmyFightsWarriorsArmyAndFirstArmyWin() {
        //Given
        var army1 = new Army();
        var army2 = new Army();

        //When
        army1.addUnits(Warrior::new, 5);
        army1.addUnits(Defender::new, 4);
        army1.addUnits(Defender::new, 5);
        army2.addUnits(Warrior::new, 4);
        var result = Battle.fight(army1, army2);

        //Then
        assertTrue(result);
    }

    @Test
    @DisplayName("4.Battle between two armies")
    void warriorsDefendersArmyFightsWarriorsDefendersArmy_AndFirstArmyWin() {
        //Given
        var army1 = new Army();
        var army2 = new Army();

        //When
        army1.addUnits(Warrior::new, 5);
        army1.addUnits(Defender::new, 20);
        army2.addUnits(Warrior::new, 21);
        army1.addUnits(Defender::new, 4);
        var result = Battle.fight(army1, army2);

        //Then
        assertTrue(result);
    }

    @Test
    @DisplayName("5.Battle between two armies")
    void warriorsDefendersArmyFightsWarriorsDefendersArmyAndFirstArmyWin_secondExample() {
        //Given
        var army1 = new Army();
        var army2 = new Army();

        //When
        army1.addUnits(Warrior::new, 10);
        army1.addUnits(Defender::new, 5);
        army2.addUnits(Defender::new, 5);
        army1.addUnits(Warrior::new, 10);
        var result = Battle.fight(army1, army2);

        //Then
        assertTrue(result);
    }

    @Test
    @DisplayName("6.Battle between two armies")
    void givenWarriorsDefendersArmyFightsWarriorsDefendersArmy_thenFirstArmyLose() {
        //Given
        var army1 = new Army();
        var army2 = new Army();

        //When
        army1.addUnits(Warrior::new, 2);
        army1.addUnits(Defender::new, 1);
        army2.addUnits(Warrior::new, 1);
        army1.addUnits(Defender::new, 5);
        var result = Battle.fight(army1, army2);

        //Then
        assertTrue(result);
    }
}

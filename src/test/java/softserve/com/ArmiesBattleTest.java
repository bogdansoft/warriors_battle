package softserve.com;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softserve.com.model.Army;
import softserve.com.model.Knight;
import softserve.com.model.Warrior;
import softserve.com.service.Battle;

import static org.junit.jupiter.api.Assertions.*;

public class ArmiesBattleTest {

    @Test
    void smokeTest() {
        //Given
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();

        var myArmy = new Army();
        var enemyArmy = new Army();
        var army3 = new Army();
        var army4 = new Army();

        //When
        myArmy.addUnits(() -> new Knight(), 3);
        enemyArmy.addUnits(() -> new Warrior(), 3);
        army3.addUnits(() -> new Warrior(), 20);
        army3.addUnits(() -> new Knight(), 5);
        army4.addUnits(() -> new Warrior(), 30);

        //Then
        assertAll(
                () -> assertFalse(Battle.fight(army3, army4)),
                () -> assertTrue(Battle.fight(myArmy, enemyArmy)),
                () -> assertTrue(Battle.fight(chuck, bruce)),
                () -> assertFalse(Battle.fight(dave, carl)),
                () -> assertTrue(chuck.isAlive()),
                () -> assertFalse(bruce.isAlive()),
                () -> assertTrue(carl.isAlive()),
                () -> assertFalse(dave.isAlive()),
                () -> assertFalse(Battle.fight(carl, mark)),
                () -> assertFalse(carl.isAlive()));
    }

    @Test
    @DisplayName("1.Battle between army of 1 Warrior versus army of 2 Warriors")
    void warriorsArmyFightWarriorsArmyAndFirstArmyLose() {
        //Given
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(() -> new Warrior(), 1);
        army2.addUnits(() -> new Warrior(), 2);

        //When
        var result = Battle.fight(army1, army2);

        //Then
        assertFalse(result);
    }

    @Test
    @DisplayName("2.Battle between army of 2 Warriors versus army of 3 Warriors")
    void warriorsArmyFightWarriorsArmyAndSmallerArmyLose_SecondExample() {
        //Given
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(() -> new Warrior(), 2);
        army2.addUnits(() -> new Warrior(), 3);

        //When
        var result = Battle.fight(army1, army2);

        //Then
        assertFalse(result);
    }

    @Test
    @DisplayName("3.Battle between army of 5 Warriors versus army of 7 Warriors")
    void warriorsArmyFightWarriorsArmyAndSmallerArmyLose_ThirdExample() {
        //Given
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(() -> new Warrior(), 5);
        army2.addUnits(() -> new Warrior(), 7);

        //When
        var result = Battle.fight(army1, army2);

        //Then
        assertFalse(result);
    }

    @Test
    @DisplayName("4.Battle between army of 20 Warriors versus army of 21 Warriors")
    void warriorsArmyFightWarriorsArmyAndFirstArmyWin() {
        //Given
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(() -> new Warrior(), 20);
        army2.addUnits(() -> new Warrior(), 21);

        //When
        var result = Battle.fight(army1, army2);

        //Then
        assertTrue(result);
    }

    @Test
    @DisplayName("5.Battle between army of 10 Warriors versus army of 11 Warriors")
    void warriorsArmyFightWarriorsArmyAndFirstArmyWin_SecondExample() {
        //Given
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(() -> new Warrior(), 10);
        army2.addUnits(() -> new Warrior(), 11);

        //When
        var result = Battle.fight(army1, army2);

        //Then
        assertTrue(result);
    }

    @Test
    @DisplayName("6.Battle between army of 11 Warriors versus army of 7 Warriors")
    void warriorsArmyFightWarriorsArmyAndFirstArmyWin_ThirdExample() {
        //Given
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(() -> new Warrior(), 11);
        army2.addUnits(() -> new Warrior(), 7);

        //When
        var result = Battle.fight(army1, army2);

        //Then
        assertTrue(result);
    }

    @Test
    @DisplayName("7.Battle between army of 3 Knights and army of 3 Warriors")
    void knightsArmyFightWarriorsArmyAndArmyOfKnightsWin() {
        //Given
        var myArmy = new Army();
        var enemyArmy = new Army();
        myArmy.addUnits(() -> new Knight(), 3);
        enemyArmy.addUnits(() -> new Warrior(), 3);

        //When
        var result = Battle.fight(myArmy, enemyArmy);

        //Then
        assertTrue(result);
    }
}

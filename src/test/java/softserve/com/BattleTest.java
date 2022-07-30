package softserve.com;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {
    @Test
    void smokeTest() {
        //Given
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();

        //When
        var res1 = Battle.fight(chuck, bruce);
        var res2 = Battle.fight(dave, carl);

        //Then
        assertAll(
                () -> assertTrue(res1),
                () -> assertFalse(res2),
                () -> assertTrue(chuck.isAlive()),
                () -> assertFalse(bruce.isAlive()),
                () -> assertTrue(carl.isAlive()),
                () -> assertFalse(dave.isAlive()));
    }

    @Test
    @DisplayName("Knight hits warrior and warrior`s health is reduced by 7")
    void givenKnightHitsWarrior_WarriorHealthIsReducedToSeven() {
        //Given
        Warrior warrior = new Warrior();
        Knight knight = new Knight();

        //When
        knight.hit(warrior);
        int actualHealth = warrior.getHealth();
        int expectedHealth = Warrior.INITIAL_HEALTH - Knight.ATTACK;

        //Then
        assertEquals(expectedHealth, actualHealth);
    }

    @Test
    @DisplayName("1.Warrior fighting against knight, knight should win")
    void warriorHitsFirstKnightShouldWin() {
        //Given
        Warrior carl = new Warrior();
        Knight jim = new Knight();

        //When
        var result = Battle.fight(carl, jim);

        //Then
        assertFalse(result);
    }

    @Test
    @DisplayName("2.Knight fighting against warrior, knight should win")
    void knightHitsFirstAndShouldWin() {
        //Given
        Warrior slevin = new Warrior();
        Knight ramon = new Knight();

        //When
        var result = Battle.fight(ramon, slevin);

        //Then
        assertTrue(result);
    }

    @Test
    @DisplayName("3.Warrior fighting against warrior, warrior Bob should win")
    void warriorAgainstWarrior() {
        //Given
        Warrior bob = new Warrior();
        Warrior mars = new Warrior();

        //When
        var result = Battle.fight(bob, mars);

        //Then
        assertTrue(result);
    }

    @Test
    @DisplayName("4.Knight fighting against warrior, knight should be alive")
    void knightShouldBeAlive() {
        //Given
        Warrior godkiller = new Warrior();
        Knight zeus = new Knight();

        //When
        Battle.fight(zeus, godkiller);
        var zeusIsAlive = zeus.isAlive();

        //Then
        assertTrue(zeusIsAlive);
    }

    @Test
    @DisplayName("5.Warrior fighting against warrior, warrior hits second and should be dead")
    void warriorShouldBeDead() {
        //Given
        Warrior husband = new Warrior();
        Warrior wife = new Warrior();

        //When
        Battle.fight(husband, wife);
        var wifeIsAlive = wife.isAlive();

        //Then
        assertFalse(wifeIsAlive);
    }

    @Test
    @DisplayName("6.Knight fighting against warrior, knight should be alive after warrior hits first")
    void knightShouldBeAliveAfterWarriorHitsFirst() {
        //Given
        Warrior dragon = new Warrior();
        Knight knight = new Knight();

        //When
        Battle.fight(dragon, knight);
        var knightIsAlive = knight.isAlive();

        //Then
        assertTrue(knightIsAlive);
    }

    @Test
    @DisplayName("7.Knight fighting against 2 warriors, knight should loose")
    void knightShouldLooseAfterBattleWithTwoWarriors() {
        //Given
        Warrior unit_1 = new Warrior();
        Knight unit_2 = new Knight();
        Warrior unit_3 = new Warrior();

        //When
        Battle.fight(unit_1, unit_2);
        var result = Battle.fight(unit_2, unit_3);

        //Then
        assertFalse(result);
    }
}
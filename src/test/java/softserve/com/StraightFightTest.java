package softserve.com;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import softserve.com.model.entities.*;
import softserve.com.service.Battle;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static softserve.com.service.Battle.fight;
import static softserve.com.service.Battle.straightFight;

class StraightFightTest {

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
        var freelancer = new Lancer();
        var vampire = new Vampire();
        var priest = new Healer();
        var myArmy = new Army();
        var enemyArmy = new Army();
        var army3 = new Army();
        var army4 = new Army();
        var army5 = new Army();
        var army6 = new Army();

        //When
        myArmy.addUnits(Defender::new, 2);
        myArmy.addUnits(Healer::new, 1);
        myArmy.addUnits(Vampire::new, 2);
        myArmy.addUnits(Lancer::new, 2);
        myArmy.addUnits(Healer::new, 1);
        myArmy.addUnits(Warrior::new, 1);

        enemyArmy.addUnits(Warrior::new, 2);
        enemyArmy.addUnits(Lancer::new, 4);
        enemyArmy.addUnits(Healer::new, 1);
        enemyArmy.addUnits(Defender::new, 2);
        enemyArmy.addUnits(Vampire::new, 3);
        enemyArmy.addUnits(Healer::new, 1);

        army3.addUnits(Warrior::new, 1);
        army3.addUnits(Lancer::new, 1);
        army3.addUnits(Healer::new, 1);
        army3.addUnits(Defender::new, 2);

        army4.addUnits(Vampire::new, 3);
        army4.addUnits(Warrior::new, 1);
        army4.addUnits(Healer::new, 1);
        army4.addUnits(Lancer::new, 2);

        army5.addUnits(Warrior::new, 10);

        army6.addUnits(Warrior::new, 6);
        army6.addUnits(Lancer::new, 5);

        //Then
        assertAll(
                () -> assertTrue(fight(chuck, bruce)),
                () -> assertFalse(fight(dave, carl)),
                () -> assertTrue(chuck.isAlive()),
                () -> assertFalse(bruce.isAlive()),
                () -> assertTrue(carl.isAlive()),
                () -> assertFalse(dave.isAlive()),
                () -> assertFalse(fight(carl, mark)),
                () -> assertFalse(carl.isAlive()),
                () -> assertFalse(fight(bob, mike)),
                () -> assertTrue(fight(lancelot, rog)),
                () -> assertFalse(fight(eric, richard)),
                () -> assertTrue(fight(ogre, adam)),
                () -> assertTrue(fight(freelancer, vampire)),
                () -> assertTrue(freelancer.isAlive()),
                () -> assertEquals(20, freelancer.getHealth()),
                () -> priest.heal(freelancer),
                () -> assertEquals(22, freelancer.getHealth()),
                () -> assertFalse(fight(myArmy, enemyArmy)),
                () -> assertFalse(fight(army3, army4)),
                () -> assertFalse(Battle.straightFight(army5, army6))
        );
    }

    private static Stream<Arguments> provideWarriorTypes() {
        //Given
        return Stream.of(
                Arguments.of(new Warrior(), new Knight(), false),
                Arguments.of(new Knight(), new Warrior(), true),
                Arguments.of(new Warrior(), new Warrior(), true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideWarriorTypes")
    @DisplayName("Smoke test of battles between warriors")
    void smokeTestWarriorBattles(Warrior warrior1, Warrior warrior2, boolean expected) {
        //When
        var actual = fight(warrior1, warrior2);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Is knight alive")
    void isKnightAlive() {
        //Given
        Warrior warrior = new Warrior();
        Knight knight = new Knight();

        //When
        fight(knight, warrior);
        var expected = knight.isAlive();

        //Then
        assertTrue(expected);
    }

    @Test
    @DisplayName("Is second warrior alive")
    void isSecondWarriorAlive() {
        //Given
        Warrior warrior1 = new Warrior();
        Warrior warrior2 = new Warrior();

        //When
        fight(warrior1, warrior2);
        var expected = warrior2.isAlive();

        //Then
        assertFalse(expected);
    }

    @Test
    @DisplayName("Is second knight alive")
    void isSecondKnightAlive() {
        //Given
        Warrior warrior = new Warrior();
        Knight knight = new Knight();

        //When
        fight(warrior, knight);
        var expected = knight.isAlive();

        //Then
        assertTrue(expected);
    }

    @Test
    @DisplayName("Knight against two warriors")
    void knightAgainstTwoWarriors() {
        //Given
        Warrior warrior1 = new Warrior();
        Warrior warrior2 = new Warrior();
        Knight knight = new Knight();

        //When
        fight(warrior1, knight);
        var expected = fight(knight, warrior2);

        //Then
        assertFalse(expected);
    }

    @Test
    @DisplayName("Defender health equals initial health")
    void defenderHealth() {
        //Given
        Defender defender = new Defender();
        Rookie rookie = new Rookie();

        //When
        fight(defender, rookie);
        var actual = defender.getHealth();

        //Then
        assertEquals(60, actual);
    }

    @Test
    @DisplayName("Defender against rookie and warrior")
    void defenderAgainstRookieAndWarrior() {
        //Given
        Defender defender = new Defender();
        Rookie rookie = new Rookie();
        Warrior warrior = new Warrior();

        //When
        fight(defender, rookie);
        var expected = fight(defender, warrior);

        //Then
        assertTrue(expected);
    }

    private static Stream<Arguments> provideArmiesForStraightFight() {
        //Given
        return Stream.of(
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 5)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 5),
                        false
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 7)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4),
                        false
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 7)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Healer::new, 1)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4),
                        false
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 4)
                                .addUnits(Warrior::new, 3)
                                .addUnits(Healer::new, 1)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Knight::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Vampire::new, 2)
                                .addUnits(Lancer::new, 4),
                        true
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideArmiesForStraightFight")
    @DisplayName("Smoke test of straight fight between armies")
    void smokeTestWarriorArmies(Army army1, Army army2, boolean expected) {
        //When
        var actual = straightFight(army1, army2);

        //Then
        assertEquals(expected, actual);
    }
}

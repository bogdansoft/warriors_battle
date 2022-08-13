package softserve.com;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import softserve.com.model.entities.*;
import softserve.com.model.entities.weapons.Shield;
import softserve.com.model.entities.weapons.Sword;
import softserve.com.service.Battle;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static softserve.com.service.Battle.fight;
import static softserve.com.service.Battle.straightFight;

class WarlordTest {

    @Test
    void smokeTest() {
        //Given
        var ronald = new Warlord();
        var heimdall = new Knight();

        var myArmy = new Army();
        var enemyArmy = new Army();

        //When
        myArmy.addUnits(Warlord::new, 1);
        myArmy.addUnits(Warrior::new, 2);
        myArmy.addUnits(Lancer::new, 2);
        myArmy.addUnits(Healer::new, 2);


        enemyArmy.addUnits(Warlord::new, 3);
        enemyArmy.addUnits(Vampire::new, 1);
        enemyArmy.addUnits(Healer::new, 2);
        enemyArmy.addUnits(Knight::new, 2);

        myArmy.moveUnits();
        enemyArmy.moveUnits();

        //Then
        assertAll(
                () -> assertFalse(fight(heimdall, ronald)),
                () -> assertEquals(Lancer.class, myArmy.getWarriors().get(0).getClass()),
                () -> assertEquals(Healer.class, myArmy.getWarriors().get(1).getClass()),
                () -> assertEquals(Warlord.class, myArmy.getWarriors().get(myArmy.getWarriors().size() - 1).getClass()),
                () -> assertEquals(Vampire.class, enemyArmy.getWarriors().get(0).getClass()),
                () -> assertEquals(Warlord.class, enemyArmy.getWarriors().get(enemyArmy.getWarriors().size() - 1).getClass()),
                () -> assertEquals(Knight.class, enemyArmy.getWarriors().get(enemyArmy.getWarriors().size() - 2).getClass()),
                () -> assertEquals(6, enemyArmy.getWarriors().size()),
                () -> assertTrue(fight(myArmy, enemyArmy)));
    }

    @Test
    @DisplayName("Warlord vs two warriors, warlord should win")
    void warlordVsTwoWarriors() {
        //Given
        var warlord = new Warlord();
        var warrior1 = new Warrior();
        var warrior2 = new Warrior();

        //When
        fight(warlord, warrior1);
        var result = fight(warlord, warrior2);

        //Then
        assertTrue(result);
    }

    private static Stream<Arguments> provideWarriorTypes() {
        //Given
        return Stream.of(
                Arguments.of(new Defender(), new Warlord(), false),
                Arguments.of(new Vampire(), new Warlord(), false),
                Arguments.of(new Knight(), new Warlord(), false),
                Arguments.of(new Warrior(), new Warlord(), false),
                Arguments.of(new Healer(), new Warlord(), false),
                Arguments.of(new Lancer(), new Warlord(), false),
                Arguments.of(new Rookie(), new Warlord(), false),
                Arguments.of(new Warlord(), new Lancer(), true),
                Arguments.of(new Warlord(), new Warrior(), true),
                Arguments.of(new Warlord(), new Knight(), true),
                Arguments.of(new Warlord(), new Vampire(), true),
                Arguments.of(new Warlord(), new Defender(), true),
                Arguments.of(new Warlord(), new Healer(), true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideWarriorTypes")
    @DisplayName("Smoke test of battles between warriors")
    void smokeTestWarriorBattles(Warrior warrior1, Warrior warrior2, boolean expected) {
        //When
        var actual = Battle.fight(warrior1, warrior2);

        //Then
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideArmiesForStraightBattles() {
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
    @MethodSource("provideArmiesForStraightBattles")
    @DisplayName("Smoke test of battles between armies")
    void smokeTestWarriorArmiesForStraightBattle(Army army1, Army army2, boolean expected) {
        //When
        var actual = straightFight(army1, army2);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Straight fight of two aries with weapons")
    void straightFightOfTwoArmiesWithWeapons() {
        //Given
        var army_1 = new Army();
        var army_2 = new Army();
        army_1.addUnits(Warrior::new, 2);
        army_1.addUnits(Lancer::new, 3);
        army_1.addUnits(Defender::new, 1);
        army_1.addUnits(Warlord::new, 1);
        army_2.addUnits(Warlord::new, 5);
        army_2.addUnits(Vampire::new, 1);
        army_2.addUnits(Rookie::new, 1);
        army_2.addUnits(Knight::new, 1);
        army_1.equipWarriorAtPosition(0, new Sword());
        army_2.equipWarriorAtPosition(0, new Shield());

        //When
        army_1.moveUnits();
        army_2.moveUnits();

        var result = straightFight(army_1, army_2);

        //Then
        assertTrue(result);
    }

    private static Stream<Arguments> provideArmiesForBattles() {
        //Given
        return Stream.of(
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 1),
                        new Army()
                                .addUnits(Warrior::new, 2),
                        false
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 3),
                        false
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 5),
                        new Army()
                                .addUnits(Warrior::new, 7),
                        false
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 20),
                        new Army()
                                .addUnits(Warrior::new, 21),
                        true
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 10),
                        new Army()
                                .addUnits(Warrior::new, 11),
                        true
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 11),
                        new Army()
                                .addUnits(Warrior::new, 7),
                        true
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 5)
                                .addUnits(Defender::new, 5)
                                .addUnits(Defender::new, 4),
                        new Army()
                                .addUnits(Warrior::new, 4),
                        true
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Defender::new, 5)
                                .addUnits(Warrior::new, 20)
                                .addUnits(Defender::new, 4),
                        new Army()
                                .addUnits(Warrior::new, 21),
                        true
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 10)
                                .addUnits(Defender::new, 5)
                                .addUnits(Defender::new, 10),
                        new Army()
                                .addUnits(Warrior::new, 5),
                        true
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Defender::new, 2)
                                .addUnits(Warrior::new, 1)
                                .addUnits(Defender::new, 1),
                        new Army()
                                .addUnits(Warrior::new, 5),
                        false
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Defender::new, 5)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Warrior::new, 7),
                        new Army()
                                .addUnits(Warrior::new, 6)
                                .addUnits(Defender::new, 6)
                                .addUnits(Vampire::new, 6),
                        true
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Defender::new, 2)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 3),
                        false
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Defender::new, 11)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 13),
                        true
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Defender::new, 9)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 8),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 13),
                        true
                ),
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
                        true
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
                        true
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 1)
                                .addUnits(Warrior::new, 3)
                                .addUnits(Healer::new, 1)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Knight::new, 2),
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
                                .addUnits(Warlord::new, 1)
                                .addUnits(Warrior::new, 2)
                                .addUnits(Lancer::new, 2)
                                .addUnits(Healer::new, 2),
                        new Army()
                                .addUnits(Warlord::new, 1)
                                .addUnits(Vampire::new, 1)
                                .addUnits(Healer::new, 2)
                                .addUnits(Knight::new, 2),
                        true
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 2)
                                .addUnits(Lancer::new, 2)
                                .addUnits(Defender::new, 1)
                                .addUnits(Warlord::new, 3),
                        new Army()
                                .addUnits(Warlord::new, 2)
                                .addUnits(Vampire::new, 1)
                                .addUnits(Healer::new, 5)
                                .addUnits(Knight::new, 2),
                        true
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideArmiesForBattles")
    @DisplayName("Smoke test of battles between armies")
    void smokeTestWarriorArmies(Army army1, Army army2, boolean expected) {
        //When
        var actual = fight(army1, army2);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Fight of two armies with weapons")
    void fightOfTwoArmiesWithWeapons() {
        //Given
        var army_1 = new Army();
        var army_2 = new Army();
        army_1.addUnits(Warrior::new, 2);
        army_1.addUnits(Lancer::new, 3);
        army_1.addUnits(Defender::new, 1);
        army_1.addUnits(Warlord::new, 4);
        army_2.addUnits(Warlord::new, 1);
        army_2.addUnits(Vampire::new, 1);
        army_2.addUnits(Rookie::new, 1);
        army_2.addUnits(Knight::new, 1);
        army_1.equipWarriorAtPosition(0, new Sword());
        army_2.equipWarriorAtPosition(0, new Shield());

        //When
        army_1.moveUnits();
        army_2.moveUnits();

        var result = fight(army_1, army_2);

        //Then
        assertTrue(result);
    }
}

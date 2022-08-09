package softserve.com;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import softserve.com.model.entities.*;
import softserve.com.service.Battle;

import static org.junit.jupiter.api.Assertions.*;
import static softserve.com.service.Battle.fight;

public class HealerTest {

    @Test
    @DisplayName("Smoke test between knight and warrior & healer")
    void knightVsWarriorAndHealer() {
        //Given
        var knight = new Knight();
        var warrior = new Warrior();
        var healer = new Healer();

        //When
        var firstResult = Battle.fight(knight, warrior);
        var secondResult = Battle.fight(knight, healer);

        //Then
        assertTrue(firstResult);
        assertTrue(secondResult);
    }

    @Test
    @DisplayName("Smoke test")
    void smokeTest() {
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

        assertTrue(Battle.fight(chuck, bruce));
        assertFalse(Battle.fight(dave, carl));
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
        assertFalse(Battle.fight(carl, mark));
        assertFalse(carl.isAlive());
        assertFalse(Battle.fight(bob, mike));
        assertTrue(Battle.fight(lancelot, rog));
        assertFalse(Battle.fight(eric, richard));
        assertTrue(Battle.fight(ogre, adam));
        assertTrue(Battle.fight(freelancer, vampire));
        assertTrue(freelancer.isAlive());
        assertEquals(20, freelancer.getHealth());
        priest.heal(freelancer);
        assertEquals(22, freelancer.getHealth());

        var my_army = new Army();
        my_army.addUnits(Defender::new, 2);
        my_army.addUnits(Healer::new, 1);
        my_army.addUnits(Vampire::new, 2);
        my_army.addUnits(Lancer::new, 2);
        my_army.addUnits(Healer::new, 1);
        my_army.addUnits(Warrior::new, 1);

        var enemy_army = new Army();
        enemy_army.addUnits(Warrior::new, 2);
        enemy_army.addUnits(Lancer::new, 4);
        enemy_army.addUnits(Healer::new, 1);
        enemy_army.addUnits(Defender::new, 2);
        enemy_army.addUnits(Vampire::new, 3);
        enemy_army.addUnits(Healer::new, 1);

        var army_3 = new Army();
        army_3.addUnits(Warrior::new, 1);
        army_3.addUnits(Lancer::new, 1);
        army_3.addUnits(Healer::new, 1);
        army_3.addUnits(Defender::new, 2);

        var army_4 = new Army();
        army_4.addUnits(Vampire::new, 3);
        army_4.addUnits(Warrior::new, 1);
        army_4.addUnits(Healer::new, 1);
        army_4.addUnits(Lancer::new, 2);

        assertFalse(Battle.fight(my_army, enemy_army));
        assertFalse(Battle.fight(army_3, army_4));
    }

    @ParameterizedTest
    @MethodSource("provideWarriorAfterBattle")
    @DisplayName("Smoke test of battles between warriors")
    void smokeTestWarriorsAfterBattles(Warrior warrior, boolean expected) {
        //When
        var actual = warrior.isAlive();

        //Then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideWarriorsForBattle")
    @DisplayName("Smoke test of battles between warriors")
    void smokeTestWarriorBattles(Warrior warrior1, Warrior warrior2, boolean expected) {
        //When
        var actual = fight(warrior1, warrior2);

        //Then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideArmies")
    @DisplayName("Smoke test of battles between armies")
    void smokeTestWarriorArmies(Army army1, Army army2, boolean expected) {
        //When
        var actual = fight(army1, army2);

        //Then
        assertEquals(expected, actual);
    }
}

package softserve.com;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import softserve.com.model.entities.Army;
import softserve.com.model.entities.Warrior;
import softserve.com.service.Battle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealerTest {

    @ParameterizedTest
    @MethodSource("provideWarriorTypes")
    @DisplayName("Smoke test of battles between warriors")
    void smokeTestWarriorBattles(Warrior warrior1, Warrior warrior2, boolean expected) {
        //When
        var actual = Battle.fight(warrior1, warrior2);

        //Then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideArmies")
    @DisplayName("Smoke test of battles between armies")
    void smokeTestWarriorArmies(Army army1, Army army2, boolean expected) {
        //When
        var actual = Battle.fight(army1, army2);

        //Then
        assertEquals(expected, actual);
    }
}

package softserve.com;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softserve.com.model.day_time.Sun;
import softserve.com.model.entities.Werewolf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WerewolfTest {
    private static Sun sun;

    @BeforeAll
    static void beforeAll() {
        sun = Sun.getInstance();
    }

    @Test
    @DisplayName("Get day from day type")
    void getDayType() {
        //When
        var result = sun.getDayType().getDay(1);

        //Then
        assertTrue(result.equalsIgnoreCase("day"));
    }

    @Test
    @DisplayName("Get night from day type")
    void getNightType() {
        //When
        var result = sun.getDayType().getDay(2);

        //Then
        assertTrue(result.equalsIgnoreCase("night"));
    }

    @Test
    @DisplayName("Get defender type")
    void getDefenderType() {
        //Given
        var werewolf = new Werewolf(1);

        //When
        var result = werewolf.getWarrior().getClass().getSimpleName();


        //Then
        assertEquals("Defender", result);
    }

    @Test
    @DisplayName("Get vampire type")
    void getVampireType() {
        //Given
        var werewolf = new Werewolf(2);

        //When
        var result = werewolf.getWarrior().getClass().getSimpleName();


        //Then
        assertEquals("Vampire", result);
    }
}

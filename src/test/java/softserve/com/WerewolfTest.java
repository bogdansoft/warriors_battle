package softserve.com;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softserve.com.model.day_time.Sun;
import softserve.com.model.entities.Defender;
import softserve.com.model.entities.Warrior;
import softserve.com.model.entities.Werewolf;

import static org.junit.jupiter.api.Assertions.*;
import static softserve.com.service.Battle.fight;

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
        var result = werewolf.getState().getClass().getSimpleName();

        //Then
        assertEquals("Defender", result);
    }

    @Test
    @DisplayName("Get vampire type")
    void getVampireType() {
        //Given
        var werewolf = new Werewolf(2);

        //When
        var result = werewolf.getState().getClass().getSimpleName();

        //Then
        assertEquals("Vampire", result);
    }

    @Test
    @DisplayName("Get vampire type and fight against warrior, vampire should win")
    void getVampireVsWarriorAndVampireWin() {
        //Given
        var werewolfVampire = new Werewolf(2);
        var defender = new Defender();

        //When
        var result = werewolfVampire.getState().getClass().getSimpleName();
        var getHealth = werewolfVampire.getState().getHealth();
        var getAttack = werewolfVampire.getState().getAttack();
        var fightResult = fight(defender,(Warrior) werewolfVampire.getState());

        //Then
        assertEquals("Vampire", result);
        assertEquals(40, getHealth);
        assertEquals(4, getAttack);
        assertTrue(fightResult);
    }
}

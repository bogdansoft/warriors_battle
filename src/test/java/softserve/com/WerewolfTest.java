package softserve.com;

import org.junit.jupiter.api.*;
import softserve.com.model.day_time.Sun;
import softserve.com.model.entities.*;
import softserve.com.model.entities.weapons.Katana;

import static org.junit.jupiter.api.Assertions.*;
import static softserve.com.service.Battle.*;

class WerewolfTest {
    private Sun sun;

    @BeforeEach
    void beforeEach() {
        sun = Sun.getInstance();
    }

    @AfterEach
    void afterEach() {
        sun = null;
    }

    @Test
    @DisplayName("Get day from day type")
    void getDayType() {
        //When
        var result = sun.getDayType().setDayTime(1);

        //Then
        assertTrue(result.equalsIgnoreCase("day"));
    }

    @Test
    @DisplayName("Get night from day type")
    void getNightType() {
        //When
        var result = sun.getDayType().setDayTime(2);

        //Then
        assertTrue(result.equalsIgnoreCase("night"));
    }

    @Test
    @DisplayName("Get vampire type and his values")
    void getVampireValues() {
        //Given
        var werewolfVampire = new Werewolf(2);

        //When
        var result = werewolfVampire.getState().getClass().getSimpleName();
        var getHealth = werewolfVampire.getHealth();
        var getAttack = werewolfVampire.getAttack();
        var getVampirism = werewolfVampire.getVampirismLevel();

        //Then
        assertAll(
                () -> assertEquals("Vampire", result),
                () -> assertEquals(40, getHealth),
                () -> assertEquals(4, getAttack),
                () -> assertEquals(50, getVampirism)
        );
    }

    @Test
    @DisplayName("Get defender type and his values")
    void getDefenderValues() {
        //Given
        var werewolfDefender = new Werewolf(1);

        //When
        var result = werewolfDefender.getState().getClass().getSimpleName();
        var getHealth = werewolfDefender.getHealth();
        var getAttack = werewolfDefender.getAttack();
        var getDefence = werewolfDefender.getDefence();

        //Then
        assertAll(
                () -> assertEquals("Defender", result),
                () -> assertEquals(60, getHealth),
                () -> assertEquals(3, getAttack),
                () -> assertEquals(2, getDefence)
        );
    }

    @Test
    @DisplayName("Battle test")
    void getBattleTest() {
        //Given
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Warrior::new, 4);
        army2.addUnits(Werewolf::new, 5);

        //When
        var result = fight(army1, army2);

        //Then
        assertFalse(result);
    }

    @Test
    @DisplayName("Smoke test with weapons")
    void weaponsBattleTest() {
        //Given
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Warrior::new, 1);
        army2.addUnits(Werewolf::new, 2);
        army1.equipWarriorAtPosition(0, new Katana());
        army2.equipWarriorAtPosition(0, new Katana());

        //When
        var result = fight(army1, army2);

        //Then
        assertFalse(result);
    }

    @Test
    @DisplayName("Smoke test with weapons in straight fight")
    void weaponsStraightBattleTest() {
        //Given
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Warrior::new, 1);
        army2.addUnits(Werewolf::new, 2);
        army1.equipWarriorAtPosition(0, new Katana());
        army2.equipWarriorAtPosition(0, new Katana());

        //When
        var result = straightFight(army1, army2);

        //Then
        assertFalse(result);
    }
}
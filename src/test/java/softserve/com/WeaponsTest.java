package softserve.com;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import softserve.com.model.entities.*;
import softserve.com.model.entities.weapons.*;
import softserve.com.service.Battle;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static softserve.com.service.Battle.fight;
import static softserve.com.service.Battle.straightFight;

class WeaponsTest {
    @Test
    void smokeTest() {
        var ogre = new Warrior();
        var lancelot = new Knight();
        var richard = new Defender();
        var eric = new Vampire();
        var freelancer = new Lancer();
        var priest = new Healer();

        var sword = new Sword();
        var shield = new Shield();
        var axe = new GreatAxe();
        var katana = new Katana();
        var wand = new MagicWand();

        var superWeapon = Weapon.builder()
                .health(50)
                .attack(10)
                .defense(5)
                .vampirism(150)
                .healPower(8).build();

        ogre.equipWeapon(sword);
        ogre.equipWeapon(shield);
        ogre.equipWeapon(superWeapon);
        lancelot.equipWeapon(superWeapon);
        richard.equipWeapon(shield);
        eric.equipWeapon(superWeapon);
        freelancer.equipWeapon(axe);
        freelancer.equipWeapon(katana);
        priest.equipWeapon(wand);
        priest.equipWeapon(shield);

        assertEquals(125, ogre.getHealth());
        assertEquals(17, lancelot.getAttack());
        assertEquals(4, richard.getDefence());
        assertEquals(200, eric.getVampirismLevel());
        assertEquals(15, freelancer.getHealth());
        assertEquals(5, priest.getHealValue());

        assertTrue(fight(ogre, eric));
        assertFalse(fight(priest, richard));
        assertTrue(fight(lancelot, freelancer));

        var myArmy = new Army();
        myArmy.addUnits(Knight::new, 1);
        myArmy.addUnits(Lancer::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Vampire::new, 1);
        enemyArmy.addUnits(Healer::new, 1);

        myArmy.equipWarriorAtPosition(0, axe);
        myArmy.equipWarriorAtPosition(1, superWeapon);

        enemyArmy.equipWarriorAtPosition(0, katana);
        enemyArmy.equipWarriorAtPosition(1, wand);

        assertTrue(fight(myArmy, enemyArmy));
    }

    private static Stream<Arguments> provideWarriorTypes() {
        //Given
        return Stream.of(
                Arguments.of(new Warrior(), new Knight(), false),
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
        var actual = Battle.fight(warrior1, warrior2);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName(("Knight vs two warriors, knight lose"))
    void knightVsTwoWarriors() {
        //Given
        var knight = new Knight();
        var warrior1 = new Warrior();
        var warrior2 = new Warrior();

        //When
        Battle.fight(warrior1, knight);
        var result = Battle.fight(knight, warrior2);

        //Then
        assertFalse(result);
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

    @Test
    @DisplayName("Warrior with weapon vs Vampire with weapon, Warrior should win")
    void warriorWithWeaponVsVampireWithWeaponWarriorWin() {
        //Given
        var warrior = new Warrior();
        var vampire = new Vampire();
        var weapon = Weapon.builder()
                .health(-10)
                .attack(5)
                .defense(0)
                .vampirism(40)
                .healPower(0).build();
        var sword = new Sword();

        //When
        warrior.equipWeapon(weapon);
        vampire.equipWeapon(sword);
        var expected = fight(warrior, vampire);

        //Then
        assertTrue(expected);
    }

    @Test
    @DisplayName("Defender vs Lancer both equipped with weapon, Lancer should win")
    void defenderWithWeaponVsLancerWithWeaponLancerWin() {
        //Given
        var defender = new Defender();
        var lancer = new Lancer();
        var shield = new Shield();
        var greatAxe = new GreatAxe();

        //When
        defender.equipWeapon(shield);
        lancer.equipWeapon(greatAxe);
        var result = fight(defender, lancer);

        //Then
        assertFalse(result);
    }

    @Test
    @DisplayName("Magic wand & Katana")
    void magicWandVsKatana() {
        //Given
        var healer = new Healer();
        var knight = new Knight();
        var magicWand = new MagicWand();
        var katana = new Katana();

        //When
        healer.equipWeapon(magicWand);
        knight.equipWeapon(katana);
        var result = fight(healer, knight);

        //Then
        assertFalse(result);
    }

    @Test
    @DisplayName("Magic wand & Katana & Shield")
    void magicWandAndShieldVsKatanaAndShield() {
        //Given
        var defender = new Defender();
        var vampire = new Vampire();
        var shield1 = new Shield();
        var shield2 = new Shield();
        var magicWand = new MagicWand();
        var katana = new Katana();

        //When
        defender.equipWeapon(shield1);
        defender.equipWeapon(magicWand);
        vampire.equipWeapon(shield2);
        vampire.equipWeapon(katana);
        var result = fight(defender, vampire);

        //Then
        assertTrue(result);
    }

    @Test
    @DisplayName("1.Equipped Army vs Equipped Army")
    void equippedArmyBattleFirstBattle() {
        //Given
        var weapon_1 = new MagicWand();
        var weapon_2 = new GreatAxe();
        var my_army = new Army();
        var enemy_army = new Army();

        //When
        my_army.addUnits(Knight::new, 1);
        my_army.addUnits(Lancer::new, 1);
        enemy_army.addUnits(Vampire::new, 1);
        enemy_army.addUnits(Healer::new, 1);
        my_army.equipWarriorAtPosition(0, weapon_1);
        my_army.equipWarriorAtPosition(1, weapon_2);
        enemy_army.equipWarriorAtPosition(0, weapon_1);
        enemy_army.equipWarriorAtPosition(1, weapon_2);
        var result = fight(my_army, enemy_army);

        //Then
        assertTrue(result);
    }

    @Test
    @DisplayName("2.Equipped Army vs Equipped Army")
    void equippedArmyBattleSecondBattle() {
        //Given
        var weapon_1 = new Sword();
        var weapon_2 = new GreatAxe();
        var my_army = new Army();
        var enemy_army = new Army();

        //When
        my_army.addUnits(Defender::new, 1);
        my_army.addUnits(Warrior::new, 1);
        enemy_army.addUnits(Knight::new, 1);
        enemy_army.addUnits(Healer::new, 1);
        my_army.equipWarriorAtPosition(0, weapon_2);
        my_army.equipWarriorAtPosition(1, weapon_2);
        enemy_army.equipWarriorAtPosition(0, weapon_1);
        enemy_army.equipWarriorAtPosition(1, weapon_1);
        var result = fight(my_army, enemy_army);

        //Then
        assertFalse(result);
    }

    @Test
    @DisplayName("3.Equipped Army vs Equipped Army")
    void equippedArmyBattleThirdBattle() {
        //Given
        var weapon_1 = new Katana();
        var weapon_2 = new Shield();
        var my_army = new Army();
        var enemy_army = new Army();

        //When
        my_army.addUnits(Defender::new, 2);
        enemy_army.addUnits(Knight::new, 1);
        enemy_army.addUnits(Vampire::new, 1);
        my_army.equipWarriorAtPosition(0, weapon_1);
        my_army.equipWarriorAtPosition(1, weapon_1);
        enemy_army.equipWarriorAtPosition(0, weapon_1);
        enemy_army.equipWarriorAtPosition(1, weapon_1);
        var result = fight(my_army, enemy_army);

        //Then
        assertFalse(result);
    }

    @Test
    @DisplayName("4.Equipped Army vs Equipped Army")
    void equippedArmyBattleFourthBattle() {
        //Given
        var weapon_1 = Weapon.builder()
                .health(-20)
                .attack(6)
                .defense(1)
                .vampirism(40)
                .healPower(-2).build();
        var weapon_2 = Weapon.builder()
                .health(20)
                .attack(-2)
                .defense(-2)
                .vampirism(-55)
                .healPower(3).build();
        var my_army = new Army();
        var enemy_army = new Army();

        //When
        my_army.addUnits(Knight::new, 3);
        enemy_army.addUnits(Warrior::new, 1);
        enemy_army.addUnits(Defender::new, 2);
        my_army.equipWarriorAtPosition(0, weapon_1);
        my_army.equipWarriorAtPosition(1, weapon_1);
        my_army.equipWarriorAtPosition(2, weapon_1);
        enemy_army.equipWarriorAtPosition(0, weapon_2);
        enemy_army.equipWarriorAtPosition(1, weapon_2);
        enemy_army.equipWarriorAtPosition(2, weapon_2);
        var result = fight(my_army, enemy_army);

        //Then
        assertTrue(result);
    }

    @Test
    @DisplayName("1.Equipped Army vs Equipped Army - straight fight")
    void equippedArmyBattleFifthBattle() {
        //Given
        var weapon_1 = Weapon.builder()
                .health(-20)
                .attack(1)
                .defense(1)
                .vampirism(40)
                .healPower(-2).build();
        var weapon_2 = Weapon.builder()
                .health(20)
                .attack(2)
                .defense(2)
                .vampirism(-55)
                .healPower(3).build();
        var my_army = new Army();
        var enemy_army = new Army();

        //When
        my_army.addUnits(Vampire::new, 3);
        enemy_army.addUnits(Warrior::new, 1);
        enemy_army.addUnits(Defender::new, 2);
        my_army.equipWarriorAtPosition(0, weapon_1);
        my_army.equipWarriorAtPosition(1, weapon_1);
        my_army.equipWarriorAtPosition(2, weapon_1);
        enemy_army.equipWarriorAtPosition(0, weapon_2);
        enemy_army.equipWarriorAtPosition(1, weapon_2);
        enemy_army.equipWarriorAtPosition(2, weapon_2);
        var result = straightFight(my_army, enemy_army);

        //Then
        assertFalse(result);
    }

    @Test
    @DisplayName("2.Equipped Army vs Equipped Army - straight fight")
    void equippedArmyBattleSixthBattle() {
        //Given
        var weapon_1 = new Katana();
        var weapon_2 = new Shield();
        var my_army = new Army();
        var enemy_army = new Army();

        //When
        my_army.addUnits(Vampire::new, 2);
        my_army.addUnits(Rookie::new, 2);
        enemy_army.addUnits(Warrior::new, 1);
        enemy_army.addUnits(Defender::new, 2);
        my_army.equipWarriorAtPosition(0, weapon_1);
        my_army.equipWarriorAtPosition(1, weapon_1);
        my_army.equipWarriorAtPosition(2, weapon_2);
        enemy_army.equipWarriorAtPosition(0, weapon_1);
        enemy_army.equipWarriorAtPosition(1, weapon_2);
        enemy_army.equipWarriorAtPosition(2, weapon_2);
        var result = straightFight(my_army, enemy_army);

        //Then
        assertTrue(result);
    }

    @Test
    @DisplayName("3.Equipped Army vs Equipped Army - straight fight")
    void equippedArmyBattleSeventhBattle() {
        //Given
        var weapon_1 = new Sword();
        var weapon_2 = new GreatAxe();
        var my_army = new Army();
        var enemy_army = new Army();

        //When
        my_army.addUnits(Vampire::new, 3);
        enemy_army.addUnits(Warrior::new, 1);
        enemy_army.addUnits(Defender::new, 1);
        my_army.equipWarriorAtPosition(0, weapon_2);
        my_army.equipWarriorAtPosition(1, weapon_2);
        my_army.equipWarriorAtPosition(2, weapon_2);
        enemy_army.equipWarriorAtPosition(0, weapon_1);
        enemy_army.equipWarriorAtPosition(1, weapon_1);
        var result = straightFight(my_army, enemy_army);

        //Then
        assertFalse(result);
    }

    @Test
    @DisplayName("4.Equipped Army vs Equipped Army - straight fight")
    void equippedArmyBattleEighthBattle() {
        //Given
        var weapon_1 = new Katana();
        var weapon_2 = new MagicWand();
        var my_army = new Army();
        var enemy_army = new Army();

        //When
        my_army.addUnits(Rookie::new, 3);
        enemy_army.addUnits(Defender::new, 1);
        enemy_army.addUnits(Healer::new, 1);
        my_army.equipWarriorAtPosition(0, weapon_1);
        my_army.equipWarriorAtPosition(1, weapon_1);
        my_army.equipWarriorAtPosition(2, weapon_1);
        enemy_army.equipWarriorAtPosition(0, weapon_2);
        enemy_army.equipWarriorAtPosition(1, weapon_2);
        var result = straightFight(my_army, enemy_army);

        //Then
        assertFalse(result);
    }
}

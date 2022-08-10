package softserve.com;

import org.junit.jupiter.api.Test;
import softserve.com.model.entities.*;
import softserve.com.model.entities.weapons.*;

import static org.junit.jupiter.api.Assertions.*;
import static softserve.com.service.Battle.fight;

public class WeaponsTest {
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

        richard.equipWeapon(shield);
        eric.equipWeapon(superWeapon);
        freelancer.equipWeapon(axe);
        freelancer.equipWeapon(katana);
        priest.equipWeapon(wand);
        priest.equipWeapon(shield);

        assertEquals(125, ogre.getHealth());
        lancelot.equipWeapon(superWeapon);
        assertEquals(17, lancelot.getAttack());
        assertEquals(4, richard.getDefence());
        assertEquals(200, eric.getVampirismLevel());
        assertEquals(15, freelancer.getHealth());
        assertEquals(5, priest.getHealValue());

        assertFalse(fight(ogre, eric));
        assertFalse(fight(priest, richard));
        assertFalse(fight(lancelot, freelancer));

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
}

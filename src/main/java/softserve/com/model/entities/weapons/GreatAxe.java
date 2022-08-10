package softserve.com.model.entities.weapons;

import lombok.*;
import softserve.com.model.entities.Weapon;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GreatAxe extends Weapon {
    private int health = -15;
    private int attack = 5;
    private int defense = -2;
    private int vampirism = 10;
}

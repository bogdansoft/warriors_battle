package softserve.com.model.entities.weapons;

import lombok.*;
import softserve.com.model.entities.Weapon;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MagicWand extends Weapon {
    private int health = 30;
    private int attack = 3;
    private int healPower = 3;
}


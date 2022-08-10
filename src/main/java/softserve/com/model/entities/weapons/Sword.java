package softserve.com.model.entities.weapons;

import lombok.*;
import softserve.com.model.entities.Weapon;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sword extends Weapon {
    private int health = 5;
    private int attack = 2;
}

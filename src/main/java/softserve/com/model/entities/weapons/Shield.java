package softserve.com.model.entities.weapons;

import lombok.*;
import softserve.com.model.entities.Weapon;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shield extends Weapon {
    private int health = 20;
    private int attack = -1;
    private int defense = 2;
}
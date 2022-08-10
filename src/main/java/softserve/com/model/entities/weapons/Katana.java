package softserve.com.model.entities.weapons;

import lombok.*;
import softserve.com.model.entities.Weapon;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Katana extends Weapon {
    private int health = -20;
    private int attack = 6;
    private int defense = -5;
    private int vampirism = 50;
}


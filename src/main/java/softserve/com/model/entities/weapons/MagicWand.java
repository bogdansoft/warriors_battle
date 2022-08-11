package softserve.com.model.entities.weapons;

import lombok.*;

@Data
@AllArgsConstructor
public class MagicWand extends Weapon {
    private int health = 30;
    private int attack = 3;
    private int healPower = 3;

    public MagicWand() {
        this.health = 30;
        this.attack = 3;
        this.healPower = 3;
    }
}


package softserve.com.model.entities.weapons;

import lombok.*;

@Data
@AllArgsConstructor
public class Sword extends Weapon {
    private int health = 5;
    private int attack = 2;

    public Sword() {
        this.health = 5;
        this.attack = 2;
    }
}

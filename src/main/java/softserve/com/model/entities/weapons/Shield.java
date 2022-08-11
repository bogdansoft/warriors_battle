package softserve.com.model.entities.weapons;

import lombok.*;

@Data
@AllArgsConstructor
public class Shield extends Weapon {
    private int health = 20;
    private int attack = -1;
    private int defense = 2;

    public Shield() {
        this.health = 20;
        this.attack = -1;
        this.defense = 2;
    }
}
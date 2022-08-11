package softserve.com.model.entities.weapons;

import lombok.*;

@Data
@AllArgsConstructor
public class GreatAxe extends Weapon {
    private int health = -15;
    private int attack = 5;
    private int defense = -2;
    private int vampirism = 10;

    public GreatAxe() {
        this.health = -15;
        this.attack = 5;
        this.defense = -2;
        this.vampirism = 10;
    }
}

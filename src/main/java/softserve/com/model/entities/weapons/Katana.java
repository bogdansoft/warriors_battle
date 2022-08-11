package softserve.com.model.entities.weapons;

import lombok.*;

@Data
@AllArgsConstructor
public class Katana extends Weapon {
    private int health = -20;
    private int attack = 6;
    private int defense = -5;
    private int vampirism = 50;

    public Katana() {
        this.health = -20;
        this.attack = 6;
        this.defense = -5;
        this.vampirism = 50;
    }
}


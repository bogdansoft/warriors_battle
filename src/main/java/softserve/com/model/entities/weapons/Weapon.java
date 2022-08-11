package softserve.com.model.entities.weapons;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Weapon {
    @Builder.Default
    private int health = 0;
    @Builder.Default
    private int attack = 0;
    @Builder.Default
    private int defense = 0;
    @Builder.Default
    private int vampirism = 0;
    @Builder.Default
    private int healPower = 0;
}

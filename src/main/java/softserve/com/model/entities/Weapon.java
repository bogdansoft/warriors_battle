package softserve.com.model.entities;

import lombok.*;
import softserve.com.model.interfaces.WeaponInterface;

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

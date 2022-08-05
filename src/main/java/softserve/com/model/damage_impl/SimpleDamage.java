package softserve.com.model.damage_impl;

import softserve.com.model.interfaces.Damage;

public record SimpleDamage(int hitPoints) implements Damage {
}

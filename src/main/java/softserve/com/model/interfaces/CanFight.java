package softserve.com.model.interfaces;

public interface CanFight {
    boolean isAlive();

    void hit(CanFight opponent);

    void receiveDamage(Damage damage, CanFight damageDealer);
}

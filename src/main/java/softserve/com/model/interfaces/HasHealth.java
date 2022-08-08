package softserve.com.model.interfaces;

public interface HasHealth {
    default boolean isAlive() {
        return getHealth() > 0;
    }

    default void reduceHealthBasedOnDamage(int hitPoints) {
        setHealth(getHealth() - hitPoints);
    }

    int getHealth();

    int getInitialHealth();

    void setHealth(int health);
}

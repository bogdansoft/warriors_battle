package softserve.com.model.interfaces;

public interface WerewolfState extends HasVampirism, CanDefend, CanAttack, HasHealth {
    @Override
    int getDefence();

    @Override
    int getVampirismLevel();

    @Override
    int healHimself();

    @Override
    int getAttack();

    @Override
    int getHealth();

    @Override
    void setHealth(int health);
}

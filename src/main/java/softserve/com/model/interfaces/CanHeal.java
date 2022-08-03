package softserve.com.model.interfaces;

public interface CanHeal {
    int getHealing();

    void letMeHealYou(CanFight colleague);
}

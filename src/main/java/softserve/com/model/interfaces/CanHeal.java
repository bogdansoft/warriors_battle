package softserve.com.model.interfaces;

public interface CanHeal {
    default void heal(HasHealth colleague) {
        var setHealthValue = Math.min(colleague.getInitialHealth(), colleague.getHealth() + getHealPower());
        colleague.setHealth(setHealthValue);
    }

    int getHealPower();
}

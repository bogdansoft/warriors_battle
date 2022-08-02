package softserve.com.model.interfaces;

import softserve.com.model.entities.Vampire;

public interface HasVampirism {
    int getVampirismLevel();

    int healHimself(Vampire vampire);
}


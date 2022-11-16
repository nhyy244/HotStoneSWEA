package hotstone.broker.doubles;

import hotstone.framework.Hero;
import hotstone.framework.Player;

public class StubHeroForBroker implements Hero {
    @Override
    public int getMana() {
        return 1;
    }

    @Override
    public int getHealth() {
        return 1;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public String getType() {
        return "hero";
    }

    @Override
    public Player getOwner() {
        return Player.FINDUS;
    }
}

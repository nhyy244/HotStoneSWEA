package hotstone.broker.doubles;

import hotstone.framework.Card;
import hotstone.framework.Player;

public class StubCardForBroker implements Card {
    @Override
    public String getName() {
        return "card0";
    }

    @Override
    public int getManaCost() {
        return 1;
    }

    @Override
    public int getAttack() {
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
    public Player getOwner() {
        return Player.FINDUS;
    }

    @Override
    public String getID() {
        return null;
    }
}

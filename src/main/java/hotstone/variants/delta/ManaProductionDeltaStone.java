package hotstone.variants.delta;

import hotstone.framework.Game;
import hotstone.framework.ManaProductionStrategy;
import hotstone.framework.Player;
import hotstone.standard.HeroImpl;

public class ManaProductionDeltaStone implements ManaProductionStrategy {
    @Override
    public void manaProduction(Player who, Game game) {
        ((HeroImpl)game.getHero(who)).setMana(7);
    }
}

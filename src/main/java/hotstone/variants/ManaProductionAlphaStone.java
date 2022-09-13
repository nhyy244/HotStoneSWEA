package hotstone.variants;

import hotstone.framework.Game;
import hotstone.framework.ManaProductionStrategy;
import hotstone.framework.Player;
import hotstone.standard.HeroImpl;

public class ManaProductionAlphaStone implements ManaProductionStrategy {
    @Override
    public void manaProduction(Player who, Game game) {
        ((HeroImpl)game.getHero(who)).setMana(3);
    }
}

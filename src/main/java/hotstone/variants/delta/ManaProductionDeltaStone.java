package hotstone.variants.delta;

import hotstone.framework.*;
import hotstone.standard.HeroImpl;

public class ManaProductionDeltaStone implements ManaProductionStrategy {
    @Override
    public void manaProduction(Player who, MutableGame game) {
        //((MutableHero)game.getHero(who)).setMana(7);
        game.deltaHeroMana(who,7);
        //((HeroImpl)game.getHero(who)).setMana(7);
    }
}

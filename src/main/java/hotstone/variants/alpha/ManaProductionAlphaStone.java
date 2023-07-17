package hotstone.variants.alpha;

import hotstone.framework.*;
import hotstone.standard.HeroImpl;

public class ManaProductionAlphaStone implements ManaProductionStrategy {
    @Override
    public void manaProduction(Player who, MutableGame game) {
        //((MutableHero)game.getHero(who)).setMana(3);
        game.deltaHeroMana(who,3);
    }
}

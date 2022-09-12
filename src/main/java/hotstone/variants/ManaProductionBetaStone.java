package hotstone.variants;

import hotstone.framework.Game;
import hotstone.framework.ManaProductionStrategy;
import hotstone.framework.Player;
import hotstone.standard.HeroImpl;

public class ManaProductionBetaStone implements ManaProductionStrategy {

    @Override
    public void manaProduction(Player who,Game game) {
        HeroImpl h = (HeroImpl) game.getHero(who);
        h.setMana(game.getTurnNumber());
        if(game.getTurnNumber() >=7){
            h.setMana(7);
        }
    }
}

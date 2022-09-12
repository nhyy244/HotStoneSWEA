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
        if(game.getTurnNumber() <=6) {//roundNumber starts at 0 in my game !
            h.setMana(game.getTurnNumber()+1);
        }
        else{
            h.setMana(7);
        }
    }
}

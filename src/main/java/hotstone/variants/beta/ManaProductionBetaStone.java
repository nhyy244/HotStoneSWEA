package hotstone.variants.beta;

import hotstone.framework.Game;
import hotstone.framework.ManaProductionStrategy;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.standard.HeroImpl;

public class ManaProductionBetaStone implements ManaProductionStrategy {

    @Override
    public void manaProduction(Player who, MutableGame game) {
        //HeroImpl h = (HeroImpl) game.getHero(who);
        game.deltaHeroMana(who,game.getTurnNumber());
        //h.setMana(game.getTurnNumber());
        if(game.getTurnNumber() <=6) {//roundNumber starts at 0 in my game !
            game.deltaHeroMana(who,game.getTurnNumber()+1);
           // h.setMana(game.getTurnNumber()+1);
        }
        else{
            game.deltaHeroMana(who,7);
           // h.setMana(7);
        }
    }
}

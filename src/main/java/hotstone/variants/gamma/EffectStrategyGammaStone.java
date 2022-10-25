package hotstone.variants.gamma;

import hotstone.framework.*;
import hotstone.standard.CardImpl;
import hotstone.standard.HeroImpl;

public class EffectStrategyGammaStone implements EffectStrategy {
    @Override
    public void usePower(Player who, MutableGame game) {
        HeroImpl h = (HeroImpl) game.getHero(who);
        String heroPower = h.getHeroPower();
        if(heroPower.equals("Chili")){
            ((HeroImpl)game.getHero(Utility.computeOpponent(who))).
                    setHealth((game.getHero(Utility.computeOpponent(who)).getHealth()-2));
            System.out.println("Opp H: (0,-2)");
        }
        if(heroPower.equals("Sovs")){
            Card sovsCard = new CardImpl("Sovs",0,1,1,false,who);
            game.playCard(who,sovsCard);
            System.out.println("Field Sovs");
        }
    }

    @Override
    public void applyCardEffects(MutableGame game, Player who, Card card) {

    }
}

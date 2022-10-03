package hotstone.variants.gamma;

import hotstone.framework.*;
import hotstone.standard.CardImpl;
import hotstone.standard.HeroImpl;

public class HeroPowerGammaStone implements HeroPowerStrategy {
    @Override
    public void usePower(Player who, Game game) {
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
}

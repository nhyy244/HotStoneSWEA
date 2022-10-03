package hotstone.variants.epsilon;

import hotstone.framework.*;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HeroPowerStrategyEpsilon implements HeroPowerStrategy {

    @Override
    public void usePower(Player who, Game game) {
        Random r = new Random();
        HeroImpl h = (HeroImpl) game.getHero(who);
        String heroPower = h.getHeroPower();
        Hero opponentHero = game.getHero(Utility.computeOpponent(who));

        List<Card> opponentHeroField = (List<Card>) game.getField(opponentHero.getOwner());
        boolean fieldOpponentHeroNotNull = opponentHeroField != null;

        List<Card> inTurnHeroField = (List<Card>) game.getField(h.getOwner());
        boolean inTurnHeroFieldNotNull = inTurnHeroField != null;

        if(heroPower.equals("Redwine") && fieldOpponentHeroNotNull){
            Card c = game.getCardInField(opponentHero.getOwner(),r.nextInt(opponentHeroField.size()));
            ((CardImpl) c).setAttack(c.getAttack()-2);
            System.out.println("Opp M: (0,-2)");
        }
        if(heroPower.equals("Pasta") && inTurnHeroFieldNotNull){
            Card c = game.getCardInField(h.getOwner(),r.nextInt(opponentHeroField.size()));
            ((CardImpl) c).setAttack(c.getAttack()+2);
            System.out.println("M: (+2,0)");
        }
    }
}

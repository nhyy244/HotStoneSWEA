package hotstone.variants.eta;

import hotstone.framework.*;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;
import hotstone.standard.StandardHotStoneGame;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class EffectStrategyEta implements EffectStrategy {
    @Override
    public void usePower(Player who, Game game) {

    }

    @Override
    public void applyCardEffects(Game game, Player who, Card card) {
        HeroImpl opponentHero = (HeroImpl)game.getHero(Utility.computeOpponent(who));
        HeroImpl playerInTurnHero = (HeroImpl)game.getHero(who);
        ArrayList<Card> cardsToBeBuffed = new ArrayList<>();
        Random r = new Random();
        switch (card.getName()){
            case GameConstants.BROWN_RICE_CARD:
                opponentHero.setHealth(opponentHero.getHealth()-1); //do one damage to opponentHero
                System.out.println("Opp H: (0,-1)");
            break;
            case GameConstants.TOMATO_SALAD_CARD:
                if(game.getField(who) != null) {
                    for(int i=1;i<game.getFieldSize(who);i++){
                        cardsToBeBuffed.add(game.getCardInField(who,i));
                    }
                    CardImpl cardToBeBuffed = (CardImpl) cardsToBeBuffed.get(r.nextInt(cardsToBeBuffed.size()));
                    cardToBeBuffed.setAttack(cardToBeBuffed.getAttack() + 1);
                    System.out.println("M: (+1,0)");
                }
            break;
            case GameConstants.POKE_BOWL_CARD:
                playerInTurnHero.setHealth(playerInTurnHero.getHealth()+2);
                System.out.println("H: (0,+2)");
            break;

            case GameConstants.NOODLE_SOUP_CARD:
                ((StandardHotStoneGame)game).drawCard(playerInTurnHero,((StandardHotStoneGame) game).getDeck(who));
                System.out.println("Draw Card");
            break;
            case GameConstants.CHICKEN_CURRY_CARD:
                if(game.getField(opponentHero.getOwner()) != null){
                    CardImpl cardToBeKilled = (CardImpl)game.getCardInField(Utility.computeOpponent(who), r.nextInt(game.getFieldSize(who)));
                        ((StandardHotStoneGame)game).getFieldList(opponentHero.getOwner()).remove(cardToBeKilled);
                        System.out.println("Kill opp M ");

            }
            break;
            case GameConstants.BEEF_BURGER_CARD:
                if(game.getField(opponentHero.getOwner()) != null){
                    CardImpl cardToBeBuffed2= (CardImpl)game.getCardInField(opponentHero.getOwner(), r.nextInt(game.getFieldSize(who)));
                        cardToBeBuffed2.setAttack(cardToBeBuffed2.getAttack()+2);
                        System.out.println("Kill opp M ");
                    }
            break;
        }

    }
}

package hotstone.variants.eta;

import hotstone.framework.*;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;
import hotstone.standard.StandardHotStoneGame;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class CardEffectsStrategyEta implements CardEffectsEtaStrategy {
    @Override
    public void applyCardEffects(Game game, Player who, Card card) {
        HeroImpl opponentHero = (HeroImpl)game.getHero(Utility.computeOpponent(who));
        HeroImpl playerInTurnHero = (HeroImpl)game.getHero(who);
        Random r = new Random();
        switch (card.getName()){
            case GameConstants.BROWN_RICE_CARD:
                opponentHero.setHealth(opponentHero.getHealth()-1); //do one damage to opponentHero
                System.out.println("Opp H: (0,-1)");
            case GameConstants.TOMATO_SALAD_CARD:
                if(game.getField(who) != null){
                    CardImpl cardToBeBuffed = (CardImpl)game.getCardInField(who, r.nextInt(game.getFieldSize(who)));
                    if(cardToBeBuffed.getName()!= GameConstants.TOMATO_SALAD_CARD){
                        cardToBeBuffed.setAttack(cardToBeBuffed.getAttack()+1);
                        System.out.println("M: (+1,0)");
                    }
                }
            case GameConstants.POKE_BOWL_CARD:
                playerInTurnHero.setHealth(playerInTurnHero.getHealth()+2);
                System.out.println("H: (0,+2)");

            case GameConstants.NOODLE_SOUP_CARD:
                ((StandardHotStoneGame)game).drawCard(playerInTurnHero,((StandardHotStoneGame) game).getDeck(who));
                System.out.println("Draw Card");
            case GameConstants.CHICKEN_CURRY_CARD:
                if(game.getField(who) != null){
                    CardImpl cardToBeKilled = (CardImpl)game.getCardInField(Utility.computeOpponent(who), r.nextInt(game.getFieldSize(who)));
                    if(cardToBeKilled.getName()!= GameConstants.CHICKEN_CURRY_CARD){
                        ((StandardHotStoneGame)game).getFieldList(who).remove(cardToBeKilled);
                        System.out.println("Kill opp M ");
                }
            }
            case GameConstants.BEEF_BURGER_CARD:
                if(game.getField(who) != null){
                    CardImpl cardToBeBuffed2= (CardImpl)game.getCardInField(Utility.computeOpponent(who), r.nextInt(game.getFieldSize(who)));
                    if(cardToBeBuffed2.getName()!= GameConstants.BEEF_BURGER_CARD){
                        cardToBeBuffed2.setAttack(cardToBeBuffed2.getAttack()+2);
                        System.out.println("Kill opp M ");
                    }
                }

        }

    }
}

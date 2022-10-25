package hotstone.TestStubs;

import hotstone.framework.*;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;
import hotstone.standard.StandardHotStoneGame;

import java.util.Random;

public class FixedEffectStrategyTest implements EffectStrategy {
    private Card c;
    public FixedEffectStrategyTest(CardImpl c ){
        this.c= c;
    }
    @Override
    public void usePower(Player who, MutableGame game) {
        HeroImpl h = (HeroImpl) game.getHero(who);
        String heroPower = h.getHeroPower();

        if(heroPower.equals("Redwine")){
            ((CardImpl) c).setAttack(c.getAttack()-2);
            System.out.println("Opp M: (0,-2)");
        }
        if(heroPower.equals("Pasta")){
            ((CardImpl) c).setAttack(c.getAttack()+2);
            System.out.println("M: (+2,0)");
        }
    }

    @Override
    public void applyCardEffects(MutableGame game, Player who, Card card) {
        HeroImpl opponentHero = (HeroImpl)game.getHero(Utility.computeOpponent(who));
        HeroImpl playerInTurnHero = (HeroImpl)game.getHero(who);
        Random r = new Random();
        switch (card.getName()){
            case GameConstants.BROWN_RICE_CARD:
                opponentHero.setHealth(opponentHero.getHealth()-1); //do one damage to opponentHero
                System.out.println("Opp H: (0,-1)");
                break;
            case GameConstants.TOMATO_SALAD_CARD:
                if(game.getField(who) != null){
                    CardImpl cardToBeBuffed = (CardImpl) this.c;
                    if(cardToBeBuffed.getName()!= GameConstants.TOMATO_SALAD_CARD){
                        cardToBeBuffed.setAttack(cardToBeBuffed.getAttack()+1);
                        System.out.println("M: (+1,0)");
                    }
                }
                break;
            case GameConstants.POKE_BOWL_CARD:
                playerInTurnHero.setHealth(playerInTurnHero.getHealth()+2);
                System.out.println("H: (0,+2)");
                break;

            case GameConstants.NOODLE_SOUP_CARD:
                game.drawCardFromDeck(game.getPlayerInTurn());
                //((StandardHotStoneGame)game).drawCard(playerInTurnHero,((StandardHotStoneGame) game).getDeck(who));
                System.out.println("Draw Card");
                break;
            case GameConstants.CHICKEN_CURRY_CARD:
                if(game.getField(who) != null){
                    CardImpl cardToBeKilled = (CardImpl) this.c;
                    if(cardToBeKilled.getName()!= GameConstants.CHICKEN_CURRY_CARD){
                        ((StandardHotStoneGame)game).getFieldList(who).remove(cardToBeKilled);
                        System.out.println("Kill opp M ");
                    }
                }
                break;
            case GameConstants.BEEF_BURGER_CARD:
                if(game.getField(who) != null){
                    CardImpl cardToBeBuffed2= (CardImpl) this.c;
                    if(cardToBeBuffed2.getName()!= GameConstants.BEEF_BURGER_CARD){
                        cardToBeBuffed2.setAttack(cardToBeBuffed2.getAttack()+2);
                        System.out.println("Kill opp M ");
                    }
                }
                break;
        }

    }
}

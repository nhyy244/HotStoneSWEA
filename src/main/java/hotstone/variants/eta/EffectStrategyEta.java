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
    public void usePower(Player who, MutableGame game) {

    }

    @Override
    public void applyCardEffects(MutableGame game, Player who, Card card) {
        MutableHero opponentHero = (MutableHero) game.getHero(Utility.computeOpponent(who));
        MutableHero playerInTurnHero = (MutableHero) game.getHero(who);
        ArrayList<Card> cardsToBeBuffed = new ArrayList<>();
        Random r = new Random();
        switch (card.getName()){
            case GameConstants.BROWN_RICE_CARD:
                game.deltaHeroHealth(opponentHero.getOwner(),opponentHero.getHealth()-1);
                //opponentHero.setHealth(opponentHero.getHealth()-1); //do one damage to opponentHero
                System.out.println("Opp H: (0,-1)");
            break;
            case GameConstants.TOMATO_SALAD_CARD:
                if(game.getField(who) != null) {
                    for(int i=1;i<game.getFieldSize(who);i++){
                        cardsToBeBuffed.add(game.getCardInField(who,i));
                    }
                    int randomIndex = r.nextInt(cardsToBeBuffed.size());
                    MutableCard cardToBeBuffed = (MutableCard) cardsToBeBuffed.get(randomIndex);
                    int indexOnField = game.getFieldArray(cardToBeBuffed.getOwner()).indexOf(cardToBeBuffed);
                    game.deltaFieldCardAttack(cardToBeBuffed.getOwner(),indexOnField,cardToBeBuffed.getAttack()+1);
                    //cardToBeBuffed.setAttack(cardToBeBuffed.getAttack() + 1);
                    System.out.println("M: (+1,0)");
                }
            break;
            case GameConstants.POKE_BOWL_CARD:
                game.deltaHeroHealth(playerInTurnHero.getOwner(), playerInTurnHero.getHealth()+2);
                //playerInTurnHero.setHealth(playerInTurnHero.getHealth()+2);
                System.out.println("H: (0,+2)");
            break;

            case GameConstants.NOODLE_SOUP_CARD:
                game.drawCardFromDeck(game.getPlayerInTurn());
                //((StandardHotStoneGame)game).drawCard(playerInTurnHero,((StandardHotStoneGame) game).getDeck(who));
                System.out.println("Draw Card");
            break;
            case GameConstants.CHICKEN_CURRY_CARD:
                if(game.getField(opponentHero.getOwner()) != null){
                    MutableCard cardToBeKilled = (MutableCard)game.getCardInField(Utility.computeOpponent(who), r.nextInt(game.getFieldSize(who)));
                        //((StandardHotStoneGame)game).getFieldList(opponentHero.getOwner()).remove(cardToBeKilled);
                        game.removeCardFromField(opponentHero.getOwner(),cardToBeKilled);
                        System.out.println("Kill opp M ");

            }
            break;
            case GameConstants.BEEF_BURGER_CARD:
                if(game.getField(opponentHero.getOwner()) != null){
                    int randomIndex = r.nextInt(game.getFieldSize(who));
                    MutableCard cardToBeBuffed2= (MutableCard)game.getCardInField(opponentHero.getOwner(), randomIndex);
                        //cardToBeBuffed2.setAttack(cardToBeBuffed2.getAttack()+2);
                    int indexOnField = game.getFieldArray(cardToBeBuffed2.getOwner()).indexOf(cardToBeBuffed2);
                    game.deltaFieldCardAttack(cardToBeBuffed2.getOwner(),indexOnField,cardToBeBuffed2.getAttack()+2);
                    System.out.println("Kill opp M ");
                    }
            break;
        }

    }
}

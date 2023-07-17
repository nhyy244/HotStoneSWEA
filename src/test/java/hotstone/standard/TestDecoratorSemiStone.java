package hotstone.standard;

import hotstone.Decorator.DecoratorHotStone;
import hotstone.framework.Game;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Utility;
import hotstone.variants.factory.SemiStoneFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestDecoratorSemiStone {

    private MutableGame game;
    private DecoratorHotStone decoratedSemiStone;

    @BeforeEach
    public void setUp(){
        game = new StandardHotStoneGame(new SemiStoneFactory());
        decoratedSemiStone = new DecoratorHotStone(game);
    }

    @Test
    public void shouldPrintPeddersenWonGame(){
        game.deltaHeroHealth(game.getPlayerInTurn(),-21);
        System.out.println("shouldPrintPeddersenWonGameTest: ");
        decoratedSemiStone.getWinner();
    }
    @Test
    public void shouldPrintFindusWonGame(){
        game.endTurn();
        game.deltaHeroHealth(game.getPlayerInTurn(),-21);
        System.out.println("shouldPrintFindusWonGameTest: ");
        decoratedSemiStone.getWinner();
    }
    @Test
    public void shouldPrintFindusPlayedCard(){
        System.out.println("shouldPrintFindusPlayedCardTest: ");
        decoratedSemiStone.playCard(decoratedSemiStone.getPlayerInTurn(),decoratedSemiStone.getCardInHand(decoratedSemiStone.getPlayerInTurn(),2));
    }
    @Test
    public void shouldPrintPeddersenPlayedCard(){
        game.endTurn();
        System.out.println("shouldPrintPeddersenPlayedCardTest: ");
        decoratedSemiStone.playCard(decoratedSemiStone.getPlayerInTurn(),decoratedSemiStone.getCardInHand(decoratedSemiStone.getPlayerInTurn(),2));
    }
    @Test
    public void shouldPrintFindusAttackedHeroWithCard(){
        System.out.println("shouldPrintFindusAttackedHeroWithCardTest: ");
        game.playCard(game.getPlayerInTurn(),game.getCardInHand(game.getPlayerInTurn(),2));
        game.endTurn();
        game.playCard(game.getPlayerInTurn(),game.getCardInHand(game.getPlayerInTurn(),3));
        game.endTurn();
        Player opponent = Utility.computeOpponent(game.getPlayerInTurn());
        decoratedSemiStone.attackCard(decoratedSemiStone.getPlayerInTurn(),decoratedSemiStone.getCardInField(decoratedSemiStone.getPlayerInTurn(),0),decoratedSemiStone.getCardInField(opponent,0));
    }
}

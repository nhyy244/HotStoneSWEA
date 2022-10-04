package hotstone.standard;

import hotstone.TestStubs.GameDouble;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.utility.TestHelper;
import hotstone.variants.factory.HotStoneFactory;
import hotstone.variants.factory.ZetaStoneFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hotstone.framework.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestZetaStone {

    private Game game;
    private Game gameDouble;

    @BeforeEach
    public void setUp(){
        game = new StandardHotStoneGame(new ZetaStoneFactory());
        gameDouble = new GameDouble(new ZetaStoneFactory());
    }

    @Test
    public void deckSizeFindusShouldBe7(){
        HotStoneFactory zetaStoneFactory = new ZetaStoneFactory();
        HashMap<Player, List<Card>> deckFindus = new HashMap<>();
        zetaStoneFactory.createGenerateDeckStrategy().generateDeck(Player.FINDUS,deckFindus);
        int deckSizeFindus = deckFindus.get(Player.FINDUS).size();
        assertThat(deckSizeFindus,is(7));
    }
    @Test
    public void deckSizePeddersenShouldBe7(){
        HotStoneFactory zetaStoneFactory = new ZetaStoneFactory();
        HashMap<Player, List<Card>> deckPeddersen = new HashMap<>();
        zetaStoneFactory.createGenerateDeckStrategy().generateDeck(Player.PEDDERSEN,deckPeddersen);
        int deckSizeFindus = deckPeddersen.get(Player.PEDDERSEN).size();
        assertThat(deckSizeFindus,is(7));
    }
    @Test
    public void allCardsInFindusDeckShouldBeCinco(){
        HotStoneFactory zetaStoneFactory = new ZetaStoneFactory();
        HashMap<Player, List<Card>> deckFindusMap = new HashMap<>();
        zetaStoneFactory.createGenerateDeckStrategy().generateDeck(Player.FINDUS,deckFindusMap);
        ArrayList<Card> deckFindus = (ArrayList<Card>) deckFindusMap.get(Player.FINDUS);
        for(Card c : deckFindus){
            assertThat(c.getName(),is(GameConstants.CINCO_CARD));
        }
    }
    @Test
    public void allCardsInPeddersenDeckShouldBeCinco(){
        HotStoneFactory zetaStoneFactory = new ZetaStoneFactory();
        HashMap<Player, List<Card>> deckPeddersenMap = new HashMap<>();
        zetaStoneFactory.createGenerateDeckStrategy().generateDeck(Player.PEDDERSEN,deckPeddersenMap);
        ArrayList<Card> deckPeddersen = (ArrayList<Card>) deckPeddersenMap.get(Player.PEDDERSEN);
        for(Card c : deckPeddersen){
            assertThat(c.getName(),is(GameConstants.CINCO_CARD));
        }
    }
    @Test
    public void FindusShouldBeWinnerWhenPeddersen0Health(){
        ((HeroImpl)game.getHero(Player.PEDDERSEN)).setHealth(0);
        assertThat(game.getWinner(),is(Player.FINDUS));
    }
    @Test
    public void PeddersenShouldBeWinnerWhenFindus0Health(){
        ((HeroImpl)game.getHero(Player.FINDUS)).setHealth(0);
        assertThat(game.getWinner(),is(Player.PEDDERSEN));
    }
    @Test
    public void FindusShouldBeWinnerAfter6RoundsPlus(){
        assertThat(game.getWinner(),is(nullValue())); //before 7 rounds
        TestHelper.advanceGameNRounds(game,7);
        assertThat(game.getPlayerInTurn(),is(Player.FINDUS));
        Status playCard1 = game.playCard(Player.FINDUS,game.getCardInHand(Player.FINDUS,0)); //findus has 0 mana now, att =3
        game.endTurn();
        game.endTurn();
        assertThat(playCard1,is(Status.OK));
        Status playCard2 = game.playCard(Player.FINDUS,game.getCardInHand(Player.FINDUS,0)); //findus has 0 mana now att =  6
        assertThat(playCard2,is(Status.OK));
        game.endTurn();
        game.endTurn();
        Status playCard3 = game.playCard(Player.FINDUS,game.getCardInHand(Player.FINDUS,0)); //findus has 0 mana now att = 11
        assertThat(playCard3,is(Status.OK));
        game.endTurn();
        game.endTurn();
        game.attackHero(Player.FINDUS,game.getCardInField(Player.FINDUS,2));
        game.getWinner();
        ((StandardHotStoneGame)game).getTotalAttackOutput(Player.PEDDERSEN);
        assertThat(game.getWinner(),is(nullValue()));
        assertThat( ((StandardHotStoneGame)game).getTotalAttackOutput(Player.FINDUS),is(5));
        game.attackHero(Player.FINDUS,game.getCardInField(Player.FINDUS,1));
        assertThat(((StandardHotStoneGame)game).getTotalAttackOutput(Player.FINDUS),is(10));
        assertThat(game.getWinner(),is(Player.FINDUS));
    }

}

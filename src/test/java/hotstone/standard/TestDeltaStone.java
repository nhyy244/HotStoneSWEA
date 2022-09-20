package hotstone.standard;

import hotstone.framework.Game;
import hotstone.framework.Hero;
import hotstone.framework.Player;
import hotstone.variants.*;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hotstone.framework.*;
import hotstone.utility.TestHelper;
import hotstone.variants.FindusWinsAt4RoundsStrategy;
import hotstone.variants.ManaProductionAlphaStone;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestDeltaStone {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new FindusWinsAt4RoundsStrategy(), new ManaProductionDeltaStone(),
                new HeroGenerationStrategyGamma(), new HeroPowerGammaStone(),
                new GenerateDeckDelta());
    }

    @Test
    public void shouldHave7ManaFindusAtGameStart(){
        int manaFindus = game.getHero(Player.FINDUS).getMana();
        assertThat(manaFindus,is(7));
    }
    @Test
    public void shouldHave7ManaPeddersenAtGameStart(){
        game.endTurn();
        int manaFindus = game.getHero(Player.PEDDERSEN).getMana();
        assertThat(manaFindus,is(7));
    }
    @Test
    public void shouldHave7ManaFindusAfterOneRound(){
        game.endTurn();
        game.endTurn();
        int manaFindus = game.getHero(Player.FINDUS).getMana();
        assertThat(manaFindus,is(7));
    }
    @Test
    public void shouldHave7ManaPeddersenAfterOneRound(){
        game.endTurn();
        game.endTurn();
        game.endTurn();
        int manaFindus = game.getHero(Player.PEDDERSEN).getMana();
        assertThat(manaFindus,is(7));
    }
    @Test
    public void shouldBe24CardsInDeckFindus(){ //Unit test with help of Strategy Pattern
        HashMap<Player,List<Card>> deckFindus = new HashMap<>();
        GenerateDeckStrategy generateDeckStrategyGamma = new GenerateDeckDelta();
        generateDeckStrategyGamma.generateDeck(Player.FINDUS,deckFindus);
        int deckSizeFindus = deckFindus.get(Player.FINDUS).size();
        assertThat(deckSizeFindus,is(24));
    }

    @Test
    public void shouldBe24CardsInDeckPeddersen(){// Unit test with help of Strategy Pattern
        HashMap<Player,List<Card>> deckPeddersen = new HashMap<>();
        GenerateDeckStrategy generateDeckStrategyGamma = new GenerateDeckDelta();
        generateDeckStrategyGamma.generateDeck(Player.PEDDERSEN,deckPeddersen);
        int deckSizePeddersen = deckPeddersen.get(Player.PEDDERSEN).size();
        assertThat(deckSizePeddersen,is(24));
    }
    @Test
    public void shouldBe21CardsInDeckAtStartGameFindus(){
        int deckSizeFindus = game.getDeckSize(Player.FINDUS);
        assertThat(deckSizeFindus,is(21));
    }
    @Test
    public void shouldBe21CardsInDeckAtStartGamePeddersen(){
        int deckSizePeddersen = game.getDeckSize(Player.PEDDERSEN);
        assertThat(deckSizePeddersen,is(21));
    }

    @Test
    public void TwoCardsShouldBe1ManaFindus(){
        String nameOfFirstCard = game.getCardInHand(Player.FINDUS, 2).getName();
        assertThat(nameOfFirstCard,
                is(either(CoreMatchers.is(GameConstants.BROWN_RICE_CARD))
                        .or(is(GameConstants.FRENCH_FRIES_CARD))));
    }
    @Test
    public void TwoCardsShouldBe2ManaOrLessFindus(){
        String nameOfFirstCard = game.getCardInHand(Player.FINDUS, 1).getName();
        assertThat(nameOfFirstCard,
                is(either(CoreMatchers.is(GameConstants.BROWN_RICE_CARD))
                        .or(is(GameConstants.FRENCH_FRIES_CARD))
                        .or(is(GameConstants.GREEN_SALAD_CARD))
                        .or(is(GameConstants.TOMATO_SALAD_CARD))));
    }
    @Test
    public void TwoCardsShouldBe4ManaOrLessFindus(){
        String nameOfFirstCard = game.getCardInHand(Player.FINDUS, 0).getName();
        assertThat(nameOfFirstCard,
                is(either(CoreMatchers.is(GameConstants.BROWN_RICE_CARD))
                        .or(is(GameConstants.FRENCH_FRIES_CARD))
                        .or(is(GameConstants.GREEN_SALAD_CARD))
                        .or(is(GameConstants.TOMATO_SALAD_CARD))
                        .or(is(GameConstants.POKE_BOWL_CARD))
                        .or(is(GameConstants.PUMPKIN_SOUP_CARD))
                        .or(is(GameConstants.NOODLE_SOUP_CARD))));
    }
    @Test
    public void brownRiceShouldHave112(){ //unit test
        GenerateDeckStrategy deckStrategy = new GenerateDeckDelta();
        HashMap<Player,List<Card>> s = new HashMap<>();
        deckStrategy.generateDeck(Player.FINDUS,s);
        System.out.println(s.get(Player.FINDUS).size());
        verfiyCardSpecs(s.get(Player.FINDUS),GameConstants.BROWN_RICE_CARD,1,1,2);
    }

    @Test
    public void frenchFriesShouldHave121(){
        GenerateDeckStrategy deckStrategy = new GenerateDeckDelta();
        HashMap<Player,List<Card>> s = new HashMap<>();
        deckStrategy.generateDeck(Player.FINDUS,s);
        verfiyCardSpecs(s.get(Player.FINDUS),GameConstants.FRENCH_FRIES_CARD,1,2,1);
    }

    @Test
    public void greenSaladCardShouldHave223(){
        GenerateDeckStrategy deckStrategy = new GenerateDeckDelta();
        HashMap<Player,List<Card>> s = new HashMap<>();
        deckStrategy.generateDeck(Player.FINDUS,s);
        verfiyCardSpecs(s.get(Player.FINDUS),GameConstants.GREEN_SALAD_CARD,2,2,3);
    }
    /**
     * .
     * .
     * .
     * osv.
    **/
    private void verfiyCardSpecs(List<? extends Card> dishDeck, String cardName, int cost, int attack, int health) {
        Card thecard = dishDeck.stream().filter(card -> card.getName().equals(cardName)).findFirst().orElse(null);
        assertThat(thecard.getManaCost(), is(cost));
        assertThat(thecard.getAttack(), is(attack));
        assertThat(thecard.getHealth(), is(health));
        assertThat(dishDeck
                        .stream()
                        .filter( card -> card.getName().equals(cardName))
                        .count(),
                is(2L));
    }
}
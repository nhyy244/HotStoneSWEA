package hotstone.standard;

import hotstone.TestStubs.FixedEffectStrategyTest;
import hotstone.framework.Player;
import hotstone.variants.factory.EtaStoneFactory;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hotstone.framework.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestEtaStone {

    private MutableGame game;

    @BeforeEach
    public void setUp(){
        game = new StandardHotStoneGame(new EtaStoneFactory());
    }

    @Test
    public void brownRiceShouldDoOneDamageToFindus(){
        Card brownRice = new CardImpl(GameConstants.BROWN_RICE_CARD,1,1,1,false,Player.PEDDERSEN);
        game.endTurn(); //Now its peddersens turn
        int healthAfterEffectOfBrownRice = game.getHero(Player.FINDUS).getHealth()-1;
        game.playCard(Player.PEDDERSEN,brownRice);
        assertThat(game.getHero(Player.FINDUS).getHealth(),is(healthAfterEffectOfBrownRice));
    }
    @Test
    public void brownRiceShouldDoOneDamageToPeddersen(){
        Card brownRice = new CardImpl(GameConstants.BROWN_RICE_CARD,1,1,1,false,Player.FINDUS);
        int healthAfterEffectOfBrownRice = game.getHero(Player.PEDDERSEN).getHealth()-1;
        game.playCard(Player.FINDUS,brownRice);
        assertThat(game.getHero(Player.PEDDERSEN).getHealth(),is(healthAfterEffectOfBrownRice));
    }

   /* public void tomatoSaladShouldAddOneAttackToRandomOwnMininon(){
        CardImpl brownRice = new CardImpl(GameConstants.BROWN_RICE_CARD,1,1,1,false,Player.FINDUS);
        GameDouble gameDouble = new GameDouble(new EtaStoneFactory());
        FixedEffectStrategyTest fixedEffectStrategyTest = new FixedEffectStrategyTest(brownRice);
        Card tomatoSalad = new CardImpl(GameConstants.TOMATO_SALAD_CARD,2,2,2,false,Player.FINDUS);
        gameDouble.endTurn();
        gameDouble.endTurn();
        gameDouble.playCard(Player.FINDUS,brownRice);
        assertThat(brownRice.getAttack(),is(1));
        gameDouble.playCard(Player.FINDUS,tomatoSalad);
        fixedEffectStrategyTest.applyCardEffects gameDouble,Player.FINDUS,tomatoSalad);
        assertThat(brownRice.getAttack(),is(2));
    }*/
    @Test
    public void tomatoSaladShouldAddOneAttackToRandomOwnMininon2(){
        CardImpl frenchFries = new CardImpl(GameConstants.FRENCH_FRIES_CARD, 1, 1, 1, false, Player.FINDUS);
        CardImpl brownRice = new CardImpl(GameConstants.BROWN_RICE_CARD,1,1,1,false,Player.FINDUS);
        Card tomatoSalad = new CardImpl(GameConstants.TOMATO_SALAD_CARD,2,2,2,false,Player.FINDUS);
        game.endTurn();
        game.endTurn();
        game.playCard(Player.FINDUS,brownRice);
        assertThat(brownRice.getAttack(),is(1));
        game.playCard(Player.FINDUS,frenchFries);
        assertThat(brownRice.getAttack(),is(1));
        game.playCard(Player.FINDUS,tomatoSalad);
        assertThat(2,
                is(either(CoreMatchers.is(frenchFries.getAttack()))
                        .or(is(brownRice.getAttack()))));
    }
    @Test
    public void tomatoSaladShouldAddOneAttackToRandomOwnMininon3(){
        CardImpl brownRice = new CardImpl(GameConstants.BROWN_RICE_CARD,1,1,1,false,Player.FINDUS);
        Card tomatoSalad = new CardImpl(GameConstants.TOMATO_SALAD_CARD,2,2,2,false,Player.FINDUS);
        game.endTurn();
        game.endTurn();
        game.playCard(Player.FINDUS,brownRice);
        assertThat(brownRice.getAttack(),is(1));
        game.playCard(Player.FINDUS,tomatoSalad);
        assertThat(brownRice.getAttack(),is(2));
    }
    @Test
    public void pokeBowlShouldAdd2HealthToOwnHero(){
        Card pokeBowl = new CardImpl(GameConstants.POKE_BOWL_CARD,1,1,1,false,Player.FINDUS);
        game.playCard(Player.FINDUS,pokeBowl);
        assertThat(game.getHero(Player.FINDUS).getHealth(),is(23));
    }
    @Test
    public void noodleSoupShouldDrawACard(){
        Card noodleSoup = new CardImpl(GameConstants.NOODLE_SOUP_CARD,1,1,1,false,Player.FINDUS);
        assertThat(game.getHandSize(Player.FINDUS),is(3));
        assertThat(game.getDeckSize(Player.FINDUS),is(21));
        game.playCard(Player.FINDUS,noodleSoup);
        assertThat(game.getHandSize(Player.FINDUS),is(4));
        assertThat(game.getDeckSize(Player.FINDUS),is(20));
    }
    @Test
    public void chickenCurryShouldKillRandomOpponentMinion(){
        Card chickenCurry = new CardImpl(GameConstants.CHICKEN_CURRY_CARD,1,1,1,false,Player.FINDUS);
        Card randomCard = new CardImpl("random",1,1,1,false,Player.PEDDERSEN);

        game.endTurn();
        game.playCard(Player.PEDDERSEN,randomCard);
        game.playCard(Player.PEDDERSEN,randomCard);
        assertThat(game.getFieldSize(Player.PEDDERSEN),is(2));
        game.endTurn();
        game.playCard(Player.FINDUS,chickenCurry);
        assertThat(game.getFieldSize(Player.PEDDERSEN),is(1));
    }
    @Test
    public void beefBurgerShouldAdd2AttackToRandomOpponentMinion(){
        Card beefBurger = new CardImpl(GameConstants.BEEF_BURGER_CARD,1,1,1,false,Player.FINDUS);
        Card randomCard = new CardImpl("random",1,1,1,false,Player.PEDDERSEN);

        game.endTurn();
        game.playCard(Player.PEDDERSEN,randomCard);
        game.playCard(Player.PEDDERSEN,randomCard);
        game.endTurn();
        game.playCard(Player.FINDUS,beefBurger);
        assertThat(3,
                is(either(CoreMatchers.is(game.getCardInField(Player.PEDDERSEN,0).getAttack()))
                        .or(is(game.getCardInField(Player.PEDDERSEN,1).getAttack()))));
    }


}

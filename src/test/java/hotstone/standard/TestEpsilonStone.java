package hotstone.standard;

import hotstone.TestStubs.FixedEffectStrategyTest;
import hotstone.framework.Game;
import hotstone.framework.Hero;
import hotstone.framework.Player;
import hotstone.utility.TestHelper;
import hotstone.variants.factory.EpsilonStoneFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hotstone.framework.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestEpsilonStone {

    private Game game;

    @BeforeEach
    public void setUp(){
        game = new StandardHotStoneGame(new EpsilonStoneFactory());
    }

    @Test
    public void findusHeroShouldBeThaiChef(){
        Hero h = game.getHero(Player.FINDUS);
        assertThat(h.getType(),is(GameConstants.FRENCH_CHEF_HERO_TYPE));
    }
    @Test
    public void peddersenHeroShouldBeDanishChef(){
        Hero h = game.getHero(Player.PEDDERSEN);
        assertThat(h.getType(),is(GameConstants.ITALIAN_CHEF_HERO_TYPE));
    }
    @Test
    public void findusHeroPowerShouldBeChili(){
        HeroImpl h = (HeroImpl) game.getHero(Player.FINDUS);
        assertThat(h.getHeroPower(),is("Redwine"));
    }
    @Test
    public void peddersenHeroPowerShouldBeSovs(){
        HeroImpl h = (HeroImpl) game.getHero(Player.PEDDERSEN);
        assertThat(h.getHeroPower(),is("Pasta"));
    }
    @Test
    public void findusHeroPowerShouldReduceMinionAttackBy2(){
        CardImpl c =new CardImpl(GameConstants.TRES_CARD, 3, 3, 3, false, Player.PEDDERSEN);
        EffectStrategy fixedHeroPower = new FixedEffectStrategyTest(c);
        fixedHeroPower.usePower(Player.FINDUS,game);
        assertThat(c.getAttack(),is(1));
    }
    @Test
    public void peddersenHeroPowerShouldIncreaseOwnMinionAttackBy2(){
        CardImpl c =new CardImpl(GameConstants.TRES_CARD, 3, 3, 3, false, Player.PEDDERSEN);
        EffectStrategy fixedHeroPower = new FixedEffectStrategyTest(c);
        fixedHeroPower.usePower(Player.PEDDERSEN,game);
        assertThat(c.getAttack(),is(5));
    }
    @Test
    public void findusShouldBeWinner(){
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
        Status attackHero1 =  game.attackHero(Player.FINDUS,game.getCardInField(Player.FINDUS,2));
        assertThat(attackHero1,is(Status.OK));
        assertThat( ((StandardHotStoneGame)game).getTotalAttackOutput(Player.FINDUS),is(3));
        game.attackHero(Player.FINDUS,game.getCardInField(Player.FINDUS,1));
        assertThat(((StandardHotStoneGame)game).getTotalAttackOutput(Player.FINDUS),is(6));
        assertThat(game.getWinner(),is(nullValue()));
        game.attackHero(Player.FINDUS,game.getCardInField(Player.FINDUS,0));
        assertThat(((StandardHotStoneGame)game).getTotalAttackOutput(Player.FINDUS),is(11));
        assertThat(game.getWinner(),is(Player.FINDUS));
    }
    @Test
    public void attackDamageShouldBeDifferentForPlayers(){
        game.playCard(Player.FINDUS,game.getCardInHand(Player.FINDUS,0)); //findus has 0 mana now, att =3
        game.endTurn();
        game.playCard(Player.PEDDERSEN,game.getCardInHand(Player.PEDDERSEN,0)); //findus has 0 mana now, att =3
        game.endTurn();
        game.attackHero(Player.FINDUS,game.getCardInField(Player.FINDUS,0));
        game.endTurn();
        game.attackHero(Player.PEDDERSEN,game.getCardInField(Player.PEDDERSEN,0));
        assertThat( ((StandardHotStoneGame)game).getTotalAttackOutput(Player.FINDUS),is(3));
        assertThat( ((StandardHotStoneGame)game).getTotalAttackOutput(Player.PEDDERSEN),is(3));

    }

}

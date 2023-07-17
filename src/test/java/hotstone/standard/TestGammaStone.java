package hotstone.standard;

import hotstone.framework.Game;
import hotstone.framework.Hero;
import hotstone.framework.Player;
import hotstone.variants.factory.GammaStoneFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hotstone.framework.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestGammaStone {

    private Game game;

    @BeforeEach
    public void setUp(){
        game = new StandardHotStoneGame(new GammaStoneFactory());
    }

    @Test
    public void findusHeroShouldBeThaiChef(){
        Hero h = game.getHero(Player.FINDUS);
        assertThat(h.getType(),is(GameConstants.THAI_CHEF_HERO_TYPE));
    }
    @Test
    public void peddersenHeroShouldBeDanishChef(){
        Hero h = game.getHero(Player.PEDDERSEN);
        assertThat(h.getType(),is(GameConstants.DANISH_CHEF_HERO_TYPE));
    }
    @Test
    public void findusHeroPowerShouldBeChili(){
        HeroImpl h = (HeroImpl) game.getHero(Player.FINDUS);
        assertThat(h.getHeroPower(),is("Chili"));
    }
    @Test
    public void peddersenHeroPowerShouldBeSovs(){
        HeroImpl h = (HeroImpl) game.getHero(Player.PEDDERSEN);
        assertThat(h.getHeroPower(),is("Sovs"));
    }
    @Test
    public void findusHeroPowerShouldDeal2DamageToOpponentHero(){
        Status ok = game.usePower(Player.FINDUS);
        assertThat(ok, is(Status.OK));
        HeroImpl h = (HeroImpl) game.getHero(Player.PEDDERSEN);
        assertThat(h.getHealth(),is(19)); //21-2
    }
    @Test
    public void peddersenHeroPowerShouldSummon11MinionOnBoard(){
        game.endTurn();//Pedersens turn
        Status ok = game.usePower(Player.PEDDERSEN);
        assertThat(ok, is(Status.OK));
        Card peddersensHeroPowerMinion = game.getCardInField(Player.PEDDERSEN,0);
        assertThat(peddersensHeroPowerMinion.getName(),is("Sovs"));
        assertThat(peddersensHeroPowerMinion.getHealth(),is(1));
        assertThat(peddersensHeroPowerMinion.getAttack(),is(1));
        assertThat(peddersensHeroPowerMinion.getManaCost(),is(0));

        assertThat(peddersensHeroPowerMinion.isActive(),is(false)); //minion should be inactive
    }

}

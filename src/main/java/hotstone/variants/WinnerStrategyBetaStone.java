package hotstone.variants;

import hotstone.framework.Hero;
import hotstone.framework.Player;
import hotstone.framework.WinnerStrategy;
import hotstone.standard.StandardHotStoneGame;

public class WinnerStrategyBetaStone implements WinnerStrategy {
    @Override
    public Player getWinner(StandardHotStoneGame game) {
        Hero findusHero = game.getHero(Player.FINDUS);
        Hero peddersenHero = game.getHero(Player.PEDDERSEN);
        if(findusHero.getHealth()<=0){
            return Player.PEDDERSEN;
        }
        if(peddersenHero.getHealth()<=0){
            return Player.FINDUS;
        }
        return null;
    }
}

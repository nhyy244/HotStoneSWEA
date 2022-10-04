package hotstone.variants.beta;

import hotstone.framework.*;
import hotstone.standard.StandardHotStoneGame;

import java.util.HashMap;

public class WinnerStrategyBetaStone implements WinnerStrategy {
    @Override
    public Player getWinner(Game game) {
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

    @Override
    public void setTotalAttackOutput(Player who, Card attackingCard, HashMap<Player, Integer> totalAttackOutputMap) {
    }
}

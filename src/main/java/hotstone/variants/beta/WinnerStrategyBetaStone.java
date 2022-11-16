package hotstone.variants.beta;

import hotstone.framework.*;
import hotstone.standard.StandardHotStoneGame;

import java.util.HashMap;

public class WinnerStrategyBetaStone implements WinnerStrategy {
    @Override
    public Player getWinner(MutableGame game) {
        Hero findusHero = game.getHero(Player.FINDUS);
        Hero peddersenHero = game.getHero(Player.PEDDERSEN);
        Player winner = null;
        System.out.println("hello");
        if(findusHero.getHealth()<=0){
            //game.getObserverHandler().notifyGameWon(Player.PEDDERSEN);
            winner = Player.PEDDERSEN;
            System.out.println("hello2");
        }
        else if(peddersenHero.getHealth()<=0){
            //game.getObserverHandler().notifyGameWon(Player.FINDUS);
            winner = Player.FINDUS;
            System.out.println("hello3");
        }
        //Player winner = game.getPlayerInTurn() ? game.getHero(game.getPlayerInTurn()).getHealth()<=0 : null;
        return winner;
    }

    @Override
    public void setTotalAttackOutput(Player who, Card attackingCard, HashMap<Player, Integer> totalAttackOutputMap) {
    }
}

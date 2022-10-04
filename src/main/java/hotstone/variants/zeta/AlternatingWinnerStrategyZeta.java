package hotstone.variants.zeta;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.WinnerStrategy;
import hotstone.standard.StandardHotStoneGame;

import java.util.HashMap;

public class AlternatingWinnerStrategyZeta implements WinnerStrategy{
    private WinnerStrategy betaWinnerStrategy;
    private WinnerStrategy epsilonWinnerStrategy;
    private WinnerStrategy currentState;
    int totalAttackOutputFindus =0;
    int totalAttackOutputPeddersen =0;



    public AlternatingWinnerStrategyZeta(WinnerStrategy betaWinnerStrategy,
                                         WinnerStrategy epsilonWinnerStrategy){
        this.betaWinnerStrategy=betaWinnerStrategy;
        this.epsilonWinnerStrategy=epsilonWinnerStrategy;
        this.currentState=null;
    }

    @Override
    public Player getWinner(Game game) {
        if(game.getTurnNumber()<=6){
            this.currentState=betaWinnerStrategy;
        }
        else{
            this.currentState=epsilonWinnerStrategy;
        }
        return currentState.getWinner(game);
    }

    @Override
    public void setTotalAttackOutput(Player who, Card attackingCard, HashMap<Player, Integer> totalAttackOutputMap) {
        if(who.equals(Player.FINDUS)){
            totalAttackOutputFindus+=attackingCard.getAttack();
            totalAttackOutputMap.put(who,totalAttackOutputFindus);
        }
        else{
            totalAttackOutputPeddersen+=attackingCard.getAttack();
            totalAttackOutputMap.put(who,totalAttackOutputPeddersen);
        }
    }

}

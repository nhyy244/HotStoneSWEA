package hotstone.variants.zeta;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.WinnerStrategy;
import hotstone.standard.StandardHotStoneGame;

public class AlternatingWinnerStrategy implements WinnerStrategy{
    private WinnerStrategy betaWinnerStrategy;
    private WinnerStrategy epsilonWinnerStrategy;
    private WinnerStrategy currentState;

    public AlternatingWinnerStrategy(WinnerStrategy betaWinnerStrategy,
                                     WinnerStrategy epsilonWinnerStrategy){
        this.betaWinnerStrategy=betaWinnerStrategy;
        this.epsilonWinnerStrategy=epsilonWinnerStrategy;
        this.currentState=null;
    }

    @Override
    public Player getWinner(Game game) {
        if(game.getTurnNumber()>6){
            this.currentState=epsilonWinnerStrategy;
        }
        else{
            this.currentState=betaWinnerStrategy;
        }
        return currentState.getWinner(game);
    }
}

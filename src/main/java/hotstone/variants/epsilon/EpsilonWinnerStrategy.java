package hotstone.variants.epsilon;

import hotstone.framework.*;
import hotstone.standard.HeroImpl;
import hotstone.standard.StandardHotStoneGame;

import java.util.ArrayList;
import java.util.List;

public class EpsilonWinnerStrategy implements WinnerStrategy {
    @Override
    public Player getWinner(Game game) {
        Player playerInTurn = game.getPlayerInTurn();

        if(((StandardHotStoneGame)game).getTotalAttackOutput(playerInTurn) >=7){
          return playerInTurn;
        }
      return null;
    }

}

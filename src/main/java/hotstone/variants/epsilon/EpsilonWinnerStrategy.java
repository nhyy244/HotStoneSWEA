package hotstone.variants.epsilon;

import hotstone.framework.*;
import hotstone.standard.HeroImpl;
import hotstone.standard.StandardHotStoneGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EpsilonWinnerStrategy implements WinnerStrategy {
    private int totalAttackOutputFindus=0;
    private int totalAttackOutputPeddersen=0;
    @Override
    public Player getWinner(Game game) {
        if(((StandardHotStoneGame)game).getTotalAttackOutput(game.getPlayerInTurn()) >=7){
          return game.getPlayerInTurn();
        }
      return null;
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

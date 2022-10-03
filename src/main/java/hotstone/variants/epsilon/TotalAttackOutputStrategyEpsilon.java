package hotstone.variants.epsilon;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.TotalAttackOutputStrategy;

import java.util.HashMap;

public class TotalAttackOutputStrategyEpsilon implements TotalAttackOutputStrategy {
    private int totalAttackOutputFindus=0;
    private int totalAttackOutputPeddersen=0;
    @Override
    public void setTotalAttackOutput(Player who, Card attackingCard,HashMap<Player,Integer> totalAttackOutputMap ) {
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

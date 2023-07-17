package hotstone.framework;

import java.util.HashMap;

public interface TotalAttackOutputStrategy {
    void setTotalAttackOutput(Player who, Card attackingCard,HashMap<Player,Integer> totalAttackOutputMap );
}

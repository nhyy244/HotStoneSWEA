package hotstone.framework;

import hotstone.standard.StandardHotStoneGame;

import java.util.HashMap;

public interface WinnerStrategy {
    Player getWinner(MutableGame game);
    void setTotalAttackOutput(Player who, Card attackingCard,HashMap<Player,Integer> totalAttackOutputMap );
}


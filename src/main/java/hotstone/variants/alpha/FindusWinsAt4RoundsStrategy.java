package hotstone.variants.alpha;

import hotstone.framework.*;
import hotstone.standard.StandardHotStoneGame;

import java.util.HashMap;

public class FindusWinsAt4RoundsStrategy implements WinnerStrategy {

    @Override
    public Player getWinner(MutableGame game) {
        return (game.getTurnNumber()>=8)? Player.FINDUS : null;
    }

    @Override
    public void setTotalAttackOutput(Player who, Card attackingCard, HashMap<Player, Integer> totalAttackOutputMap) {

    }
}

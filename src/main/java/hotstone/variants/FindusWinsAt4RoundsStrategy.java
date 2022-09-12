package hotstone.variants;

import hotstone.framework.Player;
import hotstone.framework.WinnerStrategy;
import hotstone.standard.StandardHotStoneGame;

public class FindusWinsAt4RoundsStrategy implements WinnerStrategy {


    @Override
    public Player getWinner(StandardHotStoneGame game) {
        return (game.getTurnNumber()>=8)? Player.FINDUS : null;
    }
}

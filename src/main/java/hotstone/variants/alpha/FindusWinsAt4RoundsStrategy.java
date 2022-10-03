package hotstone.variants.alpha;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.WinnerStrategy;
import hotstone.standard.StandardHotStoneGame;

public class FindusWinsAt4RoundsStrategy implements WinnerStrategy {

    @Override
    public Player getWinner(Game game) {
        return (game.getTurnNumber()>=8)? Player.FINDUS : null;
    }
}

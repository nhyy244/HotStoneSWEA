package hotstone.framework;

import hotstone.standard.StandardHotStoneGame;

public interface WinnerStrategy {
    Player getWinner(StandardHotStoneGame game);
}

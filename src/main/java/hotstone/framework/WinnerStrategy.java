package hotstone.framework;

import hotstone.standard.StandardHotStoneGame;

import java.util.HashMap;

public interface WinnerStrategy {
    Player getWinner(Game game);
}

package hotstone.framework;

import hotstone.standard.HeroImpl;
import hotstone.standard.StandardHotStoneGame;

import java.util.HashMap;

public interface HeroGenerationStrategy {

    void generateHeroes(Player who, HashMap<Player, HeroImpl> heroes);
}

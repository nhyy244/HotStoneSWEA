package hotstone.variants;

import hotstone.framework.HeroGenerationStrategy;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;

import java.util.HashMap;

public class HeroGenerationStrategyAlpha implements HeroGenerationStrategy {
    @Override
    public void generateHeroes(Player who, HashMap<Player,HeroImpl> heroes) {
        HeroImpl tempHero;
        if(who == Player.FINDUS) {
            tempHero = new HeroImpl(GameConstants.BABY_HERO_TYPE,who,"CUTE");
        }else{
            tempHero = new HeroImpl(GameConstants.BABY_HERO_TYPE,who,"CUTE");
        }
        heroes.put(who, tempHero);
    }
}

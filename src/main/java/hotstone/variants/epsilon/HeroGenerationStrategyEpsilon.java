package hotstone.variants.epsilon;

import hotstone.framework.HeroGenerationStrategy;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;

import java.util.HashMap;

public class HeroGenerationStrategyEpsilon implements HeroGenerationStrategy {
    @Override
    public void generateHeroes(Player who, HashMap<Player, HeroImpl> heroes) {
        HeroImpl tempHero;
        if(who == Player.FINDUS) {
            tempHero = new HeroImpl(GameConstants.FRENCH_CHEF_HERO_TYPE,who,"Redwine");
        }else{
            tempHero = new HeroImpl(GameConstants.ITALIAN_CHEF_HERO_TYPE,who,"Pasta");
        }
        heroes.put(who, tempHero);
    }
}

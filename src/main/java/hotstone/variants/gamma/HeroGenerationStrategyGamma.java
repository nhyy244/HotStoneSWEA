package hotstone.variants.gamma;

import hotstone.framework.HeroGenerationStrategy;
import hotstone.framework.MutableHero;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;

import java.util.HashMap;

public class HeroGenerationStrategyGamma implements HeroGenerationStrategy {
    @Override
    public void generateHeroes(Player who, HashMap<Player, MutableHero> heroes) {
        MutableHero tempHero;
        if(who == Player.FINDUS) {
            tempHero = new HeroImpl(GameConstants.THAI_CHEF_HERO_TYPE,who,"Chili");
        }else{
            tempHero = new HeroImpl(GameConstants.DANISH_CHEF_HERO_TYPE,who,"Sovs");
        }
        heroes.put(who, tempHero);
    }
}

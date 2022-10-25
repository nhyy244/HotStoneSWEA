package hotstone.variants.semi;

import hotstone.framework.HeroGenerationStrategy;
import hotstone.framework.MutableHero;
import hotstone.framework.Player;
import hotstone.framework.WinnerStrategy;
import hotstone.variants.epsilon.HeroGenerationStrategyEpsilon;
import hotstone.variants.gamma.HeroGenerationStrategyGamma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class HeroStrategySemiStone implements HeroGenerationStrategy {

    @Override
    public void generateHeroes(Player who, HashMap<Player, MutableHero> heroes) {
        //forkert, bare lav en list af alle 4 heroes og assign dem tilfældigt til who.
        HeroGenerationStrategy heroGenerationStrategyGamma = new HeroGenerationStrategyGamma();
        HeroGenerationStrategy heroGenerationStrategyEpsilon = new HeroGenerationStrategyEpsilon();
        ArrayList<HeroGenerationStrategy> heroGenerations = new ArrayList<>();
        heroGenerations.add(heroGenerationStrategyEpsilon);
        heroGenerations.add(heroGenerationStrategyGamma);
        Random r = new Random();
        heroGenerations.get(r.nextInt(heroGenerations.size())).generateHeroes(who,heroes);

    }
}

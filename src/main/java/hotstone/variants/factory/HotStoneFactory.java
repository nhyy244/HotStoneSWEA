package hotstone.variants.factory;

import hotstone.framework.*;

public interface HotStoneFactory {

    WinnerStrategy createWinnerStrategy();
    GenerateDeckStrategy createGenerateDeckStrategy();
    HeroGenerationStrategy createHeroGenerationStrategy();
    HeroPowerStrategy createHeroPowerStrategy();
    ManaProductionStrategy createManaProductionStrategy();
}

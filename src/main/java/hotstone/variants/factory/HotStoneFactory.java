package hotstone.variants.factory;

import hotstone.framework.*;

public interface HotStoneFactory {

    WinnerStrategy createWinnerStrategy();
    GenerateDeckStrategy createGenerateDeckStrategy();
    HeroGenerationStrategy createHeroGenerationStrategy();
    EffectStrategy createEffectStrategy();
    ManaProductionStrategy createManaProductionStrategy();
}

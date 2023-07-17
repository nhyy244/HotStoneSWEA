package hotstone.variants.factory;

import hotstone.framework.*;
import hotstone.variants.alpha.*;

public class AlphaGUIStoneFactory implements HotStoneFactory{
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new FindusWinsAt4RoundsStrategy();
    }

    @Override
    public GenerateDeckStrategy createGenerateDeckStrategy() {
        return new GenerateDeckStrategyAlpha();
    }

    @Override
    public HeroGenerationStrategy createHeroGenerationStrategy() {
        return new HeroGenerationStrategyAlpha();
    }

    @Override
    public EffectStrategy createEffectStrategy() {
        return new EffectStrategyAlpha();
    }

    @Override
    public ManaProductionStrategy createManaProductionStrategy() {
        return new ManaProductionAlpaGUITest();
    }
}

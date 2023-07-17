package hotstone.variants.factory;

import hotstone.framework.*;
import hotstone.variants.alpha.GenerateDeckStrategyAlpha;
import hotstone.variants.alpha.ManaProductionAlphaStone;
import hotstone.variants.epsilon.EpsilonWinnerStrategy;
import hotstone.variants.epsilon.HeroGenerationStrategyEpsilon;
import hotstone.variants.epsilon.EffectStrategyEpsilon;

public class EpsilonStoneFactory implements HotStoneFactory {
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new EpsilonWinnerStrategy();
    }

    @Override
    public GenerateDeckStrategy createGenerateDeckStrategy() {
        return new GenerateDeckStrategyAlpha();
    }

    @Override
    public HeroGenerationStrategy createHeroGenerationStrategy() {
        return new HeroGenerationStrategyEpsilon();
    }

    @Override
    public EffectStrategy createEffectStrategy() {
        return new EffectStrategyEpsilon();
    }

    @Override
    public ManaProductionStrategy createManaProductionStrategy() {
        return new ManaProductionAlphaStone();
    }
}

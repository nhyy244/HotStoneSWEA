package hotstone.variants.factory;

import hotstone.framework.*;
import hotstone.variants.alpha.FindusWinsAt4RoundsStrategy;
import hotstone.variants.alpha.HeroGenerationStrategyAlpha;
import hotstone.variants.alpha.EffectStrategyAlpha;
import hotstone.variants.delta.GenerateDeckDelta;
import hotstone.variants.delta.ManaProductionDeltaStone;

public class DeltaStoneFactory implements HotStoneFactory{
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new FindusWinsAt4RoundsStrategy();
    }

    @Override
    public GenerateDeckStrategy createGenerateDeckStrategy() {
        return new GenerateDeckDelta();
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
        return new ManaProductionDeltaStone();
    }
}

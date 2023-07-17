package hotstone.variants.factory;

import hotstone.framework.*;
import hotstone.variants.alpha.EffectStrategyAlpha;
import hotstone.variants.beta.ManaProductionBetaStone;
import hotstone.variants.beta.WinnerStrategyBetaStone;
import hotstone.variants.delta.GenerateDeckDelta;
import hotstone.variants.semi.HeroStrategySemiStone;

public class SemiStoneFactory implements HotStoneFactory{
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new WinnerStrategyBetaStone();
    }

    @Override
    public GenerateDeckStrategy createGenerateDeckStrategy() {
        return new GenerateDeckDelta();
    }

    @Override
    public HeroGenerationStrategy createHeroGenerationStrategy() {
        return new HeroStrategySemiStone();
    }

    @Override
    public EffectStrategy createEffectStrategy() {
        return new EffectStrategyAlpha();
    }

    @Override
    public ManaProductionStrategy createManaProductionStrategy() {
        return new ManaProductionBetaStone();
    }
}

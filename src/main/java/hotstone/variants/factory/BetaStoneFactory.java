package hotstone.variants.factory;

import hotstone.framework.*;
import hotstone.variants.alpha.GenerateDeckStrategyAlpha;
import hotstone.variants.alpha.HeroGenerationStrategyAlpha;
import hotstone.variants.alpha.EffectStrategyAlpha;
import hotstone.variants.beta.ManaProductionBetaStone;
import hotstone.variants.beta.WinnerStrategyBetaStone;

public class BetaStoneFactory implements HotStoneFactory{
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new WinnerStrategyBetaStone();
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
        return new ManaProductionBetaStone();
    }
}

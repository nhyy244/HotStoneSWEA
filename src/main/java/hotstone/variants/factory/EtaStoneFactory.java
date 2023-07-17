package hotstone.variants.factory;

import hotstone.framework.*;
import hotstone.variants.alpha.FindusWinsAt4RoundsStrategy;
import hotstone.variants.alpha.HeroGenerationStrategyAlpha;
import hotstone.variants.delta.GenerateDeckDelta;
import hotstone.variants.delta.ManaProductionDeltaStone;
import hotstone.variants.eta.EffectStrategyEta;
import hotstone.variants.eta.GenerateDeckStrategyEta;

public class EtaStoneFactory implements HotStoneFactory{
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new FindusWinsAt4RoundsStrategy();
    }

    @Override
    public GenerateDeckStrategy createGenerateDeckStrategy() {
        return  new GenerateDeckStrategyEta();
    }

    @Override
    public HeroGenerationStrategy createHeroGenerationStrategy() {
        return new HeroGenerationStrategyAlpha();
    }

    @Override
    public EffectStrategy createEffectStrategy() {
        return new EffectStrategyEta();
    }

    @Override
    public ManaProductionStrategy createManaProductionStrategy() {
        return new ManaProductionDeltaStone();
    }
}

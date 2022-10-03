package hotstone.variants.factory;

import hotstone.framework.*;
import hotstone.variants.alpha.FindusWinsAt4RoundsStrategy;
import hotstone.variants.alpha.GenerateDeckStrategyAlpha;
import hotstone.variants.alpha.ManaProductionAlphaStone;
import hotstone.variants.gamma.HeroGenerationStrategyGamma;
import hotstone.variants.gamma.HeroPowerGammaStone;

public class GammaStoneFactory implements HotStoneFactory{
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
        return new HeroGenerationStrategyGamma();
    }

    @Override
    public HeroPowerStrategy createHeroPowerStrategy() {
        return new HeroPowerGammaStone();
    }

    @Override
    public ManaProductionStrategy createManaProductionStrategy() {
        return new ManaProductionAlphaStone();
    }
}

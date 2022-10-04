package hotstone.variants.factory;

import hotstone.framework.*;
import hotstone.variants.alpha.HeroGenerationStrategyAlpha;
import hotstone.variants.alpha.EffectStrategyAlpha;
import hotstone.variants.alpha.ManaProductionAlphaStone;
import hotstone.variants.beta.WinnerStrategyBetaStone;
import hotstone.variants.epsilon.EpsilonWinnerStrategy;
import hotstone.variants.zeta.AlternatingWinnerStrategyZeta;
import hotstone.variants.zeta.GenerateDeckStrategyZeta;

public class ZetaStoneFactory implements HotStoneFactory{
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new AlternatingWinnerStrategyZeta(new WinnerStrategyBetaStone(),new EpsilonWinnerStrategy());
    }

    @Override
    public GenerateDeckStrategy createGenerateDeckStrategy() {
        return new GenerateDeckStrategyZeta();
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
        return new ManaProductionAlphaStone();
    }
}

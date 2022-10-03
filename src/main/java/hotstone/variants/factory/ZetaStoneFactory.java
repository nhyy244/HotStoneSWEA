package hotstone.variants.factory;

import hotstone.framework.*;
import hotstone.variants.alpha.HeroGenerationStrategyAlpha;
import hotstone.variants.alpha.HeroPowerStrategyAlpha;
import hotstone.variants.alpha.ManaProductionAlphaStone;
import hotstone.variants.beta.WinnerStrategyBetaStone;
import hotstone.variants.epsilon.EpsilonWinnerStrategy;
import hotstone.variants.zeta.AlternatingWinnerStrategy;
import hotstone.variants.zeta.GenerateDeckStrategyZeta;

public class ZetaStoneFactory implements HotStoneFactory{
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new AlternatingWinnerStrategy(new WinnerStrategyBetaStone(),new EpsilonWinnerStrategy());
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
    public HeroPowerStrategy createHeroPowerStrategy() {
        return new HeroPowerStrategyAlpha();
    }

    @Override
    public ManaProductionStrategy createManaProductionStrategy() {
        return new ManaProductionAlphaStone();
    }
}

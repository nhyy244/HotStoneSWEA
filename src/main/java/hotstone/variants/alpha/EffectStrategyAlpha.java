package hotstone.variants.alpha;

import hotstone.framework.*;

public class EffectStrategyAlpha implements EffectStrategy {
    @Override
    public void usePower(Player who, MutableGame game) {
        System.out.println("Uses cute. Does nothing");
    }

    @Override
    public void applyCardEffects(MutableGame game, Player who, Card card) {

    }
}

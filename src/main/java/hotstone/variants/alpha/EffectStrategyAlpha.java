package hotstone.variants.alpha;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.EffectStrategy;
import hotstone.framework.Player;

public class EffectStrategyAlpha implements EffectStrategy {
    @Override
    public void usePower(Player who, Game game) {
        System.out.println("Uses cute. Does nothing");
    }

    @Override
    public void applyCardEffects(Game game, Player who, Card card) {

    }
}

package hotstone.variants.alpha;

import hotstone.framework.Game;
import hotstone.framework.HeroPowerStrategy;
import hotstone.framework.Player;
import hotstone.framework.Status;

public class HeroPowerStrategyAlpha implements HeroPowerStrategy {
    @Override
    public void usePower(Player who, Game game) {
        System.out.println("Uses cute. Does nothing");
    }
}

package hotstone.variants.alpha;

import hotstone.framework.ManaProductionStrategy;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;

public class ManaProductionAlpaGUITest implements ManaProductionStrategy {
    @Override
    public void manaProduction(Player who, MutableGame game) {
        game.deltaHeroMana(who,25);
    }
}

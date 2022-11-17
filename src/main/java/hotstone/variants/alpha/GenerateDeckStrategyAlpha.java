package hotstone.variants.alpha;

import hotstone.framework.Card;
import hotstone.framework.GenerateDeckStrategy;
import hotstone.framework.MutableCard;
import hotstone.framework.Player;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GenerateDeckStrategyAlpha implements GenerateDeckStrategy {
    @Override
    public void generateDeck(Player who, HashMap<Player, List<MutableCard>> deck) {
        ArrayList<MutableCard> Deck1 = new ArrayList<>();
        Deck1.add(new CardImpl(GameConstants.UNO_CARD, 1, 1, 1, false, who));
        Deck1.add(new CardImpl(GameConstants.DOS_CARD, 2, 2, 2, false, who));
        Deck1.add(new CardImpl(GameConstants.TRES_CARD, 3, 3, 3, false, who));
        Deck1.add(new CardImpl(GameConstants.CUATRO_CARD, 2, 3, 1, false, who));
        Deck1.add(new CardImpl(GameConstants.CINCO_CARD, 3, 5, 1, false, who));
        Deck1.add(new CardImpl(GameConstants.SEIS_CARD, 2, 1, 3, false, who));
        Deck1.add(new CardImpl(GameConstants.SIETE_CARD, 3, 4, 2, false, who));
        deck.put(who, Deck1);
    }
}

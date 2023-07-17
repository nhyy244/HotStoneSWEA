package hotstone.variants.zeta;

import hotstone.framework.Card;
import hotstone.framework.GenerateDeckStrategy;
import hotstone.framework.MutableCard;
import hotstone.framework.Player;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GenerateDeckStrategyZeta implements GenerateDeckStrategy {
    @Override
    public void generateDeck(Player who, HashMap<Player, List<MutableCard>> deck) {
        ArrayList<MutableCard> deck1=new ArrayList<>();
        for(int i=0;i<7;i++){
            deck1.add(new CardImpl(GameConstants.CINCO_CARD, 3, 5, 1, false, who));
        }
        deck.put(who,deck1);
    }
}

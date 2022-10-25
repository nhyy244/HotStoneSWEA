package hotstone.TestStubs;

import hotstone.framework.Card;
import hotstone.framework.GenerateDeckStrategy;
import hotstone.framework.MutableCard;
import hotstone.framework.Player;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GenerateDeckDoubleEta implements GenerateDeckStrategy {
    @Override
    public void generateDeck(Player who, HashMap<Player, List<MutableCard>> deck) {
        ArrayList<MutableCard> deck1 = new ArrayList<>();

        for(int i=0; i<2;i++) {
            deck1.add(new CardImpl(GameConstants.BROWN_RICE_CARD, 1, 1, 2, false, who));
            deck1.add(new CardImpl(GameConstants.FRENCH_FRIES_CARD, 1, 2, 1, false, who));
            deck1.add(new CardImpl(GameConstants.GREEN_SALAD_CARD, 2, 2, 3, false, who));
            deck1.add(new CardImpl(GameConstants.TOMATO_SALAD_CARD, 2, 2, 2, false, who));
            deck1.add(new CardImpl(GameConstants.POKE_BOWL_CARD, 3, 2, 3, false, who));
            deck1.add(new CardImpl(GameConstants.PUMPKIN_SOUP_CARD, 4, 2, 7, false, who));
            deck1.add(new CardImpl(GameConstants.NOODLE_SOUP_CARD, 4, 5, 3, false, who));
            deck1.add(new CardImpl(GameConstants.SPRING_ROLLS_CARD, 5, 3, 7, false, who));
            deck1.add(new CardImpl(GameConstants.BAKED_SALMON_CARD, 5, 8, 2, false, who));
            deck1.add(new CardImpl(GameConstants.CHICKEN_CURRY_CARD, 6, 4, 4, false, who));
            deck1.add(new CardImpl(GameConstants.BEEF_BURGER_CARD, 6, 8, 6, false, who));
            deck1.add(new CardImpl(GameConstants.FILET_MIGNON_CARD, 7, 9, 5, false, who));
        }
        deck.put(who,deck1);
    }
}

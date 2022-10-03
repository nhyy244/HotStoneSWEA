package hotstone.variants.delta;

import hotstone.framework.Card;
import hotstone.framework.GenerateDeckStrategy;
import hotstone.framework.Player;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;

import java.util.*;

public class GenerateDeckStrategyEta implements GenerateDeckStrategy {
    @Override
    public void generateDeck(Player who, HashMap<Player, List<Card>> deck) {
        ArrayList<Card> Deck1 = new ArrayList<>();
        ArrayList<Card> deck2 = new ArrayList<>();

        for(int i=0; i<2;i++) {
            Deck1.add(new CardImpl(GameConstants.BROWN_RICE_CARD, 1, 1, 2, false, who));
            Deck1.add(new CardImpl(GameConstants.FRENCH_FRIES_CARD, 1, 2, 1, false, who));
            Deck1.add(new CardImpl(GameConstants.GREEN_SALAD_CARD, 2, 2, 3, false, who));
            Deck1.add(new CardImpl(GameConstants.TOMATO_SALAD_CARD, 2, 2, 2, false, who));
            Deck1.add(new CardImpl(GameConstants.POKE_BOWL_CARD, 3, 2, 3, false, who));
            Deck1.add(new CardImpl(GameConstants.PUMPKIN_SOUP_CARD, 4, 2, 7, false, who));
            Deck1.add(new CardImpl(GameConstants.NOODLE_SOUP_CARD, 4, 5, 3, false, who));
            Deck1.add(new CardImpl(GameConstants.SPRING_ROLLS_CARD, 5, 3, 7, false, who));
            Deck1.add(new CardImpl(GameConstants.BAKED_SALMON_CARD, 5, 8, 2, false, who));
            Deck1.add(new CardImpl(GameConstants.CHICKEN_CURRY_CARD, 6, 4, 4, false, who));
            Deck1.add(new CardImpl(GameConstants.BEEF_BURGER_CARD, 6, 8, 6, false, who));
            Deck1.add(new CardImpl(GameConstants.FILET_MIGNON_CARD, 7, 9, 5, false, who));
        }
        Collections.shuffle(Deck1);

        for(int i = 1; i <= 4; i=i*2) {
            for (Card c: Deck1) {
                if (c.getManaCost() <= i) {
                    deck2.add(c);
                    Deck1.remove(c);
                    break;
                }
            }
        }
        deck2.addAll(Deck1);
        deck.put(who,deck2);
    }
}

package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.GenerateDeckStrategy;
import hotstone.framework.Player;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;

import java.util.*;

public class GenerateDeckDelta implements GenerateDeckStrategy {
    @Override
    public void generateDeck(Player who, HashMap<Player, List<Card>> deck) {
        ArrayList<Card> Deck1 = new ArrayList<>();

        for(int i=0; i<2;i++) {
            Deck1.add(new CardImpl(GameConstants.BROWN_RICE_CARD, 1, 1, 2, false, who));
            Deck1.add(new CardImpl(GameConstants.FRENCH_FRIES_CARD, 1, 2, 1, false, who));
            Deck1.add(new CardImpl(GameConstants.GREEN_SALAD_CARD, 2, 2, 3, false, who));
            Deck1.add(new CardImpl(GameConstants.TOMATO_SALAD_CARD, 2, 3, 2, false, who));
            Deck1.add(new CardImpl(GameConstants.POKE_BOWL_CARD, 3, 2, 4, false, who));
            Deck1.add(new CardImpl(GameConstants.PUMPKIN_SOUP_CARD, 4, 2, 7, false, who));
            Deck1.add(new CardImpl(GameConstants.NOODLE_SOUP_CARD, 4, 5, 3, false, who));
            Deck1.add(new CardImpl(GameConstants.SPRING_ROLLS_CARD, 5, 3, 7, false, who));
            Deck1.add(new CardImpl(GameConstants.BAKED_SALMON_CARD, 5, 8, 2, false, who));
            Deck1.add(new CardImpl(GameConstants.CHICKEN_CURRY_CARD, 6, 8, 4, false, who));
            Deck1.add(new CardImpl(GameConstants.BEEF_BURGER_CARD, 6, 5, 6, false, who));
            Deck1.add(new CardImpl(GameConstants.FILET_MIGNON_CARD, 7, 9, 5, false, who));
        }
        Collections.shuffle(Deck1);
        Card one = cardShuffleBasedOnManaCost(Deck1,1);
        Deck1.remove(one);
        Deck1.add(0,one);

        Card two = cardShuffleBasedOnManaCost(Deck1,2);
        Deck1.remove(two);
        Deck1.add(1,two);

        Card three = cardShuffleBasedOnManaCost(Deck1,4);
        Deck1.remove(three);
        Deck1.add(2,three);

        deck.put(who, Deck1);
    }

    private Card cardShuffleBasedOnManaCost(ArrayList<Card> deck, int manaCost){
        ArrayList<Card>deckShuffled = new ArrayList<>();
        Random r = new Random();
        for (Card card : deck) {
            if (card.getManaCost() <= manaCost) {
                deckShuffled.add(card);
            }
        }
        return deckShuffled.get(r.nextInt(deckShuffled.size()));
    }
}

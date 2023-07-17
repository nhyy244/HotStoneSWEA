package hotstone.variants.custom;

import hotstone.framework.GenerateDeckStrategy;
import hotstone.framework.MutableCard;
import hotstone.framework.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DeltaAdapter implements GenerateDeckStrategy {
    DeckGeneratorCustom customGenerator;

    public DeltaAdapter(){
        this.customGenerator= new DeckGeneratorCustom();
    }



    @Override
    public void generateDeck(Player who, HashMap<Player, List<MutableCard>> deck) {
        ArrayList<MutableCard> Deck1 = customGenerator.generateCustomDeck(who);
        ArrayList<MutableCard> deck2 = new ArrayList<>();

        Collections.shuffle(Deck1);

        for(int i = 1; i <= 4; i=i*2) {
            for (MutableCard c: Deck1) {
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

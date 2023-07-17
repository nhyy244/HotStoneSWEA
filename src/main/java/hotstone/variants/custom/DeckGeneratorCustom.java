package hotstone.variants.custom;

import hotstone.framework.MutableCard;
import hotstone.framework.Player;
import hotstone.standard.CardImpl;
import thirdparty.CardPODO;
import thirdparty.PersonalDeckReader;

import java.util.ArrayList;

public class DeckGeneratorCustom {

    public ArrayList<MutableCard> generateCustomDeck(Player who) {
        PersonalDeckReader reader = new PersonalDeckReader("animaldeck.json");
        ArrayList<MutableCard> myList = new ArrayList<>();
        for(CardPODO card:reader){
            MutableCard cardAdapted = new CardImpl(card.name(),card.mana(),card.attack(),card.health(),false,who);
            myList.add(cardAdapted);
        }
        for(CardPODO card:reader){
            MutableCard cardAdapted = new CardImpl(card.name(),card.mana(),card.attack(),card.health(),false,who);
            myList.add(cardAdapted);
        }
        return myList;
    }
}

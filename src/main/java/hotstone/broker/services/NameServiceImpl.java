package hotstone.broker.services;

import hotstone.framework.Card;
import hotstone.framework.Hero;

import java.util.HashMap;

public class NameServiceImpl implements NameService{

    private HashMap<String,Hero> heroMap;
    private HashMap<String,Card> cardMap;

    public NameServiceImpl(){
        this.heroMap = new HashMap<>();
        this.cardMap=new HashMap<>();
    }
    @Override
    public void putHero(String objectId, Hero hero) {
        heroMap.put(objectId,hero);
    }

    @Override
    public void putCard(String objectId, Card card) {
        cardMap.put(objectId,card);
    }

    @Override
    public Hero getHero(String objectId) {
        return heroMap.get(objectId);
    }

    @Override
    public Card getCard(String objectId) {
        return cardMap.get(objectId);
    }
}

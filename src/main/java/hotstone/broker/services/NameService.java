package hotstone.broker.services;

import hotstone.framework.Card;
import hotstone.framework.Hero;

public interface NameService {

    void putHero(String objectId, Hero hero);
    void putCard(String objectId, Card card);
    Hero getHero(String objectId);
    Card getCard(String objectId);
}

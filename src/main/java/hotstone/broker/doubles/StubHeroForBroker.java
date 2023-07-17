package hotstone.broker.doubles;

import hotstone.broker.services.NameService;
import hotstone.broker.services.NameServiceImpl;
import hotstone.framework.Hero;
import hotstone.framework.Player;

import java.util.UUID;

public class StubHeroForBroker implements Hero {

    private String ID;
    //private NameService nameService = new NameServiceImpl();

    public StubHeroForBroker(){
        ID = UUID.randomUUID().toString();
        //nameService.putHero(ID,this);
    }
    @Override
    public int getMana() {
        return 1;
    }

    @Override
    public int getHealth() {
        return 1;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public String getType() {
        return "hero";
    }

    @Override
    public Player getOwner() {
        return Player.FINDUS;
    }
    public String getID(){
        return ID;
    }

}

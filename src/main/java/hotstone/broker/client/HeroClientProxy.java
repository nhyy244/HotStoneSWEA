package hotstone.broker.client;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotstone.broker.common.OperationNames;
import hotstone.broker.services.NameService;
import hotstone.broker.services.NameServiceImpl;
import hotstone.framework.Hero;
import hotstone.framework.Player;

import java.lang.reflect.Type;
import java.util.UUID;

public class HeroClientProxy implements Hero, ClientProxy {

    private Requestor requestor;
    private String objectID = "singletonHero";
    private String objectId2;
    //private String id;
    //private NameService nameService = new NameServiceImpl();


    public HeroClientProxy(Requestor requestor,String objectId2){
        this.requestor=requestor;
        this.objectId2=objectId2;
        //id = UUID.randomUUID().toString();
    }

    @Override
    public int getMana() {
        return requestor.sendRequestAndAwaitReply(getID(), OperationNames.HERO_GET_MANA,Integer.class);
    }

    @Override
    public int getHealth() {
        return requestor.sendRequestAndAwaitReply(getID(), OperationNames.HERO_GET_HEALTH,Integer.class);
    }

    @Override
    public boolean isActive() {
        return requestor.sendRequestAndAwaitReply(getID(), OperationNames.HERO_IS_ACTIVE,Boolean.class);
    }

    @Override
    public String getType() {
        return requestor.sendRequestAndAwaitReply(getID(), OperationNames.HERO_GET_TYPE,String.class);
    }

    @Override
    public Player getOwner() {
        return requestor.sendRequestAndAwaitReply(getID(), OperationNames.HERO_GET_OWNER,Player.class);
    }

    @Override
    public String getID() {
        return objectId2;
    }
}

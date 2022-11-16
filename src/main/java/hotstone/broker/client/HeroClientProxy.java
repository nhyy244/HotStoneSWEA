package hotstone.broker.client;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Hero;
import hotstone.framework.Player;

import java.lang.reflect.Type;

public class HeroClientProxy implements Hero, ClientProxy {

    private Requestor requestor;
    private String objectID = "singletonHero";


    public HeroClientProxy(Requestor requestor){
        this.requestor=requestor;
    }

    @Override
    public int getMana() {
        return requestor.sendRequestAndAwaitReply(objectID, OperationNames.HERO_GET_MANA,Integer.class);
    }

    @Override
    public int getHealth() {
        return requestor.sendRequestAndAwaitReply(objectID, OperationNames.HERO_GET_HEALTH,Integer.class);
    }

    @Override
    public boolean isActive() {
        return requestor.sendRequestAndAwaitReply(objectID, OperationNames.HERO_IS_ACTIVE,Boolean.class);
    }

    @Override
    public String getType() {
        return requestor.sendRequestAndAwaitReply(objectID, OperationNames.HERO_GET_TYPE,String.class);
    }

    @Override
    public Player getOwner() {
        return requestor.sendRequestAndAwaitReply(objectID, OperationNames.HERO_GET_OWNER,Player.class);
    }
}

package hotstone.broker.client;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import frds.broker.Servant;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Card;
import hotstone.framework.Player;

import java.util.UUID;

public class CardClientProxy implements Card, ClientProxy {

    private Requestor requestor;
    private String objectID ="singletonCard";
    private String id;

    public CardClientProxy(Requestor requestor,String id){
        this.requestor=requestor;
        this.id=id;

    }
    @Override
    public String getName() {
        return requestor.sendRequestAndAwaitReply(getID(), OperationNames.CARD_GET_NAME,String.class);
    }

    @Override
    public int getManaCost() {
        return requestor.sendRequestAndAwaitReply(getID(),OperationNames.CARD_GET_MANA_COST,Integer.class);
    }

    @Override
    public int getAttack() {
        return requestor.sendRequestAndAwaitReply(getID(),OperationNames.CARD_GET_ATTACK,Integer.class);
    }

    @Override
    public int getHealth() {
        return requestor.sendRequestAndAwaitReply(getID(),OperationNames.CARD_GET_HEALTH,Integer.class);
    }

    @Override
    public boolean isActive(){
        return requestor.sendRequestAndAwaitReply(getID(),OperationNames.CARD_IS_ACTIVE,Boolean.class);
    }

    @Override
    public Player getOwner() {
        return requestor.sendRequestAndAwaitReply(getID(),OperationNames.CARD_GET_OWNER,Player.class);
    }

    @Override
    public String getID() {
        return id;
    }
}

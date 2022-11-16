package hotstone.broker.client;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import frds.broker.Servant;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Card;
import hotstone.framework.Player;

public class CardClientProxy implements Card, ClientProxy {

    private Requestor requestor;
    private String objectID ="singletonCard";

    public CardClientProxy(Requestor requestor){
        this.requestor=requestor;
    }
    @Override
    public String getName() {
        return requestor.sendRequestAndAwaitReply(objectID, OperationNames.CARD_GET_NAME,String.class);
    }

    @Override
    public int getManaCost() {
        return requestor.sendRequestAndAwaitReply(objectID,OperationNames.CARD_GET_MANA_COST,Integer.class);
    }

    @Override
    public int getAttack() {
        return requestor.sendRequestAndAwaitReply(objectID,OperationNames.CARD_GET_ATTACK,Integer.class);
    }

    @Override
    public int getHealth() {
        return requestor.sendRequestAndAwaitReply(objectID,OperationNames.CARD_GET_HEALTH,Integer.class);
    }

    @Override
    public boolean isActive(){
        return requestor.sendRequestAndAwaitReply(objectID,OperationNames.CARD_IS_ACTIVE,Boolean.class);
    }

    @Override
    public Player getOwner() {
        return requestor.sendRequestAndAwaitReply(objectID,OperationNames.CARD_GET_OWNER,Player.class);
    }
}

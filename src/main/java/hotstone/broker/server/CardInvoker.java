package hotstone.broker.server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotstone.broker.common.OperationNames;
import hotstone.broker.services.NameService;
import hotstone.framework.Card;
import hotstone.framework.Player;

import javax.servlet.http.HttpServletResponse;

public class CardInvoker implements Invoker {

    private Card card;
    private Gson gson;

    private NameService nameService;

    public CardInvoker(NameService nameService, Gson gson){
        this.nameService=nameService;
        this.gson=gson;
    }
    @Override
    public String handleRequest(String request) {
         RequestObject requestObject = gson.fromJson(request, RequestObject.class);

        //used for getting parameters
        JsonArray arrayJson = JsonParser.parseString(requestObject.getPayload()).getAsJsonArray();

        ReplyObject reply = null;

        try{
            if(requestObject.getOperationName().equals(OperationNames.CARD_GET_NAME)){
                Card nameServiceCard = nameService.getCard(requestObject.getObjectId());
                String name = nameServiceCard.getName();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(name));
            }
            if(requestObject.getOperationName().equals(OperationNames.CARD_GET_ATTACK)){
                Card nameServiceCard = nameService.getCard(requestObject.getObjectId());
                int attack = nameServiceCard.getAttack();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(attack));
            }
            if(requestObject.getOperationName().equals(OperationNames.CARD_GET_HEALTH)){
                Card nameServiceCard = nameService.getCard(requestObject.getObjectId());
                int health = nameServiceCard.getHealth();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(health));
            }
            if(requestObject.getOperationName().equals(OperationNames.CARD_GET_MANA_COST)){
                Card nameServiceCard = nameService.getCard(requestObject.getObjectId());
                int mana = nameServiceCard.getManaCost();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(mana));
            }
            if(requestObject.getOperationName().equals(OperationNames.CARD_GET_OWNER)){
                Card nameServiceCard = nameService.getCard(requestObject.getObjectId());
                Player player = nameServiceCard.getOwner();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(player));
            }
            if(requestObject.getOperationName().equals(OperationNames.CARD_IS_ACTIVE)){
                Card nameServiceCard = nameService.getCard(requestObject.getObjectId());
                boolean active = nameServiceCard.isActive();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(active));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return gson.toJson(reply);
    }
}

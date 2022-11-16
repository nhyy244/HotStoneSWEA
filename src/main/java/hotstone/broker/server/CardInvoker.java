package hotstone.broker.server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Card;
import hotstone.framework.Player;

import javax.servlet.http.HttpServletResponse;

public class CardInvoker implements Invoker {

    private Card card;
    private Gson gson;

    public CardInvoker(Card servant){
        this.card = servant;
        this.gson=new Gson();
    }
    @Override
    public String handleRequest(String request) {
         RequestObject requestObject = gson.fromJson(request, RequestObject.class);

        //used for getting parameters
        JsonArray arrayJson = JsonParser.parseString(requestObject.getPayload()).getAsJsonArray();

        ReplyObject reply = null;

        try{
            if(requestObject.getOperationName().equals(OperationNames.CARD_GET_NAME)){
                String name = card.getName();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(name));
            }
            if(requestObject.getOperationName().equals(OperationNames.CARD_GET_ATTACK)){
                int attack = card.getAttack();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(attack));
            }
            if(requestObject.getOperationName().equals(OperationNames.CARD_GET_HEALTH)){
                int health = card.getHealth();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(health));
            }
            if(requestObject.getOperationName().equals(OperationNames.CARD_GET_MANA_COST)){
                int mana = card.getManaCost();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(mana));
            }
            if(requestObject.getOperationName().equals(OperationNames.CARD_GET_OWNER)){
                Player player = card.getOwner();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(player));
            }
            if(requestObject.getOperationName().equals(OperationNames.CARD_IS_ACTIVE)){
                boolean active = card.isActive();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(active));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return gson.toJson(reply);
    }
}

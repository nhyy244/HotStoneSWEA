package hotstone.broker.server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Hero;
import hotstone.framework.Player;

import javax.servlet.http.HttpServletResponse;

public class HeroInvoker implements Invoker {

    private Hero hero;
    private Gson gson;

    public HeroInvoker(Hero servant){
        this.hero = servant;
        gson = new Gson();
    }
    @Override
    public String handleRequest(String request) {
        RequestObject requestObject = gson.fromJson(request,RequestObject.class);

        //used for getting parameters
        JsonArray arrayJson = JsonParser.parseString(requestObject.getPayload()).getAsJsonArray();

        ReplyObject reply = null;

        try{
            if(requestObject.getOperationName().equals(OperationNames.HERO_GET_HEALTH)){
                int health = hero.getHealth();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(health));
            }
            if(requestObject.getOperationName().equals(OperationNames.HERO_GET_MANA)){
                int mana = hero.getMana();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(mana));
            }
            if(requestObject.getOperationName().equals(OperationNames.HERO_GET_OWNER)){
                Player owner = hero.getOwner();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(owner));
            }
            if(requestObject.getOperationName().equals(OperationNames.HERO_GET_TYPE)){
                String type = hero.getType();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(type));
            }
            if(requestObject.getOperationName().equals(OperationNames.HERO_IS_ACTIVE)){
                boolean active = hero.isActive();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(active));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return gson.toJson(reply);
    }
}

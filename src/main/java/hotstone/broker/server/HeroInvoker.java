package hotstone.broker.server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotstone.broker.common.OperationNames;
import hotstone.broker.doubles.StubGameForBroker;
import hotstone.broker.doubles.StubHeroForBroker;
import hotstone.broker.services.NameService;
import hotstone.broker.services.NameServiceImpl;
import hotstone.framework.Game;
import hotstone.framework.Hero;
import hotstone.framework.Player;

import javax.servlet.http.HttpServletResponse;

public class HeroInvoker implements Invoker {

    private Hero hero;
    private Gson gson;
    private NameService nameService;
    private Game g;

    public HeroInvoker(NameService nameService,Gson gson){
        this.nameService = nameService;
        this.gson=gson;
    }
    @Override
    public String handleRequest(String request) {
        RequestObject requestObject = gson.fromJson(request,RequestObject.class);

        //used for getting parameters
        JsonArray arrayJson = JsonParser.parseString(requestObject.getPayload()).getAsJsonArray();

        ReplyObject reply = null;

        try{
            if(requestObject.getOperationName().equals(OperationNames.HERO_GET_HEALTH)){
                Hero heroNameService = nameService.getHero(requestObject.getObjectId());
                int health = heroNameService.getHealth();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(health));
            }
            if(requestObject.getOperationName().equals(OperationNames.HERO_GET_MANA)){
                Hero heroNameService = nameService.getHero(requestObject.getObjectId());
                int mana = heroNameService.getMana();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(mana));
            }
            if(requestObject.getOperationName().equals(OperationNames.HERO_GET_OWNER)){
                Hero heroNameService = nameService.getHero(requestObject.getObjectId());
                Player owner = heroNameService.getOwner();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(owner));
            }
            if(requestObject.getOperationName().equals(OperationNames.HERO_GET_TYPE)){
                Hero heroNameService = nameService.getHero(requestObject.getObjectId());
                String type = heroNameService.getType();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(type));
            }
            if(requestObject.getOperationName().equals(OperationNames.HERO_IS_ACTIVE)){
                Hero heroNameService = nameService.getHero(requestObject.getObjectId());
                boolean active = heroNameService.isActive();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(active));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return gson.toJson(reply);
    }
}

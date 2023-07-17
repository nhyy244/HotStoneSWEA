package hotstone.broker.server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotstone.broker.common.OperationNames;
import hotstone.broker.doubles.StubCardForBroker;
import hotstone.broker.services.NameService;
import hotstone.broker.services.NameServiceImpl;
import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Hero;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class HotStoneRootInvoker implements Invoker {

    private Game game;
    private Gson gson;
    private final Map<String, Invoker> invokerMap;
    private NameService nameService;


    public HotStoneRootInvoker(Game game){
        this.game=game;
        gson = new Gson();

        nameService = new NameServiceImpl();
        invokerMap = new HashMap<>();

        Invoker cardInvoker = new CardInvoker(nameService,gson);
        invokerMap.put(OperationNames.CARD_PREFIX,cardInvoker);
        Invoker heroInvoker = new HeroInvoker(nameService,gson);
        invokerMap.put(OperationNames.HERO_PREFIX,heroInvoker);
        Invoker hotStoneInvoker = new HotStoneGameInvoker(game,nameService,gson);
        invokerMap.put(OperationNames.GAME_PREFIX,hotStoneInvoker);
    }


    @Override
    public String handleRequest(String request) {
        RequestObject requestObject = gson.fromJson(request, RequestObject.class);

        //used for getting parameters
        String objectId = requestObject.getObjectId();
        String operationName = requestObject.getOperationName();
        String payload = requestObject.getPayload();
        String type = operationName.substring(0,operationName.indexOf(OperationNames.SEPARATOR));
        ReplyObject reply;


        Invoker subInvoker = invokerMap.get(type);
        return subInvoker.handleRequest(request);
    }

}

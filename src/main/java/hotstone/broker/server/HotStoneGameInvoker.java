/*
 * Copyright (C) 2022. Henrik Bærbak Christensen, Aarhus University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package hotstone.broker.server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotstone.broker.common.OperationNames;
import hotstone.broker.doubles.StubHeroForBroker;
import hotstone.broker.services.NameService;
import hotstone.broker.services.NameServiceImpl;
import hotstone.framework.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class HotStoneGameInvoker implements Invoker {
  private Game game;
  private Gson gson;
  private final NameService nameService;

  public HotStoneGameInvoker(Game servant,NameService nameService,Gson gson) {
    this.game = servant;
    this.gson=gson;
    this.nameService=nameService;
  }

  @Override
  public String handleRequest(String request) {
    RequestObject requestObject = gson.fromJson(request, RequestObject.class);

    //used for getting parameters
    JsonArray arrayJson = JsonParser.parseString(requestObject.getPayload()).getAsJsonArray();

    ReplyObject reply;

    try {
      if (requestObject.getOperationName().equals(OperationNames.GAME_GET_TURN_NUMBER)) {
        //EXAMPLE ON HOW TO GET PARAMETERS FROM A METHOD REQUEST.
        //Parameters from the request EXAMPLE. Do nothing if method has no parameters.
        //String parameter = gson.fromJson(arrayJson.get(0),String.class);

        //prepare reply answer
        int turnNumber = game.getTurnNumber();

        //marshall reply answer.
        reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(turnNumber));
        return gson.toJson(reply);
      }
      if(requestObject.getOperationName().equals(OperationNames.GAME_GET_PLAYER_IN_TURN)){
        Player player = game.getPlayerInTurn();
        reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(player));
        return gson.toJson(reply);
      }
      if (requestObject.getOperationName().equals(OperationNames.GAME_GET_WINNER)) {
        Player winner = game.getWinner();
        reply = new ReplyObject(HttpServletResponse.SC_OK,gson.toJson(winner));
        return gson.toJson(reply);
      }
      if (requestObject.getOperationName().equals(OperationNames.GAME_GET_DECK_SIZE)) {
        int deckSize = game.getDeckSize(gson.fromJson(arrayJson.get(0),Player.class));
        reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(deckSize));
        return gson.toJson(reply);
      }
      if (requestObject.getOperationName().equals(OperationNames.GAME_GET_HAND_SIZE)) {
        int handSize = game.getHandSize(gson.fromJson(arrayJson.get(0),Player.class));
        reply = new ReplyObject(HttpServletResponse.SC_OK,gson.toJson(handSize));
        return gson.toJson(reply);
      }
      if (requestObject.getOperationName().equals(OperationNames.GAME_GET_FIELD_SIZE)) {
        int fieldSize = game.getFieldSize(gson.fromJson(arrayJson.get(0),Player.class));
        reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(fieldSize));
        return gson.toJson(reply);
      }
      if (requestObject.getOperationName().equals(OperationNames.GAME_END_OF_TURN)) {
        game.endTurn();
        reply = new ReplyObject(HttpServletResponse.SC_CREATED,"END TURN");
        return gson.toJson(reply);
      }
      if (requestObject.getOperationName().equals(OperationNames.GAME_USE_POWER)) {
        Status usePower = game.usePower(gson.fromJson(arrayJson.get(0),Player.class));
        reply = new ReplyObject(HttpServletResponse.SC_OK,gson.toJson(usePower));
        return gson.toJson(reply);
      }
      if(requestObject.getOperationName().equals(OperationNames.GAME_GET_HERO)){
        Player playerParameter = gson.fromJson(arrayJson.get(0),Player.class);
        Hero hero = game.getHero(playerParameter);
        String id = hero.getID();
        nameService.putHero(id,hero);

        reply = new ReplyObject(HttpServletResponse.SC_OK,gson.toJson(id));
        return gson.toJson(reply);
      }
      if(requestObject.getOperationName().equals(OperationNames.GAME_GET_CARD_IN_FIELD)){
        Player parameterPlayer = gson.fromJson(arrayJson.get(0),Player.class);
        int parameterFieldIndex = gson.fromJson(arrayJson.get(1),Integer.class);
        Card c = game.getCardInField(parameterPlayer,parameterFieldIndex);
        String id = c.getID();
        nameService.putCard(id,c);

        reply = new ReplyObject(HttpServletResponse.SC_OK,gson.toJson(id));
        return gson.toJson(reply);

      }
      if(requestObject.getOperationName().equals(OperationNames.GAME_GET_CARD_IN_HAND)){
        Player parameterPlayer = gson.fromJson(arrayJson.get(0),Player.class);
        int parameterHandIndex = gson.fromJson(arrayJson.get(1),Integer.class);
        Card c = game.getCardInHand(parameterPlayer,parameterHandIndex);
        String id = c.getID();
        nameService.putCard(id,c);

        reply = new ReplyObject(HttpServletResponse.SC_OK,gson.toJson(id));
        return gson.toJson(reply);
      }
      if(requestObject.getOperationName().equals(OperationNames.GAME_PLAY_CARD)){
        Player parameterPlayer = gson.fromJson(arrayJson.get(0), Player.class);
        String idOfCardSeconParam = gson.fromJson(arrayJson.get(1),String.class);
        Card serviceNameCard = nameService.getCard(idOfCardSeconParam);
        Status status = game.playCard(parameterPlayer,serviceNameCard);

        reply = new ReplyObject(HttpServletResponse.SC_OK,gson.toJson(status));
        return gson.toJson(reply);
      }
      if(requestObject.getOperationName().equals(OperationNames.GAME_ATTACK_CARD)){
        Player parameterPlayer = gson.fromJson(arrayJson.get(0), Player.class);
        String idOfCardSeconParam = gson.fromJson(arrayJson.get(1),String.class);
        String idOfCardThirdParam = gson.fromJson(arrayJson.get(2),String.class);
        Card serviceNameCard1 = nameService.getCard(idOfCardSeconParam);
        Card serviceNameCard2 = nameService.getCard(idOfCardThirdParam);
        Status status = game.attackCard(parameterPlayer,serviceNameCard1,serviceNameCard2);

        reply = new ReplyObject(HttpServletResponse.SC_OK,gson.toJson(status));
        return gson.toJson(reply);
      }
      if(requestObject.getOperationName().equals(OperationNames.GAME_ATTACK_HERO)){
        Player parameterPlayer = gson.fromJson(arrayJson.get(0), Player.class);
        String idOfCardSeconParam = gson.fromJson(arrayJson.get(1),String.class);
        Card serviceNameCard1 = nameService.getCard(idOfCardSeconParam);
        Status status = game.attackHero(parameterPlayer,serviceNameCard1);

        reply = new ReplyObject(HttpServletResponse.SC_OK,gson.toJson(status));
        return gson.toJson(reply);
      }
      if(requestObject.getOperationName().equals(OperationNames.GAME_GET_HAND)){
        Player parameterPlayer = gson.fromJson(arrayJson.get(0),Player.class);
        List<String> theListOfIds = new ArrayList<>();
        for(Card c : game.getHand(parameterPlayer)){
          nameService.putCard(c.getID(),c);
          theListOfIds.add(c.getID());

        }
        reply = new ReplyObject(HttpServletResponse.SC_OK,gson.toJson(theListOfIds));

        return gson.toJson(reply);
      }
      if(requestObject.getOperationName().equals(OperationNames.GAME_GET_FIELD)){
        Player parameterPlayer = gson.fromJson(arrayJson.get(0),Player.class);
        List<String> theListOfIds = new ArrayList<>();
        for(Card c : game.getField(parameterPlayer)){
          nameService.putCard(c.getID(),c);
          theListOfIds.add(c.getID());

        }
        reply = new ReplyObject(HttpServletResponse.SC_OK,gson.toJson(theListOfIds));

        return gson.toJson(reply);
      }


    } catch(Exception ignored) {

    }

    return null;
  }

public NameService getNameService(){
    return nameService;
}
}
